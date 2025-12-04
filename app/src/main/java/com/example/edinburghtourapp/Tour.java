package com.example.edinburghtourapp;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Tour implements Serializable {

    private String id;                       // e.g. "bites_pints"
    private String title;                    // e.g. "Bites and Pints Tour"
    private String category;                 // e.g. "Food", "History"
    private LinkedList<TourLocation> locations;

    public Tour(String id, String title, String category) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.locations = new LinkedList<>();
    }

    // Add a stop to the tour FIFO order
    public void addLocation(TourLocation location) {
        this.locations.add(location);
    }

    // Remove the first-most stop from the tour
    public void removeFirstLocation() {
        this.locations.removeFirst();
    }

    public String toString() {
        String output = "";

        output += "Tour ID: " + getId() + "\n";
        output += "Tour title: " + getTitle() + "\n";
        output += "Tour category: " + getCategory() + "\n";
        output += "Locations: \n";
        for (TourLocation tempLocation : getLocations()) {
            output += tempLocation.toString();
        }
        return output;
    }

    // Getters
    public String getId()               { return this.id; }
    public String getTitle()            { return this.title; }
    public String getCategory()         { return this.category; }
    public List<TourLocation> getLocations() {
        return this.locations;
    }
}