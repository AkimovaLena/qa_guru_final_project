package tests.api;

import io.restassured.response.Response;
import models.ResponseCatalog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import stepsApi.CatalogPage;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static specs.Specs.*;
import static stepsApi.auth.GetTokenAuth.getTokenAuth;

@Tags({@Tag("api"), @Tag("catalog")})
@DisplayName("Каталог API")
public class CatalogTest extends TestBase{
    CatalogPage catalogPage = new CatalogPage();

    @Test
    @DisplayName("Успешное получение каталога")
    void getCatalogTest() {
        ResponseCatalog responseCatalog = new ResponseCatalog();
        String token = getTokenAuth();
        Map<String, String> queryParams = new HashMap<>();
        Response response = catalogPage.getCatalog( token, queryParams);
        responseCatalog = response.then().spec(responseSpec200).extract().as(ResponseCatalog.class);
    }

    @Test
    @DisplayName("Проверка пагинации каталога")
    void getCatalogWithPaginationTest() {
        ResponseCatalog responseCatalog1 = new ResponseCatalog();
        String token = getTokenAuth();
        Map<String, String> queryParams1 = new HashMap<>();
        queryParams1.put("products[page]", "1");
        queryParams1.put("products[per-page]", "48");
        Response response1 = catalogPage.getCatalog( token, queryParams1);
        responseCatalog1 = response1.then().spec(responseSpec200).extract().as(ResponseCatalog.class);
        assertEquals(responseCatalog1.getMeta().getPagination().getCurrent_page(), 1);
        assertEquals(responseCatalog1.getMeta().getPagination().getCount(), 48);
        assertEquals(responseCatalog1.getMeta().getPagination().getTotal_pages(),(int) Math.ceil((double)
                responseCatalog1.getMeta().getPagination().getTotal()/48));
        Map<String, String> queryParams2 = new HashMap<>();
        queryParams1.put("products[page]", "2");
        queryParams1.put("products[per-page]", "48");
        Response response2 = catalogPage.getCatalog( token, queryParams1);
        ResponseCatalog responseCatalog2 = new ResponseCatalog();
        responseCatalog2 = response2.then().spec(responseSpec200).extract().as(ResponseCatalog.class);
        assertEquals(responseCatalog2.getMeta().getPagination().getCurrent_page(), 2);
        assertEquals(responseCatalog2.getMeta().getPagination().getCount(), 48);
        assertEquals(responseCatalog2.getMeta().getPagination().getTotal_pages(),(int) Math.ceil((double)
                responseCatalog2.getMeta().getPagination().getTotal()/48));
        assertNotSame(responseCatalog2, responseCatalog1);
    }
}
