package selenium;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Test;
import selenium.pages.YandexPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class YandexTest extends BaseTest{

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Conditional wait shouldn't be healed while loading the page")
    public void testWaitLoadingPage() {
        YandexPage yandexPage = new YandexPage(driver);
        yandexPage.open();

        boolean result = yandexPage.clickLoginButton()
                .loginToYandex()
                .checkEmailLoginWithWait();

        assertTrue(result);
    }
}
