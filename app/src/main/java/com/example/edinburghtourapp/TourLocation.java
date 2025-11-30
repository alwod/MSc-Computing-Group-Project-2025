package com.example.edinburghtourapp;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class TourLocation implements Serializable {
    // Instance variables
    private int id;
    private String name;
    private String description;
    private LatLng latLng;

    // Constructors
    public TourLocation() {
        // Set the default location to sydney in australia, for no real reason other than it's obivously incorrect; and easier to notice
        String defaultName = "Sydney";
        LatLng defaultCoords = new LatLng(-34, 151);

        new TourLocation(0, defaultName, defaultCoords);
    }
    public TourLocation(int id, String name, LatLng latLng) {
        setId(id);
        setName(name);
        setLatLng(latLng);
    }

    // toString
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

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    // end of getters and setters
} // end of class
