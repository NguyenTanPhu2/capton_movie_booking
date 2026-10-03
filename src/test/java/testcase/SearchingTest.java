package testcase;

import base.BaseTest;
import data.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MovieDetailPage;
import pages.components.Searching;
import report.ExtentReportManager;

public class SearchingTest extends BaseTest {

    private Searching searching;
    private HomePage homePage;
    private MovieDetailPage movieDetailPage;

    @BeforeMethod
    public void initializePages() {
        searching = new Searching(driver);
        homePage = new HomePage(driver);
        movieDetailPage = new MovieDetailPage(driver);
    }

    @Test(priority = 1, dataProvider = "search-by-name",
            dataProviderClass = TestDataProvider.class, groups = "searching")
    public void verify_Search_Movie_By_Name(String movieName, String tagAge) {

        searching.openHalfScreen();

        ///Step 1: Enter Name film and searching
        LOG.info("Step 1:  Enter Name film and searching");
        ExtentReportManager.info("Step 1:  Enter Name film and searching");
        searching.enterMovieName(movieName);

        ///VP: Hien thi film
        LOG.info("VP: Hien thi film");
        ExtentReportManager.info("VP: Hien thi film");
        String recordingFilmDisplay = searching.isFilmDisplayed(movieName);
        Assert.assertEquals(recordingFilmDisplay, tagAge + movieName, "Movie is not displayed");
    }

    @Test(priority = 2,
            dataProvider = "search-by-character",
            dataProviderClass = TestDataProvider.class, groups = "searching")
    public void verify_Search_Movie_By_Character(String[] movieName, String searchCharacter, String tagAge) {

        searching.openHalfScreen();

        ///Step 1: Enter character in name film and searching
        LOG.info("Step 1: Enter character in name film and searching");
        ExtentReportManager.info("Step 1: Enter character in name film and searching");
        searching.enterMovieName(searchCharacter);

        ///VP 1: Verify show film 'Man of Steel'
        LOG.info("VP 1: Verify show film 'Man of Steel'");
        ExtentReportManager.info("VP 1: Verify show film 'Man of Steel'");
        String recordingFilmManDisplay = searching.isFilmDisplayed(movieName[0]);
        Assert.assertEquals(recordingFilmManDisplay, tagAge + movieName[0], "Movie is not displayed");

        ///VP 2: Verify show film 'AVATAR 2'
        LOG.info("VP 2: Verify show film 'AVATAR 2'");
        ExtentReportManager.info("VP 2: Verify show film 'AVATAR 2''");
        String recordingFilmAvatarDisplay = searching.isFilmDisplayed(movieName[1]);
        Assert.assertEquals(recordingFilmAvatarDisplay, tagAge + movieName[1], "Movie is not displayed");
    }


    @Test(priority = 2,
            dataProvider = "search-non-existing",
            dataProviderClass = TestDataProvider.class, groups = "searching")
    public void verify_Search_Non_Existing_Movie(String movieName) {

        searching.openHalfScreen();

        ///Step 1: Enter name film and searching
        LOG.info("Step 1: Enter name film and searching");
        ExtentReportManager.info("Step 1: Enter and searching");
        searching.enterMovieName(movieName);

        ///VP: Verify result search is null
        LOG.info("VP: Verify result search is null");
        ExtentReportManager.info("VP: Verify result search is null");
        String recordingFilmDisplay = searching.getMessageText();
        Assert.assertEquals(recordingFilmDisplay, "Không có bộ phim nào trùng với từ khóa của bạn.", "Movie is not displayed");
    }

    @Test(priority = 3,
            dataProvider = "search-select-result",
            dataProviderClass = TestDataProvider.class, groups = "searching")
    public void verify_Select_Movie_From_Search_Result(String movieName) {

        searching.openHalfScreen();

        ///Step 1: Enter Name film and searching
        LOG.info("Step 1:  Enter Name film and searching");
        ExtentReportManager.info("Step 1:  Enter Name film and searching");
        searching.enterMovieName(movieName);

        ///Step 2: click On movieName
        LOG.info("Step 2: Click On movieName");
        ExtentReportManager.info("Step 2: Click On movieName");
        homePage.clickOnMovieName(movieName);

        ///VP: Verify navigate Move Detail Page
        LOG.info("VP: Verify navigate Move Detail Page");
        ExtentReportManager.info("VP: Verify navigate Move Detail Page");
        String recordingFilmDisplay = movieDetailPage.getTextNameMovie();
        Assert.assertEquals(recordingFilmDisplay, movieName, "It is not navigate movie detail ");
    }

    @Test(priority = 4, groups = "searching")
    public void verify_Search_With_Empty_Input() {
        searching.openHalfScreen();

        ///Step 1: Enter name film and searching
        LOG.info("Step 1: Enter name film and searching");
        ExtentReportManager.info("Step 1: Enter and searching");
        searching.enterMovieName("");

        ///VP: Verify result search is null
        LOG.info("VP: Verify result search is null");
        ExtentReportManager.info("VP: Verify result search is null");
        boolean recordingSearchingResult = searching.isSearchingResult();
        Assert.assertTrue(recordingSearchingResult, "Search result is displayed when search input contains whitespace");
    }

