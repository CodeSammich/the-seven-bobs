package cs2340.shelterbuzz.model;
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
}
