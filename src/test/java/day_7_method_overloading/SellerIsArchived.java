package day_7_method_overloading;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pogo.CustomResponse;
import utilities.APIRunner;
import utilities.CashWiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SellerIsArchived {
    @Test
    public void test_1_getAllIsArchivedSellers() throws JsonProcessingException {
        // /api/myaccount/sellers ?isArchived=false&page=1&size=10

        String url = Config.getProperty("baseUrl")+ "/api/myaccount/sellers";

        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 10);

        Response response  = RestAssured.given()
                .auth().oauth2(CashWiseAuthorization.getToken())
                .params(params)
                .get(url);

        ObjectMapper mapper = new ObjectMapper();

        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);

        for(int i = 0; i < customResponse.getResponses().length; i++){
            System.out.println(customResponse.getResponses()[i].getSeller_id());
        }
    }
    @Test
    public void test_2_getAllIsArchivedSellers_APIRunner(){
        String path = "/api/myaccount/sellers";

        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 10);

        APIRunner.runGET(path, params);

        CustomResponse customResponse = APIRunner.getCustomResponse();
        int size = customResponse.getResponses().length;

        for(int i = 0; i < size; i++){
            System.out.println("ID: " + customResponse.getResponses()[i].getSeller_id());
            Assert.assertNotNull(customResponse.getResponses()[i].getSeller_id());

            System.out.println("NAME: "+customResponse.getResponses()[i].getSeller_name());
            Assert.assertNotNull(customResponse.getResponses()[i].getSeller_name());

            System.out.println("----------------------------------------------------");
        }
    }

    @Test
    public void test_3_AllArchived(){
        String path = "/api/myaccount/sellers";

        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", true);
        params.put("page", 1);
        params.put("size", 6);

        APIRunner.runGET(path, params);

        CustomResponse customResponse = APIRunner.getCustomResponse();
        int size = customResponse.getResponses().length;

        for(int i = 0; i < size; i++){
            System.out.println("ID: " + customResponse.getResponses()[i].getSeller_id());
            Assert.assertNotNull(customResponse.getResponses()[i].getSeller_id());

            System.out.println("NAME: "+customResponse.getResponses()[i].getSeller_name());
            Assert.assertNotNull(customResponse.getResponses()[i].getSeller_name());

            System.out.println("----------------------------------------------------");
        }
    }
}
