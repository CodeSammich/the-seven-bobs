package cs2340.shelterbuzz.model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;
import java.util.HashMap;

/**
 * Class with methods needed for users, manages users like the
 * ShelterManager class manages shelters
 */
public class UserManager {
    private static final UserManager instance = new UserManager();
    private static final String TAG = "UserManager";

    private final Map<String, User> users;
    private final DatabaseReference database;

    private User currentUser;

    private UserManager() {
        users = new HashMap<>();
        database = FirebaseDatabase.getInstance().getReference();

        ValueEventListener usersListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    if (user != null) {
                        Log.d(TAG, String.format("user added: %s", user.getUsername()));
                        users.put(user.getUsername(), user);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Log a message
                Log.d(TAG, "loadUsers:onCancelled", databaseError.toException());
            }
        };
        database.child("users").addValueEventListener(usersListener);
    }

    /**
     * returns an instance of a UserManager
     * @return user manager instance
     */
    public static UserManager getInstance() {
        return instance;
    }

    /**
     * adds a passed in User to the database
     * @param user the user to be passed into the database
     */
    public void add(User user) {
        database.child("users").child(user.getUsername()).setValue(user);
    }

    /**
     * checks in a user based on shelter ID and the number of beds inputted
     * by the user into the database
     *
     * @param shelterId the ID of the shelter being added to
     * @param numBeds the number of beds the user is checking out
     */
    public void checkIn(int shelterId, final int numBeds) {
        currentUser.checkIn(shelterId, numBeds);
        database.child("users").child(currentUser.getUsername()).setValue(currentUser);
    }

    /**
     * checks out a user from the shelter in the database
     */
    public void checkOut() {
        currentUser.checkOut();
        database.child("users").child(currentUser.getUsername()).setValue(currentUser);
    }

    /**
     * gets a User based on the user's username
     * @param userId user's username
     * @return the User associated with that username
     */
    public User get(String userId) {
        return users.get(userId);
    }

    /**
     * returns the user who is currently using that instance of the app
     * @return the User using the app
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * sets the current user to be the passed in user
     * @param u the user that is set to current user
     */
    public void setCurrentUser(User u) {
        currentUser = u;
    }
}
