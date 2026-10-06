package data;

import org.testng.annotations.DataProvider;

import java.util.UUID;

public class TestDataProvider {

    // =========================================================
    // COMMON LOGIN
    // =========================================================

    @DataProvider(name = "login-credentials")
    public static Object[][] loginCredentials() {
        return new Object[][]{
                {"Clara", "Clara@2026"}
        };
    }

    @DataProvider(name = "invalid-login-password")
    public static Object[][] invalidLoginPassword() {
        return new Object[][]{
                {"Clara", "123457"}
        };
    }

    @DataProvider(name = "invalid-login-account")
    public static Object[][] invalidLoginAccount() {
        return new Object[][]{
                {"John", "Clara@2026"}
        };
    }


    // =========================================================
    // REGISTER
    // =========================================================

    /**
     * account and email are generated together so they are unique
     * for every DataProvider invocation.
     */
    @DataProvider(name = "register-valid")
    public static Object[][] registerValid() {
        String account = UUID.randomUUID().toString();

        return new Object[][]{
                {
                        account,
                        "123456",
                        "John",
                        account + "@gmail.com"
                }
        };
    }

    @DataProvider(name = "register-invalid-email")
    public static Object[][] registerInvalidEmail() {
        String account = UUID.randomUUID().toString();

        return new Object[][]{
                {
                        account,
                        "123456",
                        "John",
                        "C lara@.gmail.com"
                }
        };
    }

    @DataProvider(name = "register-hide-password")
    public static Object[][] registerHidePassword() {
        String account = UUID.randomUUID().toString();

        return new Object[][]{
                {
                        account,
                        "123456",
                        "John",
                        account + "@gmail.com"
                }
        };
    }

    @DataProvider(name = "register-wrong-confirm-password")
    public static Object[][] registerWrongConfirmPassword() {
        String account = UUID.randomUUID().toString();

        return new Object[][]{
                {
                        account,
                        "123456",
                        "1234567",
                        "John"
                }
        };
    }

    @DataProvider(name = "register-existing-email")
    public static Object[][] registerExistingEmail() {
        String account = UUID.randomUUID().toString();

        return new Object[][]{
                {
                        account,
                        "123456",
                        "John",
                        "Clara@2026"
                }
        };
    }

    @DataProvider(name = "register-invalid-password")
    public static Object[][] registerInvalidPassword() {
        String account = UUID.randomUUID().toString();

        return new Object[][]{
                {
                        account,
                        "1234"
                }
        };
    }

    @DataProvider(name = "Whitespace-value")
    public static Object[][] withWhitespace() {
        String account = UUID.randomUUID().toString();

        return new Object[][]{
                {
                        account,
                        "1 2 3 4 56 ",
                        "Jo hn ",
                        "Cla ra@2026"
                }
        };
    }

    // =========================================================
    // MOVIE
    // =========================================================

    @DataProvider(name = "movie-list")
    public static Object[][] movieList() {
        return new Object[][]{
                {
                        new String[]{
                                "AVATAR 2",
                                "Äá»‹a Ä‘áº¡o",
                                "Man of Steel",
                                "John Cena WWE"
                        }
                }
        };
    }

    @DataProvider(name = "movie-age-rating")
    public static Object[][] movieAgeRating() {
        return new Object[][]{
                {
                        new String[]{
                                "AVATAR 2",
                                "Äá»‹a Ä‘áº¡o",
                                "Man of Steel",
                                "John Cena WWE"
                        },
                        "C18"
                }
        };
    }


    @DataProvider(name = "movie-pagination")
    public static Object[][] moviePagination() {
        return new Object[][]{
                {new int[]{1, 2, 3}}
        };
    }

    @DataProvider(name = "movie-detail")
    public static Object[][] movieDetail() {
        return new Object[][]{
                {
                        "Äá»‹a Ä‘áº¡o",
                        2
                }
        };
    }

    @DataProvider(name = "movie-duration-rating")
    public static Object[][] movieDurationRating() {
        return new Object[][]{
                {"Man of Steel", "120phÃºt"}
        };
    }

