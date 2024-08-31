package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CatalogPage {
    private final SelenideElement breadcrumbs = $(".app-breadcrumbs__link"),
            titlePage = $(".app-catalog-page__title"),
            changeCityButtonAccept = $(".change-city-container__popup-confirmation").$(".change-city__button--accept"),
            filters = $(".catalog-template-filters"),
            selectFilters = $(".catalog-search-products__current-filters"),
            productsList = $(".products-list");

    final ElementsCollection productCards = $(".products-list").$$("article");

    @Step("Проверяем, что хлебные крошки содержат {0}")
    public CatalogPage checkBreadcrumbs(String value) {
        breadcrumbs.shouldHave(text(value));
        return this;
    }

    @Step("Проверяем, что заголовок равен {0}")
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
    public CatalogPage checkSelectFilter() {
        selectFilters.$(byText("Предзаказ")).shouldBe(visible);
        return this;
    }

    @Step("Проверяем, что все кнопки карточек товара равны {0}")
    public CatalogPage checkButtonText(String value) {
        productCards.stream().forEach(it -> it.$(".action-button__text").shouldHave(text(value)));
        return this;
    }

    @Step("Скроллим до списка товаров")
    public CatalogPage scrollToProductsList() {
        productsList.scrollTo();
        sleep(2000);
        return this;
    }

}
