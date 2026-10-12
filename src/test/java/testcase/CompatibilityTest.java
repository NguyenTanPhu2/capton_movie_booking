
package testcase;

import base.BaseTest;
import data.TestDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MovieDetailPage;
import pages.BookingPage;
import pages.modals.CommonModal;

import java.time.Duration;
import java.util.List;

public class CompatibilityTest extends BaseTest {

    // =========================================
    // TC_COMP_001: Homepage on Firefox
    // =========================================
    @Test(groups = "compatibility")
    public void TC_COMP_001_verify_Homepage_On_Firefox() {

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(15)
        );

        // Step 1: Verify homepage URL
        Assert.assertTrue(
                driver.getCurrentUrl().contains("demo1.cybersoft.edu.vn"),
                "Homepage URL is incorrect"
        );

        // Step 2: Verify banner displayed
        boolean bannerDisplayed = wait.until(d ->
                d.findElements(By.xpath("//img[@alt='Img alt']"))
                        .stream()
                        .anyMatch(WebElement::isDisplayed)
        );

        Assert.assertTrue(
                bannerDisplayed,
                "Banner is not displayed on Firefox"
        );

        // Step 3: Verify movie list displayed
        boolean moviesDisplayed = wait.until(d ->
                d.findElements(By.cssSelector("a[href*='/detail/']"))
                        .stream()
                        .anyMatch(WebElement::isDisplayed)
        );

        Assert.assertTrue(
                moviesDisplayed,
                "Movie list is not displayed on Firefox"
        );
    }

    // =========================================
    // TC_COMP_002: Login and select seat
    // on Firefox
    // =========================================
    @Test(
            groups = "compatibility",
            dataProvider = "booking-valid-seat",
            dataProviderClass = TestDataProvider.class
    )
    public void TC_COMP_002_verify_Login_And_Seat_On_Firefox(
            String account,
            String password,
            String movieName,
            String schedule,
            int numSeat) {

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MovieDetailPage movieDetailPage =
                new MovieDetailPage(driver);
        BookingPage bookingPage = new BookingPage(driver);
        CommonModal commonModal = new CommonModal(driver);

        // Step 1: Navigate to login page
        homePage.getTopNavigation().navigateToLoginPage();

        // Step 2: Login
        loginPage.login(account, password);

        // VP 1: Verify login successful
        Assert.assertEquals(
                commonModal.getMessageText(),
                "Đăng nhập thành công",
                "Login failed on Firefox"
        );

        // Step 3: Open movie detail
        homePage.clickOnMovieName(movieName);

        // Step 4: Select schedule
        movieDetailPage.clickOnSchedule(schedule);

        // Step 5: Wait for seats to load
        bookingPage.waitForSeatsToLoad();

        // Step 6: Get available seats
        List<String> availableSeats =
                bookingPage.getAvailableSeatNumbers();

        System.out.println(
                "AVAILABLE SEATS = " + availableSeats
        );

        // VP 2: Verify at least one seat is available
        Assert.assertFalse(
                availableSeats.isEmpty(),
                "No available seats on Firefox"
        );

        // Step 7: Select first available seat
        String availableSeat = availableSeats.get(0);

        System.out.println(
                "SELECTED SEAT = " + availableSeat
        );

        bookingPage.clickOnSeat(Integer.parseInt(availableSeat));

        // VP 3: Verify selected seat
        String selectedSeats = bookingPage.getSeat();

        Assert.assertTrue(
                selectedSeats.contains(
                        String.valueOf(availableSeat)
                ),
                "Selected seat is incorrect on Firefox: "
                        + selectedSeats
        );

        // VP 4: Verify ticket price
        String actualPrice = bookingPage.getPrice();

        Assert.assertTrue(
                actualPrice.matches("\\d+VND")
                        && !actualPrice.equals("0VND"),
                "Ticket price is incorrect on Firefox: "
                        + actualPrice
        );
    }

    // =========================================
    // TC_COMP_003: Homepage on Microsoft Edge
    // =========================================
    @Test(groups = "compatibility")
    public void TC_COMP_003_verify_Homepage_On_Edge() {

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(15)
        );

        // Step 1: Verify browser is Microsoft Edge
        Assert.assertTrue(
                driver instanceof org.openqa.selenium.edge.EdgeDriver,
                "Test is not running on Microsoft Edge"
        );

        // Step 2: Verify homepage URL
        Assert.assertTrue(
                driver.getCurrentUrl().contains("demo1.cybersoft.edu.vn"),
                "Homepage URL is incorrect on Edge"
        );

        // Step 3: Verify movie list is displayed
        boolean moviesDisplayed = wait.until(d ->
                d.findElements(By.cssSelector("a[href*='/detail/']"))
                        .stream()
                        .anyMatch(WebElement::isDisplayed)
        );

        Assert.assertTrue(
                moviesDisplayed,
                "Movie list is not displayed on Edge"
        );

        // Step 4: Verify images exist on homepage
        boolean imagesDisplayed = wait.until(d ->
                d.findElements(By.tagName("img"))
                        .stream()
                        .anyMatch(WebElement::isDisplayed)
        );

        Assert.assertTrue(
                imagesDisplayed,
                "Images are not displayed on Edge"
        );

        // Step 5: Scroll to bottom
        org.openqa.selenium.JavascriptExecutor js =
                (org.openqa.selenium.JavascriptExecutor) driver;

        js.executeScript(
                "window.scrollTo(0, document.body.scrollHeight);"
        );

        // VP: Verify scrolling works
        long scrollPosition = ((Number) js.executeScript(
                "return window.scrollY;"
        )).longValue();

        Assert.assertTrue(
                scrollPosition > 0,
                "Cannot scroll homepage on Edge"
        );
    }

    // =========================================
    // TC_COMP_004: Chrome zoom 50% and 150%
    // =========================================
    @Test(groups = "compatibility")
    public void TC_COMP_004_verify_Homepage_Zoom_On_Chrome() {

        Assert.assertTrue(
                driver instanceof org.openqa.selenium.chrome.ChromeDriver,
                "Test is not running on Chrome"
        );

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(15)
        );

        org.openqa.selenium.JavascriptExecutor js =
                (org.openqa.selenium.JavascriptExecutor) driver;

        // Step 1: Wait for movie list
        wait.until(d -> d.findElements(
                By.cssSelector("a[href*='/detail/']")
        ).stream().anyMatch(WebElement::isDisplayed));

        // Step 2: Test zoom at 50%
        js.executeScript("document.body.style.zoom='50%'");

        Assert.assertTrue(
                driver.findElements(
                        By.cssSelector("a[href*='/detail/']")
                ).stream().anyMatch(WebElement::isDisplayed),
                "Movie list is not displayed at 50% zoom"
        );

        // Step 3: Test zoom at 150%
        js.executeScript("document.body.style.zoom='150%'");

        Assert.assertTrue(
                driver.findElements(
                        By.cssSelector("a[href*='/detail/']")
                ).stream().anyMatch(WebElement::isDisplayed),
                "Movie list is not displayed at 150% zoom"
        );

        // Step 4: Restore zoom
        js.executeScript("document.body.style.zoom='100%'");
    }
}
