package testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import data.TestDataProvider;
import pages.CinemaListPage;
import report.ExtentReportManager;

public class CinemaListTest extends BaseTest {

    CinemaListPage cinemaListPage;

    @BeforeMethod
    public void initializePages() {
        cinemaListPage = new CinemaListPage(driver);
    }

    @Test(priority = 1, groups = "cinema")
    public void TC_CIN_10_verify_Cinema_List_Movie_Poster_Displayed_Correctly() {

        /// Step 1: Navigate to Cinema List
        LOG.info("Step 1: Navigate to Cinema List");
        ExtentReportManager.info("Step 1: Navigate to Cinema List");

        cinemaListPage.getTopNavigation().clickOnCinema();

        /// VP: Verify movie poster is displayed correctly
        LOG.info("VP: Verify movie poster is displayed correctly");
        ExtentReportManager.info("VP: Verify movie poster is displayed correctly");

        boolean recordingMoviePoster= cinemaListPage.isMoviePosterDisplayed();

        Assert.assertTrue(recordingMoviePoster,
                "Movie poster is not displayed correctly");
    }

    @Test(priority = 2, groups = "cinema")
    public void TC_CIN_11_verify_Cinema_System_Switching_CGV_BHD_MegaGS() {

        /// Step 1: Navigate to Cinema List
        LOG.info("Step 1: Navigate to Cinema List");
        ExtentReportManager.info("Step 1: Navigate to Cinema List");

        cinemaListPage.getTopNavigation().clickOnCinema();

        /// Step 2: Click CGV
        LOG.info("Step 2: Click CGV cinema system");
        ExtentReportManager.info("Step 2: Click CGV cinema system");

        cinemaListPage.clickCGV();

        /// VP: Verify CGV is displayed
        LOG.info("VP: Verify CGV cinema system is displayed");
        ExtentReportManager.info("VP: Verify CGV cinema system is displayed");

        Assert.assertTrue(cinemaListPage.isCGVDisplayed(),
                "CGV cinema system is not displayed");

        /// Step 3: Click BHD
        LOG.info("Step 3: Click BHD cinema system");
        ExtentReportManager.info("Step 3: Click BHD cinema system");

        cinemaListPage.clickBHD();

        /// VP: Verify BHD is displayed
        LOG.info("VP: Verify BHD cinema system is displayed");
        ExtentReportManager.info("VP: Verify BHD cinema system is displayed");

        Assert.assertTrue(cinemaListPage.isBHDDisplayed(),
                "BHD cinema system is not displayed");

        /// Step 4: Click MegaGS
        LOG.info("Step 4: Click MegaGS cinema system");
        ExtentReportManager.info("Step 4: Click MegaGS cinema system");

        cinemaListPage.clickMegaGS();

        /// VP: Verify MegaGS is displayed
        LOG.info("VP: Verify MegaGS cinema system is displayed");
        ExtentReportManager.info("VP: Verify MegaGS cinema system is displayed");

        Assert.assertTrue(cinemaListPage.isMegaGSDisplayed(),
                "MegaGS cinema system is not displayed");
    }

    @Test(priority = 3, groups = "cinema")
    public void TC_CIN_12_verify_Cinema_List_Showtime_Displayed() {

        /// Step 1: Navigate to Cinema List
        LOG.info("Step 1: Navigate to Cinema List");
        ExtentReportManager.info("Step 1: Navigate to Cinema List");

        cinemaListPage.getTopNavigation().clickOnCinema();

        /// VP: Verify showtime buttons are displayed
        LOG.info("VP: Verify showtime buttons are displayed");
        ExtentReportManager.info("VP: Verify showtime buttons are displayed");

        boolean isShowtimeDisplayed
                = cinemaListPage.isShowtimeDisplayed();

        Assert.assertTrue(
                isShowtimeDisplayed,
                "Showtime buttons are not displayed"
        );
    }

