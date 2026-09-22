package pages.components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Searching extends BasePage {

    private By bySearching;
    private By byNameFilm;
    private By byAlertError;
    private By bySearchResults;

    public Searching(WebDriver driver) {
        super(driver);
        this.bySearching = By.xpath("//input[@type='text']");
        this.byAlertError = By.xpath("//div[h3]");

        this.bySearchResults = By.xpath("//div[@id='lichChieu']");
    }

    public void enterMovieName(String movieName) {
        sendKeys(bySearching, movieName);
        waitVisibilityOfElementLocated(bySearching).sendKeys(Keys.ENTER);
    }

    public String isFilmDisplayed(String nameFilm) {
        byNameFilm = By.xpath("//div[text()='" + nameFilm + "']");
        return getText(byNameFilm);
    }

    public String getMessageText() {
        return getText(byAlertError);
    }

    public boolean isSearchingResult(){
        return isElementDisplayed(bySearchResults);
    }
}
