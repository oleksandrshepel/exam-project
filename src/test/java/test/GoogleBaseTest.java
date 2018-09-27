package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import page.GoogleLandingPage;
import page.GoogleSearchPage;

/**
 * Parent class for all tests. Here the driver instance is being created.
 * The class contains before and after methods and declares all pageObjects.
 */
public class GoogleBaseTest {
    protected WebDriver driver;
    protected GoogleLandingPage googleLandingPage;
    protected GoogleSearchPage googleSearchPage;

    /**
     * Before method to create webdriver instance and get to the landing page
     * Defines a webdriver instance for a particular browser
     */
    @Parameters({"browserName","mainURL"})
    @BeforeMethod
    public void beforeMethod (@Optional("chrome") String browserName,
                              @Optional("https://www.google.com/") String mainURL) throws Exception {
        switch(browserName.toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            case "internetexplorer":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new Exception("Can not work with a type of browser "+browserName);
        }
        driver.manage().window().maximize();
        driver.get(mainURL);
        googleLandingPage = new GoogleLandingPage(driver);
    }

    /**
     * After method to quit webdriver
     * alwaysRun = true - should be added otherwise if an exception is thrown in BeforeMethod then AfterMethod will not work
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod(){
        driver.quit();
    }
}
