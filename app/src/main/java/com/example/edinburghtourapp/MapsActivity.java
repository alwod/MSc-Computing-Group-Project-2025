package com.example.edinburghtourapp;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.edinburghtourapp.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
* Thanks to these sources for making this work:
* Google Maps:
* https://www.specbee.com/blogs/android-tutorials-google-map-drawing-routes-between-two-points
* https://stackoverflow.com/questions/51682775/how-to-draw-route-more-than-two-points-on-google-map-in-android
* https://www.digitalocean.com/community/tutorials/android-google-map-drawing-route-two-points
* https://stackoverflow.com/questions/54783076/you-must-use-an-api-key-to-authenticate-each-request-to-google-maps-platform-api
*
* Getting user location:
* https://github.com/Pritish-git/get-Current-Location/blob/main/MainActivity.java
*
* Code running every x seconds:
* https://www.tutorialspoint.com/how-to-run-a-method-every-10-seconds-in-android
* */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    // Variables for Google Maps
    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    // Variables for getting user location
    private LocationRequest locationRequest;
    // User location default is at Napier Merchiston campus
    private LatLng userLocation = new LatLng(55.933144, -3.212863);
    private LatLng destinationLocation;

    // Variables for tracking the tour
    Tour tour;
    int tourIndex;
    boolean hasStartedTour = false;

    // Variables for the timer
    Handler handler = new Handler();
    Runnable runnable;
    // Delay is in milliseconds; 10000 is 10 seconds
    int delay = 10000;

    // First method to run.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get LatLng and tour from ShowLocationInfoActivity
        tour = (Tour) getIntent().getSerializableExtra("tour");
        tourIndex = (int) getIntent().getIntExtra("index", 0);

        double destLat = tour.getStops().get(tourIndex).getLatitude();
        double destLng = tour.getStops().get(tourIndex).getLongitude();

        destinationLocation = new LatLng(destLat, destLng);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Set up location request stuff
        locationRequest = com.google.android.gms.location.LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);

        // Set up buttons, show route button is done later in onMapReady
        // If the info button is pressed, go back to ShowLocationInfo without removing the first location in the queue
        Button infoButton = (Button) findViewById(R.id.infoButton);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToInfoScreen();
            }
        });
    } // End of onCreate method

    @Override
    protected void onResume() {
        super.onResume();
        // This code runs every (delay) milliseconds
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(runnable, delay);
                // Only do this if the tour has actually started
                if (hasStartedTour) {
                    getCurrentLocation();
                    loopTour();
                }
            }
        }, delay);
    } // End of onResume method

    @Override
    protected void onPause() {
        super.onPause();
        // Stop the handler when activity isnt visible
        handler.removeCallbacks(runnable);
    } // End of onPause method

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // This is the earliest this method can be called, wont work any earlier like on onCreate
        getCurrentLocation();

        // Initialise the map
        mMap = googleMap;

        // Create a marker for the destination, and move over there
        addMarker(destinationLocation, false);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(destinationLocation, 16));

        // Set up button for creating the route, thus starting the tour
        Button remakeRouteButton = (Button) findViewById(R.id.remakeRouteButton);
        remakeRouteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hasStartedTour = true;
                loopTour();
            }
        });
    } // End of onMapReady method

    // Loops through the tour
    public void loopTour() {
        // Clear the map, otherwise markers will start overlapping. Don't know how to delete specific markers yet
        mMap.clear();
        // Add markers on the map
        addMarker(userLocation, true);
        addMarker(destinationLocation, false);

        // Then create the route
        String url = getUrl(userLocation, destinationLocation);
        FetchUrl fetchUrl = new FetchUrl();
        fetchUrl.execute(url);

        // Zoom to user's location
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 16));
    } // End of loopTour method

    public void addMarker(LatLng point, boolean isUser) {
        // A marker option is basically a list of things a marker can have, such as position, icon, and colour
        MarkerOptions options = new MarkerOptions();

        // Set the marker position
        options.position(point);

        // Change the colour of the marker, depending on if the marker is for the user or not
        if (isUser) {
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        } else {
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        }

        mMap.addMarker(options);
    } // End of addMarker method

    // Handles sending the tour object to ShowLocationInfo
    public void backToInfoScreen() {
        Intent intent = new Intent(this, ShowLocationInfoActivity.class);

        intent.putExtra("tour", tour);
        intent.putExtra("index", tourIndex);

        startActivity(intent);
    } // End of backToInfoScreen method

    // Runs after permissions were requested. If permissions were granted, turn on gps if not already or get user location
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (isGPSEnabled()) {
                    getCurrentLocation();
                } else {
                    turnOnGPS();
                }
            }
        }
    }// End of onRequestPermissionsResult method

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                getCurrentLocation();
            }
        }
    } // End of onActivityResult method

    // If permissions allow, get the longitude and latitude of the user's location
    private void getCurrentLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if(isGPSEnabled()) {
                    LocationServices.getFusedLocationProviderClient(MapsActivity.this).requestLocationUpdates(locationRequest, new LocationCallback() {
                        @Override
                        public void onLocationResult(@NonNull LocationResult locationResult) {
                            super.onLocationResult(locationResult);

                            LocationServices.getFusedLocationProviderClient(MapsActivity.this).removeLocationUpdates(this);

                            if (locationResult != null && locationResult.getLocations().size() > 0) {
                                int index = locationResult.getLocations().size() - 1;
                                double latitude = locationResult.getLocations().get(index).getLatitude();
                                double longitude = locationResult.getLocations().get(index).getLongitude();
                                userLocation = new LatLng(latitude, longitude);
                                System.out.println("----------Got user location!");
                            }
                            else {
                                System.out.println("----------Didnt get user location");
                            }
                        }
                    }, Looper.getMainLooper());
                } else {
                    turnOnGPS();
                }
            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    } // End of getCurrentLocation method

    // Turns on GPS
    private void turnOnGPS() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);

        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext()).checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(MapsActivity.this, "GPS is already turned on", Toast.LENGTH_SHORT).show();
                } catch (ApiException e) {
                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                ResolvableApiException resolveApiException = (ResolvableApiException) e;
                                resolveApiException.startResolutionForResult(MapsActivity.this, 2);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            break;
                    } // End of switch
                } // End of outer catch
            }
        });
    }// End of turnOnGPS method

    // Check if gps is enabled
    private boolean isGPSEnabled() {
        LocationManager locationManager = null;
        boolean isEnabled = false;

        if (locationManager == null) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        return isEnabled;
    }// End of isGPSEnabled method

    // Generate a URL to get the patch between two points, origin and dest
    private String getUrl(LatLng origin, LatLng dest) {
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=true";
        String mode = "mode=walking";

        // Get the API key from local.properties
        String apiKey = BuildConfig.MAPS_API_KEY;

        String key = "key=" + apiKey;

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode + "&" + key;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    } // End of getUrl method

    /*
    The url generated is used to calculate a route between 2 points in google maps. The data
    generated from this is downloaded in downloadUrl, then in ParserTask is processed and converted
    into polyline data that can then be overlayed on our map. This class allows all of that to be
    done asynchronously in the background of other tasks.
     */
    private class FetchUrl extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... url) {
            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
                Log.d("Background Task data", data.toString());
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        } // End of doInBackground method

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        } // End of onPostExecute method
    } // End of FetchUrl class

    // This method downloads the data generated by the url.
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();
            Log.d("downloadUrl", data.toString());
            br.close();
        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    } // End of downloadUrl method

    // This class processes the data generated by the url, and uses it to create a route
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {
        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                Log.d("ParserTask",jsonData[0].toString());
                DataParser parser = new DataParser();
                Log.d("ParserTask", parser.toString());

                // Starts parsing data
                routes = parser.parse(jObject);
                Log.d("ParserTask","Executing routes");
                Log.d("ParserTask",routes.toString());

            } catch (Exception e) {
                Log.d("ParserTask",e.toString());
                e.printStackTrace();
            }
            return routes;
        } // End of doInBackground method

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points;
            PolylineOptions lineOptions = null;

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }
                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(10);
                lineOptions.color(Color.BLUE);

                Log.d("onPostExecute","onPostExecute lineoptions decoded");
            }

            // Drawing polyline in the Google Map for the i-th route
            if(lineOptions != null) {
                mMap.addPolyline(lineOptions);
            }
            else {
                Log.d("onPostExecute","without Polylines drawn");
            }
        } // End of onPostExecute method
    } // End of ParserTask class

    // Class for reading the data generated by the url
    class DataParser {
        List<List<HashMap<String,String>>> parse(JSONObject jObject) {
            List<List<HashMap<String, String>>> routes = new ArrayList<>();
            JSONArray jRoutes;
            JSONArray jLegs;
            JSONArray jSteps;
            try {
                jRoutes = jObject.getJSONArray("routes");

                /** Traversing all routes */
                for(int i=0;i<jRoutes.length();i++) {
                    jLegs = ( (JSONObject)jRoutes.get(i)).getJSONArray("legs");
                    List path = new ArrayList<>();

                    /** Traversing all legs */
                    for(int j=0;j<jLegs.length();j++) {
                        jSteps = ( (JSONObject)jLegs.get(j)).getJSONArray("steps");

                        /** Traversing all steps */
                        for(int k=0;k<jSteps.length();k++) {
                            String polyline = "";
                            polyline = (String)((JSONObject)((JSONObject)jSteps.get(k)).get("polyline")).get("points");
                            List<LatLng> list = decodePoly(polyline);

                            /** Traversing all points */
                            for(int l=0;l<list.size();l++) {
                                HashMap<String, String> hm = new HashMap<>();
                                hm.put("lat", Double.toString((list.get(l)).latitude) );
                                hm.put("lng", Double.toString((list.get(l)).longitude) );
                                path.add(hm);
                            }
                        }
                        routes.add(path);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }catch (Exception e) {
            }
            return routes;
        } // End of parse method

        /**
         * Method to decode polyline points
         * */
        private List<LatLng> decodePoly(String encoded) {
            List<LatLng> poly = new ArrayList<>();
            int index = 0, len = encoded.length();
            int lat = 0, lng = 0;

            while (index < len) {
                int b, shift = 0, result = 0;
                do {
                    b = encoded.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lat += dlat;

                shift = 0;
                result = 0;
                do {
                    b = encoded.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lng += dlng;

                LatLng p = new LatLng((((double) lat / 1E5)),
                        (((double) lng / 1E5)));
                poly.add(p);
            }
            return poly;
        } // End of decodePoly method
    } // End of DataParser class
} // End of MapsActivity