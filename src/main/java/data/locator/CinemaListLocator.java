package data.locator;

import org.openqa.selenium.By;

public class CinemaListLocator {

        public static final String SHOWTIME_XPATH = "(//div[contains(@id, 'vertical-tabpanel-0')])[2]";

        // Cinema system locators
        public static final String BTN_CGV_XPATH = "((//div[@role='tablist'])[1]//button[@tabindex='-1'])[1]";

        public static final String BTN_BHD_XPATH = "(//div[@role='tablist'])[1]//button[@tabindex='0']";

        public static final String BTN_MEGAGS_XPATH = "((//div[@role='tablist'])[1]//button[@tabindex='-1'])[2]";

        public static final String BTN_SHOWTIME_XPATH = 
                "//div[@id='vertical-tabpanel-0']" 
                + "//div[h2[contains(normalize-space(.), '%s')]]"
                + "//a[.//p[normalize-space(.)='%s']"
                + " and .//h3[normalize-space(.)='%s']]";

        public static final String SHOWTIME_DATE_XPATH = "//a[contains(@href, '/purchase/')][1]//p[1]";

        public static final String SHOWTIME_TIME_XPATH = "//a[contains(@href, '/purchase/')][1]//h3";

        public static final String CINEMALIST_COLUMN_XPATH = "(//div[@id='vertical-tabpanel-0'])[2]";

        public static final By byShowtime = By.xpath(CinemaListLocator.SHOWTIME_XPATH);
        public static final By byBtnCGV = By.xpath(CinemaListLocator.BTN_CGV_XPATH);
        public static final By byBtnBHD = By.xpath(CinemaListLocator.BTN_BHD_XPATH);
        public static final By byBtnMegaGS = By.xpath(CinemaListLocator.BTN_MEGAGS_XPATH);
        public static final By byShowtimeDate = By.xpath(CinemaListLocator.SHOWTIME_DATE_XPATH);
        public static final By byShowtimeTime = By.xpath(CinemaListLocator.SHOWTIME_TIME_XPATH);
        public static final By byCinemaListColumn = By.xpath(CinemaListLocator.CINEMALIST_COLUMN_XPATH);
}