package testcase;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import report.ExtentReportManager;

public class TopNavigateBarTest extends BaseTest {


    /// Page Object
    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void initializePages() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1, groups = "topNavigateBar")
    public void verify_Click_Cybersoft_Logo() {
        ///Step 1: Navigate to LoginPage
        LOG.info("Step 1: Navigate to LoginPage");
        ExtentReportManager.info("Step 1: Navigate to LoginPage");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Click Logo link
        LOG.info("Step 2: Click Logo link");
        ExtentReportManager.info("Step 2: Click Logo link");
        loginPage.getTopNavigation().clickOnLogo();

        ///VP: Verify navigate to Home Page successfully
        LOG.info("VP: Verify navigate to Home Page successfully");
        ExtentReportManager.info("VP: Verify navigate to Home Page successfully");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://demo1.cybersoft.edu.vn/", "Logo navigation to Home Page is incorrect!");
    }

    @Test(priority = 2, groups = "topNavigateBar")
    public void verify_Navigate_To_Schedule() {
        ///Step 1: Click 'Lich Chieu'
        LOG.info("Step 1: Click 'Lich Chieu'");
        ExtentReportManager.info("Step 1: Click 'Lich Chieu'");
        homePage.getTopNavigation().clickOnSchedule();

        ///VP: Verify Schedule is Displays
        LOG.info("VP: Verify Schedule is Displays");
        ExtentReportManager.info("VP: Verify Schedule is Displays");
        boolean recordingSchedule = homePage.getTopNavigation().isSchedule();
        Assert.assertTrue(recordingSchedule, "Schedule is not displayed");
    }

    @Test(priority = 3, groups = "topNavigateBar")
    public void verify_Navigate_To_Cinema() {
        ///Step 1: Click 'Cinema'
        LOG.info("Step 1: Click 'Cinema'");
        ExtentReportManager.info("Step 1: Click 'Cinema'");
        homePage.getTopNavigation().clickOnCinema();

        ///VP: Verify Cinema is Displays
        LOG.info("VP: Verify Cinema is Displays");
        ExtentReportManager.info("VP: Verify Cinema is Displays");
        boolean recordingCinema = homePage.getTopNavigation().isCinema();
        Assert.assertTrue(recordingCinema, "Cinema is not displayed");
    }

    @Test(priority = 4, groups = "topNavigateBar")
    public void verify_Navigate_To_News() {
        ///Step 1: Click 'News'
        LOG.info("Step 1: Click 'News'");
        ExtentReportManager.info("Step 1: Click 'News'");
        homePage.getTopNavigation().clickOnNews();

        ///VP: Verify News is Displays
        LOG.info("VP: Verify News is Displays");
        ExtentReportManager.info("VP: Verify News is Displays");
        boolean recordingNews = homePage.getTopNavigation().isNews();
        Assert.assertTrue(recordingNews, "News is not displayed");
    }

    @Test(priority = 5, groups = "topNavigateBar")
    public void verify_Navigate_To_Application() {
        ///Step 1: Click 'Application'
        LOG.info("Step 1: Click 'Application'");
        ExtentReportManager.info("Step 1: Click 'Application'");
        homePage.getTopNavigation().clickOnApplication();

        ///VP: Verify Application is Displays
        LOG.info("VP: Verify Application is Displays");
        ExtentReportManager.info("VP: Verify Application is Displays");
        boolean recordingApplication = homePage.getTopNavigation().isApplication();
        Assert.assertTrue(recordingApplication, "Application is not displayed");
    }

    @Test(priority = 6, groups = "topNavigateBar")
    public void verify_Hover_Menu_Changes_Color() {

        ///Step 1: Get default color of 'Lịch Chiếu'
        LOG.info("Step 1: Get default color of 'Lịch Chiếu'");
        ExtentReportManager.info("Step 1: Get default color of 'Lịch Chiếu'");

        String defaultColor = homePage.getTopNavigation().getScheduleColor();

        ///Step 2: Hover on 'Lịch Chiếu'
        LOG.info("Step 2: Hover on 'Lịch Chiếu'");
        ExtentReportManager.info("Step 2: Hover on 'Lịch Chiếu'");

        homePage.getTopNavigation().hoverSchedule();

        ///VP: Verify menu color is changed after hover
        LOG.info("VP: Verify 'Lịch Chiếu' color is changed after hover");
        ExtentReportManager.info("VP: Verify 'Lịch Chiếu' color is changed after hover");

        String hoverColor = homePage.getTopNavigation().getScheduleColor();

        Assert.assertNotEquals(
                hoverColor,
                defaultColor,
                "'Lịch Chiếu' menu color did not change after hover"
        );
    }

    @Test(priority = 7, groups = "topNavigateBar")
    public void verify_Navigation_Menu_Layout() {

        ///Step 1: Verify logo is displayed in top navigation
        LOG.info("Step 1: Verify logo is displayed in top navigation");
        ExtentReportManager.info("Step 1: Verify logo is displayed in top navigation");

        Assert.assertTrue(
                homePage.getTopNavigation().isLogo(),
                "Logo is not displayed in top navigation"
        );

        ///Step 2: Verify Schedule menu is displayed
        LOG.info("Step 2: Verify Schedule menu is displayed");
        ExtentReportManager.info("Step 2: Verify Schedule menu is displayed");

        Assert.assertTrue(
                homePage.getTopNavigation().isSchedule(),
                "Schedule menu is not displayed"
        );

        ///Step 3: Verify Cinema menu is displayed
        LOG.info("Step 3: Verify Cinema menu is displayed");
        ExtentReportManager.info("Step 3: Verify Cinema menu is displayed");

        Assert.assertTrue(
                homePage.getTopNavigation().isCinema(),
                "Cinema menu is not displayed"
        );

        ///Step 4: Verify News menu is displayed
        LOG.info("Step 4: Verify News menu is displayed");
        ExtentReportManager.info("Step 4: Verify News menu is displayed");

        Assert.assertTrue(
                homePage.getTopNavigation().isNews(),
                "News menu is not displayed"
        );

        ///Step 5: Verify Application menu is displayed
        LOG.info("Step 5: Verify Application menu is displayed");
        ExtentReportManager.info("Step 5: Verify Application menu is displayed");

        Assert.assertTrue(
                homePage.getTopNavigation().isApplication(),
                "Application menu is not displayed"
        );
    }
}
