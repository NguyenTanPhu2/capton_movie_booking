package pages.components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogOut extends BasePage {

    private By byConfirmLogOut;
    private By byConfirmCancelLogOut;

    public LogOut(WebDriver driver) {
        super(driver);
        this.byConfirmLogOut = By.xpath("//button[text()='Đồng ý']");
        this.byConfirmCancelLogOut = By.xpath("//button[text()='Hủy']");
    }

    public void clickConfirmLogOut() {
        click(byConfirmLogOut);
    }

    public void clickConfirmCancelLogOut() {
        click(byConfirmCancelLogOut);
    }
}
