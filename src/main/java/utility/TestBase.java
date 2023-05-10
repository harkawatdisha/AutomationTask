package utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

public class TestBase {
    private static TestBase instanceOfTestBase = null;
    private WebDriver driver;
    JavascriptExecutor executor = (JavascriptExecutor) driver;


    // Constructor
    private TestBase() {
        WebDriverManager.chromedriver().setup();
        initialDriverCapability();

    }

    private void initialDriverCapability() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

    }

    // TO create instance of class
    public static TestBase getInstanceOfTestBase() {

        if (instanceOfTestBase == null) {
            instanceOfTestBase = new TestBase();
        }
        return instanceOfTestBase;
    }

    // To get driver
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
