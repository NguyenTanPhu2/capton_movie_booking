package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends CommonPage {

    private By byNewTicket;
    private By byProfilePage;
    private By byTxtAccount;
    private By byTxtPassword;
    private By byTxtUserName;
    private By byTxtEmail;
    private By byHidePassword;
    private By byTxtPhone;
    private By byLbHelpFullName;
    private By byLbHelpNumPhone;
    private By byLbHelpEmail;
    private By byLbHelpPassword;
    private By byBtnUpdate;

    public ProfilePage(WebDriver driver) {
        super(driver);
        this.byNewTicket = By.xpath("(//div[contains(@class,'MuiGrid-grid-md-6')])[last()]");
        this.byProfilePage = By.xpath("//div[h1[text()='Cài đặt tài khoản chung']]");
        this.byHidePassword = By.xpath("//button[contains(@class,'MuiIconButton-edgeEnd')]");

        this.byTxtAccount = By.xpath("//input[@id='taiKhoan']");
        this.byTxtPassword = By.xpath("//input[@id='matKhau']");
        this.byTxtUserName = By.xpath("//input[@id='hoTen']");
        this.byTxtEmail = By.xpath("//input[@id='email']");
        this.byTxtPhone = By.xpath("//input[@id='soDt']");

        this.byLbHelpFullName = By.xpath("//p[@id='hoTen-helper-text']");
        this.byLbHelpNumPhone = By.xpath("//p[@id='soDt-helper-text']");
        this.byLbHelpEmail = By.xpath("//p[@id='email-helper-text']");
        this.byLbHelpPassword = By.xpath("//p[@id='matKhau-helper-text']");

        this.byBtnUpdate = By.xpath("//button[span[text()='Cập Nhật']]");
    }

    public boolean isNewTicket() {
        return isElementDisplayed(byNewTicket);
    }

    public String getProfilePageTitle() {
        return getText(byProfilePage);
    }

    public String getTxtAccount() {
        return getAttribute(byTxtAccount, "value");
    }

    public String getTxtPassword() {
        return getAttribute(byTxtPassword, "value");
    }

    public String getTxtUserName() {
        return getAttribute(byTxtAccount, "value");
    }

    public String getTxtEmail() {
        return getAttribute(byTxtEmail, "value");
    }

    public boolean isTxtAccountDisabled() {
        return waitVisibilityOfElementLocated(byTxtAccount).isEnabled();
    }

    public void clickHidePassword() {
        click(byHidePassword);
    }

    /// lay type input
    public String getPasswordInputType() {
        return getAttribute(byTxtPassword, "type");
    }

    public boolean isPasswordVisible() {
        return "text".equalsIgnoreCase(getPasswordInputType());
    }

    public void clearFullName() {
        clearText(byTxtUserName);
    }

    public void clearPhoneNumber() {
        clearText(byTxtPhone);
    }

    public void clearPassword() {
        clearText(byTxtPassword);
    }

    public void clearEmail() {
        clearText(byTxtEmail);
    }

    public void clickUpdate() {
        click(byBtnUpdate);
    }

    public String getHelpMessageText(String field) {
        return switch (field) {
            case "password" -> getText(byLbHelpPassword);
            case "phone" -> getText(byLbHelpNumPhone);
            case "fullName" -> getText(byLbHelpFullName);
            case "email" -> getText(byLbHelpEmail);
            default -> "";
        };
    }

    public void enterPhoneNumber(String phoneNumber) {
        clearPhoneNumber();
        sendKeys(byTxtPhone, phoneNumber);
    }

    public void enterFullName(String fullName) {
        clearFullName();
        sendKeys(byTxtUserName, fullName);
    }

    public void enterPassword(String password) {
        clearPassword();
        sendKeys(byTxtPassword, password);
    }

    public void enterEmail(String email) {
        clearEmail();
        sendKeys(byTxtEmail, email);
    }

    public String getTxtPhoneNumber() {
        return getAttribute(byTxtPhone, "value");
    }

}
