package selenium.pages;

import com.epam.healenium.SelfHealingDriver;
import com.epam.reportportal.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class CallbackTestPage extends BasePage {

    By addSquareButton = By.xpath("//button[contains(@class, 'add')]");
    By updateSquareButton = By.xpath("//button[contains(@class, 'update')]");
    By removeSquareButton = By.xpath("//button[contains(@class, 'remove')]");

    By testButton = By.xpath("//custom-square[contains(@c, 'red')]");
    By testButtonCss = By.cssSelector("[c='red']");

    public CallbackTestPage(SelfHealingDriver driver) {
        super(driver);
    }

    @Step("Open Life cycle callbacks test page")
    public CallbackTestPage open() {
        driver.get(callbackTestPageUrl);
        return this;
    }

    @Step("Verify test button with elements in shadowRoot enabled")
    public boolean verifyShadowElement() {
        return getTestButton().isEnabled();
    }

    @Step("Verify test square with css enabled")
    public boolean verifySquareElement() {
        return driver.findElement(testButtonCss).isEnabled();
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

    @Step("Click Remove attributes button")
    public CallbackTestPage clickRemoveSquareButton() {
        driver.findElement(removeSquareButton).click();
        return this;
    }

    private WebElement getTestButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement shadowRoot = driver.findElement(testButton);
        return (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowRoot);
    }

}