    @DataProvider(name = "movie-trailer")
    public static Object[][] movieTrailer() {
        return new Object[][]{
                {
                        new String[][]{
                                {"Man of Steel", "Man of Steel - Official Trailer #2 [HD]"},
                                {"John Cena WWE", "John Cena WWE - Official Trailer #2 [HD]"}
                        }
                }
        };
    }


    @DataProvider(name = "movie-search-result")
    public static Object[][] movieSearchResult() {
        return new Object[][]{
                {"Man of Steel"}
        };
    }

    // =========================================================
    // CINEMA
    // =========================================================

    @DataProvider(name = "cinema-complex")
    public static Object[][] cinemaComplex() {
        return new Object[][]{
                {
                        new String[]{
                                "cgv",
                                "bhd-star-cineplex",
                                "lotte-cinema"
                        }
                }
        };
    }

    @DataProvider(name = "cinema-system-list")
    public static Object[][] cinemaSystemList() {
        return new Object[][]{
                {"cgv", "CGV - Pandora City"}
        };
    }

    @DataProvider(name = "cinema-complex-system-switching")
    public static Object[][] cinemaComplexSystemSwitching() {

        return new Object[][]{
                {
                        new String[][]{
                                {"cgv", "CGV - Pandora City"},
                                {"bhd-star-cineplex", "BHD Star Cineplex - Pháº¡m HÃ¹ng"},
                                {"lotte-cinema", "Lotte - PhÃº Thá»"}
                        }
                }
        };
    }

    @DataProvider(name = "cinema-name-address")
    public static Object[][] cinemaNameAddress() {
        return new Object[][]{
                {"lotte-cinema", "Lotte - PhÃº Thá»", "L4-Lotte Mart PhÃº Thá», Q.11"}
        };
    }

    @DataProvider(name = "cinema-movie")
    public static Object[][] cinemaMovie() {
        return new Object[][]{
                {"cgv", "CGV - Aeon TÃ¢n PhÃº", "John Wick"}
        };
    }

    @DataProvider(name = "cinema-poster-schedule")
    public static Object[][] cinemaPosterSchedule() {
        return new Object[][]{
                {"galaxy-cinema", "GLX - Nguyá»…n VÄƒn QuÃ¡", "John Cena WWE", "12-10-2023"}
        };
    }

    @DataProvider(name = "cinema-available_schedule")
    public static Object[][] cinemaAvailableSchedule() {
        return new Object[][]{
                {"01-01-2019", "10:10"}
        };
    }

    @DataProvider(name = "cinema-schedule-buttons-displayed")
    public static Object[][] cinemaScheduleButtonsDisplayed() {
        return new Object[][]{
                {"01-01-2019"}
        };
    }

    @DataProvider(name = "cinema-sold_out_schedule")
    public static Object[][] cinemaSoldOutSchedule() {
        return new Object[][]{
                {"cgv", "CGV - Golden Plaza", "The Gentlemen Dangbk", "07-12-2025", "06:00"}
        };
    }

    @DataProvider(name = "cinema-system-switching")
    public static Object[][] cinemaSystemSwitching() {

        return new Object[][]{
                {
                        new String[][]{
                                {"BHD Star Cineplex - 3/2", "10-12-2021"},
                                {"BHD Star Cineplex - Bitexco", "22-11-2021"},
                        }
                }
        };
    }

    @DataProvider(name = "cinema-show-time")
    public static Object[][] cinemaShowTime() {
        return new Object[][]{
                {/*"cgv", "CGV - Aeon TÃ¢n PhÃº", "John Wick", */"10:10"}
        };
    }


// =========================================================
// FILTER
// =========================================================

    @DataProvider(name = "filter-data")
    public static Object[][] filterData() {
        return new Object[][]{
                {
                        "AVATAR 2",
                        "BHD Star Cineplex - Vincom LÃª VÄƒn Viá»‡t",
                        "18/10/2021 ~ 10:25"
                }
        };
    }

    @DataProvider(name = "filter-movie")
    public static Object[][] filterMovie() {
        return new Object[][]{
                {"AVATAR 2"}
        };
    }

