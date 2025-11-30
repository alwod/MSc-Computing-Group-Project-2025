package com.example.edinburghtourapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.model.LatLng;

import java.util.LinkedList;

public class ShowLocationInfoActivity extends AppCompatActivity {

    Tour tour;
    TourLocation currentStop;
    boolean isFirstStop = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_location_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        currentStop = tour.getTourLocations().getFirst();

        TextView nameText = (TextView) findViewById(R.id.locationNameText);
        TextView descriptionText = (TextView) findViewById(R.id.locationDescriptionText);
        nameText.setText(currentStop.getName());
        descriptionText.setText(currentStop.getDescription());

        Button exitButton = (Button) findViewById(R.id.menuButton);
        Button directionsButton = (Button) findViewById(R.id.directionsButton);

        // If exit button is pressed, go back to main menu
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO either go back to main menu or close app
            }
        });



        // If next directions button is pressed
        directionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO send the tour object to MapsActivity
            }
        });

    } // End of onCreate method

    public void initialiseTour() {

    }
} // End of class