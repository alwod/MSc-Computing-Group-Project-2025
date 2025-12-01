package com.example.edinburghtourapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.ComponentActivity;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.model.LatLng;

import org.parceler.Parcels;

import java.util.LinkedList;

public class ShowLocationInfoActivity extends ComponentActivity {

    Tour tour;
    TourLocation currentStop;
    boolean isFirstStop = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get tour object
        Parcelable parcelable = getIntent().getParcelableExtra("Tour_Object");
        tour = Parcels.unwrap(parcelable);

        createTestTour();

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
                finish();
                System.exit(0);
            }
        });



        // If next directions button is pressed
        directionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO send the tour object to MapsActivity
                goToMap();
            }
        });

    } // End of onCreate method

    public void goToMap() {
        Parcelable parcelable = Parcels.wrap(tour);

        Intent fromInfoToMap = new Intent(this, MapsActivity.class);
        fromInfoToMap.putExtra("Tour_Object", parcelable);

        startActivity(fromInfoToMap);
    }

    public void createTestTour() {
        TourLocation testLocation1 = new TourLocation(0, "Napier Merchiston", "Our Uni", new LatLng(55.933144, -3.212863));
        TourLocation testLocation2 = new TourLocation(1, "Tesco", "The tesco near the uni", new LatLng(55.934010, -3.210572));

        tour = new Tour("Test Tour", false);
        tour.addLocation(testLocation1);
        tour.addLocation(testLocation2);
    }
} // End of class