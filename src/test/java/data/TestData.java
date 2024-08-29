package data;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:test.data"
})
public interface TestData extends Config{

    String defaultCity = "efre";
}
