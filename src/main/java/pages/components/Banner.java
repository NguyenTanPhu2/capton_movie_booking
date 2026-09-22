package pages.components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Banner extends BasePage {

    private By byBannerImages;

    public Banner(WebDriver driver) {
        super(driver);
        this.byBannerImages = By.xpath("//img[@alt='Img alt']");
    }

    public boolean isAltTextDisplayed() {
        return isElementDisplayed(byBannerImages);
    }
}
