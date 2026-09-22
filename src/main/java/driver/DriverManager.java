package driver;

import org.openqa.selenium.WebDriver;

//goi chay tren cac browser khac nhau nen
// can abstract class de cac class con ke thua
public abstract class DriverManager {
    protected WebDriver driver;

    public WebDriver getDriver() {
        return this.driver;
    }

    //abstract khong co than ham
    public abstract void createWebDriver();
}