    @DataProvider(name = "filter-cinema")
    public static Object[][] filterCinema() {
        return new Object[][]{
                {
                        "AVATAR 2",
                        "BHD Star Cineplex - Vincom LÃª VÄƒn Viá»‡t",
                }
        };
    }


// =========================================================
// BOOKING
// =========================================================

    @DataProvider(name = "booking-seat-legend")
    public static Object[][] bookingSeatLegend() {
        return new Object[][]{
                {
                        "gÃ¡i giÃ  láº¯m chiÃªu",
                        "21-12-2021",
                }
        };
    }

    @DataProvider(name = "booking-information")
    public static Object[][] bookingInformation() {
        return new Object[][]{
                {
                        "gÃ¡i giÃ  láº¯m chiÃªu",
                        "21-12-2021",
                        146,
                        "CGV - Vincom GÃ² Váº¥p"
                }
        };
    }

    @DataProvider(name = "booking-total-price")
    public static Object[][] bookingTotalPrice() {
        return new Object[][]{
                {
                        "gÃ¡i giÃ  láº¯m chiÃªu",
                        "21-12-2021",
                        "0VND"
                }
        };
    }

    @DataProvider(name = "booking-regular-seat")
    public static Object[][] bookingRegularSeat() {
        return new Object[][]{
                {
                        "gÃ¡i giÃ  láº¯m chiÃªu",
                        "21-12-2021",
                        146,
                        "Gháº¿ 146,",
                        "75000VND"
                }
        };
    }

    @DataProvider(name = "booking-vip-seat")
    public static Object[][] bookingVipSeat() {
        return new Object[][]{
                {
                        "gÃ¡i giÃ  láº¯m chiÃªu",
                        "21-12-2021",
                        52,
                        "Gháº¿ 52,",
                        "90000VND"
                }
        };
    }

    @DataProvider(name = "booking-delete-seat")
    public static Object[][] bookingDeleteSeat() {
        return new Object[][]{
                {
                        "gÃ¡i giÃ  láº¯m chiÃªu",
                        "21-12-2021",
                        81,
                        "",
                        "0VND"
                }
        };
    }

    @DataProvider(name = "booking-sold-seat")
    public static Object[][] bookingSoldSeat() {
        return new Object[][]{
                {
                        "gÃ¡i giÃ  láº¯m chiÃªu",
                        "21-12-2021",
                        "X"
                }
        };
    }

    @DataProvider(name = "booking-seat-status-after-booking")
    public static Object[][] bookingStatusSeat() {
        return new Object[][]{
                {
                        "Clara",
                        "Clara@2026",
                        "gÃ¡i giÃ  láº¯m chiÃªu",
                        "21-12-2021",
                        "X",
                        "14"
                }
        };
    }

    @DataProvider(name = "booking-history")
    public static Object[][] bookingHistory() {
        return new Object[][]{
                {
                        "Clara",
                        "Clara@2026"
                }
        };
    }

    @DataProvider(name = "booking-without-seat")
    public static Object[][] bookingWithoutSeat() {
        return new Object[][]{
                {
                        "Clara",
                        "Clara@2026",
                        "gÃ¡i giÃ  láº¯m chiÃªu",
                        "21-12-2021"
                }
        };
    }

    @DataProvider(name = "booking-valid-seat")
    public static Object[][] bookingValidSeat() {
        return new Object[][]{
                {
                        "Clara",
                        "Clara@2026",
                        "gÃ¡i giÃ  láº¯m chiÃªu",
                        "21-12-2021",
                        "15"
                }
        };
    }

    @DataProvider(name = "booking-without-login")
    public static Object[][] bookingWithoutLogin() {
        return new Object[][]{
                {
                        "gÃ¡i giÃ  láº¯m chiÃªu",
                        "21-12-2021",
                        146
                }
        };
    }

