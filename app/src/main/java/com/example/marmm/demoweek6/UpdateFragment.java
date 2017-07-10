package com.example.marmm.demoweek6;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment {


    //Local variables
    private EditText mReminderView;
    private long mPosInDatabase;


    public static UpdateFragment newInstance(long rownum, String text) {
        UpdateFragment fragment = new UpdateFragment();
        Bundle args = new Bundle();
        args.putLong (MainFragment.INTENT_DETAIL_ROW_NUMBER, rownum);
        args.putString(MainFragment.INTENT_DETAIL_REMINDER_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }


    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_update, container, false);

        mReminderView = (EditText) view.findViewById(R.id.editText_update);


        Bundle args = getArguments();
        long rownum= args.getLong (MainFragment.INTENT_DETAIL_ROW_NUMBER, 0);
        String text = args.getString(MainFragment.INTENT_DETAIL_REMINDER_TEXT);

        mReminderView.setText(text);

        //Obtain the parameters provided by MainActivity
        mPosInDatabase =  rownum;
        //If no "position in list" can be found, the default value is -1. This could be used to recognize an issue.




        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View view) {
                //Return entered data to MainActivity (if not empty, else throw a snackbar message
                String updatedReminderText = mReminderView.getText().toString();
                if (updatedReminderText.length() != 0) {
                    //Prepare the return parameter and return
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(MainFragment.INTENT_DETAIL_ROW_NUMBER, mPosInDatabase);
                    resultIntent.putExtra(MainFragment.INTENT_DETAIL_REMINDER_TEXT,mReminderView.getText().toString());
                    getActivity().setResult(Activity.RESULT_OK, resultIntent);
                    getActivity().finish();
                } else {
                    Snackbar.make(view, "Enter some data", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });


        return view;

    }

    }


