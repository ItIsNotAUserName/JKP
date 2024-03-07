package day_4_querry_param;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashWiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

import static utilities.CashWiseAuthorization.getToken;

public class SellerController {

    String token = getToken();
    String id = "";
    Faker faker = new Faker();

    @Test
    public void createNewSeller(){
        String url = Config.getProperty("baseUrl")+"/api/myaccount/sellers";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("company_name", faker.company().name());
        requestBody.put("seller_name", faker.name().fullName());
        requestBody.put("email", faker.internet().emailAddress());
        requestBody.put("phone_number", faker.phoneNumber().phoneNumber());
        requestBody.put("address", faker.address().fullAddress());


        Response response = RestAssured.given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);

        Assert.assertEquals("Status code is not correct", 201, response.statusCode());
        response.prettyPrint();
        id = response.jsonPath().getString("seller_id");
    }

    @Test
    public void getAllSellers(){
        String url = Config.getProperty("baseUrl")+"/api/myaccount/sellers";
        Map<String, Object> param = new HashMap<>();
        param.put("isArchived", false);
        param.put("page", 1);
        param.put("size", 10);

        Response response = RestAssured.given()
                .auth().oauth2(token)
                .params(param)
                .get(url);

        response.prettyPrint();

    }

    @Test
    public void temporary(){
        String token = CashWiseAuthorization.getToken();
        System.out.println(token);
    }
}
