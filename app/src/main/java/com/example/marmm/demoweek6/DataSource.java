package com.example.marmm.demoweek6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marmm on 6/6/17.
 */

public class DataSource {


    private SQLiteDatabase mDatabase;
    private DBHelper mDBHelper;
    private String[] REMINDERS_ALL_COLUMNS = {DBHelper.REMINDER_ID, DBHelper.REMINDER_NAME};

    public DataSource(Context context) {

        mDBHelper = new DBHelper(context);

    }

    // Opens the database to use it
    public void open() {
        mDatabase = mDBHelper.getWritableDatabase();
    }

    // Closes the database when you no longer need it
    public void close() {
        mDBHelper.close();
    }


    public void createReminder(String reminderText) {

        ContentValues values = new ContentValues();
        values.put(DBHelper.REMINDER_NAME, reminderText);
        mDatabase.insert(DBHelper.TABLE_REMINDERS, null, values);

    }


    public List<Reminder> getAllReminders() {

        List<Reminder> reminders = new ArrayList<>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_REMINDERS, REMINDERS_ALL_COLUMNS, null, null, null, null, null);


        if (cursor.moveToFirst()) {
            do {
                Reminder reminder = new Reminder(
                                cursor.getLong(cursor.getColumnIndex(DBHelper.REMINDER_ID)),
                                cursor.getString(cursor.getColumnIndex(DBHelper.REMINDER_NAME)));
                reminders.add(reminder);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return  reminders;
    }



    public void deleteReminder(Long id) {
        mDatabase.delete(DBHelper.TABLE_REMINDERS, DBHelper.REMINDER_ID + " =?",
                new String[]{Long.toString(id)});

    }

    public void updateReminder(Long id, String name) {
        ContentValues args = new ContentValues();
        args.put(DBHelper.REMINDER_NAME, name);
        mDatabase.update(DBHelper.TABLE_REMINDERS, args, DBHelper.REMINDER_ID + "=?",
                new String[]{Long.toString(id)});

    }


}