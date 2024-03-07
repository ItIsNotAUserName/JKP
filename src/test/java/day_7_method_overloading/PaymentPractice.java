package day_7_method_overloading;

import org.junit.Test;
import pogo.CustomResponse;
import utilities.APIRunner;

public class PaymentPractice {
    @Test
    public void test_1_getSinglePayment(){
        //https://backend.cashwise.us/api/myaccount/payments/204
        String path = "/api/myaccount/payments/204";

        APIRunner.runGET(path);
        CustomResponse response = APIRunner.getCustomResponse();
        System.out.println(response.getPayment_id());
    }
}
