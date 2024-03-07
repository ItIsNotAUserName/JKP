package steps;

import io.cucumber.java.en.*;
import utilities.DBUtilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductSteps {
    private static Connection connection = null;
    private static ResultSet resultSet;
    @Given("set up connection to database")
    public void set_up_connection_to_database() {
        connection = DBUtilities.getConnection();
    }
    @Given("retrieve all product prices")
    public void retrieve_all_product_prices() {
        String query = "SELECT id, price, title FROM products";
        resultSet = DBUtilities.executeQuery(connection,query);
    }
    @Then("verify prices are between {int} and {int}")
    public void verify_prices_are_between_and(Integer min, Integer max) throws SQLException {
        while(resultSet.next()){
            long price = resultSet.getLong("price");
            if(price < min || price > max){
                System.out.println("\t id: "+resultSet.getInt("id")+"\t title: `"+resultSet.getString("title")+"\t price: "+price);
            }
        }
    }
}
