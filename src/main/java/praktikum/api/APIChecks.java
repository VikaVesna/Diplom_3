package praktikum.api;

import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.qameta.allure.model.Parameter.Mode.HIDDEN;
import static io.restassured.RestAssured.given;
import static praktikum.config.EnvConfig.BASE_URL;

public class APIChecks {

    @Step("Создание нового пользователя")
    public ValidatableResponse createUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(user)
                .when()
                .post("/api/auth/register")
                .then().log().all();
    }


    @Step("Авторизация пользователя")
    public ValidatableResponse logIn(Auth login) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(login)
                .when()
                .post("/api/auth/login")
                .then().log().all();
    }


    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(@Param(mode=HIDDEN)String accessToken) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user")
                .then().log().all();
    }

}