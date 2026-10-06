package pages.components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Footer extends BasePage {

    private By byFooter;
    private By byTix;
    private By byLogoPartner;
    private By byMobileApp;
    private By byCopyright;
    private By byBoCongThuong;

    public Footer(WebDriver driver) {
        super(driver);
        this.byFooter = By.xpath("//footer[@class='jss142']");
        this.byCopyright = By.xpath("//div[contains(@class,'MuiGrid-grid-lg-8')]//h6[1]");
        this.byBoCongThuong = By.xpath("//img[contains(@src, 'daThongBao-logo')]");
    }

    public boolean isFooter() {
        return isElementDisplayed(byFooter);
    }

    public void clickTix(String tix) {
        byTix = By.xpath("//div[a[text()='" + tix + "']]");
        click(byTix);
    }

    public void clickLogoPartner(String logoPartner) {
        byLogoPartner = By.xpath("//a[img[@alt='" + logoPartner + "']]");
        click(byLogoPartner);
    }

    public void clickMobileApp(int index) {
        byMobileApp = By.xpath("(//a[img[@alt='']])[" + index + "]");
        click(byMobileApp);
    }

    public String getCopyright() {
        return getText(byCopyright);
    }

    public void clickBoCongThuong() {
        click(byBoCongThuong);
    }

}
