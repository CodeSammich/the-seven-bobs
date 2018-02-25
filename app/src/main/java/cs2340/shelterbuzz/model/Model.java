package cs2340.shelterbuzz.model;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonyw on 2/24/2018.
 * Facade. Currently contains a List of registered users.
 */

public class Model {
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    private List<User> accounts;
    private List<Shelter> shelters;

    private Model() {
        accounts = new ArrayList<>();
    }

    public List<User> getAccounts() {
        return accounts;
    }

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

    public void readCSV() {
        final String DELIMITER = ",";
        BufferedReader bf = null;
        try {
            String line = "";
            bf = new BufferedReader(new FileReader("../../res/raw/Homeless_Shelter_Database.csv"));
            bf.readLine();
            while((line = bf.readLine()) != null) {
                String[] s = line.split(DELIMITER);
                int capacity = Integer.parseInt(s[2]);
                double longitude = Double.parseDouble(s[4]);
                double latitude = Double.parseDouble(s[5]);
                Shelter shelter = new Shelter(s[1], capacity, s[3], longitude, latitude,s[6], s[7], s[8]);
                shelters.add(shelter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Shelter> getShelters() {
        return shelters;
    }
}
