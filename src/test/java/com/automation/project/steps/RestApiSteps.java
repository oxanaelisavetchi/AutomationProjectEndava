package com.automation.project.steps;

import com.automation.project.asserts.CustomAssert;
import com.automation.project.configuration.ConfigurationProperties;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static com.automation.project.actions.RestApiActions.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Slf4j
public class RestApiSteps {

    private Response response;

    private static final String baseUrl = ConfigurationProperties.getConfigPropertyValue("rest.api.url");

    @Given("availability of the test site")
    public void availabilityOfTheTestSite() {
        checkResponse("Site is available ", given().baseUri(baseUrl).when().get().then().extract().response().statusCode(), 200);
    }

    @When("run request {string}")
    public void runRequestUrl(String url) {
        response = given().baseUri(baseUrl).when().get(collectUrl(url)).then().extract().response();
    }

    @And("check get status code {int}, {string}")
    public void checkGetStatusCode(int resp, String message) {
        checkResponse(message, response.statusCode(), resp);
    }

    @Then("check get response {string}, {int}, {string}")
    public void checkGetResponse(String data, int value, String message) throws JsonProcessingException {
        if (response.statusCode() == 200) {
            CustomAssert.assertThat(message, response.getBody().jsonPath().get(data), is(value));
        }
    }

    @When("run request {string} with {string} and {string}")
    public void runRequest(String url, String field, String value) {
        response = runPostRequest(getJsonObject(field, value), url);
    }

    @And("check status code {int}, {string}")
    public void checkStatusCode(int statusCode, String message) {
        CustomAssert.assertThat(message, response.statusCode(), is(statusCode));
    }

    @Then("check post response {string}, {string}")
    public void checkPostResponse(String message, String resp) {
        if (!resp.isEmpty() && response.statusCode() < 400) {
            CustomAssert.assertThat(message, response.getBody().jsonPath().get("token"), is(resp));
        }
    }

    @When("run put request {string} with {string} and {string}")
    public void runPutRequests(String url, String field, String value) {
        response = runPutRequest(getJsonObject(field, value), url);
    }

    @Then("check response {string}")
    public void checkResponses(String message) {
        CustomAssert.assertThat(message, response.getBody().jsonPath().get("updatedAt"), notNullValue());
    }

    @When("run patch request {string} with {string} and {string}")
    public void runPatchRequestUrlWithDataAndValue(String url, String field, String value) {
        response = runPatchRequest(getJsonObject(field, value), url);
    }

    @When("run delete request {string}")
    public void deleteFunctionality(String path) {
        response = given()
                .delete(collectUrl(path))
                .then().extract().response();
    }

}
