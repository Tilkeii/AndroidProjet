package com.example.flo.projetandroid;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener,
        MyEventsFragment.OnFragmentInteractionListener,
        ListEventsFragment.OnFragmentInteractionListener {

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Home");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        ListEventsFragment listEventsFragment = ListEventsFragment.newInstance("test", "test");
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, listEventsFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                ListEventsFragment listEventsFragment = ListEventsFragment.newInstance("test", "test");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, listEventsFragment)
                        .addToBackStack(null)
                        .commit();
                getSupportActionBar().setTitle("Home");
                return true;
            case R.id.navigation_dashboard:
                MyEventsFragment myEventsFragment = MyEventsFragment.newInstance("test", "test");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, myEventsFragment)
                        .addToBackStack(null)
                        .commit();
                getSupportActionBar().setTitle("Dashboard");
                return true;
            case R.id.navigation_notifications:
                //mTextMessage.setText(R.string.title_notifications);
                return true;
        }
        return false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
