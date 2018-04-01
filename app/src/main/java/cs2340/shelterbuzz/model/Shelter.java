package cs2340.shelterbuzz.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sigma on 2/24/18.
 */

public class Shelter implements Serializable {

    private int id; // Unique id
    private String name;
	private int capacity;
	private String restrictionsString; // parse for keywords, not standardized format
	private double lng;
	private double lat;
	private String address;
	private String notes;
	private String phoneNum;

    private List<Integer> restrictions;
    private int remaining;

    // No-args constructor required by Firebase
    public Shelter() {

    }

    public String getRestrictionsString() {
        return restrictionsString;
    }

    public int getId() {
	    return id;
    }

    public String getName() {
        return name;
    }

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }

    public String getAddress() {
        return address;
    }

    public String getNotes() {
        return notes;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public int getRemaining() {
        return remaining;
    }

    public List<Integer> getRestrictions() {
        return restrictions;
    }

    public int getCapacity() {
        return capacity;
    }

    public String toString() {
	    return name;
    }
}
