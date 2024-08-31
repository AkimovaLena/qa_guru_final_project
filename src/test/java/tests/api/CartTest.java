package tests.api;

import io.restassured.response.Response;
import models.ResponseCart;
import models.ResponseError;
import models.ResponseShortCart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import steps.api.CartSteps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static specs.Specs.defaultLoggingResponseSpec;
import static steps.api.auth.GetTokenAuth.getTokenAuth;


@Tags({@Tag("api"), @Tag("cart")})
@DisplayName("Корзина API")
public class CartTest extends TestBaseAPI {
    final CartSteps cartPage = new CartSteps();

    @Test
    @DisplayName("Успешное добавление книги в корзину")
    void addBookTest() {
        String token = getTokenAuth();
        Response response = cartPage.addBook(3043215, token);
        response.then().statusCode(200).spec(defaultLoggingResponseSpec);
    }

    @Test
    @DisplayName("Получение ошибки авторизации при отправке запроса без валидного токена")
    void addBookErrorUnauthorizedTest() {
        Response response = cartPage.addBook(3043215, "");
        response.then().statusCode(401).spec(defaultLoggingResponseSpec);
    }

    @Test
    @DisplayName("Добавление несуществующей книги в корзину")
    void addBookNotFoundTest() {
        String token = getTokenAuth();
        Response response = cartPage.addBook(304320000, token);
        ResponseError responseError = response.then().statusCode(500).spec(defaultLoggingResponseSpec).extract().as(ResponseError.class);
        assertEquals(responseError.getMessage(), "данного товара не существует");
    }


    @Test
    @DisplayName("Проверка очищения корзины")
    void deleteAllBooksInCartTest() {
        String token = getTokenAuth();
        Response response = cartPage.addBook(3043215, token);
        response.then().statusCode(200).spec(defaultLoggingResponseSpec);
        ResponseShortCart responseShortCart = cartPage.getShortCart(token).then().extract().as(ResponseShortCart.class);
        assertTrue(responseShortCart.getData().getQuantity() > 0);
        response = cartPage.deleteAllBooks(token);
        response.then().statusCode(204).spec(defaultLoggingResponseSpec);
        responseShortCart = cartPage.getShortCart(token).then().extract().as(ResponseShortCart.class);
        assertEquals(0, (int) responseShortCart.getData().getQuantity());
    }

    @Test
    @DisplayName("Проверка удаления книги из корзины")
    void deleteBookInCartTest() {
        String token = getTokenAuth();
        Response response = cartPage.addBook(3043215, token);
        response.then().statusCode(200).spec(defaultLoggingResponseSpec);
        response = cartPage.getCart(token);
        response.then().statusCode(200).spec(defaultLoggingResponseSpec);
        ResponseCart responseCart = response.then().extract().as(ResponseCart.class);
        assertEquals(responseCart.getProducts().get(0).getGoodsId(), 3043215);
        response = cartPage.deleteBook(responseCart.getProducts().get(0).getId(), token);
        response.then().statusCode(204).spec(defaultLoggingResponseSpec);
        responseCart = cartPage.getCart(token).then().extract().as(ResponseCart.class);
        assertTrue(responseCart.getProducts().isEmpty());
    }

    @Test
    @DisplayName("Проверка удаления книги из корзины")
    void deleteBookInCartNotFTest() {
        String token = getTokenAuth();
        Response response = cartPage.deleteBook(734564498, token);
        ResponseError responseError = response.then().statusCode(404).spec(defaultLoggingResponseSpec).extract().as(ResponseError.class);
        assertEquals(responseError.getMessage(), "товар в корзине не найден");


    }


}
