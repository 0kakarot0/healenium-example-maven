package com.epam.healenium.selenide.pageobject.markup;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.epam.healenium.annotation.DisableHealing;
import com.epam.healenium.constants.PageUrl;
import com.epam.healenium.selenide.pageobject.SelenideBasePage;
import com.epam.reportportal.annotations.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class SelenideMainPageWithFindBy extends SelenideBasePage {
    @FindBy(how = How.XPATH, using = "//button[contains(@class,'default-btn')]")
    public SelenideElement testButton;

    @FindBy(how = How.ID, using = "markup-generation-button")
    public SelenideElement generateMarkupBtnId;

    @Step("Open page")
    public SelenideMainPageWithFindBy openPage(){
        open(PageUrl.MARKUP_URL.toString());
        return page(SelenideMainPageWithFindBy.class);
    }

    @Step("Generate markup")
    public SelenideMainPageWithFindBy generateMarkup() {
        generateMarkupBtnId.shouldBe(Condition.visible).click();
        return page(SelenideMainPageWithFindBy.class);
    }

    @Step("Click test button")
    public SelenideMainPageWithFindBy clickTestButton() {
        testButton.shouldBe(Condition.visible).click();
        return page(SelenideMainPageWithFindBy.class);
    }

    @DisableHealing
    @Step("Check locator with disabled healing annotation")
    public boolean checkLocatorTestButtonDontHealing() {
        try {
            testButton.shouldBe(Condition.visible).click();
            return false;
        } catch (ElementNotFound e) {
            return true;
        }
    }

    @Step("Confirm alert")
    public SelenideMainPageWithFindBy confirmAlert(){
        Selenide.switchTo().alert().accept();
        return page(SelenideMainPageWithFindBy.class);
    }

}
