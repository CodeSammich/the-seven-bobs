package cs2340.shelterbuzz.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cs2340.shelterbuzz.R;
import cs2340.shelterbuzz.controllers.LoginActivity;
import cs2340.shelterbuzz.controllers.RegisterActivity;
import cs2340.shelterbuzz.model.Model;

public class WelcomeActivity extends AppCompatActivity {

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


        Model model = Model.getInstance();
        model.readCSV();
        Log.d("first line", model.getShelters().get(0).toString());
    }


}
