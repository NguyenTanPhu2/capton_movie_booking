package testcase;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
import pages.NewsPage;
import report.ExtentReportManager;

public class NewsTest extends BaseTest {

        NewsPage newsPage;

        @BeforeMethod
        public void initializePages() {
                newsPage = new NewsPage(driver);
                softAssert = new SoftAssert();
        }

        @Test(priority = 1, groups = "news")
        public void TC_NEW_01() throws InterruptedException {

                // Step 1: Navigate to News section
                LOG.info("Step 1: Navigate to News section");
                ExtentReportManager.info("Step 1: Navigate to News section");

                newsPage.getTopNavigation().clickOnNews();
                newsPage.scrollToNewsSection();

                // VP 1: Verify News section is displayed
                LOG.info("VP 1: Verify News section is displayed");
                ExtentReportManager.info("VP 1: Verify News section is displayed");

                softAssert.assertTrue(
                                newsPage.isNewsSectionDisplayed(),
                                "VP 1: News section is not displayed");

                // VP 2: Verify "Điện Ảnh 24h" tab is displayed
                LOG.info("VP 2: Verify 'Điện Ảnh 24h' tab is displayed");
                ExtentReportManager.info("VP 2: Verify 'Điện Ảnh 24h' tab is displayed");

                softAssert.assertTrue(
                                newsPage.isNewsTabDisplayed("Điện Ảnh 24h"),
                                "VP 2: Tab 'Điện Ảnh 24h' is not displayed");

                // VP 3: Verify "Review" tab is displayed
                LOG.info("VP 3: Verify 'Review' tab is displayed");
                ExtentReportManager.info("VP 3: Verify 'Review' tab is displayed");

                softAssert.assertTrue(
                                newsPage.isNewsTabDisplayed("Review"),
                                "VP 3: Tab 'Review' is not displayed");

                // VP 4: Verify "Khuyến Mãi" tab is displayed
                LOG.info("VP 4: Verify 'Khuyến Mãi' tab is displayed");
                ExtentReportManager.info("VP 4: Verify 'Khuyến Mãi' tab is displayed");

                softAssert.assertTrue(newsPage.isNewsTabDisplayed("Khuyến mãi"),
                                "VP 4: Tab 'Khuyến Mãi' is not displayed");

                softAssert.assertAll();
        }

