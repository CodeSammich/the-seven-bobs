package cs2340.shelterbuzz.model;

import com.google.firebase.database.Exclude;

import java.util.List;
import java.util.ArrayList;


/**
 * User class that contains information on each user, namely
 * their name, username and type of user they are, as well as if they are
 * checked in or not.
 */
public class User {

    public static final String[] USER_TYPES = {"homeless person", "admin", "shelter employee"};

    private String name;
    private String username;
    private String userType;

    private List<Integer> checkIn; // (shelterId, numBeds)


    /**
     * No-args constructor required by Firebase
     */
    public User() {
        this.checkIn = new ArrayList<>();
    }

    /**
     * Constructs a user with the passed in name, username, and type of user.
     * @param name String name of the user's name
     * @param user String user of the username
     * @param userType String userType that tells what type of user (among homeless person,
     *                 shelter employee, or app admin)
     */
    public User(String name, String user, String userType) {
        this.name = name;
        this.username = user.split("@")[0];
        this.userType = userType;
        this.checkIn = new ArrayList<>();
    }

    /**
     * returns true or false based on if the user is checked into any shelter
     * @return a boolean telling if the user is checked into any shelter
     */
    public boolean isCheckedIn() {
        return !checkIn.isEmpty();
    }

    /**
     * returns true or false based on if the user is checked into specific shelter
     * @param shelterId the ID of the specific shelter being checked
     * @return a boolean telling if the user is checked into the specific shelter
     */
    public boolean isCheckedIn(int shelterId) {
        return (!checkIn.isEmpty()) && (checkIn.get(0) == shelterId);
    }

    /**
     * checks the user into a specific shelter based on the number of the beds the
     * user input and adds it to a list containing the ID and number of beds
     *
     * @param shelterId the ID of the shelter being checked into
     * @param numBeds the number of beds the user checks out
     */
    public void checkIn(int shelterId, int numBeds) {
        checkIn = new ArrayList<>(2);
        checkIn.add(shelterId);
        checkIn.add(numBeds);
    }

    /**
     * checks the user out by nulling out the checkIn list, meaning the user is no longer
     * checked into a shelter
     */
    public void checkOut() {
        checkIn.clear();
    }

    //getters and setters

    /**
     * gets the user's username
     * @return user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return id of shelter checked in
     */
    @Exclude
    public int getShelterCheckedIn() {
        return checkIn.get(0);
    }

    /**
     * @return num beds checked in
     */
    @Exclude
    public int getNumBedsCheckedIn() {
        return checkIn.get(1);
    }
}
