package dev.taah.todo.handler;

import dev.taah.todo.TodoApp;
import dev.taah.todo.entity.TodoItem;
import dev.taah.todo.entity.User;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author Taah
 * @since 6:31 PM [11-18-2023]
 */
@RestController
public class TodoRoute {

    @PostMapping("/todos/create")
    public void createTodo(@RequestBody String todoDate) {
        final JSONObject object = new JSONObject(todoDate);
        final String uid = object.getString("uid");
        final String description = object.getString("description");
        final LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochMilli(object.getLong("date")), ZoneId.systemDefault());
        final TodoItem todoItem = new TodoItem(uid, TodoItem.TodoType.REMINDER, description, time);
        if (TodoApp.dataManager().loadedUsers().containsKey(todoItem.ownerIdentifier())) {
            TodoApp.dataManager().loadedUsers().get(todoItem.ownerIdentifier()).items().add(todoItem);
            TodoApp.dataManager().insertTodo(todoItem);
        }
    }
}
