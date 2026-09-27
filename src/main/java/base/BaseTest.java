package base;

import driver.DriverManager;
import driver.DriverManagerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import report.ExtentReportManager;
import untils.ConfigManager;

import java.lang.reflect.Method;

public class BaseTest {
    protected WebDriver driver;
    protected final Logger LOG = LogManager.getLogger(getClass());

    /// Create report file
    @BeforeSuite
    public void beforeSuite() {
        LOG.info("BeforeSuite executing...");
        //khoi tao folder report
        ExtentReportManager.initializeExtentReports();
        ConfigManager.loadProperties(); //Khoi tao device
        LOG.info("BeforeSuite ended...");
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        LOG.info("BeforeMethod executing...");
        String browser = ConfigManager.getProperty("browser");
        //report
        ExtentReportManager.createTest(method.getName());

        //Khoi tao driver
        DriverManager driverManager = DriverManagerFactory.getDriverManager(browser);
        driverManager.createWebDriver();

        //Set driver
        driver = driverManager.getDriver();

        //Get driver
        driver.manage().window().maximize();
        driver.get("https://demo1.cybersoft.edu.vn/");
        LOG.info("BeforeMethod ended...");
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        LOG.info("AfterMethod executing...");
        if(result.getStatus() == ITestResult.FAILURE){
            ExtentReportManager.captureScreenshot(driver, result.getMethod().getMethodName());
            ExtentReportManager.fail(result.getThrowable().toString());
        }
        driver.quit();
        LOG.info("AfterMethod ended...");
    }

    @AfterSuite
    public  void afterSuite(){
        LOG.info("AfterSuite executing...");
        ExtentReportManager.flushReports(); //tong ket report
        LOG.info("AfterSuite ended...");
    }
}
