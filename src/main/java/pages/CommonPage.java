package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import pages.components.TopNavigation;

public class CommonPage extends BasePage {
    private TopNavigation topNavigation;

    public CommonPage(WebDriver driver) {
        super(driver);
        this.topNavigation = new TopNavigation(driver);
    }

    //de goi
    public TopNavigation getTopNavigation() {
        return this.topNavigation;
    }

}
