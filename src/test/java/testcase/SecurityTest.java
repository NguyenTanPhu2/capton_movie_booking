package testcase;

import base.BaseTest;
import constants.TimeOutConstants;
import data.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import pages.components.TopNavigation;
import pages.components.Searching;
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
    Searching searching;

    //Khoi tao page
    @BeforeMethod
    public void initializePages() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        movieDetailPage = new MovieDetailPage(driver);
        bookingPage = new BookingPage(driver);
        commonModal = new CommonModal(driver);
        topNavigation = new TopNavigation(driver);
        searching = new Searching(driver);
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

    @Test(priority = 4, groups = "security")
    public void TC_SECURITY_004_verify_XSS_In_Search_Field() {

        String xssPayload = "<script>alert(1)</script>";

        // Pre-condition: Open search area
        searching.openHalfScreen();

        // Step 1: Enter XSS payload into search field
        LOG.info("Step 1: Enter XSS payload into search field");
        ExtentReportManager.info("Step 1: Enter XSS payload into search field");
        searching.enterMovieName(xssPayload);

        // VP: Verify JavaScript alert is not executed
        LOG.info("VP: Verify JavaScript alert is not executed");
        ExtentReportManager.info("VP: Verify JavaScript alert is not executed");

        boolean alertDisplayed;

        try {
            driver.switchTo().alert();
            alertDisplayed = true;
        } catch (org.openqa.selenium.NoAlertPresentException e) {
            alertDisplayed = false;
        }

        Assert.assertFalse(
                alertDisplayed,
                "SECURITY BUG: XSS script was executed in search field"
        );
    }
    @Test(priority = 5, groups = "security")
    public void TC_SECURITY_006_verify_Direct_Booking_URL_Without_Login() {

        String bookingUrl = "https://demo1.cybersoft.edu.vn/purchase/46212";

        // Step 1: Access booking URL directly without login
        LOG.info("Step 1: Access booking URL directly without login");
        ExtentReportManager.info("Step 1: Access booking URL directly without login");
        driver.get(bookingUrl);

        // Step 2: Select a seat
        // Step 2: Select an available seat
        LOG.info("Step 2: Select an available seat");
        ExtentReportManager.info("Step 2: Select an available seat");

        bookingPage.waitForSeatsToLoad();

        String availableSeat = bookingPage.getAvailableSeatNumbers().get(0);
        bookingPage.clickOnSeat(availableSeat);

        // Step 3: Click Booking
        LOG.info("Step 3: Click Booking");
        ExtentReportManager.info("Step 3: Click Booking");
        bookingPage.clickBooking();

        // VP: Verify login is required
        LOG.info("VP: Verify login is required");
        ExtentReportManager.info("VP: Verify login is required");

        String actualMessage = commonModal.getMessageText();

        Assert.assertEquals(
                actualMessage,
                "Bạn chưa đăng nhập",
                "User can book directly without login"
        );
    }
    @Test(priority = 6, dataProvider = "security-login",
            dataProviderClass = TestDataProvider.class, groups = "security")
    public void TC_SECURITY_007_verify_Logout_Session_Across_Tabs(
            String account, String password) {

        // Step 1: Login in Tab 1
        LOG.info("Step 1: Login in Tab 1");
        ExtentReportManager.info("Step 1: Login in Tab 1");

        homePage.getTopNavigation().navigateToLoginPage();
        loginPage.login(account, password);

        // Save Tab 1
        String tab1 = driver.getWindowHandle();

        // Step 2: Open Tab 2
        LOG.info("Step 2: Open Tab 2");
        ExtentReportManager.info("Step 2: Open Tab 2");

        driver.switchTo().newWindow(
                org.openqa.selenium.WindowType.TAB
        );

        String tab2 = driver.getWindowHandle();

        driver.get("https://demo1.cybersoft.edu.vn/");

        // Step 3: Return to Tab 1 and logout
        LOG.info("Step 3: Logout in Tab 1");
        ExtentReportManager.info("Step 3: Logout in Tab 1");

        driver.switchTo().window(tab1);
        topNavigation.clickOnLogOut();

        // Step 4: Switch to Tab 2 and refresh
        LOG.info("Step 4: Switch to Tab 2 and refresh");
        ExtentReportManager.info("Step 4: Switch to Tab 2 and refresh");

        driver.switchTo().window(tab2);
        driver.navigate().refresh();

        // VP: Tab 2 should become logged out
        LOG.info("VP: Verify Tab 2 is logged out after refresh");
        ExtentReportManager.info(
                "VP: Verify Tab 2 is logged out after refresh"
        );

        boolean loginDisplayed = topNavigation.isLoginDisplays();

        Assert.assertTrue(
                loginDisplayed,
                "SECURITY BUG: Tab 2 still shows logged-in state after logout in Tab 1 and refresh"
        );
    }
}
