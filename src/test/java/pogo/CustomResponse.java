package pogo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomResponse {
    private static int status_code;
    //Category
    private int category_id;
    private String category_title;
    private String category_description;

    //Bank control
    private String type_of_pay;
    private String bank_account_name;
    private String description;
    private double balance;
    //Bank account response
    private int id;


    //Sellers response
    private CustomResponse[] responses;
    private int seller_id;
    private String seller_name;
    private String company_name;
    private String address;
    private String phone_number;
    private String email;

    //Product controller
    private String product_title;
    private double product_price;
    private int service_type_id;
    private int product_id;
    private String product_description;

    //Payment
    private int payment_id;
    private String client_name;
    private int client_id;
    private int[] tags_id;
}
