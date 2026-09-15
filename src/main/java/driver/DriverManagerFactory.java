package driver;

public class DriverManagerFactory {

    public static DriverManager getDriverManager(String browser) {
        return switch (browser) {
            case "chrome" -> new ChromeDriverManager();
            case "firefox" -> new FirefoxDriverManager();
            case "edge" -> new EdgeDriverManager();
            case "safari" -> new SafariDriverManager();
            default -> null;
        };
    }
}
