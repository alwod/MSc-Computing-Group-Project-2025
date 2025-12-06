package com.example.edinburghtourapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowLocationInfoActivity extends AppCompatActivity {

    private Tour tour;
    private int currentIndex = 0;

    private TextView tvTourName;
    private TextView tvCategory;
    private TextView tvStopTitle;
    private TextView tvDescription;
    private TextView tvCounter;
    private Button btnPrev, btnNext;

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
        tvCategory   = findViewById(R.id.tvCategory);
        tvStopTitle  = findViewById(R.id.tvStopTitle);
        tvDescription = findViewById(R.id.tvDescription);
        tvCounter    = findViewById(R.id.tvCounter);
        btnPrev      = findViewById(R.id.btnPrev);
        btnNext      = findViewById(R.id.btnNext);
        btnViewOnMap = findViewById(R.id.btnViewOnMap);

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
