package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class MainPage {

    private final SelenideElement changeCityPopup = $(".change-city-container__popup-confirmation"),
            changeCityButtonAccept = changeCityPopup.$(".change-city__button--accept"),
            footer = $(".app-footer__container");

    final ElementsCollection categoriesColumn = $$(".categories-menu__column");
    final ElementsCollection slider = $$(".card-slider__header");


    @Step("Открываем главную страницу")
    public MainPage openMain() {
        open("");
        changeCityButtonAccept.click();
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
    public MainPage scrollToFooter() {
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
        return slider.filterBy(text(title)).first().parent().parent().parent().$$(".slider__item");
    }

    @Step("Проверяем, что текст кнопки добавления в корзину равен {0}")
    public MainPage checkButtonName(SelenideElement element, String Name) {
        element.$(".action-button").shouldHave(text(Name));
        return this;
    }

    @Step("Добавляем товар в корзину и запоминаем название книги и автора")
    public BookData addElementInCart(SelenideElement element) {
        BookData bookData = new BookData();
        bookData.title = element.$(".product-title__head").text();
        bookData.author = element.$(".product-title__author").text();
        element.$(".action-button").click();
        return bookData;
    }


    @Step("Добавляем любой товар в корзину")
    public MainPage addAnyBook() {
        slider.first().scrollTo();
        sleep(1000);
        addElementInCart($("body"));
        checkButtonName($("body"), "Оформить");
        return this;
    }
}
