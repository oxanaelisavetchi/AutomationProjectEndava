package com.automation.project.steps;

import com.automation.project.asserts.CustomAssert;
import com.automation.project.configuration.ConfigurationProperties;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Slf4j
public class RestApiSteps {

    private Response response;
    private final static String baseUrl = "https://reqres.in/api/";

    private final String restApiUrl = ConfigurationProperties.getConfigPropertyValue("rest.api.url");

    @Given("availability of the test site")
    public void checkTheAvailabilityOfTheTestSite() {
        checkResponse("Site is available ",
                given().baseUri(restApiUrl).when().get().then().extract().response().statusCode(), 200);
    }

    @When("run request {string}")
    public void runRequestUrl(String url) {
        response = given()
                .baseUri(restApiUrl).when().get(url).then().extract().response();
    }

    @When("get status code {int}, {string}")
    public void getStatusCodeOnUrlWithResponseResponseTestMessageAndDataValue(int resp, String message) {
        checkResponse(message, response.statusCode(), resp);
    }

    @Then("get response {string}, {int}, {string}")
    public void getRequestDataValue(String data, int value, String message) {
        if (response.statusCode() == 200) {
            CustomAssert.assertThat(message, response.getBody().jsonPath().get(data), is(value));
        }
    }

    @When("run request {string} with {string} and {string}")
    public void createFunctionality(String url, String field, String value) {
        response = runPostRequest(getJsonObject(field, value), url);
    }

    @When("status code {int}, {string}")
    public void postStatusCode(int statusCode, String message) {
        CustomAssert.assertThat(message, response.statusCode(), is(statusCode));
    }

    @Then("post response {string}, {string}")
    public void postContent(String message, String resp) {
        if (!resp.isEmpty() && response.statusCode() < 400) {
            CustomAssert.assertThat(message, response.getBody().jsonPath().get("token"), is(resp));
        }
    }

    @When("run put request {string} with {string} and {string}")
    public void runPutRequestUrlWithDataAndValue(String url, String field, String value) {
        response = runPutRequest(getJsonObject(field, value), url);
    }

    @Then("response {string}")
    public void putContent(String message) {
        CustomAssert.assertThat(message, response.getBody().jsonPath().get("updatedAt"), notNullValue());
    }

    @When("run patch request {string} with {string} and {string}")
    public void runPatchRequestUrlWithDataAndValue(String url, String field, String value) {
        response = runPatchRequest(getJsonObject(field, value), url);
    }

    @When("delete functionality {string}")
    public void deleteFunctionality(String url) {
        response = given()
                .delete(url)
                .then().extract().response();
    }


    private static Response runPostRequest(JSONObject data, String url) {
        return given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post(baseUrl + url)
                .then()
                .header("Content-Type", "application/json; charset=utf-8").extract().response();
    }

    private static Response runPutRequest(JSONObject data, String url) {
        return given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .put(url)
                .then()
                .header("Content-Type", "application/json; charset=utf-8").extract().response();
    }

    private static Response runPatchRequest(JSONObject data, String url) {
        return given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .patch(url)
                .then()
                .header("Content-Type", "application/json; charset=utf-8").extract().response();
    }

    private void checkResponse(String msg, Object response, Object result) {
        CustomAssert.assertThat(msg, response, is(result));
    }

    private static JSONObject getJsonObject(String field, String value) {
        JSONObject data = new JSONObject();

        if (field.contains(",")) {

            String[] dataNames = field.split(",", 2);
            String[] val = value.split(",", 2);

            for (int i = 0; i < dataNames.length; i++) {
                data.put(dataNames[i], val[i]);
            }
        } else {
            data.put(field, value);
        }
        return data;
    }

}
