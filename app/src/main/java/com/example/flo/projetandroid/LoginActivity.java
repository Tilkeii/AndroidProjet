package com.example.flo.projetandroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private static String ANDROID_PROJECT = "ANDROID_PROJECT";
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        Button loginBtn = findViewById(R.id.loginBtn);
        Button registerBtn = findViewById(R.id.registerBtn);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    private void loginEmailPassword(String email, String password){
        if(!email.isEmpty() && !password.isEmpty()){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(ANDROID_PROJECT, "Login initialised");
                            if(task.isSuccessful()){
                                Log.d(ANDROID_PROJECT, "Login successfull");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(LoginActivity.this, "Authentication successfull " + user.getEmail(),
                                        Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Log.d(ANDROID_PROJECT, "Login failed : ", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginBtn:
                loginEmailPassword(emailEditText.getText().toString(), passwordEditText.getText().toString());
                break;
            case R.id.registerBtn:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
