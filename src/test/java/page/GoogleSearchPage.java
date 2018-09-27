package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Google search page object class
 */
public class GoogleSearchPage extends GoogleBasePage {
    private String partialUrl = "/search";
    private String pageTitle = "- Google Search";

    @FindBy(xpath = "//div[@id='logocont']")
    private WebElement logoContent;

    @FindBy(xpath = "//div[@id='resultStats']")
    private WebElement resultsStatus;

    @FindBy(xpath = "//div[@class='g']")
    private List<WebElement> relevantSearchResults;

    @FindBy(xpath = "//a[@id='pnnext']/span[2]")
    private WebElement forwardButton;


    /**
     * Constructor for GoogleSearchPage.
     *
     * @param driver - driver instance from tests.
     */
    public GoogleSearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        assertWebElementIsVisible(logoContent,10);
        assertWebElementIsVisible(resultsStatus,10);
        assertURLContains(partialUrl);
        //assertTitleContains(pageTitle); -- has some problems with encoding ukr text
    }

    /**
     * Defines number of searchresults
     *
     * @return - size of an array of search results
     */

    public int getSearchResultsNumber() {
        return relevantSearchResults.size();
    }


    /**
     * Creates list of strings with search results
     *
     * @return - list of search results
     */
    public List<String> getSearchResultsList(){
        List<String> searchResultList = new ArrayList<String>();
        for(WebElement result: relevantSearchResults){
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", result);
            searchResultList.add(result.getText());
        }
        return searchResultList;
    }

    public void clickForwardButton(){
        forwardButton.click();
    }

    public void assertResultStatusContainsText(String properText){
        try {
            waitUntilElementVisible(resultsStatus, 10);
            if(!resultsStatus.getText().contains(properText))
                throw new AssertionError("Result status row doesn't contain a proper text");

        } catch(TimeoutException e){
            throw new AssertionError("Result status row is not visible");
        }
    }



}
