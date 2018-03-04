package cs2340.shelterbuzz.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tonyw on 2/24/2018.
 */

public enum Gender {
    MALE("Men", "Male"),
    FEMALE("Women", "Female"),
    GENDERFLUID("Gender Fluid"),
    GENDERNEUTRAL("Agender");

    private final List<String> gender;

    Age(String ... keywords) {
        this.gender = Arrays.asList(keywords);
    }

    public List<String> getGender() {
        return gender;
    }

}
