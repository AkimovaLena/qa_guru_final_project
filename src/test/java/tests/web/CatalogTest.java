package tests.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CatalogPage;

@Tags({@Tag("ui"), @Tag("catalog")})
@DisplayName("Каталог UI")
public class CatalogTest extends TestBaseUI {

    private static final Logger logger = LoggerFactory.getLogger(MainPageTest.class);
    final CatalogPage catalogPage = new CatalogPage();

    @Test
    @DisplayName("Проверка фильтра Предзаказ")
    void filterPreOrderListingTest() {
        String value = "Предзаказ";
        catalogPage.openCatalog("/catalog/books/manga-110064")
                .selectFilter(value)
                .checkSelectFilter()
                .scrollToProductsList()
                .checkButtonText(value);
    }

}
