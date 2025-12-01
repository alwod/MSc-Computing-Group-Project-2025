package com.example.edinburghtourapp;

import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

import org.parceler.Parcel;

import java.io.Serializable;

@Parcel
public class TourLocation {
    // Instance variables
    int id;
    String name;
    String description;
    LatLng latLng;

    // Constructors
    public TourLocation() {
        // Set the default location to sydney in australia, for no real reason other than it's obivously incorrect; and easier to notice
        String defaultName = "Sydney";
        String defaultDescription = "Default location created";
        LatLng defaultCoords = new LatLng(-34, 151);

        new TourLocation(0, defaultName, defaultDescription, defaultCoords);
    }
    public TourLocation(int id, String name, String description, LatLng latLng) {
        setId(id);
        setName(name);
        setDescription(description);
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
