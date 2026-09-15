package pages.modals;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonModal extends BasePage {
    private By byLbMessage;
    public CommonModal(WebDriver driver) {
        super(driver);
        this.byLbMessage = By.id("swal2-title");
    }

    public String getMessageText(){
        return getText(byLbMessage);
    }

    public void waitModalDisappear(){
        waitInVisibilityOfElementLocated(byLbMessage);
    }
}
