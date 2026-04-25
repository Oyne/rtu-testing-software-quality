package petstore.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PetStoreTests {

    private static long createdPetID = -1;
    private final String username = "testUserAutomation";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    private void ensurePetExists() {
        if (createdPetID <= 0) {
            testAddPet_Positive();
        }
    }

    @Test
    @Feature("Pet")
    @Description("Add a new pet and validate response structure and ID.")
    public void testAddPet_Positive() {
        String body = "{\"name\": \"TestPet\", \"status\": \"available\"}";

        createdPetID = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/pet")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", equalTo("TestPet"))
                .body("id", is(instanceOf(Long.class)))
                .extract().path("id");
    }

    @Test
    @Feature("Pet")
    @Description("Retrieve pet by ID and verify data consistency.")
    public void testGetPetById_Positive() {
        ensurePetExists();
        given()
                .pathParam("petId", createdPetID)
                .when().get("/pet/{petId}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(createdPetID))
                .body("status", is(notNullValue()));
    }

    @Test
    @Feature("Pet")
    @Description("Update pet status and verify changes.")
    public void testUpdatePet_Positive() {
        ensurePetExists();
        String updatedBody = "{\"id\": " + createdPetID + ", \"name\": \"Rex\", \"status\": \"sold\"}";

        given()
                .contentType(ContentType.JSON)
                .body(updatedBody)
                .when().put("/pet")
                .then()
                .statusCode(200)
                .body("status", equalTo("sold"))
                .body("name", is(instanceOf(String.class)));
    }

    @Test
    @Feature("Pet")
    @Description("Verify 404 for non-existent pet ID.")
    public void testGetPetById_Negative_NotFound() {
        given()
                .pathParam("petId", 0)
                .when().get("/pet/{petId}")
                .then()
                .statusCode(404)
                .body("message", equalTo("Pet not found"));
    }

    @Test
    @Feature("Store")
    @Description("Place an order for a pet and validate order status.")
    public void testPlaceOrder_Positive() {
        ensurePetExists();
        String orderBody = "{\"petId\": " + createdPetID + ", \"quantity\": 1, \"status\": \"placed\"}";

        given()
                .contentType(ContentType.JSON)
                .body(orderBody)
                .when().post("/store/order")
                .then()
                .statusCode(200)
                .body("petId", equalTo(createdPetID))
                .body("status", equalTo("placed"))
                .body("id", is(notNullValue()));
    }

    @Test
    @Feature("Store")
    @Description("Verify inventory returns correct keys.")
    public void testGetInventory_Positive() {
        given()
                .when().get("/store/inventory")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", hasKey("available"));
    }

    @Test
    @Feature("Store")
    @Description("Verify error when deleting invalid order ID.")
    public void testDeleteOrder_Negative_NotFound() {
        given()
                .pathParam("orderId", -1)
                .when().delete("/store/order/{orderId}")
                .then()
                .statusCode(404);
    }

    @Test
    @Feature("User")
    @Description("Create a new user and validate response message.")
    public void testCreateUser_Positive() {
        String userBody = "{\"username\": \"" + username + "\", \"firstName\": \"QA\"}";

        given()
                .contentType(ContentType.JSON)
                .body(userBody)
                .when().post("/user")
                .then()
                .statusCode(200)
                .body("code", is(200))
                .body("message", is(instanceOf(String.class)));
    }

    @Test
    @Feature("User")
    @Description("Retrieve user by name and verify status type.")
    public void testGetUserByName_Positive() {
        testCreateUser_Positive();
        given()
                .pathParam("username", username)
                .when().get("/user/{username}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("username", equalTo(username))
                .body("userStatus", is(instanceOf(Integer.class)));
    }

    @Test
    @Feature("User")
    @Description("Verify error response for unknown username.")
    public void testGetUserByName_Negative_NotFound() {
        given()
                .pathParam("username", "unknown_user_999")
                .when().get("/user/{username}")
                .then()
                .statusCode(404)
                .body("message", containsString("User not found"));
    }
}