package UIFeatures;

import component.Components;
import configuration.TestBase;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class UiTest {

    TestBase testBase = TestBase.getInstanceOfTestBase();
    JavascriptExecutor js;
    Components components;
    @BeforeTest
    @Parameters({"url","resolution"})
    public void suiteParameters(String url,String resolution){
        testBase.getDriver().get(url);
        if(resolution.equalsIgnoreCase("Max")){
            testBase.getDriver().manage().window().maximize();
        }
        else if(resolution.equalsIgnoreCase("1024x678")){
            testBase.getDriver().manage().window().setSize(new Dimension(1024, 768));
        }
        else if(resolution.equalsIgnoreCase("800x600")){
            testBase.getDriver().manage().window().setSize(new Dimension(800, 600));
        }
        components= new Components();
        js = (JavascriptExecutor) testBase.getDriver();
    }

    @Test(priority = 1)
    public void uiTest() {
        components.acceptCookies();
        Assert.assertTrue(components.isDisplayedResearchTab(),"Validating research tab");
        components.isClickableResearchTab();
        js.executeScript("window.scrollBy(0,500)", "");
        Assert.assertTrue(components.isDisplayedEconomicCalendar(),"Validating economic calendar");
        testBase.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        components.isClickableEconomicCalendar();
        components.acceptCookies();
        js.executeScript("window.scrollBy(0,300)", "");
        testBase.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        components.isSelectableTodayDate();
        String todayDate= components.getDateSelectedValue();
        Assert.assertEquals(todayDate,"Today","Validating today's date");
        components.isSelectableTomorrowDate();
        String tomorrowDate= components.getDateSelectedValue();
        Assert.assertEquals(tomorrowDate,"Tomorrow","Validating tomorrow date");
        components.isSelectableNextWeek();
        String nextWeekDate= components.getDateSelectedValue();
        Assert.assertEquals(nextWeekDate,"Next Week","Validating next week");
        components.isSelectableNextMonth();
        String nextMonthDate= components.getDateSelectedValue();
        Assert.assertEquals(nextMonthDate,"Next Month" ,"Validating next month");
        testBase.getDriver().switchTo().defaultContent();
        String homeUrl=testBase.getDriver().getCurrentUrl();
        testBase.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        components.isClickableHereLink();
        String currentUrl=testBase.getDriver().getCurrentUrl();
        Assert.assertNotEquals(homeUrl,currentUrl);
        components.isClickableHereLinkRiskWarning();
        Set<String> handles = testBase.getDriver().getWindowHandles();
        Iterator<String> it =handles.iterator();
        String parentWindow= it.next();
        String childWindow= it.next();
        testBase.getDriver().switchTo().window(childWindow);
        String newUrl=testBase.getDriver().getCurrentUrl();
        Assert.assertTrue(newUrl.contains("pdf"),"Validating pdf opens in another tab");
        Assert.assertNotEquals(homeUrl,newUrl,"validating home url and pdf url are not same");
    }
    @AfterSuite()
    public void tearDown() {
        testBase.getDriver().quit();
    }


  }