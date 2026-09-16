package testcase;

import base.BaseTest;
import data.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import pages.modals.CommonModal;
import report.ExtentReportManager;

public class RegisterTest extends BaseTest {

    /// Page Object
    private RegisterPage registerPage;
    private CommonModal commonModal;
    private HomePage homePage;
    private LoginPage loginPage;

    //Khoi tao page
    @BeforeMethod
    public void initializePages() {
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        commonModal = new CommonModal(driver);
        loginPage = new LoginPage(driver);
    }


    @Test(priority = 1, dataProvider = "register-valid", dataProviderClass = TestDataProvider.class, groups = "Register")
    public void verify_Register_Successfully(String account, String password, String fullName, String email) {

        ///Step 1: Navigate to Register Page
        LOG.info("Step 1: Navigate to Register Page");
        ExtentReportManager.info("Step 1: Navigate to Register Page");
        homePage.getTopNavigation().navigateToRegisterPage();

        ///Step 2: Enter Account
        LOG.info("Step 2: Enter Account");
        ExtentReportManager.info("Step 2: Enter Account");
        registerPage.enterAccount(account);

        ///Step 3: Enter password
        LOG.info("Step 3: Enter password");
        ExtentReportManager.info("Step 3: Enter password");
        registerPage.enterPassword(password);

        ///Step 4: Enter Confirm Password
        LOG.info("Step 4: Enter Confirm Password");
        ExtentReportManager.info("Step 4: Enter Confirm Password");
        registerPage.enterConfirmPassword(password);

        ///Step 5: Enter FullName
        LOG.info("Step 5: Enter FullName");
        ExtentReportManager.info("Step 5: Enter FullName");
        registerPage.enterFullName(fullName);

        ///Step 6: Enter Email
        LOG.info("Step 6: Enter Email");
        ExtentReportManager.info("Step 6: Enter Email");
        registerPage.enterEmail(email);

        ///Step 7: Click Register
        LOG.info("Step 7: Click Register");
        ExtentReportManager.info("Step 7: Click Register");
        registerPage.clickBtnRegister();

        ///VP 1: Verify Register Successfully
        LOG.info("VP: Verify Register Successfully");
        ExtentReportManager.info("VP: Verify Register Successfully");
        String recordingRegister = commonModal.getMessageText();
        Assert.assertEquals(recordingRegister, "Đăng ký thành công", "Registration success message is incorrect");

        commonModal.waitModalDisappear();

        ///Step 8: Navigate to LoginPage
        LOG.info("Step 8: Navigate to LoginPage");
        ExtentReportManager.info("Step 8: Navigate to LoginPage");
        registerPage.clickLnkLogin();

        ///Step 9: Login new account
        LOG.info("Step 9: Login new account");
        ExtentReportManager.info("Step 9: Login new account");
        loginPage.login(account, password);
    }

