package homework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

public class PetStore {

    static String url = "";
    static String id = "";

    @Test
    public void addPet(){
        url = "https://petstore.swagger.io/v2/pet";
        String requestBody = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Pet>\n" +
                "\t<id>0</id>\n" +
                "\t<Category>\n" +
                "\t\t<id>0</id>\n" +
                "\t\t<name>Dog</name>\n" +
                "\t</Category>\n" +
                "\t<name>Messi</name>\n" +
                "\t<photoUrls>\n" +
                "\t\t<photoUrl>string</photoUrl>\n" +
                "\t</photoUrls>\n" +
                "\t<tags>\n" +
                "\t\t<Tag>\n" +
                "\t\t\t<id>0</id>\n" +
                "\t\t\t<name>string</name>\n" +
                "\t\t</Tag>\n" +
                "\t</tags>\n" +
                "\t<status>available</status>\n" +
                "</Pet>";

        Response response = RestAssured.given()
                .contentType(ContentType.XML)
                .body(requestBody)
                .post(url);

        id = response.jsonPath().getString("id");

        System.out.println("Add pet:");
        System.out.println("Status code: "+response.statusCode());
        response.prettyPrint();
    }
    @Test
    public void getPet(){
        url = "https://petstore.swagger.io/v2/pet/"+id;
        Response response = RestAssured.given()
                .get(url);

        System.out.println("Get pet by ID:");
        System.out.println("Status code: "+response.statusCode());
        response.prettyPrint();
    }

    @Test
    public void deletePet(){
        Response response = RestAssured.given()
                .delete(url);
        System.out.println("Delete pet by ID");
        System.out.println(response.statusCode());
        response.prettyPrint();
    }
}
