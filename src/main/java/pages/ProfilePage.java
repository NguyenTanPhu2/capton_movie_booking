package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends CommonPage {

    private By byNewTicket;

    public ProfilePage(WebDriver driver) {
        super(driver);
        this.byNewTicket = By.xpath("(//div[contains(@class,'MuiGrid-grid-md-6')])[last()]");
    }

    public boolean isNewTicket() {
        return isElementDisplayed(byNewTicket);
    }
}
