package day_5_recap_pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pogo.CustomResponse;
import pogo.RequestBody;
import utilities.Config;

import static utilities.CashWiseAuthorization.getToken;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankAccountPractice {
    Faker faker = new Faker();
    static String url;
    static int id;

    @Test
    public void test_1_createBankAccount() throws JsonProcessingException {
        url = Config.getProperty("baseUrl")+"/api/myaccount/bankaccount";

        RequestBody requestBody = new RequestBody();

        requestBody.setType_of_pay("CASH");
        requestBody.setBank_account_name(faker.company().name() + " bank account");
        requestBody.setDescription(faker.commerce().department() + " company");
        requestBody.setBalance(faker.number().numberBetween(15, 9000));


        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);
        response(response);

        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        id = customResponse.getId();
    }

    @Test
    public void test_2_getBankAccount(){
        url = Config.getProperty("baseUrl")+"/api/myaccount/bankaccount/"+id;

        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .get(url);
        response(response);
    }

    @Test
    public void test_3_updateBankAccount(){
        RequestBody requestBody = new RequestBody();

        requestBody.setType_of_pay("CASH");
        requestBody.setBank_account_name(faker.company().name() + " bank account");
        requestBody.setDescription(faker.commerce().department() + " company");
        requestBody.setBalance(faker.number().numberBetween(15, 9000));

        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put(url);
        response(response);
    }

    @Test
    public void test_4_deleteBankAccount(){
        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .delete(url);
        response(response);
    }

    public void response(Response response){
        System.out.println("Status code:" + response.statusCode());
        response.prettyPrint();
    }
}
