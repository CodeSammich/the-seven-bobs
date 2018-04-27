package cs2340.shelterbuzz.controllers;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import cs2340.shelterbuzz.R;
import cs2340.shelterbuzz.model.Age;
import cs2340.shelterbuzz.model.Gender;
import cs2340.shelterbuzz.model.Model;
import cs2340.shelterbuzz.model.Shelter;

import java.util.ArrayList;
import java.util.List;

/**
 * The controller for the shelter list activity view. This includes populating the list view with
 * all of the shelters as a list by iterating through and adding them. It also handles if
 * a search was performed, in which case it will call the searchShelter method to return a
 * filtered list of shelters to populate the activity with.
 * Finally, it has a method that allows users to click on a shelter in the list view and have
 * the shelter details pop up.
 */
public class ShelterListActivity extends ListActivity {

	private Model model;
	private List<Shelter> shelters;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shelter_list);

		model = Model.getInstance();

		Intent intent = getIntent();
		String shelterName = intent.getStringExtra("name");
		// If start of this activity was a result of a search...
		if (shelterName != null) {
			// only get shelters that match w/ user parameters
			Gender g = (Gender) intent.getSerializableExtra("gender");
			Age a = (Age) intent.getSerializableExtra("age");
			shelters = model.searchShelters(shelterName, a, g);
		} else {
			// else get every shelter
			shelters = model.getAllShelters();
		}

		// Populate list view with shelters
		ListAdapter listAdapter = new ArrayAdapter<>(this,
		                                             android.R.layout.simple_list_item_1, shelters);
		ListView listView = getListView();
		listView.setAdapter(listAdapter);

		// Map Button to switch to map with searched shelters as pins
		Button mapViewButton = findViewById(R.id.MapButton);
		mapViewButton.setOnClickListener(new View.OnClickListener() {
				/* Another way to pass shelters into anon class without 
				   declaring list to be final */
				private List<Shelter> shelters;
				private View.OnClickListener init(List<Shelter> list) {
					shelters = list;
					return this;
				}

				@Override
				public void onClick(View v) {
					Intent searchMapActivityIntent =
						new Intent(getApplicationContext(), SearchMapActivity.class);
                    List<Integer> sheltersById = new ArrayList<>();
                    for (Shelter s : shelters) {
                        sheltersById.add(s.getId());
                    }
					searchMapActivityIntent.putExtra("shelterList", (ArrayList<Integer>)sheltersById);
					startActivity(searchMapActivityIntent);
				}
			}.init(shelters) ); // works because setOnClickListener expects listener
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(this, ShelterDetailActivity.class);
		// EXTRA_SHELTER is just a static final string, I do it like this
		// to guarantee consistency across activities
		intent.putExtra(ShelterDetailActivity.EXTRA_SHELTER,
		                ((Shelter) getListView().getItemAtPosition(position)).getId());
		startActivity(intent);
	}
}
