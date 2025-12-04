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
        i.putExtra("Tour_Object", tour);   // Tour is Serializable
        startActivity(i);
    }

    // ===== Hard-coded tours with coords, have to replace with actual coords =====

    private Tour createBitesPintsTour() {
        Tour t = new Tour("bites_pints", "Bites and Pints Tour", "Food");

        t.addLocation(new TourLocation(
                "Pub One",
                "Try local beer and classic bar snacks.",
                50.9490, -33.1900    // TODO: replace with actual coords
        ));
        t.addLocation(new TourLocation(
                "Pub Two",
                "Traditional Scottish pub with live music.",
                15.9500, -36.1870
        ));

        return t;
    }

    private Tour createBookshopTour() {
        Tour t = new Tour("bookshop", "Bookshop Tour", "Culture");

        t.addLocation(new TourLocation(
                "Old Town Books",
                "Independent bookshop with rare editions.",
                55.9470, -3.1920
        ));
        t.addLocation(new TourLocation(
                "Comics & Tales",
                "Graphic novels, manga and collectibles.",
                55.9460, -3.1880
        ));

        return t;
    }

    private Tour createCafeTour() {
        Tour t = new Tour("cafe", "Cafe Tour", "Food");

        t.addLocation(new TourLocation(
                "Brew Lab Cafe",
                "Specialty coffee and study-friendly space.",
                55.9475, -3.1860
        ));
        t.addLocation(new TourLocation(
                "Cake Corner",
                "Home-made cakes and hot chocolate.",
                55.9482, -3.1840
        ));

        return t;
    }

    private Tour createHistoricalTour() {
        Tour t = new Tour("historical", "Historical Tour", "History");

        t.addLocation(new TourLocation(
                "Edinburgh Castle",
                "Iconic fortress with city views.",
                55.9486, -3.1999
        ));
        t.addLocation(new TourLocation(
                "Royal Mile",
                "Historic street linking castle and palace.",
                55.9495, -3.1900
        ));

        return t;
    }

    private Tour createLocalBusinessTour() {
        Tour t = new Tour("local_business", "Local Businesses Tour", "Shopping");

        t.addLocation(new TourLocation(
                "Crafts & Co.",
                "Handmade gifts from local artists.",
                55.9468, -3.1855
        ));
        t.addLocation(new TourLocation(
                "Family Deli",
                "Family-run deli with local products.",
                55.9473, -3.1835
        ));

        return t;
    }
}