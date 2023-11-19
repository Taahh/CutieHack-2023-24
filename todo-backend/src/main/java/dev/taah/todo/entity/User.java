package dev.taah.todo.entity;

import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.UUID;

/**
 * @author Taah
 * @since 5:57 PM [11-18-2023]
 */
public class User {
    private final String uniqueIdentifier;
    private final String email;
    private final LinkedList<TodoItem> items = Lists.newLinkedList();
    private boolean textNotifications, emailNotifications;
    private String phoneNumber;

    public User(String uniqueIdentifier, String email) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.email = email;
    }

    public String uniqueIdentifier() {
        return this.uniqueIdentifier;
    }

    public String email() {
        return this.email;
    }

    public LinkedList<TodoItem> items() {
        return this.items;
    }

    public boolean textNotifications() {
        return this.textNotifications;
    }

    public void textNotifications(boolean textNotifications) {
        this.textNotifications = textNotifications;
    }

    public boolean emailNotifications() {
        return this.emailNotifications;
    }

    public void emailNotifications(boolean emailNotifications) {
        this.emailNotifications = emailNotifications;
    }

    public String phoneNumber() {
        return this.phoneNumber;
    }

    public void phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
