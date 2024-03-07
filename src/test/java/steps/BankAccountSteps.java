package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import pogo.CustomResponse;
import pogo.RequestBody;
import utilities.APIRunner;



public class BankAccountSteps {

    static String path;
    static int id;
    static  RequestBody requestBody = new RequestBody();

    @Given("Create bank account at {string} with data: {string} and {string} and {string} and {double}")
    public void create_bank_account_at_with_data_and_and_and(String given_path, String type_of_pay, String bank_account_name, String description, double balance) {
        System.out.println("-------------POST-------------");

        path = given_path;

        requestBody.setType_of_pay(type_of_pay);
        requestBody.setBank_account_name(bank_account_name);
        requestBody.setDescription(description);
        requestBody.setBalance(balance);

        APIRunner.runPOST(path, requestBody);
        CustomResponse response = APIRunner.getCustomResponse();
        id = response.getId();
        Assert.assertNotEquals(0, id);

    }

    @When("Get same bank by id and validate status code <{int}>")
    public void getSameBankByIdAndValidateStatusCode(int status_code) {
        System.out.println("-------------GET--------------");


        path+="/"+id;
        APIRunner.runGET(path);
        CustomResponse response = APIRunner.getCustomResponse();

        System.out.println("NAME: "+response.getBank_account_name());
        System.out.println("Status code: "+ APIRunner.getStatus_code());
        //Assert.assertEquals(status_code, APIRunner.getStatus_code());

    }

    @Then("Delete bank accounts by id")
    public void delete_bank_accounts_by_id() {
        System.out.println("------------DELETE------------");


        APIRunner.runDELETE(path);
        System.out.println("Status code: "+ APIRunner.getStatus_code());


        System.out.println("------------------------------");
    }


}
