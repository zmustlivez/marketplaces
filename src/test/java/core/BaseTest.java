package core;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.firefox.FirefoxOptions;

abstract public class BaseTest {

    public static void setUp() {
        Configuration.browser = "firefox";
        Configuration.headless = false;
//        Configuration.timeout = 8000L;
//        Configuration.pageLoadStrategy= "eager";

        FirefoxOptions options = new FirefoxOptions();
        // Отключает встроенную защиту от отслеживания, которая иногда вызывает всплывашки
        options.addPreference("privacy.trackingprotection.enabled", false);

// Отключает уведомления (Push notifications), которые часто путают с cookie-баннерами
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("dom.webnotifications.serviceworker.enabled", false);

// Отключает запросы на доступ к геолокации
        options.addPreference("geo.enabled", false);

// Подавляет окна проверки совместимости и первого запуска
        options.addPreference("browser.startup.homepage_override.mstone", "ignore");
        options.addPreference("general.useragent.override", "Mozilla/5.0 (X11; Linux x86_64; rv:148.0) Gecko/20100101 Firefox/148.0");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        Configuration.browserCapabilities = options;
    }

    @BeforeAll
    static void init() {
        setUp();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
