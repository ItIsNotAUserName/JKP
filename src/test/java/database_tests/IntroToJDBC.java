package database_tests;

import org.junit.Assert;

import java.sql.*;

public class IntroToJDBC {
    public static void main(String[] args) {
        //Cashwise database
        //host: 18.159.52.24
        //port: 5434
        //database: postgres
        //username: cashwiseuser
        //password: cashwisepass

        //jdbc:postgresql://18.159.52.24:5434/postgres

        String url = "jdbc:postgresql://18.159.52.24:5434/postgres";
        String username = "cashwiseuser";
        String password = "cashwisepass";

        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{

        connection = DriverManager.getConnection(url, username, password);

        statement = connection.createStatement();

        String sqlQuery = "SELECT count(*) FROM clients WHERE name = ?";
            preparedStatement = connection.prepareStatement(sqlQuery);


        resultSet = statement.executeQuery(sqlQuery);

        while(resultSet.next()){
            //System.out.println(resultSet.getString("address"));
            //System.out.println(resultSet.getString((1)));

            int actualRecords = resultSet.getInt(1);

            //String clientName = resultSet.getString("client_name");
            //Assert.assertNotNull(id + " is missing client name",clientName);

            System.out.println(actualRecords);
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
