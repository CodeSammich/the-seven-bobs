package cs2340.shelterbuzz.model;

import java.util.List;

/**
 * Created by tonyw and Sigma on 2/24/2018.
 * Facade
 */
public class Model {
	private static final Model _instance = new Model();

	private ShelterManager shelterManager = ShelterManager.getInstance();
	private UserManager userManager = UserManager.getInstance();

	public static Model getInstance() { return _instance; }

	/**
	 * Adds a user to the accounts list
	 *
	 * @param u user account
	 * @return true if user added successfully, false otherwise
	 */
	public boolean addUser(User u) {
        userManager.add(u);
        return true;
	}


	/**
	 * Gets current user of the app
	 * @return current user of app
	 */
    public User getCurrentUser() {
        return userManager.getCurrentUser();
    }

	/**
	 * Set current user using user ID
	 * @param userId string representation of user ID
	 */
	public void setCurrentUser(String userId) {
        userManager.setCurrentUser(userManager.get(userId));
    }

	/**
	 * Returns the complete list of shelters
	 * @return list of all shelters
	 */
	public List<Shelter> getAllShelters() {
		return shelterManager.getAll();
	}

	/**
	 * Returns shelter with the shelter ID
	 * @return shelter by the shelter ID
	 */
	public Shelter getShelter(int shelterID) {
		return shelterManager.get(shelterID);
	}

	/**
	 * Searches shelters using shelter name, age, and gender restrictions
	 * @param name string representation of shelter name
	 * @param age Age enum value of age restriction of the shelter
	 * @param gender Gender enum value of the gender restriction of the shelter
	 *
	 * @return list of searched shelters
	 */
	public List<Shelter> searchShelters(String name, Age age, Gender gender) {
	    // Change this when searchShelters() is functioning properly
	    return shelterManager.searchShelters(name, age, gender);
    }

	/**
	 * Checks user in to the shelter with the number of beds the user requested
	 * @param shelterId int value of unique shelter ID
	 * @param numBeds int value of number of beds the user requested
	 */
    public void checkIn(int shelterId, int numBeds) {
	    shelterManager.checkIn(shelterId, numBeds);
	    userManager.checkIn(shelterId, numBeds);
    }

	/**
	 * Checks user out of shelter
	 */
    public void checkout() {
        List<Integer> checkIn = userManager.getCurrentUser().getCheckIn();
	    shelterManager.checkOut(checkIn.get(0), checkIn.get(1));
	    userManager.checkOut();
    }
}
