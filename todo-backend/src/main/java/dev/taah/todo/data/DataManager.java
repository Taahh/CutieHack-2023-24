package dev.taah.todo.data;

import com.google.common.collect.Maps;
import dev.taah.todo.entity.TodoItem;
import dev.taah.todo.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.UUID;

/**
 * @author Taah
 * @since 5:59 PM [11-18-2023]
 */
public class DataManager {
    private final Map<String, User> loadedUsers = Maps.newHashMap();
    private final DatabaseHandler databaseHandler;

    public DataManager() {
        this.databaseHandler = new DatabaseHandler();
    }

    public void createTables() {
        try (final Connection connection = this.databaseHandler.connection()) {
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users (`uniqueIdentifier` VARCHAR(128), `email` VARCHAR(320), `textNotifications` BOOLEAN, `emailNotifications` BOOLEAN, `phoneNumber` VARCHAR(10), PRIMARY KEY(`uniqueIdentifier`));");
            System.out.println("Created users? " + statement.execute());
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS items (`ownerIdentifier` VARCHAR(36), `type` VARCHAR(128), `description` TEXT, `dueDate` INTEGER);");
            System.out.println("Created items? " + statement.execute());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadUsers() {
        try (final Connection connection = this.databaseHandler.connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            final ResultSet set = statement.executeQuery();
            loadedUsers.clear();
            while (set.next()) {
                final String uid = set.getString("uniqueIdentifier");
                final String email = set.getString("email");
                final boolean textNotifs = set.getBoolean("textNotifications");
                final boolean emailNotifs = set.getBoolean("emailNotifications");
                final String phoneNumber = set.getString("phoneNumber");

                final User user = new User(uid, email);
                user.emailNotifications(emailNotifs);
                user.textNotifications(textNotifs);
                user.phoneNumber(phoneNumber);
                loadedUsers.put(uid, user);
            }
            System.out.println("Loaded " + loadedUsers.size() + " users!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadItems() {
        try (final Connection connection = this.databaseHandler.connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM items");
            final ResultSet set = statement.executeQuery();
            while (set.next()) {
                final String uid = set.getString("ownerIdentifier");
                final TodoItem.TodoType type = TodoItem.TodoType.valueOf(set.getString("type").toUpperCase());
                final String description = set.getString("description");
                final LocalDateTime dueDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(set.getLong("dueDate")), ZoneId.systemDefault());
                final TodoItem todoItem = new TodoItem(uid, type, description, dueDate);
                if (loadedUsers.containsKey(uid)) {
                    loadedUsers.get(uid).items().add(todoItem);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertUser(User user) {
        try (final Connection connection = this.databaseHandler.connection()) {
            final PreparedStatement statement = connection.prepareStatement("INSERT INTO users(`uniqueIdentifier`, `email`, `textNotifications`, `emailNotifications`, `phoneNumber`) VALUES(?, ?, ?, ?, ?)");
            statement.setString(1, user.uniqueIdentifier());
            statement.setString(2, user.email());
            statement.setBoolean(3, user.textNotifications());
            statement.setBoolean(4, user.emailNotifications());
            statement.setString(5, user.phoneNumber());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void insertTodo(TodoItem item) {
        try (final Connection connection = this.databaseHandler.connection()) {
            final PreparedStatement statement = connection.prepareStatement("INSERT INTO items (`ownerIdentifier`, `type`, `description`, `dueDate`) VALUES(?, ?, ?, ?)");
            statement.setString(1, item.ownerIdentifier());
            statement.setString(2, item.type().name());
            statement.setString(3, item.description());
            final ZoneOffset offset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
            statement.setLong(4, item.dueDate().toInstant(offset).toEpochMilli());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(User user) {
        try (final Connection connection = this.databaseHandler.connection()) {
            final PreparedStatement statement = connection.prepareStatement("UPDATE users SET `textNotifications`=?, `emailNotifications`=?, `phoneNumber`=? WHERE `uniqueIdentifier`=?");
            statement.setBoolean(1, user.textNotifications());
            statement.setBoolean(2, user.emailNotifications());
            statement.setString(3, user.phoneNumber());
            statement.setString(4, user.uniqueIdentifier());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, User> loadedUsers() {
        return this.loadedUsers;
    }
}
