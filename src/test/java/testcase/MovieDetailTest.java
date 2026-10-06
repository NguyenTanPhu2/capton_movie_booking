package testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
import data.TestDataProvider;
import pages.MovieDetailPage;
import report.ExtentReportManager;

public class MovieDetailTest extends BaseTest {

        MovieDetailPage movieDetailPage;

        // Số trang cần kiểm tra
        private static final int TOTAL_PAGES = 2;

        // Số movie trên mỗi trang
        private static final int MOVIES_PER_PAGE = 8;

        @BeforeMethod
        public void initializePages() {
                movieDetailPage = new MovieDetailPage(driver);
                softAssert = new SoftAssert();
        }

        @Test(priority = 1, groups = "movie-detail")
        public void TC_MV_01_CheckMovieCards() {

                // Step 1: Verify movie list on each page
                LOG.info("Step 1: Verify movie list on each page");
                ExtentReportManager.info("Step 1: Verify movie list on each page");

                for (int pageNumber = 1; pageNumber <= TOTAL_PAGES; pageNumber++) {
                        movieDetailPage.clickPaginationButton(pageNumber - 1);
                        checkMoviePage(pageNumber);
                }
                softAssert.assertAll();
        }

        @Test(priority = 2, groups = "movie-detail")
        public void TC_MV_02_CheckHoverMovieCards() {

                // Step 1: Verify hover movie cards on each page
                LOG.info("Step 1: Verify hover movie cards on each page");
                ExtentReportManager.info("Step 1: Verify hover movie cards on each page");

                for (int pageNumber = 1; pageNumber <= TOTAL_PAGES; pageNumber++) {

                        LOG.info("HOVER PAGE " + pageNumber);
                        ExtentReportManager.info("HOVER PAGE " + pageNumber);

                        if (pageNumber > 1) {
                                String firstMovieBeforeChange = movieDetailPage.getFirstMovieHref();

                                softAssert.assertNotNull(firstMovieBeforeChange,
                                                "Không lấy được movie đầu tiên trước khi chuyển trang "
                                                                + pageNumber);
                                movieDetailPage.clickPaginationButton(pageNumber - 1);
                                movieDetailPage.waitForMoviePageChanged(firstMovieBeforeChange);
                        }
                        int movieCount = movieDetailPage.getMovieCardCount();

                        softAssert.assertEquals(movieCount, MOVIES_PER_PAGE,
                                        "Trang " + pageNumber + " phải có " + MOVIES_PER_PAGE + " phim");
                        checkHoverMovieCard(pageNumber);
                }
                softAssert.assertAll();
        }

        @Test(priority = 3, dataProvider = "movie-trailer-click", 
                dataProviderClass = TestDataProvider.class, groups = "movie-detail")
        public void TC_MV_03_CheckClickMovieTrailer(String movieName) {
                // Step 1: Find movie
                LOG.info("Step 1: Find movie " + movieName);
                ExtentReportManager.info("Step 1: Find movie " + movieName);
                boolean movieFound = movieDetailPage.findMovieAndGoToPage(movieName);

                // VP 1: Verify movie is found
                LOG.info("VP 1: Verify movie is found");
                ExtentReportManager.info("VP 1: Verify movie is found");

                softAssert.assertTrue(movieFound, "Không tìm thấy phim: " + movieName);

                // Step 2: Hover to movie card
                LOG.info("Step 2: Hover to movie card");
                ExtentReportManager.info("Step 2: Hover to movie card");

                movieDetailPage.hoverMovieByName(movieName);

                // VP 2: Verify trailer button is displayed
                LOG.info("VP 2: Verify trailer button is displayed");
                ExtentReportManager.info("VP 2: Verify trailer button is displayed");

                softAssert.assertTrue( movieDetailPage.isTrailerButtonDisplayedByMovieName(movieName),
                                "Không hiển thị nút Play trailer của " + movieName);

                // Step 3: Click trailer button
                LOG.info("Step 3: Click trailer button");
                ExtentReportManager.info("Step 3: Click trailer button");
                movieDetailPage.clickTrailerByMovieName(movieName);

                // VP 3: Verify trailer popup is displayed
                LOG.info("VP 3: Verify trailer popup is displayed");
                ExtentReportManager.info("VP 3: Verify trailer popup is displayed");

                // Step 4: Get trailer URL
                LOG.info("Step 4: Get trailer URL");
                ExtentReportManager.info("Step 4: Get trailer URL");

                String trailerUrl = movieDetailPage.getTrailerUrl();

                LOG.info("Trailer URL: " + trailerUrl);
                ExtentReportManager.info("Trailer URL: " + trailerUrl);

                // VP 4: Verify trailer URL
                LOG.info("VP 4: Verify trailer URL");
                ExtentReportManager.info("VP 4: Verify trailer URL");

                softAssert.assertNotNull(trailerUrl,"Trailer không có URL");

                softAssert.assertFalse(trailerUrl.trim().isEmpty(),"URL trailer bị rỗng");
                                
                softAssert.assertAll();
        }

