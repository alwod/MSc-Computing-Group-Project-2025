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
        locations.add(location);
    }

    // Getters
    public String getId()               { return id; }
    public String getTitle()            { return title; }
    public String getCategory()         { return category; }
    public List<TourLocation> getLocations() {
        return locations;
    }
}
