package pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$$;

public class MenuCatalogComponent {

    final ElementsCollection categoriesColumn = $$(".categories-menu__column");


    @Step("Выбираем каталог {0}")
    public MenuCatalogComponent selectCatalog(String catalog) {
        categoriesColumn.first().$(byText(catalog)).click();
        return this;
    }

    @Step("Выбираем подкаталог {0}")
    public MenuCatalogComponent selectSubdirectory(String subdirectory) {
        categoriesColumn.get(1).$(byText(subdirectory)).click();
        return this;
    }


}
