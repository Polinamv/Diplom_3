package apiData;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static apiData.EndPoints.*;
import static apiData.RequestData.getRequestSpec;
import static io.restassured.RestAssured.given;

public class UserMethods {

    @Step("Создание нового пользователя")
    public ValidatableResponse create(UserRequestBody user) {
        return given()
                .spec(getRequestSpec())
                .body(user)
                .post(POST_CREATE_USER)
                .then();
    }

    @Step("Логин пользователя в системе")
    public ValidatableResponse login(UserCredentials credentials) {
        return given()
                .spec(getRequestSpec())
                .body(credentials)
                .post(POST_LOGIN)
                .then();
    }

    @Step("Удаление пользователя")
    public void delete(String headerValue) {
        given()
                .spec(getRequestSpec())
                .header("Authorization", headerValue)
                .when()
                .delete(DELETE_USER);
    }
}