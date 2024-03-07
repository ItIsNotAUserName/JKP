package day_5_recap_pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.cucumber.java.it.Ma;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pogo.CustomResponse;
import pogo.RequestBody;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

import static utilities.CashWiseAuthorization.getToken;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SellersPractice {
    Faker faker = new Faker();
    static String url;
    static int id;
    @Test
    public void test_1_createSeller() throws JsonProcessingException {
        url = Config.getProperty("baseUrl")+"/api/myaccount/sellers";
        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name(faker.company().name());
        requestBody.setSeller_name(faker.name().fullName());
        requestBody.setEmail(faker.internet().emailAddress());
        requestBody.setAddress(faker.address().fullAddress());

        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);

        response(response);
        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        id = customResponse.getSeller_id();
    }
    @Test
    public void test_2_getSeller(){
        url += "/"+id;

        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .get(url);
        response(response);
    }
    @Test
    public void test_3_updateSeller(){
        RequestBody requestBody = new RequestBody();

        requestBody.setCompany_name(faker.company().name());
        requestBody.setSeller_name(faker.name().fullName());
        requestBody.setEmail(faker.internet().emailAddress());
        requestBody.setAddress(faker.address().fullAddress());

        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put(url);
        response(response);
    }

    @Test
    public void test_4_deleteSeller(){
        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .delete(url);
        response(response);
    }

    @Test
    public void getAllSellers() throws JsonProcessingException {
        url = Config.getProperty("baseUrl")+"/api/myaccount/sellers/all";

        Map<String, Object> params = new HashMap<>();

        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 10);

        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .params(params)
                .get(url);
        ObjectMapper mapper = new ObjectMapper();
        CustomResponse[] customResponse = mapper.readValue(response.asString(), CustomResponse[].class);

        System.out.println("Sellers: "+customResponse.length);
        for(CustomResponse cr : customResponse){
            System.out.println("ID: "+cr.getSeller_id());
            System.out.println("Name: "+cr.getCompany_name());
            System.out.println("==========================================");
        }
    }
    public void response(Response response){
        System.out.println(response.statusCode());
        response.prettyPrint();
    }
}
