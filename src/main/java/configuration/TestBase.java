package configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

public class TestBase {
    // instance of singleton class
    private static TestBase instanceOfTestBase = null;
    private WebDriver driver;

    // Constructor
    private TestBase() {
        WebDriverManager.chromedriver().setup();
        initialDriverCapability();

    }

    private void initialDriverCapability() {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
    }
    public static TestBase getInstanceOfTestBase() {

        if (instanceOfTestBase == null) {
            instanceOfTestBase = new TestBase();
        }
        return instanceOfTestBase;
    }
    public WebDriver getDriver() {
        SessionId session =  ((RemoteWebDriver) driver).getSessionId();
        if(session!= null){
            driver.manage().deleteAllCookies();
            return driver;
        }
        initialDriverCapability();
        return driver;
    }
}
