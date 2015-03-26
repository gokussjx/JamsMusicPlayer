package com.jams.music.player.DBHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by bidyut on 3/26/15.
 */
public class SmartUDatabase extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "SmartDBSample.db";
    private static final int DATABASE_VERSION = 1;

    public SmartUDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Cursor getWeathers() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Title", "Weather", "Bpm", "Tod"};
        String sqlTables = "SmartDBSample";

        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, null, null,
                null, null, null);

        c.moveToFirst();
        return c;
    }
}
