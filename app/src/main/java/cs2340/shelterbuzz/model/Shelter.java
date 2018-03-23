package cs2340.shelterbuzz.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

import com.google.firebase.database.Exclude;

/**
 * Created by Sigma on 2/24/18.
 */

public class Shelter implements Parcelable {

    private String name;
	private int capacity;
	private String capacityString; // original shelter requirement max input
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

	public Shelter(Parcel in) {
	    this.name = in.readString();
	    this.capacity = in.readInt();
	    this.capacityString = in.readString();
	    this.restrictionsString = in.readString();
	    this.lng = in.readDouble();
	    this.lat = in.readDouble();
	    this.address = in.readString();
	    this.notes = in.readString();
	    this.phoneNum = in.readString();
    }

	public Shelter(String name, String capacity, String restrictionsString,
	               double lng, double lat, String address,
	               String notes, String phoneNum) {
		this.name = name;		
		this.capacity = this.sumPositiveIntsInString(capacity);
		this.capacityString = capacity;
		this.restrictionsString = restrictionsString;
		this.lng = lng;
		this.lat = lat;
		this.address = address;
		this.notes = notes;
		this.phoneNum = phoneNum;
	}

    @Override
    public void writeToParcel(Parcel dest, int flags) {
	    dest.writeString(name);
	    dest.writeInt(capacity);
	    dest.writeString(capacityString); // not necessary, but just in case we need
        dest.writeString(restrictionsString);
        dest.writeDouble(lng);
        dest.writeDouble(lat);
        dest.writeString(address);
        dest.writeString(notes);
        dest.writeString(phoneNum);
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

	@Exclude
    public String getCapacityString() {
        return capacityString;
    }

    public void setCapacityString(String capacityString) {
        this.capacityString = capacityString;
    }

    public String getRestrictionsString() {
        return restrictionsString;
    }

    public void setrestrictionsString(String restrictionsString) {
        this.restrictionsString = restrictionsString;
    }

    public double getlng() {
        return lng;
    }

    public void setlng(double lng) {
        this.lng = lng;
    }

    public double getlat() {
        return lat;
    }

    public void setlat(double lat) {
        this.lat = lat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getnotes() {
        return notes;
    }

    public void setnotes(String notes) {
        this.notes = notes;
    }

    public String getphoneNum() {
        return phoneNum;
    }

    public void setphoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String toString() {
	    return name;
    }

    public String getName() {
        return name;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public List<Integer> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(List<Integer> restrictions) {
        this.restrictions = restrictions;
    }

}
