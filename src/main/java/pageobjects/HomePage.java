package pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "imysearchstring")
    public static WebElement searchBar;

    @FindBy(id = "submit_deliveryarea")
    public static WebElement submitButton;

    @FindAll({
            @FindBy(className = "lp__place"),
            @FindBy(xpath = "//a[@class='lp__place selected']")
    })
    public static List<WebElement> suggestedPlaces;

    @FindBy(className = "skip")
    public static WebElement suggestions;

    @FindBy(xpath = "//div[@id='ideliveryareaerror']")
    public static WebElement errorMessage;

    @FindBy(xpath = "//a[@class='lp__place selected']")
    public static WebElement placeSelected;

    @FindBy(className = "lp__warning")
    public static WebElement warning;

    public void fillSearchBar(String name) {
        searchBar.sendKeys(name);
    }

    public void clickOnSubmitButton() {
        submitButton.click();
    }

    public boolean searchForString(String keyword) {
        //search keyword in the suggested places and check if every suggestion includes the keyword we're searching for
        int counter = 0;
        for (int i = 0; i < suggestedPlaces.size(); i++) {
            String b[] = suggestedPlaces.get(i).getText().trim().toLowerCase().split(" ");
            for (int j = 0; j < b.length; j++) {
                if (b[j].contains(keyword)) {
                    counter++;
                }
            }
        }
        if (counter == suggestedPlaces.size()) {
            return true;
        } else {
            return false;
        }
    }

    public String displayErrorMessage() {
        return errorMessage.getText();
    }

    public void sendKeysEnter() {
        searchBar.sendKeys(Keys.ENTER);
    }

    public String getWarning() {
        return warning.getText();
    }
}
