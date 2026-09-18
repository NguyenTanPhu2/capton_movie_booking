package testcase;

import base.BaseTest;
import constants.TimeOutConstants;
import data.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import pages.modals.CommonModal;
import report.ExtentReportManager;

public class BookingTest extends BaseTest {

    /// Page Object
    HomePage homePage;
    MovieDetailPage movieDetailPage;
    CommonModal commonModal;
    BookingPage bookingPage;
    LoginPage loginPage;
    ProfilePage profilePage;

    @BeforeMethod
    public void initializePages() {
        homePage = new HomePage(driver);
        movieDetailPage = new MovieDetailPage(driver);
        commonModal = new CommonModal(driver);
        bookingPage = new BookingPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
    }

    @Test(priority = 1, dataProvider = "booking-information",
            dataProviderClass = TestDataProvider.class, groups = "booking")
    public void verify_Booking_Information_Displayed(String movieName, String schedule, int numSeat, String cinema) {
        ///Step 1: Click name film to movieDetails
        LOG.info("Step 1: Click name film to movieDetails");
        ExtentReportManager.info("Step 1: Click name film to movieDetails");
        homePage.clickOnMovieName(movieName);

        ///Step 2: Click Schedule
        LOG.info("Step 2: Click Schedule");
        ExtentReportManager.info("Step 2: Click Schedule");
        movieDetailPage.clickOnSchedule(schedule);

        commonModal.getWebDriverWait(TimeOutConstants.LONG_TIMEOUT);

        ///Step 3: Click Choose Seat
        LOG.info("Step 3: Click Choose Seat");
        ExtentReportManager.info("Step 3: Click Choose Seat");
        bookingPage.clickOnSeat(numSeat);

        ///VP 1: Verify information cinema
        LOG.info("VP 1: Verify information cinema");
        ExtentReportManager.info("VP 1: Verify information cinema");
        String recordingCinema = bookingPage.getCinema();
        Assert.assertEquals(recordingCinema, cinema, "Cinema name is incorrect");

        ///VP 2: Verify information name film
        LOG.info("VP 2: Verify information name film");
        ExtentReportManager.info("VP 2: Verify information name film");
        String recordingNameFilm = bookingPage.getMovieName();
        Assert.assertEquals(recordingNameFilm, movieName, "Film name is incorrect");

        ///VP 3: Verify information Schedule
        LOG.info("VP 3: Verify information Schedule");
        ExtentReportManager.info("VP 3: Verify information Schedule");
        String recordingSchedule = bookingPage.getSchedule();
        Assert.assertEquals(recordingSchedule, "07-07-2026 -08:07", "Show time is incorrect");
    }

    @Test(priority = 2, dataProvider = "booking-seat-legend",
            dataProviderClass = TestDataProvider.class, groups = "booking")
    public void verify__Displayed(String movieName, String schedule) {
        ///Step 1: Click name film to movieDetails
        LOG.info("Step 1: Click name film to movieDetails");
        ExtentReportManager.info("Step 1: Click name film to movieDetails");
        homePage.clickOnMovieName(movieName);

        ///Step 2: Click Schedule
        LOG.info("Step 2: Click Schedule");
        ExtentReportManager.info("Step 2: Click Schedule");
        movieDetailPage.clickOnSchedule(schedule);

        commonModal.getWebDriverWait(TimeOutConstants.LONG_TIMEOUT);

        ///VP: Verify seat legend is displayed
        LOG.info("VP: Verify seat legend is displayed");
        ExtentReportManager.info("VP: Verify seat legend is displayed");
        boolean recordingSeatLegend = bookingPage.isSeatLegendDisplays();

        Assert.assertTrue(recordingSeatLegend, "Seat legend is not displayed on Booking page");

    }

    @Test(priority = 3, dataProvider = "booking-total-price",
            dataProviderClass = TestDataProvider.class, groups = "booking")
    public void verify_Total_Price_Default(String movieName, String schedule, String price) {
        ///Step 1: Click name film to movieDetails
        LOG.info("Step 1: Click name film to movieDetails");
        ExtentReportManager.info("Step 1: Click name film to movieDetails");
        homePage.clickOnMovieName(movieName);

        ///Step 2: Click schedule
        LOG.info("Step 2: Click schedule");
        ExtentReportManager.info("Step 2: Click schedule");
        movieDetailPage.clickOnSchedule(schedule);

        ///VP: Verify price ticket
        LOG.info("VP: Verify price ticket");
        ExtentReportManager.info("VP: Verify price ticket");
        String recordingPrice = bookingPage.getPrice();
        Assert.assertEquals(recordingPrice, price, "Total price is incorrect");
    }

