package base;

import constants.TimeOutConstants;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /// High-light element
    public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].style.border='3px solid red';" +
                        "arguments[0].style.backgroundColor='yellow';",
                element
        );
    }

    /// Remove High-light element
    public void removeHighlight(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].style.border='';" +
                        "arguments[0].style.backgroundColor='';",
                element
        );
    }

    /// Thoi gian doi

    public WebDriverWait getWebDriverWait(long timeOutInSec) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeOutInSec));
    }

    /// Action visibility element wait
    // enter time out
    public WebElement waitVisibilityOfElementLocated(By locator, long timeOutInSec) {
        WebDriverWait wait = getWebDriverWait(timeOutInSec);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //don't enter time out
    public WebElement waitVisibilityOfElementLocated(By locator) {
        return waitVisibilityOfElementLocated(locator, TimeOutConstants.DEFAULT_TIMEOUT);
    }

    /// Action clickable element wait
    // enter time out
    public WebElement waitElementToBeClickable(By locator, long timeOutInSec) {
        WebDriverWait wait = getWebDriverWait(timeOutInSec);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    //don't enter time out
    public WebElement waitElementToBeClickable(By locator) {
        return waitElementToBeClickable(locator, TimeOutConstants.DEFAULT_TIMEOUT);
    }

    /// Action senkey
    // enter time out
    public void sendKeys(By locator, String value, long timeOutInSec) {
        WebElement element = waitVisibilityOfElementLocated(locator, timeOutInSec);
        highlightElement(element);
        element.sendKeys(value);
        removeHighlight(element);
    }

    //don't enter time out
    public void sendKeys(By locator, String value) {
        sendKeys(locator, value, TimeOutConstants.DEFAULT_TIMEOUT);
    }

    /// Action click
    // enter time out
    public void click(By locator, long timeOutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeOutConstants.DEFAULT_TIMEOUT));
        WebElement element = waitElementToBeClickable(locator, timeOutInSec);
        highlightElement(element);
        element.click();
    }

    //don't enter time out
    public void click(By locator) {
        click(locator, TimeOutConstants.DEFAULT_TIMEOUT);
    }

    /// Action getText
    // enter time out
    public String getText(By locator, long timeOutInSec) {
        WebElement element = waitVisibilityOfElementLocated(locator, timeOutInSec);
        highlightElement(element);
        removeHighlight(element);
        return element.getText();
    }

    //don't enter time out
    public String getText(By locator) {
        return getText(locator, TimeOutConstants.DEFAULT_TIMEOUT);
    }

    /// Action getAttribute
    public String getAttribute(By locator, String attributeName, long timeOutInSec) {
        WebElement element = waitVisibilityOfElementLocated(locator, timeOutInSec);
        highlightElement(element);
        removeHighlight(element);
        return element.getAttribute(attributeName);
    }

    public String getAttribute(By locator, String attributeName) {
        return getAttribute(locator, attributeName, TimeOutConstants.DEFAULT_TIMEOUT);
    }

    /// Action doi popup/alert tat
    // enter time out
    public boolean waitInVisibilityOfElementLocated(By locator, long timeOutInSec) {
        WebDriverWait wait = getWebDriverWait(timeOutInSec);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    //don't enter time out
    public boolean waitInVisibilityOfElementLocated(By locator) {
        return waitInVisibilityOfElementLocated(locator, TimeOutConstants.DEFAULT_TIMEOUT);
    }


    //Kiem tra có hiển thị hay không
    public boolean isElementDisplayed(By locator) {
        try {
            return waitVisibilityOfElementLocated(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /// kiem tra option sau select
    public boolean isOptionDisplayed(By selectLocator, String optionText) {
        try {
            WebElement select = waitVisibilityOfElementLocated(selectLocator);

            return select.findElements(By.tagName("option"))
                    .stream()
                    .anyMatch(option ->
                            option.getText().trim().equals(optionText)
                    );

        } catch (Exception e) {
            return false;
        }
    }

    /// click <option> va de dong the
    public void choose(By selectLocator, String name) {
        WebElement selectElement = waitVisibilityOfElementLocated(selectLocator);

        Select select = new Select(selectElement);
        select.selectByVisibleText(name);

        selectElement.sendKeys(Keys.ESCAPE);
    }

    /// Hover mouse
    public void hoverMouse(By locator) {
        WebElement element = waitVisibilityOfElementLocated(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();

    }

    /// Scroll
    public void scroll(By locator, int y) {
        Actions actions = new Actions(driver);

        WebElement element = waitVisibilityOfElementLocated(locator);

        actions.moveToElement(element)
                .scrollByAmount(0, y)
                .perform();
    }

    public void scroll() {
        Actions actions = new Actions(driver);
        actions.scrollByAmount(0, 3386)
                .perform();
    }

    /// Find iframe
    public void findIFrame(By locator) {
        WebElement iframe = waitVisibilityOfElementLocated(locator);
        driver.switchTo().frame(iframe);
    }

    /// Get iframe
    public String getIFrame(By locator) {
        WebElement iframe = waitVisibilityOfElementLocated(locator);
        return iframe.getAttribute("textContent").trim();
    }

    /// Lay textRong
    public String getTextIfPresent(By locator) {
        try {
            return driver.findElement(locator).getText().trim();
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    /// Switch tag
    public void switchToNewTab() {
        String currentWindow = driver.getWindowHandle();

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(currentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    /// Refresh page
    public void refreshPage() {
        driver.navigate().refresh();
    }

    /// Open Split-screen
    public void openHalfScreen() {
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(960, 1080));
        driver.manage().window().setPosition(new org.openqa.selenium.Point(0, 0));

    }
}
