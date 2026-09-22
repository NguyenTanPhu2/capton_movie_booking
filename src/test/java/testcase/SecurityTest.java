package testcase;

import base.BaseTest;
import constants.TimeOutConstants;
import data.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import pages.components.TopNavigation;
import pages.modals.CommonModal;
import report.ExtentReportManager;

public class SecurityTest extends BaseTest {

    /// Page Object
    HomePage homePage;
    LoginPage loginPage;
    MovieDetailPage movieDetailPage;
    BookingPage bookingPage;
    CommonModal commonModal;
    TopNavigation topNavigation;

    //Khoi tao page
    @BeforeMethod
    public void initializePages() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        movieDetailPage = new MovieDetailPage(driver);
        bookingPage = new BookingPage(driver);
        commonModal = new CommonModal(driver);
        topNavigation = new TopNavigation(driver);
    }

    @Test(priority = 1, dataProvider = "security-login",
            dataProviderClass = TestDataProvider.class, groups = "security")
    public void verify_Functions_By_Login_Status(String account, String password) {
        ///VP 1: Verify Login Page is displayed
        LOG.info("VP 1: Verify Login Page is displayed");
        ExtentReportManager.info("VP 1: Verify Login Page is displayed");

        boolean recordingBeforeLogin = topNavigation.isLoginDisplays();
        Assert.assertTrue(recordingBeforeLogin, "Login Page is not displayed");

        commonModal.getWebDriverWait(TimeOutConstants.MEDIUM_TIMEOUT);

        ///Step 1: Navigate to Login Page
        LOG.info("Step 1: Navigate to Login Page");
        ExtentReportManager.info("Step 1: Navigate to Login Page");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Login account
        LOG.info("Step 2: Login account");
        ExtentReportManager.info("Step 2: Login account");
        loginPage.login(account, password);

        commonModal.getWebDriverWait(TimeOutConstants.MEDIUM_TIMEOUT);

        ///VP 2: Verify Login Successfully
        LOG.info("VP 2: Verify Login Successfully");
        ExtentReportManager.info("VP 2: Verify Login Successfully");
        boolean recordingAfterLogin = topNavigation.isProfileDisplays();
        Assert.assertTrue(recordingAfterLogin, "Login was not successful");

    }

    @Test(priority = 2, dataProvider = "security-login",
            dataProviderClass = TestDataProvider.class, groups = "security")
    public void verify_Logout_Button_After_Login(String account, String password) {
        ///Step 1: Navigate to Login Page
        LOG.info("Step 1: Navigate to Login Page");
        ExtentReportManager.info("Step 1: Navigate to Login Page");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Login account
        LOG.info("Step 2: Login account");
        ExtentReportManager.info("Step 2: Login account");
        loginPage.login(account, password);

        ///VP: Verify Login Successfully
        LOG.info("VP: Verify Login Successfully");
        ExtentReportManager.info("VP: Verify Login Successfully");
        boolean recordingLogOut = homePage.getTopNavigation().isLogOutDisplays();
        Assert.assertTrue(recordingLogOut, "Logout message is not displayed");
    }

    @Test(priority = 3, dataProvider = "security-booking",
            dataProviderClass = TestDataProvider.class, groups = "security")
    public void verify_Booking_Requires_Login(String movieName, int numSeat, String schedule) {

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

        ///Step 4: Click button Booking
        LOG.info("Step 4: Click button Booking");
        ExtentReportManager.info("Step 4: Click button Booking");
        bookingPage.clickBooking();

        ///VP: Requires Login
        LOG.info("VP: Requires Login");
        ExtentReportManager.info("VP: Requires Login");
        String recordedRequire = commonModal.getMessageText();
        Assert.assertEquals(recordedRequire, "Bạn chưa đăng nhập", "Login required message is incorrect");
    }
}
