package com.example.android.projecttracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.projecttracker.data.ProjectContract.ProjectEntry;

/**
 * Created by JOAO on 30-Apr-18.
 */

public class ProjectDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "works.db";

    private static final int DATABASE_VERSION = 1;

    public ProjectDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_PROJECTS_TABLE = "CREATE TABLE " + ProjectEntry.TABLE_NAME + "("
                + ProjectEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ProjectEntry.COLUMN_PROJECT_NAME + " TEXT NOT NULL, "
                + ProjectEntry.COLUMN_PROJECT_TYPE + " TEXT NOT NULL, "
                + ProjectEntry.COLUMN_PROJECT_CLIENT + " TEXT NOT NULL, "
                + ProjectEntry.COLUMN_PROJECT_PRICE + " INTEGER NOT NULL)";

        db.execSQL(SQL_CREATE_PROJECTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
