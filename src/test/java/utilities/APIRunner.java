package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;
import pogo.CustomResponse;
import pogo.RequestBody;

import java.util.Map;

import static utilities.CashWiseAuthorization.getToken;

public class APIRunner {
    @Getter
    private static int status_code;
    @Getter
    private static String responseBody;
    @Getter
    private static CustomResponse customResponse = null;
    @Getter
    private static CustomResponse[] customResponseArray = null;

    static String url;

    public static void runGET(String path){

        url = Config.getProperty("baseUrl") + path;

        Response response = RestAssured.given()
                .auth().oauth2(   getToken()   )
                .get(   url   );

        ObjectMapper mapper = new ObjectMapper();
        try {
            customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        } catch (JsonProcessingException e) {
            try {
                customResponseArray = mapper.readValue(response.asString(), CustomResponse[].class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
        status_code = response.statusCode();
        responseBody = response.asPrettyString();
        //response.prettyPrint();
    }

    public static void runGET(String path, Map<String, Object> params){

        url = Config.getProperty("baseUrl") + path;

        Response response = RestAssured.given()
                .auth().oauth2(   getToken()   )
                .params(params)
                .get(   url   );

        ObjectMapper mapper = new ObjectMapper();
        try {
            customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        } catch (JsonProcessingException e) {
            try {
                customResponseArray = mapper.readValue(response.asString(), CustomResponse[].class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
        status_code = response.statusCode();
        responseBody = response.asPrettyString();
    }

    public static void runPOST(String path, RequestBody requestBody){

        url = Config.getProperty("baseUrl")+path;

        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);

        ObjectMapper mapper = new ObjectMapper();
        try {
            customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        status_code = response.statusCode();
        responseBody = response.asPrettyString();
        //System.out.println("Status code: "+response.statusCode());
        //response.prettyPrint();
    }

    public static void runDELETE(String path){

        url = Config.getProperty("baseUrl")+path;

        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .delete(url);

        ObjectMapper mapper = new ObjectMapper();
        try {
            customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        status_code = response.statusCode();
        responseBody = response.asPrettyString();
    }
}
