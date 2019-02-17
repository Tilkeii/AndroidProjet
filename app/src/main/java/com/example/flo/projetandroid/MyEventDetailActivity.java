package com.example.flo.projetandroid;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyEventDetailActivity extends AppCompatActivity implements View.OnClickListener, OnCompleteListener<QuerySnapshot> {

    Toolbar mToolbar;
    Event mEvent;
    String mIdDocument;
    FirebaseFirestore mFirebaseFirestore;
    Button leaveBtn;
    RecyclerView mRecyclerView;
    UsersInEventAdapter mAdapter;
    TextView mMessDateLimite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_event_detail);

        mToolbar = findViewById(R.id.toolbar_myevent_detail);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Details");

        mFirebaseFirestore = FirebaseFirestore.getInstance();

        mEvent = (Event)getIntent().getSerializableExtra("event");
        mIdDocument = getIntent().getStringExtra("idDocument");

        mMessDateLimite = findViewById(R.id.messageDateLimite);
        mRecyclerView = findViewById(R.id.recyclerViewUserInEvent);

        if(mEvent.getDateLimite().after(Calendar.getInstance().getTime())){

            mMessDateLimite.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);

        } else {
            mMessDateLimite.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);

            leaveBtn = findViewById(R.id.leaveBtn);
            leaveBtn.setOnClickListener(this);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(linearLayoutManager);

            mAdapter = new UsersInEventAdapter(this);
            mRecyclerView.setAdapter(mAdapter);

            for(String userId : mEvent.getUsers()){
                mFirebaseFirestore.collection("user-details").whereEqualTo("userId", userId).get().addOnCompleteListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        mFirebaseFirestore.collection("events").document(mIdDocument).update("users", FieldValue.arrayRemove(FirebaseAuth.getInstance().getUid()))
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "SUCCESS !", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Log.e("TEST", "Error update document: ", task.getException());
                        }
                    }
                });
    }

    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {
        if(task.isSuccessful()){

            QuerySnapshot querySnapshot = task.getResult();

            if(querySnapshot != null) {
                User user = querySnapshot.getDocuments().get(0).toObject(User.class);
                mAdapter.addUser(user);
            }

        }
    }

}
