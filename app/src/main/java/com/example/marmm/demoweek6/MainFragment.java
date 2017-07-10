package com.example.marmm.demoweek6;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    //Local variables
    private List<Reminder> mReminders;
    private EditText mNewReminderText;
    private ReminderAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private DataSource mDataSource;


    //Constants used when calling the detail activity
    public static final String INTENT_DETAIL_ROW_NUMBER = "Row number";
    public static final String INTENT_DETAIL_REMINDER_TEXT = "Reminder text";
    public static final int REQUESTCODE = 2;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);


        //Initialize the local variables

        mNewReminderText = (EditText) view.findViewById(R.id.editText_main);
        mReminders = new ArrayList<Reminder>();

        mDataSource = new DataSource(getActivity());
        mDataSource.open();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);



        updateUI();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get the user text from the textfield
                String text = mNewReminderText.getText().toString();
                // Reminder newReminder = new Reminder(text);

                //Check if some text has been added
                if (!(TextUtils.isEmpty(text))) {
                    //Add the text to the list (datamodel)
                    //mReminders.add(newReminder);

                    mDataSource.createReminder(text);

                    updateUI();

                    //Initialize the EditText for the next item
                    mNewReminderText.setText("");
                } else {
                    //Show a message to the user if the textfield is empty
                    Snackbar.make(view, "Please enter some text in the textfield", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }


        });


        return  view;

    }

    private void updateUI() {
        mReminders = mDataSource.getAllReminders();
        if (mAdapter == null) {
            mAdapter = new ReminderAdapter(getActivity(), mReminders);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.updateList(mReminders);
            mAdapter.notifyDataSetChanged();
        }
    }

    //Process return parameters
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //if (requestCode == REQUESTCODE) {
         //   if (resultCode == RESULT_OK) {

                mDataSource.open();
                mDataSource.updateReminder(data.getLongExtra(INTENT_DETAIL_ROW_NUMBER, -1),data.getStringExtra(INTENT_DETAIL_REMINDER_TEXT ));
                updateUI();
         //   }
       // }
    }


    public void onResume() {
        super.onResume();
        mDataSource.open();

    }

    public void onPause() {
        super.onPause();
        mDataSource.close();

    }



}
