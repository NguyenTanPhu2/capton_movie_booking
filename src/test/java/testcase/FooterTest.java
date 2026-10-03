package testcase;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.components.Footer;

public class FooterTest extends BaseTest {

    private Footer footer;

    @BeforeMethod
    public void setupTest() {
        driver.get("https://demo1.cybersoft.edu.vn/");
        footer = new Footer(driver);
    }

    // 1. Test link FAQ
    @Test
    public void testFaqLinkNavigation() {
        String actualUrl = footer.clickFaqAndGetUrl();
        Assert.assertTrue(actualUrl.contains("faq") || actualUrl.contains("demo1.cybersoft.edu.vn"),
                "URL của FAQ không chính xác: " + actualUrl);
    }

    // 2. Test link Brand Guidelines
    @Test
    public void testBrandGuidelinesLinkNavigation() {
        String actualUrl = footer.clickBrandGuidelinesAndGetUrl();
        Assert.assertTrue(actualUrl.contains("brand") || actualUrl.contains("guidelines"),
                "URL của Brand Guidelines không chính xác: " + actualUrl);
    }

    // 3. Test link Thỏa thuận sử dụng
    @Test
    public void testTermsLinkNavigation() {
        String actualUrl = footer.clickTermsAndGetUrl();
        Assert.assertTrue(actualUrl.contains("thoa-thuan") || actualUrl.contains("terms"),
                "URL của Thỏa thuận sử dụng không chính xác: " + actualUrl);
    }

    // 4. Test link Chính sách bảo mật
    @Test
    public void testPrivacyLinkNavigation() {
        String actualUrl = footer.clickPrivacyAndGetUrl();
        Assert.assertTrue(actualUrl.contains("bao-mat") || actualUrl.contains("privacy"),
                "URL của Chính sách bảo mật không chính xác: " + actualUrl);
    }

    // 5a. Test Đối tác 1 (CGV)
    @Test
    public void testCgvPartnerLinkNavigation() {
        String actualUrl = footer.clickCgvPartnerAndGetUrl();
        Assert.assertTrue(actualUrl.contains("cgv.vn"), "Link đối tác CGV chuyển hướng sai: " + actualUrl);
    }

    // 5b. Test Đối tác 2 (BHD)
    @Test
    public void testBhdPartnerLinkNavigation() {
        String actualUrl = footer.clickBhdPartnerAndGetUrl();
        Assert.assertTrue(actualUrl.contains("bhdstar.vn"), "Link đối tác BHD chuyển hướng sai: " + actualUrl);
    }

    // 6. Test Mobile App iOS
    @Test
    public void testAppleAppStoreNavigation() {
        String actualUrl = footer.clickAppleAppAndGetUrl();
        Assert.assertTrue(actualUrl.contains("apple.com") || actualUrl.contains("apps.apple"),
                "Link iOS App Store không chính xác: " + actualUrl);
    }

    // 7. Test Mobile App Android
    @Test
    public void testAndroidGooglePlayNavigation() {
        String actualUrl = footer.clickAndroidAppAndGetUrl();
        Assert.assertTrue(actualUrl.contains("play.google.com"),
                "Link Android Google Play không chính xác: " + actualUrl);
    }

    // 8. Test Social Facebook
    @Test
    public void testFacebookLinkNavigation() {
        String actualUrl = footer.clickFacebookAndGetUrl();
        Assert.assertTrue(actualUrl.contains("facebook.com"),
                "Link Facebook không chính xác: " + actualUrl);
    }

    // 9. Test Social Zalo
    @Test
    public void testZaloLinkNavigation() {
        String actualUrl = footer.clickZaloAndGetUrl();
        Assert.assertTrue(actualUrl.contains("zalo.me") || actualUrl.contains("zalo"),
                "Link Zalo không chính xác: " + actualUrl);
    }
}