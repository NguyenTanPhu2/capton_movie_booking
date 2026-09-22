package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MovieDetailPage extends CommonPage {

    private By bySchedule;
    private By byGetNameMovie;

    public MovieDetailPage(WebDriver driver) {
        super(driver);
        this.byGetNameMovie = By.xpath("//div[h1]");
    }

    public void clickOnSchedule(String schedule) {
        bySchedule = By.xpath("//a[p[text()='" + schedule + "']]");
        click(bySchedule);
    }

    public String getTextNameMovie() {
        return getText(byGetNameMovie);
    }
}