    @Test(priority = 4, dataProvider = "booking-regular-seat",
            dataProviderClass = TestDataProvider.class, groups = "booking")
    public void verify_Select_Regular_Seat(String movieName, String schedule, int numSeat, String chooseSeat, String price) {
        ///Step 1: Click name film to movieDetails
        LOG.info("Step 1: Click name film to movieDetails");
        ExtentReportManager.info("Step 1: Click name film to movieDetails");
        homePage.clickOnMovieName(movieName);

        ///Step 2: Click Schedule
        LOG.info("Step 2: Click Schedule");
        ExtentReportManager.info("Step 2: Click Schedule");
        movieDetailPage.clickOnSchedule(schedule);

        ///Step 3: Click Choose Seat
        LOG.info("Step 3: Click Choose Seat");
        ExtentReportManager.info("Step 3: Click Choose Seat");
        bookingPage.clickOnSeat(numSeat);
        ///VP 1: Verify number seat
        LOG.info("VP 1: Verify number seat");
        ExtentReportManager.info("VP 1: Verify number seat");
        String recordingSeat = bookingPage.getSeat();
        Assert.assertEquals(recordingSeat, chooseSeat, "Number seat is incorrect");

        ///VP 2: Verify price
        LOG.info("VP 2: Verify price");
        ExtentReportManager.info("VP 2: Verify price");
        String recordingPrice = bookingPage.getPrice();
        Assert.assertEquals(recordingPrice, price, "Total price is incorrect");
    }

    @Test(priority = 5, dataProvider = "booking-vip-seat",
            dataProviderClass = TestDataProvider.class, groups = "booking")
    public void verify_Select_VIP_Seat(String movieName, String schedule, int numSeat, String chooseSeat, String price) {
        ///Step 1: Click name film to movieDetails
        LOG.info("Step 1: Click name film to movieDetails");
        ExtentReportManager.info("Step 1: Click name film to movieDetails");
        homePage.clickOnMovieName(movieName);

        ///Step 2: Click Schedule
        LOG.info("Step 2: Click Schedule");
        ExtentReportManager.info("Step 2: Click Schedule");
        movieDetailPage.clickOnSchedule(schedule);

        ///Step 3: Click Choose Seat
        LOG.info("Step 3: Click Choose Seat");
        ExtentReportManager.info("Step 3: Click Choose Seat");
        bookingPage.clickOnSeat(numSeat);

        ///VP 1: Verify number seat
        LOG.info("VP 1: Verify number seat");
        ExtentReportManager.info("VP 1: Verify number seat");
        String recordingSeat = bookingPage.getSeat();
        Assert.assertEquals(recordingSeat, chooseSeat, "Number seat is incorrect");

        ///VP 2: Verify price
        LOG.info("VP 2: Verify price");
        ExtentReportManager.info("VP 2: Verify price");
        String recordingPrice = bookingPage.getPrice();
        Assert.assertEquals(recordingPrice, price, "Total price is incorrect");
    }

    @Test(priority = 6, dataProvider = "booking-delete-seat",
            dataProviderClass = TestDataProvider.class, groups = "booking")
    public void verify_Deselect_Seat(String movieName, String schedule, int numSeat, String chooseSeat, String price) {
        ///Step 1: Click name film to movieDetails
        LOG.info("Step 1: Click name film to movieDetails");
        ExtentReportManager.info("Step 1: Click name film to movieDetails");
        homePage.clickOnMovieName(movieName);

        ///Step 2: Click Schedule
        LOG.info("Step 2: Click Schedule");
        ExtentReportManager.info("Step 2: Click Schedule");
        movieDetailPage.clickOnSchedule(schedule);

        ///Step 3: Click Choose Seat
        LOG.info("Step 3: Click Choose Seat");
        ExtentReportManager.info("Step 3: Click Choose Seat");
        bookingPage.clickOnSeat(numSeat);

        commonModal.getWebDriverWait(TimeOutConstants.MEDIUM_TIMEOUT);

        ///Step 4: Click delete seat
        LOG.info("Step 4: Click delete seat ");
        ExtentReportManager.info("Step 4: Click delete seat ");
        bookingPage.clickOnSeat(numSeat);

        ///VP 1: Verify number seat
        LOG.info("VP 1: Verify empty seat");
        ExtentReportManager.info("VP 1: Verify number empty seat");
        String recordingSeat = bookingPage.getEmptySeat();
        Assert.assertEquals(recordingSeat, chooseSeat, " seat is not empty");

        ///VP 2: Verify price
        LOG.info("VP 2: Verify price is 0");
        ExtentReportManager.info("VP 2: Verify price");
        String recordingPrice = bookingPage.getPrice();
        Assert.assertEquals(recordingPrice, price, "Total price is incorrect");
    }

