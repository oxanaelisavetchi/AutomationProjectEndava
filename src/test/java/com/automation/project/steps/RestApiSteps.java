package com.automation.project.steps;

import com.automation.project.asserts.CustomAssert;
import com.automation.project.configuration.ConfigurationProperties;
import com.automation.project.entity.SuccessUserReg;
import com.automation.project.enums.ApiPaths;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

import static com.automation.project.actions.RestApiActions.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Slf4j
public class RestApiSteps {

    // maybe create a POJO class Response a check it
    private Response response;

    private static final String baseUrl = ConfigurationProperties.getConfigPropertyValue("rest.api.url");
    // private static final String BASEURL = ConfigurationProperties.getConfigPropertyValue("rest.api.url");

    @Given("the API is reachable at base URL")
    public void apiIsReachableAtBaseUrl() {
        log.info("Checking availability of base API URL: {}", baseUrl);

        int statusCode = given()
                .baseUri(baseUrl)
                .when()
                .get()
                .then()
                .extract()
                .statusCode();

        log.info("Status code received: {}", statusCode);
        // log.info("Received HTTP status code [{}] while verifying API availability.", statusCode);
        CustomAssert.assertThat("API availability check", statusCode, is(200));
        // CustomAssert.assertThat("Verifying that the API is operational and returns HTTP 200", statusCode, is(200));
    }

    @When("run request {string}")
    public void runRequestUrl(ApiPaths apiPath) {
        // valueOf not safety and uncomfortable
        String path = apiPath.getPath();
        response = runGetRequest(path);
        // log more business and info
        log.info("Response: {}", response.asString());
    }


    @And("check get status code {int}, {string}")
    public void checkGetStatusCode(int resp, String message) {
        // get from scenario context
        checkResponse(message, response.statusCode(), resp);
        // add log info
    }

    @Then("check get response {string}, {int}, {string}")
    public void checkGetResponse(String data, int value, String message) {
        // rewrite the log message
        log.info("Validating JSON path '{}' equals value '{}'", data, value);

        // No hardcode 200 -> use variable or Enum
        if (response.statusCode() == 200) {
            Object actualValue = response.getBody().jsonPath().get(data);
            log.info("Actual value for {}: {}", data, actualValue);

            CustomAssert.assertThat(message, actualValue, is(value));
        } else {
            log.warn("Skipping response value check — status: {}", response.statusCode());
        }
    }

    @When("run request {string} with {string} and {string}")
    // Use ApiPaths enum instead og String url
    public void runRequest(String url, String field, String value) {
        // String apiPath = ApiPaths.valueOf(url).getPath();
        ApiPaths apiPath = ApiPaths.valueOf(url);
        response = runPostRequest(getJsonObject(field, value), apiPath.getPath());
        // use scenario context to save response
        // ScenarioContext.saveScenario("Response", response);
    }


    @And("check status code {int}, {string}")
    public void checkStatusCode(int statusCode, String message) {
        // get response from ScenarioContext
        // ScenarioContext.getScenario("Response");
        // StatusCode actual = response.getStatusCode();
        // assertThat(message, actual, equalsTo(statusCode)), assertEquals
        CustomAssert.assertThat(message, response.statusCode(), is(statusCode));
    }

    @Then("check post response {string}, {string}")
    public void checkPostResponse(String message, String expectedToken) throws Exception {
        // get response from scenario context
        // use enum with status codes, maybe exist in library og rest-assured
        // response.statusCode() < 400 -> check this
        // Response response = ScenarioContext.getScenario("Response");

        if (!expectedToken.isEmpty() && response.statusCode() < 400) {
            SuccessUserReg user = new ObjectMapper().readValue(response.asString(), SuccessUserReg.class);
            CustomAssert.assertThat(message, user.getToken(), is(expectedToken));
        }
    }

    @When("run put request {string} with {string} and {string}")
    public void runPutRequests(String url, String field, String value) {
        ApiPaths apiPath = ApiPaths.valueOf(url);
        response = runPutRequest(getJsonObject(field, value), apiPath.getPath());
    }


    @Then("check response {string}")
    public void checkResponses(String message) {
        CustomAssert.assertThat(message, response.getBody().jsonPath().get("updatedAt"), notNullValue());
    }

    @When("run patch request {string} with {string} and {string}")
    public void runPatchRequestUrlWithDataAndValue(String url, String field, String value) {
        ApiPaths apiPath = ApiPaths.valueOf(url);
        response = runPatchRequest(getJsonObject(field, value), apiPath.getPath());
    }


    @When("run delete request {string}")
    public void deleteFunctionality(String path) {
        ApiPaths apiPath = ApiPaths.valueOf(path);
        // move delete to ApiRequests
        response = given()
                .baseUri(baseUrl)
                .when()
                .delete(collectUrl(apiPath.getPath()))
                .then()
                .extract().response();
        // add log
    }


    @When("I create a user with the following details:")
    public void createUserWithDataTable(DataTable table) {
        Map<String, String> userData = table.asMap(String.class, String.class);
        log.info("Trimitem acest JSON: {}", userData);

        response = given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(userData)
                .when()
                .post("api/register")
                .then()
                .extract().response();
    }

    @Then("the response should contain token")
    public void responseShouldContainToken() {
        System.out.println("Response: " + response.asString());

        String error = response.jsonPath().get("error");
        if (error != null) {
            Assertions.fail("Request failed: " + error);
        }

        String token = response.jsonPath().get("token");
        Assertions.assertNotNull(token, "Token is null – user may not be registered.");
    }


}
