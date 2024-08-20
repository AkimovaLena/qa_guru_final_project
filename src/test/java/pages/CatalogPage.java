package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CatalogPage {
    private final SelenideElement breadcrumbs = $(".app-breadcrumbs__link"),
            titlePage = $(".app-catalog-page__title"),
            changeCityButtonAccept = $(".change-city-container__popup-confirmation").$(".change-city__button--accept"),
            filters = $(".catalog-template-filters"),
            selectFilters = $(".catalog-template-filters__list-item-checkbox");

    ElementsCollection productCards = $(".products-list").$$("article");

    @Step("Проверяем, что хлебные крошки содержат {0}")
    public CatalogPage checkBreadcrumbs(String value) {
        breadcrumbs.shouldHave(text(value));
        return this;
    }

    @Step("Проверяем, что хлебные крошки содержат {0}")
    public CatalogPage checkTitlePage(String value) {
        titlePage.shouldHave(text(value));
        return this;
    }

    @Step("Открываем {0}")
    public CatalogPage openCatalog(String path) {
        open(path);
        changeCityButtonAccept.click();
        return this;
    }

    @Step("Выбираем фильтр {0}")
    public CatalogPage selectFilter(String value) {
        filters.$(byText(value)).click();
        return this;
    }

    @Step("Проверяем, что фильтр {0} выбран")
    public CatalogPage checkSelectFilter(String value) {
        selectFilters.$("[name=Предзаказ]").sibling(0).$(".checkbox-native__icon").shouldHave(attribute("alt", "Отмечено"));
        return this;
    }

    @Step("Проверяем, что все кнопки карточек товара равны {0}")
    public CatalogPage checkButtonText(String value) {
        productCards.stream().forEach(it -> it.$(".action-button__text").shouldHave(text(value)));
        return this;
    }
}
