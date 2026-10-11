package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static data.locator.NewsLocator.*;

public class NewsPage extends CommonPage {

    public NewsPage(WebDriver driver) {
        super(driver);
    }

    // Scroll to News section
    public void scrollToNewsSection() {
        scroll(byNewsSection, 500);
    }
    public boolean isNewsSectionDisplayed() {
        return isElementDisplayed(byNewsSection);
    }

    // Verify News tab is displayed
    public boolean isNewsTabDisplayed(String tabName) {
        By byBtnNewsTab = By.xpath(NEWS_TAB_XPATH.replace("%s", tabName));
        return isElementDisplayed(byBtnNewsTab);
    }
    public void clickNewsTab(String tabName) {
        By byBtnNewsTab = By.xpath(NEWS_TAB_XPATH.replace("%s", tabName));
        click(byBtnNewsTab);
    }

    public void waitForNewsArticlesLoaded() {
        WebDriverWait wait = getWebDriverWait(10);

        wait.until(driver -> {
            List<WebElement> images = driver.findElements(byNewsImages);
            return images.size() >= 8
                    && images.stream().allMatch(WebElement::isDisplayed);
        });
    }

    public int getVisibleNewsImageCount() {
        List<WebElement> images = getDriver().findElements(byVisibleNewsImages);

        return (int) images.stream()
                .filter(WebElement::isDisplayed)
                .count();
    }
    public boolean waitForNewsImagesDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(
                    getDriver(),
                    Duration.ofSeconds(10)
            );

