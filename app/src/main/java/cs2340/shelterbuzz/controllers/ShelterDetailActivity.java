package cs2340.shelterbuzz.controllers;

import cs2340.shelterbuzz.R;
import cs2340.shelterbuzz.model.Model;
import cs2340.shelterbuzz.model.Shelter;
import cs2340.shelterbuzz.model.User;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

/**
 *
 * An activity class that holds UI elements from the shelter detail activity xml so that they can be
 * updated with the information on whichever shelter was selected. This is just one activity
 * that changes according to passed in shelter data to the methods, which is then used to update
 * the UI fields on the xml.
 *
 */
public class ShelterDetailActivity extends Activity {

    public static final String EXTRA_SHELTER = "shelterNo";

    private Model model;
    private int shelterId;
    private User currentUser;

	private TextView name;
	private TextView capacity;
	private TextView restrictions;
	private TextView address;
	private TextView specialNotes;
	private TextView phoneNumber;
	private EditText numRoomInput;
	private Button checkInButton;

    // Frequency at which UI
	private final int updateFreq = 500; // ms

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_detail);
        model = Model.getInstance();
        currentUser = model.getCurrentUser();

        name = findViewById(R.id.name);
        capacity = findViewById(R.id.capacity);
        restrictions = findViewById(R.id.restrictions);
        address = findViewById(R.id.address);
        specialNotes = findViewById(R.id.notes);
        phoneNumber = findViewById(R.id.phone);
        numRoomInput = findViewById(R.id.numRoomInput);
        checkInButton = findViewById(R.id.checkinButton);

        updateUI();
    }

    /**
     * A method that takes in a view which is the button that was clicked, in this case
     * the check in button. It allows users to check into a shelter in the shelter detail activity
     * by calling the check in and check out methods in the model.
     *
     * It also gives users a pop up stating if their check in or check out was successful, or if
     * an invalid field was entered, then an error message pops up.
     *
     * Finally, the method gives time for firebase to update with the check in data before updating
     * the UI with the user's check in or out.
     *
     * @param view the button that is connected to the handler, which is check in
     */
    public void onCheckInPressed(View view) {
        try {
            if (currentUser.isCheckedIn(shelterId)) {
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
            }, updateFreq);
            updateUI();
        } catch (IllegalArgumentException e) {
            Toast.makeText(ShelterDetailActivity.this, e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method gets the shelter that is to be rendered and loads all of the shelter data into
     * the respective text fields in the detail activity xml. If the user using the app is not
     * currently checked in, then the user will have an option to input bed numbers and click the
     * check in button.
     * If the user is already checked in, then the user will be able to click the button with
     * the updated phrase "checkout"
     */
    private void updateUI() {
        shelterId = getIntent().getIntExtra(EXTRA_SHELTER, -1);
        Shelter shelter = model.getShelter(shelterId);

        if (currentUser.isCheckedIn()) {
            numRoomInput.setEnabled(false);
            checkInButton.setEnabled(false);
            if (currentUser.isCheckedIn(shelterId)) {
                checkInButton.setEnabled(true);
                checkInButton.setText("Check Out");
            }
        } else {
            numRoomInput.setEnabled(true);
            checkInButton.setEnabled(true);
            checkInButton.setText("Check In");
        }

        name.setText(shelter.getName());
        capacity.setText(String.format(Locale.getDefault(), "%d / %d",
                shelter.getRemaining(), shelter.getCapacity()));
        restrictions.setText(shelter.getRestrictionsString());
        address.setText(shelter.getAddress());
        specialNotes.setText(shelter.getNotes());
        phoneNumber.setText(shelter.getPhoneNum());
    }
}
