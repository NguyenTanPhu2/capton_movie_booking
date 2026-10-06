package data.locator;

import org.openqa.selenium.By;

public class MovieDetailLocator {

    public static final String MOVIE_CARDS_XPATH =
        "//a[contains(@href, '/detail/')][.//h4]";
    

    public static final String AGE_RATINGS_XPATH =
        "//span[normalize-space()='C18']";

    public static final String TITLES_XPATH =
        "//div[span[normalize-space()='C18']]";

    public static final String MOVIE_NAME_XPATH =
        "//a[contains(@href, '/detail/')][contains(normalize-space(.), '%s')]";

    public static final String MOVIE_AGE_RATINGS_XPATH =
        MOVIE_CARDS_XPATH + AGE_RATINGS_XPATH;

    public static final String MOVIE_TITLES_XPATH =
        MOVIE_CARDS_XPATH + TITLES_XPATH; 

    public static final String MOVIE_DESCRIPTIONS_XPATH =
        MOVIE_CARDS_XPATH + "//h4";

    public static final String MOVIE_POSTERS_XPATH =
        MOVIE_CARDS_XPATH + "//div[contains(@style, 'background-image')]";

    public static final String TRAILER_BUTTONS_XPATH =
        MOVIE_CARDS_XPATH + "//img[@alt='video-button']";

    public static final String BUY_TICKET_BUTTONS_XPATH =
        MOVIE_CARDS_XPATH + "//a[normalize-space()='MUA VÉ']";

    public static final String PAGINATION_BUTTONS_XPATH =
        "//div[@id='lichChieu']//div[count(./button)=3 and not(.//div[count(./button)=3])]/button";

    public static final String ACTIVE_BUTTON_PAGINATION_XPATH =
        "//div[@id='lichChieu']//button[contains(@style, 'color: rgb(251, 66, 38);')]";

    public final static String MOVIE_DETAIL_POSTER =
   "//div[contains(@style, 'background-image')]";

    public final static String MOVIE_DETAIL_TITLE =
       "//h1";

    public final static String MOVIE_DETAIL_DURATION =
       "//h5[contains(normalize-space(), 'phút')]";

    public final static String MOVIE_DETAIL_RELEASE_DATE =
       "//h4";

    public final static String MOVIE_DETAIL_BUY_TICKET =
       "//a[normalize-space()='Mua vé']";

    public final static String MOVIE_DETAIL_RATING =
       "//span[@role='img' and contains(@aria-label, 'Stars')]";

    public final static String MOVIE_DETAIL_CINEMA_LIST_ID = "cinemaList";

    public final static String MOVIE_DETAIL_TRAILER = "//div[button[.//img[@alt='video-button']]]";

    public final static String MOVIE_DETAIL_TRAILER_BUTTON = "//button[.//img[@alt='video-button']]";

    public final static String MOVIE_DETAIL_TRAILER_PLAYER = "div.modal-video iframe[src*='youtube.com/embed']";

    public static final String MOVIE_DETAIL_TRAILER_TITLE = "//a[contains(@class,'ytmVideoInfoVideoTitle')]//span";
    
    public final static String MOVIE_DETAIL_TRAILER_CLOSE_BUTTON = "//button[@class='modal-video-close-btn']";

    public final static String MOVIE_DETAIL_CINEMA_SYSTEM_BUTTON = "//button[.//img[contains(translate(@alt, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), translate('%s', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))]]";

    public final static String MOVIE_DETAIL_CINEMA_NAME = "//div[contains(@id, 'vertical-tabpanel')]//h3[contains(., '%s')]";

    public static final String MOIVE_DETAIL_RELEASE_DATE = "//h1[normalize-space()='%s']/preceding::h4[1]";

    public static final String MOVIE_DETAIL_SHOWTIME_BUTTON = "//a[.//p[normalize-space()='%s'] and .//p[normalize-space()='%s']]";




    public static final By byMovieCards = By.xpath(MOVIE_CARDS_XPATH);
    public static final By byMovieAgeRatings = By.xpath(MOVIE_AGE_RATINGS_XPATH);
    public static final By byMovieTitles = By.xpath(MOVIE_TITLES_XPATH);
    public static final By byMovieDescriptions = By.xpath(MOVIE_DESCRIPTIONS_XPATH);
    public static final By byMoviePosters = By.xpath(MOVIE_POSTERS_XPATH);
    public static final By byPaginationButtons = By.xpath(PAGINATION_BUTTONS_XPATH);
    public static final By byTrailerButtons = By.xpath(TRAILER_BUTTONS_XPATH);
    public static final By byBtnActivePagination = By.xpath(ACTIVE_BUTTON_PAGINATION_XPATH);
    public static final By byBuyTicketButtons = By.xpath(BUY_TICKET_BUTTONS_XPATH);
    public static final By byMovieDetailPoster = By.xpath(MOVIE_DETAIL_POSTER);
    public static final By byMovieDetailTitle = By.xpath(MOVIE_DETAIL_TITLE);
    public static final By byMovieDetailDuration = By.xpath(MOVIE_DETAIL_DURATION);
    public static final By byMovieDetailReleaseDate = By.xpath(MOVIE_DETAIL_RELEASE_DATE);
    public static final By byMovieDetailBuyTicket = By.xpath(MOVIE_DETAIL_BUY_TICKET);
    public static final By byMovieDetailRating = By.xpath(MOVIE_DETAIL_RATING);
    public static final By byCinemaList = By.id(MOVIE_DETAIL_CINEMA_LIST_ID);
    public static final By byMovieDetailTrailer = By.xpath(MOVIE_DETAIL_TRAILER);
    public static final By byMovieDetailTrailerTitle = By.xpath(MOVIE_DETAIL_TRAILER_TITLE);
    public static final By byMovieDetailTrailerButton = By.xpath(MOVIE_DETAIL_TRAILER_BUTTON);
    public static final By byTrailerPlayer = By.cssSelector(MOVIE_DETAIL_TRAILER_PLAYER);


    public static final By byBtnCloseTrailer = By.xpath(MOVIE_DETAIL_TRAILER_CLOSE_BUTTON);
}