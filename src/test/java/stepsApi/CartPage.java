package stepsApi;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.RequestProduct;

import static io.restassured.RestAssured.given;
import static specs.Specs.requestSpec;

public class CartPage {

    @Step("Добавление книги с ID={0} в корзину, с токеном {1}")
    public Response addBook(Integer bookId, String token) {
        RequestProduct request = new RequestProduct();
        request.setId(bookId);
        Response response = given(requestSpec)
                .header("authorization", token)
                .body(request)
                .post("/api/v1/cart/product");
        return response;
    }

    @Step("Очищение корзины")
    public Response deleteAllBooks(String token) {
        Response response = given(requestSpec)
                .header("authorization", token)
                .delete("/api/v1/cart");
        return response;
    }

    @Step("Удаление книги из корзины корзиныс Id {0}")
    public Response deleteBook(Integer bookId,String  token) {
        Response response = given(requestSpec)
                .header("authorization", token)
                .delete("/api/v1/cart/product/{bookId}",bookId);
        return response;
    }

    @Step("Получение данных корзины(краткое)")
    public Response getShortCart(String token) {
        Response response = given(requestSpec)
                .header("authorization", token)
                .get("/api/v1/cart/short");
        return response;
    }

    @Step("Получение данных корзины(полное)")
    public Response getCart(String token) {
        Response response = given(requestSpec)
                .header("authorization", token)
                .get("/api/v1/cart");
        return response;
    }


}
