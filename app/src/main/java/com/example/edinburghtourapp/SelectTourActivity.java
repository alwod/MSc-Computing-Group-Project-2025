package com.example.edinburghtourapp;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.model.LatLng;

public class SelectTourActivity extends AppCompatActivity {

    LatLng testLatLng1 = new LatLng(0, 0);
    TourLocation testLocation1 = new TourLocation("Test Location 1", testLatLng1);

    LatLng testLatLng2 = new LatLng(1, 1);
    TourLocation testLocation2 = new TourLocation("Test Location 2", testLatLng2);

    //Tour testTour = new Tour("Test Tour", false, );


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

        View text = findViewById(R.id.tour1);

        //text.setOnClickListener();



    }
}