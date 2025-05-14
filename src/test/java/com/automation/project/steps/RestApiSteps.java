
package com.automation.project.steps;

import com.automation.project.actions.ApiRequests;
import com.automation.project.asserts.CustomAssert;
import com.automation.project.configuration.ConfigurationProperties;
import com.automation.project.entity.SuccessUserReg;
import com.automation.project.enums.ApiPaths;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

import static com.automation.project.actions.ApiRequests.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Slf4j
public class RestApiSteps {
    private Response response;
    private static final String BASE_URL = ConfigurationProperties.getConfigPropertyValue("rest.api.url");

    @Given("the API is accessible via the configured base URL")
    public void theApiIsAccessibleViaTheConfiguredBaseUrl() {
        log.info("Checking API accessibility at: {}", BASE_URL);
        int statusCode = given()
                .baseUri(BASE_URL)
                .when()
                .get()
                .then()
                .extract()
                .statusCode();
        CustomAssert.assertThat("API should return HTTP 200", statusCode, is(200));
    }

    @When("a GET request is sent to the endpoint {string}")
    public void aGetRequestIsSentToTheEndpoint(String urlKey) {
        ApiPaths apiPath = ApiPaths.valueOf(urlKey);
        response = sendGetRequest(apiPath.getPath());
        log.info("GET response from [{}]: {}", apiPath.getPath(), response.statusCode());
    }

    @Then("the response should contain {string} with value {int}")
    public void validateResponseField(String field, int expectedValue) {
        Object actualValue = response.getBody().jsonPath().get(field);
        CustomAssert.assertThat("Validating response field", actualValue, is(expectedValue));
    }

    @When("a POST request is sent to the endpoint {string} with data {string} and value {string}")
    public void sendPostRequest(String urlKey, String field, String value) {
        ApiPaths apiPath = ApiPaths.valueOf(urlKey);
        response = ApiRequests.sendPostRequest(getJsonObject(field, value), apiPath.getPath());
    }
    @Then("the response of POST status code should be {int} with message {string}")
    public void theResponseOfPostStatusCodeShouldBeWithMessage(Integer statusCode, String message) {
        CustomAssert.assertThat(message, response.statusCode(), is(statusCode));
    }

    @Then("the response should contain token {string}")
    public void validateTokenInResponse(String expectedToken) throws Exception {
        if (!expectedToken.isEmpty() && response.statusCode() < 400) {
            SuccessUserReg user = new ObjectMapper().readValue(response.asString(), SuccessUserReg.class);
            CustomAssert.assertThat("Validating token", user.getToken(), is(expectedToken));
        }
    }

    @When("the PUT request is sent {string} with data {string} and value {string}")
    public void sendPutRequest(String urlKey, String field, String value) {
        response = ApiRequests.sendPutRequest(getJsonObject(field, value), ApiPaths.valueOf(urlKey).getPath());
    }

    @Then("the response status code should be {int}, with message {string}")
    public void validateStatusCode(int expectedCode, String message) {
        int actualStatusCode = response.statusCode();
        log.info("Expected status: {}, Actual status: {}, Message: {}", expectedCode, actualStatusCode, message);

        CustomAssert.assertThat(message, actualStatusCode, is(expectedCode));
    }



    @When("the PATCH request is sent to {string} with data {string} and value {string}")
    public void sendPatchRequest(String urlKey, String field, String value) {
        response = ApiRequests.sendPatchRequest(getJsonObject(field, value), ApiPaths.valueOf(urlKey).getPath());
    }

    @Then("the response should contain field {string}")
    public void validateResponseFieldPresence(String field) {
        Object value = response.getBody().jsonPath().get(field);
        CustomAssert.assertThat("Response should contain field", value, notNullValue());
    }

    @When("I delete a user at {string}")
    public void sendDeleteRequest(String urlKey) {
        response = ApiRequests.sendDeleteRequest(ApiPaths.valueOf(urlKey).getPath());
    }

    @When("I create a user with the following details:")
    public void createUserWithDataTable(DataTable table) {
        Map<String, String> userData = table.asMap(String.class, String.class);
        response = given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(userData)
                .when()
                .post("api/register")
                .then()
                .extract().response();
    }

    @Then("the response should contain token")
    public void validateTokenExists() {
        String error = response.jsonPath().get("error");
        if (error != null) {
            Assertions.fail("API returned error: " + error);
        }
        String token = response.jsonPath().get("token");
        Assertions.assertNotNull(token, "Token is null – user may not be registered.");
    }
    @When("a POST request is sent to the endpoint {string} with data {string} and value {string}")
    public void sendPostRequestMatchingFeature(String urlKey, String field, String value) {
        ApiPaths apiPath = ApiPaths.valueOf(urlKey);
        response = ApiRequests.sendPostRequest(getJsonObject(field, value), apiPath.getPath());
    }


}
