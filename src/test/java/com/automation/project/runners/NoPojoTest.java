package com.automation.project.runners;

import com.automation.project.configuration.ConfigurationProperties;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static com.automation.project.context.Specifications.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class NoPojoTest {
    private static final String baseUrl = ConfigurationProperties.getConfigPropertyValue("rest.api.url");

    @Test
    @DisplayName("Avatars contains user id")
    public void checkAvatarsNoPojoTest() {
        installSpecification(requestSpec(baseUrl), responseSpecOK200());
        Response response = given()
                .when()
                .get("api/users?page=2")
                .then()
                .body("page", equalTo(2))
                .body("data.id", notNullValue())
                .body("data.email", notNullValue())
                .body("data.first_name", notNullValue())
                .body("data.last_name", notNullValue())
                .body("data.avatar", notNullValue())
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> emails = jsonPath.get("data.email");
        List<Integer> ids = jsonPath.get("data.id");
        List<String> avatars = jsonPath.get("data.avatar");

        IntStream.range(0, avatars.size()).mapToObj(i -> avatars.get(i).contains(ids.get(i).toString())).forEach(Assertions::assertTrue);

        Assertions.assertTrue(emails.stream().allMatch(x -> x.endsWith("@reqres.in")));
    }

    @Test
    @DisplayName("Successful registration")
    public void successUserTestNoPojo() {
        installSpecification(requestSpec(baseUrl), responseSpecOK200());
        Map<String, String> user = new HashMap<>();
        user.put("email", "eve.holt@reqres.in");
        user.put("password", "pistol");
        Response response = given()
                .body(user)
                .when()
                .post("api/register")
                .then()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("id");
        String token = jsonPath.get("token");
        Assertions.assertEquals(4, id);
        Assertions.assertEquals("QpwL5tke4Pnpja7X4", token);
    }

    @Test
    @DisplayName("Unsuccessful registration")
    public void unsuccessfulUserNoPojo() {
        installSpecification(requestSpec(baseUrl), responseSpecError400());
        Map<String, String> user = new HashMap<>();
        user.put("email", "sydney@fife");
        Response response = given()
                .body(user)
                .when()
                .post("api/register")
                .then()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assertions.assertEquals("Missing password", error);
    }
}
