package com.example.edinburghtourapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ComponentActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class ShowLocationInfoActivity extends ComponentActivity {

    private Tour tour;
    private int currentIndex = 0;

    private TextView tvTourName;
    private TextView tvStopTitle;
    private TextView tvCategory;
    private TextView tvDescription;
    private TextView tvCounter;
    private Button btnPrev;
    private Button btnViewOnMap;
    private Button btnSaveTour;
    private Button btnNext;
    private Button btnBackToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_location_info);

        // Get Tour from Intent
        tour = (Tour) getIntent().getSerializableExtra("tour");
        if (tour == null) {
            finish();
            return;
        }

        // Link Java variables to XML views
        tvTourName   = findViewById(R.id.tvTourName);
        tvStopTitle  = findViewById(R.id.tvStopTitle);
        tvCategory   = findViewById(R.id.tvCategory);
        tvDescription = findViewById(R.id.tvDescription);
        tvCounter    = findViewById(R.id.tvCounter);
        btnPrev      = findViewById(R.id.btnPrev);
        btnNext      = findViewById(R.id.btnNext);
        btnViewOnMap = findViewById(R.id.btnViewOnMap);
        btnBackToMenu = findViewById(R.id.btnBackToMenu);
        btnSaveTour = findViewById(R.id.btnSaveTour);


        tvTourName.setText(tour.getName());
        tvCategory.setText(tour.getCategory());

        btnPrev.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                showCurrentStop();
            }
        });

        btnNext.setOnClickListener(v -> {
            if (currentIndex < tour.getStops().size() - 1) {
                currentIndex++;
                showCurrentStop();
            }
        });

        btnViewOnMap.setOnClickListener(v -> {
            TourLocation loc = tour.getStops().get(currentIndex);

            Intent intent = new Intent(ShowLocationInfoActivity.this, MapsActivity.class);
            intent.putExtra("location_name", loc.getTitle());
            intent.putExtra("latitude",     loc.getLatitude());
            intent.putExtra("longitude",    loc.getLongitude());
            startActivity(intent);
        });
        // Show the first stop
        showCurrentStop();

        btnSaveTour.setOnClickListener(v -> {
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

            FirebaseFirestore.getInstance()
                    .collection("users")
                    .document(uid)
                    .update("savedTours", FieldValue.arrayUnion(tour.getId()))
                    .addOnSuccessListener(unused ->
                            Toast.makeText(this, "Tour saved!", Toast.LENGTH_SHORT).show()
                    )
                    .addOnFailureListener(e ->
                            Toast.makeText(this, "Failed to save tour", Toast.LENGTH_SHORT).show()
                    );
        });

        btnBackToMenu.setOnClickListener(v -> {
            finish();
        });
    }

    private void showCurrentStop() {
        TourLocation loc = tour.getStops().get(currentIndex);

        tvStopTitle.setText(loc.getTitle());
        tvDescription.setText(loc.getDescription());

        String counterText = (currentIndex + 1) + " / " + tour.getStops().size();
        tvCounter.setText(counterText);

        btnPrev.setEnabled(currentIndex > 0);
        btnNext.setEnabled(currentIndex < tour.getStops().size() - 1);
    }
}
