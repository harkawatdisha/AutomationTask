package component;

import configuration.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Components {
    TestBase testBase = TestBase.getInstanceOfTestBase();
    JavascriptExecutor js = (JavascriptExecutor) testBase.getDriver();
    Actions moveSlider = new Actions(testBase.getDriver());
    @FindBy(className = "navbar-brand logo")
    WebElement pageLogo;
    @FindBy(className = "main_nav_research")
    WebElement researchTab;
    @FindBy(xpath ="//a[contains(text(),\" Economic Calendar\")]" )
    WebElement economicCalendar;
    @FindBy(xpath = "//div[@class=\"mat-slider-thumb\"]")
    WebElement slider;
    @FindBy(xpath ="//div[@id=\"cookieModal\"]//button[@class=\"btn btn-block btn-red btn-solid js-acceptDefaultCookie gtm-acceptDefaultCookieFirstVisit\"]")
    WebElement acceptCookies;
    @FindBy(xpath = "//a[text()=\"here\"]")
    WebElement hereLink;
    @FindBy(xpath = "//Strong[text()=\"Disclaimer:\"]")
    WebElement disclaimer;
    @FindBy(xpath = "//h5[text()=\"Forward-Looking Statements\"]")
    WebElement riskWarning;
    @FindBy(xpath = "//div[@id=\"risk-block\"]//a[text()=\"Risk Disclosure\"]")
    WebElement hereLinkRiskWarning;
    @FindBy(xpath = "//iframe[@title=\"iframe\"]")
    WebElement iframe;
    @FindBy(xpath = "//span//div[@class=\"ng-star-inserted\"]")
    WebElement dateSelected;



    public Components() {
        PageFactory.initElements(testBase.getDriver(), this);
    }
    public boolean isDisplayedResearchTab(){
        return researchTab.isDisplayed();
    }
    public void isClickableResearchTab(){
        researchTab.click();
    }
    public boolean isDisplayedEconomicCalendar(){
       return economicCalendar.isDisplayed();
    }
    public void isClickableEconomicCalendar(){
      economicCalendar.click();
    }
    public void isSelectableTodayDate(){
        testBase.getDriver().switchTo().frame(iframe);
        Action action = moveSlider.dragAndDropBy(slider, 30, 0).build();
        action.perform();
    }
    public String getDateSelectedValue(){
        new WebDriverWait(testBase.getDriver(), 40).until(driver-> ExpectedConditions.visibilityOf(dateSelected));
        return dateSelected.getText();
    }
    public void isSelectableTomorrowDate(){
        new WebDriverWait(testBase.getDriver(), 40).until(driver-> ExpectedConditions.visibilityOf(slider));
        Action action = moveSlider.dragAndDropBy(slider, 30, 0).build();
        action.perform();
    }
    public void isSelectableNextWeek(){
        Action action = moveSlider.dragAndDropBy(slider, 90, 0).build();
        action.perform();
        }
    public void isSelectableNextMonth(){
        Action action = moveSlider.dragAndDropBy(slider, 90, 0).build();
        action.perform();
         }
    public void acceptCookies(){
        new WebDriverWait(testBase.getDriver(), 40).until(driver-> ExpectedConditions.elementToBeClickable(acceptCookies));
        js.executeScript("arguments[0].click();", acceptCookies);
    }
    public void isClickableHereLink(){
        new WebDriverWait(testBase.getDriver(), 40).until(driver-> ExpectedConditions.visibilityOf(hereLink));
        js.executeScript("arguments[0].scrollIntoView(true);", disclaimer);
        hereLink.click();
    }
    public void isClickableHereLinkRiskWarning(){
        new WebDriverWait(testBase.getDriver(), 40).until(driver-> ExpectedConditions.visibilityOf(hereLinkRiskWarning));
        hereLinkRiskWarning.click();
    }


}
