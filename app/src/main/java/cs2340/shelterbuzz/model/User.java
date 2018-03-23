package cs2340.shelterbuzz.model;

/**
 * Created by tonyw on 2/24/2018.
 */

public class User {

    public static final String[] USER_TYPES = {"homeless person", "admin", "shelter employee"};

    private String name;
    private String username;
    private String userType;

    // No-args constructor required by Firebase
    public User() {

    }

    public User(String name, String user, String userType) {
        this.name = name;
        this.username = user.split("@")[0];
        this.userType = userType;
    }

    //getters and setters
    public String getName() {
        return name;
    }
    public void setName(String s) {
        this.name = s;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserType() {
        return userType;
    }
}
