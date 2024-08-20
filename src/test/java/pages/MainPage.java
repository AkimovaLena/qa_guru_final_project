package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class MainPage {

    private final SelenideElement changeCityPopup = $(".change-city-container__popup-confirmation"),
            changeCityButtonAccept = changeCityPopup.$(".change-city__button--accept"),
            changeCityButtonCancel = changeCityPopup.$(".change-city__button--cancel"),
            headerCity = $(".header-city__title"),
            cityModal = $(".city-modal__content"),
            cityModalTitle = $(".city-modal__content h1"),
            cities = $(".city-modal__popular"),
            catalogButton = $(".catalog__button"),
            categories = $(".categories-menu"),
            footer = $(".app-footer__container"),
            headerCart = $(".header-cart__badge");

    ElementsCollection categoriesColumn = $$(".categories-menu__column"),
            slider = $$(".card-slider__header");


    @Step("Открываем главную страницу")
    public MainPage openMain() {
        open("");
        changeCityButtonAccept.click();
        return this;
    }

    @Step("Проверяем, что в заголовке город {0}")
    public MainPage checkCity(String cityName) {
        headerCity.shouldHave(text("Россия, " + cityName));
        return this;
    }

    @Step("Открываем поп-ап изменения города")
    public MainPage openPopupChangeCity() {
        headerCity.click();
        changeCityPopup.shouldBe(Condition.visible);
        changeCityButtonCancel.click();
        cityModal.shouldBe(Condition.visible);
        cityModalTitle.shouldHave(text("Выберите город"));
        return this;
    }

    @Step("Выбираем город {0}")
    public MainPage selectCity(String cityName) {
        cities.$(byText(cityName)).click();
        sleep(2000); //задержка чтобы успел измениться город
        return this;
    }

    @Step("Открывает меню каталога")
    public MainPage openCatalogMenu() {
        catalogButton.click();
        categories.shouldBe(Condition.visible);
        return this;
    }

    @Step("Выбираем каталог {0}")
    public MainPage selectCatalog(String catalog) {
        categoriesColumn.first().$(byText(catalog)).click();
        return this;
    }

    @Step("Выбираем подкаталог {0}")
    public MainPage selectSubdirectory(String subdirectory) {
        categoriesColumn.get(1).$(byText(subdirectory)).click();
        return this;
    }

    @Step("Прогружаем всю страницу")
    public MainPage loadPage() {
        footer.scrollTo();
        return this;
    }

    @Step("Скролл до {0}")
    public MainPage scrollToSlider(String title) {
        slider.filterBy(text(title)).first().scrollTo();
        WebDriver driver = getWebDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        sleep(1000); // задержка чтобы скрол успел отработать
        js.executeScript("window.scrollBy(0,-100)"); //скролл вверх на ширину прилипающего хедера
        return this;
    }

    @Step("Открываем подборку {0}")
    public MainPage openSlider(String title) {
        slider.filterBy(text(title)).first().parent().parent().$(".card-slider__link-text").click();
        return this;
    }

    @Step("Получаем набор карточек товарта в подбоке {0}")
    public ElementsCollection getCardsSlider(String title) {
        ElementsCollection sliders = slider.filterBy(text(title)).first().parent().parent().parent().$$(".slider__item");
        return sliders;
    }

    @Step("Проверяем, что текст кнопки добавления в корзину равен {0}")
    public MainPage checkButtonName(SelenideElement element, String Name) {
        element.$(".action-button").shouldHave(text(Name));
        return this;
    }

    @Step("Добавляем товар в корзину")
    public MainPage addElementInCart(SelenideElement element) {
        element.$(".action-button").click();
        return this;
    }

    @Step("Проверяем, что в хедере счетчик корзины равен {0}")
    public MainPage checkCountInCart(int value) {
        Assertions.assertEquals(Integer.toString(value), headerCart.text());
        return this;
    }

    @Step("Переходим в корзину (черех хедер)")
    public void clickCart() {
        headerCart.click();
        sleep(1000);
    }

    @Step("Добавляем любой товар в корзину")
    public MainPage addAnyBook() {
        slider.first().scrollTo();
        sleep(1000);
        addElementInCart($("body"));
        checkButtonName($("body"),"Оформить");
        checkCountInCart(1);
        return this;
    }
}
