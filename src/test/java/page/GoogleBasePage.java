package page;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Google parent object class
 *
 * Contains declaration of WebDriver instance, WebDriverWait instance
 */
public abstract class GoogleBasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Common method to get current url of an appropriate pageobject class
     * @return - current url
     */
    private String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    /**
     * Common method to get current title of an appropriate pageobject class
     * @return - current title
     */
    private String getCurrentTitle(){
        return driver.getTitle();
    }

    /**
     * Base method for waiting  webelement visibility
     * @param webElement
     * @param timeOutInSeconds
     * @return - webelement
     */
    protected WebElement waitUntilElementVisible(WebElement webElement, int timeOutInSeconds){
        wait = new WebDriverWait(driver, timeOutInSeconds);
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * Returns boolean depending the fact whether url contains or not partial text
     * @param partialUrl - a char sequence of
     * @param timeOutInSec - timeout parameter in seconds
     * @return - boolean
     */
    protected boolean isUrlContains(String partialUrl, int timeOutInSec){
        wait = new WebDriverWait(driver, timeOutInSec);
        try{
            return wait.until(ExpectedConditions.urlContains(partialUrl));
        } catch(TimeoutException e){
            return false;
        }
    }

    /**
     * Asserts whether url matches a pattern
     *
     * @param url - a string with url
     */
    protected void assertURLEquals(String url){
        if (!getCurrentUrl().equals(url)) throw new AssertionError("URL doesn't match");
    }

    /**
     * Asserts whether url contains a pattern
     *
     * @param partialUrl - a string with partialurl
     */
    protected void assertURLcontains(String partialUrl){
        if (!getCurrentUrl().contains(partialUrl)) throw new AssertionError("URL doesn't contain a partialUrl");
    }

    /**
     * Asserts whether page title matches a pattern
     *
     * @param pageTitle - a string with page title
     */
    protected void assertTitleEquals(String pageTitle){
        if (!getCurrentTitle().equals(pageTitle)) throw new AssertionError("Page title doesn't match");
    }

    /**
     * Asserts whether page title contains a pattern
     *
     * @param pageTitle - a string with page title
     */
    protected void assertTitleContains(String pageTitle){
        if (!getCurrentTitle().contains(pageTitle)) throw new AssertionError("Page title doesn't contain a pattern");
    }


    /**
     * Asserts whether webelement is visible or not
     *
     * @param element - a webelement that is expected to be visible
     * @param timeOutInSec - a time period in seconds
     */
    protected void assertWebElementIsVisible(WebElement element, int timeOutInSec){
        try {
            waitUntilElementVisible(element, timeOutInSec);
        } catch(TimeoutException e){
            throw new AssertionError("Linkedin page is not loaded");
        }
    }

}
