package cs2340.shelterbuzz.model;

/**
 * Created by tonyw on 2/24/2018.
 */

public enum Gender {
    MALE("Men", "Male"),
    FEMALE("Women", "Female"),
    GENDERFLUID("Gender Fluid"),
    GENDERNEUTRAL("Agender");

    private final String[] gender;

    Age(String ... keyword) {
        this.gender = keyword;
    }

    public String[] getGender() {
        return gender;
    }
}
