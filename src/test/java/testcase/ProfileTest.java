package testcase;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProfilePage;
import pages.components.TopNavigation;

public class ProfileTest extends BaseTest {

    private ProfilePage profilePage;
    private LoginPage loginPage;
    private TopNavigation topNavigation;

    @BeforeMethod
    public void setupProfileTest() {
        // 1. Mở trang chủ
        driver.get("https://demo1.cybersoft.edu.vn/");

        // 2. Click nút Đăng nhập trên thanh điều hướng
        topNavigation = new TopNavigation(driver);
        topNavigation.navigateToLoginPage();

        // 3. Điền thông tin và Đăng nhập
        loginPage = new LoginPage(driver);
        loginPage.login("Clara", "Clara@2026");

        // 4. Nghỉ 2 giây để hệ thống xử lý lưu Token/Session Đăng nhập
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 5. Điều hướng thẳng vào trang Cài đặt tài khoản
        driver.get("https://demo1.cybersoft.edu.vn/account");
        profilePage = new ProfilePage(driver);
    }

    // TC01: Kiểm tra dữ liệu hiển thị mặc định của 6 mục (1, 2, 3, 4, 5, 6)
    @Test
    public void testAccountFieldsDisplayedCorrectly() {
        Assert.assertFalse(profilePage.getUsernameValue().isEmpty(), "Mục 1 (Tài Khoản) bị trống");
        Assert.assertFalse(profilePage.getFullNameValue().isEmpty(), "Mục 2 (Họ Tên) bị trống");
        Assert.assertFalse(profilePage.getPhoneValue().isEmpty(), "Mục 3 (Số điện thoại) bị trống");
        Assert.assertFalse(profilePage.getEmailValue().isEmpty(), "Mục 5 (Email) bị trống");
        Assert.assertFalse(profilePage.getUserTypeValue().isEmpty(), "Mục 6 (Mã Loại Người Dùng) bị trống");
    }

    // TC02: Kiểm tra ô 1 (Tài khoản) và ô 6 (Mã loại người dùng) không cho chỉnh sửa (Disabled)
    @Test
    public void testReadOnlyFieldsCannotBeEdited() {
        Assert.assertTrue(profilePage.isUsernameDisabled(), "Mục 1 (Tài Khoản) phải ở trạng thái Read-only/Disabled");
        Assert.assertTrue(profilePage.isUserTypeDisabled(), "Mục 6 (Mã Loại Người Dùng) phải ở trạng thái Read-only/Disabled");
    }

    // TC03: Cập nhật thông tin tài khoản thành công cho các ô cho phép sửa (2, 3, 4, 5)
    @Test
    public void testUpdateAccountInformationSuccess() {
        String newFullName = "Clara";
        String newPhone = "123455789";
        String newPassword = "Admin123";
        String newEmail = "trang34244467@gmail.com";

        // Thực hiện cập nhật
        profilePage.updateAccountInfo(newFullName, newPhone, newPassword, newEmail);

        // Refresh lại trang để kiểm tra dữ liệu thực tế trên Server
        driver.navigate().refresh();

        Assert.assertEquals(profilePage.getFullNameValue(), newFullName, "Mục 2 (Họ Tên) chưa cập nhật đúng");
        Assert.assertEquals(profilePage.getPhoneValue(), newPhone, "Mục 3 (Số điện thoại) chưa cập nhật đúng");
        Assert.assertEquals(profilePage.getEmailValue(), newEmail, "Mục 5 (Email) chưa cập nhật đúng");
    }
}