        @Test(priority = 4, groups = "movie-detail")
        public void TC_MV_04_CheckMoviePagination() {

                // Step 1: Verify page 1 is active
                LOG.info("Step 1: Verify page 1 is active");
                ExtentReportManager.info("Step 1: Verify page 1 is active");

                Assert.assertTrue(movieDetailPage.isPaginationActive(1),
                                "Trang 1 chưa được active khi bắt đầu test");

                String firstMoviePage1 = movieDetailPage.getFirstMovieHref();
                Assert.assertNotNull(firstMoviePage1,
                                "Không lấy được phim đầu tiên của trang 1");

                // Step 2: Navigate to page 2
                LOG.info("Step 2: Navigate to page 2");
                ExtentReportManager.info("Step 2: Navigate to page 2");
                movieDetailPage.clickPaginationButton(1);

                movieDetailPage.waitForMoviePageChanged(firstMoviePage1);
        
                // VP 1: Verify page 2 is displayed
                LOG.info("VP 1: Verify page 2 is displayed");
                ExtentReportManager.info("VP 1: Verify page 2 is displayed");
                String firstMoviePage2 = movieDetailPage.getFirstMovieHref();

                Assert.assertNotEquals(firstMoviePage2, firstMoviePage1,
                                "Danh sách phim không thay đổi khi chuyển sang trang 2");

                Assert.assertTrue(movieDetailPage.isPaginationActive(2),
                                "Nút pagination trang 2 chưa chuyển sang màu cam");


                // Step 3: Navigate back to page 1
                LOG.info("Step 3: Navigate back to page 1");
                ExtentReportManager.info("Step 3: Navigate back to page 1");

                movieDetailPage.clickPaginationButton(0);
                movieDetailPage.waitForMoviePageChanged(firstMoviePage2);

                // VP 2: Verify page 1 is displayed again
                LOG.info("VP 2: Verify page 1 is displayed again");
                ExtentReportManager.info("VP 2: Verify page 1 is displayed again");

                String firstMovieAfterBackPage1 = movieDetailPage.getFirstMovieHref();

                Assert.assertEquals(firstMovieAfterBackPage1, firstMoviePage1,
                                "Danh sách phim không quay lại trang 1");

                Assert.assertTrue(movieDetailPage.isPaginationActive(1),
                                "Nút pagination trang 1 chưa chuyển sang màu cam");
        }

        @Test(priority = 5, dataProvider = "movie-detail-navigate", 
                dataProviderClass = TestDataProvider.class, groups = "movie-detail")
        public void TC_MV_05_CheckNavigateToMovieDetail(String movieName) {

                // Step 1: Find movie
                LOG.info("Step 1: Find movie " + movieName);
                ExtentReportManager.info("Step 1: Find movie " + movieName);

                boolean movieFound = movieDetailPage.findMovieAndGoToPage( movieName);

                // VP 1: Verify movie is found
                LOG.info("VP 1: Verify movie is found");
                ExtentReportManager.info("VP 1: Verify movie is found");

                Assert.assertTrue(movieFound, "Không tìm thấy phim: " + movieName);

                // Step 2: Hover to movie card
                LOG.info("Step 2: Hover to movie card");
                ExtentReportManager.info("Step 2: Hover to movie card");
                movieDetailPage.hoverMovieByName(movieName);

                // Step 3: Click MUA VÉ
                LOG.info("Step 3: Click MUA VÉ");
                ExtentReportManager.info(
                                "Step 3: Click MUA VÉ");
                movieDetailPage.clickBuyTicketByMovieName(movieName);

                // VP 2: Verify navigate to Movie Detail page
                LOG.info("VP 2: Verify navigate to Movie Detail page");
                ExtentReportManager.info("VP 2: Verify navigate to Movie Detail page");

                String currentUrl = movieDetailPage.getCurrentUrl();

                softAssert.assertTrue(currentUrl.contains("/detail/"),
                                "Không điều hướng đến trang chi tiết. URL: " + currentUrl);

                // VP 3: Verify Movie Detail page is loaded
                LOG.info("VP 3: Verify Movie Detail page is loaded");
                ExtentReportManager.info("VP 3: Verify Movie Detail page is loaded");
                softAssert.assertTrue(movieDetailPage.isMovieDetailPageLoaded(),
                        "Trang chi tiết phim " + movieName + " bị trắng: #root không có nội dung");

                // VP 4: Verify movie title is displayed
                LOG.info("VP 4: Verify movie title is displayed");
                ExtentReportManager.info("VP 4: Verify movie title is displayed");
                softAssert.assertTrue(movieDetailPage.isMovieDetailTitleDisplayed(),
                        "Trang chi tiết phim " + movieName + " không hiển thị dữ liệu phim");

                softAssert.assertAll();
        }

