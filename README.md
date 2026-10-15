# 🎬 Movie Booking Automation Testing

Automation testing project for the CyberSoft movie booking website, built with **Java, Selenium WebDriver, TestNG, Gradle, Log4j 2, and ExtentReports**.

The project uses a Page Object Model (POM)-style structure with page classes, driver management, centralized test data, TestNG Groups, parallel test execution, logging, and automated reporting.

---

## 📌 Project Overview

The project automates functional and UI test scenarios for the website configured in `src/test/resources/configure.properties`.

**Test website:** <https://demo1.cybersoft.edu.vn>

Testing areas represented by the current test classes include:

- Login and logout
- User registration
- User profile
- Movie search
- Movie details
- Cinema list and showtimes
- Movie filtering
- Movie booking and seat selection
- Homepage banners and movie lists
- Top navigation
- Footer links
- News
- Browser compatibility
- Error handling
- Security-related scenarios

---

## 🛠️ Tech Stack

| Technology | Version / Details |
| --- | --- |
| Java | JDK 21 project setting |
| Selenium WebDriver | `4.47.0` |
| TestNG | `7.12.0` |
| Gradle Wrapper | `9.3.0` |
| Log4j 2 | `2.26.1` |
| ExtentReports | `5.1.2` |
| IDE project configuration | IntelliJ IDEA |
| Supported browser values | Chrome, Firefox, Edge, Safari |

The dependency versions above are taken from `build.gradle` and the Gradle Wrapper configuration. The JDK version is taken from the project's IntelliJ configuration.

---

## 🏗️ Framework Architecture

The project separates common test setup, browser management, page objects, test data, reporting, and test cases.

```text
captons_movie_booking/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── base/
│   │       │   ├── BasePage.java
│   │       │   └── BaseTest.java
│   │       ├── constants/
│   │       │   └── TimeOutConstants.java
│   │       ├── data/
│   │       │   └── TestDataProvider.java
│   │       ├── driver/
│   │       │   ├── DriverManager.java
│   │       │   ├── DriverManagerFactory.java
│   │       │   ├── ChromeDriverManager.java
│   │       │   ├── EdgeDriverManager.java
│   │       │   ├── FirefoxDriverManager.java
│   │       │   └── SafariDriverManager.java
│   │       ├── pages/
│   │       │   ├── BookingPage.java
│   │       │   ├── CinemaListPage.java
│   │       │   ├── CommonPage.java
│   │       │   ├── HomePage.java
│   │       │   ├── LoginPage.java
│   │       │   ├── MovieDetailPage.java
│   │       │   ├── NewsPage.java
│   │       │   ├── ProfilePage.java
│   │       │   └── RegisterPage.java
│   │       ├── report/
│   │       │   └── ExtentReportManager.java
│   │       └── untils/
│   │           └── ConfigManager.java
│   └── test/
│       ├── java/
│       │   └── testcase/
│       │       ├── BannerTest.java
│       │       ├── BookingTest.java
│       │       ├── CinemaListTest.java
│       │       ├── CompatibilityTest.java
│       │       ├── ErrorHandlingTest.java
│       │       ├── FilterTest.java
│       │       ├── FooterTest.java
│       │       ├── HomeTest.java
│       │       ├── LoginTest.java
│       │       ├── LogOutTest.java
│       │       ├── MovieDetailTest.java
│       │       ├── NewsTest.java
│       │       ├── ProfileTest.java
│       │       ├── RegisterTest.java
│       │       ├── SearchingTest.java
│       │       ├── SecurityTest.java
│       │       └── TopNavigateBarTest.java
│       └── resources/
│           ├── configure.properties
│           ├── extent-config.xml
│           ├── extent-pdf-config.yml
│           ├── extent.properties
│           ├── log4j2.xml
│           └── testng-parallel.xml
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
├── .gitignore
└── README.md
```

