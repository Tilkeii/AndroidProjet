package com.example.flo.projetandroid;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.firestore.FirebaseFirestore;

public class ProfilActivity extends AppCompatActivity {

    TextView mNom, mPrenom, mEmail, mTel;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);


        mAuth = FirebaseAuth.getInstance();

        mNom = findViewById(R.id.nomProfilText);
        mPrenom = findViewById(R.id.sportEventDetails);
        mEmail = findViewById(R.id.lieuEventDetails);
        mTel = findViewById(R.id.dateEventDetails);

        mNom.setText(mAuth.getCurrentUser().getDisplayName());
        mPrenom.setText(mAuth.getCurrentUser().getDisplayName());
        mEmail.setText(mAuth.getCurrentUser().getEmail());
        mTel.setText(mAuth.getCurrentUser().getPhoneNumber());

    }

}
