package cs2340.shelterbuzz.model;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Created by tonyw and Sigma on 2/24/2018.
 * Facade. Currently contains a List of registered users.
 */

public class Model {
	private static final Model _instance = new Model();
	public static Model getInstance() { return _instance; }

	private List<User> accounts;
	private List<Shelter> shelters;

	private class ShelterPriority implements Comparable<ShelterPriority> {
		/* Class to sort shelters by priority during search */
		private Shelter shelter;
		private int priority;

		public ShelterPriority(Shelter shelter, int priority) {
			this.shelter = shelter;
			this.priority = priority;
		}

		@Override
		public int compareTo(ShelterPriority other) {
			// -1 because Android displays smallest priority element first
			return -1 * Integer.compare(this.priority, other.priority);
		}

		public void setShelter(Shelter shelter) {
			this.shelter = shelter;
		}

		public void setPriority(int priority) {
			this.priority = priority;
		}

		public Shelter getShelter() {
			return this.shelter;
		}

		public int getPriority() {
			return this.priority;
		}
	}

	private Model() {
		accounts = new ArrayList<>();
		shelters = new ArrayList<>();
	}

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
		for (User curr: accounts) {
			if (curr.getName().equals(u.getName())
			    && curr.getPass().equals(u.getPass())
			    && curr.getUsername().equals(u.getUsername())) {
				//returns false if duplicate user
				return false;
			}
		}
		accounts.add(u);
		return true;
	}

	/**
	 * Adds a shelter to the list
	 *
	 * @param name
	 * @param capacity
	 * @param restrictions
	 * @param longitude
	 * @param lattitude
	 * @param address
	 * @param specialNotes
	 * @param phoneNumber
	 */
	public void addShelter(String name, String capacity, String restrictions, double longitude,
	                       double lattitude, String address, String specialNotes, String phoneNumber) {
		Shelter shelter = new Shelter(name, capacity, restrictions,
		                              longitude, lattitude, address, specialNotes, phoneNumber);
		shelters.add(shelter);
	}

	/**
	 * Returns the complete list of shelters
	 */
	public List<Shelter> getShelters() {
		return this.shelters;
	}

	/**
	 * Gets a list of shelters meeting search criteria sorted by search name accuracy
	 *
	 * @param name String to find shelters with similar names
	 * @param age Enum
	 * @param gender Enum
	 * @return list of Shelters ordered by accuracy
	 */
	public List<Shelter> getShelters(String name, Age age, Gender gender) {
		PriorityQueue<ShelterPriority> searchedShelters = new PriorityQueue<>();

		// order priority queue by longest common subsequence length between
		// the search query and the shelter name
		if (name.equals("")) {
			// if no name field entered, just search by Age/Gender
			for (int simple = 0; simple < this.shelters.size(); simple++) {
				Shelter current = shelters.get(simple);
				if (containsAge(current.getRestrictions(), age)
				    &&
				    containsGender(current.getRestrictions(), gender)) {
					// add them all as same priority
					searchedShelters.add(new ShelterPriority(current, 1));
				}
			}
		} else {
			// name field is not trivial, so prioritize by name
			String[] nameSplit = name.split(" ");
			for (int i = 0; i < this.shelters.size(); i++) {
				Shelter current = shelters.get(i);

				// Prioritize results by accuracy per word, "best matching word"
				// number of characters matching b.t. search/shelter per word
				String[] shelterNameSplit = current.getName().split(" ");
				int priority = 0;
				for (int j = 0; j < nameSplit.length; j++) {
					int currPriority = 0;
					for (int k = 0; k < shelterNameSplit.length; k++) {
						// find the best matching word in a phrase
						// may overcount duplicates, but is accurate enough for n^2
						if (nameSplit[j].equals(shelterNameSplit[k])) {
							// if one word matches exactly, add a lot of priority
							currPriority += 100;
						} else {
							// find the most accurate word
							int temp = longestCommonSubsequenceLength(nameSplit[j], shelterNameSplit[k]);
							if (temp > currPriority) {
								currPriority = temp;
							}
						}
					}
					// sum all the greatest matches to find total priority
					// "total matching characters" per word
					priority += currPriority;
				}

				// name incorrect, check if gender and age also match
				// by default is UNSPECIFIED, so containsAge/Gender will be true
				if (priority >= 2 // at least x number of characters matching
				    &&
				    containsAge(current.getRestrictions(), age)
				    &&
				    containsGender(current.getRestrictions(), gender)) {
					searchedShelters.add(new ShelterPriority(current, priority));
				}
			}
		}

		// Convert PQ to generic List for consistency
		ArrayList<Shelter> shelters = new ArrayList<>();
		ShelterPriority[] PQShelters = searchedShelters.toArray(new ShelterPriority[0]);
		for (int i = 0; i < PQShelters.length; i++) {
			shelters.add(PQShelters[i].getShelter());
		}
		return shelters;
	}

	public Shelter getShelter(int i) {
		return shelters.get(i);
	}

	/**
	 * Checks if the String contains one of the strings in the Age enum value list
	 *
	 * @param restrictions String provided by Shelter object
	 * @param age Enum to get list of keyword phrases
	 * @return true if restrictions contains age keywords, false otherwise
	 */
	private boolean containsAge(String restrictions, Age age) {
		for (String phrase : age.getValue()) {
			// phrase is a way a restriction might be phrased
			if (restrictions.toLowerCase().contains(phrase.toLowerCase())) {
				return true; // if restriction contains matching phrase
			}
		}
		return false;
	}

	/**
	 * Checks if the String contains one of the strings in the Gender enum value list
	 *
	 * @param restrictions String provided by Shelter object
	 * @param gender Enum to get list of keyword phrases
	 * @return true if restrictions contains gender keywords, false otherwise
	 */
	private boolean containsGender(String restrictions, Gender gender) {
		for (String phrase : gender.getValue()) {
			// phrase is a way a restriction might be phrased
			if (restrictions.toLowerCase().contains(phrase.toLowerCase())) {
				return true; // if restriction contains matching phrase
			}
		}
		return false;
	}

	/**
	 * Finds the length of Longest Common Subsequence of two strings
	 * in O(nm) time given n, m are the lengths of the strings
	 *
	 * @param s1 String
	 * @param s2 String
	 * @return length of LCS
	 */
	private int longestCommonSubsequenceLength(String s1, String s2) {
		// DP solution for LCS, slightly modified from 3510 notes
		// since we start at x_0 rather than x_1
		if (s1.equals("") || s2.equals("")) {
			return 0;
		}

		int n = s1.length() - 1;
		int m = s2.length() - 1;

		// L = max length of LCS between s1_1 -> s1_n and s2_1 -> s2_m
		int[][] L = new int[n+1][m+1];
		for (int k = 0; k <= m; k++) {
			L[0][k] = 0;
		}

		for (int p = 1; p <= n; p++) {
			L[p][0] = 0;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					// charAt(i-1) and charAt(j-1) is the same as x_i and y_j
					L[i][j] = 1 + L[i-1][j-1];
				} else {
					L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
				}
			}
		}

		return L[n][m];
	}
}