        @Test(priority = 2, groups = "news")
        public void TC_NEW_02() {

                // Step 1: Navigate to News section
                LOG.info("Step 1: Navigate to News section");
                ExtentReportManager.info("Step 1: Navigate to News section");

                newsPage.getTopNavigation().clickOnNews();
                newsPage.scrollToNewsSection();

                //Step 2: Click "Xem thêm" button
                LOG.info("Step 2: Click \"Xem thêm\" button");
                ExtentReportManager.info("Step 2: Click \"Xem thêm\" button");

                newsPage.clickCollapseButton();
                newsPage.waitForNewsArticlesLoaded();

                // VP 1: Verify 2 large news articles are displayed
                LOG.info("VP 1: Verify 2 large news articles are displayed");
                ExtentReportManager.info("VP 1: Verify 2 large news articles are displayed");

                int largeNewsCount = newsPage.getLargeNewsArticleCount();

                LOG.info("Large news article count: " + largeNewsCount);
                ExtentReportManager.info("Large news article count: " + largeNewsCount);

                softAssert.assertEquals(largeNewsCount,2,
                                "VP 1: Expected 2 large news articles but found " + largeNewsCount);

                // VP 2: Verify large articles have title
                LOG.info("VP 2: Verify large articles have title");
                ExtentReportManager.info(
                                "VP 2: Verify large articles have title");

                int largeTitleCount = newsPage.getLargeNewsArticleTitleCount();

                LOG.info("Large article title count: " + largeTitleCount);
                ExtentReportManager.info(
                                "Large article title count: " + largeTitleCount);

                softAssert.assertEquals(largeTitleCount,2,
                                "VP 2: Expected 2 large article titles but found " + largeTitleCount);

                // VP 3: Verify large articles have description
                LOG.info("VP 3: Verify large articles have description");
                ExtentReportManager.info(
                                "VP 3: Verify large articles have description");

                int largeDescriptionCount = newsPage.getLargeNewsArticleDescriptionsCount();

                LOG.info("Large Article Description Count: " + largeDescriptionCount);
                ExtentReportManager.info(
                                "Large Article Description Count: " + largeDescriptionCount);

                softAssert.assertEquals(largeDescriptionCount, 2,
                                "VP 3: Expected 2 large article descriptions but found " + largeDescriptionCount);

                // VP 4: Verify 2 medium news articles are displayed
                LOG.info("VP 4: Verify 2 medium news articles are displayed");
                ExtentReportManager.info("VP 4: Verify 2 medium news articles are displayed");

                int mediumNewsCount = newsPage.getMediumNewsArticleCount();

                LOG.info("Medium news article count: " + mediumNewsCount);
                ExtentReportManager.info("Medium news article count: " + mediumNewsCount);

                softAssert.assertEquals(mediumNewsCount, 2,
                                "VP 4: Expected 2 medium news articles but found " + mediumNewsCount);

                // VP 5: Verify medium articles have title
                LOG.info("VP 5: Verify medium articles have title");
                ExtentReportManager.info(
                                "VP 5: Verify medium articles have title");

                int mediumTitleCount = newsPage.getMediumNewsArticleTitleCount();

                LOG.info("Medium article title count: " +  mediumTitleCount);
                ExtentReportManager.info(
                                "Medium article title count: " +  mediumTitleCount);

                softAssert.assertEquals( mediumTitleCount,2,
                                "VP 5: Expected 2 medium article titles but found " +  mediumTitleCount);

                /// VP 6: Verify medium articles have description
                LOG.info("VP 6: Verify medium articles have description");
                ExtentReportManager.info(
                                "VP 6: Verify medium articles have description");

                int mediumDescriptionCount = newsPage.getMediumNewsArticleDescriptionsCount();

                LOG.info("Medium Article Description Count: " + mediumDescriptionCount);
                ExtentReportManager.info("Medium Article Description Count: " + mediumDescriptionCount);

                softAssert.assertEquals(mediumDescriptionCount, 2,
                                "VP 6: Expected 2 medium article descriptions but found " + mediumDescriptionCount);

                // VP 7: Verify 4 small news articles are displayed
                LOG.info("VP 7: Verify 4 small news articles are displayed");
                ExtentReportManager.info("VP 7: Verify 4 small news articles are displayed");

                int smallNewsCount = newsPage.getSmallNewsArticleCount();

                LOG.info("Small news article count: " + smallNewsCount);
                ExtentReportManager.info("Small news article count: " + smallNewsCount);

                softAssert.assertEquals(smallNewsCount,4,
                                "VP 7: Expected 4 small news articles but found " + smallNewsCount);

                // VP 8: Verify small articles have title
                LOG.info("VP 8: Verify small articles have title");
                ExtentReportManager.info(
                                "VP 8: Verify small articles have title");

                int smallTitleCount = newsPage.getSmallNewsArticleTitleCount();

                LOG.info("Small article title count: " + smallTitleCount);
                ExtentReportManager.info(
                                "Small article title count: " + smallTitleCount);

                softAssert.assertEquals(smallTitleCount,4,
                                "VP 8: Expected 4 small article titles but found " + smallTitleCount);

                // VP 9: Verify news images are displayed
                LOG.info("VP 9: Verify news images are displayed");
                ExtentReportManager.info(
                                "VP 9: Verify news images are displayed");

                int newsImageCount = newsPage.getNewsImageCount();

                LOG.info("News image count: " + newsImageCount);
                ExtentReportManager.info("News image count: " + newsImageCount);

                softAssert.assertEquals(newsImageCount,8,
                        "VP 9: Expected 8 news images but found "+ newsImageCount);


                // VP 10: Verify all news images are loaded correctly
                LOG.info("VP 10: Verify all news images are loaded correctly");
                ExtentReportManager.info(
                                "VP 10: Verify all news images are loaded correctly");

                softAssert.assertTrue(newsPage.areNewsImagesLoaded(),
                                "VP 10: One or more news images are broken");

                softAssert.assertAll();
        }

