package steps.api.auth;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static specs.Specs.defaultLoggingRequestSpec;

public class GetTokenAuth {


    @Step("Получение токена  авторизации")
    public static String getTokenAuth() {
        Response response = given(defaultLoggingRequestSpec).get("https://www.chitai-gorod.ru/");
        return response.getCookies().get("access-token").replace("%20", " ");
    }
}
