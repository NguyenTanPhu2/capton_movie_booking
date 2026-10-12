
package testcase;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class HomeTest extends BaseTest {

    private final By movieLinks =
            By.cssSelector("a[href*='/detail/']");

    private List<WebElement> waitForMovies() {
        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(15)
        );

        return wait.until(d -> {
            List<WebElement> movies = d.findElements(movieLinks);

            boolean hasVisibleMovie = movies.stream()
                    .anyMatch(WebElement::isDisplayed);

            return hasVisibleMovie ? movies : null;
        });
    }

    // =========================================
    // TC_HOME_001: Default movie list
    // =========================================
    @Test(groups = "homepage")
    public void TC_HOME_001_verify_Default_Movie_List() {

        // Step 1: Verify homepage
        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains("demo1.cybersoft.edu.vn"),
                "Homepage URL is incorrect"
        );

        // Step 2: Wait for movie list
        List<WebElement> movies = waitForMovies();

        // VP 1: Movie list is not empty
        Assert.assertFalse(
                movies.isEmpty(),
                "Movie list is empty"
        );

        // VP 2: At least one movie is displayed
        Assert.assertTrue(
                movies.stream().anyMatch(WebElement::isDisplayed),
                "Movie list is not displayed"
        );

        // VP 3: Movie information is displayed
        boolean movieHasName = movies.stream()
                .filter(WebElement::isDisplayed)
                .anyMatch(movie ->
                        !movie.getText().trim().isEmpty()
                );

        Assert.assertTrue(
                movieHasName,
                "Movie name is not displayed"
        );
    }

    // =========================================
    // TC_HOME_002: Navigate to movie detail
    // =========================================
    @Test(groups = "homepage")
    public void TC_HOME_002_verify_Navigate_To_Movie_Detail() {

        // Step 1: Wait for movie list
        List<WebElement> movies = waitForMovies();

        // Step 2: Find first visible movie
        WebElement movie = movies.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElseThrow(() ->
                        new AssertionError(
                                "No visible movie found"
                        )
                );

        // Step 3: Get movie detail URL
        String movieUrl = movie.getAttribute("href");

        Assert.assertNotNull(
                movieUrl,
                "Movie detail URL is missing"
        );

        // Step 4: Click movie
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", movie
        );

        // VP: Verify navigation
        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(15)
        );

        wait.until(d ->
                d.getCurrentUrl().contains("/detail/")
        );

        Assert.assertEquals(
                driver.getCurrentUrl(),
                movieUrl,
                "Navigation to movie detail is incorrect"
        );

        // VP: Detail page has visible content
        Assert.assertFalse(
                driver.findElement(By.tagName("body"))
                        .getText().trim().isEmpty(),
                "Movie detail page is empty"
        );
    }

    // =========================================
    // TC_HOME_003: Scroll movie list
    // =========================================
    @Test(groups = "homepage")
    public void TC_HOME_003_verify_Movie_List_After_Scroll() {

        // Step 1: Wait for movie list
        List<WebElement> movies = waitForMovies();

        Assert.assertFalse(
                movies.isEmpty(),
                "Movie list is empty"
        );

        // Step 2: Scroll to movie list
        WebElement firstMovie = movies.get(0);

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                firstMovie
        );

        // Step 3: Scroll further down
        js.executeScript(
                "window.scrollBy(0, 600);"
        );

        // VP 1: Page can be scrolled
        long scrollPosition = ((Number) js.executeScript(
                "return window.scrollY;"
        )).longValue();

        Assert.assertTrue(
                scrollPosition > 0,
                "Homepage cannot be scrolled"
        );

        // VP 2: Movie list still exists
        List<WebElement> moviesAfterScroll =
                driver.findElements(movieLinks);

        Assert.assertFalse(
                moviesAfterScroll.isEmpty(),
                "Movie list disappeared after scrolling"
        );

        // VP 3: Movie elements are still displayed
        Assert.assertTrue(
                moviesAfterScroll.stream()
                        .anyMatch(WebElement::isDisplayed),
                "Movies are not displayed after scrolling"
        );
    }
}
