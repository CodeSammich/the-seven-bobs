package cs2340.shelterbuzz.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonyw and Sigma on 2/24/2018.
 * Facade. Currently contains a List of registered users.
 */
public class Model {
	private static final Model _instance = new Model();

	private ShelterManager shelterManager = ShelterManager.getInstance();
	private UserManager userManager = UserManager.getInstance();
	private List<User> accounts;

	private Model() {
		accounts = new ArrayList<>();
	}

	public static Model getInstance() { return _instance; }

	/**
	 * Returns list of user accounts
	 *
	 * @return List of user accounts
	 */
	public List<User> getAccounts() {
		return accounts;
	}

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

	public void addShelter(Shelter shelter) {
		shelterManager.add(shelter);
	}

	/**
	 * Returns the complete list of shelters
	 */
	public List<Shelter> getShelters() {
		return shelterManager.getAll();
	}

	public Shelter getShelter(int i) {
		return shelterManager.get(i);
	}
}
