package ToDoList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DailyTasks {
    private String name;
    private int priority;
    private LocalDate dateOfCreation;
    private String status;
    private LocalDateTime dateOfCompletion;

    public DailyTasks(String name, int priority, String status) {
        this.name = name;
        this.priority = priority;
        this.dateOfCreation = LocalDate.now();
        this.status = status;
        this.dateOfCompletion = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(LocalDateTime dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    @Override
    public String toString() {
        String completionStr;
        if (dateOfCompletion == null) {
            completionStr = "Отсутствует";
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            completionStr = dateOfCompletion.format(formatter);
        }
        return name + " | Приоритет: " + priority
                + " | Статус: " + status
                + " | Дата создания: " + dateOfCreation
                + " | Время выполнения: " + completionStr;
    }
}