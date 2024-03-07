package day_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.CashWiseAuthorization;
import utilities.Config;

import java.io.File;

public class FileUpload {
    @Test
    public void fileUpload(){
        String url = Config.getProperty("baseUrl")+ "/api/myaccount/file/v2";
        String token = CashWiseAuthorization.getToken();

        String filePath = "FileToUpload.txt";

        File file = new File(filePath);

        Response response = RestAssured.given()
                .auth().oauth2(token)
                .multiPart("file", file, "multiple/form-data")
                .post(url);
        System.out.println(response.statusCode());

        response.prettyPrint();
    }
}
