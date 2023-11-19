package dev.taah.todo.entity;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Taah
 * @since 5:58 PM [11-18-2023]
 */
public class TodoItem {
    private final String ownerIdentifier;

    private final TodoType type;
    private final String description;
    private final LocalDateTime dueDate;

    public TodoItem(String ownerIdentifier, TodoType type, String description, LocalDateTime dueDate) {
        this.ownerIdentifier = ownerIdentifier;
        this.type = type;
        this.description = description;
        this.dueDate = dueDate;
    }

    public String ownerIdentifier() {
        return this.ownerIdentifier;
    }

    public TodoType type() {
        return this.type;
    }

    public String description() {
        return this.description;
    }

    public LocalDateTime dueDate() {
        return this.dueDate;
    }

    public enum TodoType {
        REMINDER, TASK
    }
}
