package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import report.ExtentReportManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static data.locator.MovieDetailLocator.*;

public class MovieDetailPage extends CommonPage {

    private static final Logger LOG = LogManager.getLogger(MovieDetailPage.class);

    private By bySchedule;
    private final By byGetNameMovie;
    private List<WebElement> paginationButtonsList;

    public MovieDetailPage(WebDriver driver) {
        super(driver);

        this.byGetNameMovie = By.xpath("//div[h1]");

        paginationButtonsList = waitVisibilityOfElementsLocated(byPaginationButtons);
    }

    public void clickOnSchedule(String schedule) {
        bySchedule = By.xpath("//a[p[text()='" + schedule + "']]");
        click(bySchedule);
    }

    public String getTextNameMovie() {
        return getText(byGetNameMovie);
    }

    public int getMovieCardCount() {
        return waitVisibilityOfElementsLocated(byMovieCards).size();
    }

    public boolean isMovieListDisplayed() {
        return isElementDisplayed(byMovieCards);
    }

    public boolean areAgeRatingsDisplayed() {
        return isElementDisplayed(byMovieAgeRatings);
    }

    public boolean areMovieTitlesDisplayed() {
        return isElementDisplayed(byMovieTitles);
    }

    public boolean areMovieDescriptionsDisplayed() {
        return isElementDisplayed(byMovieDescriptions);
    }

    public boolean areMoviePostersDisplayed() {
        return isElementDisplayed(byMoviePosters);
    }

    public int getPaginationCount() {

        return paginationButtonsList.size();
    }

    public boolean isPaginationActive() {
        return isElementDisplayed(byBtnActivePagination);
    }

    public void clickPaginationButton(int index) {
        clickWebElement(paginationButtonsList.get(index));
    }

    public String getMovieAgeRating(int index) {
        By locator = byXpath(
                "(" + MOVIE_AGE_RATINGS_XPATH + ")[" + index + "]");

        return getText(locator);
    }

    public String getMovieTitle(int index) {
        By locator = byXpath(
                "(" + MOVIE_TITLES_XPATH + ")[" + index + "]");

        String text = getText(locator);
        return text.replaceFirst("^C18\\s*", "").trim();
    }

    public String getMovieDescription(int index) {
        By locator = byXpath(
                "(" + MOVIE_DESCRIPTIONS_XPATH + ")[" + index + "]");

        return getText(locator);
    }

    public String getMoviePosterBackgroundImage(int index) {
        By locator = byXpath(
                "(" + MOVIE_POSTERS_XPATH + ")[" + index + "]");

        return getAttribute(locator, "style");
    }

    public String getMoviePosterWidth(int index) {
        By locator = byXpath(
                "(" + MOVIE_POSTERS_XPATH + ")[" + index + "]");

        return getCssValue(locator, "width");
    }

    public String getMoviePosterHeight(int index) {
        By locator = By.xpath(
                "(" + MOVIE_POSTERS_XPATH + ")[" + index + "]");

        return getCssValue(locator, "height");
    }

    public String getMoviePosterBackgroundSize(int index) {
        By locator = byXpath(
                "(" + MOVIE_POSTERS_XPATH + ")[" + index + "]");

        return getCssValue(locator, "background-size");
    }

    public String getMoviePosterBackgroundPosition(int index) {
        By locator = byXpath(
                "(" + MOVIE_POSTERS_XPATH + ")[" + index + "]");

        return getCssValue(locator, "background-position");
    }

    public int getMoviePosterWidthValue(int index) {
        By locator = byXpath(
                "(" + MOVIE_POSTERS_XPATH + ")[" + index + "]");

        return waitVisibilityOfElementLocated(locator).getSize().getWidth();
    }

    public int getMoviePosterHeightValue(int index) {
        By locator = byXpath(
                "(" + MOVIE_POSTERS_XPATH + ")[" + index + "]");

        return waitVisibilityOfElementLocated(locator).getSize().getHeight();
    }

    public void hoverMovieCard(int index) {
        By locator = byXpath(
                "(" + MOVIE_POSTERS_XPATH + ")[" + index + "]");

        hoverMouse(locator);
    }

    public boolean isTrailerButtonDisplayed(int index) {
        By locator = byXpath(
                "(" + TRAILER_BUTTONS_XPATH + ")[" + index + "]");

        return isElementDisplayed(locator);
    }

