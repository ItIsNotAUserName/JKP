package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import pogo.CustomResponse;
import utilities.APIRunner;

import java.util.HashMap;
import java.util.Map;

public class Sellers_steps {
    @Given("get all sellers with api path {string} and parameters: isArchived {string}, page {int}, size {int}")
    public void get_all_sellers_with_api_path_and_parameters_is_archived_page_size(String path, String isArchived, Integer page, Integer size) {

        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", isArchived);
        params.put("page", page);
        params.put("size", size);

        APIRunner.runGET(path, params);

    }
    @Then("user get all phone numbers and validate is not null")
    public void user_get_all_phone_numbers_and_validate_is_not_null() {

        CustomResponse response = APIRunner.getCustomResponse();
        int size = response.getResponses().length;

        for(int i = 0; i < size; i++){
            System.out.println("Phone number: "+response.getResponses()[i].getEmail());
            Assert.assertNotNull(response.getResponses()[i].getEmail());
            System.out.println("-----------------------------------------------------------");
        }


    }

    @Given("get all sellers with api path {string}")
    public void get_all_sellers_with_api_path(String path) {
        APIRunner.runGET(path);
    }
    @Then("print seller names and validate it is not null")
    public void print_seller_names_and_validate_it_is_not_null() {
        CustomResponse[] response = APIRunner.getCustomResponseArray();
        for(int i = 0; i < response.length; i++){
            System.out.println("Name: "+response[i].getSeller_name());
            Assert.assertNotNull(response[i].getSeller_name());
            System.out.println("-----------------------------------");
        }

    }
}
