package tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.WebDriverConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBaseUI {

    static final WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());


    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = System.getProperty("browser_size", config.browserSize());
        Configuration.browser = System.getProperty("browser", config.browser());
        Configuration.browserVersion = System.getProperty("version", config.browserVersion());
        Configuration.baseUrl = System.getProperty("stand", config.baseUrl());
        System.out.println(config.isRemote());
        if (config.isRemote()) {
            Configuration.remote = config.remoteUrl();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void afterEach() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        if (!Configuration.browser.equals("firefox")) {
            Attach.browserConsoleLogs();
        }
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}
