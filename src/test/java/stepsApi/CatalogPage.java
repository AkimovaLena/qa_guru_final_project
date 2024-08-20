package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.RequestProduct;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static specs.Specs.requestSpec;

public class CatalogPage {

    @Step("Получение каталога")
    public Response getCatalog(String token, Map<String,String> queryParams) {
        Response response = given(requestSpec)
                .header("authorization", token)
                .queryParams(queryParams)
                .get("/api/v2/products/");
        return response;
    }
}
