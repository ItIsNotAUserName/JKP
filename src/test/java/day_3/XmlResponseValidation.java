package day_3;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class XmlResponseValidation {
    static String id = "";
    @Test
    public void createTraveler(){
        String url = "http://restapi.adequateshop.com/api/Traveler";
        Faker faker = new Faker();

        String name = faker.name().firstName();
        String email = faker.internet().emailAddress();
        String address = faker.address().fullAddress();

        String requestBody = "<?xml version=\"1.0\"?>\n" +
                "<Travelerinformation>\n" +
                "    <name>"+name+"</name>\n" +
                "    <email>"+email+"</email>\n" +
                "    <adderes>"+address+"</adderes>\n" +
                "</Travelerinformation>";

        Response response = RestAssured.given()
                .contentType(ContentType.XML)
                .body(requestBody)
                .post(url);

        id = response.xmlPath().getString("Travelerinformation.id");
        System.out.println(id);
    }

    @Test
    public void getTraveler(){
        String url = "http://restapi.adequateshop.com/api/Traveler/"+id;
        Response response = RestAssured.given()
                .get(url);
        response.prettyPrint();

        String actualName = response.xmlPath().getString("Travelerinformation.name");
        String email = response.xmlPath().getString("Travelerinformation.email");

        Assert.assertNotNull("Name is null", actualName);
        Assert.assertNotNull("email is null", email);
    }

    @Test
    public void getListOfTravelers(){
        String url = "http://restapi.adequateshop.com/api/Traveler";
        Response response = RestAssured.get(url);


        int numOfTraveler = 0;
        String id = response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation["+numOfTraveler+"].id");
        String name = response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation["+numOfTraveler+"].name");
        String email = response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation["+numOfTraveler+"].email");

        System.out.println(id);
        System.out.println(name);
        System.out.println(email);
    }
}
