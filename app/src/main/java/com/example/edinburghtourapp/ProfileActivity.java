package com.example.edinburghtourapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.ComponentActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends ComponentActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private EditText etFirst, etLast;
    private TextView tvEmail;
    private Spinner spMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        // If not logged in, go to auth screen
        if (user == null) {
            startActivity(new Intent(this, AuthActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_profile);

        // Init views
        etFirst = findViewById(R.id.etFirst);
        etLast  = findViewById(R.id.etLast);
        tvEmail = findViewById(R.id.tvEmail);
        spMethod = findViewById(R.id.spMethod);

        Button btnSave   = findViewById(R.id.btnSave);
        Button btnLogout = findViewById(R.id.btnLogout);
        Button btnBackToMenuProfile = findViewById(R.id.btnBackToMenuProfile);

        // Show email
        tvEmail.setText(user.getEmail());


        if (spMethod.getAdapter() == null) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                    this, R.array.travel_methods, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spMethod.setAdapter(adapter);
        }

        db = FirebaseFirestore.getInstance();
        DocumentReference doc = db.collection("users").document(user.getUid());

        // Load profile
        doc.get().addOnSuccessListener(snap -> {
            if (snap.exists()) {
                etFirst.setText(snap.getString("firstName"));
                etLast.setText(snap.getString("lastName"));

                String method = snap.getString("preferredMethod");
                if (method != null) selectSpinnerValue(method);
            } else {
                Map<String, Object> base = new HashMap<>();
                base.put("email", user.getEmail());
                base.put("createdAt", FieldValue.serverTimestamp());
                doc.set(base);
            }
        });

        // Save button
        btnSave.setOnClickListener(v -> {
            String first = etFirst.getText().toString().trim();
            String last  = etLast.getText().toString().trim();
            String method = spMethod.getSelectedItem() != null
                    ? spMethod.getSelectedItem().toString() : null;

            Map<String, Object> data = new HashMap<>();
            data.put("firstName", first);
            data.put("lastName", last);
            data.put("preferredMethod", method);
            data.put("updatedAt", FieldValue.serverTimestamp());

            doc.set(data, SetOptions.merge())
                    .addOnSuccessListener(x -> {
                        toast("Saved");
                        Intent i = new Intent(ProfileActivity.this, TourMenuActivity.class);
                        startActivity(i);
                        finish();
                    })
                    .addOnFailureListener(e -> toast(e.getMessage()));
        });

        // Logout > back to AuthActivity
        btnLogout.setOnClickListener(v -> {
            auth.signOut();
            startActivity(new Intent(this, AuthActivity.class));
            finish();
        });

        //Back to Tour Menu
        btnBackToMenuProfile.setOnClickListener(v -> {
            Intent i = new Intent(ProfileActivity.this, TourMenuActivity.class);
            startActivity(i);
            finish();
        });
    }

    private void selectSpinnerValue(String value) {
        ArrayAdapter<?> ad = (ArrayAdapter<?>) spMethod.getAdapter();
        for (int i = 0; i < ad.getCount(); i++) {
            if (value.equals(ad.getItem(i))) {
                spMethod.setSelection(i);
                break;
            }
        }
    }

    private void toast(String m) { Toast.makeText(this, m, Toast.LENGTH_SHORT).show(); }
}
