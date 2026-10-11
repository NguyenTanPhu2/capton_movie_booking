package data.locator;

import org.openqa.selenium.By;

public class NewsLocator {

    public static final String NEWS_SECTION_XPATH = "//div[@id='tinTuc']";
    public static final String NEWS_TABPANEL_XPATH = "//div[contains(@id, 'simple-tabpanel')]";
    public static final String NEWS_TAB_XPATH = NEWS_SECTION_XPATH + "//button[contains(normalize-space(.), '%s')]";
    public static final String ACTIVE_NEWS_TAB_XPATH = NEWS_SECTION_XPATH + "//button[@role='tab' and @aria-selected='true'" + " and contains(normalize-space(.), '%s')]";
    public static final String NEWS_TAB_PANEL_XPATH = NEWS_SECTION_XPATH + "//div[@role='tabpanel' and @id='simple-tabpanel-%s']";

    // Large news articles xpath
    public static final String LARGE_NEWS_ARTICLES_XPATH = NEWS_TABPANEL_XPATH + "//div[contains(@class, 'md-6')][./a]";
    public static final String LARGE_NEWS_ARTICLES_TITLE_XPATH = NEWS_TABPANEL_XPATH + "//div[contains(@class, 'md-6')]/a";
    public static final String LARGE_NEWS_ARTICLES_DESCRIPTION_XPATH = NEWS_TABPANEL_XPATH + "//div[contains(@class, 'md-6')]/p";

    // medium news articles xpath
    public static final String MEDIUM_NEWS_ARTICLES_XPATH = "//div[contains(@class, 'md-4')][./a and ./p]";
    public static final String MEDIUM_NEWS_ARTICLES_TITLE_XPATH = NEWS_TABPANEL_XPATH + "//div[contains(@class, 'md-4')]/a";
    public static final String MEDIUM_NEWS_ARTICLES_DESCRIPTION_XPATH = NEWS_TABPANEL_XPATH + "//div[contains(@class, 'md-4')]/p";

    // small news articles xpath
    public static final String SMALL_NEWS_ARTICLES_XPATH = "//div[contains(@class, 'md-4')]/div[contains(@class, 'xs-12')]";
    public static final String SMALL_NEWS_ARTICLES_TITLE_XPATH = SMALL_NEWS_ARTICLES_XPATH + "/a";


    // News section
    public static final By byNewsSection = By.xpath(NEWS_SECTION_XPATH);

    // News tabs
    public static final By byNewsTabList =
            By.xpath(NEWS_SECTION_XPATH + "//div[@role='tablist']");

    // News tab indicator
    public static final By byNewsTabIndicator =
            By.xpath(NEWS_SECTION_XPATH + "//span[contains(@class, 'MuiTabs-indicator')]");

    // Large news articles locator
    public static final By byLargeNewsArticles = By.xpath(LARGE_NEWS_ARTICLES_XPATH);
    public static final By byLargeNewsArticlesTitle = By.xpath(LARGE_NEWS_ARTICLES_TITLE_XPATH);
    public static final By byLargeNewsArticleDescriptions = By.xpath(LARGE_NEWS_ARTICLES_DESCRIPTION_XPATH);

    // Medium news articles
    public static final By byMediumNewsArticles = By.xpath(MEDIUM_NEWS_ARTICLES_XPATH);
    public static final By byMediumNewsArticlesTitle = By.xpath(MEDIUM_NEWS_ARTICLES_TITLE_XPATH);
    public static final By byMediumNewsArticlesDescriptions = By.xpath(MEDIUM_NEWS_ARTICLES_DESCRIPTION_XPATH);

    // Small news articles
    public static final By bySmallNewsArticles = By.xpath(SMALL_NEWS_ARTICLES_XPATH);
    public static final By bySmallNewsArticlesTitle = By.xpath(SMALL_NEWS_ARTICLES_TITLE_XPATH);

    // News article images
    public static final By byNewsImages = By.xpath(NEWS_SECTION_XPATH+ "//img[@alt='poster']");

    public static final By byVisibleNewsImages = By.xpath(
    NEWS_SECTION_XPATH + "//div[@role='tabpanel' and not(@hidden)]//img[@alt='poster']");

    public static final By byVisibleNewsPanel = 
        By.xpath( NEWS_SECTION_XPATH + "//div[@role='tabpanel' and not(@hidden)]");

    // Large and medium article descriptions
    public static final By byMediumNewsArticleDescriptions =
            By.xpath( NEWS_TABPANEL_XPATH + "//div[contains(@class, 'md-4')]/p");

    // Small article titles
    public static final By bySmallNewsTitles =
            By.xpath(
                    NEWS_SECTION_XPATH
                            + "//div[contains(@class, 'MuiGrid-item')]"
                            + "//p[not(following-sibling::p)]"
            );

    // News article links
    public static final By byNewsArticleLinks =
            By.xpath(
                    NEWS_SECTION_XPATH
                            + "//a[@target='_blank' and @rel='noreferrer']"
            );

    // Collapse button
    public static final By byCollapseButton =
            By.xpath(
                    NEWS_SECTION_XPATH
                            + "//button[contains(@class, 'MuiButton-outlined')]"  //normalize-space()='RÚT GỌN'
            );

    
}