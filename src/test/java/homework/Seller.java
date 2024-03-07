package homework;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashWiseAuthorization;
import utilities.Config;

public class Seller {
    @Test
    public void getListOfSellers(){
        String token = CashWiseAuthorization.getToken();
        String url = Config.getProperty("baseUrl")+"/api/myaccount/sellers/all";

        Response response = RestAssured.given()
                .auth().oauth2(token)
                .get(url);

        System.out.println("Status code: "+response.statusCode());
        System.out.println("Size: "+response.jsonPath().getList("").size());

        Assert.assertNotNull(response.jsonPath().getString("seller_id"));
        Assert.assertNotNull(response.jsonPath().getString("company_name"));
        Assert.assertNotNull(response.jsonPath().getString("seller_name"));
    }
}
