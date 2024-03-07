package utilities;

import lombok.SneakyThrows;

import java.sql.*;

public class DBUtilities {
    private static final String url = Config.getProperty("dbUrl");
    private static final String username = Config.getProperty("dbUser");
    private static final String password = Config.getProperty("dbPassword");

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void closeConnection(Connection connection){
        try{
            if (connection != null){
                connection.close();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


    @SneakyThrows
    public static ResultSet executeQuery(Connection connection, String query){
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeQuery();
    }

    @SneakyThrows
    public static int executeUpdate(Connection connection, String query){
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeUpdate();
    }
}
