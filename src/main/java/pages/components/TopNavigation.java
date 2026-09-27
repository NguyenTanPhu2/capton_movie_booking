package pages.components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TopNavigation extends BasePage {
    private By byLnkLogin;
    private By byLnkRegister;

    public TopNavigation(WebDriver driver) {
        super(driver);
        this.byLnkLogin = By.xpath("//a[h3[text()='Đăng Nhập']]");
        this.byLnkRegister = By.xpath("//a[h3[text()='Đăng Ký' ]]");
    }

    public void navigateToLoginPage(){
        click(byLnkLogin);
    }

    public void navigateToRegisterPage(){
        click(byLnkRegister);
    }

}
