package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends CommonPage {

    private By byNewTicket;

    // --- Locators cho 6 mục Cài đặt tài khoản chung ---
    private final By usernameInput = By.xpath("//input[@name='taiKhoan' or @id='taiKhoan']");     // 1. Tài Khoản
    private final By fullNameInput = By.xpath("//input[@name='hoTen' or @id='hoTen']");           // 2. Họ Tên
    private final By phoneInput = By.xpath("//input[@name='soDT' or @name='soDt' or @id='soDT']"); // 3. Số điện thoại
    private final By passwordInput = By.xpath("//input[@name='matKhau' or @type='password']");   // 4. Mật Khẩu
    private final By emailInput = By.xpath("//input[@name='email' or @type='email']");            // 5. Email
    private final By userTypeInput = By.xpath("//input[@name='maLoaiNguoiDung'] | //select[@name='maLoaiNguoiDung']"); // 6. Mã Loại Người Dùng

    // Nút Cập Nhật
    private final By updateButton = By.xpath("//button[contains(text(),'CẬP NHẬT') or contains(text(),'Cập nhật')]");

    public ProfilePage(WebDriver driver) {
        super(driver);
        this.byNewTicket = By.xpath("//div[contains(@class, 'MuiGrid-grid-md-6')]");
    }

    public boolean isNewTicket() {
        return isElementDisplayed(byNewTicket);
    }

    // --- Lấy giá trị hiển thị trên ô Input bằng hàm getAttribute của CommonPage ---
    public String getUsernameValue() {
        return getAttribute(usernameInput, "value");
    }

    public String getFullNameValue() {
        return getAttribute(fullNameInput, "value");
    }

    public String getPhoneValue() {
        return getAttribute(phoneInput, "value");
    }

    public String getEmailValue() {
        return getAttribute(emailInput, "value");
    }

    public String getUserTypeValue() {
        return getAttribute(userTypeInput, "value");
    }

    // --- Kiểm tra thuộc tính Read-only / Disabled ---
    public boolean isUsernameDisabled() {
        String disabled = getAttribute(usernameInput, "disabled");
        String readonly = getAttribute(usernameInput, "readonly");
        return disabled != null || readonly != null;
    }

    public boolean isUserTypeDisabled() {
        String disabled = getAttribute(userTypeInput, "disabled");
        String readonly = getAttribute(userTypeInput, "readonly");
        return disabled != null || readonly != null;
    }

    // --- Thao tác nhập liệu ---
    public void enterFullName(String fullName) {
        sendKeys(fullNameInput, fullName);
    }

    public void enterPhone(String phone) {
        sendKeys(phoneInput, phone);
    }

    public void enterPassword(String password) {
        sendKeys(passwordInput, password);
    }

    public void enterEmail(String email) {
        sendKeys(emailInput, email);

    }

    public void clickUpdateButton() {
        click(updateButton);
    }

    // Hàm tổng hợp: Cập nhật thông tin tài khoản
    public void updateAccountInfo(String fullName, String phone, String password, String email) {
        enterFullName(fullName);
        enterPhone(phone);
        enterPassword(password);
        enterEmail(email);
        clickUpdateButton();
    }
}