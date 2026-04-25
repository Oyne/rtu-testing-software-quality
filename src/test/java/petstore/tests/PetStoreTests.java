package petstore.tests;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static org.hamcrest.Matchers.*;

public class PetStoreTests {

    private long createdPetID = -1;
    private final String username = "testUserAutomation";

    private void ensurePetExists() {
        if (createdPetID <= 0) {
            createdPetID = given().contentType(ContentType.JSON)
                    .body("{\"name\": \"TestPet\", \"status\": \"available\"}")
                    .when()
                    .post("/pet")
                    .then()
                    .statusCode(200)
                    .extract()
                    .path("id");
        }
    }

    private void ensureUserExists() {
        String userBody = "{" + "\"username\": \"" + username + "\"," + "\"firstName\": \"Test\"," + "\"lastName\": \"User\"," + "\"email\": \"test@test.com\"," + "\"password\": \"pass123\"," + "\"phone\": \"1234567890\"," + "\"userStatus\": 1" + "}";

        given()
                .contentType(ContentType.JSON)
                .body(userBody)
                .post("/user");
    }


    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    @Description("Validates that a pet can be successfully added and stores the ID for future tests.")
    public void testAddPet_Positive() {
        String body = "{" + "\"name\": \"TestPet\"," + "\"status\": \"available\"" + "}";

        createdPetID = given()
                .contentType(ContentType.JSON)
                .body(body).when().post("/pet")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", equalTo("TestPet"))
                .extract()
                .path("id");
    }

    @Test
    @Description("Validates the data structure and specific values of a pet record.")
    public void testGetPetById_Positive() {
        ensurePetExists();
        given().pathParam("petId", createdPetID)
                .when().get("/pet/{petId}")
                .then()
                .statusCode(200)
                .body("id", is(notNullValue()))
                .body("name", is(instanceOf(String.class)))
                .body("status", anyOf(equalTo("available"), equalTo("pending"), equalTo("sold")));
    }

    @Test
    @Description("Validates that a 404 error is returned for a non-existent pet.")
    public void testGetPetById_Negative_NotFound() {
        given()
                .pathParam("petId", -1)
                .when()
                .get("/pet/{petId}")
                .then()
                .statusCode(404)
                .body("message", equalTo("Pet not found"));
    }

    @Test
    @Description("Validates that an existing pet's status can be updated via PUT.")
    public void testUpdatePet_Positive() {
        ensurePetExists();
        String updatedBody = "{" + "\"id\": " + createdPetID + "," + "\"name\": \"Rex\"," + "\"status\": \"sold\"" + "}";

        given()
                .contentType(ContentType.JSON)
                .body(updatedBody)
                .when()
                .put("/pet")
                .then()
                .statusCode(200)
                .body("status", equalTo("sold"));
    }

    @Test
    @Description("Validates that a user can place an order for a pet.")
    public void testPlaceOrder_Positive() {
        ensurePetExists();
        String orderBody = "{" + "\"id\": 5," + "\"petId\": " + createdPetID + "," + "\"quantity\": 1," + "\"shipDate\": \"2023-10-27T10:00:00.000Z\"," + "\"status\": \"placed\"," + "\"complete\": true" + "}";

        given()
                .contentType(ContentType.JSON)
                .body(orderBody).when()
                .post("/store/order")
                .then().statusCode(200)
                .body("status", equalTo("placed"))
                .body("complete", equalTo(true));
    }

    @Test
    @Description("Validates the inventory schema (key-value pairs of statuses).")
    public void testGetInventory_Positive() {
        given()
                .when()
                .get("/store/inventory")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", hasKey("available"));
    }

    @Test
    @Description("Validates error response when deleting an order that doesn't exist.")
    public void testDeleteOrder_Negative_NotFound() {
        given()
                .pathParam("orderId", -1)
                .when()
                .delete("/store/order/{orderId}")
                .then()
                .statusCode(404);
    }

    @Test
    @Description("Validates that a user can be created via POST.")
    public void testCreateUser_Positive() {
        String userBody = "{" + "\"username\": \"" + username + "\"," + "\"firstName\": \"Test\"," + "\"lastName\": \"User\"," + "\"email\": \"test@test.com\"," + "\"password\": \"pass123\"," + "\"phone\": \"1234567890\"," + "\"userStatus\": 1" + "}";

        given()
                .contentType(ContentType.JSON)
                .body(userBody).when()
                .post("/user")
                .then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", is(notNullValue()));
    }

    @Test
    @Description("Validates the details of a created user.")
    public void testGetUserByName_Positive() {
        ensureUserExists();
        given()
                .pathParam("username", username)
                .when()
                .get("/user/{username}")
                .then()
                .statusCode(200)
                .body("username", equalTo(username))
                .body("email", equalTo("test@test.com"));
    }

    @Test
    @Description("Validates system behavior when logging in without parameters.")
    public void testLogin_Negative_EmptyParams() {
        given()
                .queryParam("username", "")
                .queryParam("password", "")
                .when()
                .get("/user/login")
                .then()
                .statusCode(200)
                .body("message", containsString("logged in user session"));
    }
}