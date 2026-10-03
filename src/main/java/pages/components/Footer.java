package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Footer {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators cho TIX
    private final By faqLink = By.xpath("//a[contains(text(),'FAQ')]");
    private final By brandGuidelinesLink = By.xpath("//a[contains(text(),'Brand Guidelines')]");
    private final By termsLink = By.xpath("//a[contains(text(),'Thỏa thuận sử dụng')]");
    private final By privacyLink = By.xpath("//a[contains(text(),'Chính sách bảo mật')]");

    // Locators cho ĐỐI TÁC (Ví dụ icon 1: CGV, icon 2: BHD)
    private final By cgvPartnerLogo = By.xpath("//a[contains(@href, 'cgv') or contains(@title, 'CGV')]");
    private final By bhdPartnerLogo = By.xpath("//a[contains(@href, 'bhd') or contains(@title, 'BHD')]");

    // Locators cho MOBILE APP
    private final By appleIcon = By.xpath("//a[contains(@href, 'apple') or contains(@href, 'ios')]");
    private final By androidIcon = By.xpath("//a[contains(@href, 'android') or contains(@href, 'google')]");

    // Locators cho SOCIAL
    private final By facebookIcon = By.xpath("//a[contains(@href, 'facebook')]");
    private final By zaloIcon = By.xpath("//a[contains(@href, 'zalo')]");

    public Footer (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Hàm dùng chung: Click vào link và lấy URL trang đích.
     * Xử lý được cả trường hợp chuyển trang trên tab hiện tại hoặc mở tab mới.
     */
    public String getUrlAfterClick(By locator) {
        String originalWindow = driver.getWindowHandle();
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();

        // Kiểm tra xem có mở tab mới không
        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        if (windowHandles.size() > 1) {
            for (String window : windowHandles) {
                if (!window.equals(originalWindow)) {
                    driver.switchTo().window(window);
                    break;
                }
            }
        }

        String currentUrl = driver.getCurrentUrl();

        // Đóng tab mới nếu có và quay lại tab chính
        if (driver.getWindowHandles().size() > 1) {
            driver.close();
            driver.switchTo().window(originalWindow);
        }

        return currentUrl;
    }

    // Các hàm helper để gọi từng item
    public String clickFaqAndGetUrl() { return getUrlAfterClick(faqLink); }
    public String clickBrandGuidelinesAndGetUrl() { return getUrlAfterClick(brandGuidelinesLink); }
    public String clickTermsAndGetUrl() { return getUrlAfterClick(termsLink); }
    public String clickPrivacyAndGetUrl() { return getUrlAfterClick(privacyLink); }

    public String clickCgvPartnerAndGetUrl() { return getUrlAfterClick(cgvPartnerLogo); }
    public String clickBhdPartnerAndGetUrl() { return getUrlAfterClick(bhdPartnerLogo); }

    public String clickAppleAppAndGetUrl() { return getUrlAfterClick(appleIcon); }
    public String clickAndroidAppAndGetUrl() { return getUrlAfterClick(androidIcon); }

    public String clickFacebookAndGetUrl() { return getUrlAfterClick(facebookIcon); }
    public String clickZaloAndGetUrl() { return getUrlAfterClick(zaloIcon); }
}