package cs2340.shelterbuzz.controllers;

import cs2340.shelterbuzz.R;
import cs2340.shelterbuzz.model.Model;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
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

	private static int counter = 0;
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
                                counter++; // add a number of tries counter lock user
                            }

                            if (counter >= 3) {
	                            // lock user out and start timer
	                            locked = true;
	                            
	                            counterDisplay = (TextView)findViewById(R.id.textView1);
	                            
	                            new CountDownTimer(5000, 1000) { // one minute
		                            private NotificationManager notifManager;
		                            
		                            public void onTick(long millisUntilFinished) {
			                            counterDisplay.setText("Please wait " + millisUntilFinished / 1000 + " seconds before attempting to login again");
			                            //here you can have your logic to set text to edittext
		                            }
		                            
		                            public void onFinish() {
			                            // push notification for API 27 (50 points EC!!)
			                            final int NOTIFY_ID = 1002;
			                            Context context = getApplicationContext();

			                            String name = "Shelterbuzz";
			                            String id = "Shelterbuzz"; // The user-visible name of the channel.
			                            String description = "You may now login again"; // The user-visible description of the channel.
			                            String message = "You may now login again";
			                            
			                            Intent intent;
			                            PendingIntent pendingIntent;
			                            NotificationCompat.Builder builder;
			                            
			                            if (notifManager == null) {
				                            notifManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
			                            }

			                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
				                            int importance = NotificationManager.IMPORTANCE_HIGH;
				                            NotificationChannel mChannel = notifManager.getNotificationChannel(id);
				                            if (mChannel == null) {
					                            mChannel = new NotificationChannel(id, name, importance);
					                            mChannel.setDescription(description);
					                            mChannel.enableVibration(true);
					                            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
					                            notifManager.createNotificationChannel(mChannel);
				                            }
				                            builder = new NotificationCompat.Builder(context, id);
				                            
				                            intent = new Intent(context, LoginActivity.class);
				                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
				                            pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
				                            
				                            builder.setContentTitle(message)  // required
					                            .setSmallIcon(R.drawable.ic_launcher) // required
					                            .setContentText(context.getString(R.string.app_name))  // required         
					                            .setDefaults(Notification.DEFAULT_ALL)
					                            .setAutoCancel(true)
					                            .setContentIntent(pendingIntent)
					                            .setTicker(message)
					                            .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
			                            } else {

				                            builder = new NotificationCompat.Builder(context);
				                            
				                            intent = new Intent(context, MainActivity.class);
				                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
				                            pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
				                            
				                            builder.setContentTitle(message)                           // required
					                            .setSmallIcon(R.drawable.ic_launcher) // required
					                            .setContentText(context.getString(R.string.app_name))  // required
					                            .setDefaults(Notification.DEFAULT_ALL)
					                            .setAutoCancel(true)
					                            .setContentIntent(pendingIntent)
					                            .setTicker(message)
					                            .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
					                            .setPriority(Notification.PRIORITY_HIGH);
			                            }
			                            
			                            Notification n = builder.build();

			                            notifManager.notify(NOTIFY_ID, n);


			                            // Reset login count and show user
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

