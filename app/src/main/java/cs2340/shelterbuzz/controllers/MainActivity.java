package cs2340.shelterbuzz.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cs2340.shelterbuzz.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent WelcomeActivityIntent = new Intent(getApplicationContext(),
                        WelcomeActivity.class);
                startActivity(WelcomeActivityIntent);

            }}

        );
        Button listButton = findViewById(R.id.listButton);
        listButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent ShelterListActivityIntent = new Intent(getApplicationContext(),
                        ShelterListActivity.class);
                startActivity(ShelterListActivityIntent);

            }}

        );
        Button searchButton = findViewById(R.id.searchbutton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent SearchActivityIntent = new Intent(getApplicationContext(),
                        Main2Activity.class);
                startActivity(SearchActivityIntent);

            }}

        );
    }
}
