package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends CommonPage {

    private By byTxtAccount;
    private By byTxtPassword;
    private By byBtnLogin;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.byTxtAccount = By.id("taiKhoan");
        this.byTxtPassword = By.id("matKhau");
        this.byBtnLogin = By.xpath("//button[span[text()='Đăng nhập']]");
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
}
