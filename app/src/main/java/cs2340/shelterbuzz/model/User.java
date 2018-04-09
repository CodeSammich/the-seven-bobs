package cs2340.shelterbuzz.model;

import java.util.List;
import java.util.ArrayList;



public class User {

    public static final String[] USER_TYPES = {"homeless person", "admin", "shelter employee"};

    private String name;
    private String username;
    private String userType;

    private List<Integer> checkIn; // (shelterId, numBeds)

    // No-args constructor required by Firebase
    public User() {

    }

    public User(String name, String user, String userType) {
        this.name = name;
        this.username = user.split("@")[0];
        this.userType = userType;
        this.checkIn = null;
    }

    public boolean isCheckedIn() {
        return checkIn != null;
    }

    public boolean isCheckedIn(int shelterId) {
        return checkIn != null && checkIn.get(0) == shelterId;
    }

    public void checkIn(int shelterId, int numBeds) {
        checkIn = new ArrayList<>(2);
        checkIn.add(shelterId);
        checkIn.add(numBeds);
    }

    public void checkOut() {
        checkIn = null;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public List<Integer> getCheckIn() {
        return checkIn;
    }

    public String getUserType() {
        return userType;
    }
}
