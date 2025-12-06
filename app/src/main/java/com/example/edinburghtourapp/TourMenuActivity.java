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

        TourLocation l1 = new TourLocation("Civerino's Stockbridge",
                "Any good food and drink tour should start with a slice of pizza the size of your head, so you’re in luck! To start the tour, make your way down to Civerino’s in Stockbridge for a mean slice or one of their great starters.",
                55.95928325456785, -3.2117698339064393);
        locations.add(l1);

        TourLocation l2 = new TourLocation("St. Vincent's",
                "Next, it’s time for our first pub. Wander up through the beautiful high street of Stockbridge, and down the famous Circus Lane to The Vinnie. This cosy pub serves a wide range of drinks in a historic venue.",
                55.95817080496498, -3.2028219860295546);
        locations.add(l2);

        TourLocation l3 = new TourLocation("Good Brothers Wine Cellar",
                "Keep going up the hill to our next stop: the trendy Good Brothers Wine Cellar. With small bites to accompany their unique wine selection, this beautiful bar is an Edinburgh must-try.",
                55.95690082261341, -3.1980595455502834);
        locations.add(l3);

        TourLocation l4 = new TourLocation("Bramble",
                "While it may be difficult to find, the next stop is worth the search. Located down a surreptitious staircase, Bramble is a fun, dimly-lit cocktail bar. We highly recommend their namesake cocktail, the bramble!",
                55.95499949631317, -3.1970993897286006);
        locations.add(l4);

        TourLocation l5 = new TourLocation("Spry Wines",
                "Then, head back up to the surface-world and make your way down Leith Walk. Spry is another great wine bar with unique pours and light bites. You can also visit their sister cafe downstairs, Ante, for brunch and pastries during the day.",
                55.95936658344025, -3.18327555904325);
        locations.add(l5);

        TourLocation l6 = new TourLocation("MIRIN",
                "Now it’s time to focus on the food again. MIRIN serves Asian-fusion small plates that are perfect for sharing with your tour partner, or for keeping all to yourself! Since the restaurant is so small, you may have to share your table, so come ready to make friends.",
                55.96229786408493, -3.1787812050706132);
        locations.add(l6);

        TourLocation l7 = new TourLocation("Victoria Bar",
                "Then, it’s back to the pub. This friendly neighbourhood pub pours great pints in a great atmosphere. Be sure to say hello to the crocodile on the back wall!",
                55.96586463547331, -3.1751162185634927);
        locations.add(l7);

        TourLocation l8 = new TourLocation("The Mother Superior",
                "As you continue down Leith Walk, let’s pop into another great, neighbourhood pub. The Mother Superior has a wide range of whiskies and a great food menu. We recommend stopping in on a Friday to see the electric harpist perform.",
                55.96912902888916, -3.172360907466771);
        locations.add(l8);

        TourLocation l9 = new TourLocation("The Lioness of Leith",
                "Finally, you end your tour in Leith proper. From cocktails to pints and burgers, there is something for everyone at the Lioness. You can even unwind after your tour with some classic arcade games.",
                55.97037506330608, -3.1692123344529293);
        locations.add(l9);

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

        TourLocation l1 = new TourLocation("Room and Rumors",
                "Located just steps away from Waverley train station in the Arches, this cute cafe has great drinks and snacks in a trendy setting. Their specialty is donuts, so donut miss out!",
                55.95152137845162, -3.1843510418518255);
        locations.add(l1);

        TourLocation l2 = new TourLocation("Black Medicine Coffee",
                "An Edinburgh institution, Black Medicine Coffee Co is a popular spot with uni students for a reason. Find a seat upstairs to watch the world go by on the busy South Bridge, or head to the basement for a cosier coffee.",
                55.94726358835867, -3.1854526204141154);
        locations.add(l2);

        TourLocation l3 = new TourLocation("Lady and the Bear",
                "Next, start strolling towards the Meadows and stop in to Lady and the Bear to try their award-winning Mediterranean food, as well as coffee from their own micro-roastery. You can also come back in the evening for theatre and music performances.",
                55.940799855553955, -3.1818270302088427);
        locations.add(l3);

        TourLocation l4 = new TourLocation("Uplands Roast",
                "Then, head along Meadows Walk to Uplands Roast. While this coffee cart has many excellent drinks, their hot chocolates are the talk of the town. Grab one to sip on as you walk through the Meadows.",
                55.94264372432357, -3.18962217808571);
        locations.add(l4);

        TourLocation l5 = new TourLocation("C O M B",
                "After your stroll, you may want a bigger bite to eat! So head to Comb for good food and good coffee in an eclectic, artsy atmosphere.",
                55.941800169378, -3.202807291578874);
        locations.add(l5);

        TourLocation l6 = new TourLocation("Lovecrumbs",
                "As your penultimate stop, head to Lovecrumbs and see if you can snag a coveted window seat, where you can curl up and watch the world go by. Laptop-free on the weekends, this cafe is all about connection and conversation.",
                55.946157138102585, -3.200719378632662);
        locations.add(l6);

        TourLocation l7 = new TourLocation("The Milkman",
                "Finally, a city-center cafe tour isn’t complete without a visit to the Milkman. With two cafes called the Milkman, one at the top and one at the bottom of Cockburn Street, we suggest the one with the shortest queue. They’re both popular, and both excellent, so you should be well taken care of on your way back to the train station.",
                55.950807815809775, -3.1906311241127265);
        locations.add(l7);

        t = new Tour("cafe", "City-Centre Cafe Loop", "Food", locations);

        return t;
    }

    private Tour createHistoricalTour() {
        Tour t;
        LinkedList<TourLocation> locations = new LinkedList<TourLocation>();

        TourLocation l1 = new TourLocation("",
                "",
                55.0, -3.0);
        locations.add(l1);

        TourLocation l2 = new TourLocation("",
                "",
                55.0, -3.0);
        locations.add(l2);

        TourLocation l3 = new TourLocation("",
                "",
                55.0, -3.0);
        locations.add(l3);

        TourLocation l4 = new TourLocation("",
                "",
                55.0, -3.0);
        locations.add(l4);

        TourLocation l5 = new TourLocation("",
                "",
                55.0, -3.0);
        locations.add(l5);

        TourLocation l6 = new TourLocation("",
                "",
                55.0, -3.0);
        locations.add(l6);

        TourLocation l7 = new TourLocation("",
                "",
                55.0, -3.0);
        locations.add(l7);

        TourLocation l8 = new TourLocation("",
                "",
                55.0, -3.0);
        locations.add(l8);

        TourLocation l9 = new TourLocation("",
                "",
                55.0, -3.0);
        locations.add(l9);

        t = new Tour("historical", "Edinburgh's Hidden History", "History", locations);

        return t;
    }

    private Tour createLocalBusinessTour() {
        Tour t;
        LinkedList<TourLocation> locations = new LinkedList<TourLocation>();
        TourLocation l1 = new TourLocation("",
                "",
                55.0, -3.0);
        locations.add(l1);

        TourLocation l2 = new TourLocation("",
                "",
                55.0, -3.0);
        locations.add(l2);

        TourLocation l3 = new TourLocation("",
                "",
                55.0, -3.0);
        locations.add(l3);

        TourLocation l4 = new TourLocation("",
                "",
                55.0, -3.0);
        locations.add(l4);

        TourLocation l5 = new TourLocation("",
                "",
                55.0, -3.0);
        locations.add(l5);

        TourLocation l6 = new TourLocation("",
                "",
                55.0, -3.0);
        locations.add(l6);

        TourLocation l7 = new TourLocation("",
                "",
                55.0, -3.0);
        locations.add(l7);

        t = new Tour("nature", "Nature in the City", "Culture", locations);

        return t;
    }
}