    @Test(priority = 2,groups = "Register")
    public void verify_Register_With_Empty_Required_Fields() {
        ///Step 1: Navigate to Register Page
        LOG.info("Step 1: Navigate to Register Page");
        ExtentReportManager.info("Step 1: Navigate to Register Page");
        homePage.getTopNavigation().navigateToRegisterPage();

        ///Step 2: Empty Account
        LOG.info("Step 2: Empty Account");
        ExtentReportManager.info("Step 2: Empty Account");
        registerPage.enterAccount("");

        ///Step 3: Empty password
        LOG.info("Step 3: Empty password");
        ExtentReportManager.info("Step 3: Empty password");
        registerPage.enterPassword("");

        ///Step 4: Empty Confirm Password
        LOG.info("Step 4: Empty Confirm Password");
        ExtentReportManager.info("Step 4: Empty Confirm Password");
        registerPage.enterConfirmPassword("");

        ///Step 5: Empty FullName
        LOG.info("Step 5: Empty FullName");
        ExtentReportManager.info("Step 5: Empty FullName");
        registerPage.enterFullName("");

        ///Step 6: Empty Email
        LOG.info("Step 6: Empty Email");
        ExtentReportManager.info("Step 6: Empty Email");
        registerPage.enterEmail("");

        ///Step 7: Click Register
        LOG.info("Step 7: Click Register");
        ExtentReportManager.info("Step 7: Click Register");
        registerPage.clickBtnRegister();

        ///VP 1: Verify error messages for empty fields Account
        LOG.info("VP 1: Verify error messages for empty fields Account");
        ExtentReportManager.info("VP 1: Verify error messages for empty fields Password");
        String recordingEmptyAccount = commonModal.getHelpMessageText("account");
        Assert.assertEquals(recordingEmptyAccount, "Đây là trường bắt buộc !", "Required account field error message is incorrect");

        ///VP 2: Verify error messages for empty fields Password
        LOG.info("VP 2: Verify error messages for empty fields Password");
        ExtentReportManager.info("VP 2: Verify error messages for empty fields Password");
        String recordingEmptyPassword = commonModal.getHelpMessageText("password");
        Assert.assertEquals(recordingEmptyPassword, "Đây là trường bắt buộc !", "Required account field error message is incorrect");

        ///VP 3: Verify error messages for empty fields Confirm Password
        LOG.info("VP 3: Verify error messages for empty fields Confirm Password");
        ExtentReportManager.info("VP 3: Verify error messages for empty fields Confirm Password");
        String recordingEmptyConfirmPassword = commonModal.getHelpMessageText("confirmPassword");
        Assert.assertEquals(recordingEmptyConfirmPassword, "Đây là trường bắt buộc !", "Required account field error message is incorrect");

        ///VP 4: Verify error messages for empty fields FullName
        LOG.info("VP 4: Verify error messages for empty fields FullName");
        ExtentReportManager.info("VP 4: Verify error messages for empty fields FullName");
        String recordingEmptyFullName = commonModal.getHelpMessageText("fullName");
        Assert.assertEquals(recordingEmptyFullName, "Đây là trường bắt buộc !", "Required account field error message is incorrect");

        ///VP 5: Verify error messages for empty fields Email
        LOG.info("VP 5: Verify error messages for empty fields Email");
        ExtentReportManager.info("VP 5: Verify error messages for empty fields Email");
        String recordingEmptyEmail = commonModal.getHelpMessageText("email");
        Assert.assertEquals(recordingEmptyFullName, "Đây là trường bắt buộc !", "Required account field error message is incorrect");

    }

    @Test(priority = 3, dataProvider = "register-invalid-email",
         dataProviderClass = TestDataProvider.class,groups = "Register")
    public void verify_Register_With_Invalid_Email_Format(String account, String password, String fullName, String email) {
        ///Step 1: Navigate to Register Page
        LOG.info("Step 1: Navigate to Register Page");
        ExtentReportManager.info("Step 1: Navigate to Register Page");
        homePage.getTopNavigation().navigateToRegisterPage();

        ///Step 2: Enter Account
        LOG.info("Step 2: Enter Account");
        ExtentReportManager.info("Step 2: Enter Account");
        registerPage.enterAccount(account);

        ///Step 3: Enter password
        LOG.info("Step 3: Enter password");
        ExtentReportManager.info("Step 3: Enter password");
        registerPage.enterPassword(password);

        ///Step 4: Enter Confirm Password
        LOG.info("Step 4: Enter Confirm Password");
        ExtentReportManager.info("Step 4: Enter Confirm Password");
        registerPage.enterConfirmPassword(password);

        ///Step 5: Enter FullName
        LOG.info("Step 5: Enter FullName");
        ExtentReportManager.info("Step 5: Enter FullName");
        registerPage.enterFullName(fullName);

        ///Step 6: Enter Email
        LOG.info("Step 6: Enter Email");
        ExtentReportManager.info("Step 6: Enter Email");
        registerPage.enterEmail(email);

        ///Step 7: Click Register
        LOG.info("Step 7: Click Register");
        ExtentReportManager.info("Step 7: Click Register");
        registerPage.clickBtnRegister();

        ///VP: Invalid email error message is displayed correctly
        LOG.info("VP: Invalid email error message is displayed correctly");
        ExtentReportManager.info("VP: Invalid email error message is displayed correctly");
        String recordingErrorEmail = commonModal.getMuitAler();
        Assert.assertEquals(recordingErrorEmail, "email không hợp lệ", "Invalid email error message is incorrect");
    }

