package steps.auth;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static io.restassured.internal.assertion.CookieMatcher.getCookies;
import static specs.Specs.requestSpec;
import static specs.Specs.responseSpec200;

public class GetTokenAuth {


    public final static String TEMP_TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
            ".eyJleHAiOjE3MDkyMjUwMTQsImlhdCI6MTcwOTA1NzAxNCwiaXNzIjoiL2FwaS92MS9hdX" +
            "RoL2Fub255bW91cyIsInN1YiI6ImI1ZDM3ZDM1ZTg5NjQyYzIxOGYyZDYwYjU4NjU2YmI4M" +
            "2JiMjZhZGRlOGRmYjRiODRlNTEyOTA0YjZkOTRkMGEiLCJ0eXBlIjoxMH0" +
            ".vhVqgbYfleN9fOYXsgwQETcy09I6r0laSijT6rSyLCA";


   @Step("Получение токена  авторизации")
    public  static String  getTokenAuth() {
        Response response = given(requestSpec).get("https://www.chitai-gorod.ru/");
        String token = response.getCookies().get("access-token");
        token = token.replace("%20", " ");
        return token;

    }
}
