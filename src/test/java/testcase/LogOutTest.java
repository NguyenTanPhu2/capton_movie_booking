package testcase;

import base.BaseTest;
import data.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.components.LogOut;
import pages.modals.CommonModal;
import report.ExtentReportManager;

public class LogOutTest extends BaseTest {

    // Page Object
    HomePage homePage;
    LoginPage loginPage;
    LogOut logOut;
    CommonModal commonModal;

    @BeforeMethod
    public void initializePages() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        logOut = new LogOut(driver);
        commonModal = new CommonModal(driver);
    }

    @Test(priority = 1, dataProvider = "logout-credentials",
            dataProviderClass = TestDataProvider.class, groups = "logout")
    public void verify_Logout_Successfully(String account, String password) {
        ///Step 1: Navigate to Login Page
        LOG.info("Step 1: Navigate To Login Page");
        ExtentReportManager.info("Step 1: Navigate To Login Page");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Login account
        LOG.info("Step 2: Login account");
        ExtentReportManager.info("Step 2: Login account");
        loginPage.login(account, password);

        ///Step 3: Click Log Out link
        LOG.info("Step 3: Click Log Out link");
        ExtentReportManager.info("Step 3: Click Log Out link");
        homePage.getTopNavigation().clickOnLogOut();

        ///Step 4: Confirm LogOut
        LOG.info("Step 4: Confirm Logout");
        ExtentReportManager.info("Step 4: Confirm Logout");
        logOut.clickConfirmLogOut();

        ///VP: Verify Log Out Successfully
        LOG.info("VP: Verify Log Out Successfully");
        ExtentReportManager.info("VP: Verify Log Out Successfully");
        String recordingLogOut = commonModal.getMessageText();
        Assert.assertEquals(recordingLogOut, "Đã đăng xuất", "Logout message is incorrect");
    }

    @Test(priority = 2, dataProvider = "logout-credentials",
            dataProviderClass = TestDataProvider.class, groups = "logout")
    public void verify_Logout_Status_After_Logout(String account, String password) {
        ///Step 1: Navigate to Login Page
        LOG.info("Step 1: Navigate To Login Page");
        ExtentReportManager.info("Step 1: Navigate To Login Page");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Login account
        LOG.info("Step 2: Login account");
        ExtentReportManager.info("Step 2: Login account");
        loginPage.login(account, password);

        ///Step 3: Click Log Out link
        LOG.info("Step 3: Click Log Out link");
        ExtentReportManager.info("Step 3: Click Log Out link");
        homePage.getTopNavigation().clickOnLogOut();

        ///VP: Verify button LogOut is Correctly
        LOG.info("VP: Verify button LogOut is Correctly");
        ExtentReportManager.info("VP: Verify button LogOut is Correctly");
        String recordingLogOut = commonModal.getMessageText();
        Assert.assertEquals(recordingLogOut, "Bạn có muốn đăng xuất ?", "Logout confirmation message is incorrect");
    }

    @Test(priority = 3, dataProvider = "logout-credentials",
            dataProviderClass = TestDataProvider.class, groups = "logout")
    public void verify_Back_Button_After_Logout(String account, String password) {
        ///Step 1: Navigate to Login Page
        LOG.info("Step 1: Navigate To Login Page");
        ExtentReportManager.info("Step 1: Navigate To Login Page");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Login account
        LOG.info("Step 2: Login account");
        ExtentReportManager.info("Step 2: Login account");
        loginPage.login(account, password);

        ///Step 3: Click LogOut link
        LOG.info("Step 3: Click LogOut link");
        ExtentReportManager.info("Step 3: Click LogOut link");
        homePage.getTopNavigation().clickOnLogOut();

        ///Step 4: Click confirm logOut
        LOG.info("Step 4: Click confirm logOut");
        ExtentReportManager.info("Step 4: Click confirm logOut");
        logOut.clickConfirmLogOut();

        ///Step 5: Click browser Back button
        LOG.info("Step 5: Click browser Back button");
        ExtentReportManager.info("Step 5: Click browser Back button");
        homePage.clickBackBrowser();

        ///VP : Verify user cannot return to authenticated state
        LOG.info("VP : Verify user cannot return to authenticated state");
        ExtentReportManager.info("VP: Verify user cannot return to authenticated state");
        boolean recordingAfterBack = homePage.getTopNavigation().isLoginDisplays();
        Assert.assertTrue(recordingAfterBack, " User can return to authenticated state after logout");
    }

    @Test(priority = 4, dataProvider = "logout-credentials",
            dataProviderClass = TestDataProvider.class, groups = "logout")
    public void verify_Access_Old_URL_After_Logout(String account, String password) {
        ///Step 1: Navigate to Login Page
        LOG.info("Step 1: Navigate To Login Page");
        ExtentReportManager.info("Step 1: Navigate To Login Page");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Login account
        LOG.info("Step 2: Login account");
        ExtentReportManager.info("Step 2: Login account");
        loginPage.login(account, password);

        ///Step 3: Capture URL then logout
        LOG.info("Step 3: Capture URL then logout");
        ExtentReportManager.info("Step 3: Capture URL then logout");
        String previousUrl = driver.getCurrentUrl();
        homePage.getTopNavigation().clickOnLogOut();
        logOut.clickConfirmLogOut();

        ///Step 4: Access the old url again
        LOG.info("Step 4: Access the old url again");
        ExtentReportManager.info("Step 4: Access the old url again");
        driver.navigate().to(previousUrl);

        ///VP: Verify user cannot access authenticated page after logout
        LOG.info("VP: Verify user cannot access authenticated page after logout");
        ExtentReportManager.info("VP: Verify user cannot access authenticated page after logout");
        Assert.assertTrue(homePage.getTopNavigation().isLoginDisplays(), "User can still access protected page after logout");
    }
}
