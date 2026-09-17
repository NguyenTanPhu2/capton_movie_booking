package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MovieDetailPage extends CommonPage {

    private By bySchedule;
    private By byGetNameMovie;

    public MovieDetailPage(WebDriver driver) {
        super(driver);
        this.byGetNameMovie = By.xpath("//div[h1]");
        this.bySchedule = By.xpath("//div[@class='jss955']");
    }

    public void clickOnSchedule() {
        click(bySchedule);
    }

    public String getTextNameMovie() {
        return getText(byGetNameMovie);
    }
}