    @Test(priority = 5, groups = "searching")
    public void verify_Search_With_Whitespace() {
        searching.openHalfScreen();

        ///Step 1: Enter name film and searching
        LOG.info("Step 1: Enter name film and searching");
        ExtentReportManager.info("Step 1: Enter and searching");
        searching.enterMovieName("  ");

        ///VP: Verify search result is not displayed
        LOG.info("VP: Verify search result is not displayed");
        ExtentReportManager.info("VP: Verify search result is not displayed");
        boolean recordingSearchingResult = searching.isSearchingResult();

        Assert.assertTrue(recordingSearchingResult, "Search result is displayed when search input contains whitespace");
    }
    @Test(priority = 6,
            dataProvider = "search-case-insensitive",
            dataProviderClass = TestDataProvider.class,
            groups = "searching")
    public void verify_Search_Case_Insensitive(String searchKeyword, String expectedMovieName) {

        searching.openHalfScreen();

        // Step 1: Enter uppercase movie name and search
        LOG.info("Step 1: Enter uppercase movie name and search");
        ExtentReportManager.info("Step 1: Enter uppercase movie name and search");
        searching.enterMovieName(searchKeyword);

        // VP: Verify movie is displayed regardless of letter case
        LOG.info("VP: Verify movie is displayed regardless of letter case");
        ExtentReportManager.info("VP: Verify movie is displayed regardless of letter case");
        String recordingFilmDisplay = searching.isFilmDisplayed(expectedMovieName);

        Assert.assertTrue(
                recordingFilmDisplay.contains(expectedMovieName),
                "Movie is not displayed when searching with uppercase keyword"
        );
    }
    @Test(priority = 7,
            dataProvider = "search-partial-name",
            dataProviderClass = TestDataProvider.class,
            groups = "searching")
    public void verify_Search_By_Partial_Movie_Name(String searchKeyword, String expectedMovieName) {

        searching.openHalfScreen();

        // Step 1: Enter partial movie name and search
        LOG.info("Step 1: Enter partial movie name and search");
        ExtentReportManager.info("Step 1: Enter partial movie name and search");
        searching.enterMovieName(searchKeyword);

        // VP: Verify movie containing the keyword is displayed
        LOG.info("VP: Verify movie containing the keyword is displayed");
        ExtentReportManager.info("VP: Verify movie containing the keyword is displayed");
        String recordingFilmDisplay = searching.isFilmDisplayed(expectedMovieName);

        Assert.assertTrue(
                recordingFilmDisplay.contains(expectedMovieName),
                "Movie containing the partial search keyword is not displayed"
        );
    }
    @Test(priority = 8,
            dataProvider = "search-special-characters",
            dataProviderClass = TestDataProvider.class, groups = "searching")
    public void verify_Search_With_Special_Characters(String searchKeyword) {

        searching.openHalfScreen();

        // Step 1: Enter special characters and search
        LOG.info("Step 1: Enter special characters and search");
        ExtentReportManager.info("Step 1: Enter special characters and search");
        searching.enterMovieName(searchKeyword);

        // VP: Verify no matching movie message
        LOG.info("VP: Verify no matching movie message");
        ExtentReportManager.info("VP: Verify no matching movie message");
        String recordingMessage = searching.getMessageText();

        Assert.assertEquals(recordingMessage,
                "Không có bộ phim nào trùng với từ khóa của bạn.",
                "Incorrect message");
    }
    @Test(priority = 9,
            dataProvider = "search-long-string",
            dataProviderClass = TestDataProvider.class, groups = "searching")
    public void verify_Search_With_Long_String(String searchKeyword) {

        searching.openHalfScreen();

        // Step 1: Enter long string and search
        LOG.info("Step 1: Enter long string and search");
        ExtentReportManager.info("Step 1: Enter long string and search");
        searching.enterMovieName(searchKeyword);

        // VP: Verify no matching movie message
        LOG.info("VP: Verify no matching movie message");
        ExtentReportManager.info("VP: Verify no matching movie message");
        String recordingMessage = searching.getMessageText();

        Assert.assertEquals(recordingMessage,
                "Không có bộ phim nào trùng với từ khóa của bạn.",
                "Incorrect message");
    }
    @Test(priority = 10,
            dataProvider = "search-trim-whitespace",
            dataProviderClass = TestDataProvider.class, groups = "searching")
    public void verify_Search_With_Leading_And_Trailing_Spaces(String searchKeyword, String expectedMovieName) {

        searching.openHalfScreen();

        // Step 1: Enter movie name with leading and trailing spaces
        LOG.info("Step 1: Enter movie name with leading and trailing spaces");
        ExtentReportManager.info("Step 1: Enter movie name with leading and trailing spaces");
        searching.enterMovieName(searchKeyword);

        // VP: Verify movie is displayed
        LOG.info("VP: Verify movie is displayed");
        ExtentReportManager.info("VP: Verify movie is displayed");
        String recordingFilmDisplay = searching.isFilmDisplayed(expectedMovieName);

        Assert.assertTrue(recordingFilmDisplay.contains(expectedMovieName),
                "Movie is not displayed when keyword contains leading and trailing spaces");
    }
}
