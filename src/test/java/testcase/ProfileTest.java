package testcase;

import base.BaseTest;
import data.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;
import pages.modals.CommonModal;
import report.ExtentReportManager;


public class ProfileTest extends BaseTest {

    /// Page Object
    LoginPage loginPage;
    HomePage homePage;
    CommonModal commonModal;
    ProfilePage profilePage;

    @BeforeMethod
    public void initializePages() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        commonModal = new CommonModal(driver);
        profilePage = new ProfilePage(driver);
    }

    /// US_05 - User Profile

    @Test(priority = 1, dataProvider = "profile-login", dataProviderClass = TestDataProvider.class, groups = "profile")
    public void verify_User_Avatar_Displayed(String account, String password) {

        ///Step 1: Navigate to LoginPage
        LOG.info("Step 1: Navigate to LoginPage");
        ExtentReportManager.info("Step 1: Navigate to LoginPage");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Login account
        LOG.info("Step 2: Login account");
        ExtentReportManager.info("Step 2: Login account");
        loginPage.login(account, password);

        ///VP: Verify user avatar is displayed
        LOG.info("VP: Verify user avatar is displayed");
        ExtentReportManager.info("VP: Verify user avatar is displayed");
        boolean recordingAvatar = homePage.getTopNavigation().isAvatarDisplayed();

        Assert.assertTrue(recordingAvatar, "User avatar is not displayed after login");
    }

    @Test(priority = 2, dataProvider = "profile-login", dataProviderClass = TestDataProvider.class, groups = "profile")
    public void verify_Username_Displayed(String account, String password) {

        ///Step 1: Navigate to LoginPage
        LOG.info("Step 1: Navigate to LoginPage");
        ExtentReportManager.info("Step 1: Navigate to LoginPage");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Login account
        LOG.info("Step 2: Login account");
        ExtentReportManager.info("Step 2: Login account");
        loginPage.login(account, password);

        ///VP: Verify username is displayed
        LOG.info("VP: Verify username is displayed");
        ExtentReportManager.info("VP: Verify username is displayed");
        String recordingAccount = homePage.getTopNavigation().isUserDisplays();

        Assert.assertEquals(recordingAccount, account, "Username displayed is incorrect");
    }

    @Test(priority = 3, dataProvider = "profile-login", dataProviderClass = TestDataProvider.class, groups = "profile")
    public void verify_Avatar_Username_Layout(String account, String password) {

        ///Step 1: Navigate to LoginPage
        LOG.info("Step 1: Navigate to LoginPage");
        ExtentReportManager.info("Step 1: Navigate to LoginPage");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Login account
        LOG.info("Step 2: Login account");
        ExtentReportManager.info("Step 2: Login account");
        loginPage.login(account, password);

        ///VP: Verify avatar and username are displayed in the same profile area
        LOG.info("VP: Verify avatar and username are displayed in the same profile area");
        ExtentReportManager.info("VP: Verify avatar and username are displayed in the same profile area");
        boolean recordingProfile = homePage.getTopNavigation().isAvatarDisplayed();

        Assert.assertTrue(recordingProfile, "Avatar and username layout is not displayed correctly");
    }

    /// US_17 - Account Update

    @Test(priority = 4, dataProvider = "profile-login", dataProviderClass = TestDataProvider.class, groups = "profile")
    public void verify_Account_Information_Displayed(String account, String password) {
        boolean hasError = false;
        ///Step 1: Navigate to LoginPage
        LOG.info("Step 1: Navigate to LoginPage");
        ExtentReportManager.info("Step 1: Navigate to LoginPage");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Login account
        LOG.info("Step 2: Login account");
        ExtentReportManager.info("Step 2: Login account");
        loginPage.login(account, password);

        if (commonModal.isLbMessage()) {
            commonModal.waitModalDisappear();
        }

        ///Step 3: Navigate to ProfilePage
        LOG.info("Step 3: Navigate to ProfilePage");
        ExtentReportManager.info("Step 3: Navigate to ProfilePage");
        homePage.getTopNavigation().navigateToProfilePage();

        ///VP 1: Navigate to ProfilePage successfully
        LOG.info("VP 1: Navigate to ProfilePage successfully");
        ExtentReportManager.info("VP 1: Navigate to ProfilePage successfully");
        String recordingProfilePage = profilePage.getProfilePageTitle();
        Assert.assertEquals(recordingProfilePage, "Cài đặt tài khoản chung" +
                "\nThông tin có thể được thay đổi", "Profile page content is incorrect");

        ///VP 2: Verify account is displayed correctly
        try {
            LOG.info("VP 2: Verify account is displayed correctly");
            ExtentReportManager.info("VP 2: Verify account is displayed correctly");
            String recordingAccount = profilePage.getTxtAccount();
            Assert.assertEquals(recordingAccount, account, "Displayed account does not match the logged-in account");

        } catch (AssertionError e) {
            hasError = true;
            LOG.error("VP 2 failed: " + e.getMessage());
            ExtentReportManager.fail("VP 2 failed: " + e.getMessage());
        }

        ///VP 3: Verify password is displayed correctly
        try {
            LOG.info("VP 3: Verify password is displayed correctly");
            ExtentReportManager.info("VP 3: Verify password is displayed correctly");
            String recordingPassword = profilePage.getTxtPassword();
            Assert.assertEquals(recordingPassword, password, "Displayed account does not match the logged-in password");
        } catch (AssertionError e) {
            hasError = true;
            LOG.error("VP 3 failed: " + e.getMessage());
            ExtentReportManager.fail("VP 3 failed: " + e.getMessage());
        }

        ///VP 4: Verify UserName is displayed correctly
        try {
            LOG.info("VP 4: Verify UserName is displayed correctly");
            ExtentReportManager.info("VP 4: Verify UserName is displayed correctly");
            String recordingUserName = profilePage.getTxtUserName();
            Assert.assertEquals(recordingUserName, account, "Displayed account does not match the logged-in UserName");
        } catch (AssertionError e) {
            hasError = true;
            LOG.error("VP 4 failed: " + e.getMessage());
            ExtentReportManager.fail("VP 4 failed: " + e.getMessage());
        }

        ///VP 5: Verify Email is displayed correctly
        try {
            LOG.info("VP 5: Verify Email is displayed correctly");
            ExtentReportManager.info("VP 5: Verify Email is displayed correctly");
            String recordingEmail = profilePage.getTxtEmail();
            Assert.assertEquals(recordingEmail, account + "@gmail.com", "Displayed account does not match the logged-in Email");
        } catch (AssertionError e) {
            hasError = true;
            LOG.error("VP 5 failed: " + e.getMessage());
            ExtentReportManager.fail("VP 5 failed: " + e.getMessage());
        }

        if (hasError) {
            Assert.fail("One or more verification points failed");
        }
    }

    @Test(priority = 5, dataProvider = "profile-login", dataProviderClass = TestDataProvider.class, groups = "profile")
    public void verify_Account_Fields_Disabled(String account, String password) {
        ///Step 1: Navigate to Login page
        LOG.info("Step 1: Navigate to Login Page");
        ExtentReportManager.info("Step 1: Navigate to Login Page");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Login account
        LOG.info("Step 2: Login account");
        ExtentReportManager.info("Step 2: Login account");
        loginPage.login(account, password);

        if (commonModal.isLbMessage()) {
            commonModal.waitModalDisappear();
        }

        ///Step 3: Navigate to Profile Page
        LOG.info("Step 3: Navigate to Profile Page");
        ExtentReportManager.info("Step 3: Navigate to Profile Page");
        homePage.getTopNavigation().navigateToProfilePage();

        ///VP : Verify Account Enabled is displays
        LOG.info("VP : Verify Account Enabled is displays");
        ExtentReportManager.info("VP 1: Verify Account Enabled is displays");
        boolean isElementEnabled = profilePage.isTxtAccountDisabled();
        Assert.assertFalse(isElementEnabled, "Account field is not disabled");
    }

    @Test(priority = 6, dataProvider = "profile-login", dataProviderClass = TestDataProvider.class, groups = "profile")
    public void verify_Show_Hide_Account_Password(String account, String password) {
        ///Step 1: Navigate to Login page
        LOG.info("Step 1: Navigate to Login Page");
        ExtentReportManager.info("Step 1: Navigate to Login Page");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Login account
        LOG.info("Step 2: Login account");
        ExtentReportManager.info("Step 2: Login account");
        loginPage.login(account, password);

        if (commonModal.isLbMessage()) {
            commonModal.waitModalDisappear();
        }

        ///Step 3: Navigate to Profile Page
        LOG.info("Step 3: Navigate to Profile Page");
        ExtentReportManager.info("Step 3: Navigate to Profile Page");
        homePage.getTopNavigation().navigateToProfilePage();

        ///Step 4: Click Hide password
        LOG.info("Step 4: Click Hide password");
        ExtentReportManager.info("Step 4: Click Hide password");
        profilePage.clickHidePassword();

        ///VP: Hide button should reveal the password field
        LOG.info("VP: Hide button should reveal the password field");
        ExtentReportManager.info("VP: Hide button should reveal the password field");
        boolean recordingHideVisible = profilePage.isPasswordVisible();
        Assert.assertTrue(recordingHideVisible, "Hide button was not clicked or did not reveal the password");
    }

    @Test(priority = 7, dataProvider = "profile-login", dataProviderClass = TestDataProvider.class, groups = "profile")
    public void verify_Update_With_Empty_Required_Fields(String account, String password) {
        ///Step 1: Navigate to Login page
        LOG.info("Step 1: Navigate to Login Page");
        ExtentReportManager.info("Step 1: Navigate to Login Page");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Login account
        LOG.info("Step 2: Login account");
        ExtentReportManager.info("Step 2: Login account");
        loginPage.login(account, password);

        if (commonModal.isLbMessage()) {
            commonModal.waitModalDisappear();
        }

        ///Step 3: Navigate to Profile Page
        LOG.info("Step 3: Navigate to Profile Page");
        ExtentReportManager.info("Step 3: Navigate to Profile Page");
        homePage.getTopNavigation().navigateToProfilePage();

        ///Step 4: Fill blank filed fullName
        LOG.info("Step 4: Fill blank filed fullName");
        ExtentReportManager.info("Step 4: Fill blank filed fullName");
        profilePage.clearFullName();

        ///Step 5: Fill blank filed phoneNumber
        LOG.info("Step 5: Fill blank filed fullName");
        ExtentReportManager.info("Step 5: Fill blank filed fullName");
        profilePage.clearPhoneNumber();

        ///Step 6: Fill blank filed Email
        LOG.info("Step 6: Fill blank filed Email");
        ExtentReportManager.info("Step 6: Fill blank filed Email");
        profilePage.clearEmail();

        ///Step 7: Fill blank filed password
        LOG.info("Step 7: Fill blank filed password");
        ExtentReportManager.info("Step 7: Fill blank filed password");
        profilePage.clearPassword();

        ///Step 8: Click btn update
        LOG.info("Step 8: Click btn update");
        ExtentReportManager.info("Step 8: Click btn update");
        profilePage.clickUpdate();

        ///VP 1: Verify error filed fullName
        LOG.info("VP 1: Verify error filed fullName");
        ExtentReportManager.info("VP 1: Verify error filed fullName");
        String recordingFullName = profilePage.getHelpMessageText("fullName");
        Assert.assertEquals(recordingFullName, "Đây là trường bắt buộc !", "Required field error message is incorrect");

        ///VP 2: Verify error filed phoneNumber
        LOG.info("VP 2: Verify error filed phoneNumber");
        ExtentReportManager.info("VP 2: Verify error filed phoneNumber");
        String recordingPhoneNumber = profilePage.getHelpMessageText("phone");
        Assert.assertEquals(recordingPhoneNumber, "Vui lòng nhập số điện thoại", "Required field error message is incorrect");

        ///VP 3: Verify error filed Email
        LOG.info("VP 3: Verify error filed Email");
        ExtentReportManager.info("VP 3: Verify error filed Email");
        String recordingEmail = profilePage.getHelpMessageText("email");
        Assert.assertEquals(recordingEmail, "Đây là trường bắt buộc !", "Required field error message is incorrect");

        ///VP 3: Verify error filed password
        LOG.info("VP 3: Verify error filed password");
        ExtentReportManager.info("VP 3: Verify error filed password");
        String recordingPassword = profilePage.getHelpMessageText("password");
        Assert.assertEquals(recordingPassword, "Đây là trường bắt buộc !", "Required field error message is incorrect");
    }

    @Test(priority = 8, dataProvider = "profile-update-phone-number", dataProviderClass = TestDataProvider.class, groups = "profile")
    public void verify_Update_With_Invalid_Phone_Number(String account, String password, String phoneNumber) {
        ///Step 1: Navigate to Login page
        LOG.info("Step 1: Navigate to Login Page");
        ExtentReportManager.info("Step 1: Navigate to Login Page");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Login account
        LOG.info("Step 2: Login account");
        ExtentReportManager.info("Step 2: Login account");
        loginPage.login(account, password);

        if (commonModal.isLbMessage()) {
            commonModal.waitModalDisappear();
        }

        ///Step 3: Navigate to Profile Page
        LOG.info("Step 3: Navigate to Profile Page");
        ExtentReportManager.info("Step 3: Navigate to Profile Page");
        homePage.getTopNavigation().navigateToProfilePage();

        ///Step 4: Enter phoneNumber invalid
        LOG.info("Step 4: Enter phoneNumber invalid");
        ExtentReportManager.info("Step 4: Enter phoneNumber invalid");
        profilePage.enterPhoneNumber(phoneNumber);

        ///Step 5: Click btn Update
        LOG.info("Step 5: Click btn update");
        ExtentReportManager.info("Step 5: Click btn update");
        profilePage.clickUpdate();

        ///VP: Verify invalid PhoneNumber
        LOG.info("VP: Verify invalid PhoneNumber");
        ExtentReportManager.info("VP: Verify invalid PhoneNumber");
        String recordingPhoneNumber = profilePage.getHelpMessageText("phone");
        Assert.assertEquals(recordingPhoneNumber, "Vui lòng nhập số điện thoại", "Required field error message is incorrect");

    }

    @Test(priority = 9, dataProvider = "profile-update-successfully", dataProviderClass = TestDataProvider.class, groups = "profile")
    public void verify_Update_Account_Successfully(String account, String password, String fullName,
                                                   String phoneNumber, String email) {
        ///Step 1: Navigate to Login page
        LOG.info("Step 1: Navigate to Login Page");
        ExtentReportManager.info("Step 1: Navigate to Login Page");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Login account
        LOG.info("Step 2: Login account");
        ExtentReportManager.info("Step 2: Login account");
        loginPage.login(account, password);

        if (commonModal.isLbMessage()) {
            commonModal.waitModalDisappear();
        }

        ///Step 3: Navigate to Profile Page
        LOG.info("Step 3: Navigate to Profile Page");
        ExtentReportManager.info("Step 3: Navigate to Profile Page");
        homePage.getTopNavigation().navigateToProfilePage();

        ///Step 4: Enter fullName
        LOG.info("Step 4: Enter fullName ");
        ExtentReportManager.info("Step 4: Enter fullName ");
        profilePage.enterFullName(fullName);

        ///Step 5: Enter phoneNumber
        LOG.info("Step 5: Enter phoneNumber ");
        ExtentReportManager.info("Step 5: Enter phoneNumber ");
        profilePage.enterPhoneNumber(phoneNumber);

        ///Step 6: Enter password
        LOG.info("Step 6: Enter password ");
        ExtentReportManager.info("Step 6: Enter password ");
        profilePage.enterPassword(password);

        ///Step 7: Enter email
        LOG.info("Step 7: Enter email ");
        ExtentReportManager.info("Step 7: Enter email ");
        profilePage.enterEmail(email);

        ///Step 8: Click btn Update
        LOG.info("Step 8: Click btn Update");
        ExtentReportManager.info("Step 8: Click btn Update");
        profilePage.clickUpdate();

        ///VP: Verify Update Successfully
        LOG.info("VP: Verify Update Successfully");
        ExtentReportManager.info("VP: Verify Update Successfully");
        String recordingUpdate = commonModal.getMessageText();
        Assert.assertEquals(recordingUpdate, "Cập nhật thành công", "Failed updating account");
    }

    @Test(priority = 10, dataProvider = "profile-without-update", dataProviderClass = TestDataProvider.class, groups = "profile")
    public void verify_Close_Account_Popup_Without_Update(String account, String password, String fullName, String phoneNumber) {

        ///Step 1: Navigate to Login page
        LOG.info("Step 1: Navigate to Login Page");
        ExtentReportManager.info("Step 1: Navigate to Login Page");
        homePage.getTopNavigation().navigateToLoginPage();

        ///Step 2: Login account
        LOG.info("Step 2: Login account");
        ExtentReportManager.info("Step 2: Login account");
        loginPage.login(account, password);

        if (commonModal.isLbMessage()) {
            commonModal.waitModalDisappear();
        }

        ///Step 3: Navigate to Profile Page
        LOG.info("Step 3: Navigate to Profile Page");
        ExtentReportManager.info("Step 3: Navigate to Profile Page");
        homePage.getTopNavigation().navigateToProfilePage();

        String fullNameBeforeUpdate = profilePage.getTxtUserName();
        String phoneNumberBeforeUpdate = profilePage.getTxtPhoneNumber();

        ///Step 4: Enter fullName
        LOG.info("Step 4: Enter fullName ");
        ExtentReportManager.info("Step 4: Enter fullName ");
        profilePage.enterFullName(fullName);

        ///Step 5: Enter phoneNumber
        LOG.info("Step 5: Enter phoneNumber ");
        ExtentReportManager.info("Step 5: Enter phoneNumber ");
        profilePage.enterPhoneNumber(phoneNumber);

        ///Step 6: Navigate to homePage
        LOG.info("Step 6: Navigate to homePage");
        ExtentReportManager.info("Step 6: Navigate to homePage");
        profilePage.getTopNavigation().clickOnLogo();

        ///Step 7: Navigate to ProfilePage again
        LOG.info("Step 7: Navigate to ProfilePage again");
        ExtentReportManager.info("Step 7: Navigate to ProfilePage again");
        homePage.getTopNavigation().navigateToProfilePage();

        ///VP 1: Verify FullName don't update
        LOG.info("VP 1: Verify FullName don't update");
        ExtentReportManager.info("VP 1: Verify FullName don't update");
        String recordingFullName = profilePage.getTxtUserName();
        Assert.assertEquals(recordingFullName, fullNameBeforeUpdate, "FullName was updated unexpectedly");

        ///VP 2: Verify phoneNumber don't update
        LOG.info("VP 2: Verify phoneNumber don't update");
        ExtentReportManager.info("VP 2: Verify phoneNumber don't update");
        String recordingPhoneNumber = profilePage.getTxtPhoneNumber();
        Assert.assertEquals(recordingPhoneNumber, phoneNumberBeforeUpdate, "phoneNumber was updated unexpectedly");
    }

}
