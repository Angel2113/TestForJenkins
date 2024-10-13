package api;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class APITest  {

    private String mainPage = "https://reqres.in/";

    @Test(description = "Valid Login", priority = 0)
    public void test01(){

        String endpoint = mainPage + "api/login";

        JSONObject data = new JSONObject();

        data.put("email", "eve.holt@reqres.in");
        data.put("password", "cityslicka");

        Response login = given()
                .contentType(ContentType.JSON)
                .body(data.toString())
                .when()
                .post(endpoint)
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println(login.getBody().prettyPrint());


    }

    @Test(description = "Invalid Login", priority = 0)
    public void test02(){

        String endpoint = mainPage + "api/login";

        JSONObject data = new JSONObject();

        data.put("email", "email@reqres.in");
        data.put("password", "hello");

        Response login = given()
                .contentType(ContentType.JSON)
                .body(data.toString())
                .when()
                .post(endpoint)
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println(login.getBody().prettyPrint());


    }

}