> The ZIP also contains generated build files and test-report output. These are generated artifacts rather than the main source structure.

---

## 🧩 Framework Components

### Base Layer

**`BaseTest`**

- Initializes ExtentReports and loads configuration before the suite.
- Reads the selected browser and base URL.
- Creates a WebDriver before each test method.
- Creates an ExtentReport test and assigns TestNG Groups.
- Captures a screenshot when a test fails.
- Closes the browser after each test.
- Flushes the report after the suite.

**`BasePage`**

Provides shared Selenium page-level functionality for page classes.

### Driver Management

Browser creation is organized under `src/main/java/driver/`:

- `DriverManager`
- `DriverManagerFactory`
- `ChromeDriverManager`
- `EdgeDriverManager`
- `FirefoxDriverManager`
- `SafariDriverManager`

The selected browser is read from `configure.properties`.

### Page Classes

The `pages` package contains page classes for booking, cinema lists, common page behavior, the homepage, login, movie details, news, profile, and registration.

### Centralized Test Data

Test data is stored in `src/main/java/data/TestDataProvider.java` and supplied to test methods through TestNG `@DataProvider` methods.

### Reporting and Logging

- `ExtentReportManager` manages the ExtentReports instance, test entries, and failure screenshots.
- `log4j2.xml` configures console and file logging.

---

## 🧪 Test Coverage

The following test classes are present in the project:

| Test Class | Test Area |
| --- | --- |
| `BannerTest` | Homepage banners |
| `BookingTest` | Booking flow, seat selection, and price checks |
| `CinemaListTest` | Cinema list, cinema switching, and showtimes |
| `CompatibilityTest` | Browser compatibility |
| `ErrorHandlingTest` | Invalid URL, offline mode, and invalid movie ID scenarios |
| `FilterTest` | Movie, cinema, and schedule filters |
| `FooterTest` | Footer content, links, and responsive layout |
| `HomeTest` | Homepage movie list and navigation |
| `LoginTest` | Login and login state |
| `LogOutTest` | Logout and access after logout |
| `MovieDetailTest` | Movie cards, trailers, details, and showtimes |
| `NewsTest` | News tabs, news items, and expand/collapse behavior |
| `ProfileTest` | Profile information and account updates |
| `RegisterTest` | Registration and input validation |
| `SearchingTest` | Movie search and search input scenarios |
| `SecurityTest` | Login-state restrictions, XSS-related scenarios, and session behavior |
| `TopNavigateBarTest` | Main navigation links and layout |

---

## 🏷️ TestNG Groups

The test classes assign the following groups in their `@Test` annotations:

```text
banner
booking
cinema
compatibility
error-handling
filter
footer
homepage
login
logout
movie-detail
news
profile
register
searching
security
topNavigateBar
```

`ProfileTest` contains tests assigned to both `profile` and `security`.

The suite file `src/test/resources/testng-parallel.xml` currently runs the `testcase` package. To run only one group, create or update a TestNG suite with a group filter, for example:

```xml
<groups>
    <run>
        <include name="booking"/>
    </run>
</groups>
```

Replace `booking` with a group name listed above to target another area.

---

## 📦 Test Data Management

Test data is centralized in:

```text
src/main/java/data/TestDataProvider.java
```

The project uses TestNG `@DataProvider` methods to supply input data to test methods. The data provider class contains datasets for areas including movies, cinema lists, showtimes, filters, registration, login, and booking.

Example of how a test can reference a data provider:

```java
@Test(
    dataProvider = "movie-list",
    dataProviderClass = TestDataProvider.class,
    groups = "movie-detail"
)
```

Use the actual data provider name required by the test method. Some datasets refer to specific movies, cinema names, schedules, or seat numbers on the configured demo website; these values may need to be reviewed if the site's data changes.

### Booking test data

Before running booking-related tests, check the relevant data providers in `TestDataProvider.java`. Seat availability and schedules may depend on the current state of the demo website. Update test data only when necessary for the scenario being tested.

