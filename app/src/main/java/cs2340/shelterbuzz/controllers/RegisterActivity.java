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
import cs2340.shelterbuzz.model.Gender;
import cs2340.shelterbuzz.model.HomelessPerson;
import cs2340.shelterbuzz.model.Model;
import cs2340.shelterbuzz.model.User;

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

        // set default text, set to "" during deployment!!
        nameField.setText("");
        useridField.setText("User");
        passwordField.setText("Password");

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,
                new ArrayList(Arrays.asList("User", "Admin", "Shelter Employee")));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountType.setAdapter(adapter);
    }

    public void onConfirmPressed(View view) {
        String name = nameField.getText().toString();
        String userid = useridField.getText().toString();
        String password = passwordField.getText().toString();

        User newuser = new HomelessPerson(name, userid, password, 25, false, Gender.FEMALE);

        Model.getInstance().getAccounts().add(newuser);
        //Log.d("stuff", LoginActivity.credentials.get(0));
        finish();
    }
}
