package cs2340.shelterbuzz.model;

import android.util.Pair;

/**
 * Created by tonyw on 2/24/2018.
 */

public abstract class User {
    //name of this user
    private String name;

    //Username and password Pair
    private Pair<String, String> userPass;

    //getters and setters
    public String getName() {
        return name;
    }

    public Pair<String, String> getUserPass() {
        return userPass;
    }

    public void setName(String s) {
        this.name = s;
    }

    public void setUserPass(Pair<String, String> p) {
        this.userPass = p;
    }

    public User(String name, Pair<String, String> pair) {
        this.name = name;
        this.userPass = pair;
    }
}
