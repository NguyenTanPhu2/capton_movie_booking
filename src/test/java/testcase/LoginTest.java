package testcase;

import base.BaseTest;
import constants.TimeOutConstants;
import data.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.components.LogOut;
import pages.modals.CommonModal;
import report.ExtentReportManager;

public class LoginTest extends BaseTest {

    /// Page Object
    LoginPage loginPage;
    CommonModal commonModal;
    LogOut logOutPage;
    HomePage homePage;

    @BeforeMethod
    public void initializePages() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        commonModal = new CommonModal(driver);
        logOutPage = new LogOut(driver);
    }

    @Test(priority = 1, dataProvider = "login-credentials", dataProviderClass = TestDataProvider.class, groups = "login")
    public void verify_Login_Successfully(String account, String password) {

        ///Step 1: Navigate to LoginPage
        LOG.info("Step 1: Navigate to LoginPage");
        ExtentReportManager.info("Step 1: Navigate to LoginPage");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Enter Account
        LOG.info("Step 2: Enter Account");
        ExtentReportManager.info("Step 2: Enter Account");
        loginPage.enterAccount(account);

        ///Step 3: Enter Password
        LOG.info("Step 3: Enter Password");
        ExtentReportManager.info("Step 3: Enter Password");
        loginPage.enterPassword(password);

        ///Step 4: Click Login
        LOG.info("Step 4: Click Login");
        ExtentReportManager.info("Step 4: Click Login");
        loginPage.clickBtnLogin();

        ///VP: Verify Login successfully
        LOG.info("VP: Verify Login Successfully");
        ExtentReportManager.info("VP: Verify Login Successfully");
        String recordingLogin = commonModal.getMessageText();
        Assert.assertEquals(recordingLogin, "Đăng nhập thành công", "Login success message is incorrect");
    }

    @Test(priority = 2, dataProvider = "invalid-login-password", dataProviderClass = TestDataProvider.class, groups = "login")
    public void verify_Login_With_Invalid_Password(String account, String password) {
        ///Step 1: Navigate to LoginPage
        LOG.info("Step 1: Navigate to LoginPage");
        ExtentReportManager.info("Step 1: Navigate to LoginPage");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Enter Account
        LOG.info("Step 2: Enter Account");
        ExtentReportManager.info("Step 2: Enter Account");
        loginPage.enterAccount(account);

        ///Step 3: Enter wrong Password
        LOG.info("Step 3: Enter wrong Password");
        ExtentReportManager.info("Step 3: Enter wrong Password");
        loginPage.enterPassword(password);

        ///Step 4: Click Login
        LOG.info("Step 4: Click Login");
        ExtentReportManager.info("Step 4: Click Login");
        loginPage.clickBtnLogin();

        ///VP: Invalid password error message is displayed correctly
        LOG.info("VP: Invalid password error message is displayed correctly");
        ExtentReportManager.info("VP: Invalid account or password error message is displayed correctly");
        String recordingLogin = commonModal.getMuitAler();
        Assert.assertEquals(recordingLogin, "Tài khoản hoặc mật khẩu không đúng!", "Invalid password error message is incorrect");
    }

    @Test(priority = 3, dataProvider = "invalid-login-account", dataProviderClass = TestDataProvider.class, groups = "login")
    public void verify_Login_With_Invalid_Account(String account, String password) {
        ///Step 1: Navigate to LoginPage
        LOG.info("Step 1: Navigate to LoginPage");
        ExtentReportManager.info("Step 1: Navigate to LoginPage");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Enter wrong Account
        LOG.info("Step 2: Enter wrong Account");
        ExtentReportManager.info("Step 2: Enter wrong Account");
        loginPage.enterAccount(account);

        ///Step 3: Enter  Password
        LOG.info("Step 3: Enter  Password");
        ExtentReportManager.info("Step 3: Enter Password");
        loginPage.enterPassword(password);

        ///Step 4: Click Login
        LOG.info("Step 4: Click Login");
        ExtentReportManager.info("Step 4: Click Login");
        loginPage.clickBtnLogin();

        ///VP: Invalid account error message is displayed correctly
        LOG.info("VP: Invalid account or password error message is displayed correctly");
        ExtentReportManager.info("VP: Invalid account error message is displayed correctly");
        String recordingLogin = commonModal.getMuitAler();
        Assert.assertEquals(recordingLogin, "Tài khoản hoặc mật khẩu không đúng!", "Invalid account error message is incorrect");

    }

    @Test(priority = 4, groups = "login")
    public void verify_Login_With_Empty_Fields() {
        ///Step 1: Navigate to LoginPage
        LOG.info("Step 1: Navigate to LoginPage");
        ExtentReportManager.info("Step 1: Navigate to LoginPage");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Enter empty Account
        LOG.info("Step 2: Enter empty Account");
        ExtentReportManager.info("Step 2: Enter empty Account");
        loginPage.enterAccount("");

        ///Step 3: Enter empty Password
        LOG.info("Step 3: Enter empty Password");
        ExtentReportManager.info("Step 3: Enter empty Password");
        loginPage.enterPassword("");

        ///Step 4: Click Login
        LOG.info("Step 4: Click Login");
        ExtentReportManager.info("Step 4: Click Login");
        loginPage.clickBtnLogin();

        ///VP 1: Verify empty Account
        LOG.info("VP 1: Verify empty Account");
        ExtentReportManager.info("VP 1: Verify empty Account");
        String recordingEmptyAccount = commonModal.getHelpMessageText("account");
        Assert.assertEquals(recordingEmptyAccount, "Đây là trường bắt buộc !", "Required account field error message is incorrect");

        ///VP 2: Verify empty Password
        LOG.info("VP 2: Verify empty Password");
        ExtentReportManager.info("VP 2: Verify empty Password");
        String recordingEmptyPassword = commonModal.getHelpMessageText("password");
        Assert.assertEquals(recordingEmptyPassword, "Đây là trường bắt buộc !", "Required account field error message is incorrect");
    }

    @Test(priority = 5, dataProvider = "login-credentials", dataProviderClass = TestDataProvider.class, groups = "login")
    public void verify_Remember_Account(String account, String password) {

        ///Step 1: Navigate to LoginPage
        LOG.info("Step 1: Navigate to LoginPage");
        ExtentReportManager.info("Step 1: Navigate to LoginPage");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Enter Account
        LOG.info("Step 2: Enter Account");
        ExtentReportManager.info("Step 2: Enter Account");
        loginPage.enterAccount(account);

        ///Step 3: Enter Password
        LOG.info("Step 3: Enter Password");
        ExtentReportManager.info("Step 3: Enter Password");
        loginPage.enterPassword(password);

        ///Step 4: Click Remember
        LOG.info("Step 4: Click Remember");
        ExtentReportManager.info("Step 4: Click Remember");
        loginPage.clickBtnRemember();

        ///Step 5: Click Login
        LOG.info("Step 5: Click Login");
        ExtentReportManager.info("Step 5: Click Login");
        loginPage.clickBtnLogin();

        ///Step 6: Click Log Out
        LOG.info("Step 6: Click to Log Out");
        ExtentReportManager.info("Step 6: Click to Log Out");
        loginPage.getTopNavigation().clickOnLogOut();

        ///Step 7: Confirm Log Out
        LOG.info("Step 7: Confirm Log Out");
        ExtentReportManager.info("Step 7: Confirm Log Out");
        logOutPage.clickConfirmLogOut();

        ///Step 8: Navigate to LoginPage again
        LOG.info("Step 8: Navigate to LoginPage again");
        ExtentReportManager.info("Step 8: Navigate to LoginPage again");
        homePage.getTopNavigation().navigateToLoginPage();

        commonModal.waitModalDisappear();

        ///VP 1: Account information is displayed correctly
        LOG.info("VP 1: Account information is displayed correctly");
        ExtentReportManager.info("VP 1: Account information is displayed correctly");
        String recordingAccount = loginPage.getTxtAccount();
        Assert.assertEquals(recordingAccount, account, "Account information is incorrect");

        ///VP 2: Password information is displayed correctly
        LOG.info("VP 2: Password information is displayed correctly");
        ExtentReportManager.info("VP 2: Password information is displayed correctly");
        String recordingPassword = loginPage.getTxtPassword();
        Assert.assertEquals(recordingPassword, password, "Password information is incorrect");
    }

    @Test(priority = 6, groups = "login")
    public void verify_Navigate_To_LoginPage() {
        ///Step 1: Navigate to LoginPage
        LOG.info("Step 1: Navigate to LoginPage");
        ExtentReportManager.info("Step 1: Navigate to LoginPage");
        homePage.getTopNavigation().navigateToLoginPage();

        ///VP: Verify navigate to Login Page
        LOG.info("VP: Verify navigate to Login Page");
        ExtentReportManager.info("VP: Verify navigate to Login Page");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://demo1.cybersoft.edu.vn/sign-in", "Current URL is incorrect");
    }

    @Test(priority = 7, groups = "login")
    public void verify_Navigate_To_Register() {
        ///Step 1: Navigate to LoginPage
        LOG.info("Step 1: Navigate to LoginPage");
        ExtentReportManager.info("Step 1: Navigate to LoginPage");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Navigate to RegisterPage
        LOG.info("Step 2: Navigate to RegisterPage");
        ExtentReportManager.info("Step 2: Navigate to RegisterPage");
        loginPage.clickRegisterLink();

        ///VP: Verify navigate to RegisterPage
        LOG.info("VP: Verify navigate to RegisterPage");
        ExtentReportManager.info("VP: Verify navigate to RegisterPage");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://demo1.cybersoft.edu.vn/sign-up", "Current URL is incorrect");
    }

    @Test(priority = 8, groups = "login")
    public void verify_Login_With_Empty_Information() {
        ///Step 1: Navigate to LoginPage
        LOG.info("Step 1: Navigate to LoginPage");
        ExtentReportManager.info("Step 1: Navigate to LoginPage");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Enter field Login
        LOG.info("Step 2: Enter field Login");
        ExtentReportManager.info("Step 2: Enter field Login");
        loginPage.login("", "");
    }

    @Test(priority = 9,
            dataProvider = "login-credentials",
            dataProviderClass = TestDataProvider.class, groups = "login")
    public void verify_Login_State_After_Refresh(String account, String password) {
        ///Step 1: Navigate to LoginPage
        LOG.info("Step 1: Navigate to LoginPage");
        ExtentReportManager.info("Step 1: Navigate to LoginPage");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Login Account
        LOG.info("Step 2: Login Account");
        ExtentReportManager.info("Step 2: Login Account");
        loginPage.login(account, password);

        commonModal.getWebDriverWait(TimeOutConstants.DEFAULT_TIMEOUT);

        ///Step 5: Refresh Page
        LOG.info("Step 5: Refresh Page");
        ExtentReportManager.info("Step 5: Refresh Page");
        loginPage.refreshPage();

        ///VP: Verify after Refresh
        LOG.info("VP: Verify after Refresh");
        ExtentReportManager.info("VP: Verify after Refresh");
        String recordingIsLoggingIn = loginPage.getTxtProfile();
        Assert.assertEquals(recordingIsLoggingIn, account, "Account is incorrect");

    }
}
