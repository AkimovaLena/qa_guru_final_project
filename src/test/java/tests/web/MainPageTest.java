package tests.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class SimplTest extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(SimplTest.class);


    @ValueSource(strings = {
            "Екатеринбург",
            "Краснодар",
            "Казань",
            "Красноярск"
    })
    @ParameterizedTest(name = "Проверка изменения Российского города на {0}")
    void changeCityRussiaTest(String city) {
        open("");
        $(".change-city-container__popup-confirmation").$(".change-city__button--accept").click();
        $(".header-city__title").shouldHave(text("Россия, Москва"));
        $(".header-city__title").click();
        $(".change-city-container__popup-confirmation").shouldBe(Condition.visible);
        $(".change-city__button--cancel").click();
        $(".city-modal__content").shouldBe(Condition.visible);
        $(".city-modal__content h1").shouldHave(text("Выберите город"));
        $(".city-modal__popular").$(byText(city)).click();
        sleep(2000);
        $(".header-city__title").shouldHave(text("Россия, "+ city));
    }


    @CsvSource(value = {
            "Книги, Комиксы",
            "Книги, Манга",
            "Подарки и сувениры, Брелоки",
            "Творчество и хобби, Наклейки"
    })
    @ParameterizedTest(name = "Проверка каталога {0} и подраздела {1}")
    void catalogTest(String catalog, String level2) {
        open("");
        $(".change-city-container__popup-confirmation").$(".change-city__button--accept").click();
        $(".catalog__button").click();
        $(".categories-menu").shouldBe(Condition.visible);
        $$(".categories-menu__column").first().$(byText(catalog)).click();
        $$(".categories-menu__column").get(1).$(byText(level2)).click();
        $(".app-breadcrumbs__link").shouldHave(text(catalog));
        $(".app-catalog-page__title").shouldHave(text(level2));
    }

    @ValueSource(strings = {
            "Новинки литературы",
            "Лучшие из лучших",
            "Рюкзаки, ранцы и пеналы",
            "25 книг с летним настроением",
            "Эксклюзивно в «Читай-городе»",
            "Скоро в продаже",
            "Скетчбуки для ваших шедевров",
            "Дневники для пятёрок"
    })
    @ParameterizedTest(name = "Открытие каталога {0}")
    void listTest(String nameTitle) {
        open("");
        $(".change-city-container__popup-confirmation").$(".change-city__button--accept").click();
        $(".app-footer__container").scrollTo(); //для активации загрузки всех страницы
//        sleep(3000);// задержка чтобы скрол успел отработать
        WebDriver driver = getWebDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        $$(".card-slider__header").filterBy(text(nameTitle)).first().scrollTo();
        sleep(1000);// задержка чтобы скрол успел отработать
        js.executeScript("window.scrollBy(0,-100)");
        $$(".card-slider__header").filterBy(text(nameTitle)).first().parent().parent().$(".card-slider__link-text").click();
        $(".app-catalog-page__title").shouldHave(text(nameTitle));
    }


    @Test
    void addBooksTest() {
        open("");
        $(".change-city-container__popup-confirmation").$(".change-city__button--accept").click();
        $("[data-chg-slider-type=novelty]").scrollTo();
        ElementsCollection slider = $("[data-chg-slider-type=novelty]").$$(".slider__item");
        Assertions.assertTrue(slider.size() >= 6);
        for (int i = 0; i < 6; i++) {
            slider.get(i).$(".action-button").shouldHave(text("Купить")).click();
            slider.get(i).$(".action-button").shouldHave(text("Оформить"));
            Assertions.assertEquals(Integer.toString(i + 1), $(".header-cart__badge").text());
        }
    }

    @Test
    void deleteBooksTest() {
        open("");
        $(".change-city-container__popup-confirmation").$(".change-city__button--accept").click();
        $$(".card-slider__header").first().scrollTo();
        sleep(1000);
        $(".action-button").click();
        $(".action-button").shouldHave(text("Оформить"));
        $(".header-cart__badge").shouldHave(text("1"));
        $(".header-cart__badge").click();
        sleep(1000);
        Assertions.assertTrue($$(".products__items").size() == 1);
        $(".cart-item__actions-button--delete").click();
        $(".cart-item__content").shouldHave(text("Удалили товар из корзины."));
        $(".header-cart__badge").should(disappear);
    }

    @Test
    void incBooksInCartTest() {
        open("");
        $(".change-city-container__popup-confirmation").$(".change-city__button--accept").click();
        $$(".card-slider__header").first().scrollTo();
        sleep(1000);
        $(".action-button").click();
        $(".action-button").shouldHave(text("Оформить"));
        $(".header-cart__badge").shouldHave(text("1"));
        $(".header-cart__badge").click();
        sleep(1000);
        Assertions.assertTrue($$(".products__items").size() == 1);
        $$(".products__items").first().$(byText("+")).click();
//        sleep(1000);

        $(".header-cart__badge").shouldHave(text("2"));

    }

    @Test
    void vilterListingTest() {
        open("/catalog/books/komiksy-110063");
        $(".change-city-container__popup-confirmation").$(".change-city__button--accept").click();
        $(".catalog-template-filters").$(byText("Предзаказ")).click();
        $(".catalog-template-filters__list-item-checkbox").$("[name=Предзаказ]").sibling(0).$(".checkbox-native__icon").shouldHave(attribute("alt", "Отмечено"));
        $(".products-list").$$("article").stream().forEach(it -> it.$(".action-button__text").shouldHave(text("Предзаказ")));
    }


}
