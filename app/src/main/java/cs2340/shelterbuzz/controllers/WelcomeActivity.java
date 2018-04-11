package cs2340.shelterbuzz.controllers;

import cs2340.shelterbuzz.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * The welcome activity contains the first screen that pops up, which includes
 * the login button and the register button and the logo
 */
public class WelcomeActivity extends AppCompatActivity {
    private static final String TAG = "WelcomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginActivityIntent = new Intent(WelcomeActivity.this,
                        LoginActivity.class);
                startActivity(LoginActivityIntent);
            }

            }
            );

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RegisterActivityIntent = new Intent(WelcomeActivity.this,
                        RegisterActivity.class);
                startActivity(RegisterActivityIntent);
            }
        });
        Log.d("Doh!", TAG);
    }
}
