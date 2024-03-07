package day_3;

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

public class ProductController {
    String token = CashWiseAuthorization.getToken();

    @Test
    public void createNewProduct(){
        String url = Config.getProperty("baseUrl")+"/api/myaccount/products";

        Faker faker = new Faker();
        String productTitle = faker.commerce().productName();
        double productPrice = faker.number().numberBetween(100,5600);
        int serviceTypeId = 2;
        int categoryId = 805;
        String description = productTitle + " company";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("product_title", productTitle);
        requestBody.put("product_price", productPrice);
        requestBody.put("service_type_id", serviceTypeId);
        requestBody.put("category_id", categoryId);
        requestBody.put("product_description", description);

        Response response = RestAssured.given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);
        System.out.println("Post request: "+response.statusCode());
        int productID = response.jsonPath().getInt("product_id");

        url = Config.getProperty("baseUrl")+"/api/myaccount/products/" + productID;

        response = RestAssured.given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .get(url);

        System.out.println("Get request: "+response.statusCode());
        Assert.assertEquals("Product title is not correct", response.jsonPath().getString("product_title"), productTitle);
        Assert.assertEquals("Product price is not correct",response.jsonPath().getDouble("product_price"), productPrice, 1);
        Assert.assertEquals("Product description is not correct",response.jsonPath().getString("product_description"), description);
    }
}
