package com.jams.music.player.SmartUFragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jams.music.player.Helpers.UIElementsHelper;
import com.jams.music.player.R;
import com.jams.music.player.Utils.Common;

/**
 * Created by bidyut on 3/25/15.
 */
public class SmartUFragment extends Fragment {
    //Context.
    private Context mContext;
    private SmartUFragment mSmartUFragment;
    private Common mApp;

    //UI Elements.
    private ListView listView;

    //Handler.
    private Handler mHandler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.test, container, false);
        mContext = getActivity().getApplicationContext();
        mSmartUFragment = this;
        mApp = (Common) mContext;


        listView = (ListView) rootView.findViewById(R.id.folders_list_view);
        listView.setFastScrollEnabled(true);
        listView.setVisibility(View.INVISIBLE);

        //Set the background color based on the theme.
        rootView.setBackgroundColor(UIElementsHelper.getBackgroundColor(mContext));

        //Apply the ListView params.
        //Apply the ListViews' dividers.
        if (mApp.getCurrentTheme() == Common.DARK_THEME) {
            listView.setDivider(mContext.getResources().getDrawable(R.drawable.icon_list_divider));
        } else {
            listView.setDivider(mContext.getResources().getDrawable(R.drawable.icon_list_divider_light));
        }

        listView.setDividerHeight(1);

        //KitKat translucent navigation/status bar.
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            int topPadding = Common.getStatusBarHeight(mContext);

            //Calculate navigation bar height.
            int navigationBarHeight = 0;
            int resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                navigationBarHeight = getResources().getDimensionPixelSize(resourceId);
            }

            rootView.setPadding(0, topPadding, 0, 0);

            listView.setClipToPadding(false);
            listView.setPadding(0, 0, 0, navigationBarHeight);
        }

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mContext = null;
        listView = null;

    }
}
