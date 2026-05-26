public class Task {

    private String title;
    private boolean completed;
    private String priority;   // LOW / MEDIUM / HIGH
    private String dueDate;    // YYYY-MM-DD

    // Constructor
    public Task(String title, String priority, String dueDate) {
        this.title = title;
        this.priority = priority;
        this.dueDate = dueDate;
        this.completed = false;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getPriority() {
        return priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    // Display Task (VERY IMPORTANT for UI)
    @Override
    public String toString() {

        return "--------------------------------------------------\n"
                + "Task      : " + title + "\n"
                + "Status    : " + (completed ? "DONE" : "PENDING") + "\n"
                + "Priority  : " + priority + "\n"
                + "Due Date  : " + dueDate + "\n"
                + "--------------------------------------------------";
    }
}