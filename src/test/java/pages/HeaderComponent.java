package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HeaderComponent {

    private final SelenideElement changeCityPopup = $(".change-city-container__popup-confirmation"),
            changeCityButtonCancel = changeCityPopup.$(".change-city__button--cancel"),
            headerCity = $(".header-city__title"),
            cityModal = $(".city-modal__content"),
            cityModalTitle = $(".city-modal__content h1"),
            cities = $(".city-modal__popular"),
            catalogButton = $(".catalog__button"),
            categories = $(".categories-menu"),
            headerCart = $(".header-cart__badge");


    @Step("Проверяем, что в заголовке город {0}")
    public HeaderComponent checkCity(String cityName) {
        headerCity.shouldHave(text("Россия, " + cityName));
        return this;
    }

    @Step("Открываем поп-ап изменения города")
    public HeaderComponent openPopupChangeCity() {
        headerCity.click();
        changeCityPopup.shouldBe(Condition.visible);
        changeCityButtonCancel.click();
        cityModal.shouldBe(Condition.visible);
        cityModalTitle.shouldHave(text("Выберите город"));
        return this;
    }

    @Step("Выбираем город {0}")
    public HeaderComponent selectCity(String cityName) {
        cities.$(byText(cityName)).click();
        sleep(2000); //задержка чтобы успел измениться город
        return this;
    }

    @Step("Открывает меню каталога")
    public HeaderComponent openCatalogMenu() {
        catalogButton.click();
        categories.shouldBe(Condition.visible);
        return this;
    }


    @Step("Проверяем, что в хедере счетчик корзины равен {0}")
    public HeaderComponent checkCountInCart(int value) {
        Assertions.assertEquals(Integer.toString(value), headerCart.text());
        return this;
    }

    @Step("Переходим в корзину (через хедер)")
    public void clickCart() {
        headerCart.click();
        sleep(1000);
    }


}
