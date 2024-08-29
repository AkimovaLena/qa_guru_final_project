package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CartPage {
    private final SelenideElement buttonDelete = $(".cart-item__actions-button--delete"),
            cartContent = $(".cart-item__content");

    ElementsCollection productInCarts = $$(".products__items"),
            dataCart = $(".cart-content").$$(".cart-item");


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

    @Step("Получаем список всех книг в корзине")
    public List<BookData> getBooksData() {

        List<BookData> booksCart = new java.util.ArrayList<>(List.of());
        for (int i = 0; i < dataCart.size(); i++) {
            BookData tmp = new BookData();
            tmp.setTitle(dataCart.get(i).$(".product-title__head").text());
            tmp.setAuthor(dataCart.get(i).$(".product-title__author").text());
            booksCart.add(tmp);
        }
        return booksCart;
    }

    @Step("Получаем список всех книг в корзине")
    public CartPage checkListsBooksMatches(List<BookData> list1, List<BookData> list2) {
        assertThat(list1, Matchers.containsInAnyOrder(list2.toArray()));
        return this;
    }


}
