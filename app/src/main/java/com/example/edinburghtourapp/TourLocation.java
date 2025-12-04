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

    public String toString() {
        String output = "";

        output += "Location name: " + getName() + "\n";
        output += "Location description: " + getDescription() + "\n";
        output += "Coordinates: " + getLatitude() + " latitude, " + getLongitude() + "longitude\n";

        return output;
    }

    // Getters to use in ShowLocationInfoActivity
    public String getName()        { return this.name; }
    public String getDescription() { return this.description; }
    public double getLatitude()    { return this.latitude; }
    public double getLongitude()   { return this.longitude; }
}
