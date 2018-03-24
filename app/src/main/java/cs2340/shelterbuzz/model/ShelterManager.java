package cs2340.shelterbuzz.model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

public class ShelterManager {
    private static final ShelterManager instance = new ShelterManager();
    private static final String TAG = "ShelterManager";

    private Map<Integer, Shelter> shelters;
    private List<Shelter> sheltersList;
    private DatabaseReference database;

    private ShelterManager() {
        shelters = new HashMap<>();
        database = FirebaseDatabase.getInstance().getReference();

        ValueEventListener sheltersListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Shelter shelter = snapshot.getValue(Shelter.class);
                    shelters.put(shelter.getId(), shelter);
                }
                sheltersList = new ArrayList<>(shelters.values());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Log a message
                Log.d(TAG, "loadShelters:onCancelled", databaseError.toException());
            }
        };
        database.child("shelters").addValueEventListener(sheltersListener);
    }

    public static final ShelterManager getInstance() {
        return instance;
    }

    public List<Shelter> getAll() {
        return sheltersList;
    }

    public Shelter get(int id) {
        return shelters.get(id);
    }

    public void checkIn(int shelterId, final int numBeds) {
        final Shelter shelter = shelters.get(shelterId);
        if (numBeds <= shelter.getRemaining()) {
            Query query = database.child("shelters").orderByChild("id").equalTo(shelterId);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            DatabaseReference ref = snapshot.getRef();
                            ref.child("remaining").setValue(shelter.getRemaining() - numBeds);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d(TAG, databaseError.getMessage());
                }
            });
        } else {
            throw new IllegalArgumentException("number of beds exceeds remaining capacity of beds");
        }
    }

    public void checkOut(int shelterId, final int numBeds) {
        final Shelter shelter = shelters.get(shelterId);
        Query query = database.child("shelters").orderByChild("id").equalTo(shelterId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        DatabaseReference ref = snapshot.getRef();
                        ref.child("remaining").setValue(shelter.getRemaining() + numBeds);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
            }
        });
    }

    /**
	 * Gets a list of sheltersList meeting search criteria sorted by search name accuracy
	 *
	 * @param name String to find sheltersList with similar names
	 * @param age Enum
	 * @param gender Enum
	 * @return list of sheltersList ordered by accuracy
	 */
	public List<Shelter> searchShelters(String name, Age age, Gender gender) {
        PriorityQueue<ShelterPriority> searchedShelters = new PriorityQueue<>();

        // order priority queue by longest common subsequence length between
        // the search query and the shelter name
        if (name.equals("")) {
            // if no name field entered, just search by Age/Gender
            for (int simple = 0; simple < this.sheltersList.size(); simple++) {
                Shelter current = sheltersList.get(simple);
                if (containsAge(current.getRestrictionsString(), age)
                        &&
                        containsGender(current.getRestrictionsString(), gender)) {
                    // add them all as same priority
                    searchedShelters.add(new ShelterPriority(current, 1));
                }
            }
        } else {
            // name field is not trivial, so prioritize by name
            String[] nameSplit = name.split(" ");
            for (int i = 0; i < this.sheltersList.size(); i++) {
                Shelter current = sheltersList.get(i);

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
                        containsAge(current.getRestrictionsString(), age)
                        &&
                        containsGender(current.getRestrictionsString(), gender)) {
                    searchedShelters.add(new ShelterPriority(current, priority));
                }
            }
        }

        // Convert PQ to generic List for consistency
        ArrayList<Shelter> sheltersList = new ArrayList<>();
        ShelterPriority[] PQShelters = searchedShelters.toArray(new ShelterPriority[0]);
        for (int i = 0; i < PQShelters.length; i++) {
            sheltersList.add(PQShelters[i].getShelter());
        }
        return sheltersList;
	}


    /***
     * Dumb way to do search. We'll have to use this until searchShelters() is functioning
     * properly.
     *
     * @param name String to find sheltersList with similar names
     * @param age Enum
     * @param gender Enum
     * @return list of sheltersList that matches the search parameters
     */
	public List<Shelter> searchSheltersDumb(String name, Age age, Gender gender) {
        List<Shelter> searchedShelters = new ArrayList<>();

        if (name.equals("")) {
            // if no name field entered, just search by Age/Gender
            for (int i = 0; i < sheltersList.size(); i++) {
                Shelter current = sheltersList.get(i);
                if (containsAge(current.getRestrictionsString(), age)
                        &&
                        containsGender(current.getRestrictionsString(), gender)) {
                    searchedShelters.add(current);
                }
            }
        } else {
            for (int i = 0; i < sheltersList.size(); i++) {
                Shelter current = sheltersList.get(i);
                if (current.getName().toLowerCase().equals(name.toLowerCase())) {
                    searchedShelters.add(current);
                }
            }
        }

        return searchedShelters;
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
                // VERY quick, dumb fix to this
                if (phrase.toLowerCase().equals("men")
                        && restrictions.toLowerCase().contains("women")) {
                    return false;
                } else if (phrase.toLowerCase().equals("male")
                        && restrictions.toLowerCase().contains("female")) {
                    return false;
                }
                return true; // if restriction contains matching phrase
            }
        }
        return false;
    }

    /**
     * Helper class for filtering & searching sheltersList
     */
    private class ShelterPriority implements Comparable<ShelterPriority> {
        /* Class to sort sheltersList by priority during search */
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
}