    @Test(priority = 4, dataProvider = "register-hide-password", dataProviderClass = TestDataProvider.class, groups = "Register")
    public void verify_Hide_Password(String account, String password, String fullName, String email) {
        ///Step 1: Navigate to Register Page
        LOG.info("Step 1: Navigate to Register Page");
        ExtentReportManager.info("Step 1: Navigate to Register Page");
        homePage.getTopNavigation().navigateToRegisterPage();

        ///Step 2: Enter Account
        LOG.info("Step 2: Enter Account");
        ExtentReportManager.info("Step 2: Enter Account");
        registerPage.enterAccount(account);

        ///Step 3: Enter password
        LOG.info("Step 3: Enter password");
        ExtentReportManager.info("Step 3: Enter password");
        registerPage.enterPassword(password);

        ///Step 4: Show/Hide Password
        LOG.info("Step 4: Show/Hide Password");
        ExtentReportManager.info("Step 4: Show/Hide Password");
        registerPage.clickBtnHide();

        ///Step 5: Enter Confirm Password
        LOG.info("Step 5: Enter Confirm Password");
        ExtentReportManager.info("Step 5: Enter Confirm Password");
        registerPage.enterConfirmPassword(password);

        ///Step 6: Enter FullName
        LOG.info("Step 6: Enter FullName");
        ExtentReportManager.info("Step 6: Enter FullName");
        registerPage.enterFullName(fullName);

        ///Step 7: Enter Email
        LOG.info("Step 6: Enter Email");
        ExtentReportManager.info("Step 7: Enter Email");
        registerPage.enterEmail(email);

        ///VP: Hide button should reveal the password field
        LOG.info("VP: Hide button should reveal the password field");
        ExtentReportManager.info("VP: Hide button should reveal the password field");
        boolean recordingHideVisible = registerPage.isPasswordVisible();
        Assert.assertTrue(recordingHideVisible, "Hide button was not clicked or did not reveal the password");
    }

    @Test(priority = 5, dataProvider = "register-wrong-confirm-password",
            dataProviderClass = TestDataProvider.class,groups = "Register")
    public void verify_Confirm_Password_Matching(String account, String fullName, String password, String wrongPassword) {

        ///Step 1: Navigate to Register Page
        LOG.info("Step 1: Navigate to Register Page");
        ExtentReportManager.info("Step 1: Navigate to Register Page");
        homePage.getTopNavigation().navigateToRegisterPage();

        ///Step 2: Enter Account
        LOG.info("Step 2: Enter Account");
        ExtentReportManager.info("Step 2: Enter Account");
        registerPage.enterAccount(account);

        ///Step 3: Enter password
        LOG.info("Step 3: Enter password");
        ExtentReportManager.info("Step 3: Enter password");
        registerPage.enterPassword(password);

        ///Step 4: Show/Hide Password
        LOG.info("Step 4: Show/Hide Password");
        ExtentReportManager.info("Step 4: Show/Hide Password");
        registerPage.clickBtnHide();

        ///Step 5: Enter Confirm Password
        LOG.info("Step 5: Enter Confirm Password");
        ExtentReportManager.info("Step 5: Enter Confirm Password");
        registerPage.enterConfirmPassword(wrongPassword);

        ///Step 6: Enter FullName
        LOG.info("Step 6: Enter FullName");
        ExtentReportManager.info("Step 6: Enter FullName");
        registerPage.enterFullName(fullName);

        //VP: Verify Confirm password
        LOG.info("VP: Verify Confirm password");
        ExtentReportManager.info("VP: Verify Confirm password");
        String recordingNotMatchPassword = commonModal.getHelpMessageText("confirmPassword");
        Assert.assertEquals(recordingNotMatchPassword, "Mật khẩu không khớp !", "Wrong Confirm Password");
    }


    @Test(priority = 6,groups = "Register")
    public void verify_Register_Button() {
        ///Step 1: Navigate to Register Page
        LOG.info("Step 1: Navigate to Register Page");
        ExtentReportManager.info("Step 1: Navigate to Register Page");
        homePage.getTopNavigation().navigateToRegisterPage();

        ///VP: Verify navigate to RegisterPage
        LOG.info("VP: Verify navigate to RegisterPage");
        ExtentReportManager.info("VP: Verify navigate to RegisterPage");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://demo1.cybersoft.edu.vn/sign-up", "Current URL is incorrect");
    }

