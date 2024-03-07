package database_tests;

import lombok.SneakyThrows;
import utilities.DBUtilities;

import java.sql.*;

public class PrepareStatementPractice {


    public static void main(String[] args) {
        String companyName = "Google";
        printClients(companyName);
    }
    @SneakyThrows
    public static void printClients(String companyName){
        Connection connection = DBUtilities.getConnection();

        String query = "SELECT client_name FROM clients WHERE company_name = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, companyName);

        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            System.out.println(resultSet.getString(1));
        }
    }

}
