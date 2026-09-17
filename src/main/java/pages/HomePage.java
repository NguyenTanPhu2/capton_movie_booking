package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends CommonPage {

    private By byClickOnName;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnMovieName(String nameFilm) {
        byClickOnName = By.xpath("//div[text()='" + nameFilm + "']");
        click(byClickOnName);
    }
}
