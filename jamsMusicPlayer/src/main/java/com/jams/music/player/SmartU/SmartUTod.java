package com.jams.music.player.SmartU;

import android.content.Context;
import android.os.Handler;

import java.util.Calendar;

/**
 * Created by bidyut on 4/10/15.
 */
public class SmartUTod {
    private String mDaySegment;
    private String tod;
    private int meridiem;
    private Handler handler;
    private Context mContext;

    public SmartUTod(Context context) {
        mContext = context;
    }

    public String getTod() {
        Calendar now = Calendar.getInstance();
        meridiem = now.get(Calendar.AM_PM);

        tod = mDaySegment = getDaySegment(now, meridiem);

        //Toast.makeText(this, "Try the " + mDaySegment + " Music!", Toast.LENGTH_LONG).show();

        return tod;
    }

    public String getDaySegment(Calendar now, int meridiem) {
        int hour = now.get(Calendar.HOUR);

        if (meridiem == Calendar.PM &&
                hour >= 1 &&
                hour < 12) {
            hour += 12;
        }

        if (hour >= 5 && hour < 12) {
            return "Morning";
        } else if (hour >= 12 && hour < 17) {
            return "Afternoon";
        } else if (hour >= 17 && hour < 20) {
            return "Evening";
        } else {
            return "Night";
        }
    }
}
