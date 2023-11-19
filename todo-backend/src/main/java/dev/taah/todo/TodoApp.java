package dev.taah.todo;

import dev.taah.todo.data.DataManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Taah
 * @since 5:26 PM [11-18-2023]
 */

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TodoApp {

    private static final DataManager dataManager = new DataManager();
    public static void main(String[] args) {
        dataManager.createTables();

        dataManager.loadUsers();
        dataManager.loadItems();
        SpringApplication.run(TodoApp.class, args);
    }

    public static DataManager dataManager() {
        return dataManager;
    }
}
