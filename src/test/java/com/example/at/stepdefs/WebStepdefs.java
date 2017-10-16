package com.example.at.stepdefs;

import com.example.at.pageObjects.SearchResultsPage;
import com.example.at.pageObjects.ShopHomePage;
import com.example.at.support.web.BrowserNavigation;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class WebStepdefs {
    ShopHomePage shopHomePage;
    SearchResultsPage searchResultsPage;

    @Given("^home page is opened$")
    public void homePageIsOpened() throws Throwable {
        BrowserNavigation.openHomePage();

        shopHomePage = new ShopHomePage(); //init shopHomePage object
        assertThat(shopHomePage.isPageDisplayed()).as("Home Page is not displayed").isTrue();
    }

    @Given("^user searched for a \"([^\"]*)\"$")
    public void userSearchedForA(String arg0) throws Throwable {

        shopHomePage.enterSearch("Blouse");
        shopHomePage.clickSearch();
    }

    @Then("^Search Result page for \"([^\"]*)\" is displayed$")
    public void searchResultPageForIsDisplayed(String productName) throws Throwable {
        searchResultsPage = new SearchResultsPage();
        assertThat(searchResultsPage.isPageDisplayed()).as("Search Result page is not displayed").isTrue();
        assertThat(searchResultsPage.getHeaderText()).contains(productName.toUpperCase());
    }

    @Then("^search result size is equal to \"([^\"]*)\"$")
    public void searchResultSizeIsEqualTo(int resultSize) throws Throwable {
        assertThat(searchResultsPage.getSearchResultWidgetsCount()).isEqualTo(resultSize);
    }

    @Then("^user clicks on Add To Cart for \"([^\"]*)\"$")
    public void userClicksOnAddToCartFor(String productName) throws Throwable {
        searchResultsPage.clickOnAddToCartForProduct(productName);
    }
}
