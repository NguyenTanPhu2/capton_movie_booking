package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BookingPage extends CommonPage {

    private By bySeat;
    private By byBtnBooking;
    private By byLbNameCinema;
    private By byLbMovieName;
    private By byLbSchedule;
    private By byAreaSeatLegend;
    private By byLbPrice;
    private By byLbSeat;
    private By byLbEmptySeat;
    private By bySeatSold;
    private By byConfirmLogin;

    public BookingPage(WebDriver driver) {
        super(driver);
        this.byBtnBooking = By.xpath("//button[span[text()='ĐẶT VÉ']]");
        this.byLbNameCinema = By.xpath("//h3[preceding-sibling::h3[normalize-space()='Cụm Rạp:']]");
        this.byLbMovieName = By.xpath("//h3[preceding-sibling::h3[normalize-space()='Tên Phim:']]");
        this.byLbSchedule = By.xpath("//h3[preceding-sibling::h3[normalize-space()='Ngày giờ chiếu:']]");
        this.byAreaSeatLegend = By.xpath("//div[@class='jss250']");
        this.byLbPrice = By.xpath("//p[contains(normalize-space(), 'VND')]");
        this.byLbSeat = By.xpath("//h3[preceding-sibling::h3[normalize-space()='Chọn:']]");
        this.byLbEmptySeat = By.xpath("//h3[normalize-space()='Chọn:']/following-sibling::h3[1]");

        this.byConfirmLogin = By.xpath("//button[text()='Đồng ý']");
    }

    public void clickOnSeat(int numSeat) {
        bySeat = By.xpath("//button[span[text()='" + numSeat + "']]");
        click(bySeat);
    }

    public void clickOnSeat(String numSeat) {
        bySeat = By.xpath("//button[span[text()='" + numSeat + "']]");
        click(bySeat);
    }

    public void clickBooking() {
        click(byBtnBooking);
    }

    public String getCinema() {
        return getText(byLbNameCinema);
    }

    public String getMovieName() {
        return getText(byLbMovieName);
    }

    public String getSchedule() {
        return getText(byLbSchedule);
    }

    public boolean isSeatLegendDisplays() {
        return isElementDisplayed(byAreaSeatLegend);
    }

    public String getPrice() {
        return getText(byLbPrice);
    }

    public String getSeat() {
        return getText(byLbSeat);
    }

    public String getEmptySeat() {
        return getTextIfPresent(byLbEmptySeat);
    }

    public String getSeatSold(int index) {
        bySeatSold = By.xpath("(//button[span[text()='X']])[" + index + "]");
        return getText(bySeatSold);
    }

    public void clickConfirmLogin() {
        click(byConfirmLogin);
    }

}
