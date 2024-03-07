package day_6;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import pogo.CustomResponse;
import pogo.RequestBody;
import utilities.APIRunner;

public class SellersAPIRunner {
    Faker faker = new Faker();
    @Test
    public void getListOfSellers(){
        String path = "/api/myaccount/sellers/all";
        APIRunner.runGET(path);
        CustomResponse[] customResponse = APIRunner.getCustomResponseArray();
        for (CustomResponse response: customResponse) {
            System.out.println("----------------------------------------");
            System.out.println("ID     : " + response.getSeller_id());
            Assert.assertNotEquals(response.getSeller_id(), null);

            System.out.println("NAME   : " + response.getSeller_name());
            Assert.assertNotNull(response.getSeller_name());

            System.out.println("COMPANY: "+response.getCompany_name());
            Assert.assertNotNull(response.getCompany_name());
        }
    }
    @Test
    public void test_2_createSeller(){
        String path = "/api/myaccount/sellers";

        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name(faker.company().name());
        requestBody.setSeller_name(faker.name().fullName());
        requestBody.setEmail(faker.internet().emailAddress());
        requestBody.setPhone_number(faker.number().digit());
        requestBody.setAddress(faker.address().fullAddress());

        APIRunner.runPOST(path, requestBody);
        CustomResponse customResponse = APIRunner.getCustomResponse();

        System.out.println("--------------------------------");
        System.out.println("ID: "+customResponse.getSeller_id());
        System.out.println("NAME: "+customResponse.getSeller_name());

        Assert.assertNotEquals(customResponse.getSeller_id(), null);
        Assert.assertNotEquals(customResponse.getSeller_name(), null);
    }
}
