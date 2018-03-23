package cs2340.shelterbuzz.model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;
import java.util.HashMap;


public class UserManager {
    private static final UserManager instance = new UserManager();
    private static final String TAG = "UserManager";

    private Map<String, User> users;
    private DatabaseReference database;

    private UserManager() {
        users = new HashMap<>();
        database = FirebaseDatabase.getInstance().getReference();

        ValueEventListener sheltersListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    users.put(user.getUsername(), user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Log a message
                Log.d(TAG, "loadUsers:onCancelled", databaseError.toException());
            }
        };
        database.child("users").addValueEventListener(sheltersListener);
    }

    public static final UserManager getInstance() {
        return instance;
    }

    public void add(User user) {
        database.child("users").child(user.getUsername()).setValue(user);
    }

    public User get(String userId) {
        return users.get(userId);
    }
}
