package cs2340.shelterbuzz.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import android.util.Log;

/**
 * Created by Sigma on 2/24/18.
 */

public class Shelter implements Parcelable {

    private String name;
	private int capacity;
	private String capacityString; // original shelter requirement max input
	private String restrictions; // parse for keywords, not standardized format
	private double longitude;
	private double latitude;
	private String address;
	private String specialNotes;
	private String phoneNumber;

	public Shelter(Parcel in) {
	    this.name = in.readString();
	    this.capacity = in.readInt();
	    this.capacityString = in.readString();
	    this.restrictions = in.readString();
	    this.longitude = in.readDouble();
	    this.latitude = in.readDouble();
	    this.address = in.readString();
	    this.specialNotes = in.readString();
	    this.phoneNumber = in.readString();
    }

	public Shelter(String name, String capacity, String restrictions,
	               double longitude, double latitude, String address,
	               String specialNotes, String phoneNumber) {
		this.name = name;		
		this.capacity = this.sumPositiveIntsInString(capacity);
		this.capacityString = capacity;
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
	    dest.writeInt(capacity);
	    dest.writeString(capacityString); // not necessary, but just in case we need
        dest.writeString(restrictions);
        dest.writeDouble(longitude);
        dest.writeDouble(latitude);
        dest.writeString(address);
        dest.writeString(specialNotes);
        dest.writeString(phoneNumber);
    }

    /**
     * Returns sum of integers in string
     *
     * @param str String
     * @return sum of all available non-negative integers
     */
	public int sumPositiveIntsInString(String str) {
		str = str.replaceAll("[^0-9]+", " ");  // replaces non-ints with spaces
		String[] nums = str.trim().split(" "); // splits string and puts Strings of numbers into array

		int sum = 0;
		for (String num : nums) {
			if (!num.equals("")) {
				sum += Integer.parseInt(num);
			}
		}
		return sum;
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

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
    public String getCapacityString() {
        return capacityString;
    }

    public void setCapacityString(String capacityString) {
        this.capacityString = capacityString;
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
