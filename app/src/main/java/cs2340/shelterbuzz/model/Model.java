package cs2340.shelterbuzz.model;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonyw on 2/24/2018.
 * Facade
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
        accounts.add(u);
    }
}
