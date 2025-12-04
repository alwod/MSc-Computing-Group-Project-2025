package com.example.edinburghtourapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.ComponentActivity;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ShowLocationInfoActivity extends ComponentActivity {
    Tour tour;
    TourLocation currentStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get tour object
        tour = (Tour) getIntent().getSerializableExtra("Tour_Object");

        // Stuff required for the ui
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_location_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Store the first-most stop in the tour
        currentStop = tour.getLocations().getFirst();

        // Set ui text boxes
        TextView nameText = (TextView) findViewById(R.id.locationNameText);
        TextView descriptionText = (TextView) findViewById(R.id.locationDescriptionText);
        nameText.setText(currentStop.getName());
        descriptionText.setText(currentStop.getDescription());

        // Create buttons
        Button exitButton = (Button) findViewById(R.id.menuButton);
        Button directionsButton = (Button) findViewById(R.id.directionsButton);

        // If exit button is pressed, go back to main menu
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTourMenu();
            }
        });

        // If next directions button is pressed
        directionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMap();
            }
        });

    } // End of onCreate method

    // Handles sending the tour object to MapsActivity
    public void goToMap() {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("Tour_Object", tour);

        startActivity(intent);
    } // End of goToMap method

    public void goToTourMenu() {
        Intent intent = new Intent(this, TourMenuActivity.class);

        startActivity(intent);
    }
} // End of ShowLocationInfoActivity