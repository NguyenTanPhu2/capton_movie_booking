package pages.components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Filter extends BasePage {

    private By byFilterFilm;
    private By byFilterCinema;
    private By byFilterSchedule;
    private By byBtnBooking;

    public Filter(WebDriver driver) {
        super(driver);
        this.byFilterFilm = By.xpath("//select[@name='film']");
        this.byFilterCinema = By.xpath("//select[@name='cinema']");
        this.byFilterSchedule = By.xpath("//select[@name='date']");
        this.byBtnBooking = By.xpath("//button[span[text()='MUA VÉ NGAY']]");
    }

    public void clickFilterFilm() {
        jsClick(byFilterFilm);
    }

    public boolean isMovieDisplayed(String movieName) {
        return isOptionDisplayed(byFilterFilm, movieName);
    }

    public void clickFilterCinema() {
        jsClick(byFilterCinema);
    }

    public String isCinema() {
        return getText(byFilterCinema);
    }

    public void clickFilterSchedule() {
        jsClick(byFilterSchedule);
    }

    public String isSchedule() {
        return getText(byFilterSchedule);
    }

    public void clickChooseFilm(String nameFilm) {
        clickFilterFilm();
        choose(byFilterFilm, nameFilm);
    }

    public void clickChooseCinema(String cinema) {
        clickFilterCinema();
        choose(byFilterCinema, cinema);
    }

    public void clickBtnBooking() {
        jsClick(byBtnBooking);
    }

    public void clickChooseSchedule(String time) {
        clickFilterSchedule();
        choose(byFilterSchedule, time);
    }
}
