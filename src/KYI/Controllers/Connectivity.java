package KYI.Controllers;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connectivity {
    public Connection connection;
    public Connection getConnection() {
        String dbName = "knowyourincome";
        String userName = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/knowyourincome?serverTimezone=" +
                            "Europe/Bratislava",
                    userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;

    }
}