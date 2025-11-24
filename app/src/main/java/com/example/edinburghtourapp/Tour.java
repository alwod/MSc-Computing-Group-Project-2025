package com.example.edinburghtourapp;

public class Tour {
    // Instance variables
    private String tourName;
    final static int ARRAY_SIZE = 3;
    private TourLocation [] locations = new TourLocation[ARRAY_SIZE];

    Boolean isAccessible = false;

    // Constructors
    public Tour(String tourName, Boolean isAccessible, TourLocation [] locations) {
        setTourName(tourName);
        setAccessible(isAccessible);
        setLocations(locations);
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

        for(int index = 0; index < getLocations().length; index++) {
            output += getLocations()[index].toString();
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

    public void setLocations(TourLocation[] locations) {
        this.locations = locations;
    }
    public TourLocation[] getLocations() {
        return this.locations;
    }

    public String getTourName() {
        return this.tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }
    // end of getters and setters
} // end of class
