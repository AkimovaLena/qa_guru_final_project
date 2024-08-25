package tests.api;

import io.restassured.response.Response;
import models.ResponseCart;
import models.ResponseError;
import models.ResponseShortCart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import stepsApi.CartPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static specs.Specs.*;
import static stepsApi.auth.GetTokenAuth.getTokenAuth;


@Tags({@Tag("api"), @Tag("cart")})
@DisplayName("Корзина API")
public class CartTest extends TestBase {
    CartPage cartPage = new CartPage();

    @Test
    @DisplayName("Успешное добавление книги в корзину")
    void addBookTest() {
        String token = getTokenAuth();
        Response response = cartPage.addBook(3043215, token);
        response.then().spec(responseSpec200);
    }

    @Test
    @DisplayName("Получение ошибки авторизации при отправке запроса без валидного токена")
    void addBookErrorUnauthorizedTest() {
        String token = getTokenAuth();
        Response response = cartPage.addBook(3043215, "");
        response.then().spec(responseSpec401);
    }

    @Test
    @DisplayName("Добавление несуществующей книги в корзину")
    void addBookNotFoundTest() {
        String token = getTokenAuth();
        ResponseError responseError = new ResponseError();
        Response response = cartPage.addBook(304320000, token);
        responseError = response.then().spec(responseSpec500).extract().as(ResponseError.class);
        assertEquals(responseError.getMessage(), "данного товара не существует");
    }


    @Test
    @DisplayName("Проверка ощищения корзины")
    void deteleAllBooksInCartTest() {
        String token = getTokenAuth();
        ResponseShortCart responseShortCart = new ResponseShortCart();
        Response response = cartPage.addBook(3043215, token);
        response.then().spec(responseSpec200);
        responseShortCart = cartPage.getShortCart(token).then().extract().as(ResponseShortCart.class);
        assertTrue(responseShortCart.getData().getQuantity()>0);
        response=cartPage.deleteAllBooks(token);
        response.then().spec(responseSpec204);
        responseShortCart = cartPage.getShortCart(token).then().extract().as(ResponseShortCart.class);
        assertTrue(responseShortCart.getData().getQuantity()==0);
    }

    @Test
    @DisplayName("Проверка удаления книги из корзины")
    void deteleBookInCartTest() {
        String token = getTokenAuth();
        ResponseCart responseCart = new ResponseCart();
        Response response = cartPage.addBook(3043215, token);
        response.then().spec(responseSpec200);
        response = cartPage.getCart(token);
        response.then().spec(responseSpec200);
        responseCart= response.then().extract().as(ResponseCart.class);
        assertEquals(responseCart.getProducts().get(0).getGoodsId(),3043215);
        response=cartPage.deleteBook(responseCart.getProducts().get(0).getId(),token);
        response.then().spec(responseSpec204);
        responseCart = cartPage.getCart(token).then().extract().as(ResponseCart.class);
        assertTrue(responseCart.getProducts().isEmpty());
    }

    @Test
    @DisplayName("Проверка удаления книги из корзины")
    void deteleBookInCartNotFTest() {
        String token = getTokenAuth();
        Response  response =cartPage.deleteBook(734564498,token);
        ResponseError responseError = response.then().spec(responseSpec404).extract().as(ResponseError.class);
        assertEquals(responseError.getMessage(), "товар в корзине не найден");


    }


}
