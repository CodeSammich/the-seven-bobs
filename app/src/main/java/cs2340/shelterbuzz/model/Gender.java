package cs2340.shelterbuzz.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tonyw on 2/24/2018.
 */

public enum Gender {
    UNSPECIFIED(""),
    MALE("Men", "Male"),
    FEMALE("Women", "Female"),
    GENDER_FLUID("Fluid"),
    GENDER_NEUTRAL("Agender", "Neutral");


    private final List<String> gender;

    Gender(String ... keyword) {
        this.gender = Arrays.asList(keyword);
    }

    public List<String> getValue() {
        return gender;
    }

    public static Gender enumOf(String word) {
        for (Gender gender : values()) {
            if (gender.getValue().contains(word)) {
                return gender;
            }
        }
        return null;
    }
}
