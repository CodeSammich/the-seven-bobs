package cs2340.shelterbuzz.model;

import java.util.Arrays;
import java.util.List;


public enum Gender {
    UNSPECIFIED(""),
    MALE("Men", "Male", "Anyone"),
    FEMALE("Women", "Female", "Anyone"),
    GENDER_FLUID("Fluid", "Anyone"),
    GENDER_NEUTRAL("Agender", "Neutral", "Anyone");


    private final List<String> gender;

    Gender(String ... keyword) {
        this.gender = Arrays.asList(keyword);
    }

    /**
     * Returns list of the enum's possible String representations.
     * @return Returns list of the enum's possible String representations.
     */
    public List<String> getValue() {
        return gender;
    }

    /**
     * Gets the enum constant of the associated with the String value passed in.
     * @param word gender restriction keyword
     * @return enum constant according to the string value.
     * If no matches are found, return null.
     */
    public static Gender enumOf(String word) {
        for (Gender gender : values()) {
            if (gender.getValue().contains(word)) {
                return gender;
            }
        }
        return null;
    }
}
