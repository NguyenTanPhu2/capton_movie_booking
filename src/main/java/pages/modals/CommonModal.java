package pages.modals;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonModal extends BasePage {

    private By byLbMessage;

    private By byLbHelpAccount;
    private By byLbHelpPassword;
    private By byLbHelpConfirmPassword;
    private By byLbHelpFullName;
    private By byLbHelpEmail;

    private By byMuiAlertMessage;

    public CommonModal(WebDriver driver) {
        super(driver);
        this.byLbMessage = By.id("swal2-title");

        ///help message
        this.byLbHelpAccount = By.id("taiKhoan-helper-text");
        this.byLbHelpPassword = By.id("matKhau-helper-text");
        this.byLbHelpConfirmPassword = By.id("confirmPassWord-helper-text");
        this.byLbHelpFullName = By.id("hoTen-helper-text");
        this.byLbHelpEmail = By.id("email-helper-text");

        ///mlert Message
        this.byMuiAlertMessage = By.xpath("//div[@class='MuiAlert-message']");
    }

    public String getMessageText() {
        return getText(byLbMessage);
    }

    public void waitModalDisappear() {
        waitInVisibilityOfElementLocated(byLbMessage);
    }

    public String getHelpMessageText(String field) {
        return switch (field) {
            case "account" -> getText(byLbHelpAccount);
            case "password" -> getText(byLbHelpPassword);
            case "confirmPassword" -> getText(byLbHelpConfirmPassword);
            case "fullName" -> getText(byLbHelpFullName);
            case "email" -> getText(byLbHelpEmail);
            default -> "";
        };
    }

    public String getMuitAler() {
        return getText(byMuiAlertMessage);
    }

    public boolean isLbMessage(){
        return isElementDisplayed(byLbMessage);
    }
}
