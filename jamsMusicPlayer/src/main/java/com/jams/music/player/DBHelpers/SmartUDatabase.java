package com.jams.music.player.DBHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.jams.music.player.Utils.Common;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by bidyut on 3/26/15.
 */
public class SmartUDatabase extends SQLiteAssetHelper {

    //Database instance. Will last for the lifetime of the application.
    private static SmartUDatabase sInstance;
    //Writable database instance.
    private SQLiteDatabase mDatabase;
    //Commmon utils object.
    private Common mApp;

    private static final String DATABASE_NAME = "SmartDBSample.db";
    private static final int DATABASE_VERSION = 1;
    public static final String SMARTU_TABLE = "SmartDBSample";
    public static final String SONG_TITLE = "title";
    public static final String SONG_WEATHER = "weather";
    public static final String SONG_BPM = "bpm";
    public static final String SONG_TOD = "tod";

    //Song source values.
    public static final String GMUSIC = "gmusic";
    public static final String LOCAL = "local";

    public SmartUDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mApp = (Common) context.getApplicationContext();
    }

    /**
     * Returns a singleton instance for the database.
     *
     * @param context
     * @return
     */
    public static synchronized SmartUDatabase getInstance(Context context) {
        if (sInstance == null)
            sInstance = new SmartUDatabase(context.getApplicationContext());

        return sInstance;
    }

    /**
     * Returns a writable instance of the database. Provides an additional
     * null check for additional stability.
     */
    private synchronized SQLiteDatabase getDatabase() {
        if (mDatabase == null)
            mDatabase = getWritableDatabase();

        return mDatabase;
    }

    public Cursor getSmartCols() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //String[] sqlSelect = {"title", "weather", "bpm", "tod"};
        //String sqlTables = "SmartDBSample";

        qb.setTables(SMARTU_TABLE);
//        Cursor c = qb.query(db, sqlSelect, null, null,
//                null, null, null);
        String selectQuery = "SELECT  * FROM " + SMARTU_TABLE;

        Cursor c = db.rawQuery(selectQuery, null);

        c.moveToFirst();
        return c;
    }
}