    public boolean isBuyTicketButtonDisplayed(int index) {
        By locator = byXpath(
                "(" + BUY_TICKET_BUTTONS_XPATH + ")[" + index + "]");
        return isElementDisplayed(locator);
    }

    public String getBuyTicketButtonText(int index) {
        By locator = byXpath(
                "(" + BUY_TICKET_BUTTONS_XPATH + ")[" + index + "]");

        return getText(locator);
    }

    public double getMoviePosterAspectRatio(int index) {

        int width = getMoviePosterWidthValue(index);
        int height = getMoviePosterHeightValue(index);

        if (height == 0) {
            return 0;
        }

        return (double) width / height;
    }

    public String getMoviePosterUrl(int index) {
        String style = getMoviePosterBackgroundImage(index);

        if (style == null || style.isEmpty()) {
            return "";
        }
        int start = style.indexOf("url(");
        if (start == -1) {
            return "";
        }
        start += 4;
        int end = style.indexOf(")", start);
        if (end == -1) {
            return "";
        }
        String url = style.substring(start, end).trim();
        return url.replace("\"", "").replace("'", "");
    }

    public boolean isMoviePosterUrlValid(int index) {
        String posterUrl = getMoviePosterUrl(index);

        return posterUrl != null
                && !posterUrl.isEmpty()
                && posterUrl.startsWith("http");
    }

    public boolean isDefaultMoviePoster(int index) {
        String posterUrl = getMoviePosterUrl(index);

        return posterUrl.toLowerCase().contains("default-film.webp");
    }

    public boolean isMoviePosterLoaded(int index) {

        By locator = byXpath(
                "(" + MOVIE_POSTERS_XPATH + ")[" + index + "]");

        WebElement poster = waitVisibilityOfElementLocated(locator);

        String backgroundImage = poster.getCssValue("background-image");

        if (backgroundImage == null
                || backgroundImage.isEmpty()
                || backgroundImage.equals("none")) {
            return false;
        }

        return !backgroundImage.contains("default-film.webp");
    }

    public boolean isMoviePosterImageLoaded(int index) {

        String posterUrl = getMoviePosterUrl(index);

        if (posterUrl == null
                || posterUrl.trim().isEmpty()
                || posterUrl.toLowerCase().contains("default-film.webp")) {
            return false;
        }

        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        Boolean loaded = (Boolean) js.executeAsyncScript("""
                var url = arguments[0];
                var callback = arguments[arguments.length - 1];

                var img = new Image();

                img.onload = function() {
                    callback(
                        img.naturalWidth > 0 &&
                        img.naturalHeight > 0
                    );
                };

                img.onerror = function() {
                    callback(false);
                };

                img.src = url;
                """, posterUrl);

        return Boolean.TRUE.equals(loaded);
    }

    public String getFirstMovieHref() {
        By firstMovie = By.xpath(
                "(" + MOVIE_CARDS_XPATH + ")[1]");

        return getAttribute(firstMovie, "href");
    }

    public void hoverMovieByName(String movieName) {

        By locator = byXpath(
                MOVIE_NAME_XPATH.replace("%s", movieName));

        hoverMouse(locator);
    }

    public void moveToTrailer() {
        moveToElement(byMovieDetailTrailer);
    }

    public void hoverToTrailerPlayer() {
        moveToElement(byTrailerPlayer);
    }

    public boolean isTrailerButtonDisplayedByMovieName(String movieName) {

        By locator = By.xpath(
                MOVIE_NAME_XPATH.replace("%s", movieName)
                        + "//img[@alt='video-button']");

        return isElementDisplayed(locator);
    }

    public void clickTrailerByMovieName(String movieName) {

        By locator = byXpath(
                MOVIE_NAME_XPATH.replace("%s", movieName)
                        + "//img[@alt='video-button']");
        click(locator);
    }

    public boolean isTrailerPopupDisplayed() {

        By iframeLocator = byXpath("//iframe");

        return isElementDisplayed(iframeLocator);
    }

    public String getTrailerUrl() {

        By iframeLocator = byXpath("//iframe");

        return getAttribute(iframeLocator, "src");
    }

    public boolean isMovieDetailDurationDisplayed() {
        return isElementDisplayed(byMovieDetailDuration);
    }

    public boolean isMovieDetailReleaseDateDisplayed() {
        return isElementDisplayed(byMovieDetailReleaseDate);
    }

