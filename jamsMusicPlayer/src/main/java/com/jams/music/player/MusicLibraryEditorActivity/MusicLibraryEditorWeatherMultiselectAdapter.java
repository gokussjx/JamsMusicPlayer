package com.jams.music.player.MusicLibraryEditorActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.jams.music.player.DBHelpers.DBAccessHelper;
import com.jams.music.player.Helpers.TypefaceHelper;
import com.jams.music.player.R;
import com.jams.music.player.Utils.Common;

/**
 * Created by bidyut on 4/7/15.
 */
public class MusicLibraryEditorWeatherMultiselectAdapter extends SimpleCursorAdapter {
    private Context mContext;
    private SharedPreferences sharedPreferences;
    private Common mApp;

    public MusicLibraryEditorWeatherMultiselectAdapter(Context context, Cursor cursor) {
        super(context, -1, cursor, new String[]{}, new int[]{}, 0);
        mContext = context;
        sharedPreferences = mContext.getSharedPreferences("com.jams.music.player", Context.MODE_PRIVATE);
        mApp = (Common) mContext.getApplicationContext();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Cursor c = (Cursor) getItem(position);
        SongsListViewHolder holder = null;

        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.music_library_editor_weather_layout, parent, false);
            holder = new SongsListViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.weatherThumbnailMusicLibraryEditor);
            holder.title = (TextView) convertView.findViewById(R.id.weatherNameMusicLibraryEditor);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.weatherCheckboxMusicLibraryEditor);

            convertView.setTag(holder);
        } else {
            holder = (SongsListViewHolder) convertView.getTag();
        }

        final View finalConvertView = convertView;
        final String songId = c.getString(c.getColumnIndex(DBAccessHelper._ID));
        final String songWeather = c.getString(c.getColumnIndex(DBAccessHelper.SONG_WEATHER));
        String songAlbumArtPath = c.getString(c.getColumnIndex(DBAccessHelper.SONG_ALBUM_ART_PATH));

        holder.title.setTypeface(TypefaceHelper.getTypeface(mContext, "RobotoCondensed-Light"));
        holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

        //Set the song title.
        holder.title.setText(songWeather);
        mApp.getImageLoader().displayImage(songAlbumArtPath, holder.image, MusicLibraryEditorActivity.displayImageOptions);

        //Check if the song's DB ID exists in the HashSet and set the appropriate checkbox status.
        if (MusicLibraryEditorActivity.songDBIdsList.contains(songId)) {
            holder.checkBox.setChecked(true);
            convertView.setBackgroundColor(0xCC0099CC);
        } else {
            holder.checkBox.setChecked(false);
            convertView.setBackgroundColor(0x00000000);
        }

        //Set a tag to the row that will attach the weather's name to it.
        convertView.setTag(R.string.weather, songWeather);

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton checkbox, boolean isChecked) {

                if (isChecked) {

                    //Only receive inputs by the user and ignore any system-made changes to the checkbox state.
                    if (checkbox.isPressed()) {
                        finalConvertView.setBackgroundColor(0xCC0099CC);
                        AsyncGetWeatherSongIds task = new AsyncGetWeatherSongIds(songWeather);
                        task.execute(new String[]{"ADD"});
                    }

                } else if (!isChecked) {

                    //Only receive inputs by the user and ignore any system-made changes to the checkbox state.
                    if (checkbox.isPressed()) {
                        finalConvertView.setBackgroundColor(0x00000000);
                        AsyncGetWeatherSongIds task = new AsyncGetWeatherSongIds(songWeather);
                        task.execute(new String[]{"REMOVE"});

                    }

                }

            }

        });

        return convertView;
    }

    static class SongsListViewHolder {
        public ImageView image;
        public TextView title;
        public CheckBox checkBox;
    }

    /**
     * ************************************************************
     * This AsyncTask goes through a specified weather and retrieves
     * every song by the weather and its ID. It then inserts the ID(s)
     * into a HashSet.
     * *************************************************************
     */
    static class AsyncGetWeatherSongIds extends AsyncTask<String, String, String> {

        private String mWeather;

        public AsyncGetWeatherSongIds(String weather) {
            mWeather = weather;
        }

        @Override
        protected String doInBackground(String... params) {
            //Check if the user is adding or removing an weather from the list.
            String operation = params[0];
            if (operation.equals("ADD")) {
                addWeatherToLibrary();
            } else {
                removeWeatherFromLibrary();
            }

            return null;
        }

        public void addWeatherToLibrary() {

            Cursor cursor = MusicLibraryEditorActivity.dbHelper.getAllSongsByWeather(mWeather);
            if (cursor != null && cursor.getCount() > 0) {

                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToPosition(i);
                    MusicLibraryEditorActivity.songDBIdsList.add(cursor.getString(cursor.getColumnIndex(DBAccessHelper._ID)));
                }
                cursor.close();
                cursor = null;

            }

        }

        public void removeWeatherFromLibrary() {

            Cursor cursor = MusicLibraryEditorActivity.dbHelper.getAllSongsByWeather(mWeather);
            if (cursor != null && cursor.getCount() > 0) {

                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToPosition(i);
                    MusicLibraryEditorActivity.songDBIdsList.remove(cursor.getString(cursor.getColumnIndex(DBAccessHelper._ID)));
                }
                cursor.close();
                cursor = null;

            }

        }

    }
}
