package com.example.edinburghtourapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

public class SelectTourActivity extends ComponentActivity {

    LatLng testLatLng1 = new LatLng(-34, 151);
    TourLocation testLocation1 = new TourLocation("Test Location 1", testLatLng1);

    LatLng testLatLng2 = new LatLng(-34, 152);
    TourLocation testLocation2 = new TourLocation("Test Location 2", testLatLng2);

    Tour testTour = new Tour("Test Tour", false);

    Button testButton1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_tour);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        testTour.addTour(testLocation1);
        testTour.addTour(testLocation2);

        testButton1 = findViewById(R.id.tour1Button);

        Intent intent = new Intent(this, MapsActivity.class);

        testButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("LatLng", testLocation1.getLatLng());
                startActivity(intent);
            }
        });
    }
}