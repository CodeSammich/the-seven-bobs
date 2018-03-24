package cs2340.shelterbuzz.controllers;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import cs2340.shelterbuzz.model.Age;
import cs2340.shelterbuzz.model.Gender;
import cs2340.shelterbuzz.model.Model;
import cs2340.shelterbuzz.model.Shelter;


import java.util.List;


public class ShelterListActivity extends ListActivity {

    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = Model.getInstance();

        List<Shelter> shelters;
        String shelterName = getIntent().getStringExtra("name");
        // If start of this activity was a result of a search...
        if (shelterName != null) {
            // only get shelters that match w/ user parameters
            Gender g = (Gender) getIntent().getSerializableExtra("gender");
            Age a = (Age) getIntent().getSerializableExtra("age");
            shelters = model.searchShelters(shelterName, a, g);
        } else {
            // else get every shelter
            shelters = Model.getInstance().getAllShelters();
        }

        // Populate list view with shelters
        ListAdapter listAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, shelters);
        ListView listView = getListView();
        listView.setAdapter(listAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, ShelterDetailActivity.class);
        // EXTRA_SHELTER is just a static final string, I do it like this
        // to guarantee consistency across activities
        intent.putExtra(ShelterDetailActivity.EXTRA_SHELTER, ((Shelter) getListView()
                .getItemAtPosition(position)).getId());
        startActivity(intent);
    }
}
