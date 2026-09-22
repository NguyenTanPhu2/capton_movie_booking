package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends CommonPage {

    private By byTxtAccount;
    private By byTxtPassword;
    private By byTxtConfirmPassword;
    private By byTxtFullName;
    private By byTxtEmail;
    private By byBtnRegister;
    private By byBtnHide;
    private By byLnkLogin;

    public RegisterPage(WebDriver driver) {
        super(driver);
        this.byTxtAccount = By.id("taiKhoan");
        this.byTxtPassword = By.id("matKhau");
        this.byTxtConfirmPassword = By.id("confirmPassWord");
        this.byTxtFullName = By.id("hoTen");
        this.byTxtEmail = By.id("email");
        this.byBtnRegister = By.xpath("//button[@type='submit']");
        this.byBtnHide = By.xpath("//input[@id='matKhau']/following-sibling::div//button");
        this.byLnkLogin = By.xpath("//a[h3[text()='Bạn đã có tài khoản? Đăng nhập']]");
    }

    public void enterAccount(String account) {
        sendKeys(byTxtAccount, account);
    }

    public void enterPassword(String password) {
        sendKeys(byTxtPassword, password);
    }

    public void enterConfirmPassword(String password) {
        sendKeys(byTxtConfirmPassword, password);
    }

    public void enterFullName(String fullName) {
        sendKeys(byTxtFullName, fullName);
    }

    public void enterEmail(String email) {
        sendKeys(byTxtEmail, email);
    }

    public void clickBtnRegister() {
        click(byBtnRegister);
    }

    public void clickBtnHide() {
        click(byBtnHide);
    }

    /// lay type input
    public String getPasswordInputType() {
        return getAttribute(byTxtPassword, "type");
    }

    public boolean isPasswordVisible() {
        return "text".equalsIgnoreCase(getPasswordInputType());
    }

    public void clickLnkLogin() {
        click(byLnkLogin);
    }
}