        @Test(priority = 7, dataProvider = "movie-detail-duration", 
                dataProviderClass = TestDataProvider.class, groups = "movie-detail")
        public void TC_MV_07_CheckMovieNameAndDuration(String movieName, String expectedDuration) {
                // Step 1: Navigation to detail page of Movie
                LOG.info("Step 1: Navigation to detail page of " + movieName);
                ExtentReportManager.info(
                        "Step 1: Navigation to detail page of " + movieName);

                movieDetailPage.findMovieAndGoToPage(movieName);
                movieDetailPage.hoverMovieByName(movieName);
                movieDetailPage.clickBuyTicketByMovieName(movieName);

                // Step 2: Verify movie detail information
                LOG.info("Step 2: Verify movie detail information");
                ExtentReportManager.info("Step 2: Verify movie detail information");

                // VP 1: Verify movie title is displayed
                LOG.info("VP: Verify movie title is displayed");
                ExtentReportManager.info("VP: Verify movie title is displayed");

                Assert.assertTrue(
                        movieDetailPage.isMovieDetailTitleDisplayed(),
                        "Movie title is not displayed on the Movie Detail page");

                // VP 2: Verify movie duration is displayed
                LOG.info("VP: Verify movie duration is displayed");
                ExtentReportManager.info(
                        "VP: Verify movie duration is displayed");

                Assert.assertTrue(
                        movieDetailPage.isMovieDetailDurationDisplayed(),
                         "Movie duration is not displayed on the Movie Detail page");

                // VP 3: Verify movie title
                LOG.info("VP: Verify movie title is correct");
                ExtentReportManager.info("VP: Verify movie title is correct");

                Assert.assertEquals( movieDetailPage.getMovieDetailTitle().trim(), movieName,
                        "Movie title on the Movie Detail page is incorrect");

                // VP 4: Verify movie duration
                LOG.info("VP: Verify movie duration is correct");
                ExtentReportManager.info(
                        "VP: Verify movie duration is correct");

                Assert.assertEquals( movieDetailPage.getMovieDetailDuration().trim(), expectedDuration,
                          "Movie duration on the Movie Detail page is incorrect");
        }

        @Test(priority = 8, dataProvider = "movie-detail-rating", 
                dataProviderClass = TestDataProvider.class, groups = "movie-detail")
        public void TC_MV_08_CheckMovieRating(String movieName) {

                // Step 1: Navigation to detail page of Movie
                LOG.info("Step 1: Navigation to detail page of " + movieName);
                ExtentReportManager.info("Step 1: Navigation to detail page of " + movieName);

                movieDetailPage.findMovieAndGoToPage(movieName);
                movieDetailPage.hoverMovieByName(movieName);
                movieDetailPage.clickBuyTicketByMovieName(movieName);

                // VP 1: Check movie is displayed
                LOG.info("VP 1: Check movie is displayed");
                ExtentReportManager.info("VP 1: Check movie is displayed");
                Assert.assertTrue(
                                movieDetailPage.isMovieDetailRatingDisplayed(),
                                "Rating movie is not display");

                // Lấy rating từ aria-label
                String actualRating = movieDetailPage.getMovieDetailRating();

                Assert.assertNotNull(
                                actualRating,
                                "Không lấy được rating của phim");

                Assert.assertFalse(
                                actualRating.trim().isEmpty(),
                                "Rating phim đang rỗng");

                Assert.assertTrue(
                                actualRating.matches("\\d+ Stars"),
                                "Rating không đúng định dạng: " + actualRating);

                LOG.info("Movie rating: " + actualRating);
        }

        @Test(priority = 11, dataProvider = "movie-detail-trailer-playing",
                dataProviderClass = TestDataProvider.class, groups = "movie-detail")
        public void TC_MV_011_CheckMovieTrailerDisplayedCorrectly(String movieName) {
                // String movieName = "John Cena WWE";
                /// Step 1: Navigation to detail page of Movie
                LOG.info("Step 1: Navigation to detail page of " + movieName);
                ExtentReportManager.info("Step 1: Navigation to detail page of " + movieName);

                movieDetailPage.findMovieAndGoToPage(movieName);
                movieDetailPage.hoverMovieByName(movieName);
                movieDetailPage.clickBuyTicketByMovieName(movieName);

                /// Step 2: Hover to Trailer and click on trailer
                LOG.info("Step 2: Hover to Trailer and click on trailer");
                ExtentReportManager.info("Step 2: Hover to Trailer and click on trailer");

                movieDetailPage.moveToTrailer();
                movieDetailPage.clickTrailerButton();

                /// VP 1: Verify trailer is displayed
                LOG.info("VP 1: Verify trailer is displayed");
                ExtentReportManager.info(
                        "VP 1: Verify trailer is displayed");

                Assert.assertTrue(
                        movieDetailPage.isTrailerPopupDisplayed(),
                        "Trailer không được mở");

                /// Step 3: Get trailer title
                LOG.info("Step 3: Get trailer title");
                ExtentReportManager.info(
                        "Step 3: Get trailer title"
                );

                String trailerTitle = movieDetailPage.getTrailerTitle();

                LOG.info("Movie name: " + movieName);
                LOG.info("Trailer title: " + trailerTitle);

                ExtentReportManager.info(
                        "Movie name: " + movieName);

                ExtentReportManager.info(
                        "Trailer title: " + trailerTitle);

                /// VP 2: Verify trailer is related to movie
                LOG.info("VP 2: Verify trailer is related to movie");
                ExtentReportManager.info(
                        "VP 2: Verify trailer is related to movie");

                Assert.assertTrue(
                        movieDetailPage.isTrailerRelatedToMovie(movieName),
                        "Trailer is not related to the movie: " + movieName
                                + ". Trailer title: " + trailerTitle);
        }