    @DataProvider(name = "booking-multiple-regular-seats")
    public static Object[][] bookingMultipleRegularSeats() {
        return new Object[][]{
                {
                        "gÃ¡i giÃ  láº¯m chiÃªu",
                        "21-12-2021",
                        17,
                        18,
                        19,
                        "Gháº¿ 17, Gháº¿ 18, Gháº¿ 19,",
                        "225000VND"
                }
        };
    }
    @DataProvider(name = "booking-regular-and-vip-seat")
    public static Object[][] bookingRegularAndVipSeat() {
        return new Object[][]{
                {
                        "gÃ¡i giÃ  láº¯m chiÃªu",
                        "21-12-2021",
                        17,
                        36,
                        "Gháº¿ 17, Gháº¿ 36,",
                        "165000VND"
                }
        };
    }
    @DataProvider(name = "booking-remove-one-seat")
    public static Object[][] bookingRemoveOneSeat() {
        return new Object[][]{
                {
                        "gÃ¡i giÃ  láº¯m chiÃªu",
                        "21-12-2021",
                        17,
                        18,
                        19,
                        "Gháº¿ 17, Gháº¿ 19,",
                        "150000VND"
                }
        };
    }
    @DataProvider(name = "booking-refresh-page")
    public static Object[][] bookingRefreshPage() {
        return new Object[][]{
                {
                        "gÃ¡i giÃ  láº¯m chiÃªu",
                        "21-12-2021",
                        17
                }
        };
    }
    @DataProvider(name = "booking-multiple-seats-successfully")
    public static Object[][] bookingMultipleSeatsSuccessfully() {
        return new Object[][]{
                {
                        "Clara",
                        "Clara@2026",
                        "gÃ¡i giÃ  láº¯m chiÃªu",
                        "21-12-2021",
                        17,
                        18
                }
        };
    }
    @DataProvider(name = "booking-price-format")
    public static Object[][] bookingPriceFormat() {
        return new Object[][]{
                {
                        "NhÃ  BÃ  Ná»¯",
                        "03-11-2022",
                        18
                }
        };
    }


// =========================================================
// SEARCH
// =========================================================
    @DataProvider(name = "search-case-insensitive")
    public static Object[][] searchCaseInsensitive() {
        return new Object[][]{
                {"THE GENTLEMEN", "The Gentlemen Dangbk"}
        };
    }
    @DataProvider(name = "search-partial-name")
    public static Object[][] searchPartialName() {
        return new Object[][]{
                {"gentle", "The Gentlemen Dangbk"}
        };
    }
    @DataProvider(name = "search-special-characters")
    public static Object[][] searchSpecialCharacters() {
        return new Object[][]{
                {"@#$%^&*"}
        };
    }
    @DataProvider(name = "search-long-string")
    public static Object[][] searchLongString() {
        return new Object[][]{
                {"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"}
        };
    }
    @DataProvider(name = "search-trim-whitespace")
    public static Object[][] searchTrimWhitespace() {
        return new Object[][]{
                {" the gentlemen ", "The Gentlemen Dangbk"}
        };
    }
    @DataProvider(name = "search-by-name")
    public static Object[][] searchByName() {
        return new Object[][]{
                {"Man of Steel", "C18"}
        };
    }

    @DataProvider(name = "search-by-character")
    public static Object[][] searchByCharacter() {
        return new Object[][]{
                {new String[]{"Man of Steel", "AVATAR 2"}, "T", "C18"}
        };
    }

    @DataProvider(name = "search-non-existing")
    public static Object[][] searchNonExisting() {
        return new Object[][]{
                {"Ãc Ma"}
        };
    }

    @DataProvider(name = "search-select-result")
    public static Object[][] searchSelectResult() {
        return new Object[][]{
                {"Man of Steel"}
        };
    }


// =========================================================
// NEWS
// =========================================================

    @DataProvider(name = "news-tag")
    public static Object[][] newsTag() {
        return new Object[][]{
                {
                        new String[]{
                                "Äiá»‡n áº¢nh 24h",
                                "Review",
                                "Khuyáº¿n mÃ£i"
                        }
                }
        };
    }

