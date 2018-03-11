package cs2340.shelterbuzz.controllers;

import cs2340.shelterbuzz.R;
import cs2340.shelterbuzz.model.Model;
import cs2340.shelterbuzz.model.Shelter;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class ShelterDetailActivity extends Activity {

	private TextView name;
	private TextView capacity;
	private TextView restrictions;
	private TextView address;
	private TextView specialNotes;
	private TextView phoneNumber;

	public static final String EXTRA_SHELTER = "shelterNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_detail);

        name = (TextView) findViewById(R.id.name);
        capacity = (TextView) findViewById(R.id.capacity);
        restrictions = (TextView) findViewById(R.id.restrictions);
        address = (TextView) findViewById(R.id.address);
        specialNotes = (TextView) findViewById(R.id.notes);
        phoneNumber = (TextView) findViewById(R.id.phone);

        Shelter shelter = (Shelter) getIntent().getParcelableExtra(EXTRA_SHELTER);

        // use String.valueOf when passing in an int, or setText interprets as a
        // Android resource ID
        name.setText(shelter.getName());
        capacity.setText(String.valueOf(shelter.getCapacity())); 
        restrictions.setText(shelter.getRestrictions());
        address.setText(shelter.getAddress());
        specialNotes.setText(shelter.getSpecialNotes());
        phoneNumber.setText(shelter.getPhoneNumber());
    }
}
