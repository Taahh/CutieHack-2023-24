package dev.taah.todo.handler;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import dev.taah.todo.TodoApp;
import dev.taah.todo.entity.TodoItem;
import dev.taah.todo.entity.User;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
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

    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(TypeToken.get(LocalDateTime.class).getType(), (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) -> new JsonPrimitive(src.toInstant(ZoneId.systemDefault().getRules().getOffset(Instant.now())).toEpochMilli())).setPrettyPrinting().create();

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

    @GetMapping("/todos/list/{uid}")
    public String getTodos(@PathVariable String uid) {
        System.out.println("You tried ig");
        if (!TodoApp.dataManager().loadedUsers().containsKey(uid)) {
            return "";
        }
        return GSON.toJson(TodoApp.dataManager().loadedUsers().get(uid).items());
    }
}
