package com.automation.project.actions;

import com.automation.project.asserts.CustomAssert;
import com.automation.project.configuration.ConfigurationProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

// RestApiActions -> ApiRequests
public class RestApiActions {

    private static final String baseUrl = ConfigurationProperties.getConfigPropertyValue("rest.api.url");

    // run -> send
    public static Response runGetRequest(String path) {
        return given()
                .baseUri(baseUrl)
                .when()
                .get(collectUrl(path))
                .then()
                .extract().response();
    }

    public static Response runPostRequest(JSONObject data, String path) {
        return given()
                .baseUri(baseUrl)
                // use rest-assured library for ContentType
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post(collectUrl(path))
                .then()
                .extract().response();
    }


    public static Response runPutRequest(JSONObject data, String path) {
        return given()
                .baseUri(baseUrl)
                // use rest-assured library for ContentType
                .contentType(ContentType.JSON)
                .body(data.toString())
                .when()
                .put(collectUrl(path))
                .then()
                .extract().response();
    }


    public static Response runPatchRequest(JSONObject data, String path) {
        return given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(data.toString())
                .when()
                .patch(collectUrl(path))
                .then()
                .extract().response();
    }


    // can move to some utils/ApiUtils
    public static void checkResponse(String msg, Object response, Object result) {
        CustomAssert.assertThat(msg, response, is(result));
    }

    // can move to some utils
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

    // can move to some utils
    public static String collectUrl(String path) {
        return baseUrl + "api/" + path;
    }
}
