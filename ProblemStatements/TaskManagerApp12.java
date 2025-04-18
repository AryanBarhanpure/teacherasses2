import java.time.LocalDate;
import java.util.*;

class Task {
    private String title;
    private String description;
    private LocalDate deadline;
    private int priority; // 1 - High, 2 - Medium, 3 - Low
    private boolean isCompleted;

    public Task(String title, String description, LocalDate deadline, int priority) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.isCompleted = false;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nDeadline: " + deadline +
                "\nPriority: " + (priority == 1 ? "High" : priority == 2 ? "Medium" : "Low") +
                "\nStatus: " + (isCompleted ? "Completed" : "Pending");
    }
}

class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(String title, String description, LocalDate deadline, int priority) {
        tasks.add(new Task(title, description, deadline, priority));
        System.out.println("✅ Task added.");
    }

    public void markTaskCompleted(String title) {
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title) && !task.isCompleted()) {
                task.markAsCompleted();
                System.out.println("✅ Task marked as completed.");
                return;
            }
        }
        System.out.println("❌ Task not found or already completed.");
    }

    public void showAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("❌ No tasks available.");
            return;
        }
        System.out.println("\n📋 All Tasks:");
        for (Task task : tasks) {
            System.out.println("\n" + task);
        }
    }

    public void showTasksByPriority(int priorityLevel) {
        System.out.println("\n📋 Tasks with " + (priorityLevel == 1 ? "High" : priorityLevel == 2 ? "Medium" : "Low") + " priority:");
        for (Task task : tasks) {
            if (task.getPriority() == priorityLevel) {
                System.out.println("\n" + task);
            }
        }
    }

    public void showPendingTasks() {
        System.out.println("\n📋 Pending Tasks:");
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                System.out.println("\n" + task);
            }
        }
    }
}

public class TaskManagerApp12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        // Adding sample tasks
        manager.addTask("Finish Project", "Complete the project by the end of this week", LocalDate.of(2025, 4, 25), 1);
        manager.addTask("Buy Groceries", "Purchase vegetables and fruits", LocalDate.of(2025, 4, 18), 2);
        manager.addTask("Read Book", "Complete reading the book 'Java Programming'", LocalDate.of(2025, 5, 5), 3);

        manager.showAllTasks();

        // Marking a task as completed
        System.out.print("\nEnter task title to mark as completed: ");
        String title = sc.nextLine();
        manager.markTaskCompleted(title);

        // Displaying tasks based on priority
        System.out.print("\nEnter priority to filter (1 - High, 2 - Medium, 3 - Low): ");
        int priority = sc.nextInt();
        manager.showTasksByPriority(priority);

        // Show pending tasks
        manager.showPendingTasks();
    }
}

