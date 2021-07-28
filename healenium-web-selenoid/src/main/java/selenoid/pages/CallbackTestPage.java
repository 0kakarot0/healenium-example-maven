package selenoid.pages;


import com.epam.healenium.SelfHealingDriver;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class CallbackTestPage extends BasePage {

    By addSquareButton = By.xpath("//button[contains(@class, 'add')]");
    By updateSquareButton = By.xpath("//button[contains(@class, 'update')]");
    By testButton = By.xpath("//custom-square[contains(@c, 'red')]");

    public CallbackTestPage(SelfHealingDriver driver) {
        super(driver);
    }

    @Step("Open Life cycle callbacks test page")
    public CallbackTestPage open() {
        driver.get(callbackTestPageUrl);
        return this;
    }

    @Step("Verify test button with elements in shadowRoot enabled")
    public void verifyShadowElement() {
        Assertions.assertTrue(getTestButton().isEnabled(),
                "Element in shadowRoot enabled");
    }

    @Step("Click Add custom-square to DOM button")
    public CallbackTestPage clickAddSquareButton() {
        driver.findElement(addSquareButton).click();
        return this;
    }

    @Step("Click Update attributes button")
    public CallbackTestPage clickUpdateSquareButton() {
        driver.findElement(updateSquareButton).click();
        return this;
    }

    private WebElement getTestButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement shadowRoot = driver.findElement(testButton);
        return (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowRoot);
    }

}