        @Test(priority = 12, dataProvider = "movie-detail-trailer-playing", 
                dataProviderClass = TestDataProvider.class, groups = "movie-detail")
        public void TC_MV_012_CheckMovieTrailerPlayNormally() {
                String movieName = "Man of Steel";
                // Step 1: Navigation to detail page of Movie
                LOG.info("Step 1: Navigation to detail page of " + movieName);
                ExtentReportManager.info("Step 1: Navigation to detail page of " + movieName);

                movieDetailPage.findMovieAndGoToPage(movieName);
                movieDetailPage.hoverMovieByName(movieName);
                movieDetailPage.clickBuyTicketByMovieName(movieName);

                // Step 2: Hover to movie card and click trailer button
                LOG.info("Step 2: Hover to movie card and click trailer button");
                ExtentReportManager.info("Step 2: Hover to movie card and click trailer button");
                movieDetailPage.moveToTrailer();
                movieDetailPage.clickTrailerButton();

                // VP 1: Trailer popup is open
                LOG.info("VP 1: Trailer popup is open");
                ExtentReportManager.info("VP 1: Trailer popup is open");
                Assert.assertTrue(
                                movieDetailPage.isTrailerPopupDisplayed(),
                                "Trailer not open");

                // VP 2: Verify trailer player
                LOG.info("VP 2: Verify trailer player");
                ExtentReportManager.info("VP 2: Verify trailer player");
                Assert.assertTrue(
                                movieDetailPage.isTrailerPlayerDisplayed(),
                                "Trình phát trailer không hiển thị");

                // VP 3: Verify trailer is playing
                LOG.info("VP 3: Verify trailer is playing");
                ExtentReportManager.info("VP 3: Verify trailer is playing");
                Assert.assertTrue(
                                movieDetailPage.isTrailerPlaying(),
                                "Trailer is not playing");
        }

        @Test(priority = 14, dataProvider = "movie-detail-trailer-close", 
                dataProviderClass = TestDataProvider.class, groups = "movie-detail")
        public void TC_MV_014_CloseTrailerByXButton(String movieName) {

                // Step 1: Navigation to detail page of Movie
                LOG.info("Step 1: Navigation to detail page of " + movieName);
                ExtentReportManager.info("Step 1: Navigation to detail page of " + movieName);

                movieDetailPage.findMovieAndGoToPage(movieName);
                movieDetailPage.hoverMovieByName(movieName);
                movieDetailPage.clickBuyTicketByMovieName(movieName);

                // Step 2: Hover to movie card and click trailer button
                LOG.info("Step 2: Hover to movie card and click trailer button");
                ExtentReportManager.info("Step 2: Hover to movie card and click trailer button");
                movieDetailPage.moveToTrailer();
                movieDetailPage.clickTrailerButton();

                // Step 3: Click X button to close trailer
                LOG.info("Step 4: Click X button to close trailer");
                ExtentReportManager.info("Step 4: Click X button to close trailer");
                movieDetailPage.clickCloseTrailerButton();

                // VP: Verify trailer is closed
                LOG.info("VP: Verify trailer is closed");
                ExtentReportManager.info("VP: Verify trailer is closed");

                Assert.assertFalse(
                                movieDetailPage.isTrailerPopupDisplayed(),
                                "Trailer vẫn hiển thị sau khi click nút X");
        }

        @Test(priority = 15, dataProvider = "movie-trailer-close", 
                dataProviderClass = TestDataProvider.class, groups = "movie-detail")
        public void TC_MV_015_CloseTrailerByClickOutsidePopup(String movieName) {

                // Step 1: Navigation to detail page of Movie
                LOG.info("Step 1: Navigation to detail page of " + movieName);
                ExtentReportManager.info("Step 1: Navigation to detail page of " + movieName);

                movieDetailPage.findMovieAndGoToPage(movieName);

                movieDetailPage.hoverMovieByName(movieName);

                movieDetailPage.clickBuyTicketByMovieName(movieName);

                // Step 2: Hover to movie card and click trailer button
                LOG.info("Step 2: Hover to movie card and click trailer button");
                ExtentReportManager.info(
                                "Step 2: Hover to movie card and click trailer button");

                movieDetailPage.moveToTrailer();
                movieDetailPage.clickTrailerButton();

                // Step 3: Click outside popup trailer
                LOG.info("Step 3: Click outside popup trailer");
                ExtentReportManager.info(
                                "Step 3: Click outside popup trailer");
                movieDetailPage.clickOutsideTrailerPopup();

                // VP: Trailer was close
                Assert.assertTrue(
                                movieDetailPage.waitUntilTrailerPopupClosed(),
                                "Trailer vẫn hiển thị sau khi click ngoài popup");
        }

