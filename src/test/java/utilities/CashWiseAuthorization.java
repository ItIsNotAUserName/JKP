package utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pogo.RequestBody;

import java.util.HashMap;
import java.util.Map;

public class CashWiseAuthorization {

    private static String token;


    public static String getToken(){
        String url = "https://backend.cashwise.us/api/myaccount/auth/login";
        RequestBody requestBody = new RequestBody();
        requestBody.setEmail(Config.getProperty("email"));
        requestBody.setPassword(Config.getProperty("password"));

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body( requestBody )
                .post( url );

        token = response.getBody().jsonPath().getString("jwt_token");
        return token;
    }
}
