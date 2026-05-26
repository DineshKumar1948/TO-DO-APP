import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FileManager {

    private static final String USER_FILE = "users.txt";
    private static final String TASK_FILE = "tasks.txt";

    // ================= SAVE USER =================
    public static void saveUser(User user) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE, true))) {

            bw.write(user.getName() + ","
                    + user.getEmail() + ","
                    + user.getEncryptedPassword());

            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error saving user data.");
        }
    }

    // ================= SAVE TASK =================
    public static void saveTask(String email, Task task) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TASK_FILE, true))) {

            bw.write(email + ","
                    + task.getTitle() + ","
                    + task.getPriority() + ","
                    + task.getDueDate() + ","
                    + task.isCompleted());

            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error saving task data.");
        }
    }

    // ================= LOAD USERS =================
    public static ArrayList<User> loadUsers() {

        ArrayList<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length == 3) {

                    User user = new User(data[0], data[1], "temp");
                    user.setEncryptedPassword(data[2]); // restore encrypted password

                    users.add(user);
                }
            }

        } catch (IOException e) {
            System.out.println("No user file found or error loading users.");
        }

        return users;
    }

    // ================= LOAD TASKS =================
    public static HashMap<String, ArrayList<Task>> loadTasks() {

        HashMap<String, ArrayList<Task>> map = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(TASK_FILE))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length == 5) {

                    String email = data[0];
                    String title = data[1];
                    String priority = data[2];
                    String dueDate = data[3];
                    boolean completed = Boolean.parseBoolean(data[4]);

                    Task task = new Task(title, priority, dueDate);
                    task.setCompleted(completed);

                    map.putIfAbsent(email, new ArrayList<>());
                    map.get(email).add(task);
                }
            }

        } catch (IOException e) {
            System.out.println("No task file found or error loading tasks.");
        }

        return map;
    }
}
