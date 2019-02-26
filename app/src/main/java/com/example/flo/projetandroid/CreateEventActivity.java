package com.example.flo.projetandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CreateEventActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar mToolbar;
    EditText mTitre, mSport, mLieu, mDate, mDateLimite;
    Button mCancel, mConfirm;
    FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        mToolbar = findViewById(R.id.toolbar_create_event);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Creation d'evenement");

        mFirestore = FirebaseFirestore.getInstance();

        mTitre = findViewById(R.id.titre_create_event);
        mSport = findViewById(R.id.sport_create_event);
        mLieu = findViewById(R.id.lieu_create_event);
        mDate = findViewById(R.id.date_create_event);
        mDateLimite = findViewById(R.id.date_limite_create_event);

        mConfirm = findViewById(R.id.confirmer_create_event);
        mCancel = findViewById(R.id.cancel_btn);

        mConfirm.setOnClickListener(this);
        mCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.confirmer_create_event:
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                try {
                    Date date = dateFormat.parse(mDate.getText().toString());
                    Date dateLimite = dateFormat.parse(mDateLimite.getText().toString());
                    Event event = new Event(
                            mTitre.getText().toString(),
                            mSport.getText().toString(),
                            mLieu.getText().toString(),
                            new ArrayList<String>(),
                            date,
                            dateLimite
                    );
                    mFirestore.collection("events").add(event);
                    finish();
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.cancel_btn:
                finish();
                break;
        }
    }
}
