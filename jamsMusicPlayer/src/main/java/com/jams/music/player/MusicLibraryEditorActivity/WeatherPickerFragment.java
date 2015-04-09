package com.jams.music.player.MusicLibraryEditorActivity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.database.Cursor;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.jams.music.player.Helpers.TypefaceHelper;
import com.jams.music.player.Helpers.UIElementsHelper;
import com.jams.music.player.R;

/**
 * Created by bidyut on 4/2/15.
 */
public class WeatherPickerFragment extends Fragment {
    public static Cursor cursor;
    public static ListView listView;
    private TextView instructions;

    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather_music_library_editor, null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            rootView.setBackground(UIElementsHelper.getBackgroundGradientDrawable(getActivity()));
        } else {
            rootView.setBackgroundDrawable(UIElementsHelper.getBackgroundGradientDrawable(getActivity()));
        }

        cursor = MusicLibraryEditorActivity.dbHelper.getAllUniqueWeather("");
        listView = (ListView) rootView.findViewById(R.id.musicLibraryEditorWeatherListView);
        listView.setFastScrollEnabled(true);
        listView.setAdapter(new MusicLibraryEditorWeatherMultiselectAdapter(getActivity(), cursor));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int which, long dbID) {
                CheckBox checkbox = (CheckBox) view.findViewById(R.id.weatherCheckboxMusicLibraryEditor);
                checkbox.performClick();

				/* Since we've performed a software-click (checkbox.performClick()), all we have
                 * to do now is determine the *new* state of the checkbox. If the checkbox is checked,
				 * that means that the user tapped on it when it was unchecked, and we should add
				 * the weather's songs to the HashSet. If the checkbox is unchecked, that means the user
				 * tapped on it when it was checked, so we should remove the weather's songs from the
				 * HashSet.
				 */
                if (checkbox.isChecked()) {
                    view.setBackgroundColor(0xCC0099CC);
                    MusicLibraryEditorWeatherMultiselectAdapter.AsyncGetWeatherSongIds task = new MusicLibraryEditorWeatherMultiselectAdapter.AsyncGetWeatherSongIds((String) view.getTag(R.string.weather));
                    task.execute(new String[]{"ADD"});
                } else {
                    view.setBackgroundColor(0x00000000);
                    MusicLibraryEditorWeatherMultiselectAdapter.AsyncGetWeatherSongIds task = new MusicLibraryEditorWeatherMultiselectAdapter.AsyncGetWeatherSongIds((String) view.getTag(R.string.weather));
                    task.execute(new String[]{"REMOVE"});
                }

            }

        });

        instructions = (TextView) rootView.findViewById(R.id.weather_music_library_editor_instructions);
        instructions.setTypeface(TypefaceHelper.getTypeface(getActivity(), "RobotoCondensed-Light"));
        instructions.setPaintFlags(instructions.getPaintFlags() | Paint.ANTI_ALIAS_FLAG | Paint.SUBPIXEL_TEXT_FLAG);

        //KitKat translucent navigation/status bar.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            //Calculate navigation bar height.
            int navigationBarHeight = 0;
            int resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                navigationBarHeight = getResources().getDimensionPixelSize(resourceId);
            }

            listView.setClipToPadding(false);
            listView.setPadding(0, 0, 0, navigationBarHeight);
        }

        return rootView;
    }
}
