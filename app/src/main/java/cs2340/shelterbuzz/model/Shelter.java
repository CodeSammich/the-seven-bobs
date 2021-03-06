package cs2340.shelterbuzz.model;

/**
 * Represents a homeless shelter
 */
public class Shelter {

    private int id; // Unique id
    private String name;
	private int capacity;
	private String restrictionsString; // parse for keywords, not standardized format
	private double lng;
	private double lat;
	private String address;
	private String notes;
	private String phoneNum;

    private int remaining;

    /**
     * No-args constructor required by Firebase
     */
    public Shelter() {

    }

    /**
     * Constructs a shelter
     *
     * @param id shelter ID
     * @param name shelter name
     * @param capacity shelter capacity
     * @param restrictionsString shelter restrictions represented as string
     * @param lng shelter longitude
     * @param lat shelter latitude
     * @param address shelter address
     * @param notes shelter notes
     * @param phoneNum shelter phoneNum
     */
    public Shelter(int id, String name, int capacity, String restrictionsString,
                   double lng, double lat, String address, String notes, String phoneNum) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.restrictionsString = restrictionsString;
        this.lng = lng;
        this.lat = lat;
        this.address = address;
        this.notes = notes;
        this.phoneNum = phoneNum;
        this.remaining = capacity;
    }

    /**
     * Gets string representation of the restrictions of the shelter
     *
     * @return string string representation of the restrictions of the shelter
     */
    public String getRestrictionsString() {
        return restrictionsString;
    }

    /**
     * Gets unique ID of the shelter
     *
     * @return id int value of unique ID of the shelter
     **/
    public int getId() {
	    return id;
    }


    /**
     * Gets name of the shelter
     *
     * @return name string value of name of the shelter
     **/
    public String getName() {
        return name;
    }


    /**
     * Gets longitudinal coordinate of the shelter
     *
     * @return lng double value of longitudinal coordinate of the shelter
     **/
    public double getLng() {
        return lng;
    }

    /**
     * Gets latitudinal coordinate of the shelter
     *
     * @return lat double value of latitudinal coordinate of the shelter
     **/
    public double getLat() {
        return lat;
    }

    /**
     * Gets address of the shelter
     * @return address string representation of address of the shelter
     */
    public CharSequence getAddress() {
        return address;
    }

    /**
     * Gets extra notes of the shelter
     * @return notes string representation of extra notes of the shelter
     */
    public CharSequence getNotes() {
        return notes;
    }

    /**
     * Gets phone number contact of the shelter
     * @return phoneNum int value of phone number contact of the shelter
     */
    public CharSequence getPhoneNum() {
        return phoneNum;
    }

    /**
     * Gets number of vacancies left in the shelter
     * @return remaining int value of number of vacancies left in the shelter
     */
    public int getRemaining() {
        return remaining;
    }

    /**
     * Gets total capacity the shelter
     * @return capacity int value of total capacity of the shelter
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns name of the shelter
     * @return name string value of name of the shelter
     */
    public String toString() {
	    return name;
    }
}
