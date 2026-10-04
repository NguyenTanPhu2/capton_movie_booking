package testcase;

import base.BaseTest;
import data.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BookingPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MovieDetailPage;
import pages.modals.CommonModal;

public class BookingTest extends BaseTest {

    private BookingPage bookingPage;
    private HomePage homePage;
    private MovieDetailPage movieDetailPage;
    private LoginPage loginPage;
    private CommonModal commonModal;

    @BeforeMethod
    public void beforeMethod() {
        bookingPage = new BookingPage(driver);
        homePage = new HomePage(driver);
        movieDetailPage = new MovieDetailPage(driver);
        loginPage = new LoginPage(driver);
        commonModal = new CommonModal(driver);
    }

    @Test(
            dataProvider = "booking-multiple-regular-seats",
            dataProviderClass = TestDataProvider.class,
            groups = "booking"
    )
    public void verify_Total_Price_When_Selecting_Multiple_Seats(
            String movieName,
            String schedule,
            int seat1,
            int seat2,
            int seat3,
            String expectedSeats,
            String expectedPrice) {

        // Step 1: Open movie and schedule
        LOG.info("Step 1: Open movie and schedule");
        homePage.clickOnMovieName(movieName);
        movieDetailPage.clickOnSchedule(schedule);

        // Step 2: Select seats 30, 31, 32
        LOG.info("Step 2: Select multiple regular seats");
        bookingPage.clickOnSeat(seat1);
        bookingPage.clickOnSeat(seat2);
        bookingPage.clickOnSeat(seat3);

        // VP: Verify selected seats
        LOG.info("VP: Verify selected seats");
        String actualSeats = bookingPage.getSeat();

        Assert.assertEquals(
                actualSeats,
                expectedSeats,
                "Selected seats are incorrect"
        );

        // VP: Verify total price
        LOG.info("VP: Verify total price");
        String actualPrice = bookingPage.getPrice();

        Assert.assertEquals(
                actualPrice,
                expectedPrice,
                "Total price is incorrect"
        );
    }
    @Test(
            dataProvider = "booking-regular-and-vip-seat",
            dataProviderClass = TestDataProvider.class,
            groups = "booking"
    )
    public void verify_Total_Price_When_Selecting_Regular_And_Vip_Seat(
            String movieName,
            String schedule,
            int regularSeat,
            int vipSeat,
            String expectedSeats,
            String expectedPrice) {

        // Step 1: Open movie and schedule
        LOG.info("Step 1: Open movie and schedule");
        homePage.clickOnMovieName(movieName);
        movieDetailPage.clickOnSchedule(schedule);

        // Step 2: Select regular and VIP seat
        LOG.info("Step 2: Select regular and VIP seat");
        bookingPage.clickOnSeat(regularSeat);
        bookingPage.clickOnSeat(vipSeat);

        // VP: Verify selected seats
        LOG.info("VP: Verify selected seats");
        String actualSeats = bookingPage.getSeat();

        Assert.assertEquals(
                actualSeats,
                expectedSeats,
                "Selected seats are incorrect"
        );

        // VP: Verify total price
        LOG.info("VP: Verify total price");
        String actualPrice = bookingPage.getPrice();

        Assert.assertEquals(
                actualPrice,
                expectedPrice,
                "Total price is incorrect"
        );
    }
    @Test(
            dataProvider = "booking-remove-one-seat",
            dataProviderClass = TestDataProvider.class,
            groups = "booking"
    )
    public void verify_Total_Price_After_Removing_One_Seat(
            String movieName,
            String schedule,
            int seat1,
            int seat2,
            int seat3,
            String expectedSeats,
            String expectedPrice) {

        // Step 1: Open movie and schedule
        LOG.info("Step 1: Open movie and schedule");
        homePage.clickOnMovieName(movieName);
        movieDetailPage.clickOnSchedule(schedule);

        // Step 2: Select multiple seats
        LOG.info("Step 2: Select multiple seats");
        bookingPage.clickOnSeat(seat1);
        bookingPage.clickOnSeat(seat2);
        bookingPage.clickOnSeat(seat3);

        // Step 3: Remove one selected seat
        LOG.info("Step 3: Remove one selected seat");
        bookingPage.clickOnSeat(seat2);

        // VP: Verify remaining seats
        LOG.info("VP: Verify remaining seats");
        String actualSeats = bookingPage.getSeat();

        Assert.assertEquals(
                actualSeats,
                expectedSeats,
                "Remaining seats are incorrect"
        );

        // VP: Verify total price
        LOG.info("VP: Verify total price");
        String actualPrice = bookingPage.getPrice();

        Assert.assertEquals(
                actualPrice,
                expectedPrice,
                "Total price after removing seat is incorrect"
        );
    }
    @Test(
            dataProvider = "booking-refresh-page",
            dataProviderClass = TestDataProvider.class,
            groups = "booking"
    )
    public void verify_Refresh_Page_When_Seat_Is_Selected(
            String movieName,
            String schedule,
            int seat) {

        // Step 1: Open movie and schedule
        LOG.info("Step 1: Open movie and schedule");
        homePage.clickOnMovieName(movieName);
        movieDetailPage.clickOnSchedule(schedule);

        // Step 2: Select seat
        LOG.info("Step 2: Select seat");
        bookingPage.clickOnSeat(seat);

        // Step 3: Refresh page
        LOG.info("Step 3: Refresh booking page");
        driver.navigate().refresh();

        // VP: Verify booking page still works after refresh
        LOG.info("VP: Verify booking page still works after refresh");
        String actualPrice = bookingPage.getPrice();

        Assert.assertNotNull(
                actualPrice,
                "Total price is not displayed after refresh"
        );
    }
    @Test(
            dataProvider = "booking-refresh-page",
            dataProviderClass = TestDataProvider.class,
            groups = "booking"
    )
    public void verify_Browser_Back_From_Booking_Page(
            String movieName,
            String schedule,
            int seat) {

        // Step 1: Open movie detail
        LOG.info("Step 1: Open movie detail");
        homePage.clickOnMovieName(movieName);

        // Step 2: Open booking page
        LOG.info("Step 2: Open booking page");
        movieDetailPage.clickOnSchedule(schedule);

        // Step 3: Click browser Back
        LOG.info("Step 3: Click browser Back");
        driver.navigate().back();

        // VP: Verify returned to previous page
        LOG.info("VP: Verify returned to previous page");
        Assert.assertFalse(
                driver.getCurrentUrl().isEmpty(),
                "Page is not displayed after clicking browser Back"
        );
    }
    @Test(
            dataProvider = "booking-multiple-seats-successfully",
            dataProviderClass = TestDataProvider.class,
            groups = "booking"
    )
    public void verify_Booking_Multiple_Seats_Successfully(
            String account,
            String password,
            String movieName,
            String schedule,
            int seat1,
            int seat2) {

        // Step 1: Login
        LOG.info("Step 1: Login");
        homePage.getTopNavigation().navigateToLoginPage();
        loginPage.login(account, password);

        // Step 2: Open movie and schedule
        LOG.info("Step 2: Open movie and schedule");
        homePage.clickOnMovieName(movieName);
        movieDetailPage.clickOnSchedule(schedule);

        // Step 3: Select seats
        LOG.info("Step 3: Select multiple seats");
        bookingPage.clickOnSeat(seat1);
        bookingPage.clickOnSeat(seat2);

        // Step 4: Booking
        LOG.info("Step 4: Booking");
        bookingPage.clickBooking();

        // VP: Verify booking successfully
        LOG.info("VP: Verify booking successfully");
        String actualMessage = commonModal.getMessageText();

        Assert.assertEquals(
                actualMessage,
                "Đặt vé thành công",
                "Booking success message is incorrect"
        );
    }
    @Test(
            dataProvider = "booking-price-format",
            dataProviderClass = TestDataProvider.class,
            groups = "booking"
    )
    public void verify_Total_Price_Format(
            String movieName,
            String schedule,
            int seat) {

        LOG.info("Step 1: Open movie and schedule");
        homePage.clickOnMovieName(movieName);
        movieDetailPage.clickOnSchedule(schedule);

        LOG.info("Step 2: Select seat");
        bookingPage.clickOnSeat(seat);

        LOG.info("VP: Verify total price format");
        String actualPrice = bookingPage.getPrice();

        Assert.assertTrue(
                actualPrice.matches("\\d+VND"),
                "Total price format is incorrect: " + actualPrice
        );
    }
}