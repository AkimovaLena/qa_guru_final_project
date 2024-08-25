package tests.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CartPage;
import pages.MainPage;

@Tags({@Tag("ui"), @Tag("cart")})
@DisplayName("Корзина")
public class CartTest extends TestBase {
    private static final Logger log = LoggerFactory.getLogger(MainPageTest.class);
    MainPage mainPage = new MainPage();
    CartPage cartPage = new CartPage();


    @Test
    @DisplayName("Очищение корзины")
    void deleteBooksTest() {
        mainPage.openMain()
                .addAnyBook()
                .clickCart();
        cartPage.checkQuantityCart(1)
                .deleteAllBooksInCart()
                .checkDeleteCart();
    }

    @Test
    @DisplayName("Увиличение колличества товара на 1")
    void incBooksInCartTest() {
        mainPage.openMain()
                .addAnyBook()
                .clickCart();
        cartPage.checkQuantityCart(1)
                .incBookInCart()
                .checkQuantityCart(1);
        mainPage.checkCountInCart(2);
    }


}
