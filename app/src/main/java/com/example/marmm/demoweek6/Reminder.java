package com.example.marmm.demoweek6;

/**
 * Created by marmm on 6/5/17.
 */

public class Reminder {




    private String mReminderText;
    private long mReminderID;


    public long getmReminderID() {
        return mReminderID;
    }

    public void setmReminderID(int mReminderID) {
        this.mReminderID = mReminderID;
    }

    public Reminder(long mReminderID, String mReminderText) {
        this.mReminderText = mReminderText;
        this.mReminderID = mReminderID;

    }


    public String getmReminderText() {
        return mReminderText;
    }

    public void setmReminderText(String mReminderText) {
        this.mReminderText = mReminderText;
    }


    @Override
    public String toString() {
        return mReminderText;

    }
}
