package com.example.edinburghtourapp;

import java.io.Serializable;

public class TourLocation implements Serializable {

    private String name;        // Place name
    private String description; // Short info for the user
    private double latitude;    // Map latitude
    private double longitude;   // Map longitude

    public TourLocation(String name, String description,
                        double latitude, double longitude) {
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters to use in ShowLocationInfoActivity
    public String getName()        { return name; }
    public String getDescription() { return description; }
    public double getLatitude()    { return latitude; }
    public double getLongitude()   { return longitude; }
}