    @Test(priority = 7, dataProvider = "booking-sold-seat",
            dataProviderClass = TestDataProvider.class, groups = "booking")
    public void verify_Cannot_Select_Booked_Seat(String movieName, String schedule, String chooseSeat) {
        ///Step 1: Click name film to movieDetails
        LOG.info("Step 1: Click name film to movieDetails");
        ExtentReportManager.info("Step 1: Click name film to movieDetails");
        homePage.clickOnMovieName(movieName);

        ///Step 2: Click Schedule
        LOG.info("Step 2: Click Schedule");
        ExtentReportManager.info("Step 2: Click Schedule");
        movieDetailPage.clickOnSchedule(schedule);

        ///Step 3: Seat status is displayed correctly
        LOG.info("Step 3: Seat status is displayed correctly");
        ExtentReportManager.info("Step 3: Seat status is displayed correctly");

        String recordingSeat = bookingPage.getSeatSold(1);
        Assert.assertEquals(recordingSeat, chooseSeat, "Seat status is incorrect");
    }

    @Test(priority = 8, dataProvider = "booking-without-seat",
            dataProviderClass = TestDataProvider.class, groups = "booking")
    public void verify_Booking_Without_Selecting_Seat(String account, String password, String movieName, String schedule) {
        ///Step 1: Navigate Login page
        LOG.info("Step 1: Navigate Login page");
        ExtentReportManager.info("Step 1: Navigate Login page");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Login account
        LOG.info("Step 2: Login Account");
        ExtentReportManager.info("Step 2: Login Account");
        loginPage.login(account, password);

        ///Step 3: Click name film to movieDetails
        LOG.info("Step 3: Click name film to movieDetails");
        ExtentReportManager.info("Step 3: Click name film to movieDetails");
        homePage.clickOnMovieName(movieName);

        ///Step 4: Click Schedule
        LOG.info("Step 4: Click Schedule");
        ExtentReportManager.info("Step 4: Click Schedule");
        movieDetailPage.clickOnSchedule(schedule);

        ///Step 5: Click Mua ve
        LOG.info("Step 5: Click Mua Ve");
        ExtentReportManager.info("Step 5: Click Mua Ve");
        bookingPage.clickBooking();

        ///VP: Seat selection required message is displayed correctly
        LOG.info("VP: Seat selection required message is displayed correctly");
        ExtentReportManager.info("VP: Seat selection required message is displayed correctly");
        String recordingTicket = commonModal.getMessageText();
        Assert.assertEquals(recordingTicket, "Bạn chưa chọn ghế", "Login required message is incorrect");
    }

    @Test(priority = 9, dataProvider = "booking-valid-seat",
            dataProviderClass = TestDataProvider.class, groups = "booking") //change numSeat
    public void verify_Booking_Successfully(String account, String password, String movieName, String schedule, int numSeat) {
        ///Step 1: Navigate Login page
        LOG.info("Step 1: Navigate Login page");
        ExtentReportManager.info("Step 1: Navigate Login page");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Login account
        LOG.info("Step 2: Login Account");
        ExtentReportManager.info("Step 2: Login Account");
        loginPage.login(account, password);

        ///Step 3: Click name film to movieDetails
        LOG.info("Step 3: Click name film to movieDetails");
        ExtentReportManager.info("Step 3: Click name film to movieDetails");
        homePage.clickOnMovieName(movieName);

        ///Step 4: Click Schedule
        LOG.info("Step 4: Click Schedule");
        ExtentReportManager.info("Step 4: Click Schedule");
        movieDetailPage.clickOnSchedule(schedule);

        ///Step 5: Click chon ghe
        LOG.info("Step 5: Click chon ghe");
        ExtentReportManager.info("Step 5: Click chon ghe");
        bookingPage.clickOnSeat(numSeat);

        ///Step 6: Click Mua ve
        LOG.info("Step 6: Click Mua Ve");
        ExtentReportManager.info("Step 6: Click Mua Ve");
        bookingPage.clickBooking();

        ///VP: Booking success message is displayed correctly
        LOG.info("VP: Booking success message is displayed correctly");
        ExtentReportManager.info("VP: Booking success message is displayed correctly");
        String recordingTicket = commonModal.getMessageText();
        Assert.assertEquals(recordingTicket, "Đặt vé thành công", "Booking success message is incorrect");
    } //change numSeat