        @Test(priority = 16, dataProvider = "movie-detail-poster", 
                dataProviderClass = TestDataProvider.class, groups = "movie-detail")
        public void TC_MV_016_verify_Movie_Detail_Poster(String movieName) {

                // Step 1: Navigation to detail page of Movie
                LOG.info("Step 1: Navigation to detail page of " + movieName);
                ExtentReportManager.info("Step 1: Navigation to detail page of " + movieName);

                movieDetailPage.findMovieAndGoToPage(movieName);
                movieDetailPage.hoverMovieByName(movieName);
                movieDetailPage.clickBuyTicketByMovieName(movieName);

                /// Step 2: Observe poster, movie name and description
                LOG.info("Step 2: Observe poster, movie name and description");
                ExtentReportManager.info(
                                "Step 2: Observe poster, movie name and description");

                /// VP 1: Verify movie poster is displayed
                LOG.info("VP 1: Verify movie poster is displayed");
                ExtentReportManager.info("VP 1: Verify movie poster is displayed");

                Assert.assertTrue(
                                movieDetailPage.isMovieDetailPosterDisplayed(),
                                "Movie poster is not displayed correctly");

                /// VP 2: Verify movie name is displayed
                LOG.info("VP 2: Verify movie name is displayed");
                ExtentReportManager.info("VP 2: Verify movie name is displayed");

                Assert.assertTrue(
                                movieDetailPage.isMovieDetailTitleDisplayed(),
                                "Movie name is not displayed");

                Assert.assertEquals(
                                movieDetailPage.getMovieDetailTitle().trim(),
                                movieName,
                                "Movie name is incorrect");
        }

        @Test(priority = 17, dataProvider = "movie-detail-cinema-system-switching", dataProviderClass = TestDataProvider.class, groups = "movie-detail")
        public void TC_MV_017_verify_Movie_Detail_Cinema_System_Switching(String movieName) {

                // Step 1: Navigation to detail page of Movie
                LOG.info("Step 1: Navigation to detail page of " + movieName);
                ExtentReportManager.info("Step 1: Navigation to detail page of " + movieName);

                movieDetailPage.hoverMovieByName(movieName);
                movieDetailPage.clickBuyTicketByMovieName(movieName);

                /// Step 2: Click CGV cinema system
                LOG.info("Step 2: Click CGV cinema system");
                ExtentReportManager.info("Step 2: Click CGV cinema system");

                movieDetailPage.clickCinemaButton("cgv");

                /// VP 1: Verify CGV cinema system is displayed
                LOG.info("VP 1: Verify CGV cinema system is displayed");
                ExtentReportManager.info(
                                "VP 1: Verify CGV cinema system is displayed");

                Assert.assertTrue(
                                movieDetailPage.isCinemaShowtimeDisplayed("CGV"),
                                "CGV cinema system is not displayed");

                /// Step 3: Click BHD cinema system
                LOG.info("Step 3: Click BHD cinema system");
                ExtentReportManager.info("Step 3: Click BHD cinema system");

                movieDetailPage.clickCinemaButton("bhd");

                /// VP 2: Verify BHD cinema system is displayed
                LOG.info("VP 2: Verify BHD cinema system is displayed");
                ExtentReportManager.info(
                                "VP 2: Verify BHD cinema system is displayed");

                Assert.assertTrue(
                                movieDetailPage.isCinemaShowtimeDisplayed("BHD"),
                                "BHD cinema system is not displayed");

                /// Step 4: Click MegaGS cinema system
                LOG.info("Step 4: Click MegaGS cinema system");
                ExtentReportManager.info(
                                "Step 4: Click MegaGS cinema system");

                movieDetailPage.clickCinemaButton("mega");

                /// VP 3: Verify MegaGS cinema system is displayed
                LOG.info("VP 3: Verify MegaGS cinema system is displayed");
                ExtentReportManager.info(
                                "VP 3: Verify MegaGS cinema system is displayed");

                Assert.assertTrue(
                                movieDetailPage.isCinemaShowtimeDisplayed("MegaGS"),
                                "MegaGS cinema system is not displayed");
        }

