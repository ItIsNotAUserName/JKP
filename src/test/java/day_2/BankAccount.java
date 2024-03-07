package day_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashWiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

public class BankAccount {
    @Test
    public void create_new_bankAccount(){
        //String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdHVkZW50QGNvZGV3aXNlLmNvbSIsImV4cCI6MTcwNzE4MzcwOCwiaWF0IjoxNzA2NTc4OTA4fQ.hU3zvoBa90oAJTbw7IpMrhODbednMcx-fvz-4o-akUQl-YHjwwUh2OsEBGNFcDfo34BufaxPQHWNnyk1fqqK1w";
        String url = "https://backend.cashwise.us/api/myaccount/bankaccount";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("type_of_pay", "BANK" );
        requestBody.put("bank_account_name", "Yooo2" );
        requestBody.put("description", "Financial company");
        requestBody.put("balance", 905 );

        Response response = RestAssured.given()
                .auth().oauth2(CashWiseAuthorization.getToken())
                .contentType(ContentType.JSON)
                .body( requestBody )
                .post(url);

        response.prettyPrint();
        System.out.println(response.statusCode());

    }

    @Test
    public void getListOfBankAccounts(){
        String token = CashWiseAuthorization.getToken();
        String url = Config.getProperty("baseUrl") +  "/api/myaccount/bankaccount";

        Response response = RestAssured.given()
                .auth().oauth2( token )
                .get( url );

        System.out.println("Status code: "+response.statusCode());

        for(int i = 0; i < response.jsonPath().getList("").size(); i++){
            String id = response.jsonPath().getString("["+i+"].id" );
            String bankAccountName = response.jsonPath().getString("["+i+"].bank_account_name");
            String description = response.jsonPath().getString("["+i+"].description");
            String typeOfPay = response.jsonPath().getString("["+i+"].type_of_pay");
            String balance = response.jsonPath().getString("["+i+"].balance");

            System.out.println(  "Bank id: " + id);
            System.out.println(  "Bank account name: " + bankAccountName);
            System.out.println(  "Bank description: " + description);
            System.out.println(  "Type of pay: " + typeOfPay);
            System.out.println(  "Balance: " + balance);

            Assert.assertNotNull("Id is null", id);
            Assert.assertNotNull("Bank account name is null", bankAccountName);
            Assert.assertNotNull("Description is null", description);
            Assert.assertNotNull("Type of pay is null", typeOfPay);
            Assert.assertNotNull("Balance is null", balance);
            System.out.println("=====================================");
        }

    }
    @Test
    public void getBank(){
        int id = 864;
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount/" + id;
        String token = CashWiseAuthorization.getToken();

        Response response = RestAssured.given()
                .auth().oauth2(  token  )
                .get(  url  );
        response.prettyPrint();

        String bankName = response.jsonPath().getString("bank_account_name");
        System.out.println(bankName);
    }
}
