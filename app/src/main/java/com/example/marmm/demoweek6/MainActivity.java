package com.example.marmm.demoweek6;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mFragmentManager = getSupportFragmentManager();
        Fragment listFragment = mFragmentManager.findFragmentById(R.id.list_container);

        if (listFragment == null) {
            listFragment = new MainFragment();
            mFragmentManager.beginTransaction()
                    .add(R.id.list_container, listFragment)
                    .commit();
        }
    }



    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data)
    { Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.list_container);
        fragment.onActivityResult(requestCode, resultCode, data); }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
