package com.example.flo.projetandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener,
        FloatingActionButton.OnClickListener,
        MyEventsFragment.OnFragmentInteractionListener,
        ListEventsFragment.OnFragmentInteractionListener,
        ParametresFragment.OnFragmentInteractionListener {

    private Toolbar mToolbar;
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar_main_activity);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Home");

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        mFloatingActionButton = findViewById(R.id.floatingActionButton);
        mFloatingActionButton.setOnClickListener(this);

        ListEventsFragment listEventsFragment = ListEventsFragment.newInstance();
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
                ListEventsFragment listEventsFragment = ListEventsFragment.newInstance();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, listEventsFragment)
                        .addToBackStack(null)
                        .commit();
                getSupportActionBar().setTitle("Home");
                return true;
            case R.id.navigation_dashboard:
                MyEventsFragment myEventsFragment = MyEventsFragment.newInstance();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, myEventsFragment)
                        .addToBackStack(null)
                        .commit();
                getSupportActionBar().setTitle("Dashboard");
                return true;
            case R.id.navigation_parametres:
                ParametresFragment parametresFragment = ParametresFragment.newInstance();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, parametresFragment)
                        .addToBackStack(null)
                        .commit();
                getSupportActionBar().setTitle("Parametres");
                return true;
        }
        return false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, CreateEventActivity.class);
        startActivity(intent);
    }
}
