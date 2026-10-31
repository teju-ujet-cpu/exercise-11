
import java.sql.*;

public class InsertData {

    // Database connection details
    static final String JDBC_URL =
        "jdbc:mysql://localhost:3306/mydatabase";

    static final String USERNAME = "root";
    static final String PASSWORD = "password";

    public static void main(String[] args) {

        try (
            Connection con = DriverManager.getConnection(
                JDBC_URL, USERNAME, PASSWORD
            )
        ) {

            // Create users table if it does not exist
            String createTable =
                "CREATE TABLE IF NOT EXISTS users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "email VARCHAR(100))";

            try (PreparedStatement ps1 =
                     con.prepareStatement(createTable)) {

                ps1.executeUpdate();
            }

            // Insert user records
            String insertQuery =
                "INSERT INTO users (name, email) VALUES (?, ?)";

            try (PreparedStatement ps2 =
                     con.prepareStatement(insertQuery)) {

                // Insert first record
                ps2.setString(1, "John Doe");
                ps2.setString(2, "john@example.com");
                ps2.executeUpdate();

                // Insert second record
                ps2.setString(1, "Jane Doe");
                ps2.setString(2, "jane@example.com");
                ps2.executeUpdate();
            }

            System.out.println("Data inserted successfully.");

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }
    }
}
