package cs2340.shelterbuzz.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.Serializable;

import cs2340.shelterbuzz.R;
import cs2340.shelterbuzz.model.Age;
import cs2340.shelterbuzz.model.Gender;

/**
 * Activity in which users search shelters based on the inputs
 * name, gender, and age
 */
public class SearchActivity extends AppCompatActivity {

    private EditText nameField;
    private Spinner gender;
    private Spinner age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main2);

        nameField = findViewById(R.id.nameFieldSearch);
        gender = findViewById(R.id.spinner3);
        age = findViewById(R.id.spinner4);

        // set default text to empty
        nameField.setText("");

        ArrayAdapter<Gender> adapter =
                new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Gender.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

        ArrayAdapter<Age> adapter2 =
                new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Age.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        age.setAdapter(adapter2);

    }

    /**
     * Searches shelters when user presses search button
     * @param view search button
     */
    public void onSearchPressed(View view) {
        String name = nameField.getText().toString();
        Serializable g = (Gender) gender.getSelectedItem();
        Serializable a = (Age) age.getSelectedItem();

        Intent intent = new Intent(this, ShelterListActivity.class);
        // Pass on user input to ShelterListActivity
        intent.putExtra("name", name);
        intent.putExtra("gender", g);
        intent.putExtra("age", a);
        startActivity(intent);
    }
}
