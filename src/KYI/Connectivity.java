package KYI;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connectivity {
    public Connection connection;
    public Connection getConnection() {
        String dbName = "kyi";
        String userName = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lendit?serverTimezone=Europe/Bratislava", userName, password);
        } catch (Exception e) {
            return null;
        }
        return connection;

    }
}