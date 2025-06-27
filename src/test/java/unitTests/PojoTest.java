package unitTests;

import com.automation.project.configuration.ConfigurationProperties;
import com.automation.project.context.Specifications;
import com.automation.project.entity.*;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;

// the same here move to src/main
@Slf4j
@DisplayName("Api tests with Pojo classes")
@Feature("Api Pojo")
public class PojoTest {

    private static final String baseUrl = ConfigurationProperties.getConfigPropertyValue("rest.api.url");
    public static final String API_KEY = "x-api-key";
    public static final String API_KEY_VALUE = "reqres-free-v1";

    @Test
    @DisplayName("Avatars contains user id")
    public void checkAvatarContainsIdTest() {
        Specifications.installSpecification(Specifications.requestSpec(baseUrl), Specifications.responseSpecOK200());

        List<UserData> users = RestAssured.given()
                .when()
                .get("api/users?page=2")
                .then()
                .extract().body().jsonPath().getList("data", UserData.class);

        users.forEach(x -> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assertions.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));

        List<String> realPeopleAvatars = users.stream()
                .map(UserData::getAvatar)
                .toList();

        List<String> realPeopleIds = users.stream()
                .map(x -> x.getId().toString())
                .toList();

        IntStream.range(0, realPeopleAvatars.size()).mapToObj(i -> realPeopleAvatars.get(i).contains(realPeopleIds.get(i))).forEach(Assertions::assertTrue);
    }

    @Test
    @DisplayName("Successful registration")
    public void successUserRegTest() {
        Integer userId = 4;
        String userPassword = "QpwL5tke4Pnpja7X4";
        Specifications.installSpecification(Specifications.requestSpec(baseUrl), Specifications.responseSpecOK200());
        Register user = new Register("eve.holt@reqres.in", "pistol");
        SuccessUserReg successUserReg = RestAssured.given()
                .body(user).header(API_KEY, API_KEY_VALUE)
                .when()
                .post("api/register")
                .then()
                .extract().as(SuccessUserReg.class);
        Assertions.assertNotNull(successUserReg.getId());
        Assertions.assertNotNull(successUserReg.getToken());
        Assertions.assertEquals(userId, successUserReg.getId());
        Assertions.assertEquals(userPassword, successUserReg.getToken());
    }

    @Test
    @DisplayName("Unsuccessful registration")
    public void unSuccessUserRegTest() {
        Specifications.installSpecification(Specifications.requestSpec(baseUrl), Specifications.responseSpecError400());
        Register peopleSecond = new Register("sydney@fife", "");
        UnsuccessUserReg unSuccessUserReg = RestAssured.given()
                .body(peopleSecond).header(API_KEY, API_KEY_VALUE)
                .when()
                .post("/api/register")
                .then()
                .extract().as(UnsuccessUserReg.class);
        Assertions.assertNotNull(unSuccessUserReg.getError());
        Assertions.assertEquals("Missing password", unSuccessUserReg.getError());
    }

    @Test
    @DisplayName("Check years order")
    public void checkSortedYearsTest() {
        Specifications.installSpecification(Specifications.requestSpec(baseUrl), Specifications.responseSpecOK200());
        List<Data> data = RestAssured.given()
                .body("").header(API_KEY, API_KEY_VALUE)
                .when()
                .get("/api/unknown")
                .then()
                .extract().body().jsonPath().getList("data", Data.class);

        List<Integer> dataYears = data.stream().map(Data::getYear).collect(Collectors.toList());
        List<Integer> sortedDataYears = dataYears.stream().sorted().collect(Collectors.toList());
        Assertions.assertEquals(dataYears, sortedDataYears);

        log.info(dataYears.toString());
        log.info(sortedDataYears.toString());
    }

    @Test
    @DisplayName("Delete user")
    public void deleteUserTest() {
        Specifications.installSpecification(Specifications.requestSpec(baseUrl), Specifications.responseSpec(204));
        RestAssured.given().body("").header(API_KEY, API_KEY_VALUE).when().delete("/api/users/2").then();
    }

}
