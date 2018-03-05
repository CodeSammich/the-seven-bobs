package cs2340.shelterbuzz.controllers;

import cs2340.shelterbuzz.R;
import cs2340.shelterbuzz.model.Shelter;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class ShelterDetailActivity extends Activity {

	TextView name;
	TextView capacity;
	TextView restrictions;
	TextView address;
	TextView specialNotes;
	TextView phoneNumber;

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

        Shelter shelter = (Shelter) getIntent().getParcelableExtra("parcel_data");
        name.setText(shelter.getName());
        capacity.setText(shelter.getCapacity());
        restrictions.setText(shelter.getRestrictions());
        address.setText(shelter.getAddress());
        specialNotes.setText(shelter.getSpecialNotes());
        phoneNumber.setText(shelter.getPhoneNumber());
    }


}
