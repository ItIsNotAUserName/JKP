package day_4_querry_param;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pogo.CustomResponse;
import pogo.RequestBody;
import utilities.Config;

import static utilities.CashWiseAuthorization.getToken;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryController {
    static String id ="";
    Faker faker = new Faker();
    @Test
    public void createCategory(){
        String url = Config.getProperty("baseUrl")+"/api/myaccount/categories";
        RequestBody requestBody = new RequestBody();
        requestBody.setCategory_title(faker.commerce().department());
        requestBody.setCategory_description(faker.commerce().department()+" company");
        requestBody.setFlag(false);

        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);

        response.prettyPrint();
        id = response.jsonPath().getString("category_id");
    }

    @Test
    public void getCategory() throws JsonProcessingException {
        String url = Config.getProperty("baseUrl")+"/api/myaccount/categories/"+id;
        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .get(url);

        System.out.println("Status code: "+response.statusCode());
        response.prettyPrint();


        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);

        Assert.assertNotEquals(0, customResponse.getCategory_id());
        Assert.assertNotNull(customResponse.getCategory_title());
        Assert.assertNotNull(customResponse.getCategory_description());


    }
}
