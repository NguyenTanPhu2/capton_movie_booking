
package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static data.locator.CinemaListLocator.*;

public class CinemaListPage extends CommonPage {
    private final WebDriver driver;

    private final By byMoviePoster;

    public CinemaListPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

        byMoviePoster = By.xpath("//img");
    }

    public boolean isMoviePosterDisplayed() {
        return isElementDisplayed(byMoviePoster);
    }

    public boolean isShowtimeDisplayed() {
        return isElementDisplayed(byShowtime);
    }

    // Click CGV
    public void clickCGV() {
        click(byBtnCGV);
    }

    // Click BHD
    public void clickBHD() {
        click(byBtnBHD);
    }

    // Click MegaGS
    public void clickMegaGS() {
        click(byBtnMegaGS);
    }

    // Check cinema system displayed
    public boolean isCGVDisplayed() {
        return isElementDisplayed(byBtnCGV);
    }

    public boolean isBHDDisplayed() {
        return isElementDisplayed(byBtnBHD);
    }

    public boolean isMegaGSDisplayed() {
        return isElementDisplayed(byBtnMegaGS);
    }

    public void hoverToShowtime(String movieName, String day, String time) {
        By byBtnShowtime = By.xpath(String.format(BTN_SHOWTIME_XPATH, movieName, day, time));
        hoverMouse(byBtnShowtime);
    }

    public String getShowtimeDateColor() {
        return getCssValue(
                byShowtimeDate,
                "color"
        );
    }

    public String getShowtimeTimeColor() {
        return getCssValue(
                byShowtimeTime,
                "color"
        );
    }

    public String getShowtimeDateFontWeight() {
        return getCssValue(
                byShowtimeDate,
                "font-weight"
        );
    }

    public String getShowtimeTimeFontWeight() {
        return getCssValue(
                byShowtimeTime,
                "font-weight"
        );
    }

    public void clickMovieShowtime(String movieName, String day, String time) {
        By byBtnShowtime = By.xpath(String.format(BTN_SHOWTIME_XPATH, movieName, day, time));
        click(byBtnShowtime);
    }

    public void scrollCinemaSystemColumn(int column) {
        scrollElement(byCinemaListColumn, column);
    }
    public void selectCinemaSystem(String systemName) {
        By system = By.xpath(
                "(//div[@role='tablist'])[1]//button[" +
                        ".//img[contains(translate(@alt," +
                        "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                        "'abcdefghijklmnopqrstuvwxyz'),'" +
                        systemName.toLowerCase() + "')]" +
                        " or contains(translate(@aria-label," +
                        "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                        "'abcdefghijklmnopqrstuvwxyz'),'" +
                        systemName.toLowerCase() + "')" +
                        "]"
        );

        click(system);
    }

    public String getSelectedCinemaSystem() {
        return driver.findElement(
                By.xpath("(//div[@role='tablist'])[1]//button[@aria-selected='true']")
        ).getAttribute("aria-label");
    }

    public String getCinemaListContent() {

        By cinemaNames = By.xpath(
                "(//div[@role='tablist'])[2]//button[@role='tab']//h4"
        );

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(15)
        );

        wait.until(d -> !d.findElements(cinemaNames).isEmpty());

        StringBuilder result = new StringBuilder();

        for (org.openqa.selenium.WebElement cinema :
                driver.findElements(cinemaNames)) {

            result.append(cinema.getText().trim())
                    .append("\n");
        }

        return result.toString().trim();
    }

    public String getMovieListContent() {
        return driver.findElement(
                By.xpath("//div[@id='vertical-tabpanel-0']")
        ).getText().trim();
    }
}