    @Test(priority = 7,groups = "Register")
    public void verify_Navigate_To_Login() {
        ///Step 1: Navigate to Register Page
        LOG.info("Step 1: Navigate to Register Page");
        ExtentReportManager.info("Step 1: Navigate to Register Page");
        homePage.getTopNavigation().navigateToRegisterPage();

        ///Step 2: Navigate to LoginPage
        LOG.info("Step 2: Navigate to Login Page");
        ExtentReportManager.info("Step 2: Navigate to LoginPage");
        registerPage.clickLnkLogin();

        ///VP: Verify navigate to Login Page
        LOG.info("VP: Verify navigate to Login Page");
        ExtentReportManager.info("VP: Verify navigate to Login Page");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://demo1.cybersoft.edu.vn/sign-in", "Current URL is incorrect");

    }

    @Test(priority = 8,dataProvider = "register-existing-email",
            dataProviderClass = TestDataProvider.class,groups = "Register")
    public void verify_Register_With_Existing_Email(String account, String password, String fullName, String existingEmail) {
        ///Step 1: Navigate to Register Page
        LOG.info("Step 1: Navigate to Register Page");
        ExtentReportManager.info("Step 1: Navigate to Register Page");
        homePage.getTopNavigation().navigateToRegisterPage();

        ///Step 2: Enter Account
        LOG.info("Step 2: Enter Account");
        ExtentReportManager.info("Step 2: Enter Account");
        registerPage.enterAccount(account);

        ///Step 3: Enter password
        LOG.info("Step 3: Enter password");
        ExtentReportManager.info("Step 3: Enter password");
        registerPage.enterPassword(password);

        ///Step 4: Enter Confirm Password
        LOG.info("Step 4: Enter Confirm Password");
        ExtentReportManager.info("Step 4: Enter Confirm Password");
        registerPage.enterConfirmPassword(password);

        ///Step 5: Enter FullName
        LOG.info("Step 5: Enter FullName");
        ExtentReportManager.info("Step 5: Enter FullName");
        registerPage.enterFullName(fullName);

        ///Step 6: Enter Email
        LOG.info("Step 6: Enter Email");
        ExtentReportManager.info("Step 6: Enter Email");
        registerPage.enterEmail(existingEmail);

        ///Step 7: Click Register
        LOG.info("Step 7: Click Register");
        ExtentReportManager.info("Step 7: Click Register");
        registerPage.clickBtnRegister();

        ///VP: Existing email error message is displayed correctly
        LOG.info("VP: Existing email error message is displayed correctly");
        ExtentReportManager.info("VP: Existing email error message is displayed correctly");
        String recordingExistingEmail = commonModal.getMuitAler();
        Assert.assertEquals(recordingExistingEmail, "Email đã tồn tại!", "Existing email error message is incorrect");

    }

    @Test(priority = 9, dataProvider = "register-invalid-password",
            dataProviderClass = TestDataProvider.class,groups = "Register")
    public void verify_Register_With_Invalid_Password(String account, String password) {
        ///Step 1: Navigate to Register Page
        LOG.info("Step 1: Navigate to Register Page");
        ExtentReportManager.info("Step 1: Navigate to Register Page");
        homePage.getTopNavigation().navigateToRegisterPage();

        ///Step 2: Enter Account
        LOG.info("Step 2: Enter Account");
        ExtentReportManager.info("Step 2: Enter Account");
        registerPage.enterAccount(account);

        ///Step 3: Invalid password
        LOG.info("Step 3: Invalid password");
        ExtentReportManager.info("Step 3: Invalid password");
        registerPage.enterPassword(password);

        ///Step 4: Enter Confirm Password
        LOG.info("Step 4: Enter Confirm Password");
        ExtentReportManager.info("Step 4: Enter Confirm Password");
        registerPage.enterConfirmPassword(password);

        ///VP: Verify invalid Password
        LOG.info("VP: Verify Invalid Password");
        ExtentReportManager.info("VP: Verify Invalid Password");
        String recordingInvalidPassword = commonModal.getHelpMessageText("password");

        Assert.assertEquals(recordingInvalidPassword, "Mật khẩu phải có ít nhất 6 kí tự !", "Invalid Password");
    }

