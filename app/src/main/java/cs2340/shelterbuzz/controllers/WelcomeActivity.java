package cs2340.shelterbuzz.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import cs2340.shelterbuzz.R;
import cs2340.shelterbuzz.controllers.LoginActivity;
import cs2340.shelterbuzz.controllers.RegisterActivity;
import cs2340.shelterbuzz.model.Model;
import cs2340.shelterbuzz.model.Shelter;

public class WelcomeActivity extends AppCompatActivity {

    private static final String TAG = "MY_APP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent LoginActivityIntent = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(LoginActivityIntent);
            }

            }
            );

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent RegisterActivityIntent = new Intent(getApplicationContext(),
                            RegisterActivity.class);
                    startActivity(RegisterActivityIntent);
                }
                }

        );
        readCSV();
        //button for testing the shelter view
//        Button shitButton = findViewById(R.id.test);
//        shitButton.setOnClickListener(new View.OnClickListener() {
//                                              public void onClick(View v) {
//                                                  Intent RegisterActivityIntent = new Intent(getApplicationContext(),
//                                                          ShelterListActivity.class);
//                                                  startActivity(RegisterActivityIntent);
//                                              }
//                                          }
//
//        );


//        Model model = Model.getInstance();
//        model.readCSV();
//        Log.d("first line", model.getShelters().get(0).toString());

    }

    public void readCSV() {
        try {
            Model model = Model.getInstance();
            //Open a stream on the raw file
            InputStream is = getResources().openRawResource(R.raw.homeless_shelter_database);
            //From here we probably should call a model method and pass the InputStream
            //Wrap it in a BufferedReader so that we get the readLine() method
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String line;
            br.readLine(); //get rid of header line
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                model.addShelter(tokens[1], tokens[2], tokens[3],
                        Double.parseDouble(tokens[4]), Double.parseDouble(tokens[5]), tokens[6], tokens[7], tokens[8]);
            }

            for (Shelter shelter : model.getShelters()) {
                Log.d(TAG, shelter.toString());
            }

            br.close();
        } catch (IOException e) {
            Log.e(TAG, "error reading assets", e);
        }
    }

}
