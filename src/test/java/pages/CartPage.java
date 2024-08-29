package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CartPage {
    private final SelenideElement buttonDelete = $(".cart-item__actions-button--delete");
    private final SelenideElement cartContent = $(".cart-item__content");

    final ElementsCollection productInCarts = $$(".products__items");


    @Step("Очищаем корзину")
    public CartPage deleteAllBooksInCart() {
        buttonDelete.click();
        return this;
    }

    @Step("Проверяем, что колличество товаров в корзине равно {0}")
    public CartPage checkQuantityCart(int value) {
        Assertions.assertEquals(productInCarts.size(), value);
        return this;
    }

    @Step("Проверяем, что колличество товаров в корзине равно {0}")
    public CartPage checkDeleteCart() {
        cartContent.shouldHave(text("Удалили товар из корзины."));
        return this;
    }

    @Step("Увеличиваем колличество товара")
    public CartPage incBookInCart() {
        productInCarts.first().$(byText("+")).click();
        sleep(3000);
        return this;
    }


}
