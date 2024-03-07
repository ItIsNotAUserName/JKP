package day_6;

import com.github.javafaker.Faker;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pogo.CustomResponse;
import pogo.RequestBody;
import utilities.APIRunner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankAccountAPIRunner {
    Faker faker = new Faker();
    static int bankId;

    @Test
    public void test_1_getListOfBankAccounts(){
        String path = "/api/myaccount/bankaccount";
        APIRunner.runGET(path);

        CustomResponse[] customResponses = APIRunner.getCustomResponseArray();
        for (CustomResponse response : customResponses) {
            System.out.println("ID: "+response.getId());
        }
    }
    @Test
    public void test_2_createBankAccount(){
        String path = "/api/myaccount/bankaccount";

        RequestBody requestBody = new RequestBody();

        requestBody.setType_of_pay("CASH");
        requestBody.setBank_account_name(faker.company().name() + " bank account");
        requestBody.setDescription(faker.commerce().department() + " company");
        requestBody.setBalance(faker.number().numberBetween(15, 9000));

        APIRunner.runPOST(path, requestBody);
        CustomResponse response = APIRunner.getCustomResponse();
        System.out.println(response.getBank_account_name());
        bankId = response.getId();
    }

    @Test
    public void test_3_deleteBank(){
        String path = "/api/myaccount/bankaccount/"+bankId;
        APIRunner.runDELETE(path);
    }
}
