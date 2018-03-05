package cs2340.shelterbuzz.controllers;

import cs2340.shelterbuzz.R;
import cs2340.shelterbuzz.model.Shelter;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class ShelterDetailActivity extends Activity {

    TextView name = (TextView) findViewById(R.id.name);
    TextView capacity = (TextView) findViewById(R.id.capacity);
    TextView restrictions = (TextView) findViewById(R.id.restrictions);
    TextView address = (TextView) findViewById(R.id.address);
    TextView specialNotes = (TextView) findViewById(R.id.notes);
    TextView phoneNumber = (TextView) findViewById(R.id.phone);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_detail);

        Shelter shelter = (Shelter) getIntent().getParcelableExtra("parcel_data");
        name.setText(shelter.getName());
        capacity.setText(shelter.getCapacity());
        restrictions.setText(shelter.getRestrictions());
        address.setText(shelter.getAddress());
        specialNotes.setText(shelter.getSpecialNotes());
        phoneNumber.setText(shelter.getPhoneNumber());
    }


}
