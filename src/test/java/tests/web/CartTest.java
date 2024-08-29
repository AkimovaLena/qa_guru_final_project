package tests.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CartPage;
import pages.HeaderComponent;
import pages.MainPage;

@Tags({@Tag("ui"), @Tag("cart")})
@DisplayName("Корзина UI")
public class CartTest extends TestBaseUI {
    private static final Logger logger  = LoggerFactory.getLogger(MainPageTest.class);
    final MainPage mainPage = new MainPage();
    final CartPage cartPage = new CartPage();
    final HeaderComponent header = new HeaderComponent();


    @Test
    @DisplayName("Очищение корзины")
    void deleteBooksTest() {
        mainPage.openMain()
                .addAnyBook();
        header.checkCountInCart(1);
        header.clickCart();
        cartPage.checkQuantityCart(1)
                .deleteAllBooksInCart()
                .checkDeleteCart();
    }

    @Test
    @DisplayName("Увеличение колличества товара на 1")
    void incBooksInCartTest() {
        mainPage.openMain()
                .addAnyBook();
        header.checkCountInCart(1);
        header.clickCart();
        cartPage.checkQuantityCart(1)
                .incBookInCart()
                .checkQuantityCart(1);
        header.checkCountInCart(2);
    }


}
