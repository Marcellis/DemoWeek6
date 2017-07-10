package com.example.marmm.demoweek6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by marmm on 6/6/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_FIRST_NAME = "reminders";
    private static final String DATABASE_EXTENSION = ".db";
    private static final String DATABASE_NAME = DATABASE_FIRST_NAME + DATABASE_EXTENSION;

    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_REMINDERS = "Reminders";
    public static final String REMINDER_ID = "_id";
    public static final String REMINDER_NAME = "reminder";




    // Creating the table

    private static final String DATABASE_CREATE =
            "CREATE TABLE " + TABLE_REMINDERS +
                    "(" +
                    REMINDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    REMINDER_NAME +
                  ");";

    //Constructor
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_REMINDERS);
        onCreate(sqLiteDatabase);
    }

}