        @Test(priority = 19, dataProvider = "movie-detail-navigate-to-booking", dataProviderClass = TestDataProvider.class, groups = "movie-detail")
        public void TC_MV_019_verify_Click_Showtime_Navigate_To_Booking(String movieName, String day, String time) {

                // Step 1: Navigation to detail page of Movie
                LOG.info("Step 1: Navigation to detail page of " + movieName);
                ExtentReportManager.info("Step 1: Navigation to detail page of " + movieName);

                // TODO: Navigate to Movie Detail
                movieDetailPage.findMovieAndGoToPage(movieName);
                movieDetailPage.hoverMovieByName(movieName);
                movieDetailPage.clickBuyTicketByMovieName(movieName);

                /// Step 2: Click a showtime
                LOG.info("Step 2: Click a showtime");
                ExtentReportManager.info("Step 2: Click a showtime");

                movieDetailPage.clickShowtime(day, time);

                /// VP: Verify navigate to Booking page
                LOG.info("VP: Verify navigate to Booking page");
                ExtentReportManager.info("VP: Verify navigate to Booking page");

                String currentUrl = movieDetailPage.getCurrentUrl();

                LOG.info("Current URL: " + currentUrl);
                ExtentReportManager.info("Current URL: " + currentUrl);

                Assert.assertTrue(
                                currentUrl.contains("/purchase/"),
                                "User is not navigated to Booking page");
        }

        @Test(priority = 21, dataProvider = "movie-detail-release-date", dataProviderClass = TestDataProvider.class, groups = "movie-detail")
        public void TC_MV_021_verify_Movie_Detail_Release_Date_Format(String movieName, String expectedReleaseDate) {

                // Step 1: Navigation to detail page of Movie
                LOG.info("Step 1: Navigation to detail page of " + movieName);
                ExtentReportManager.info("Step 1: Navigation to detail page of " + movieName);

                movieDetailPage.findMovieAndGoToPage(movieName);
                movieDetailPage.hoverMovieByName(movieName);
                movieDetailPage.clickBuyTicketByMovieName(movieName);

                /// Step 2: Get movie release date
                LOG.info("Step 2: Get movie release date");
                ExtentReportManager.info("Step 2: Get movie release date");

                String releaseDate = movieDetailPage.getMovieReleaseDate(movieName);

                LOG.info("Movie name: " + movieName);
                LOG.info("Expected release date: " + expectedReleaseDate);
                LOG.info("Actual release date: " + releaseDate);

                ExtentReportManager.info("Movie name: " + movieName);
                ExtentReportManager.info(
                                "Expected release date: " + expectedReleaseDate);
                ExtentReportManager.info(
                                "Actual release date: " + releaseDate);

                /// VP 1: Verify release date is not invalid
                LOG.info("VP 1: Verify release date is valid");
                ExtentReportManager.info(
                                "VP 1: Verify release date is valid");

                Assert.assertNotNull(
                                releaseDate,
                                "Movie release date is null");

                Assert.assertFalse(
                                releaseDate.trim().isEmpty(),
                                "Movie release date is empty");

                Assert.assertNotEquals(
                                releaseDate.trim(),
                                "Invalid Date",
                                "Movie release date displays Invalid Date");

                /// VP 2: Verify release date format is dd.MM.yyyy
                LOG.info("VP 2: Verify release date format is dd.MM.yyyy");
                ExtentReportManager.info(
                                "VP 2: Verify release date format is dd.MM.yyyy");

                Assert.assertTrue(
                                releaseDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}"),
                                "Movie release date does not have format dd.MM.yyyy: "
                                                + releaseDate);

                /// VP 3: Verify release date matches expected data
                LOG.info("VP 3: Verify release date matches expected data");
                ExtentReportManager.info(
                                "VP 3: Verify release date matches expected data");

