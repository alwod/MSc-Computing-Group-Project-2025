package com.example.edinburghtourapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    private EditText etFirst, etLast, etEmail, etPassword, etConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();
        db   = FirebaseFirestore.getInstance();

        etFirst   = findViewById(R.id.etFirst);
        etLast    = findViewById(R.id.etLast);
        etEmail   = findViewById(R.id.etEmail);
        etPassword= findViewById(R.id.etPassword);
        etConfirm = findViewById(R.id.etConfirm);
        Button btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(v -> doSignup());
    }

    private void doSignup() {
        String first = etFirst.getText().toString().trim();
        String last  = etLast.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String pass  = etPassword.getText().toString();
        String conf  = etConfirm.getText().toString();

        if (TextUtils.isEmpty(first) || TextUtils.isEmpty(last)) { toast("Enter your name"); return; }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) { toast("Enter a valid email"); return; }
        if (pass.length() < 8) { toast("Password must be at least 8"); return; }
        if (!pass.equals(conf)) { toast("Passwords do not match"); return; }

        auth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener(r -> {
            String uid = r.getUser() != null ? r.getUser().getUid() : null;
            if (uid == null) { toast("Sign up error"); return; }

            Map<String,Object> profile = new HashMap<>();
            profile.put("firstName", first);
            profile.put("lastName",  last);
            profile.put("email",     email);
            profile.put("method",    "Walking"); // default

            db.collection("users").document(uid)
                    .set(profile, SetOptions.merge())
                    .addOnSuccessListener(x -> {
                        toast("Thank you for signing up! Returning to login.");
                        finish(); // go back to AuthActivity
                    })
                    .addOnFailureListener(e -> toast("Account created, profile save failed"));
        }).addOnFailureListener(e -> toast(e.getMessage()));
    }

    private void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
