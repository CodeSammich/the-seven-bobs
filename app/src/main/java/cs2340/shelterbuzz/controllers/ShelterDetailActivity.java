package cs2340.shelterbuzz.controllers;

import cs2340.shelterbuzz.R;
import cs2340.shelterbuzz.model.Model;
import cs2340.shelterbuzz.model.Shelter;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShelterDetailActivity extends Activity {

    public static final String EXTRA_SHELTER = "shelterNo";

    private Model model;
    private int shelterId;

	private TextView name;
	private TextView capacity;
	private TextView restrictions;
	private TextView address;
	private TextView specialNotes;
	private TextView phoneNumber;
	private EditText numRoomInput;
	private Button checkInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_detail);
        model = Model.getInstance();

        name = (TextView) findViewById(R.id.name);
        capacity = (TextView) findViewById(R.id.capacity);
        restrictions = (TextView) findViewById(R.id.restrictions);
        address = (TextView) findViewById(R.id.address);
        specialNotes = (TextView) findViewById(R.id.notes);
        phoneNumber = (TextView) findViewById(R.id.phone);
        numRoomInput = (EditText) findViewById(R.id.numRoomInput);
        checkInButton = (Button) findViewById(R.id.checkinButton);

        updateUI();
    }

    public void onCheckInPressed(View view) {
        try {
            if (model.getCurrentUser().isCheckedIn(shelterId)) {
                model.checkout();
                Toast.makeText(ShelterDetailActivity.this, "Check out successful!",
                        Toast.LENGTH_SHORT).show();
            } else {
                int numRooms = Integer.parseInt(numRoomInput.getText().toString());
                model.checkIn(shelterId, numRooms);
                Toast.makeText(ShelterDetailActivity.this, "Check in successful!",
                        Toast.LENGTH_SHORT).show();
            }
            // Give time for Firebase to update data
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    updateUI();
                }
            }, 500);
            updateUI();
        } catch (IllegalArgumentException e) {
            Toast.makeText(ShelterDetailActivity.this, e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void updateUI() {
        shelterId = getIntent().getIntExtra(EXTRA_SHELTER, -1);
        Shelter shelter = model.getShelter(shelterId);

        if (model.getCurrentUser().isCheckedIn()) {
            numRoomInput.setEnabled(false);
            checkInButton.setEnabled(false);
            if (model.getCurrentUser().isCheckedIn(shelterId)) {
                checkInButton.setEnabled(true);
                checkInButton.setText("Checkout");
            }
        } else {
            numRoomInput.setEnabled(true);
            checkInButton.setEnabled(true);
            checkInButton.setText("Check In");
        }

        name.setText(shelter.getName());
        capacity.setText(String.format("%d / %d",
                shelter.getRemaining(), shelter.getCapacity()));
        restrictions.setText(shelter.getRestrictionsString());
        address.setText(shelter.getAddress());
        specialNotes.setText(shelter.getNotes());
        phoneNumber.setText(shelter.getPhoneNum());
    }
}
