package com.example.edinburghtourapp;

public class Tour {
    // Instance variables
    final static int ARRAY_SIZE = 3;
    private TourLocation [] locations = new TourLocation[ARRAY_SIZE];

    Boolean isAccessible = false;





    public String toString() {
        String output = "";

        if(getAccessible()) {
            output += "The tour is considered accessible\n";
        }
        else {
            output += "The tour is not considered accessible";
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
    // end of getters and setters
} // end of class
