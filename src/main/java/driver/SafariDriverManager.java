package driver;

import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class SafariDriverManager extends DriverManager {
    @Override
    public void createWebDriver() {
        SafariOptions options = new SafariOptions();
        this.driver = new SafariDriver(options);
    }
}
