package cs2340.shelterbuzz.controllers;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import cs2340.shelterbuzz.R;
import cs2340.shelterbuzz.model.Shelter;
import cs2340.shelterbuzz.model.ShelterManager;

public class SearchMapActivity extends FragmentActivity implements OnMapReadyCallback {

	private List<Shelter> searchResults = new ArrayList<>();

    // Atlanta by default
    private final double lat = 33.7490;
    private final double lng = 84.3880;
    private final float defaultZoom = 12.0f;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // receive the intent passed from ShelterListActivity from the new map button
        // and store it to searchResults. The map button assumes searchResults list is initialized
        Intent i = getIntent();
        this.searchResults = (List<Shelter>) i.getSerializableExtra("shelterList");
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng cameraLocation = new LatLng(lat, lng); // Atlanta by default
        for (Shelter shelter : searchResults) {
	        // Add markers
	        LatLng shelterLocation = new LatLng(shelter.getLat(), shelter.getLng());
	        googleMap.addMarker(new MarkerOptions().position(shelterLocation)
	                       .title(shelter.getName() + " / " + shelter.getPhoneNum()));

	        // set camera location to be last of the loaded shelters
	        // set this to be user location later
	        cameraLocation = shelterLocation; 
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(cameraLocation));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cameraLocation, defaultZoom));
    }
}
