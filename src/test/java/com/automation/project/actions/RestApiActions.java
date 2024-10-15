package com.automation.project.actions;

import com.automation.project.asserts.CustomAssert;
import com.automation.project.configuration.ConfigurationProperties;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class RestApiActions {

    private static final String baseUrl = ConfigurationProperties.getConfigPropertyValue("rest.api.url");

    public static Response runPostRequest(JSONObject data, String path) {
        return given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post(collectUrl(path))
                .then()
                .header("Content-Type", "application/json; charset=utf-8").extract().response();
    }

    public static Response runPutRequest(JSONObject data, String path) {
        return given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .put(collectUrl(path))
                .then()
                .header("Content-Type", "application/json; charset=utf-8").extract().response();
    }

    public static Response runPatchRequest(JSONObject data, String path) {
        return given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .patch(collectUrl(path))
                .then()
                .header("Content-Type", "application/json; charset=utf-8").extract().response();
    }

    public static void checkResponse(String msg, Object response, Object result) {
        CustomAssert.assertThat(msg, response, is(result));
    }

    public static JSONObject getJsonObject(String field, String value) {
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

    public static String collectUrl(String path) {
        return baseUrl + "api/" + path;
    }
}
