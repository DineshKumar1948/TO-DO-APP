import java.util.ArrayList;
import java.util.HashMap;

public class TodoService {

    // Store tasks per user (email → task list)
    private static HashMap<String, ArrayList<Task>> userTasks = new HashMap<>();

    // ================= ADD TASK =================
    public void addTask(String email, String title, String priority, String dueDate) {

        userTasks.putIfAbsent(email, new ArrayList<>());

        Task task = new Task(title, priority, dueDate);

        userTasks.get(email).add(task);

        // Save to file
        FileManager.saveTask(email, task);
    }

    // ================= VIEW TASKS =================
    public void viewTasks(String email) {

        ArrayList<Task> tasks = userTasks.get(email);

        if (tasks == null || tasks.isEmpty()) {
            System.out.println("\nNo tasks found!");
            return;
        }

        System.out.println("\n================ YOUR TASKS ================");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\nTask No: " + (i + 1));
            System.out.println(tasks.get(i));
        }
    }

    // ================= MARK TASK DONE =================
    public void markTaskDone(String email, int index) {

        ArrayList<Task> tasks = userTasks.get(email);

        if (tasks == null || index < 0 || index >= tasks.size()) {
            System.out.println("Invalid task number!");
            return;
        }

        tasks.get(index).setCompleted(true);

        System.out.println("Task marked as DONE!");
    }

    // ================= DELETE TASK =================
    public void deleteTask(String email, int index) {

        ArrayList<Task> tasks = userTasks.get(email);

        if (tasks == null || index < 0 || index >= tasks.size()) {
            System.out.println("Invalid task number!");
            return;
        }

        tasks.remove(index);

        System.out.println("Task deleted successfully!");
    }

    // ================= SEARCH TASK =================
    public void searchTask(String email, String keyword) {

        ArrayList<Task> tasks = userTasks.get(email);

        if (tasks == null || tasks.isEmpty()) {
            System.out.println("No tasks found!");
            return;
        }

        System.out.println("\n=========== SEARCH RESULTS ===========");

        boolean found = false;

        for (Task t : tasks) {

            if (t.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching tasks found!");
        }
    }

    // ================= GET TASK COUNT (BONUS FEATURE) =================
    public void showTaskStats(String email) {

        ArrayList<Task> tasks = userTasks.get(email);

        if (tasks == null) {
            System.out.println("No tasks available!");
            return;
        }

        int total = tasks.size();
        int done = 0;

        for (Task t : tasks) {
            if (t.isCompleted()) {
                done++;
            }
        }

        System.out.println("\n===== TASK STATISTICS =====");
        System.out.println("Total Tasks   : " + total);
        System.out.println("Completed     : " + done);
        System.out.println("Pending       : " + (total - done));
    }

    // ================= LOAD TASKS (FOR FUTURE USE) =================
    public void loadTasks(HashMap<String, ArrayList<Task>> loadedTasks) {
        userTasks.clear();
        userTasks.putAll(loadedTasks);
    }
}