    @DataProvider(name = "news-big-article")
    public static Object[][] newsBigArticle() {
        return new Object[][]{
                new String[][]{
                        {"TENET cÃ´ng bá»‘ ngÃ y khá»Ÿi chiáº¿u chÃ­nh thá»©c táº¡i Viá»‡t Nam", "https://tix.vn/goc-dien-anh/7943-tenet-cong-bo-ngay-khoi-chieu-chinh-thuc-tai-viet-nam"},
                        {"Khi phá»¥ ná»¯ khÃ´ng cÃ²n á»Ÿ tháº¿ trá»‘n cháº¡y cá»§a náº¡n nhÃ¢n", "https://tix.vn/goc-dien-anh/7941-khi-phu-nu-khong-con-o-the-tron-chay-cua-nan-nhan"}
                }
        };
    }

    @DataProvider(name = "news-small-article")
    public static Object[][] newsSmallArticle() {
        return new Object[][]{
                new String[][]{
                        {"Pee Nak 2 - Váº¡n kiáº¿p thiÃªn thu, Ä‘i tu khÃ´ng háº¿t nghiá»‡p!", "https://tix.vn/goc-dien-anh/7938-pee-nak-2-van-kiep-thien-thu-di-tu-khong-het-nghiep"},
                        {"Loáº¡t phim kinh dá»‹ khÃ´ng thá»ƒ bá» lá»¡ trong thÃ¡ng 7!", "https://tix.vn/goc-dien-anh/7937-loat-phim-kinh-di-khong-the-bo-lo-trong-thang-7"},
                        {"RÃ’M tung trailer hÃ© lá»™ cuá»™c sá»‘ng cá»§a dÃ¢n chÆ¡i sá»‘ Ä‘á»", "https://tix.vn/goc-dien-anh/7936-rom-tung-trailer-he-lo-cuoc-song-cua-dan-choi-so-de"},
                        {"Antebellum - Trailer cuá»‘i cÃ¹ng khÃ´ng hÃ© lá»™ báº¥t cá»© thÃ´ng tin gÃ¬ thÃªm", "https://tix.vn/goc-dien-anh/7935-antebellum-trailer-cuoi-cung-khong-he-lo-bat-cu-thong-tin-gi-them"}
                }
        };
    }

    @DataProvider(name = "news-medium-article")
    public static Object[][] newsMediumArticle() {
        return new Object[][]{
                new String[][]{
                        {"Gerard Butler cÃ¹ng bá»“ cÅ© Deadpool tham gia Greenland", "https://tix.vn/goc-dien-anh/7940-gerard-butler-cung-bo-cu-deadpool-tham-gia-greenland"},
                        {"Diá»…n viÃªn Ä‘áº·c biá»‡t cá»§a Báº±ng Chá»©ng VÃ´ HÃ¬nh", "https://tix.vn/goc-dien-anh/7939-dien-vien-dac-biet-cua-bang-chung-vo-hinh"}
                }
        };
    }


// =========================================================
// FOOTER
// =========================================================

    @DataProvider(name = "footer-link")
    public static Object[][] footerLink() {
        return new Object[][]{
                {
                        new String[][]{
                                {"FAQ", "https://demo1.cybersoft.edu.vn/FAQ"},
                                {"Brand Guidelines", "https://demo1.cybersoft.edu.vn/Brand_Guidelines"},
                                {"Thá»a thuáº­n sá»­ dá»¥ng", "https://demo1.cybersoft.edu.vn/thoa_thuan_su_dung"},
                                {"ChÃ­nh sÃ¡ch báº£o máº­t", "https://demo1.cybersoft.edu.vn/chinh_sach_bao_mat"}
                        }
                }
        };
    }

    @DataProvider(name = "footer-partner")
    public static Object[][] footerPartner() {
        return new Object[][]{
                {
                        new String[][]{
                                {"cgvlogo", "https://www.cgv.vn/"},
                                {"bhdlogo", "https://www.bhdstar.vn/"},
                                {"galaxylogo", "https://www.galaxycine.vn/"}
                        }
                }
        };
    }

    @DataProvider(name = "footer-mobile-social")
    public static Object[][] footerMobileSocial() {
        return new Object[][]{
                {
                        new String[]{
                                "https://apps.apple.com/vn/app/tix-%C4%91%E1%BA%B7t-v%C3%A9-nhanh-nh%E1%BA%A5t/id615186197",
                                "https://play.google.com/store/apps/details?id=vn.com.vng.phim123",
                                "https://www.facebook.com/",
                                "https://zalo.me/vi/"
                        }
                }
        };
    }


// =========================================================
// SECURITY
// =========================================================

