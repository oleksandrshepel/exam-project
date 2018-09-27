package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Google landing page object class
 */
public class GoogleLandingPage extends GoogleBasePage{
    private String url = "https://www.google.com/";
    private String pageTitle = "Google";

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchField;

    @FindBy(xpath = "//input[@name='btnK']")
    private WebElement googleSearchButton;

    @FindBy(xpath = "//input[@name='btnI']")
    private WebElement iAmLuckyButton;

    /**
     * Constructor for GoogleLandingPage.
     *
     * @param driver - driver instance from tests.
     */
    public GoogleLandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        assertWebElementIsVisible(googleSearchButton,10);
        assertWebElementIsVisible(iAmLuckyButton,10);
        assertURLEquals(url);
        assertTitleEquals(pageTitle);
    }

    /**
     * Defines whether webElement is displayed
     *
     * @return - boolean
     */
    public boolean isSearchFieldDisplayed() {
        return searchField.isDisplayed();
    }

    /**
     * Make search by term
     *
     * @param requestData - search term
     * @return - Linkedin Search PageObject
     */
    public GoogleSearchPage makeSearchRequest(String requestData) {
        searchField.click();
        searchField.sendKeys(requestData + Keys.ENTER);
        return new GoogleSearchPage(driver);
    }

}
