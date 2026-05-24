package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    void startDriver() {
        driver = DriverFactory.createChromeDriver();
    }

    @AfterEach
    void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected static String env(String name) {
        String v = System.getenv(name);
        return v == null ? "" : v;
    }

    protected static boolean haveBandcampCredentials() {
        return !env("BANDCAMP_USER").isEmpty() && !env("BANDCAMP_PASS").isEmpty();
    }
}
