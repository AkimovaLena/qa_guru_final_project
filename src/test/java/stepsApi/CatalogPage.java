package stepsApi;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import tests.web.TestBase;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static specs.Specs.requestSpec;

public class CatalogPage extends TestBase {

    @Step("Получение каталога")
    public Response getCatalog(String token, Map<String,String> queryParams) {
        Response response = given(requestSpec)
                .header("authorization", token)
                .queryParams(queryParams)
                .get("/api/v2/products/");
        return response;
    }
}
