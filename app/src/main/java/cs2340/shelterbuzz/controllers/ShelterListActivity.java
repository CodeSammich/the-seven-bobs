package cs2340.shelterbuzz.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cs2340.shelterbuzz.R;
import cs2340.shelterbuzz.model.Model;
import cs2340.shelterbuzz.model.Shelter;

public class ShelterListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_list);

        ListView shelters = (ListView)findViewById(R.id.shelter_list);
        //CODE BELOW WILL MAKE IT SO WHEN YOU CLICK ON A LIST ITEM, IT TAKES YOU TO DETAILS PAGE
        shelters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(getBaseContext(), ShelterDetailActivity.class);
                intent.putExtra("parcel_data", (Parcelable) parent.getItemAtPosition(position));
                startActivity(intent);
            }
        });
        load();



    }
    public void load() {
        Model model = Model.getInstance();
        List<Shelter> shelters = model.getShelters();
        //just putting some dummy info

        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, shelters);
        ListView shelterList = findViewById(R.id.shelter_list);
        shelterList.setAdapter(listAdapter);
    }
}
