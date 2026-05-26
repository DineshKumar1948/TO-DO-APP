import java.util.ArrayList;

public class AuthService {

    // In-memory user storage
    private static ArrayList<User> users = new ArrayList<>();

    // ================= SIGN UP =================
    public boolean signUp(String name, String email, String password) {

        // Check duplicate user
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return false; // user already exists
            }
        }

        // Create new user
        User newUser = new User(name, email, password);

        // Add to memory
        users.add(newUser);

        // Save to file
        FileManager.saveUser(newUser);

        return true;
    }

    // ================= LOGIN =================
    public boolean login(String email, String password) {

        for (User u : users) {

            if (u.getEmail().equalsIgnoreCase(email)
                    && u.checkPassword(password)) {
                return true;
            }
        }

        return false;
    }

    // ================= GET USER =================
    public User getUser(String email) {

        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }

        return null;
    }

    // ================= LOAD USERS (FOR FUTURE USE) =================
    public void loadUsers(ArrayList<User> loadedUsers) {
        users.clear();
        users.addAll(loadedUsers);
    }
}