        @Test(priority = 3, groups = "news")
        public void TC_NEW_03_CheckClickReviewTab() {
        
                // Step 1: Navigate to News section
                LOG.info("Step 1: Navigate to News section");
                ExtentReportManager.info("Step 1: Navigate to News section");

                newsPage.getTopNavigation().clickOnNews();
                newsPage.scrollToNewsSection();

                // Step 2: Click tab Review
                LOG.info("Step 2: Click tab Review");
                ExtentReportManager.info("Step 2: Click tab Review");

                newsPage.clickNewsTab("Review");

                //Step 3: Click "Xem thêm" button
                LOG.info("Step 3: Click \"Xem thêm\" button");
                ExtentReportManager.info("Step 3: Click \"Xem thêm\" button");
                newsPage.clickCollapseButton();
                newsPage.waitForNewsArticlesLoaded();

                // VP 1: Verify Review tab is selected
                LOG.info("VP 1: Verify Review tab is selected");
                ExtentReportManager.info("VP 1: Verify Review tab is selected");

                softAssert.assertTrue(newsPage.isNewsTabSelected("Review"),
                        "VP 1: Tab Review chưa được kích hoạt");

                // VP 2: Verify previous news list is hidden
                LOG.info("VP 2: Verify previous news list is hidden");
                ExtentReportManager.info("VP 2: Verify previous news list is hidden");

                softAssert.assertFalse(newsPage.isNewsTabPanelDisplayed("0"),
                        "VP 2: Panel Điện Ảnh 24h vẫn đang hiển thị"
                );

                // VP 3: Verify Review panel is displayed
                LOG.info("VP 3: Verify Review panel is displayed");
                ExtentReportManager.info("VP 3: Verify Review panel is displayed");

                softAssert.assertTrue(newsPage.isNewsTabPanelDisplayed("1"),
                        "VP 3: Panel Review không hiển thị");

                softAssert.assertAll();
        }

        @Test(priority = 4, groups = "news")
        public void TC_NEW_004_CheckClickPromotionTab() {

                // Step 1: Navigate to News section
                LOG.info("Step 1: Navigate to News section");
                ExtentReportManager.info("Step 1: Navigate to News section");

                newsPage.getTopNavigation().clickOnNews();
                newsPage.scrollToNewsSection();

                // Step 2: Click tab Khuyến mãi
                LOG.info("Step 2: Click tab Khuyến mãi");
                ExtentReportManager.info("Step 2: Click tab Khuyến mãi");

                newsPage.clickNewsTab("Khuyến mãi");

                
                //Step 3: Click "Xem thêm" button
                LOG.info("Step 3: Click \"Xem thêm\" button");
                ExtentReportManager.info("Step 3: Click \"Xem thêm\" button");

                newsPage.clickCollapseButton();
                newsPage.waitForNewsArticlesLoaded();

                //VP 1: Verify Khuyến mãi tab is selected
                LOG.info("VP 1: Verify Khuyến mãi tab is selected");
                ExtentReportManager.info("VP 1: Verify Khuyến mãi tab is selected");

                softAssert.assertTrue(newsPage.isNewsTabSelected("Khuyến mãi"),
                        "VP 1: Tab Khuyến mãi chưa được kích hoạt");
                
                //VP 2: Verify previous news list is hidden
                LOG.info("VP 2: Verify previous news list is hidden");
                ExtentReportManager.info("VP 2: Verify previous news list is hidden");

                softAssert.assertFalse(newsPage.isNewsTabPanelDisplayed("0"),
                        "VP 2: Panel Điện Ảnh 24h vẫn đang hiển thị");

                //VP 3: Verify promotion panel is displayed
                LOG.info("VP 3: Verify promotion panel is displayed");
                ExtentReportManager.info("VP 3: Verify promotion panel is displayed");

                softAssert.assertTrue(newsPage.isNewsTabPanelDisplayed("2"),
                        "VP 3: Panel Khuyến mãi không hiển thị");

                softAssert.assertAll();
        }

