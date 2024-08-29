package tests.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBaseAPI {

    @BeforeAll
    public static void beforeAll() {
        RestAssured.baseURI = "https://web-gate.chitai-gorod.ru";

    }
}
