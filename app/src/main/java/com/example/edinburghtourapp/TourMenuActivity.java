package com.example.edinburghtourapp;

import androidx.activity.ComponentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class TourMenuActivity extends ComponentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_menu);

        // ---profile icon in Navigation bar  ---
        ImageButton btnProfile = findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(v -> {
            Intent profileIntent = new Intent(this, ProfileActivity.class);
            startActivity(profileIntent);
        });

        // --- Tour buttons ---
        Button btnBitesPints    = findViewById(R.id.btnBitesPints);
        Button btnBookshop      = findViewById(R.id.btnBookshop);
        Button btnCafe          = findViewById(R.id.btnCafe);
        Button btnHistorical    = findViewById(R.id.btnHistorical);
        Button btnLocalBusiness = findViewById(R.id.btnLocalBusiness);

        // When a button is clicked, build the Tour and send to ShowLocationInfoActivity
        btnBitesPints.setOnClickListener(v -> openTour(createBitesPintsTour()));
        btnBookshop.setOnClickListener(v -> openTour(createBookshopTour()));
        btnCafe.setOnClickListener(v -> openTour(createCafeTour()));
        btnHistorical.setOnClickListener(v -> openTour(createHistoricalTour()));
        btnLocalBusiness.setOnClickListener(v -> openTour(createLocalBusinessTour()));
    }

    // Opens the next screen with the selected Tour
    private void openTour(Tour tour) {
        Intent i = new Intent(this, ShowLocationInfoActivity.class);
        i.putExtra("tour", tour);   // Tour is Serializable
        startActivity(i);
    }

    // ===== Hard-coded tours with coords, have to replace with actual coords =====

    private Tour createBitesPintsTour() {
        Tour t;

        return t;
    }

    private Tour createBookshopTour() {
        Tour t;

        return t;
    }

    private Tour createCafeTour() {
        Tour t;

        return t;
    }

    private Tour createHistoricalTour() {
        Tour t;

        return t;
    }

    private Tour createLocalBusinessTour() {
        Tour t;

        return t;
    }
}