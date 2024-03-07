package day_5_recap_pojo;

import org.junit.Test;
import pogo.CustomResponse;
import utilities.APIRunner;

public class BankAccountWithAPIRunner {

    @Test
    public void test_1_getBankAccount(){
        //https://backend.cashwise.us       /api/myaccount/bankaccount      /1283
        String path = "/api/myaccount/bankaccount/1283";
        APIRunner.runGET(   path   );
        CustomResponse customResponse = APIRunner.getCustomResponse();
        System.out.println(customResponse.getBank_account_name());
    }
}
