import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class tests {
    public static class FooterTest {
        private WebDriver driver;
        private WebDriverWait wait;
        private final String BASE_URL = "https://demo1.cybersoft.edu.vn/";

        @BeforeMethod
        public void setUp() {
            // Khởi tạo ChromeDriver tự động bằng WebDriverManager
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Mở trang web
            driver.get(BASE_URL);
        }

        @Test
        public void testFooterFAQLink() {
            // Cuộn trang xuống tận cùng
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            // Tạm dừng 2 giây để bạn kịp nhìn thấy giao diện đã cuộn xuống footer
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Tìm và click vào link FAQ ở footer
            WebElement faqLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//footer//a[contains(text(),'FAQ')]")));
            faqLink.click();

            // Tạm dừng thêm 2 giây sau khi click để xem kết quả chuyển hướng trước khi đóng trình duyệt
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Current URL sau khi click FAQ: " + driver.getCurrentUrl());
        }
    }
}
