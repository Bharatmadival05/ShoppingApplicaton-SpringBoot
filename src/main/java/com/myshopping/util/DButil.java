package com.myshopping.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return connection;
        } else {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://root:XWOsxYVDYsrlPhgoeZJilGUqsEHdAGvE@junction.proxy.rlwy.net:46793/railway";
            String user = "root";
            String password = "XWOsxYVDYsrlPhgoeZJilGUqsEHdAGvE";

            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                return connection;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new SQLException("Failed to load MySQL JDBC driver");
            }
        }
    }

    // Close the connection
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle or log the exception
        }
    }
}
