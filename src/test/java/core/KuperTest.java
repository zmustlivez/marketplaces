package core;

import kuper.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static config.Mapper.PROP;

public class KuperTest extends BaseTest{

    @Test
    public void checkMagnitLogo() {
        Assertions.assertTrue(new MainPage(PROP.getURL())
                .selectMagnitExpress()
                .getLogo());
    }
}
