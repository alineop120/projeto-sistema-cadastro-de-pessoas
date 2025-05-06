package util;

import java.sql.*;

/**
 *
 * @author 364975
 */
public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/cadastrosbd";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC não encontrado!", e);
        }
    }
}
