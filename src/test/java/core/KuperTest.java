package core;

import com.codeborne.selenide.SelenideElement;
import kuper.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static config.Mapper.PROP;

public class KuperTest extends BaseTest{

    @Test
    public void checkMagnitLogo() {
        Assertions.assertTrue(new MainPage(PROP.getURL())
                .selectMagnitExpress()
                .getLogo());
    }
}
