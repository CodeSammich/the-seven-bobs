package cs2340.shelterbuzz.model;

/**
 * Created by Sigma on 2/24/18.
 */

public class Shelter {
    private String name;
	private String capacity;
	private String restrictions; // parse for keywords, not standardized format
	private double longitude;
	private double latitude;
	private String address;
	private String specialNotes;
	private String phoneNumber;
	
	public Shelter() {
		this.name = "";
		this.capacity = "";
		this.restrictions = "";
		this.longitude = 0;
		this.latitude = 0;
		this.address = "";
		this.specialNotes = "";
		this.phoneNumber = "";
	}

	public Shelter(String name, String capacity, String restrictions,
	               double longitude, double latitude, String address,
	               String specialNotes, String phoneNumber) {
		this();
		this.name = name;
		this.capacity = capacity;
		this.restrictions = restrictions;
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
		this.specialNotes = specialNotes;
		this.phoneNumber = phoneNumber;
	}

	    public String getName() {
        return name;
    }

	// Getter and Setters
    public void setName(String name) {
        this.name = name;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecialNotes() {
        return specialNotes;
    }

    public void setSpecialNotes(String specialNotes) {
        this.specialNotes = specialNotes;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
	    return name + " Capacity: " + capacity + ", Restrictions: " + restrictions
                + ", Longitude: " + longitude + ", Latitude: " + latitude + ", Address: "
                + address + ", Phone number: " + phoneNumber;
    }
}
