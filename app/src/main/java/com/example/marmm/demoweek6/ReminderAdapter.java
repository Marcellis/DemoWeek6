package com.example.marmm.demoweek6;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static com.example.marmm.demoweek6.MainFragment.INTENT_DETAIL_REMINDER_TEXT;
import static com.example.marmm.demoweek6.MainFragment.INTENT_DETAIL_ROW_NUMBER;
import static com.example.marmm.demoweek6.MainFragment.REQUESTCODE;


/**
 * Created by marmm on 6/5/17.
 */

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ViewHolder> {


    private Context mContext;
    private List<Reminder> mReminders;

    public ReminderAdapter(Context mContext, List<Reminder> mReminders) {
        this.mContext = mContext;
        this.mReminders = mReminders;
    }


    public void updateList (List<Reminder> reminders){
        mReminders.clear();
        mReminders = reminders;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, null);

        // Return a new holder instance
        ReminderAdapter.ViewHolder viewHolder = new ReminderAdapter.ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ReminderAdapter.ViewHolder holder, final int position) {

        final Reminder reminder =  mReminders.get(position);

        holder.textView.setText(reminder.getmReminderText());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, UpdateActivity.class);
                intent.putExtra(INTENT_DETAIL_ROW_NUMBER, reminder.getmReminderID());
                intent.putExtra(INTENT_DETAIL_REMINDER_TEXT, reminder.getmReminderText());
                ((MainActivity) mContext).startActivityForResult(intent, REQUESTCODE);


            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                DataSource dataSource = new DataSource(mContext);
                dataSource.open();
                dataSource.deleteReminder(reminder.getmReminderID());
                dataSource.close();
                mReminders.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,mReminders.size());
                return true;
            }
        });

    }


    @Override
    public int getItemCount() {
        return mReminders.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public View mView;
        public TextView textView;

        //Constructor
        public ViewHolder(View v) {


            super(v);
            textView = (TextView) v.findViewById(android.R.id.text1);
            mView = v;
        }
    }


}
