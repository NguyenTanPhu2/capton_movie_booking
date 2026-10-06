package testcase;

import base.BaseTest;
import data.TestDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.components.Footer;
import report.ExtentReportManager;

public class FooterTest extends BaseTest {

    // Page Object
    Footer footer;

    @BeforeMethod
    public void initializePages() {
        footer = new Footer(driver);
    }

    @Test(priority = 1, groups = "footer")
    public void verify_Footer_Displayed_Correctly() {
        ///Step 1: Scroll to end page
        LOG.info("Step 1: Scroll to end page");
        ExtentReportManager.info("Step 1: Scroll to end page");
        footer.scroll();

        ///VP: Footer is displays
        LOG.info("VP: Footer is displays");
        ExtentReportManager.info("VP: Footer is displays");
        boolean recordingFooter = footer.isFooter();
        Assert.assertTrue(recordingFooter, "Footer is not displayed");
    }

    @Test(priority = 2, dataProvider = "footer-link",
            dataProviderClass = TestDataProvider.class, groups = "footer")
    public void verify_FAQ_Link(String[][] tix) {

        ///Step 1: Scroll to end page
        LOG.info("Step 1: Scroll to end page");
        ExtentReportManager.info("Step 1: Scroll to end page");
        footer.scroll();

        ///Step 2: Click FAQ link
        LOG.info("Step 2: Click FAQ link");
        ExtentReportManager.info("Step 2: Click FAQ link");
        footer.clickTix(tix[0][0]);

        ///VP: Verify navigate to FAQ Page
        LOG.info("VP: Verify navigate to FAQ Page");
        ExtentReportManager.info("VP: Verify navigate to FAQ Page");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, tix[0][1], "Current URL is incorrect");
    }

    @Test(priority = 3, dataProvider = "footer-link",
            dataProviderClass = TestDataProvider.class, groups = "footer")
    public void verify_Brand_Guidelines_Link(String[][] tix) {
        ///Step 1: Scroll to end page
        LOG.info("Step 1: Scroll to end page");
        ExtentReportManager.info("Step 1: Scroll to end page");
        footer.scroll();

        ///Step 2: Click Brand Guidelines link
        LOG.info("Step 2: Click Brand Guidelines link");
        ExtentReportManager.info("Step 2: Click Brand Guidelines link");
        footer.clickTix(tix[1][0]);

        ///VP: Verify navigate to Brand Guidelines Page
        LOG.info("VP: Verify navigate to Brand Guidelines Page");
        ExtentReportManager.info("VP: Verify navigate to Brand Guidelines Page");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, tix[1][1], "Current URL is incorrect");

    }

    @Test(priority = 4, dataProvider = "footer-link",
            dataProviderClass = TestDataProvider.class, groups = "footer")
    public void verify_Terms_Of_Use_Link(String[][] tix) {

        ///Step 1: Scroll to end page
        LOG.info("Step 1: Scroll to end page");
        ExtentReportManager.info("Step 1: Scroll to end page");
        footer.scroll();

        ///Step 2: Click Thỏa thuận sử dụng link
        LOG.info("Step 2: Click Thỏa thuận sử dụng link");
        ExtentReportManager.info("Step 2: Click Thỏa thuận sử dụng link");
        footer.clickTix(tix[2][0]);

        ///VP: Verify navigate to Thỏa thuận sử dụng Page
        LOG.info("VP: Verify navigate to Thỏa thuận sử dụng Page");
        ExtentReportManager.info("VP: Verify navigate to Thỏa thuận sử dụng Page");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, tix[2][1], "Current URL is incorrect");

    }

    @Test(priority = 5, dataProvider = "footer-link",
            dataProviderClass = TestDataProvider.class, groups = "footer")
    public void verify_Privacy_Policy_Link(String[][] tix) {

        ///Step 1: Scroll to end page
        LOG.info("Step 1: Scroll to end page");
        ExtentReportManager.info("Step 1: Scroll to end page");
        footer.scroll();

        ///Step 2: Click Chính sách bảo mật link
        LOG.info("Step 2: Click Chính sách bảo mật link");
        ExtentReportManager.info("Step 2: Click Chính sách bảo mậtlink");
        footer.clickTix(tix[3][0]);

        ///VP: Verify navigate to Chính sách bảo mật Page
        LOG.info("VP: Verify navigate to Chính sách bảo mật Page");
        ExtentReportManager.info("VP: Verify navigate to Chính sách bảo mật Page");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, tix[3][1], "Current URL is incorrect");

    }

    @Test(priority = 6, dataProvider = "footer-partner",
            dataProviderClass = TestDataProvider.class, groups = "footer")
    public void verify_Partner_Logos(String[][] logoPartner) {

        ///Step 1: Scroll to end page
        LOG.info("Step 1: Scroll to end page");
        ExtentReportManager.info("Step 1: Scroll to end page");
        footer.scroll();

        ///Step 2: Click logo Partner
        LOG.info("Step 2: Click logo Partner");
        ExtentReportManager.info("Step 2: Click logo Partner");
        footer.clickLogoPartner(logoPartner[0][0]);

        ///VP: Verify navigate to Partner
        LOG.info("VP: Verify navigate to Partner");
        ExtentReportManager.info("VP: Verify navigate to Partner");
        // Switch sang tab mới
        footer.switchToNewTab();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, logoPartner[0][1], "Current URL is incorrect");
    }

    @Test(priority = 7, dataProvider = "footer-mobile-social",
            dataProviderClass = TestDataProvider.class, groups = "footer")
    public void verify_iOS_App_Link(String[] link) {

        ///Step 1: Scroll to end page
        LOG.info("Step 1: Scroll to end page");
        ExtentReportManager.info("Step 1: Scroll to end page");
        footer.scroll();

        ///Step 2: Click Mobile app IOS link
        LOG.info("Step 2: Click Mobile app IOS link");
        ExtentReportManager.info("Step 2: Click Mobile app IOS link");
        footer.clickMobileApp(1);

        ///VP: Verify navigate to Download Mobile app IOS Page
        LOG.info("VP: Verify navigate to Download Mobile app IOS Page");
        ExtentReportManager.info("VP: Verify navigate to Download Mobile app IOS Page");
        // Switch sang tab mới
        footer.switchToNewTab();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, link[0], "Current URL is incorrect");
    }

    @Test(priority = 8, dataProvider = "footer-mobile-social",
            dataProviderClass = TestDataProvider.class, groups = "footer")
    public void verify_Android_App_Link(String[] link) {
        ///Step 1: Scroll to end page
        LOG.info("Step 1: Scroll to end page");
        ExtentReportManager.info("Step 1: Scroll to end page");
        footer.scroll();

        ///Step 2: Click Mobile app Android link
        LOG.info("Step 2: Click Mobile app Android link");
        ExtentReportManager.info("Step 2: Click Mobile app IOS link");
        footer.clickMobileApp(2);

        ///VP: Verify navigate to Download Mobile app Android Page
        LOG.info("VP: Verify navigate to Download Mobile app Android Page");
        ExtentReportManager.info("VP: Verify navigate to Download Mobile app Android Page");
        // Switch sang tab mới
        footer.switchToNewTab();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, link[1], "Current URL is incorrect");
    }

    @Test(priority = 9, dataProvider = "footer-mobile-social",
            dataProviderClass = TestDataProvider.class, groups = "footer")
    public void verify_Facebook_Link(String[] link) {
        ///Step 1: Scroll to end page
        LOG.info("Step 1: Scroll to end page");
        ExtentReportManager.info("Step 1: Scroll to end page");
        footer.scroll();

        ///Step 2: Click Social Facebook link
        LOG.info("Step 2: Click Social Facebook link");
        ExtentReportManager.info("Step 2: Click Social Facebook link");
        footer.clickMobileApp(3);

        ///VP: Verify navigate to Facebook Page
        LOG.info("VP: Verify navigate to Facebook Page");
        ExtentReportManager.info("VP: Verify navigate to Facebook Page");
        // Switch sang tab mới
        footer.switchToNewTab();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, link[2], "Current URL is incorrect");
    }

    @Test(priority = 10, dataProvider = "footer-mobile-social",
            dataProviderClass = TestDataProvider.class, groups = "footer")
    public void verify_Zalo_Link(String[] link) {
        ///Step 1: Scroll to end page
        LOG.info("Step 1: Scroll to end page");
        ExtentReportManager.info("Step 1: Scroll to end page");
        footer.scroll();

        ///Step 2: Click Social Zalo link
        LOG.info("Step 2: Click Social Zalo link");
        ExtentReportManager.info("Step 2: Click Social Zalo link");
        footer.clickMobileApp(4);

        ///VP: Verify navigate to Facebook Page
        LOG.info("VP: Verify navigate to Zalo Page");
        ExtentReportManager.info("VP: Verify navigate to Zalo Page");
        // Switch sang tab mới
        footer.switchToNewTab();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, link[3], "Current URL is incorrect");
    }

    @Test(priority = 11, groups = "footer")
    public void verify_Copyright_Information() {
        ///Step 1: Scroll to end page
        LOG.info("Step 1: Scroll to end page");
        ExtentReportManager.info("Step 1: Scroll to end page");
        footer.scroll();

        ///VP: Verify information Copyright
        LOG.info("VP: Verify information Copyright");
        ExtentReportManager.info("VP: Verify information Copyright");
        String recordingCopyright = footer.getCopyright();
        Assert.assertEquals(recordingCopyright, "TIX – SẢN PHẨM CỦA CÔNG TY CỔ PHẦN ZION", "Copyright text is incorrect");
    }

    @Test(priority = 12, groups = "footer")
    public void verify_Ministry_Of_Industry_And_Trade_Link() {
        ///Step 1: Scroll to end page
        LOG.info("Step 1: Scroll to end page");
        ExtentReportManager.info("Step 1: Scroll to end page");
        footer.scroll();

        ///Step 2: Click Bo Cong Thuong link
        LOG.info("Step 2: Click Bo Cong Thuong link");
        ExtentReportManager.info("Step 2: Click Bo Cong Thuong link");
        footer.clickBoCongThuong();

        ///VP: Verify navigate to Bo Cong Thuong Page
        LOG.info("VP: Verify navigate to Bo Cong Thuong Page");
        ExtentReportManager.info("VP: Verify navigate to Bo Cong Thuong Page");
        // Switch sang tab mới
        footer.switchToNewTab();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://online.gov.vn/", "Current URL is incorrect");
    }

    @Test(priority = 13, groups = "footer")
    public void verify_Footer_Responsive_Layout() {
        ///Step 1: Change to mobile viewport
        LOG.info("Step 1: Change to mobile viewport");
        ExtentReportManager.info("Step 1: Change to mobile viewport");
        footer.switchToPhone();

        ///Step 2: Scroll to footer
        LOG.info("Step 2: Scroll to footer");
        ExtentReportManager.info("Step 2: Scroll to footer");
        footer.scroll();

        ///VP: Footer is visible and fits the mobile screen
        LOG.info("VP: Footer is visible and fits the mobile screen");
        ExtentReportManager.info("VP: Footer is visible and fits the mobile screen");

        Assert.assertTrue(footer.isFooter(), "Footer is not displayed on mobile");

        Dimension viewport = driver.manage().window().getSize();
        WebElement footerElement = driver.findElement(By.xpath("//footer[@class='jss142']"));
        int footerWidth = footerElement.getRect().getWidth();

        Assert.assertTrue(footerWidth <= viewport.getWidth(), "Footer exceeds mobile viewport width");
        Assert.assertTrue(footerElement.getRect().getX() >= 0, "Footer is not aligned inside the viewport");
        Assert.assertTrue(footerElement.getRect().getY() > 0, "Footer is not positioned below the page top");
    }
}
