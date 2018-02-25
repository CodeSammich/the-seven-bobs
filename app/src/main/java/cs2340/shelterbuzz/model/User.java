package cs2340.shelterbuzz.model;

import android.util.Pair;

/**
 * Created by tonyw on 2/24/2018.
 */

public abstract class User {
    //name of this user
    private String name;

    private String username;

    private String pass;

    //getters and setters
    public String getName() {
        return name;
    }


    public void setName(String s) {
        this.name = s;
    }


    public User(String name, String user, String pass) {
        this.name = name;
        this.username = user;
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
