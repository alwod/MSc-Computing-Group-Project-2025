package com.example.edinburghtourapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;


import androidx.activity.ComponentActivity;
// Tour selection screen

public class TourMenuActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_menu);

        //Profile icon in navigation bar
        ImageButton btnProfile = findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(v -> {
            Intent profileIntent = new Intent(this, ProfileActivity.class);
            startActivity(profileIntent);
        });

        //Tour buttons
        Button btnBitesPints = findViewById(R.id.btnBitesPints);
        Button btnBookshop   = findViewById(R.id.btnBookshop);
        Button btnCafe       = findViewById(R.id.btnCafe);
        Button btnHistorical = findViewById(R.id.btnHistorical);
        Button btnNature = findViewById(R.id.btnNature);

        //Saved Tours button
        Button btnSavedTours = findViewById(R.id.btnSavedTours);

        // When a button is clicked, get Tour from repository and send to ShowLocationInfoActivity
        btnBitesPints.setOnClickListener(
                v -> openTour(ToursRepository.TOUR_BITES_PINTS)
        );

        btnBookshop.setOnClickListener(
                v -> openTour(ToursRepository.TOUR_BOOKSHOP)
        );

        btnCafe.setOnClickListener(
                v -> openTour(ToursRepository.TOUR_CAFE_LOOP)
        );

        btnHistorical.setOnClickListener(
                v -> openTour(ToursRepository.TOUR_HISTORY)
        );

        btnNature.setOnClickListener(
                v -> openTour(ToursRepository.TOUR_NATURE)
        );

        if (btnSavedTours != null) {
            btnSavedTours.setOnClickListener(v ->
                    startActivity(new Intent(this, SavedToursActivity.class))
            );
        }
    }

    private void openTour(String tourId) {
        Tour tour = ToursRepository.getTour(tourId);
        if (tour == null) return;

        Intent i = new Intent(this, ShowLocationInfoActivity.class);
        i.putExtra("tour", tour);
        startActivity(i);
    }
}
