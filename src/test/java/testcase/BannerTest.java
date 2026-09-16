package testcase;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.components.Banner;
import report.ExtentReportManager;

public class BannerTest extends BaseTest {

    /// Page Object
    Banner banner;

    @BeforeMethod
    public void initializePages() {
        banner = new Banner(driver);
    }

    @Test(priority = 1, groups = "banner")
    public void verify_Banner_WithOut_Alt_Text() {
        ///VP:Banner does not display the text "Img alt"
        boolean recordingBannerAlt = banner.isAltTextDisplayed();
        Assert.assertFalse(recordingBannerAlt, "Banner is displaying the text 'Img alt'");
    }

    @Test(priority = 2, groups = "banner")
    public void verify_Banner_After_Refresh() {

        ///Step 1: Refresh page
        LOG.info("Step 1: Refresh page");
        ExtentReportManager.info("Step 1: Refresh page");
        banner.refreshPage();

        ///VP:Banner does not display the text "Img alt" after Refresh
        LOG.info("VP:Banner does not display the text 'Img alt' after Refresh");
        ExtentReportManager.info("VP:Banner does not display the text 'Img alt' after Refresh");
        boolean recordingBannerAltAfterRefresh = banner.isAltTextDisplayed();
        Assert.assertFalse(recordingBannerAltAfterRefresh, "Banner is displaying the text 'Img alt'");
    }

    @Test(priority = 3, groups = "banner")
    public void verify_Banner_On_Different_Devices() {
        // VP: Banner on Desktop
        Assert.assertTrue(
                banner.isAltTextDisplayed(),
                "Banner is not displayed on Desktop"
        );

        banner.switchToPhone();

        banner.refreshPage();

        // VP: Banner on Phone
        Assert.assertTrue(
                banner.isAltTextDisplayed(),
                "Banner is not displayed on Phone"
        );
    }
}
