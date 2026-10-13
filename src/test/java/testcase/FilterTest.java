package testcase;

import base.BaseTest;
import data.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.components.Filter;
import pages.modals.CommonModal;
import report.ExtentReportManager;

public class FilterTest extends BaseTest {

    /// Page Object
    Filter filter;
    CommonModal commonModal;
    HomePage homePage;

    @BeforeMethod
    public void initializePages() {
        filter = new Filter(driver);
        commonModal = new CommonModal(driver);
        homePage = new HomePage(driver);
    }

    @Test(priority = 1, dataProvider = "filter-movie",
            dataProviderClass = TestDataProvider.class, groups = "filter")
    public void verify_Movie_Dropdown_List(String movieName) {
        ///Step 1: click dropdown Film
        LOG.info("Step 1: click dropdown Film");
        ExtentReportManager.info("Step 1: click dropdown Film");
        filter.clickFilterFilm();

        ///VP: Check display the movie list
        LOG.info("VP: Check display the movie list");
        ExtentReportManager.info("VP: Check display the movie list");
        boolean recordingFilterFilm = filter.isMovieDisplayed(movieName);
        Assert.assertTrue(recordingFilterFilm, "The movie list does not display the film ");

    }

    @Test(priority = 2, groups = "filter")
    public void verify_Cinema_And_Schedule_Default_State() {
        ///Step 1: click dropdown Cinema
        LOG.info("Step 1: click dropdown Cinema");
        ExtentReportManager.info("Step 1: click dropdown Cinema");
        filter.clickFilterCinema();

        ///VP: Check display the Cinema list
        LOG.info("VP: Check display the Cinema list");
        ExtentReportManager.info("VP: Check display the Cinema list");
        String recordingCinema = filter.isCinema();
        Assert.assertEquals(recordingCinema, "Rạp", "Cinema information is not displayed");

        ///Step 2: Click dropdown Schedule
        LOG.info("Step 2: Click dropdown Schedule");
        ExtentReportManager.info("Step 2: Click dropdown Schedule");
        filter.clickFilterSchedule();

        ///VP: Check display the Schedule list
        LOG.info("VP: Check display the Schedule list");
        ExtentReportManager.info("VP: Check display the Schedule list");
        String recordingShowTime = filter.isSchedule();
        Assert.assertEquals(recordingShowTime, "Ngày giờ chiếu", "Schedule information is not displayed");

    }

    @Test(priority = 3, dataProvider = "filter-cinema",
            dataProviderClass = TestDataProvider.class, groups = "filter")
    public void verify_Cinema_Dropdown_After_Selecting_Movie(String movieName, String cinemaName) {
        ///Step 1: Select film
        LOG.info("Step 1: Select film");
        ExtentReportManager.info("Step 1: Select film");
        filter.clickChooseFilm(movieName);

        ///Step 2: Select cinema
        LOG.info("Step 2: Select cinema");
        ExtentReportManager.info("Step 2: Select cinema");
        filter.clickChooseCinema(cinemaName);
    }

    @Test(priority = 4, dataProvider = "filter-data",
            dataProviderClass = TestDataProvider.class,groups = "filter")
    public void verify_Schedule_Dropdown_After_Selecting_Cinema(String movieName, String cinemaName, String schedule) {

        ///Step 1: Select film
        LOG.info("Step 1: Select film");
        ExtentReportManager.info("Step 1: Select film");
        filter.clickChooseFilm(movieName);

        ///Step 2: Select Cinema
        LOG.info("Step 2: Select cinema");
        ExtentReportManager.info("Step 2: Select cinema");
        filter.clickChooseCinema(cinemaName);

        ///Step 3: Select Schedule
        LOG.info("Step 3: Select Schedule");
        ExtentReportManager.info("Step 3: Select Schedule");
        filter.clickChooseSchedule(schedule);
    }

    @Test(priority = 5, groups = "filter")
    public void verify_Buy_Ticket_Without_Complete_Information() {
        ///Step 1: Click button Mua ve ngay
        LOG.info("Step 1: Click button Mua ve ngay");
        ExtentReportManager.info("Step 1: Click button Mua ve ngay");
        filter.clickBtnBooking();

        ///VP: Verifle "Bạn chưa chọn phim"
        LOG.info("VP: Verifle 'Bạn chưa chọn phim'");
        ExtentReportManager.info("VP: Verifle 'Bạn chưa chọn phim'");
        String recordingBooking = commonModal.getMessageText();
        Assert.assertEquals(recordingBooking, "Bạn chưa chọn phim", "Movie selection required message is incorrect");

    }

    @Test(priority = 6, dataProvider = "filter-data",
            dataProviderClass = TestDataProvider.class, groups = "filter")
    public void verify_Buy_Ticket_With_Complete_Information(String movieName, String cinemaName, String schedule) {
        ///Step 1: Select film
        LOG.info("Step 1: Select film");
        ExtentReportManager.info("Step 1: Select film");
        filter.clickChooseFilm(movieName);

        ///Step 2: Select Cinema
        LOG.info("Step 2: Select cinema");
        ExtentReportManager.info("Step 2: Select cinema");
        filter.clickChooseCinema(cinemaName);

        ///Step 3: Select Schedule
        LOG.info("Step 3: Select Schedule");
        ExtentReportManager.info("Step 3: Select Schedule");
        filter.clickChooseSchedule(schedule);

        ///Step 4: Click btn Booking
        LOG.info("Step 4: Click btn Booking");
        ExtentReportManager.info("Step 4: Click btn Booking");
        filter.clickBtnBooking();

        ///VP: Navigate Booking Page
        LOG.info("VP: Navigate Booking Page");
        ExtentReportManager.info("VP: Navigate Booking Page");
        String recordingCurrentURL = driver.getCurrentUrl();
        Assert.assertEquals(recordingCurrentURL, "https://demo1.cybersoft.edu.vn/purchase/45060", "HomePage navigation to Booking Page is incorrect!");

    }
}
