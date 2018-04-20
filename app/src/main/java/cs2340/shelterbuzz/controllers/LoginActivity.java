package cs2340.shelterbuzz.controllers;

import cs2340.shelterbuzz.R;
import cs2340.shelterbuzz.model.Model;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

    private Model model;

    private AutoCompleteTextView emailView;
    private EditText passwordView;
    private View progressView;
    private View loginFormView;

    private FirebaseAuth auth;

	private int counter = 0;
	private boolean locked = false;
	private TextView counterDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        model = Model.getInstance();

        // Set up the login form
        emailView = findViewById(R.id.email);
        passwordView = findViewById(R.id.password);
        loginFormView = findViewById(R.id.login_form);
        progressView = findViewById(R.id.login_progress);

        auth = FirebaseAuth.getInstance();

        // just make counter time invisible by default
        counterDisplay = (TextView)findViewById(R.id.textView1);
        counterDisplay.setText("");
    }

	@Override
	public void onBackPressed() {
		if (locked) {
			// this block disable back button
			// display toast message to user
			Toast.makeText(getApplicationContext(), "You cannot unlock your account until 1 minute is up", Toast.LENGTH_SHORT).show();
		} else {
			// this block enable back button
			super.onBackPressed();       
		}
	}

	
    /**
     * Runs when the user presses the "Login" button.
     * @param view the button that onLoginAttempt is tied to.
     */
    public void onLoginAttempt(View view) {
	    if (counter > 2) {
		    emailView.setError("You have failed too many times. You are now locked out of the account for 1 minute");
		    emailView.requestFocus();
		    return;
	    }
	    
        String email = emailView.getText().toString();
        String pass = passwordView.getText().toString();

        if (email.isEmpty()) {
            emailView.setError(getString(R.string.error_field_required));
            emailView.requestFocus();
        } else if (pass.isEmpty()) {
            passwordView.setError(getString(R.string.error_field_required));
            passwordView.requestFocus();
        }

        auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("TEST", "Testing authetication");

                        if (task.isSuccessful()) {
                            // Sign in success
                            Log.d("SUCCESSS", "signInWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            if (user != null) {
                                String email = user.getEmail();
                                if (email != null) {
                                    model.setCurrentUser(email.split("@")[0]);
                                    showProgress(true);
                                    Intent MainActivityIntent = new Intent(getApplicationContext(),
                                            MainActivity.class);
                                    startActivity(MainActivityIntent);
                                }
                            }
                        } else {
                            Log.w("FAILURE", "signInWithEmail:failure", task.getException());

                            // If sign in fails, display a message to the user.
                            if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                                emailView.setError(getString(R.string.error_invalid_email));
                                emailView.requestFocus();
                            } else {
	                            passwordView.setError(getString(R.string.error_incorrect_password) + " " + (counter+1) + " times");
                                passwordView.requestFocus();
                            }

                            counter++; // add a number of tries counter lock user

                            if (counter >= 3) {
	                            // lock user out and start timer
	                            locked = true;
	                            
	                            counterDisplay = (TextView)findViewById(R.id.textView1);
	                            
	                            new CountDownTimer(60000, 1000) { // one minute
		                            public void onTick(long millisUntilFinished) {
			                            counterDisplay.setText("Please wait " + millisUntilFinished / 1000 + " seconds before attempting to login again");
			                            //here you can have your logic to set text to edittext
		                            }
		                            
		                            public void onFinish() {
			                            counterDisplay.setText("You may now log in your account again");
			                            counter = 0;
			                            locked = false;
		                            }
	                            }.start();
                            }
                        }
                        showProgress(false);
                    }
                });

    }

    /**
     * Shows the progress UI and hides the login form.
     * Tony is quite fond of this visual amenity.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        loginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        progressView.setVisibility(show ? View.VISIBLE : View.GONE);
        progressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }
}

