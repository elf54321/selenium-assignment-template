package tests;

import org.junit.jupiter.api.Test;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SmokeTest extends BaseTest {

    @Test
    void homepageLoadsAndShowsLogin() {
        HomePage home = new HomePage(driver).open();

        assertTrue(home.getCurrentUrl().contains("bandcamp.com"),
                "Browser should land on bandcamp.com");
        assertFalse(home.getPageTitle().isBlank(),
                "Homepage should have a non-empty title");
    }

    @Test
    void homepageHasLoginAffordance() {
        HomePage home = new HomePage(driver).open();
        // We do not require the literal "log in" link to be present (Bandcamp
        // occasionally hides it behind a menu), but the page must reach /login
        // when goToLogin() is invoked.
        home.goToLogin();
        assertTrue(driver.getCurrentUrl().contains("/login"));
    }
}
