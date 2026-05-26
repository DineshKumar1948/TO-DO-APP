import java.util.Base64;

public class SecurityUtil {

    // ================= ENCRYPT PASSWORD =================
    public static String encrypt(String password) {

        if (password == null) return null;

        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    // ================= DECRYPT PASSWORD =================
    public static String decrypt(String encryptedPassword) {

        if (encryptedPassword == null) return null;

        return new String(Base64.getDecoder().decode(encryptedPassword));
    }
}
