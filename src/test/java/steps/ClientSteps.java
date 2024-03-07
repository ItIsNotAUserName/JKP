package steps;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import org.junit.Assert;
import pogo.CustomResponse;
import pogo.RequestBody;
import utilities.APIRunner;

import java.sql.*;

public class ClientSteps {
    Faker faker = new Faker();


    @Given("new client is created")
    public void new_client_is_created() {

        String path = "/api/myaccount/clients";

        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name("Company name");
        requestBody.setClient_name("Client name");
        requestBody.setEmail(faker.internet().emailAddress());
        requestBody.setPhone_number("+19321239212");
        requestBody.setAddress("123 w weddy st");
        requestBody.setTags_id(new int[]{9});

        APIRunner.runPOST(path,requestBody);

    }
    @Then("verify client exists in database")
    public void verify_client_exists_in_database() throws SQLException {
        String sqlQuery = "select client_id, company_name, client_name, " +
                " email, phone_number, address FROM clients WHERE client_id = "+APIRunner.getCustomResponse().getClient_id();

        String url = "jdbc:postgresql://18.159.52.24:5434/postgres";
        String username = "cashwiseuser";
        String password = "cashwisepass";

        Connection connection = DriverManager.getConnection(url, username, password);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        String actName = "";
        while(resultSet.next()){
            actName = resultSet.getString("client_name");
        }
        Assert.assertEquals(APIRunner.getCustomResponse().getClient_name(), actName);
    }
    @Then("delete client in database")
    public void delete_client_in_database() throws SQLException {
        String sqlQuery1 = "DELETE FROM client_tag WHERE client_id = "+APIRunner.getCustomResponse().getClient_id();
        String sqlQuery2 = "DELETE FROM clients WHERE client_id = "+APIRunner.getCustomResponse().getClient_id();

        String url = "jdbc:postgresql://18.159.52.24:5434/postgres";
        String username = "cashwiseuser";
        String password = "cashwisepass";

        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();

        statement.executeUpdate(sqlQuery1);
        statement.executeUpdate(sqlQuery2);
    }

    @Then("verify client does not exist using API")
    public void verify_client_does_not_exist_using_api() {
        String path = "/api/myaccount/clients/"+APIRunner.getCustomResponse().getClient_id();
        APIRunner.runGET(path);
        Assert.assertEquals(APIRunner.getStatus_code(), 500);
    }
}
