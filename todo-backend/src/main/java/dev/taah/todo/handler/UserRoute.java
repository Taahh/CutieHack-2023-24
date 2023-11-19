package dev.taah.todo.handler;

import dev.taah.todo.TodoApp;
import dev.taah.todo.entity.User;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Taah
 * @since 6:31 PM [11-18-2023]
 */
@RestController
public class UserRoute {

    @PostMapping("/users/create")
    public void createUser(@RequestBody String basicUserData) {
        final JSONObject object = new JSONObject(basicUserData);
        final User user = new User(object.getString("uid"), object.getString("email"));
        TodoApp.dataManager().insertUser(user);
        TodoApp.dataManager().loadedUsers().put(user.uniqueIdentifier(), user);
        System.out.println("Inserted user: " + user.uniqueIdentifier() + " : " + user.email());
    }

    @PostMapping("/users/update")
    public void updateUser(@RequestBody String userData) {
        final JSONObject object = new JSONObject(userData);
        final String uid = object.getString("uid");
        if (TodoApp.dataManager().loadedUsers().containsKey(uid)) {
            final User user = TodoApp.dataManager().loadedUsers().get(uid);
            user.textNotifications(object.getBoolean("textNotifications"));
            user.emailNotifications(object.getBoolean("emailNotifications"));
            user.phoneNumber(object.getString("phoneNumber"));
        }
    }
}
