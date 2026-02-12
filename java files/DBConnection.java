
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:student.db");
            System.out.println("Connected to Database");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
