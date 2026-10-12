
package testcase;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumNetworkConditions;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ErrorHandlingTest extends BaseTest {

    @Test(groups = "error-handling")
    public void TC_ERR_001_verify_Invalid_URL() {

        // Step 1: Navigate to an invalid URL
        String invalidUrl =
                "https://demo1.cybersoft.edu.vn/page-not-found-test-404";

        driver.get(invalidUrl);

        // Step 2: Verify URL
        Assert.assertEquals(
                driver.getCurrentUrl(),
                invalidUrl,
                "Browser did not navigate to invalid URL"
        );

        // Step 3: Check whether page displays a 404 message
        String pageText = driver.findElement(
                By.tagName("body")
        ).getText();

        boolean has404Message =
                pageText.contains("404")
                        || pageText.toLowerCase().contains("not found")
                        || pageText.contains("Không tìm thấy");

        Assert.assertTrue(
                has404Message,
                "Website does not display a 404 error message"
        );
    }

    @Test(groups = "error-handling")
    public void TC_ERR_002_verify_Offline_Mode() {

        // Step 1: Verify Chrome browser
        Assert.assertTrue(
                driver instanceof ChromeDriver,
                "This test requires Chrome"
        );

        ChromeDriver chrome = (ChromeDriver) driver;

        ChromiumNetworkConditions offline =
                new ChromiumNetworkConditions();
        offline.setOffline(true);

        try {
            // Step 2: Simulate offline mode
            chrome.setNetworkConditions(offline);

            // Step 3: Navigate to website
            boolean navigationFailed = false;

            try {
                driver.get(
                        "https://demo1.cybersoft.edu.vn/offline-test"
                );
            } catch (WebDriverException e) {
                navigationFailed = true;
                System.out.println(
                        "OFFLINE ERROR = " + e.getMessage()
                );
            }

            // VP: Navigation should fail when offline
            Assert.assertTrue(
                    navigationFailed,
                    "Navigation unexpectedly succeeded while offline"
            );

        } finally {
            // Step 4: Restore network
            ChromiumNetworkConditions online =
                    new ChromiumNetworkConditions();
            online.setOffline(false);
            chrome.setNetworkConditions(online);
        }
    }

    // =========================================
    // TC_ERR_003: Invalid movie ID
    // =========================================
    @Test(groups = "error-handling")
    public void TC_ERR_003_verify_Invalid_Movie_ID() {

        // Step 1: Open movie detail with invalid ID
        String invalidMovieUrl =
                "https://demo1.cybersoft.edu.vn/detail/999999999";

        driver.get(invalidMovieUrl);

        // Step 2: Wait for page content
        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(15)
        );

        wait.until(d -> d.findElement(
                By.tagName("body")
        ).isDisplayed());

        // Step 3: Get page content
        String pageText = driver.findElement(
                By.tagName("body")
        ).getText().toLowerCase();

        // VP: Verify invalid movie error
        boolean hasErrorMessage =
                pageText.contains("404")
                        || pageText.contains("not found")
                        || pageText.contains("không tìm thấy")
                        || pageText.contains("phim không tồn tại");

        Assert.assertTrue(
                hasErrorMessage,
                "Website does not display an error for invalid movie ID"
        );
    }
}
