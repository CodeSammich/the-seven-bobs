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

    public User getCurrentUser() {
        return userManager.getCurrentUser();
    }

    public void setCurrentUser(String userId) {
        userManager.setCurrentUser(userManager.get(userId));
    }

	/**
	 * Returns the complete list of shelters
	 */
	public List<Shelter> getAllShelters() {
		return shelterManager.getAll();
	}

	public Shelter getShelter(int i) {
		return shelterManager.get(i);
	}

	public List<Shelter> searchShelters(String name, Age age, Gender gender) {
	    // Change this when searchShelters() is functioning properly
	    return shelterManager.searchSheltersDumb(name, age, gender);
    }

    public void checkIn(int shelterId, int numBeds) {
	    shelterManager.checkIn(shelterId, numBeds);
	    userManager.checkIn(shelterId, numBeds);
    }

    public void checkout() {
        List<Integer> checkIn = userManager.getCurrentUser().getCheckIn();
	    shelterManager.checkOut(checkIn.get(0), checkIn.get(1));
	    userManager.checkOut();
    }
}