        @Test(priority = 5, groups = "news")
        public void TC_NEW_05_CheckClickLargeNews() {

                // Step 1: Navigate to News section
                LOG.info("Step 1: Navigate to News section");
                ExtentReportManager.info("Step 1: Navigate to News section");

                newsPage.getTopNavigation().clickOnNews();
                newsPage.scrollToNewsSection();
                
                //Step 2: Click "Xem thêm" button
                LOG.info("Step 2: Click \"Xem thêm\" button");
                ExtentReportManager.info("Step 2: Click \"Xem thêm\" button");

                newsPage.clickCollapseButton();
                newsPage.waitForNewsArticlesLoaded();

                //Step 3: Click first large news article
                LOG.info("Step 3: Click first large news article");

                String expectedUrl = newsPage.getLargeNewsUrl(1);
                ExtentReportManager.info("Step 3: Click first large news article");
                newsPage.clickLargeNewsTitle(1);
                newsPage.switchToNewTab();

                // VP 1: Verify URL navigation
                LOG.info("VP 1: Verify URL navigation");
                ExtentReportManager.info("VP 1: Verify URL navigation");
                String actualUrl = driver.getCurrentUrl();

                LOG.info("Actual URL: " + actualUrl);
                ExtentReportManager.info("Actual URL: " + actualUrl);

                softAssert.assertEquals(actualUrl,expectedUrl,
                        "VP 1: URL tab mới không đúng với bài viết đã chọn");

                // VP 2: Verify article detail page is loaded
                LOG.info("VP 2: Verify article detail page is loaded");
                ExtentReportManager.info("VP 2: Verify article detail page is loaded");

                boolean articleLoaded = newsPage.isArticlePageLoaded();
                softAssert.assertTrue(articleLoaded,
                        "VP 2: URL đã mở nhưng trang chi tiết không tải được nội dung bài viết");

                softAssert.assertAll();
        }

        @Test(priority = 6, groups = "news")
        public void TC_NEW_06_CheckClickMediumNews() {

                // Step 1: Navigate to News section
                LOG.info("Step 1: Navigate to News section");
                ExtentReportManager.info("Step 1: Navigate to News section");

                newsPage.getTopNavigation().clickOnNews();
                newsPage.scrollToNewsSection();
                
                //Step 2: Click "Xem thêm" button
                LOG.info("Step 2: Click \"Xem thêm\" button");
                ExtentReportManager.info("Step 2: Click \"Xem thêm\" button");

                newsPage.clickCollapseButton();
                newsPage.waitForNewsArticlesLoaded();

                //Step 3: Click first medium news article
                LOG.info("Step 3: Click first medium news article");
                ExtentReportManager.info("Step 3: Click first medium news article");

                String expectedUrl = newsPage.getMediumNewsUrl(1);
                newsPage.clickMediumNewsTitle(1);
                newsPage.switchToNewTab();

                // VP 1: Verify URL navigation
                LOG.info("VP 1: Verify URL navigation");
                ExtentReportManager.info("VP 1: Verify URL navigation");
                String actualUrl = driver.getCurrentUrl();

                LOG.info("Actual URL: " + actualUrl);
                ExtentReportManager.info("Actual URL: " + actualUrl);

                softAssert.assertEquals(actualUrl,expectedUrl,
                        "VP 1: URL tab mới không đúng với bài viết đã chọn");

                // VP 2: Verify article detail page is loaded
                LOG.info("VP 2: Verify article detail page is loaded");
                ExtentReportManager.info("VP 2: Verify article detail page is loaded");

                boolean articleLoaded = newsPage.isArticlePageLoaded();
                softAssert.assertTrue(articleLoaded,
                        "VP 2: URL đã mở nhưng trang chi tiết không tải được nội dung bài viết");

                softAssert.assertAll();
        }

