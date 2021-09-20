package selenium.pages;


import com.epam.healenium.SelfHealingDriver;
import com.epam.healenium.annotation.DisableHealing;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPage extends BasePage {
    By generateMarkupBtnId = By.id("markup-generation-button");
    By testButton = By.className("default-btn");
    By testGeneratedButton = By.id("random-id");

    By checkboxAccount = By.xpath("//*[@class='checkbox checkbox_size_m checkbox_theme_alfa-on-white']");
    By textFirstSelect = By.xpath("(//*[text()='Select Account'])[1]");
    By textSecondSelect = By.xpath("(//*[text()='Select Account'])[2]");

    public MainPage(SelfHealingDriver driver) {
        super(driver);
    }

    @Step("Open Main page")
    public MainPage open() {
        driver.get(mainPageUrl);
        return this;
    }

    @Step("Generate Markup")
    public MainPage generateMarkup() {
        driver.findElement(generateMarkupBtnId).click();
        return this;
    }

    @Step("Click test button")
    public MainPage clickTestButton() {
        driver.findElement(testButton).click();
        return this;
    }

    @Step("Click test button after generating markup")
    public void clickTestGeneratedButton() {
        driver.findElement(testGeneratedButton).click();
    }

    @DisableHealing
    @Step("Check that checkboxes available")
    public boolean displayedText() {
        try {
            return driver.findElement(textFirstSelect).isEnabled();
//                    && !driver.findElement(textSecondSelect).isEnabled();
        } catch (NoSuchElementException e1) {
            return false;
        }
    }

    @Step("Select first available account from checkboxes")
    public void selectFirstCheckbox() {
        driver.findElement(checkboxAccount).click();
    }

    @Step("Verify first account checkbox selected")
    public boolean verifyFirstCheckbox() {
        return driver.findElements(checkboxAccount).get(0).isEnabled();
    }

    private List<WebElement> findAllCheckboxes(){
        WebElement parent = driver.findElement(By.xpath("//*[@class='checkbox-group__box']"));
        List<WebElement> checkboxes = parent.findElements(checkboxAccount);
        return checkboxes;
    }
    public void selectAllCheckboxes(){
        List<WebElement> checkboxes = findAllCheckboxes();
        checkboxes.forEach(c->c.click());
    }

    public void verifyAllCheckboxes(){
        List<WebElement> checkboxes = findAllCheckboxes();
        checkboxes.forEach(c->assertTrue(c.isEnabled()));
    }
}
