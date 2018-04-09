package cs2340.shelterbuzz.controllers;

import cs2340.shelterbuzz.R;
import cs2340.shelterbuzz.model.Model;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
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
    }

    public void onLoginAttempt(View view) {
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
                            model.setCurrentUser(user.getEmail().split("@")[0]);
                            showProgress(true);
                            Intent MainActivityIntent = new Intent(getApplicationContext(),
                                    MainActivity.class);
                            startActivity(MainActivityIntent);
                        } else {
                            Log.w("FAILURE", "signInWithEmail:failure", task.getException());

                            // If sign in fails, display a message to the user.
                            if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                                emailView.setError(getString(R.string.error_invalid_email));
                                emailView.requestFocus();
                            } else {
                                passwordView.setError(getString(R.string.error_incorrect_password));
                                passwordView.requestFocus();
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
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
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}

