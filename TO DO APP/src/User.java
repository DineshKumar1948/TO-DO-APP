public class User {

    private String name;
    private String email;
    private String encryptedPassword;

    // Constructor
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;

        // store encrypted password
        this.encryptedPassword = SecurityUtil.encrypt(password);
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    // PASSWORD CHECK METHOD (IMPORTANT)
    public boolean checkPassword(String inputPassword) {
        return encryptedPassword.equals(SecurityUtil.encrypt(inputPassword));
    }

    // Display user info (safe - no password shown)
    @Override
    public String toString() {
        return "Name: " + name + " | Email: " + email;
    }
}
