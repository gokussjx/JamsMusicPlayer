package com.jams.music.player.PlaylistEditorActivity;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.jams.music.player.Helpers.TypefaceHelper;
import com.jams.music.player.R;
import com.jams.music.player.Utils.Common;

/**
 * Created by bidyut on 4/7/15.
 */
public class WeatherPickerFragment extends Fragment {
    public static Cursor cursor;
    public static ListView listView;
    private Context mContext;
    private Common mApp;
    private TextView instructions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity().getApplicationContext();
        mApp = (Common) getActivity().getApplicationContext();
        View rootView = inflater.inflate(R.layout.fragment_weather_music_library_editor, null);

        cursor = mApp.getDBAccessHelper().getAllUniqueWeather("");
        listView = (ListView) rootView.findViewById(R.id.musicLibraryEditorWeatherListView);
        listView.setFastScrollEnabled(true);
        listView.setAdapter(new PlaylistEditorWeatherMultiselectAdapter(getActivity(), cursor));

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
                    PlaylistEditorWeatherMultiselectAdapter.AsyncGetWeatherSongIds task = new PlaylistEditorWeatherMultiselectAdapter.AsyncGetWeatherSongIds(mContext, (String) view.getTag(R.string.weather));
                    task.execute(new String[]{"ADD"});
                } else {
                    view.setBackgroundColor(0x00000000);
                    PlaylistEditorWeatherMultiselectAdapter.AsyncGetWeatherSongIds task = new PlaylistEditorWeatherMultiselectAdapter.AsyncGetWeatherSongIds(mContext, (String) view.getTag(R.string.weather));
                    task.execute(new String[]{"REMOVE"});
                }

            }

        });

        instructions = (TextView) rootView.findViewById(R.id.weather_music_library_editor_instructions);
        instructions.setTypeface(TypefaceHelper.getTypeface(getActivity(), "RobotoCondensed-Light"));
        instructions.setPaintFlags(instructions.getPaintFlags() | Paint.ANTI_ALIAS_FLAG | Paint.SUBPIXEL_TEXT_FLAG);

        return rootView;
    }
}
