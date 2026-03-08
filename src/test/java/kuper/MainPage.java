package kuper;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

import java.time.Duration;
import java.util.Date;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static config.Mapper.PROP;

public class MainPage {

    private final SelenideElement shopMagnit =
            $("a[href*=\"magnit\"][data-qa*=\"886b022e-b648-4ee6-8b4d-6aebe21d9e0f\"]");
    /*private final SelenideElement cookieWindow =
            $("button[data-qa=\"cookies_consent_confirm_button\"]");
    */private final SelenideElement cookieWindow =
            $(byText("Понятно"));
    private final SelenideElement discountWindow =
            $("button[class^=\"Button-module\"][class*=\"DownloadAppModal_closeButton\"] path[clip-rule=\"evenodd\"]");

    public MainPage(String url) {
        if (url == null || url.isEmpty()) throw new RuntimeException("URL is empty!");
        Selenide.open("https://kuper.ru/robots.txt");
        addFullAuthStack();
        Selenide.open(url);

//        Selenide.refresh();
    }

    public void closeCoookieWindow() {
//        if (cookieWindow.should(Condition.exist, Duration.ofSeconds(10)).isDisplayed()) {
        if(cookieWindow.exists()){
            cookieWindow.click();
        }
    }

    public void closeDiscountWindow() {
        if (discountWindow.exists()) {
            discountWindow.click();
        }
    }

    public MagnitExpressPage selectMagnitExpress() {
        closeCoookieWindow();
        closeDiscountWindow();
        shopMagnit.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        return new MagnitExpressPage();
    }


    private void addFullAuthStack() {
        // Основной токен
        addCookie(".kuper.ru", "remember_user_token", PROP.getToken());
        // Сессия
        addCookie(".kuper.ru", "sessionId", PROP.getSessionID());
        addCookie("kuper.ru", "remember_user_token", PROP.getToken());
        // Сессия
        addCookie("kuper.ru", "sessionId", PROP.getSessionID());
        // Историческая сессия (возьмите из Storage)
        addCookie(".kuper.ru", "_instamart_session", PROP.getInstamartSession());
        // Согласие на куки (чтобы окно не всплывало)

    }

    private void addCookie(String domain, String name, String value) {
        Cookie cookie = new Cookie.Builder(name, value)
                .domain(domain)
                .expiresOn((new Date(System.currentTimeMillis()+100000)))
                .path("/")
                .isSecure(true)
                .build();
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
    }
}