    @DataProvider(name = "security-login")
    public static Object[][] securityLogin() {
        return new Object[][]{
                {"Clara", "Clara@2026"}
        };
    }

    @DataProvider(name = "security-booking")
    public static Object[][] securityBooking() {
        return new Object[][]{
                {
                        "gÃ¡i giÃ  láº¯m chiÃªu",
                        113,
                        "21-12-2021"
                }
        };
    }


// =========================================================
// LOGOUT
// =========================================================

    @DataProvider(name = "logout-credentials")
    public static Object[][] logoutCredentials() {
        return new Object[][]{
                {"Clara", "Clara@2026"}
        };
    }


// =========================================================
// PROFILE
// =========================================================

    @DataProvider(name = "profile-login")
    public static Object[][] profileLogin() {
        return new Object[][]{
                {"Clara", "Clara@2026"}
        };
    }

    @DataProvider(name = "profile-update-phone-number")
    public static Object[][] profileUpdtaPhoneNumber() {
        return new Object[][]{
                {"Clara", "Clara@2026","037921182178"}
        };
    }

    @DataProvider(name = "profile-update-successfully")
    public static Object[][] profileUpdateSuccessfully() {
        return new Object[][]{
                {"Clara", "Clara@2026", "Clara JohnShon", "0379211821", "Clara@gmail.com"}
        };
    }

    @DataProvider(name = "profile-without-update")
    public static Object[][] profileWithoutUpdate() {
        return new Object[][]{
                {"Clara", "Clara@2026", "Clara JohnShon", "0379211821"}
        };
    }

    // ===== Cinema & Movie Detail - merged from Duy =====
    @DataProvider(name = "cinema-list-showtime")
    public static Object[][] cinemaListShowtime() {
        return new Object[][]{
            {"John Wick", "01-01-2019", "10:10"}
        };
    }

    @DataProvider(name = "movie-trailer-click")
    public static Object[][] movieTrailerClick() {
        return new Object[][]{
            {"CÃ” GÃI Tá»ª QUÃ KHá»¨"}
        };
    }

    @DataProvider(name = "movie-detail-navigate")
    public static Object[][] movieDetailNavigate() {
        return new Object[][]{
            {"CÃ” GÃI Tá»ª QUÃ KHá»¨"}, {"Äá»‹a Ä‘áº¡o"}
        };
    }

    @DataProvider(name = "movie-detail-duration")
    public static Object[][] movieDetailDuration() {
        return new Object[][]{
            {"Man of Steel",  "120phÃºt"}
        };
    }

    @DataProvider(name = "movie-detail-trailer-close")
    public static Object[][] movieTrailerClose() {
        return new Object[][]{
            {"Man of Steel"}
        };
    }

    @DataProvider(name = "movie-detail-poster")
    public static Object[][] movieDetailPoster() {
        return new Object[][]{
            {"Man of Steel"}
        };
    }

    @DataProvider(name = "movie-detail-cinema-system-switching")
    public static Object[][] movieDetailCinemaSystemSwitching() {
        return new Object[][]{
            {"AVATAR 2"}
        };
    }

    @DataProvider(name = "movie-detail-navigate-to-booking")
    public static Object[][] movieDetailNavigateToBooking() {
        return new Object[][]{
            {"Man of Steel", "09-10-2021", "02:40"}
        };
    }

    @DataProvider(name = "movie-detail-release-date")
    public static Object[][] movieDetailReleaseDate() {
        return new Object[][]{
            {"Man of Steel", "14.10.2021"}
        };
    }

    @DataProvider(name = "movie-detail-mobile")
    public static Object[][] movieDetailMobile() {
        return new Object[][]{
            {"Man of Steel"}
        };
    }

    @DataProvider(name = "movie-detail-refresh")
    public static Object[][] movieDetailRefresh() {
        return new Object[][]{
            {"Man of Steel"}
        };
    }

}
