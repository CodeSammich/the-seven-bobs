package cs2340.shelterbuzz.controllers;

import cs2340.shelterbuzz.R;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cs2340.shelterbuzz.model.Model;
import cs2340.shelterbuzz.model.User;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    private EditText nameField;
    private EditText userIdField;
    private EditText passwordField;
    private Spinner accountType;

    private Model model;
    private FirebaseAuth auth;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameField = (EditText) findViewById(R.id.name);
        userIdField = (EditText) findViewById(R.id.userId);
        passwordField = (EditText) findViewById(R.id.password);
        accountType = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                User.USER_TYPES);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountType.setAdapter(adapter);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        model = Model.getInstance();
    }

    public void onCancelPressed(View view) {
        Intent intent = new Intent(RegisterActivity.this, WelcomeActivity.class);
        startActivity(intent);
    }

    public void onConfirmPressed(View view) {
        // Need to be final to be accessed from inner classes for some reason
        final String name = nameField.getText().toString();
        final String userId = userIdField.getText().toString();
        String password = passwordField.getText().toString();
        final String userType = (String) accountType.getSelectedItem();

        Log.d("Button pressed", "Button pressed");
        auth.createUserWithEmailAndPassword(userId, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            //FirebaseUser user = auth.getCurrentUser();
                            User user = new User(name, userId, userType);
                            model.addUser(user);
                            model.setCurrentUser(user.getUsername());
                            Intent intent = new Intent(RegisterActivity.this, WelcomeActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user
                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}