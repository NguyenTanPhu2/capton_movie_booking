package pages.components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TopNavigation extends BasePage {
    private By byLnkLogin;
    private By byLnkRegister;
    private By byLnkLogOut;
    private By byLnkLogo;
    private By byLnkSchedule;
    private By byLnkCinema;
    private By byLnkNews;
    private By byLnkApplication;

    private By byIsSchedule;
    private By byIsCinema;
    private By byIsNews;
    private By byIsApplication;

    public TopNavigation(WebDriver driver) {
        super(driver);
        this.byLnkLogin = By.xpath("//a[h3[text()='Đăng Nhập']]");
        this.byLnkRegister = By.xpath("//a[h3[text()='Đăng Ký' ]]");
        this.byLnkLogOut = By.xpath("//a[h3[text() ='Đăng xuất']]");
        this.byLnkLogo = By.xpath("//img[@alt='Logo']");
        this.byLnkSchedule = By.xpath("//a[h4[text()='Lịch Chiếu']]");
        this.byLnkCinema = By.xpath("//a[h4[text()='Cụm Rạp']]");
        this.byLnkNews =By.xpath("//a[h4[text()='Tin Tức']]");
        this.byLnkApplication = By.xpath("//a[h4[text()='Ứng Dụng']]");

        this.byIsSchedule = By.xpath("//div[@id='lichChieu']");
        this.byIsCinema = By.xpath("//div[@id='cumRap']");
        this.byIsNews = By.xpath("//div[@id='tinTuc']");
        this.byIsApplication = By.xpath("//div[@id='ungDung']");

    }

    public void navigateToLoginPage() {
        click(byLnkLogin);
    }

    public void navigateToRegisterPage() {
        click(byLnkRegister);
    }

    public void clickOnLogOut() {
        click(byLnkLogOut);
    }

    public void clickOnLogo() {
        click(byLnkLogo);
    }

    public void clickOnSchedule() {
        click(byLnkSchedule);
    }

    public boolean isSchedule() {
        return isElementDisplayed(byIsSchedule);
    }

    public void clickOnCinema() {
        click(byLnkCinema);
    }

    public boolean isCinema() {
        return isElementDisplayed(byIsCinema);
    }

    public void clickOnNews() {
        click(byLnkNews);
    }

    public boolean isNews() {
        return isElementDisplayed(byIsNews);
    }

    public void clickOnApplication() {
        click(byLnkApplication);
    }

    public boolean isApplication() {
        return isElementDisplayed(byIsApplication);
    }

    public String getScheduleColor() {
        return getCssValue(byLnkSchedule, "color");
    }

    public void hoverSchedule(){
        hoverMouse(byLnkSchedule);
    }

    public boolean isLogo(){
        return isElementDisplayed(byLnkLogo);
    }

}
