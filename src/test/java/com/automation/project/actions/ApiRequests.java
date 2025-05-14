
package com.automation.project.actions;

import com.automation.project.asserts.CustomAssert;
import com.automation.project.configuration.ConfigurationProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ApiRequests {

    private static final String baseUrl = ConfigurationProperties.getConfigPropertyValue("rest.api.url");

    public static Response sendGetRequest(String path) {
        return given().baseUri(baseUrl).when().get(collectUrl(path)).then().extract().response();
    }

    public static Response sendPostRequest(JSONObject data, String path) {
        return given().baseUri(baseUrl).contentType(ContentType.JSON).body(data.toString()).when().post(collectUrl(path)).then().extract().response();
    }

    public static Response sendPutRequest(JSONObject data, String path) {
        return given().baseUri(baseUrl).contentType(ContentType.JSON).body(data.toString()).when().put(collectUrl(path)).then().extract().response();
    }

    public static Response sendPatchRequest(JSONObject data, String path) {
        return given().baseUri(baseUrl).contentType(ContentType.JSON).body(data.toString()).when().patch(collectUrl(path)).then().extract().response();
    }

    public static Response sendDeleteRequest(String path) {
        return given().baseUri(baseUrl).when().delete(collectUrl(path)).then().extract().response();
    }


    public static JSONObject getJsonObject(String field, String value) {
        JSONObject data = new JSONObject();
        String[] fields = field.split(",");
        String[] values = value.split(",");
        for (int i = 0; i < fields.length; i++) {
            data.put(fields[i], values[i]);
        }
        return data;
    }

    public static String collectUrl(String path) {
        return baseUrl + "api/" + path;
    }
}