    @Test(priority = 4, dataProvider = "cinema-list-showtime", 
                dataProviderClass = TestDataProvider.class, groups = "cinema-list")
    public void TC_CIN_13_verify_Cinema_List_Showtime_Hover_Color(String movieName, String day, String time){

        /// Step 1: Navigate to Cinema List
        LOG.info("Step 1: Navigate to Cinema List");
        ExtentReportManager.info("Step 1: Navigate to Cinema List");
        cinemaListPage.getTopNavigation().clickOnCinema();

        /// Step 2: Get date and time color before hover
        LOG.info("Step 2: Get showtime date and time color before hover");
        ExtentReportManager.info("Step 2: Get showtime date and time color before hover");
        String dateColorBefore = cinemaListPage.getShowtimeDateColor();
        String timeColorBefore = cinemaListPage.getShowtimeTimeColor();

        /// Step 3: Hover to showtime
        LOG.info("Step 3: Hover to showtime");
        ExtentReportManager.info("Step 3: Hover to showtime");
        cinemaListPage.hoverToShowtime(movieName, day, time);

        /// Step 4: Get date and time color after hover
        LOG.info("Step 4: Get showtime date and time color after hover");
        ExtentReportManager.info(
            "Step 4: Get showtime date and time color after hover");
        String dateColorAfter = cinemaListPage.getShowtimeDateColor();
        String timeColorAfter = cinemaListPage.getShowtimeTimeColor();
        LOG.info("Date color before hover: " + dateColorBefore);
        LOG.info("Date color after hover: " + dateColorAfter);
        LOG.info("Time color before hover: " + timeColorBefore);
        LOG.info("Time color after hover: " + timeColorAfter);
        ExtentReportManager.info("Date color before hover: " + dateColorBefore);
        ExtentReportManager.info("Date color after hover: " + dateColorAfter);
        ExtentReportManager.info("Time color before hover: " + timeColorBefore);
        ExtentReportManager.info("Time color after hover: " + timeColorAfter);

        /// VP: Verify date color changes
        LOG.info("VP: Verify date color changes after hover");
        ExtentReportManager.info(
            "VP: Verify date color changes after hover");
        Assert.assertNotEquals(dateColorAfter, dateColorBefore,
                    "Date color does not change after hover");

        /// VP: Verify time color changes
        LOG.info("VP: Verify time color changes after hover");
        ExtentReportManager.info(
            "VP: Verify time color changes after hover");
        Assert.assertNotEquals(timeColorAfter, timeColorBefore,
                    "Time color does not change after hover");
    }

    @Test(priority = 5, dataProvider = "cinema-list-showtime", 
                dataProviderClass = TestDataProvider.class, groups = "cinema-list")
    public void TC_CIN_14_verify_Cinema_List_Showtime_Navigate_To_Booking(String movieName, String day, String time){

        LOG.info("Step 1: Navigate to Cinema List");
        ExtentReportManager.info("Step 1: Navigate to Cinema List");

        cinemaListPage.getTopNavigation().clickOnCinema();

        LOG.info("Step 2: Click showtime " + movieName + " " + day + " " + time );
        ExtentReportManager.info("Step 2: Click showtime " + movieName + " " + day + " " + time );

        cinemaListPage.clickMovieShowtime(movieName, day, time);

        LOG.info("VP: Verify navigate to Booking page");
        ExtentReportManager.info(
                "VP: Verify navigate to Booking page"
        );

        String currentUrl = cinemaListPage.getCurrentUrl();

        LOG.info("Current URL: " + currentUrl);
        ExtentReportManager.info("Current URL: " + currentUrl);

        Assert.assertTrue(currentUrl.contains("/purchase/"),
                "User is not navigated to Booking page");
    }

    @Test(priority = 6, groups = "cinema")
    public void TC_CIN_15_verify_Cinema_List_Inner_Scrollbar() throws InterruptedException {

        LOG.info("Step 1: Navigate to Cinema List");
        ExtentReportManager.info("Step 1: Navigate to Cinema List");

        cinemaListPage.getTopNavigation().clickOnCinema();

        LOG.info("Step 2: Scroll inside cinema list column");
        ExtentReportManager.info("Step 2: Scroll inside cinema list column");

        cinemaListPage.scrollCinemaSystemColumn(1000);

        LOG.info("VP: Verify inner scrollbar works normally");
        ExtentReportManager.info("VP: Verify inner scrollbar works normally");
    }
}
