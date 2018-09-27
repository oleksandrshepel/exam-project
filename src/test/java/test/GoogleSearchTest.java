package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleSearchTest extends GoogleBaseTest {
    @DataProvider
    public Object[][] searchLandingPage() {
        return new Object[][]{
                {"Selenium"},
                {"selenium"}
        };
    }
    /**
     * Preconditions:
     * - Open new browser
     * - Navigate to 'Google.com'
     *
     * Scenario:
     * - Open landing page
     * - Verify landing page is loaded
     * - Search for Search-term
     * - Verify Search page is loaded
     * - Verify 10 results are displayed on the search page
     * - Verify each result item contains search-term
     * - Switch to second results page
     * - Verify result status
     * - Verify 10 results are displayed on the search page
     * - Verify each result item contains search-term
     * Close browser
     */

    @Test(dataProvider = "searchLandingPage")
    public void basicSearchTest(String requestData){
        Assert.assertTrue(googleLandingPage.isSearchFieldDisplayed(), "The searchField is not displayed");
        googleSearchPage = googleLandingPage.makeSearchRequest(requestData);
        Assert.assertEquals(googleSearchPage.getSearchResultsNumber(), 10, "Number of search results is incorrect");
        List<String> searchResultsList = googleSearchPage.getSearchResultsList();
        for(String result: searchResultsList){
            Assert.assertTrue(result.toLowerCase().contains(requestData.toLowerCase()),
                    "Search term "+requestData+" is not found in: "+result);
        }
        googleSearchPage.clickForwardButton();
        googleSearchPage.assertResultStatusContainsText("Сторінка 2 з такої приблизної кількості результатів:");
        Assert.assertEquals(googleSearchPage.getSearchResultsNumber(), 10, "Number of search results is incorrect");
        List<String> secondSearchResultsList = googleSearchPage.getSearchResultsList();
        for(String result: secondSearchResultsList){
            Assert.assertTrue(result.toLowerCase().contains(requestData.toLowerCase()),
                    "Search term "+requestData+" is not found in: "+result);
        }
    }
}