---

## ⚡ Parallel Test Execution

The project configures TestNG to run test classes in parallel in `src/test/resources/testng-parallel.xml`:

```xml
<suite name="Parallel Test Suite"
       parallel="classes"
       thread-count="5">
```

The suite includes the `testcase` package and allows up to **five test classes to execute concurrently**.

---

## 📝 Logging

The project uses **Log4j 2** for logging.

Configuration file:

```text
src/test/resources/log4j2.xml
```

The configuration defines console logging and a rolling file appender. The configured log file path is:

```text
testReport_output/automation.log
```

---

## 📈 Test Reporting

The project uses **ExtentReports 5.1.2** with an `ExtentSparkReporter`.

The reporting code records test results and adds a screenshot to a failed test when `BaseTest.afterMethod()` handles a failure.

### Report output

The report path is configured in `ExtentReportManager`:

```text
testReport_output/ExtentReport_<timestamp>.html
```

Failure screenshots are saved under:

```text
testReport_output/screenshots/
```

TestNG's configured output directory is:

```text
test-output/
```

Generated report files and screenshots may differ between test runs.

---

## ⚙️ Configuration

The main test configuration is:

```text
src/test/resources/configure.properties
```

Current values in the project:

```properties
platform=web
browser=edge
baseUrl=https://demo1.cybersoft.edu.vn
headless=false
maximize=true
timeout=10
```

The supported browser values documented in the configuration are:

```text
chrome
firefox
edge
safari
```

To select a different browser, update the `browser` property. The selected browser must be installed on the machine running the tests.

---

## ▶️ How to Run

### 1. Prerequisites

- JDK 21, as specified in the IntelliJ project configuration
- A supported browser: Chrome, Firefox, Edge, or Safari
- Network access to the configured website and Maven Central

The project includes the Gradle Wrapper, so a separate Gradle installation is normally not required.

### 2. Open the project

Open the project root in IntelliJ IDEA and import it as a Gradle project.

### 3. Verify Java

```bash
java -version
```

### 4. Run all tests

**Windows Command Prompt:**

```bat
gradlew.bat test
```

**Windows PowerShell:**

```powershell
.\gradlew.bat test
```

**macOS / Linux:**

```bash
./gradlew test
```

The Gradle `test` task uses `src/test/resources/testng-parallel.xml`, which runs the `testcase` package with parallel execution.

---

## 🌐 Test Environment

The configured test website is:

<https://demo1.cybersoft.edu.vn>

This is a demo website configured in the project. Movie information, cinema schedules, and seat availability may change and can affect tests that depend on specific data.

---

## 🎯 Project Goals

The current framework demonstrates:

- Selenium WebDriver UI automation
- Page-based organization of test code
- Browser driver management
- Centralized test data with TestNG `@DataProvider`
- TestNG Groups
- Parallel test execution
- ExtentReports test reporting
- Failure screenshot capture
- Log4j-based logging
- Functional, compatibility, error-handling, and security-related test scenarios

---

## 🤝 Contributing

1. Create a branch for your change.
2. Keep changes focused on the relevant page, test, data provider, or configuration.
3. Run the relevant tests, then run the full suite when practical.
4. Include a concise description of the change and the tests executed in your pull request.

---

## 👨‍💻 Author

👨‍💻 Author
**Nguyễn Tấn Phú**
**Trần Thị Ngọc Thương**
**Nguyễn Thị Thùy Trang**
**Trần Lê Anh Duy**
Automation Testing / QA Engineer

Skills demonstrated in this project:
```text
Java
Selenium WebDriver
TestNG
Gradle
Page Object Model
DataProvider
TestNG Groups
XPath
Explicit Wait
Log4j
ExtentReports
Parallel Testing
Functional Testing
```
---

## 📄 License

This project is created for **learning, testing and portfolio purposes**.