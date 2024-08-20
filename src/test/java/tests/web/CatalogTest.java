package tests.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CatalogPage;

@Tags({@Tag("ui"), @Tag("catalog")})
public class CatalogTest  extends TestBase{

    private static final Logger log = LoggerFactory.getLogger(MainPageTest.class);
    CatalogPage catalogPage = new CatalogPage();

    @Test
    @DisplayName("Проверка фильтра Предзаказ")
    void filterPreOrderListingTest() {
        String value = "Предзаказ";
        catalogPage.openCatalog("/catalog/books/komiksy-110063")
                .selectFilter(value)
                .checkSelectFilter(value)
                .checkButtonText(value);
    }
}