    public void waitForMoviePageChanged(String oldHref) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(driver -> {
                    try {
                        String currentHref = getFirstMovieHref();
                        return currentHref != null
                                && !currentHref.equals(oldHref);
                    } catch (Exception e) {
                        return false;
                    }
                });
    }

    public boolean isMovieDisplayedByName(String movieName) {

        By locator = byXpath(
                MOVIE_NAME_XPATH.replace("%s", movieName));
        
        return isElementDisplayed(locator, 1);
    }

    public int getActivePaginationIndex() {
        paginationButtonsList = waitVisibilityOfElementsLocated(byPaginationButtons);

        WebElement activePagination = waitVisibilityOfElementLocated(byBtnActivePagination);

        for (int i = 0; i < paginationButtonsList.size(); i++) {
            if (paginationButtonsList.get(i).equals(activePagination)) {
                return i;
            }
        }

        return -1;
    }

    public boolean isPaginationActive(int pageNumber) {
        return getActivePaginationIndex() == pageNumber - 1;
    }

    public boolean findMovieAndGoToPage(String movieName) {
        int page = 0;
        while(paginationButtonsList.get(page) != null){
            clickPaginationButton(page);
            if (!isMovieDisplayedByName(movieName)) {
                page++;
            } else {
                return true;
            }
        }
        return false;
    }

    public void clickBuyTicketByMovieName(String movieName) {
        By locator = By.xpath(
                "//a[contains(@href, '/detail/')][.//h4]"
                        + "[.//div[./span[normalize-space()='C18']]"
                        + " and contains(normalize-space(), '" + movieName + "')]"
                        + "//a[normalize-space()='MUA VÉ']");

        click(locator);
    }

    public boolean isMovieDetailDisplayed() {
        return isElementDisplayed(byMovieDetailTitle);
    }

    public String getMovieDetailTitle() {
        return getText(byMovieDetailTitle);
    }

    public String getMovieDetailDuration() {
        return getText(byMovieDetailDuration);
    }

    public String getMovieDetailReleaseDate() {
        return getText(byMovieDetailReleaseDate);
    }

    public boolean isMovieDetailPosterDisplayed() {
        return isElementDisplayed(byMovieDetailPoster);
    }

    public boolean isMovieDetailBuyTicketDisplayed() {
        return isElementDisplayed(byMovieDetailBuyTicket);
    }

    public boolean isMovieDetailRatingDisplayed() {
        return isElementDisplayed(byMovieDetailRating);
    }

    public boolean isCinemaListDisplayed() {
        return isElementDisplayed(byCinemaList);
    }

    public String getMovieDetailRootContent() {
        By root = By.id("root");

        WebElement rootElement = waitVisibilityOfElementLocated(root);

        return rootElement.getAttribute("innerHTML");
    }

    public boolean isMovieDetailPageLoaded() {
        By root = By.id("root");

        WebElement rootElement = waitVisibilityOfElementLocated(root);

        String content = rootElement.getAttribute("innerHTML");

        return content != null && !content.trim().isEmpty();
    }

    public boolean isMovieDetailTitleDisplayed() {
        return isElementDisplayed(By.xpath("//h1"));
    }

    public String getMovieDetailRating() {
        return getAttribute(byMovieDetailRating, "aria-label");
    }

    public void clickTrailerButton() {
        click(byMovieDetailTrailerButton);
    }

    public boolean isTrailerButtonDisplayed() {
        return isElementDisplayed(byMovieDetailTrailerButton);
    }

    public boolean isTrailerPlayerDisplayed() {
        return waitPresenceOfElementLocated(byTrailerPlayer).isDisplayed();
    }

    public boolean isTrailerPlaying() {
        try {
            WebElement iframe = waitVisibilityOfElementLocated(
                    By.cssSelector("div.modal-video iframe"));

            String src = iframe.getAttribute("src");

            LOG.info("Trailer iframe src: " + src);

            return src != null
                    && src.contains("youtube.com/embed/")
                    && src.contains("autoplay=1");

        } catch (Exception e) {
            LOG.error("Không xác định được trailer", e);
            return false;
        }
    }

    public void clickCloseTrailerButton() {
        click(byBtnCloseTrailer);
    }

    public void clickOutsideTrailerPopup() {
        ((JavascriptExecutor) getDriver()).executeScript(
                """
                        arguments[0].click();
                        """,
                waitVisibilityOfElementLocated(By.cssSelector("div.modal-video")));
    }

    public boolean waitUntilTrailerPopupClosed() {
        return waitInVisibilityOfElementLocated(By.cssSelector("div.modal-video"));
    }

    public void clickCinemaButton(String cinemaName) {
        click(By.xpath(MOVIE_DETAIL_CINEMA_SYSTEM_BUTTON.replace("%s", cinemaName)));
    }

    public boolean isCinemaShowtimeDisplayed(String cinemaName) {
        By byCinemaShowtimeBtn = byXpath(MOVIE_DETAIL_CINEMA_NAME.replace("%s", cinemaName));
        return isElementDisplayed(byCinemaShowtimeBtn);
    }

    public String getMovieReleaseDate(String movieName) {
        By releaseDateLocator = By.xpath(MOIVE_DETAIL_RELEASE_DATE.replace("%s", movieName));
        return getText(releaseDateLocator);
    }

    public void clickShowtime(String day, String time) {
        By showtimeLocator = By.xpath(String.format(MOVIE_DETAIL_SHOWTIME_BUTTON, day, time));
        click(showtimeLocator);
    }

    public String getTrailerTitle() {

        WebElement iframe = waitPresenceOfElementLocated(byTrailerPlayer);

        getDriver().switchTo().frame(iframe);

        try {
            WebElement player = waitPresenceOfElementLocated(
                    By.id("player-control-overlay"));

            new Actions(getDriver())
                    .moveToElement(player)
                    .pause(Duration.ofMillis(800))
                    .perform();

            WebElement trailerTitle = waitVisibilityOfElementLocated(
                    byMovieDetailTrailerTitle);

            return trailerTitle.getText().trim();

        } finally {
            getDriver().switchTo().defaultContent();
        }
    }

    public boolean isTrailerRelatedToMovie(String movieName) {
        String trailerTitle = getTrailerTitle();

        if (trailerTitle == null || trailerTitle.trim().isEmpty()) {
            return false;
        }

        String normalizedMovieName = movieName
                .trim()
                .toLowerCase();

        String normalizedTrailerTitle = trailerTitle
                .trim()
                .toLowerCase();

        return normalizedTrailerTitle.contains(normalizedMovieName)
                || normalizedMovieName.contains(normalizedTrailerTitle);
    }

    public boolean isHorizontalScrollbarDisplayed() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        return (Boolean) js.executeScript(
                "return document.documentElement.scrollWidth > "
                        + "document.documentElement.clientWidth;");
    }

    public boolean isMobileLayoutDisplayedCorrectly() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        return (Boolean) js.executeScript(
                "return document.documentElement.scrollWidth <= "
                        + "document.documentElement.clientWidth;");
    }

    public void findHorizontalOverflowElements() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        List<String> elements = (List<String>) js.executeScript(
                "var result = [];" +
                        "var viewportWidth = document.documentElement.clientWidth;" +

                        "document.querySelectorAll('*').forEach(function(element) {" +
                        "    var rect = element.getBoundingClientRect();" +

                        "    if (rect.right > viewportWidth + 1 || rect.left < -1) {" +
                        "        result.push(" +
                        "            element.tagName +" +
                        "            ' class=' + element.className +" +
                        "            ' id=' + element.id +" +
                        "            ' left=' + rect.left +" +
                        "            ' right=' + rect.right +" +
                        "            ' width=' + rect.width" +
                        "        );" +
                        "    }" +
                        "});" +

                        "return result;");

        if (elements.isEmpty()) {
            LOG.info("No horizontal overflow element found");
            ExtentReportManager.info("No horizontal overflow element found");
            return;
        }

        for (String element : elements) {
            LOG.info("Horizontal overflow: " + element);
            ExtentReportManager.info("Horizontal overflow: " + element);
        }
    }

    public void checkMobileHorizontalOverflow() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        Long scrollWidth = (Long) js.executeScript(
                "return document.documentElement.scrollWidth;");

        Long clientWidth = (Long) js.executeScript(
                "return document.documentElement.clientWidth;");

        LOG.info("Mobile clientWidth: " + clientWidth);
        LOG.info("Mobile scrollWidth: " + scrollWidth);

        ExtentReportManager.info(
                "Mobile clientWidth: " + clientWidth
                        + ", scrollWidth: " + scrollWidth);
    }
}
