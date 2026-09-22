package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends CommonPage {

    private By byTxtAccount;
    private By byTxtPassword;
    private By byBtnLogin;
    private By byBtnRemember;
    private By byLnkRegister;
    private By byLnkProfile;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.byTxtAccount = By.id("taiKhoan");
        this.byTxtPassword = By.id("matKhau");
        this.byBtnLogin = By.xpath("//button[span[text()='Đăng nhập']]");
        this.byBtnRemember = By.xpath("//input[@name='remember']/ancestor::span[contains(@class,'MuiCheckbox-root')]");
        this.byLnkRegister = By.xpath("//a[h3[text()='Bạn chưa có tài khoản? Đăng ký']]");
        this.byLnkProfile = By.xpath("//a[@href='/account']");
    }

    public void enterAccount(String account) {
        sendKeys(byTxtAccount, account);
    }

    public void enterPassword(String password) {
        sendKeys(byTxtPassword, password);
    }

    public void clickBtnLogin() {
        click(byBtnLogin);
    }

    public void login(String account, String password) {
        enterAccount(account);
        enterPassword(password);
        clickBtnLogin();
    }

    public void clickBtnRemember() {
        click(byBtnRemember);
    }

    public String getTxtAccount() {
        return getAttribute(byTxtAccount, "value");
    }

    public String getTxtPassword() {
        return getAttribute(byTxtPassword, "value");
    }

    public void clickRegisterLink() {
        click(byLnkRegister);
    }

    public String getTxtProfile() {
        return getText(byLnkProfile);
    }
}
