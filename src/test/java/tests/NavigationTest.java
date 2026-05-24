package tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NavigationTest extends BaseTest {

    // A list of Bandcamp pages we expect to be reachable. The
    // everyPageHasATitle test iterates over this array.
    private static final String[] PAGES = {
            "https://bandcamp.com/",
            "https://bandcamp.com/login",
            "https://bandcamp.com/signup",
            "https://bandcamp.com/discover",
            "https://bandcamp.com/about"
    };

    @Test
    void everyKnownPageHasANonEmptyTitle() {
        for (String url : PAGES) {
            driver.get(url);
            assertFalse(driver.getTitle().isBlank(),
                    "Page should expose a non-empty title: " + url);
        }
    }

    @Test
    void browserBackAndForwardNavigateThroughHistory() {
        driver.get("https://bandcamp.com/");
        driver.get("https://bandcamp.com/login");
        assertTrue(driver.getCurrentUrl().contains("/login"),
                "We should start on the login page");

        driver.navigate().back();
        assertFalse(driver.getCurrentUrl().contains("/login"),
                "Back should leave the login page");

        driver.navigate().forward();
        assertTrue(driver.getCurrentUrl().contains("/login"),
                "Forward should return to the login page");
    }
}