                Assert.assertEquals(
                                releaseDate.trim(),
                                expectedReleaseDate.trim(),
                                "Movie release date does not match expected data");
        }

        @Test(priority = 22, dataProvider = "movie-detail-mobile",
                dataProviderClass = TestDataProvider.class, groups = "movie-detail")
        public void TC_MV_022_verify_Movie_Detail_On_Mobile(String movieName) {

                /// Step 1: Switch browser to mobile view
                LOG.info("Step 1: Switch browser to mobile view");
                ExtentReportManager.info("Step 1: Switch browser to mobile view");

                movieDetailPage.switchToPhone();

                LOG.info("Window width: " + driver.manage().window().getSize().getWidth());
                LOG.info("Window height: " + driver.manage().window().getSize().getHeight());

                /// Step 2: Navigate to Movie Detail
                LOG.info("Step 2: Navigate to Movie Detail");
                ExtentReportManager.info("Step 2: Navigate to Movie Detail");

                movieDetailPage.findMovieAndGoToPage(movieName);

                movieDetailPage.hoverMovieByName(movieName);
                movieDetailPage.clickBuyTicketByMovieName(movieName);

                /// Step 3: Scroll through Movie Detail page
                LOG.info("Step 3: Scroll through Movie Detail page");
                ExtentReportManager.info("Step 3: Scroll through Movie Detail page");

                movieDetailPage.scroll();

                /// Step 4: Check horizontal overflow
                LOG.info("Step 4: Check horizontal overflow");
                ExtentReportManager.info("Step 4: Check horizontal overflow");

                
                movieDetailPage.checkMobileHorizontalOverflow();
                movieDetailPage.findHorizontalOverflowElements();

                /// VP 1: Verify page does not have horizontal scrollbar
                LOG.info("VP 1: Verify page does not have horizontal scrollbar");
                ExtentReportManager.info("VP 1: Verify page does not have horizontal scrollbar");

                Assert.assertFalse(
                        movieDetailPage.isHorizontalScrollbarDisplayed(),
                        "Movie Detail page has horizontal scrollbar on mobile");

                /// VP 2: Verify page layout is not overflowed
                LOG.info("VP 2: Verify Movie Detail page layout is not overflowed");
                ExtentReportManager.info(
                        "VP 2: Verify Movie Detail page layout is not overflowed");

                Assert.assertTrue(
                        movieDetailPage.isMobileLayoutDisplayedCorrectly(),
                        "Movie Detail page layout is broken or overflowed on mobile");
        }


        @Test(priority = 23, dataProvider = "movie-detail-refresh",
                dataProviderClass = TestDataProvider.class, groups = "movie-detail")
        public void TC_MV_023_verify_Refresh_Movie_Detail(String movieName) {

                /// Step 1: Navigate to Movie Detail
                LOG.info("Step 1: Navigate to Movie Detail");
                ExtentReportManager.info("Step 1: Navigate to Movie Detail");

                movieDetailPage.findMovieAndGoToPage(movieName);
                movieDetailPage.hoverMovieByName(movieName);
                movieDetailPage.clickBuyTicketByMovieName(movieName);

                /// Step 2: Refresh Movie Detail page
                LOG.info("Step 2: Refresh Movie Detail page");
                ExtentReportManager.info("Step 2: Refresh Movie Detail page");

                movieDetailPage.refreshPage();

                /// VP 1: Verify Movie Detail page is displayed
                LOG.info("VP 1: Verify Movie Detail page is displayed");
                ExtentReportManager.info( "VP 1: Verify Movie Detail page is displayed");

                Assert.assertTrue( movieDetailPage.isMovieDetailTitleDisplayed(),
                        "Movie Detail page is blank after refresh");

                /// VP: Verify movie name is correct after refresh
                LOG.info("VP 1: Verify movie name is correct after refresh");
                ExtentReportManager.info(
                        "VP 1: Verify movie name is correct after refresh"
                );

                String actualMovieName = movieDetailPage.getMovieDetailTitle();

                LOG.info("Expected movie name: " + movieName);
                LOG.info("Actual movie name: " + actualMovieName);

                ExtentReportManager.info("Expected movie name: " + movieName);

                ExtentReportManager.info("Actual movie name: " + actualMovieName);
                Assert.assertEquals( actualMovieName.trim(), movieName.trim(),
                        "Movie name is incorrect after refresh");
        }

        private void checkMoviePage(int pageNumber) {

                LOG.info("CHECK PAGE " + pageNumber);

                ExtentReportManager.info("CHECK PAGE " + pageNumber);

                int movieCount = movieDetailPage.getMovieCardCount();

                LOG.info("Page " + pageNumber + " movie count: " + movieCount);

                ExtentReportManager.info("Page " + pageNumber + " movie count: " + movieCount);

                softAssert.assertEquals(
                                movieCount,
                                MOVIES_PER_PAGE,
                                "Trang " + pageNumber + " phải hiển thị " + MOVIES_PER_PAGE + " phim");

                softAssert.assertTrue(
                                movieDetailPage.isMovieListDisplayed(),
                                "Trang " + pageNumber + " không hiển thị danh sách phim");

                for (int movieIndex = 1; movieIndex <= movieCount; movieIndex++) {
                        checkMovieCard(pageNumber, movieIndex);
                }

                softAssert.assertTrue(
                                movieDetailPage.areAgeRatingsDisplayed(),
                                "Trang " + pageNumber + " không hiển thị nhãn C18");

                softAssert.assertTrue(
                                movieDetailPage.areMovieTitlesDisplayed(),
                                "Trang " + pageNumber + " không hiển thị tên phim");

                softAssert.assertTrue(
                                movieDetailPage.areMovieDescriptionsDisplayed(),
                                "Trang " + pageNumber + " không hiển thị mô tả phim");

                softAssert.assertTrue(
                                movieDetailPage.areMoviePostersDisplayed(),
                                "Trang " + pageNumber + " không hiển thị poster");

                LOG.info("PAGE " + pageNumber + " PASSED");

                ExtentReportManager.info("PAGE " + pageNumber + " PASSED");
        }

        private void checkMovieCard(int pageNumber, int movieIndex) {

                String movieTitle = movieDetailPage.getMovieTitle(movieIndex);

                LOG.info("Page " + pageNumber + " - Movie " + movieIndex + ": " + movieTitle);

                ExtentReportManager.info("Page " + pageNumber + " - Movie " + movieIndex + ": " + movieTitle);

                String ageRating = movieDetailPage.getMovieAgeRating(movieIndex);

                softAssert.assertEquals(ageRating, "C18",
                                "Page " + pageNumber + " - Movie [" + movieTitle + "] không có nhãn C18");

                softAssert.assertFalse(movieTitle == null || movieTitle.trim().isEmpty(),
                                "Page " + pageNumber + " - Movie [" + movieTitle + "] không có tên phim");

                String description = movieDetailPage.getMovieDescription(movieIndex);

                softAssert.assertFalse(description == null || description.trim().isEmpty(),
                                "Page " + pageNumber + " - Movie [" + movieTitle + "] không có mô tả");

                String posterUrl = movieDetailPage.getMoviePosterUrl(movieIndex);

                softAssert.assertFalse(posterUrl == null || posterUrl.trim().isEmpty(),
                                "Page " + pageNumber + " - Movie [" + movieTitle + "] không có poster URL");

                softAssert.assertTrue(posterUrl.startsWith("http"),
                                "Page " + pageNumber + " - Movie [" + movieTitle + "] poster URL không hợp lệ: "
                                                + posterUrl);
                ExtentReportManager.info(posterUrl);

                softAssert.assertFalse(movieDetailPage.isDefaultMoviePoster(movieIndex),
                                "Page " + pageNumber + " - Movie [" + movieTitle + "] đang sử dụng default poster");

                boolean posterLoaded = movieDetailPage.isMoviePosterImageLoaded(movieIndex);

                String posterErrorMessage = "Poster phim " + movieIndex + " [" + movieTitle + "]" + " có URL "
                                + posterUrl
                                + " nhưng ảnh không load được";

                if (!posterLoaded) {

                        LOG.error(posterErrorMessage);

                        ExtentReportManager.info("❌ " + posterErrorMessage);
                }

                softAssert.assertTrue(posterLoaded, posterErrorMessage);

                int width = movieDetailPage.getMoviePosterWidthValue(movieIndex);

                int height = movieDetailPage.getMoviePosterHeightValue(movieIndex);

                String backgroundSize = movieDetailPage
                                .getMoviePosterBackgroundSize(movieIndex);

                String backgroundPosition = movieDetailPage
                                .getMoviePosterBackgroundPosition(movieIndex);

                LOG.info(
                                "Poster UI - Movie [" + movieTitle + "]: " + "width=" + width + ", height=" + height
                                                + ", background-size=" + backgroundSize + ", background-position="
                                                + backgroundPosition);

                ExtentReportManager.info(
                                "Poster UI - Movie [" + movieTitle + "]: " + "width=" + width + ", height=" + height
                                                + ", background-size=" + backgroundSize + ", background-position="
                                                + backgroundPosition);

                softAssert.assertTrue(width > 0,
                                "Page " + pageNumber + " - Movie [" + movieTitle + "] poster width không hợp lệ: "
                                                + width);

                softAssert.assertTrue(height > 0,
                                "Page " + pageNumber + " - Movie [" + movieTitle + "] poster height không hợp lệ: "
                                                + height);

                softAssert.assertFalse(backgroundSize == null || backgroundSize.trim().isEmpty(),
                                "Page " + pageNumber + " - Movie [" + movieTitle + "] không có background-size");

                softAssert.assertFalse(backgroundPosition == null || backgroundPosition.trim().isEmpty(),
                                "Page " + pageNumber + " - Movie [" + movieTitle + "] không có background-position");

                LOG.info("Movie [" + movieTitle + "] - " + "C18: OK | " + "Title: OK | "
                                + "Description: OK | " + "Poster URL: OK | " + "Poster UI: OK");

                ExtentReportManager.info("Movie [" + movieTitle + "] - " + "C18: OK | " + "Title: OK | "
                                + "Description: OK | " + "Poster URL: OK | " + "Poster UI: OK");
        }

        private void checkHoverMovieCard(int pageNumber) {

                int movieCount = movieDetailPage.getMovieCardCount();

                for (int movieIndex = 1; movieIndex <= movieCount; movieIndex++) {

                        LOG.info("Page " + pageNumber + " - Hover movie " + movieIndex);

                        ExtentReportManager.info("Page " + pageNumber + " - Hover movie " + movieIndex);

                        movieDetailPage.hoverMovieCard(movieIndex);

                        softAssert.assertTrue(
                                        movieDetailPage.isTrailerButtonDisplayed(movieIndex),
                                        "Page " + pageNumber + " - Movie " + movieIndex
                                                        + ": Không hiển thị nút Play trailer");

                        // Kiểm tra nút MUA VÉ
                        softAssert.assertTrue(
                                        movieDetailPage.isBuyTicketButtonDisplayed(movieIndex),
                                        "Page " + pageNumber + " - Movie " + movieIndex
                                                        + ": Không hiển thị nút MUA VÉ");

                        // Kiểm tra text MUA VÉ
                        softAssert.assertEquals(
                                        movieDetailPage.getBuyTicketButtonText(movieIndex),
                                        "MUA VÉ",
                                        "Page " + pageNumber + " - Movie " + movieIndex + ": Text nút không đúng");
                }
        }
}
