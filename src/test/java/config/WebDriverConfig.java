package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:local.properties",
        "classpath:${env}.properties"
})
public interface WebDriverConfig extends Config {

    @DefaultValue("https://www.chitai-gorod.ru")
    String baseUrl();

    @DefaultValue("CHROME")
    String browser();

    @DefaultValue("1920x1080")
    String browserSize();

    @DefaultValue("127")
    String browserVersion();

    @DefaultValue("false")
    Boolean isRemote();

    @DefaultValue("https://user1:1234@selenoid.autotests.cloud/wd/hub")
    String remoteUrl();

}