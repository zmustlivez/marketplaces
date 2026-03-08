package kuper;

import com.codeborne.selenide.SelenideElement;
import org.apache.commons.codec.digest.DigestUtils;
import org.openqa.selenium.OutputType;

import static com.codeborne.selenide.Selenide.$;

public class MagnitExpressPage {

    SelenideElement magnitLogo = $("img[class*=\"StoreButton\"]");

    public boolean getLogo() {
        if (magnitLogo.isImage()) {
            DigestUtils.md5Hex(
                    magnitLogo.getScreenshotAs(OutputType.BYTES)).equals(
                    "8731f058879ab7666abfdaeba860253d0bf644fd85f83a6e9358b3dddadbdfcc");
            return true;
        }
        return false;
    }
}
