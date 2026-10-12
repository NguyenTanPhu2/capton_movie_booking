package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

public class HomePage extends CommonPage {

    private By byClickOnName;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnMovieName(String nameFilm) {
        byClickOnName = By.xpath("//a[contains(@href, '/detail/')][contains(normalize-space(.), '" + nameFilm + "')]");
        WebElement movieLink = waitElementToBeClickable(byClickOnName);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", movieLink);
    }
}
