package cs2340.shelterbuzz.model;
import android.util.Pair;

import cs2340.shelterbuzz.model.Gender;
import cs2340.shelterbuzz.model.User;
import cs2340.shelterbuzz.model.UserType;



public class HomelessPerson extends User {

    private Gender gender;
    private boolean veteran;
    private int age;

    /**
     * Creates a HomelessPerson object with the parameters passed in.
     * @param name name of the HomelessPerson
     * @param user the email of the person passed in
     * @param a the age of the person
     * @param v the veteran status of the person passed in
     * @param g the gender of the person passed in
     */
    public HomelessPerson(String name, String user, int a, boolean v, Gender g) {
        super(name, user, "homeless person");
        age = a;
        veteran = v;
        gender = g;
    }

    /**
     * Returns gender of this HomelessPerson
     * @return enum of the HomelessPerson's gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Returns veteran status of the HomelessPerson
     * @return boolean representing whether the HomelessPerson is a veteran
     */
    public boolean getVeteran() {
        return veteran;
    }

    /**
     * Returns age of the HomelessPerson
     * @return int of the HomelessPerson's age
     */
    public int getAge() {
        return age;
    }
}
