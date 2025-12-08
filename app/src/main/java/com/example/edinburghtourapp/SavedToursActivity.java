package com.example.edinburghtourapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import androidx.appcompat.app.ComponentActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class SavedToursActivity extends ComponentActivity {

    private LinearLayout savedToursContainer;
    private TextView tvNoSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_tours);

        savedToursContainer = findViewById(R.id.savedToursContainer);
        tvNoSaved = findViewById(R.id.tvNoSaved);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Toast.makeText(this, "Please log in to view saved tours.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseFirestore.getInstance()
                .collection("users")
                .document(uid)
                .get()
                .addOnSuccessListener(doc -> {
                    List<String> savedIds = (List<String>) doc.get("savedTours");

                    if (savedIds == null || savedIds.isEmpty()) {
                        tvNoSaved.setVisibility(View.VISIBLE);
                        return;
                    }

                    tvNoSaved.setVisibility(View.GONE);
                    savedToursContainer.removeAllViews();

                    for (String id : savedIds) {
                        Tour t = ToursRepository.getTour(id);
                        if (t == null) continue;

                        String label = t.getName();
                        if (label == null || label.trim().isEmpty()) {
                            label = id;  // fallback
                        }

                        Button b = new Button(SavedToursActivity.this);
                        b.setAllCaps(false);
                        b.setText(label);
                        b.setTextColor(Color.BLACK);
                        b.setTextSize(16);

                        b.setOnClickListener(v -> {
                            Intent i = new Intent(SavedToursActivity.this, ShowLocationInfoActivity.class);
                            i.putExtra("tour", t);
                            startActivity(i);
                        });

                        savedToursContainer.addView(b);
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to load saved tours.", Toast.LENGTH_SHORT).show();
                });
    }
}
