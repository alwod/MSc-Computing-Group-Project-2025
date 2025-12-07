package com.example.edinburghtourapp;

import java.io.Serializable;
import java.util.LinkedList;

public class Tour implements Serializable {

    private String id;                       // e.g. "bites_pints"
    private String name;                    // e.g. "Bites and Pints Tour"
    private String category;                 // e.g. "Food", "History"
    private LinkedList<TourLocation> stops;

    public Tour(String id, String name, String category, LinkedList<TourLocation> stops) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.stops = stops;
    }

    // Add a stop to the tour FIFO order
    //public void addLocation(TourLocation location) {
    //    locations.add(location);
    //}

    // Getters
    public String getId(){
        return id; }
    public String getName(){
        return name; }
    public String getCategory(){
        return category; }
    public LinkedList<TourLocation> getStops() {
        return stops;
    }
}
