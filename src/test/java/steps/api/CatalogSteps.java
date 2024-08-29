package steps.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import tests.web.TestBaseUI;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static specs.Specs.defaultLoggingRequestSpec ;

public class CatalogSteps extends TestBaseUI {

    @Step("Получение каталога")
    public Response getCatalog(String token, Map<String, String> queryParams) {
        return given(defaultLoggingRequestSpec )
                .header("authorization", token)
                .queryParams(queryParams)
                .get("/api/v2/products/");
    }
}
