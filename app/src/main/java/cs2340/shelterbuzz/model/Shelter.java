package cs2340.shelterbuzz.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sigma on 2/24/18.
 */

public class Shelter implements Parcelable {

    private String name;
	private String capacity;
	private String restrictions; // parse for keywords, not standardized format
	private double longitude;
	private double latitude;
	private String address;
	private String specialNotes;
	private String phoneNumber;

	public Shelter(Parcel in) {
	    name = in.readString();
	    capacity = in.readString();
	    restrictions = in.readString();
	    longitude = in.readDouble();
	    latitude = in.readDouble();
	    address = in.readString();
	    specialNotes = in.readString();
	    phoneNumber = in.readString();
    }

	public Shelter(String name, String capacity, String restrictions,
	               double longitude, double latitude, String address,
	               String specialNotes, String phoneNumber) {
		this.name = name;
		this.capacity = capacity;
		this.restrictions = restrictions;
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
		this.specialNotes = specialNotes;
		this.phoneNumber = phoneNumber;
	}

    @Override
    public void writeToParcel(Parcel dest, int flags) {
	    dest.writeString(name);
	    dest.writeString(capacity);
        dest.writeString(restrictions);
        dest.writeDouble(longitude);
        dest.writeDouble(latitude);
        dest.writeString(address);
        dest.writeString(specialNotes);
        dest.writeString(phoneNumber);
    }

    public static final Parcelable.Creator<Shelter>CREATOR = new Parcelable.Creator<Shelter>() {
	    public Shelter createFromParcel(Parcel in) {
	        return new Shelter(in);
        }
        public Shelter[] newArray(int size) {
	        return new Shelter[size];
        }
    };

	public int describeContents() {
	    return 0;
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
	    return name;
    }

    public String getName() {
        return name;
    }
}
