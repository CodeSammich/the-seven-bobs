package cs2340.shelterbuzz.model;
import java.util.ArrayList;
import java.util.List;

import android.util.Pair;

/**
 * Created by tonyw on 2/24/2018.
 * Facade
 */

public class Model {
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    private List<Pair<String, String>> accounts;

    private Model() {
        accounts = new ArrayList<>();
    }

    public List<Pair<String, String>> getAccounts() {
        return accounts;
    }

    public boolean addUser(Pair<String, String> p) {
        accounts.add(p);
    }
}
