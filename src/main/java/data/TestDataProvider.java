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
                                "Địa đạo",
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
                                "Địa đạo",
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
                {2}
        };
    }

    @DataProvider(name = "movie-detail")
    public static Object[][] movieDetail() {
        return new Object[][]{
                {
                        new String[]{
                                "Địa đạo",
                                "Man of Steel"
                        },
                        "120phút"
                }
        };
    }

    @DataProvider(name = "movie-duration-rating")
    public static Object[][] movieDurationRating() {
        return new Object[][]{
                {"Man of Steel", "120phút", "C18"}
        };
    }

    @DataProvider(name = "movie-trailer")
    public static Object[][] movieTrailer() {
        return new Object[][]{
                {
                        new String[]{
                                "Man of Steel",
                                "John Cena WWE"
                        }
                }
        };
    }


    @DataProvider(name = "movie-search-result")
    public static Object[][] movieSearchResult() {
        return new Object[][]{
                {"Man of Steel",
                        "C18"}
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

    @DataProvider(name = "cinema-system-switching")
    public static Object[][] cinemaSystemSwitching() {

        return new Object[][]{
                {
                        new String[][]{
                                {"cgv", "CGV - Pandora City"},
                                {"bhd-star-cineplex", "BHD Star Cineplex - Phạm Hùng"},
                                {"lotte-cinema", "Lotte - Phú Thọ"}
                        }
                }
        };
    }

    @DataProvider(name = "cinema-name-address")
    public static Object[][] cinemaNameAddress() {
        return new Object[][]{
                {"lotte-cinema", "Lotte - Phú Thọ", "L4-Lotte Mart Phú Thọ, Q.11"}
        };
    }

    @DataProvider(name = "cinema-movie")
    public static Object[][] cinemaMovie() {
        return new Object[][]{
                {"cgv", "CGV - Aeon Tân Phú", "John Wick"}
        };
    }

    @DataProvider(name = "cinema-show-time")
    public static Object[][] cinemaShowTime() {
        return new Object[][]{
                {/*"cgv", "CGV - Aeon Tân Phú", "John Wick", */"10:10"}
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
                        "BHD Star Cineplex - Vincom Lê Văn Việt",
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
                        "BHD Star Cineplex - Vincom Lê Văn Việt",
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
                        "gái già lắm chiêu",
                        "21-12-2021",
                }
        };
    }

    @DataProvider(name = "booking-information")
    public static Object[][] bookingInformation() {
        return new Object[][]{
                {
                        "gái già lắm chiêu",
                        "21-12-2021",
                        22,
                        "CGV - Vincom Gò Vấp"
                }
        };
    }

    @DataProvider(name = "booking-total-price")
    public static Object[][] bookingTotalPrice() {
        return new Object[][]{
                {
                        "gái già lắm chiêu",
                        "21-12-2021",
                        "0VND"
                }
        };
    }

    @DataProvider(name = "booking-regular-seat")
    public static Object[][] bookingRegularSeat() {
        return new Object[][]{
                {
                        "gái già lắm chiêu",
                        "21-12-2021",
                        22,
                        "Ghế 22,",
                        "75000VND"
                }
        };
    }

    @DataProvider(name = "booking-vip-seat")
    public static Object[][] bookingVipSeat() {
        return new Object[][]{
                {
                        "gái già lắm chiêu",
                        "21-12-2021",
                        52,
                        "Ghế 52,",
                        "90000VND"
                }
        };
    }

    @DataProvider(name = "booking-delete-seat")
    public static Object[][] bookingDeleteSeat() {
        return new Object[][]{
                {
                        "gái già lắm chiêu",
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
                        "gái già lắm chiêu",
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
                        "gái già lắm chiêu",
                        "21-12-2021",
                        "X",
                        "04"
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
                        "gái già lắm chiêu",
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
                        "gái già lắm chiêu",
                        "21-12-2021",
                        147
                }
        };
    }

    @DataProvider(name = "booking-without-login")
    public static Object[][] bookingWithoutLogin() {
        return new Object[][]{
                {
                        "gái già lắm chiêu",
                        "21-12-2021",
                        10
                }
        };
    }


// =========================================================
// SEARCH
// =========================================================

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
                {"Ác Ma"}
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
                                "Điện Ảnh 24h",
                                "Review",
                                "Khuyến mãi"
                        }
                }
        };
    }

    @DataProvider(name = "news-big-article")
    public static Object[][] newsBigArticle() {
        return new Object[][]{
                {"TENET công bố ngày khởi chiếu chính thức tại Việt Nam"}
        };
    }

    @DataProvider(name = "news-small-article")
    public static Object[][] newsSmallArticle() {
        return new Object[][]{
                {"Pee Nak 2 - Vạn kiếp thiên thu, đi tu không hết nghiệp!"}
        };
    }

    @DataProvider(name = "news-medium-article")
    public static Object[][] newsMediumArticle() {
        return new Object[][]{
                {"Gerard Butler cùng bồ cũ Deadpool tham gia Greenland"}
        };
    }


// =========================================================
// FOOTER
// =========================================================

    @DataProvider(name = "footer-link")
    public static Object[][] footerLink() {
        return new Object[][]{
                {
                        new String[]{
                                "FAQ",
                                "Brand Guidelines",
                                "Thỏa thuận sử dụng",
                                "Chính sách bảo mật"
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
                        "gái già lắm chiêu",
                        22,
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
}