package pogo;

import lombok.Data;

@Data
public class RequestBody {
    private String email;
    private String password;


    //Category control
    private String category_title;
    private String category_description;
    private boolean flag;


    //Bank control
    private String type_of_pay;
    private String bank_account_name;
    private String description;
    private double balance;


    //Seller control
    private String company_name;
    private String seller_name;
    //private String email;
    private String phone_number;
    private String address;

    //Client control
    private String client_name;
    private int client_id;
    private int[] tags_id;


}