        @Test(priority = 7, groups = "news")
        public void TC_NEW_07_CheckClickSmallNews() {

                // Step 1: Navigate to News section
                LOG.info("Step 1: Navigate to News section");
                ExtentReportManager.info("Step 1: Navigate to News section");

                newsPage.getTopNavigation().clickOnNews();
                newsPage.scrollToNewsSection();
                
                //Step 2: Click "Xem thêm" button
                LOG.info("Step 2: Click \"Xem thêm\" button");
                ExtentReportManager.info("Step 2: Click \"Xem thêm\" button");

                newsPage.clickCollapseButton();
                newsPage.waitForNewsArticlesLoaded();

                //Step 3: Click first small news article
                LOG.info("Step 3: Click first small news article");
                ExtentReportManager.info("Step 3: Click first small news article");

                String expectedUrl = newsPage.getSmallNewsUrl(1);
                newsPage.clickSmallNewsTitle(1);
                newsPage.switchToNewTab();

                // VP 1: Verify URL navigation
                LOG.info("VP 1: Verify URL navigation");
                ExtentReportManager.info("VP 1: Verify URL navigation");
                String actualUrl = driver.getCurrentUrl();

                LOG.info("Actual URL: " + actualUrl);
                ExtentReportManager.info("Actual URL: " + actualUrl);

                softAssert.assertEquals(actualUrl,expectedUrl,
                        "VP 1: URL tab mới không đúng với bài viết đã chọn");

                // VP 2: Verify article detail page is loaded
                LOG.info("VP 2: Verify article detail page is loaded");
                ExtentReportManager.info("VP 2: Verify article detail page is loaded");

                boolean articleLoaded = newsPage.isArticlePageLoaded();
                softAssert.assertTrue(articleLoaded,
                        "VP 2: URL đã mở nhưng trang chi tiết không tải được nội dung bài viết");

                softAssert.assertAll();
        }

        @Test(priority = 8, groups = "news")
        public void TC_NEW_08_CheckExpandButtonDisplayed() {

                //Step 1: Scroll to bottom of News section
                LOG.info("Step 1: Scroll to bottom of News section");
                ExtentReportManager.info("Step 1: Scroll to bottom of News section");

                newsPage.scrollToCollapseButton();

                //VP 1: Verify XEM THÊM / RÚT GỌN button is displayed
                LOG.info("VP 1: Verify XEM THÊM / RÚT GỌN button is displayed");
                ExtentReportManager.info("VP 1: Verify XEM THÊM / RÚT GỌN button is displayed");

                softAssert.assertTrue(newsPage.isCollapseButtonDisplayed(),
                        "VP 1: Không hiển thị nút XEM THÊM / RÚT GỌN");

                softAssert.assertAll();
        }

        @Test(priority = 9, groups = "news")
        public void TC_NEW_009_CheckExpandNews() {

                //Step 1: Scroll to bottom of News section
                LOG.info("Step 1: Scroll to bottom of News section");
                ExtentReportManager.info("Step 1: Scroll to bottom of News section");

                newsPage.scrollToCollapseButton();

                //VP 1: Verify initial news list is empty
                LOG.info("VP 1: Verify initial news list is empty");
                ExtentReportManager.info("VP 1: Verify initial news list is empty");

                softAssert.assertEquals(newsPage.getVisibleNewsImageCount(),0,
                        "VP 1: Ban đầu vẫn có hình ảnh bài viết");

                //Step 2: Click XEM THÊM
                LOG.info("Step 2: Click XEM THÊM");
                ExtentReportManager.info("Step 2: Click XEM THÊM");

                softAssert.assertEquals(newsPage.getCollapseButtonText(),
                        "XEM THÊM","VP 2: Không tìm thấy nút XEM THÊM");

                newsPage.clickCollapseButton();
                newsPage.waitForNewsArticlesLoaded();

                LOG.info("VP 3: Verify news images are displayed");
                ExtentReportManager.info("VP 3: Verify news images are displayed");

                softAssert.assertTrue(
                        newsPage.waitForNewsImagesDisplayed(),
                        "VP 3: Không có hình ảnh bài viết xuất hiện sau khi nhấn XEM THÊM"
                );

                softAssert.assertAll();
        }

