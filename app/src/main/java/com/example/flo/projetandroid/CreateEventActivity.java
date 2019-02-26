package com.example.flo.projetandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


public class CreateEventActivity extends AppCompatActivity {

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        mToolbar = findViewById(R.id.toolbar_create_event);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Creation d'evenement");

    }
}
