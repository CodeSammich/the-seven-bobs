package cs2340.shelterbuzz.controllers;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import cs2340.shelterbuzz.R;
import cs2340.shelterbuzz.model.Age;
import cs2340.shelterbuzz.model.Gender;
import cs2340.shelterbuzz.model.Model;
import cs2340.shelterbuzz.model.Shelter;

public class ShelterListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Shelter> shelters;
        String shelterName = getIntent().getStringExtra("name");
        // If start of this activity was a result of a search...
        if (shelterName != null) {
            // get shelters matching w/ user data
            Gender g = (Gender) getIntent().getSerializableExtra("gender");
            Age a = (Age) getIntent().getSerializableExtra("age");
            shelters = Model.getInstance().getShelters(shelterName, a, g);
        } else {
            // else get every shelter
            shelters = Model.getInstance().getShelters();
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
        intent.putExtra(ShelterDetailActivity.EXTRA_SHELTER, (Parcelable) getListView()
                .getItemAtPosition(position));
        startActivity(intent);
    }
}
