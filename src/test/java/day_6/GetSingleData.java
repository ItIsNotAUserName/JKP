package day_6;

import org.junit.Assert;
import org.junit.Test;
import pogo.CustomResponse;
import utilities.APIRunner;

public class GetSingleData {
    @Test
    public void test_1_getSeller(){
        String path = "/api/myaccount/sellers/3467";

        APIRunner.runGET(   path   );
        CustomResponse customResponse = APIRunner.getCustomResponse();

        System.out.println(customResponse.getSeller_id());
        System.out.println(customResponse.getSeller_name());
    }

    @Test
    public void test_2_getProduct(){
        String path = "/api/myaccount/products/913";
        APIRunner.runGET(   path   );
        CustomResponse customResponse = APIRunner.getCustomResponse();

        System.out.println((customResponse.getProduct_title()));
        System.out.println(customResponse.getProduct_price());
        System.out.println((customResponse.getProduct_id()));

        Assert.assertNotNull(customResponse.getProduct_title());
        Assert.assertNotEquals(customResponse.getProduct_price(), null);
        Assert.assertNotEquals(customResponse.getProduct_id(), null);
    }
}