        @Test(priority = 10, groups = "news")
        public void TC_NEW_010_CheckCollapseNews() {

                // Step 1: Scroll to bottom of News section
                LOG.info("Step 1: Scroll to bottom of News section");
                ExtentReportManager.info("Step 1: Scroll to bottom of News section");

                newsPage.scrollToCollapseButton();

                // Step 2: Click XEM THÊM
                LOG.info("Step 2: Click XEM THÊM");
                ExtentReportManager.info("Step 2: Click XEM THÊM");

                newsPage.clickCollapseButton();
                newsPage.waitForNewsArticlesLoaded();

                // Ghi nhận số ảnh khi danh sách đang mở rộng
                int expandedImageCount = newsPage.getVisibleNewsImageCount();

                LOG.info("Expanded image count: " + expandedImageCount);
                ExtentReportManager.info("Expanded image count: " + expandedImageCount);

                softAssert.assertTrue(
                        expandedImageCount > 0,
                        "Không có bài viết nào hiển thị sau khi nhấn XEM THÊM"
                );

                // Step 3: Verify button changes to RÚT GỌN
                LOG.info("Step 3: Verify button RÚT GỌN");
                ExtentReportManager.info("Step 3: Verify button RÚT GỌN");

                softAssert.assertEquals(
                        newsPage.getCollapseButtonText(),
                        "RÚT GỌN",
                        "Nút chưa chuyển sang RÚT GỌN"
                );

                // Step 4: Click RÚT GỌN
                LOG.info("Step 4: Click RÚT GỌN");
                ExtentReportManager.info("Step 4: Click RÚT GỌN");

                newsPage.clickCollapseButton();

                // VP 1: Verify the news list is collapsed
                LOG.info("VP 1: Verify news list is collapsed");
                ExtentReportManager.info("VP 1: Verify news list is collapsed");

                boolean isCollapsed =
                        newsPage.waitForNewsImageCountLessThan(expandedImageCount);

                softAssert.assertTrue(
                        isCollapsed,
                        "VP 1: Số ảnh hiển thị không giảm sau khi nhấn RÚT GỌN"
                );

                // VP 2: Verify the button changes back to XEM THÊM
                LOG.info("VP 2: Verify button changes to XEM THÊM");
                ExtentReportManager.info("VP 2: Verify button changes to XEM THÊM");

                softAssert.assertEquals(
                        newsPage.getCollapseButtonText(),
                        "XEM THÊM",
                        "VP 2: Nút chưa chuyển lại thành XEM THÊM"
                );

                // VP 3: Verify News section still exists
                LOG.info("VP 3: Verify News section still exists");
                ExtentReportManager.info("VP 3: Verify News section still exists");

                softAssert.assertTrue(
                        newsPage.isNewsSectionDisplayed(),
                        "VP 3: Khối Tin Tức bị mất sau khi RÚT GỌN"
                );

                softAssert.assertAll();
        }


