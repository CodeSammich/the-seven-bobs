package cs2340.shelterbuzz.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import android.util.Pair;

import cs2340.shelterbuzz.R;
import cs2340.shelterbuzz.model.Model;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText useridField;
    private EditText passwordField;
    private Spinner accountType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent CancelActivityIntent = new Intent(getApplicationContext(),
                        WelcomeActivity.class);
                startActivity(CancelActivityIntent);
            }
        }
        );

        nameField = (EditText) findViewById(R.id.editText2);
        useridField = (EditText) findViewById(R.id.editText3);
        passwordField = (EditText) findViewById(R.id.editText4);
        accountType = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,
                new ArrayList(Arrays.asList("User", "Admin", "Shelter Employee")));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountType.setAdapter(adapter);
    }

    public void onConfirmPressed(View view) {
        String name = nameField.getText().toString();
        String userid = useridField.getText().toString();
        String password = passwordField.getText().toString();

        Model model = Model.getInstance();

        LoginActivity.credentials.add(new Pair<>(userid, password));
        //Log.d("stuff", LoginActivity.credentials.get(0));
        finish();
    }
}
