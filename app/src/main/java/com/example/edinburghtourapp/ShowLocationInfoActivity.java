package com.example.edinburghtourapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ShowLocationInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_location_info);

        // Get the Tour object that was passed from TourMenuActivity
        Tour selectedTour = (Tour) getIntent().getSerializableExtra("tour");

        System.out.println(selectedTour.getLocations().getFirst().getName());
    }
}