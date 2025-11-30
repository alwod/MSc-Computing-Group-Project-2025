package com.example.edinburghtourapp;

import java.io.Serializable;
import java.util.LinkedList;

public class Tour implements Serializable {
    // Instance variables
    private String tourName;
    private LinkedList<TourLocation> tourLocations = new LinkedList<TourLocation>();
    Boolean isAccessible = false;

    // Constructors
    public Tour(String tourName, Boolean isAccessible) {
        setTourName(tourName);
        setAccessible(isAccessible);
    }

    public void addLocation(TourLocation location) {
        getTourLocations().addFirst(location);
    }

    public void removeLocation() {
        getTourLocations().removeFirst();
    }

    public String toString() {
        String output = "";

        output += "Name of tour: " + getTourName() + "\n";

        if(getAccessible()) {
            output += "The tour is considered accessible\n";
        }
        else {
            output += "The tour is not considered accessible\n";
        }

        for (TourLocation tempLocation : getTourLocations()) {
            output += tempLocation.toString();
        }

        return output;
    }

    // Getters and setters
    public void setAccessible(Boolean accesable) {
        this.isAccessible = accesable;
    }
    public Boolean getAccessible() {
        return this.isAccessible;
    }

    public LinkedList<TourLocation> getTourLocations() {
        return this.tourLocations;
    }

    public void setTourLocations(LinkedList<TourLocation> tourLocations) {
        this.tourLocations = tourLocations;
    }

    public String getTourName() {
        return this.tourName;
    }
    public void setTourName(String tourName) {
        this.tourName = tourName;
    }
    // end of getters and setters
} // end of class
