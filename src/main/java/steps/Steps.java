package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.HomePage;
import pageobjects.ResultsPage;

public class Steps {

    private WebDriver driver;

    @Before
    public void StartUp() {

        //Start Browser
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {

        //Close driver
        driver.close();
        driver.quit();

    }

    @Given("^I navigate to lieferand.com$")
    public void iNavigateToLieferandcom() {
        driver.navigate().to("https://www.lieferando.de/en/");
    }

    @When("^I fill the search bar with \"([^\"]*)\"$")
    public void iFillTheSearchBarWith(String input) {
        HomePage homePage = new HomePage(driver);
        homePage.fillSearchBar(input);
    }

    @And("^I click on submit button$")
    public void iClickOnSubmitButton() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnSubmitButton();
    }

    @Then("^I should navigate to restaurant list page for \"([^\"]*)\" successfully$")
    public void iShouldNavigateToRestaurantListPageFor(String searched) {
        //if keyword is postcode, it compares the postcode we search and postcode on the results page
        //if keyword is full address, it compares the full address we search and the address on the results page
        ResultsPage resultsPage = new ResultsPage(driver);
        waitUntil(resultsPage.location);
        Assert.assertEquals(searched, resultsPage.getLocation(searched));

    }

    @Then("^Suggestions should be relevant to search \"([^\"]*)\" keyword$")
    public void suggestionsShouldBeRelevantToSearchKeyword(String keyword) {
        //checks every suggestion if it consists of the keyword
        HomePage homePage = new HomePage(driver);
        waitUntil(homePage.suggestions);
        Assert.assertTrue(homePage.searchForString(keyword));
    }


    @Then("^I should see an error message \"([^\"]*)\"$")
    public void iShouldSeeAnErrorMessage(String message) {
        //waits until error message is seen, then asserts that message and expected message are same
        HomePage homePage = new HomePage(driver);
        waitUntil(HomePage.errorMessage);
        String messageOnTheScreen = homePage.displayErrorMessage();
        Assert.assertEquals(messageOnTheScreen, message);
    }

    @And("^I click on Enter button$")
    public void iClickOnEnterButton() {
        HomePage homePage = new HomePage(driver);
        waitUntil(homePage.placeSelected);
        homePage.sendKeysEnter();
    }

    @Then("^I should see an error warning \"([^\"]*)\"$")
    public void iShouldSeeAnErrorWarning(String warning) {
        HomePage homePage = new HomePage(driver);
        waitUntil(homePage.warning);
        Assert.assertEquals(warning, homePage.getWarning());
    }

    public void waitUntil(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(element));

    }
}
