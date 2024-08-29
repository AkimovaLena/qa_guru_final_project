package tests.web;

import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CatalogPage;
import pages.HeaderComponent;
import pages.MainPage;
import pages.MenuCatalogComponent;

import static data.TestData.defaultCity;

@Tags({@Tag("ui"), @Tag("main_page")})
@DisplayName("Главная страница UI")
public class MainPageTest extends TestBaseUI {
    private static final Logger logger = LoggerFactory.getLogger(MainPageTest.class);
    final MainPage mainPage = new MainPage();
    final CatalogPage catalogPage = new CatalogPage();
    final HeaderComponent header = new HeaderComponent();
    final MenuCatalogComponent menuCatalog = new MenuCatalogComponent();


    @ValueSource(strings = {
            "Екатеринбург",
            "Краснодар",
            "Казань",
            "Красноярск"
    })
    @ParameterizedTest(name = "Проверка изменения Российского города на {0}")
    void changeCityRussiaTest(String cityName) {
        mainPage.openMain();
        header.checkCity(defaultCity)
                .openPopupChangeCity()
                .selectCity(cityName)
                .checkCity(cityName);
    }

    @CsvSource(value = {
            "Книги, Комиксы",
            "Книги, Манга",
            "Подарки и сувениры, Брелоки",
            "Творчество и хобби, Наклейки"
    })
    @ParameterizedTest(name = "Проверка изменения breadcrumbs после выбора каталога {0} и подраздела {1}")
    void openCatalogTest(String catalog, String subdirectory) {
        mainPage.openMain();
        header.openCatalogMenu();
        menuCatalog.selectCatalog(catalog)
                .selectSubdirectory(subdirectory);
        catalogPage.checkBreadcrumbs(catalog)
                .checkTitlePage(subdirectory);
    }

    @ValueSource(strings = {
            "Новинки литературы",
            "Лучшие из лучших",
            "Рюкзаки, ранцы и пеналы",
            "Эксклюзивно в «Читай-городе»",
            "Скоро в продаже",
            "Дневники для пятёрок"
    })
    @ParameterizedTest(name = "Открытие подборки {0}")
    void openSliderTest(String nameTitle) {
        mainPage.openMain()
                .scrollToFooter()
                .scrollToSlider(nameTitle)
                .openSlider(nameTitle);
        catalogPage.checkTitlePage(nameTitle);
    }

    @ValueSource(strings = {
            "Новинки литературы"
    })
    @ParameterizedTest(name = "Добавление товаров с подборки {0}")
    void addBooksFromMainTest(String nameTitle) {
        mainPage.openMain()
                .scrollToSlider(nameTitle);
        ElementsCollection slider = mainPage.getCardsSlider(nameTitle);
        Assertions.assertTrue(slider.size() >= 6);//проверяем только видимые на экране карточки
        for (int i = 0; i < 6; i++) {
            mainPage.checkButtonName(slider.get(i), "Купить")
                    .addElementInCart(slider.get(i))
                    .checkButtonName(slider.get(i), "Оформить");
            header.checkCountInCart(i + 1);
        }
    }

}
