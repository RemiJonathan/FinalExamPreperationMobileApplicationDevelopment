package com.remijonathan.finalexampreperationmobileapplicationdevelopment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.remijonathan.finalexampreperationmobileapplicationdevelopment.ProjectContract.*;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "holidayList.db";
    public static final int DATABASE_VERSION = 1;

    //Only use the context constructor, not the other ones
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_HOLIDAYLIST_TABLE = "CREATE TABLE " +
                HolidaysEntry.TABLE_NAME + " (" +
                HolidaysEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HolidaysEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                HolidaysEntry.COLUMN_DATE + " TEXT NOT NULL" +
                ");";

        db.execSQL(SQL_CREATE_HOLIDAYLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+HolidaysEntry.TABLE_NAME);
        onCreate(db);
    }
}
