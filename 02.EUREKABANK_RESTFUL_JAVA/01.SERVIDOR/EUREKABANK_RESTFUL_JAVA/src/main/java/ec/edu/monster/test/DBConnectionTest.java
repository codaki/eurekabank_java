
package ec.edu.monster.test;

import static ec.edu.monster.DBB.DBConnection.closeConnection;
import static ec.edu.monster.DBB.DBConnection.getConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionTest {
      public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            closeConnection(conn);
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }    
}
