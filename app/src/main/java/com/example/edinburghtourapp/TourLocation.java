package com.example.edinburghtourapp;

import com.google.android.gms.maps.model.LatLng;

public class TourLocation {
    // Instance variables
    private String name;
    private LatLng latLng;



    // Constructors
    public TourLocation() {
        // Set the default location to sydney in australia, for no real reason other than it's obivously incorrect; and easier to notice
        String defaultName = "Sydney";
        LatLng defaultCoords = new LatLng(-34, 151);

        setName(defaultName);
        setLatLng(defaultCoords);
    }

    public TourLocation(String name, LatLng latLng) {
        setName(name);
        setLatLng(latLng);
    }


    public String toString() {
        String output = "";

        output += "The name of the location is " + name + "\n";
        output += "The latitude and longitude of the location is " + latLng + "\n";

        return output;
    }


    // Getters and Setters
    public LatLng getLatLng() {
        return this.latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
