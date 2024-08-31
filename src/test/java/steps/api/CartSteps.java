package steps.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.RequestProduct;

import static io.restassured.RestAssured.given;
import static specs.Specs.defaultLoggingRequestSpec;

public class CartSteps {

    @Step("Добавление книги с ID={0} в корзину, с токеном {1}")
    public Response addBook(Integer bookId, String token) {
        RequestProduct request = new RequestProduct();
        request.setId(bookId);
        return given(defaultLoggingRequestSpec)
                .header("authorization", token)
                .body(request)
                .post("/api/v1/cart/product");
    }

    @Step("Очищение корзины")
    public Response deleteAllBooks(String token) {
        return given(defaultLoggingRequestSpec)
                .header("authorization", token)
                .delete("/api/v1/cart");
    }

    @Step("Удаление книги из корзины корзиныс Id {0}")
    public Response deleteBook(Integer bookId, String token) {
        return given(defaultLoggingRequestSpec)
                .header("authorization", token)
                .delete("/api/v1/cart/product/{bookId}", bookId);
    }

    @Step("Получение данных корзины(краткое)")
    public Response getShortCart(String token) {
        return given(defaultLoggingRequestSpec)
                .header("authorization", token)
                .get("/api/v1/cart/short");
    }

    @Step("Получение данных корзины(полное)")
    public Response getCart(String token) {
        return given(defaultLoggingRequestSpec)
                .header("authorization", token)
                .get("/api/v1/cart");
    }


}
