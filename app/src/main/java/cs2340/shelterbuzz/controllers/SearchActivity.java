package cs2340.shelterbuzz.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import cs2340.shelterbuzz.R;
import cs2340.shelterbuzz.model.Age;
import cs2340.shelterbuzz.model.Gender;

public class SearchActivity extends AppCompatActivity {

    private EditText nameField;
    private Spinner gender;
    private Spinner age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main2);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        nameField = (EditText) findViewById(R.id.nameFieldSearch);
        gender = (Spinner) findViewById(R.id.spinner3);
        age = (Spinner) findViewById(R.id.spinner4);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Gender.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Age.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        age.setAdapter(adapter2);

    }

    public void onSearchPressed(View view) {
        String name = nameField.getText().toString();
        Gender g = (Gender) gender.getSelectedItem();
        Age a = (Age) age.getSelectedItem();

        Intent intent = new Intent(this, ShelterListActivity.class);
        // Pass on user input to ShelterListActivity
        intent.putExtra("name", name);
        intent.putExtra("gender", g);
        intent.putExtra("age", a);
        startActivity(intent);
    }
}