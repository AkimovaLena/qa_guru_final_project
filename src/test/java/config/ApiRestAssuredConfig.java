package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:api.properties"
})
public interface ApiRestAssuredConfig extends Config {

    @DefaultValue("https://web-gate.chitai-gorod.ru")
    String baseUrl();
}
