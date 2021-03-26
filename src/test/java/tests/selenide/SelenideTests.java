package tests.selenide;

import org.junit.jupiter.api.Test;
import pages.selenide.SelenideMainPage;
import pages.selenide.SelenideMainPageWithFindBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelenideTests extends SelenideBaseTest{

  @Test
  public void selenideTest(){
    SelenideMainPage mainPage = new SelenideMainPage();

    mainPage.openPage();
    mainPage.clickTestButton();
    assertTrue(mainPage.getAlertText().contains("button found!"), "Alert text is not correct");

    mainPage.confirmAlert();
    mainPage.clickCreateMarkupButton();
    assertTrue(("Click me").equals(mainPage.getTestButtonText()), "Button text is not correct");

    mainPage.clickTestButton();
    mainPage.confirmAlert();

    mainPage.refreshPage();
  }

  @Test
  public void selenideWithFindByTest(){
    SelenideMainPageWithFindBy mainPage = SelenideMainPageWithFindBy.openPage();

    assertTrue(("Click me").equals(mainPage.getTestButtonText()),
        "Button text is not correct");
    mainPage.clickBtn();
    assertTrue(("button found!").equals(mainPage.getAlertText()),
        "Button text is not correct");
    mainPage.confirmAlert();
    mainPage.clickCreateMarkupButton();
    mainPage.clickBtn();
  }

}