    @Test(priority = 10, dataProvider = "register-valid", dataProviderClass = TestDataProvider.class, groups = "Register")
    public void verify_Register_Success_Message(String account, String password, String fullName, String email) {

        ///Step 1: Navigate to Register Page
        LOG.info("Step 1: Navigate to Register Page");
        ExtentReportManager.info("Step 1: Navigate to Register Page");
        homePage.getTopNavigation().navigateToRegisterPage();

        ///Step 2: Enter Account
        LOG.info("Step 2: Enter Account");
        ExtentReportManager.info("Step 2: Enter Account");
        registerPage.enterAccount(account);

        ///Step 3: Enter password
        LOG.info("Step 3: Enter password");
        ExtentReportManager.info("Step 3: Enter password");
        registerPage.enterPassword(password);

        ///Step 4: Enter Confirm Password
        LOG.info("Step 4: Enter Confirm Password");
        ExtentReportManager.info("Step 4: Enter Confirm Password");
        registerPage.enterConfirmPassword(password);

        ///Step 5: Enter FullName
        LOG.info("Step 5: Enter FullName");
        ExtentReportManager.info("Step 5: Enter FullName");
        registerPage.enterFullName(fullName);

        ///Step 6: Enter Email
        LOG.info("Step 6: Enter Email");
        ExtentReportManager.info("Step 6: Enter Email");
        registerPage.enterEmail(email);

        ///Step 7: Click Register
        LOG.info("Step 7: Click Register");
        ExtentReportManager.info("Step 7: Click Register");
        registerPage.clickBtnRegister();

        ///VP: Verify Register Successfully
        LOG.info("VP: Verify Register Successfully");
        ExtentReportManager.info("VP: Verify Register Successfully");
        String recordingRegister = commonModal.getMessageText();
        Assert.assertEquals(recordingRegister, "Đăng ký thành công", "Registration success message is incorrect");
    }


    @Test(priority = 11,dataProvider = "Whitespace-value", dataProviderClass = TestDataProvider.class, groups = "Register")
    public void verify_Register_With_Whitespace(String account, String password, String fullName, String email) {
        ///Step 1: Navigate to Register Page
        LOG.info("Step 1: Navigate to Register Page");
        ExtentReportManager.info("Step 1: Navigate to Register Page");
        homePage.getTopNavigation().navigateToRegisterPage();

        ///Step 2: Enter Account
        LOG.info("Step 2: Enter Account");
        ExtentReportManager.info("Step 2: Enter Account");
        registerPage.enterAccount(account);

        ///Step 3: Enter password
        LOG.info("Step 3: Enter password");
        ExtentReportManager.info("Step 3: Enter password");
        registerPage.enterPassword(password);

        ///Step 4: Enter Confirm Password
        LOG.info("Step 4: Enter Confirm Password");
        ExtentReportManager.info("Step 4: Enter Confirm Password");
        registerPage.enterConfirmPassword(password);

        ///Step 5: Enter FullName
        LOG.info("Step 5: Enter FullName");
        ExtentReportManager.info("Step 5: Enter FullName");
        registerPage.enterFullName(fullName);

        ///Step 6: Enter Email
        LOG.info("Step 6: Enter Email");
        ExtentReportManager.info("Step 6: Enter Email");
        registerPage.enterEmail(email);

        ///Step 7: Click Register
        LOG.info("Step 7: Click Register");
        ExtentReportManager.info("Step 7: Click Register");
        registerPage.clickBtnRegister();

        ///VP: Verify invalid format message
        LOG.info("VP: Verify invalid format message");
        ExtentReportManager.info("VP: Verify invalid format message");
        String recordingFormat = commonModal.getMuitAler();
        Assert.assertEquals(recordingFormat, "Vui lòng nhập đúng định dạng", "Invalid format message is incorrect");

    }
}
