package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrowserFeaturesTest extends BaseTest {

    @Test
    void cookiesCanBeAddedReadAndDeleted() {
        new HomePage(driver).open();

        Cookie testCookie = new Cookie("selenium_assignment", "bandcamp-test");
        driver.manage().addCookie(testCookie);

        Cookie stored = driver.manage().getCookieNamed("selenium_assignment");
        assertNotNull(stored, "Cookie should be readable right after it is added");
        assertEquals("bandcamp-test", stored.getValue());

        driver.manage().deleteCookieNamed("selenium_assignment");
        assertNull(driver.manage().getCookieNamed("selenium_assignment"),
                "Cookie should no longer exist after deletion");
    }

    @Test
    void javascriptExecutorCanScrollThePage() {
        HomePage home = new HomePage(driver).open();

        long offsetBefore = home.getVerticalScrollOffset();
        home.scrollToBottom();
        long offsetAfter = home.getVerticalScrollOffset();

        assertTrue(offsetAfter >= offsetBefore,
                "Scrolling with JavaScript should not move the viewport upwards");
    }
}