    @Test(priority = 10, dataProvider = "booking-without-login",
            dataProviderClass = TestDataProvider.class, groups = "booking")
    public void verify_Booking_Requires_Login(String movieName, String schedule, int numSeat) {
        ///Step 1: Click name film to movieDetails
        LOG.info("Step 1: Click name film to movieDetails");
        ExtentReportManager.info("Step 1: Click name film to movieDetails");
        homePage.clickOnMovieName(movieName);

        ///Step 2: Click Schedule
        LOG.info("Step 2: Click Schedule");
        ExtentReportManager.info("Step 2: Click Schedule");
        movieDetailPage.clickOnSchedule(schedule);

        ///Step 3: Click chon ghe
        LOG.info("Step 3: Click chon ghe");
        ExtentReportManager.info("Step 3: Click chon ghe");
        bookingPage.clickOnSeat(numSeat);

        ///Step 4: Click Mua ve
        LOG.info("Step 4: Click Mua Ve");
        ExtentReportManager.info("Step 4: Click Mua Ve");
        bookingPage.clickBooking();

        ///Step 5: Click confirm navigate to Login Page
        LOG.info("Step 5: Click confirm navigate to Login Page");
        ExtentReportManager.info("Step 5: Click confirm navigate to Login page");
        bookingPage.clickConfirmLogin();

        ///VP: Verify navigate to Login Page
        LOG.info("VP: Verify navigate to Login Page");
        ExtentReportManager.info("VP: Verify navigate to Login Page");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://demo1.cybersoft.edu.vn/sign-in", "Current URL is incorrect");
    }

    @Test(priority = 11, dataProvider = "booking-seat-status-after-booking",
            dataProviderClass = TestDataProvider.class, groups = "booking") //Change numbSeat
    public void verify_Seat_Status_After_Booking(String account, String password, String movieName, String schedule, String seatSold, String numSeat) {
        ///Step 1: Navigate Login page
        LOG.info("Step 1: Navigate Login page");
        ExtentReportManager.info("Step 1: Navigate Login page");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Login account
        LOG.info("Step 2: Login Account");
        ExtentReportManager.info("Step 2: Login Account");
        loginPage.login(account, password);

        ///Step 3: Click name film to movieDetails
        LOG.info("Step 3: Click name film to movieDetails");
        ExtentReportManager.info("Step 3: Click name film to movieDetails");
        homePage.clickOnMovieName(movieName);

        ///Step 4: Click Schedule
        LOG.info("Step 4: Click Schedule");
        ExtentReportManager.info("Step 4: Click Schedule");
        movieDetailPage.clickOnSchedule(schedule);

        ///Step 5: Click chon ghe
        LOG.info("Step 5: Click chon ghe");
        ExtentReportManager.info("Step 5: Click chon ghe");
        bookingPage.clickOnSeat(numSeat);

        ///Step 6: Click Mua ve
        LOG.info("Step 6: Click Mua Ve");
        ExtentReportManager.info("Step 6: Click Mua Ve");
        bookingPage.clickBooking();

        ///VP 1: Booking success message is displayed correctly
        LOG.info("VP 1: Booking success message is displayed correctly");
        ExtentReportManager.info("VP 1: Booking success message is displayed correctly");
        String recordingTicket = commonModal.getMessageText();
        Assert.assertEquals(recordingTicket, "Đặt vé thành công", "Booking success message is incorrect");

        ///VP 2: Seat status is displayed correctly
        LOG.info("VP 2: Seat status is displayed correctly");
        ExtentReportManager.info("VP 2: Seat status is displayed correctly");

        String recordingSeat = bookingPage.getSeatSold(2);
        Assert.assertEquals(recordingSeat, seatSold, "Seat status is incorrect");
    }

    @Test(priority = 12,
            dataProvider = "booking-history",
            dataProviderClass = TestDataProvider.class)
    public void verify_Booking_History_Information(String account, String password) {
        ///Step 1: Navigate to LoginPage
        LOG.info("Step 1: Navigate to LoginPage");
        ExtentReportManager.info("Step 1: Navigate to LoginPage");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Login Account
        LOG.info("Step 2: Login Account");
        ExtentReportManager.info("Step 2: Login Account");
        loginPage.login(account, password);

        ///Step 3: Navigate to Profile page
        LOG.info("Step 3: Navigates to profile page");
        ExtentReportManager.info("Step 3: Navigates to profile page");
        homePage.getTopNavigation().navigateToProfilePage();

        ///Step 4: Scroll
        LOG.info("Step 4: Scroll");
        ExtentReportManager.info("Step 4: Scroll");
        profilePage.scroll();

        ///VP : Verify new ticket
        LOG.info("VP: Verify new ticket");
        ExtentReportManager.info("VP: Verify new ticket");
        boolean recordingNewTicket = profilePage.isNewTicket();
        Assert.assertTrue(recordingNewTicket, "Ticket is new ticket");

    }

}
