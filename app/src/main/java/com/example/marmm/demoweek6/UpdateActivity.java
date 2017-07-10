package com.example.marmm.demoweek6;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class UpdateActivity extends AppCompatActivity {


    private FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mFragmentManager = getSupportFragmentManager();
        Fragment updateFragment = mFragmentManager.findFragmentById(R.id.update_container);

        if (updateFragment == null) {
            updateFragment =  UpdateFragment.newInstance(getIntent().getLongExtra(MainFragment.INTENT_DETAIL_ROW_NUMBER, -1), getIntent().getStringExtra(MainFragment.INTENT_DETAIL_REMINDER_TEXT));
            mFragmentManager.beginTransaction()
                    .add(R.id.update_container, updateFragment)
                    .commit();
        }
    }

    }