        @Test(priority = 11, groups = "news")
        public void TC_NEW_011_CheckExpandCollapseMobile() {

                //Step 1: Set mobile viewport
                LOG.info("Step 1: Set mobile viewport");
                ExtentReportManager.info("Step 1: Set mobile viewport");

                newsPage.switchToPhoneEdge();
                newsPage.scrollToNewsSection();

                //VP 1: Verify expand/collapse button is displayed
                LOG.info("VP 1: Verify expand/collapse button is displayed");
                ExtentReportManager.info("VP 1: Verify expand/collapse button is displayed");

                softAssert.assertTrue(newsPage.isCollapseButtonDisplayed(),
                        "VP 1: Không hiển thị nút XEM THÊM / RÚT GỌN trên viewport mobile");

                //VP 2: Verify button is enabled
                LOG.info("VP 2: Verify button is enabled");
                ExtentReportManager.info("VP 2: Verify button is enabled");

                softAssert.assertTrue(driver.findElement(data.locator.NewsLocator.byCollapseButton).isEnabled(),
                        "VP 2: Nút XEM THÊM / RÚT GỌN không thể tương tác trên mobile");

                softAssert.assertAll();

                // Khôi phục kích thước trình duyệt
                driver.manage().window().maximize();
        }

        @Test(priority = 12, groups = "news")
        public void TC_NEW_012_CheckExpandCollapseWhenSwitchingTabs() {

                // Step 1: Open News section
                LOG.info("Step 1: Open News section");
                ExtentReportManager.info("Step 1: Open News section");

                newsPage.getTopNavigation().clickOnNews();
                newsPage.scrollToNewsSection();

                // Step 2: Expand Điện Ảnh 24h
                LOG.info("Step 2: Expand Điện Ảnh 24h");
                ExtentReportManager.info("Step 2: Expand Điện Ảnh 24h");

                if ("XEM THÊM".equals(newsPage.getCollapseButtonText())) {
                        newsPage.clickCollapseButton();
                        newsPage.waitForNewsArticlesLoaded();
                }

                softAssert.assertEquals(
                        newsPage.getCollapseButtonText(),
                        "RÚT GỌN",
                        "Step 2: Nút chưa chuyển thành RÚT GỌN"
                );

                // Step 3: Switch to Review
                LOG.info("Step 3: Switch to Review");
                ExtentReportManager.info("Step 3: Switch to Review");

                newsPage.clickNewsTab("Review");
                newsPage.logNewsTabDebugInfo();
                
                newsPage.clickCollapseButton(); // RÚT GỌN
                newsPage.clickCollapseButton(); // XEM THÊM
                newsPage.logNewsTabDebugInfo();

                softAssert.assertTrue(
                        newsPage.isNewsTabSelected("Review"),
                        "VP 1: Tab Review chưa được kích hoạt"
                );

                boolean reviewDisplayed = newsPage.waitForActiveTabNewsDisplayed("Review");

                LOG.info("VP 1: Review news displayed = " + reviewDisplayed);
                ExtentReportManager.info("VP 1: Review news displayed = " + reviewDisplayed);

                softAssert.assertTrue(
                        reviewDisplayed,
                        "VP 1: Danh sách bài viết Review không hiển thị sau khi chuyển tab"
                );

                // Step 4: Switch to Khuyến mãi
                LOG.info("Step 4: Switch to Khuyến mãi");
                ExtentReportManager.info("Step 4: Switch to Khuyến mãi");

                newsPage.clickNewsTab("Khuyến mãi");
                newsPage.logNewsTabDebugInfo();

                softAssert.assertTrue(
                        newsPage.isNewsTabSelected("Khuyến mãi"),
                        "VP 2: Tab Khuyến mãi chưa được kích hoạt"
                );

                // VP 2: Verify promotion content or empty message
                LOG.info("VP 2: Verify promotion content or empty message");
                ExtentReportManager.info("VP 2: Verify promotion content or empty message");

                softAssert.assertTrue(
                        newsPage.isPromotionContentValid(),
                        "VP 2: Tab Khuyến mãi không hiển thị bài viết hoặc thông báo chưa có khuyến mãi"
                );

                softAssert.assertAll();
        }
}