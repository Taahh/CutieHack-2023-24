package dev.taah.todo.data;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Taah
 * @since 6:02 PM [11-18-2023]
 */
public class DatabaseHandler {

    private final File file;

    public DatabaseHandler() {
        final File folder = new File("data" + File.separatorChar);
        if (!folder.exists()) folder.mkdir();

        this.file = new File(folder, "data.db");
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + this.file.getAbsolutePath());
    }

}
