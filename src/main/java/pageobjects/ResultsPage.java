package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultsPage {
    public ResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@class='topbar__title js-header-location-update show-location']")
    public static WebElement location;


    public String getLocation(String searched) {
        //Get the postcode of location from the results page by deleting all the alphabetic chars from the string
        //Get the full address from the results if keyword is a full address
        String locationText = location.getText();
        if (searched.matches("[0-9]+") && searched.length() > 2) {
            locationText = locationText.replaceAll("\\D+", "");
            return locationText;
        } else {
            return locationText;
        }
    }
}
