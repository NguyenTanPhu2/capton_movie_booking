
package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static data.locator.CinemaListLocator.*;

public class CinemaListPage extends CommonPage {

    private final By byMoviePoster;

    public CinemaListPage(WebDriver driver) {
        super(driver);

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
}