package cs2340.shelterbuzz.model;

import java.util.List;
import java.util.Arrays;


/**
 * Created by YEJIKIM on 3/3/18.
 * Enum class of age ranges
 */

public enum Age {
    UNSPECIFIED(""),
    FAMILIES("Families", "Families w/", "Families with", "Family"),
    CHILDREN("Children", "Childrens", "Child", "Newborn"),
    TEENS("Young adults", "Young Adults", "Teenagers", "Teens"),
    VETERAN("Veterans", "Vet", "Veteran", "Military"),
    ANYONE("Anyone", "Everyone", "All");

    private final List<String> age;

    Age(String ... keywords) {
        this.age = Arrays.asList(keywords);
    }

    /**
     * Returns a list of the possible String values of the enum.
     * @return List of possible String values of the enum.
     */
    public List<String> getValue() {
        return age;
    }

    /**
     * Gets the enum constant of the value it is associated with
     * @param word age restriction keyword
     * @return range enum constant according to the string value.
     * If no matches are found, return null.
     */
    public static Age enumOf(String word) {
        for (Age age : values()) {
            if (age.getValue().contains(word)) {
                return age;
            }
        }

        return null;
    }


}


