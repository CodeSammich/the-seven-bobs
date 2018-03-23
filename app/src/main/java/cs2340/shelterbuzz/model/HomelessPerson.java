package cs2340.shelterbuzz.model;
import android.util.Pair;

import cs2340.shelterbuzz.model.Gender;
import cs2340.shelterbuzz.model.User;
import cs2340.shelterbuzz.model.UserType;

/**
 * Created by tonyw on 2/24/2018.
 */

public class HomelessPerson extends User {

    private Gender gender;
    private boolean veteran;
    private int age;

    private String checkedIn;

    public HomelessPerson(String name, String user, int a, boolean v, Gender g) {
        super(name, user, "homeless person");
        age = a;
        veteran = v;
        gender = g;
    }

    public Gender getGender() {
        return gender;
    }
    public boolean getVeteran() {
        return veteran;
    }
    public int getAge() {
        return age;
    }
    public boolean isCheckedIn() {
        return !checkedIn.equals("");
    }

    public void checkIn(String shelterName) {
        checkedIn = shelterName;
    }
}
