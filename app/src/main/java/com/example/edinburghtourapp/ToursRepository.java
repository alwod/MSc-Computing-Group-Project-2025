package com.example.edinburghtourapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ToursRepository {

    public static final String TOUR_BOOKSHOP    = "bookshop";
    public static final String TOUR_CAFE_LOOP   = "cafe_loop";
    public static final String TOUR_BITES_PINTS = "bites_pints";
    public static final String TOUR_HISTORY     = "hidden_history";
    public static final String TOUR_NATURE      = "nature_city";

    private static final Map<String, Tour> TOUR_MAP = new HashMap<>();

    static {
        buildBookshopTour();
        buildCafeLoopTour();
        buildBitesAndPintsTour();
        buildHistoryTour();
        buildNatureTour();
    }

    public static Tour getTour(String id) {
        return TOUR_MAP.get(id);
    }

    public static List<Tour> getAllTours() {
        return new ArrayList<>(TOUR_MAP.values());
    }

    //Bookshop Tour

    private static void buildBookshopTour() {
        LinkedList<TourLocation> stops = new LinkedList<>();

        stops.add(new TourLocation(
                "The Edinburgh Bookshop",
                "Established in 2008, this bookshop is in the heart of Bruntsfield. It hosts regular events, from children’s story hours to book launches and author talks.",
                55.93767190063053, -3.205557581943443));

        stops.add(new TourLocation(
                "Armchair Books",
                "Underneath Edinburgh Castle, Armchair is a second-hand bookshop with a maze of shelves to get lost in. It has a wide range of genres and titles, including unique, antique gems.",
                55.94642009813978, -3.199473103222256));

        stops.add(new TourLocation(
                "Tills Bookshop",
                "As one of Edinburgh’s oldest second-hand bookshops, Tills packs tons of books into a small, cozy space!",
                55.94126595923714, -3.1823708762363783));

        stops.add(new TourLocation(
                "Lighthouse Bookshop",
                "“Edinburgh’s radical bookshop” is a queer-owned and woman led bookshop with a focus on intersectional books and zines.",
                55.944807307119895, -3.1853384566476066));

        stops.add(new TourLocation(
                "Blackwell’s",
                "Originally from Oxford, Blackwells is a UK institution, with their Edinburgh shop having a large, well-rounded collection worth browsing.",
                55.94790015653161, -3.1852847804819744));

        stops.add(new TourLocation(
                "Topping & Company",
                "A reader could spend hours exploring the different rooms in the beautiful Topping and Co. Ask the friendly book sellers for a free cup of tea, and you might not want to finish the tour!",
                55.95776252759628, -3.1837582473996657));

        stops.add(new TourLocation(
                "Typewronger Books",
                "Hidden in a basement shop on Leith Walk, Typewronger is a nonprofit organization selling books and zines, as well as running a print shop and supporting a Writers in Residence program.",
                55.959444889010726, -3.1829678553444354));

        stops.add(new TourLocation(
                "Argonaut Books",
                "At the bottom of Leith Walk, Argonaut books is a community bookstore with a cafe and a great selection of reads old and new.",
                55.970243182185605, -3.171482764169678));

        stops.add(new TourLocation(
                "The Portobello Bookshop",
                "A little off the beaten path in Portobello, this bookshop is the perfect place to finish the tour. Grab a book from the beautiful shop and head to the seaside to relax after all that walking and book shopping!",
                55.95517710225312, -3.117111847399814));

        Tour t = new Tour(
                TOUR_BOOKSHOP,
                "Bookshop Tour",
                "Bookshop",
                stops
        );
        TOUR_MAP.put(t.getId(), t);
    }

    //City-Centre Cafe Loop

    private static void buildCafeLoopTour() {
        LinkedList<TourLocation> stops = new LinkedList<>();

        stops.add(new TourLocation(
                "Room & Rumours",
                "Located just steps away from Waverley train station in the Arches, this cute cafe has great drinks and snacks in a trendy setting. Their specialty is donuts, so donut miss out!",
                55.95152137845162, -3.1843510418518255));

        stops.add(new TourLocation(
                "Black Medicine Coffee Co.",
                "An Edinburgh institution, Black Medicine Coffee Co is a popular spot with uni students for a reason. Find a seat upstairs to watch the world go by on the busy South Bridge, or head to the basement for a cozier coffee",
                55.94726358835867, -3.1854526204141154));

        stops.add(new TourLocation(
                "Lady and the Bear",
                "Next, start strolling towards the Meadows and stop in to Lady and the Bear to try their award-winning Mediterranean food, as well as coffee from their own micro-roastery. You can also come back in the evening for theatre and music performances.",
                55.940799855553955, -3.1818270302088427));

        stops.add(new TourLocation(
                "Uplands Roast (The Meadows)",
                "Then, head along Meadows Walk to Uplands Roast. While this coffee cart has many excellent drinks, their hot chocolates are the talk of the town. Grab one to sip on as you walk through the Meadows.",
                55.94264372432357, -3.18962217808571));

        stops.add(new TourLocation(
                "C O M B",
                "After your stroll, you may want a bigger bite to eat! So head to Comb for good food and good coffee in an eclectic, artsy atmosphere.",
                55.941800169378, -3.202807291578874));

        stops.add(new TourLocation(
                "Lovecrumbs",
                "As your penultimate stop, head to Lovecrumbs and see if you can snag a coveted window seat, where you can curl up and watch the world go by. Laptop-free on the weekends, this cafe is all about connection and conversation.",
                55.946157138102585, -3.200719378632662));

        stops.add(new TourLocation(
                "The Milkman",
                "Finally, a city-center cafe tour isn’t complete without a visit to the Milkman. With two cafes called the Milkman, one at the top and one at the bottom of Cockburn Street, we suggest the one with the shortest queue. They’re both popular, and both excellent, so you should be well taken care of on your way back to the train station.",
                55.950807815809775, -3.1906311241127265));

        Tour t = new Tour(
                TOUR_CAFE_LOOP,
                "City-Centre Cafe Loop",
                "Cafe",
                stops
        );
        TOUR_MAP.put(t.getId(), t);
    }

    //Bites and Pints Tour

    private static void buildBitesAndPintsTour() {
        LinkedList<TourLocation> stops = new LinkedList<>();

        stops.add(new TourLocation(
                "Civerino’s Stockbridge",
                "Any good food and drink tour should start with a slice of pizza the size of your head, so you’re in luck! To start the tour, make your way down to Civerino’s in Stockbridge for a mean slice or one of their great starters.",
                55.95928325456785, -3.2117698339064393));

        stops.add(new TourLocation(
                "St. Vincent’s",
                "Next, it’s time for our first pub. Wander up through the beautiful high street of Stockbridge, and down the famous Circus Lane to The Vinnie. This cosy pub serves a wide range of drinks in a historic venue.",
                55.95817080496498, -3.2028219860295546));

        stops.add(new TourLocation(
                "Good Brothers Wine Cellar",
                "Keep going up the hill to our next stop: the trendy Good Brothers Wine Cellar. With small bites to accompany their unique wine selection, this beautiful bar is an Edinburgh must-try.",
                55.95690082261341, -3.1980595455502834));

        stops.add(new TourLocation(
                "Bramble",
                "While it may be difficult to find, the next stop is worth the search. Located down a surreptitious staircase, Bramble is a fun, dimly-lit cocktail bar. We highly recommend their namesake cocktail, the bramble!",
                55.95499949631317, -3.1970993897286006));

        stops.add(new TourLocation(
                "Spry Wines",
                "Then, head back up to the surface-world and make your way down Leith Walk. Spry is another great wine bar with unique pours and light bites. You can also visit their sister cafe downstairs, Ante, for brunch and pastries during the day.  ",
                55.95936658344025, -3.18327555904325));

        stops.add(new TourLocation(
                "MIRIN",
                "Now it’s time to focus on the food again. MIRIN serves Asian-fusion small plates that are perfect for sharing with your tour partner, or for keeping all to yourself! Since the restaurant is so small, you may have to share your table, so come ready to make friends.",
                55.96229786408493, -3.1787812050706132));

        stops.add(new TourLocation(
                "Victoria Bar",
                "Then, it’s back to the pub. This friendly neighbourhood pub pours great pints in a great atmosphere. Be sure to say hello to the crocodile on the back wall!",
                55.96586463547331, -3.1751162185634927));

        stops.add(new TourLocation(
                "The Mother Superior",
                "As you continue down Leith Walk, let’s pop into another great, neighbourhood pub. The Mother Superior has a wide range of whiskies and a great food menu. We recommend stopping in on a Friday to see the electric harpist perform.",
                55.96912902888916, -3.172360907466771));

        stops.add(new TourLocation(
                "The Lioness of Leith",
                "Finally, you end your tour in Leith proper. From cocktails to pints and burgers, there is something for everyone at the Lioness. You can even unwind after your tour with some classic arcade games.",
                55.97037506330608, -3.1692123344529293));

        Tour t = new Tour(
                TOUR_BITES_PINTS,
                "Bites and Pints Tour",
                "Food & Drink",
                stops
        );
        TOUR_MAP.put(t.getId(), t);
    }

    //Edinburgh’s Hidden History

    private static void buildHistoryTour() {
        LinkedList<TourLocation> stops = new LinkedList<>();

        stops.add(new TourLocation(
                "Mary, Queen of Scots’ Bath House",
                "Outside the gates of Holyrood Palace, you’ll find a stone building with a plaque that says the building shares an association with Mary, Queen of Scots (1542 –1587). While little is known about the building, Queen Mary did reside in Holyrood Palace, and the building was once part of the original boundary wall of the palace.",
                55.953885372654064, -3.1742928984891217));

        stops.add(new TourLocation(
                "5 Bakehouse Close",
                "From Holyrood Palace, head a short distance up the Royal Mile and turn down Bakehouse Close. On the left, you’ll find the courtyard of the former Cock and Trumpet Pub, where men could find female companionship. Fans of the show Outlander will recognize the Close as the location of Jamie’s printshop.",
                55.951133969932144, -3.1792099783952237));

        stops.add(new TourLocation(
                "Museum of Edinburgh",
                "When you leave Bakehouse Close and turn left back onto the Royal Mile, take a few short steps to the entrance of the Museum of Edinburgh. This free museum takes visitors through its unique permanent collection and 16th century building. Be sure to check out their rotating special exhibitions as well!",
                55.951443989557774, -3.1793484053425383));

        stops.add(new TourLocation(
                "John Knox House",
                "Head further up the street and enter the John Knox House, the oldest, original surviving medieval building on the Mile. You can tour the house, as well as visit the Scottish Storytelling Centre, a storytelling venue with programming year-round.",
                55.95063474192641, -3.1850442673285513));

        stops.add(new TourLocation(
                "Heart of Midlothian",
                "As you continue up the Royal Mile, pause in the courtyard of St. Giles Cathedral to admire the Heart of Midlothian. The mosaic marks the former entrance to Edinburgh’s Old Tollbooth, a municipal building since the 14th century. While people today spit on the heart for good luck in love, the tradition was most likely started by people spitting on the location out of disdain for the public executions that used to take place in the tollbooth.",
                55.94963398695388, -3.191448549563111));

        stops.add(new TourLocation(
                "Thistle Chapel, St Giles’ Cathedral",
                "Then, head into St. Giles Cathedral and go to the back right of the building. Behind the gates is Thistle Chapel. This gorgeously carved room is home to Scotland chivalric Order of the Thistle. The chapel is only open when a staff member is available to supervise, and they’ll be happy to answer your questions about the coat of arms that line the walls. Try to spot the three angels with bagpipes hidden in the carvings!",
                55.94937410558156, -3.190377776411825));

        stops.add(new TourLocation(
                "The Witches’ Well",
                "To find this memorial for those burned at the stake in Scotland between 1472 and 1722, walk all the way up the Royal Mile, almost to the entrance to the castle. When you reach the Tartan Weaving Mill and Experience, look down the alley to your right and you will see the plaque on the side of the building. Hundreds of people falsely accused of witchcraft perished outside the castle gates during a dark chapter of the city’s history.",
                55.94896816335771, -3.1961784304951153));

        stops.add(new TourLocation(
                "Surgeons’ Hall Museums",
                "If you have a sensitive stomach or a delicate constitution, you may want to skip this stop. Owned by The Royal College of Surgeons of Edinburgh, the collection takes visitors through the evolution of surgery and its techniques throughout history. From human remains to surgical instruments, the museum is a deep dive into the history of surgery.",
                55.94656812917011, -3.1851223649546063));

        stops.add(new TourLocation(
                "Dovecot Studios",
                "Located in a Victorian bath house, this studio aims to maintain heritage looming and weaving techniques. Visitors can view the artists at work from the viewing balcony, then head down to the gift shop and cafe afterwards for a well-deserved treat!",
                55.94832536526945, -3.184779042202845));

        Tour t = new Tour(
                TOUR_HISTORY,
                "Edinburgh’s Hidden History",
                "History",
                stops
        );
        TOUR_MAP.put(t.getId(), t);
    }

    //Nature in the City

    private static void buildNatureTour() {
        LinkedList<TourLocation> stops = new LinkedList<>();

        stops.add(new TourLocation(
                "National Gallery Modern 1",
                "Start this tour outside the National Galleries Modern 1 campus to see the land feature by landscape architect Charles Jencks. After you explore the museum, head out the back garden and start your walk along the Water of Leith.",
                55.950894958542236, -3.226992372536922));

        stops.add(new TourLocation(
                "Water of Leith Walkway",
                "A 13-mile walk from beginning to end, the Walkway along the Water of Leith takes you away from the hustle and bustle of the city. By starting from Modern One and walking to the Royal Botanics, you’ll pass through Dean Village and Stockbridge. Keep an eye out for local art installations, as well as the numerous animals that call the Water of Leith home!",
                55.95998611111111, -3.2283194444444447));

        stops.add(new TourLocation(
                "Royal Botanic Garden",
                "While you can walk the Water of Leith until the end, we recommend leaving the path at the Royal Botanic Garden. With over 70 acres to explore, you can find plants from all over the world throughout the grounds. The garden is free to visit, but any donations you make go to supporting and preserving biodiversity and horticultural research.",
                55.96420073138137, -3.2122470604866096));

        stops.add(new TourLocation(
                "Princes St Gardens",
                "Head up from the Royal Botanic Garden into New Town to explore Princes St Gardens. These gardens, both East and West, have a malodorous history. They used to be Edinburgh’s Nor Loch, which acted as sewage and drainage ponds for waste from the Old Town. This noxious lake was one of the reasons Edinburgh was known as “Auld Reekie” in the Middle Ages. Now, though, the gardens are a beautiful, beloved part of the city!",
                55.9510639373663, -3.1988460630623776));

        stops.add(new TourLocation(
                "Calton Hill",
                "If you walk east down Prince St, you’ll come to the steps taking you up Calton Hill. One of the 7 hills of Edinburgh, Calton Hill was modeled after an Athenian Acropolis, including an unfinished version of the Parthenon. The hill also features the National Monument, the City Observatory , the Dugald Stewart Monument, and the Nelson Monument. Head up at sunset for stunning views of the city lighting up.",
                55.955255308970784, -3.181870243748458));

        stops.add(new TourLocation(
                "Holyrood Park & Arthur’s Seat",
                "If you’re up for a proper hike, make your way to Holyrood Park and follow signs to the summit of Arthur’s Seat. Another of Edinburgh’s 7 hills, this extinct volcano offers a moderate hike, with a bit of a scramble at the very top. The views of Edinburgh and the surrounding area are more than worth the effort! ",
                55.94419802081812, -3.162829192557789));

        stops.add(new TourLocation(
                "Dr. Neil’s Garden",
                "On the other side of Arthur’s Seat in Duddingston, you can relax after your hike in a secret garden. Dr. Neil’s looks over Duddingston Loch and is the perfect place for calm reflection or meditation. When you’re done reflecting, we recommend heading next door to the Sheep Heid’s Inn, which claims to be the oldest surviving pub in Scotland.",
                55.94081786101165, -3.1492070647972237));




        Tour t = new Tour(
                TOUR_NATURE,
                "Nature in the City",
                "Nature",
                stops
        );
        TOUR_MAP.put(t.getId(), t);
    }
}
