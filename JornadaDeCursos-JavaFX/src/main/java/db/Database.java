package db;

import util.Constants;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static Connection connection;

    static {
        try {
            Class.forName("org.sqlite.JDBC");

            Database.initializeDatabase();
            Database.initializeTables();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Database() {
        // Avoid class instantiation
    }

    private static void initializeDatabase() throws SQLException {
        Database.connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", Paths.get(Constants.PERSISTENT_DIRECTORY, "sqlite.db").toString()));
    }

    private static void initializeTables() throws SQLException {
        Statement statement = Database.connection.createStatement();
        // TODO: CREATE TABLE IF NOT EXISTS
    }

    public static Connection getConnection() throws SQLException {
        if (Database.connection.isClosed())
            Database.initializeDatabase();

        return Database.connection;
    }
}
