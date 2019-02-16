package com.example.flo.projetandroid;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class EventDetailActivity extends AppCompatActivity implements View.OnClickListener, OnCompleteListener {

    TextView mLieu, mSport, mTitre, mDate, mDateLimite;
    Button mRejoindre;
    Event mEvent;
    String mIdDocument;
    FirebaseFirestore mFirebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        mFirebaseFirestore = FirebaseFirestore.getInstance();

        mEvent = (Event)getIntent().getSerializableExtra("event");
        mIdDocument = getIntent().getStringExtra("idDocument");

        mTitre = findViewById(R.id.titreEventDetails);
        mSport = findViewById(R.id.sportEventDetails);
        mLieu = findViewById(R.id.lieuEventDetails);
        mDate = findViewById(R.id.dateEventDetails);
        mDateLimite = findViewById(R.id.dateLimiteEventDetails);
        mRejoindre = findViewById(R.id.rejoindreBtn);

        mTitre.setText(mEvent.getTitre());
        mSport.setText(mEvent.getSport());
        mLieu.setText(mEvent.getLieu());
        mDate.setText(mEvent.getDate().toString());
        mDateLimite.setText(mEvent.getDateLimite().toString());

        mRejoindre.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mFirebaseFirestore.collection("events").document(mIdDocument).update("users", FieldValue.arrayUnion(FirebaseAuth.getInstance().getUid()))
                .addOnCompleteListener(this);

    }

    @Override
    public void onComplete(@NonNull Task task) {
        if(task.isSuccessful()){
            Toast.makeText(this, "SUCCESS !", Toast.LENGTH_LONG).show();
        } else {
            Log.e("TEST", "Error update document: ", task.getException());
        }
    }
}
