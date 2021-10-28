package selenium.pages;

import com.epam.healenium.SelfHealingDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexPage extends BasePage{

    private static final By loginInput = By.className("Textinput-Control");
    private static final By credentialButton = By.id("passp:sign-in");
    private static final By passwdInput = By.id("passp-field-passwd");
    private static final By mailPageTitle = By.xpath("//*[@title=\"Написать (w, c)\"]");
    private static final By mailButtonYandex = By.className("desk-notif-card__mail-title");
    private static final By loginButton = By.className("desk-notif-card__login-new-item");

    public YandexPage(SelfHealingDriver driver) {
        super(driver);
    }

    @Step("Open yandex test page")
    public YandexPage open() {
        driver.get(yandexPageUrl);
        return this;
    }

    @Step("Click Login button on yandex page")
    public YandexPage clickLoginButton(){
        driver.findElement(loginButton).click();
        return this;
    }

    @Step("Input yandex credentials and login into yandex system")
    public YandexPage loginToYandex() {
        driver.findElement(loginInput)
                .sendKeys("<login>");
        driver.findElement(credentialButton).click();

        driver.findElement(passwdInput)
                .sendKeys("<password>");
        driver.findElement(credentialButton).click();
        return this;
    }

    @Step("Go to email page and check it by title visibility")
    public boolean checkEmailLoginWithWait() {
        driver.findElement(mailButtonYandex).click();
        String handle= (String) driver.getWindowHandles().toArray()[1];
        driver.switchTo().window(handle);


        WebDriverWait wait = new WebDriverWait(driver, 1);
        WebElement testButton = driver.findElement(mailPageTitle);
        return wait.until(ExpectedConditions.visibilityOf(testButton)).isEnabled();
    }
}
