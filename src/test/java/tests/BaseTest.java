package tests;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.AllureAttachments;

import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @Parameters({"browser", "headless"})
    @BeforeMethod
    public void setUp(
            @Optional("chrome") String browser,
            @Optional("false") String headless
    ) {

        driver = createDriver(
                browser,
                Boolean.parseBoolean(headless)
        );

        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        Allure.parameter("Browser", browser);
    }

    private WebDriver createDriver(String browser, boolean headless) {

        switch (browser.toLowerCase()) {

            case "firefox" -> {

                FirefoxOptions options = new FirefoxOptions();

                if (headless) {
                    options.addArguments("-headless");
                }

                return new FirefoxDriver(options);
            }

            case "edge" -> {

                EdgeOptions options = new EdgeOptions();

                if (headless) {
                    options.addArguments("--headless=new");
                }

                return new EdgeDriver(options);
            }

            default -> {

                ChromeOptions options = new ChromeOptions();

                if (headless) {
                    options.addArguments("--headless=new");
                }

                return new ChromeDriver(options);
            }
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        if (driver != null && !result.isSuccess()) {

            AllureAttachments.screenshot(
                    driver,
                    "Failure screenshot"
            );

            AllureAttachments.pageSource(
                    driver,
                    "Page source"
            );
        }

        if (driver != null) {
            driver.quit();
        }
    }
}