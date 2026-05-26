import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static AuthService authService = new AuthService();
    static TodoService todoService = new TodoService();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n=================================");
            System.out.println("         TO DO APP");
            System.out.println("=================================");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println("=================================");
            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {

                case 1:
                    signUp();
                    break;

                case 2:
                    login();
                    break;

                case 3:
                    System.out.println("Thank you for using To-Do App!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ================= SIGN UP =================
    public static void signUp() {

        System.out.println("\n----- SIGN UP -----");

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        boolean ok = authService.signUp(name, email, password);

        if (ok) {
            System.out.println("Signup successful!");
        } else {
            System.out.println("User already exists!");
        }
    }

    // ================= LOGIN =================
    public static void login() {

        System.out.println("\n----- LOGIN -----");

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        boolean ok = authService.login(email, password);

        if (ok) {
            System.out.println("Login successful!");
            taskMenu(email);
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    // ================= TASK MENU =================
    public static void taskMenu(String email) {

        while (true) {

            System.out.println("\n=================================");
            System.out.println("         TASK MENU");
            System.out.println("=================================");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Search Task");
            System.out.println("4. Mark Task Done");
            System.out.println("5. Delete Task");
            System.out.println("6. Logout");
            System.out.println("=================================");
            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {

                case 1:
                    addTask(email);
                    break;

                case 2:
                    todoService.viewTasks(email);
                    break;

                case 3:
                    searchTask(email);
                    break;

                case 4:
                    markDone(email);
                    break;

                case 5:
                    deleteTask(email);
                    break;

                case 6:
                    System.out.println("Logged out successfully!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ================= ADD TASK =================
    public static void addTask(String email) {

        System.out.print("Enter Task Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Priority (LOW/MEDIUM/HIGH): ");
        String priority = sc.nextLine();

        System.out.print("Enter Due Date (YYYY-MM-DD): ");
        String dueDate = sc.nextLine();

        todoService.addTask(email, title, priority, dueDate);

        System.out.println("Task added successfully!");
    }

    // ================= SEARCH TASK =================
    public static void searchTask(String email) {

        System.out.print("Enter keyword: ");
        String keyword = sc.nextLine();

        todoService.searchTask(email, keyword);
    }

    // ================= MARK DONE =================
    public static void markDone(String email) {

        System.out.print("Enter task number: ");
        int index = readInt();

        todoService.markTaskDone(email, index - 1);
    }

    // ================= DELETE TASK =================
    public static void deleteTask(String email) {

        System.out.print("Enter task number: ");
        int index = readInt();

        todoService.deleteTask(email, index - 1);
    }

    // ================= SAFE INPUT =================
    public static int readInt() {

        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.print("Enter valid number: ");
            }
        }
    }
}