            return wait.until(driver -> {
                List<WebElement> images =
                        driver.findElements(byVisibleNewsImages);

                return !images.isEmpty()
                        && images.stream().allMatch(WebElement::isDisplayed);
            });
        } catch (TimeoutException e) {
            return false;
        }
    }
    //Collapse Button Method
    public void clickCollapseButton(){
        click(byCollapseButton);
    }
    public boolean isCollapseButtonDisplayed() {
        return isElementDisplayed(byCollapseButton);
    }
    public String getCollapseButtonText() {
        return getText(byCollapseButton);
    }
    public void scrollToCollapseButton() {
        scroll(byCollapseButton, 500);
    }

    // Large News Article Method
    public int getLargeNewsArticleCount() {
        return waitVisibilityOfElementsLocated(byLargeNewsArticles).size();
    }
    public int getLargeNewsArticleTitleCount() {
        return waitVisibilityOfElementsLocated(byLargeNewsArticlesTitle).size();
    }
    public int getLargeNewsArticleDescriptionsCount() {
        return waitVisibilityOfElementsLocated(byLargeNewsArticleDescriptions).size();
    }
    public String getLargeNewsUrl(int index) {
        By byTitle = By.xpath( LARGE_NEWS_ARTICLES_TITLE_XPATH + "[" + index + "]");
        return waitElementToBeClickable(byTitle).getAttribute("href");
    }
    public void clickLargeNewsTitle(int index) {
        By byTitle = By.xpath( LARGE_NEWS_ARTICLES_TITLE_XPATH + "[" + index + "]");
        click(byTitle);
    }

    // Medium News Article Method
    public int getMediumNewsArticleCount() {
        return waitVisibilityOfElementsLocated(byMediumNewsArticles).size();
    }
    public int getMediumNewsArticleTitleCount() {
        return waitVisibilityOfElementsLocated(byMediumNewsArticlesTitle).size();
    }
    public int getMediumNewsArticleDescriptionsCount() {
        return waitVisibilityOfElementsLocated(byMediumNewsArticleDescriptions).size();
    }
    public String getMediumNewsUrl(int index) {
        By byTitle = By.xpath( MEDIUM_NEWS_ARTICLES_TITLE_XPATH + "[" + index + "]");
        return waitElementToBeClickable(byTitle).getAttribute("href");
    }
    public void clickMediumNewsTitle(int index) {
        By byTitle = By.xpath( MEDIUM_NEWS_ARTICLES_TITLE_XPATH + "[" + index + "]");
        click(byTitle);
    }

    // Small News Article Method
    public int getSmallNewsArticleCount() {
        return waitVisibilityOfElementsLocated(bySmallNewsArticles).size();
    }
    public int getSmallNewsArticleTitleCount() {
                return waitVisibilityOfElementsLocated(bySmallNewsArticlesTitle).size();
    }
    public String getSmallNewsUrl(int index) {
        By byTitle = By.xpath( SMALL_NEWS_ARTICLES_TITLE_XPATH + "[" + index + "]");
        return waitElementToBeClickable(byTitle).getAttribute("href");
    }
    public void clickSmallNewsTitle(int index) {
        By byTitle = By.xpath( SMALL_NEWS_ARTICLES_TITLE_XPATH + "[" + index + "]");
        click(byTitle);
    }

    // Get number of news images
    public int getNewsImageCount() {
        return waitVisibilityOfElementsLocated(byNewsImages).size();
    }

    // Kiểm tra tab đã được kích hoạt bằng aria-selected
    public boolean isNewsTabSelected(String tabName) {
        By byActiveTab = By.xpath(
                ACTIVE_NEWS_TAB_XPATH.replace("%s", tabName)
        );
        return isElementDisplayed(byActiveTab);
    }

    // Kiểm tra panel của tab có hiển thị hay không
    public boolean isNewsTabPanelDisplayed(String panelIndex) {
        By byPanel = By.xpath(NEWS_TAB_PANEL_XPATH.replace("%s", panelIndex));
        return isElementDisplayed(byPanel);
    }

    public boolean areNewsImagesLoaded() {
        JavascriptExecutor js =
                (JavascriptExecutor) getDriver();

        return (Boolean) js.executeScript(
                "const images = document.querySelectorAll("
                        + "'#tinTuc img[alt=\"poster\"]');"
                        + "return Array.from(images).every("
                        + "img => img.complete && img.naturalWidth > 0"
                        + ");"
        );
    }

    public boolean waitForNewsImageCountLessThan(int previousCount) {
        try {
            WebDriverWait wait = getWebDriverWait(10);

            return wait.until(driver -> {
                List<WebElement> images =
                        driver.findElements(byVisibleNewsImages);

                long currentCount = images.stream()
                        .filter(WebElement::isDisplayed)
                        .count();

                return currentCount < previousCount;
            });
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean waitForActiveTabNewsDisplayed(String tabName) {
        WebDriverWait wait = getWebDriverWait(10);

        try {
            return wait.until(driver -> {
                // Xác định tab đang được chọn
                List<WebElement> selectedTabs = driver.findElements(
                        By.xpath(
                            "//div[@id='tinTuc']//button[@role='tab'"
                            + " and @aria-selected='true']"
                        )
                );

                if (selectedTabs.isEmpty()
                        || !selectedTabs.get(0).getText().trim().contains(tabName)) {
                    return false;
                }

                // Tìm panel đang hiển thị
                List<WebElement> panels = driver.findElements(
                        By.xpath(
                            "//div[@id='tinTuc']//div[@role='tabpanel'"
                            + " and not(@hidden)]"
                        )
                );

                if (panels.isEmpty()) {
                    return false;
                }

                WebElement panel = panels.get(0);

                // Có bài viết hoặc thông báo nội dung trống
                boolean hasArticle = panel.findElements(
                        By.xpath(".//a[@href and .//p]")
                ).stream().anyMatch(WebElement::isDisplayed);

                boolean hasEmptyMessage = panel.findElements(
                        By.xpath(
                            ".//*[contains(normalize-space(.),"
                            + "'Hiện chưa có khuyến mãi nào đang diễn ra')]"
                        )
                ).stream().anyMatch(WebElement::isDisplayed);

                return hasArticle || hasEmptyMessage;
            });
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void logNewsTabDebugInfo() {
        List<WebElement> tabs = getDriver().findElements(
                By.xpath("//div[@id='tinTuc']//button[@role='tab']")
        );

        for (WebElement tab : tabs) {
            System.out.println(
                    "TAB: " + tab.getText().trim()
                    + " | aria-selected="
                    + tab.getAttribute("aria-selected")
            );
        }

        List<WebElement> panels = getDriver().findElements(
                By.xpath("//div[@id='tinTuc']//div[@role='tabpanel']")
        );

        for (WebElement panel : panels) {
            System.out.println(
                    "PANEL ID: " + panel.getAttribute("id")
                    + " | hidden=" + panel.getAttribute("hidden")
                    + " | displayed=" + panel.isDisplayed()
                    + " | text=" + panel.getText().trim()
                    + " | images=" + panel.findElements(
                            By.xpath(".//img")
                    ).size()
                    + " | links=" + panel.findElements(
                            By.xpath(".//a[@href]")
                    ).size()
            );
            String style = panel.getAttribute("style");

            System.out.println(
                    "PANEL ID: " + panel.getAttribute("id")
                    + " | hidden=" + panel.getAttribute("hidden")
                    + " | displayed=" + panel.isDisplayed()
                    + " | width=" + panel.getRect().getWidth()
                    + " | height=" + panel.getRect().getHeight()
                    + " | style=" + style
                    + " | text=" + panel.getText().trim()
            );
        }
        
        System.out.println(
                "BUTTON TEXT: " + getCollapseButtonText()
        );
    }

    public boolean isPromotionContentValid() {
        By byPromotionPanel = By.id("simple-tabpanel-2");

        WebDriverWait wait = getWebDriverWait(10);

        try {
            return wait.until(driver -> {
                WebElement panel = driver.findElement(byPromotionPanel);

                if (!panel.isDisplayed()) {
                    return false;
                }

                // Trạng thái 1: Có bài viết khuyến mãi
                boolean hasArticles = panel.findElements(
                        By.xpath(".//a[@href and .//p]")
                ).stream().anyMatch(WebElement::isDisplayed);

                // Trạng thái 2: Không có khuyến mãi
                boolean hasEmptyMessage = panel.findElements(
                        By.xpath(
                            ".//*[contains(normalize-space(.),"
                            + "'Hiện chưa có khuyến mãi nào đang diễn ra')]"
                        )
                ).stream().anyMatch(WebElement::isDisplayed);

                return hasArticles || hasEmptyMessage;
            });
        } catch (TimeoutException e) {
            return false;
        }
    }
}