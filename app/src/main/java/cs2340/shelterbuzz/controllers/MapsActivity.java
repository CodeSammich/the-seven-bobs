package cs2340.shelterbuzz.controllers;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import cs2340.shelterbuzz.R;
import cs2340.shelterbuzz.model.Model;
import cs2340.shelterbuzz.model.Shelter;

/**
 * The activity for displaying the Google Map of the shelters. Can either display
 * every shelter, or some shelters after they have been filtered by a search.
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private Model model;

	// Atlanta
	private double lat = 33.7490;
	private double lng = 84.3880;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        model = Model.getInstance();
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
        GoogleMap mMap;

        // Atlanta by default
        lat = this.lat;
        lng = this.lng;
        float defaultZoom = 12.0f;

        mMap = googleMap;

        List<Shelter> shelters = model.getAllShelters();

        LatLng cameraLocation = new LatLng(lat, lng);
        for (Shelter shelter : shelters) {
	        // Add markers
	        LatLng shelterLocation = new LatLng(shelter.getLat(), shelter.getLng());
	        mMap.addMarker(new MarkerOptions().position(shelterLocation)
	                       .title(shelter.getName() + " / " + shelter.getPhoneNum()));

	        // set camera location to be last of the loaded shelters
	        // set this to be user location later
	        cameraLocation = shelterLocation; 
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(cameraLocation));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cameraLocation, defaultZoom));
    }
}
