package tests.api;

import config.ApiRestAssuredConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

public class TestBaseAPI {

    static ApiRestAssuredConfig config = ConfigFactory.create(ApiRestAssuredConfig.class, System.getProperties());


    @BeforeAll
    public static void beforeAll() {
        RestAssured.baseURI = config.baseUrl();

    }
}
