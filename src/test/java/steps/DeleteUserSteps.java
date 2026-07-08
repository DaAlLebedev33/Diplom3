package steps;

import apistellarburgers.LoginUser;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static constants.Url.DELETE_USER;
import static constants.Url.LOGIN_USER;
import static io.restassured.RestAssured.given;

public class DeleteUserSteps {

    @Step("Логин пользователя")
    public Response loginUser(String email, String password) {
        LoginUser loginUser = new LoginUser(email, password);
        return given()
                .header("Content-type", "application/json")
                .body(loginUser)
                .post(LOGIN_USER);
    }

    @Step("Удаление пользователя")
    public Response deleteUser(String accessToken) {
        return RestAssured.given()
                .header("Authorization", accessToken)
                .delete(DELETE_USER);
    }

    @Step("Логин и удаление пользователя по email и password")
    public Response loginAndDeleteUser(String email, String password) {

        Response loginResponse = loginUser(email, password);
        String accessToken = loginResponse.jsonPath().getString("accessToken");

        if (accessToken != null && !accessToken.isEmpty()) {
            return deleteUser(accessToken);
        }
        return loginResponse;
    }
}
