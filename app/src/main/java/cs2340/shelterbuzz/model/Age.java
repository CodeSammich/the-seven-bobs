package cs2340.shelterbuzz.model;

import java.util.List;
import java.util.Arrays;


/**
 * Created by YEJIKIM on 3/3/18.
 * Enum class of age ranges
 */

public enum Age {
    FAMILIES("Families", "Families w/", "Families with"),
    CHILDREN("Children", "Childrens"),
    TEENS("Young adults", "Young Adults", "Teenagers"),
    ANYONE;

    private final List<String> age;

    Age(String ... keywords) {
        this.age = Arrays.asList(keywords);
    }

    public List<String> getValue() {
        return age;
    }

    /**
     *
     * @param word age restriction keyword
     * @return range enum constant according to the string value
     */

    @Override
    public static Age valueOf(String word) throws IllegalArgumentException {
        for (Age age : values()) {
            if (age.equals(word)) {
                return age;
            }
        }
        throw new IllegalArgumentException("There is no age range indicated of that age.");
    }

    //equals

}


