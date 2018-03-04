package cs2340.shelterbuzz.model;

/**
 * Created by YEJIKIM on 3/3/18.
 * Enum class of age ranges
 */

public enum Age {
    FAMILIES("Families", "Families w/", "Families with"),
    CHILDREN("Children", "Childrens"),
    TEENS("Young adults", "Young Adults", "Teenagers"),
    ANYONE;

    private final String[] age;

    Age(String ... keyword) {
        this.age = keyword;
    }

    public String[] getAge() {
        return age;
    }


}


