package com.example.edinburghtourapp;

import androidx.activity.ComponentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.LinkedList;

public class TourMenuActivity extends ComponentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_menu);

        // ---profile icon in Navigation bar  ---
        ImageButton btnProfile = findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(v -> {
            Intent profileIntent = new Intent(this, ProfileActivity.class);
            startActivity(profileIntent);
        });

        // --- Tour buttons ---
        Button btnBitesPints    = findViewById(R.id.btnBitesPints);
        Button btnBookshop      = findViewById(R.id.btnBookshop);
        Button btnCafe          = findViewById(R.id.btnCafe);
        Button btnHistorical    = findViewById(R.id.btnHistorical);
        Button btnLocalBusiness = findViewById(R.id.btnLocalBusiness);

        // When a button is clicked, build the Tour and send to ShowLocationInfoActivity
        btnBitesPints.setOnClickListener(v -> openTour(createBitesPintsTour()));
        btnBookshop.setOnClickListener(v -> openTour(createBookshopTour()));
        btnCafe.setOnClickListener(v -> openTour(createCafeTour()));
        btnHistorical.setOnClickListener(v -> openTour(createHistoricalTour()));
        btnLocalBusiness.setOnClickListener(v -> openTour(createLocalBusinessTour()));
    }

    // Opens the next screen with the selected Tour
    private void openTour(Tour tour) {
        Intent i = new Intent(this, ShowLocationInfoActivity.class);
        i.putExtra("tour", tour);   // Tour is Serializable
        startActivity(i);
    }

    // ===== Hard-coded tours with coords, have to replace with actual coords =====

    private Tour createBitesPintsTour() {
        Tour t;
        LinkedList<TourLocation> locations = new LinkedList<TourLocation>();

        t = new Tour("bites_pints", "Bites and Pints Tour", "Food", locations);

        return t;
    }

    private Tour createBookshopTour() {
        Tour t;
        LinkedList<TourLocation> locations = new LinkedList<TourLocation>();

        //The Edinburgh Bookshop
        TourLocation l1 = new TourLocation("The Edinburgh Bookshop",
                "Established in 2008, this bookshop is in the heart of Bruntsfield. It hosts regular events, from children's story hours to book launches and author talks.",
                55.9376190063053, -3.205557581943443);
        locations.add(l1);

        TourLocation l2 = new TourLocation("Armchair Books",
                "Underneath Edinburgh Castle, Armchair is a second-hand bookshop with a maze of shelves to get lost in. It has a wide range of genres and titles, including unique, antique gems.",
                55.94642009813978, -3.199473103222256);
        locations.add(l2);

        TourLocation l3 = new TourLocation("Tills Bookshop",
                "As one of Edinburgh’s oldest second-hand bookshops, Tills packs tons of books into a small, cozy space!",
                55.94126595923714, -3.1823708762363783);
        locations.add(l3);

        TourLocation l4 = new TourLocation("Lighthouse Books",
                "Edinburgh’s radical bookshop” is a queer-owned and woman led bookshop with a focus on intersectional books and zines.",
                55.944807307119895, -3.1853384566476066);
        locations.add(l4);

        TourLocation l5 = new TourLocation("Blackwells",
                "Originally from Oxford, Blackwells is a UK institution, with their Edinburgh shop having a large, well-rounded collection worth browsing.",
                55.94790015653161, -3.1852847804819744);
        locations.add(l5);

        TourLocation l6 = new TourLocation("Topping and Co",
                "A reader could spend hours exploring the different rooms in the beautiful Topping and Co. Ask the friendly book sellers for a free cup of tea, and you might not want to finish the tour!",
                55.95776252759628, -3.1837582473996657);
        locations.add(l6);

        TourLocation l7 = new TourLocation("Typewronger",
                "Hidden in a basement shop on Leith Walk, Typewronger is a nonprofit organization selling books and zines, as well as running a print shop and supporting a Writers in Residence program.",
                55.959444889010726, -3.1829678553444354);
        locations.add(l7);

        TourLocation l8 = new TourLocation("Argonaut Books",
                "At the bottom of Leith Walk, Argonaut books is a community bookstore with a cafe and a great selection of reads old and new.",
                55.970243182185605, -3.171482764169678);
        locations.add(l8);

        TourLocation l9 = new TourLocation("The Portobello Bookshop",
                "A little off the beaten path in Portobello, this bookshop is the perfect place to finish the tour. Grab a book from the beautiful shop and head to the seaside to relax after all that walking and book shopping!",
                55.95517710225312, -3.117111847399814);
        locations.add(l9);

        t = new Tour("bookshop", "Bookshop Tour", "Culture", locations);

        return t;
    }

    private Tour createCafeTour() {
        Tour t;
        LinkedList<TourLocation> locations = new LinkedList<TourLocation>();

        t = new Tour("cafe", "City-Centre Cafe Loop", "Food", locations);

        return t;
    }

    private Tour createHistoricalTour() {
        Tour t;
        LinkedList<TourLocation> locations = new LinkedList<TourLocation>();

        t = new Tour("historical", "Edinburgh's Hidden History", "History", locations);

        return t;
    }

    private Tour createLocalBusinessTour() {
        Tour t;
        LinkedList<TourLocation> locations = new LinkedList<TourLocation>();

        t = new Tour("nature", "Nature in the City", "Culture", locations);

        return t;
    }
}