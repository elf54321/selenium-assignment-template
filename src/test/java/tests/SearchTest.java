package tests;

import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.SearchResultsPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest extends BaseTest {

    @Test
    void searchingForArtistTakesUserToResultsPage() {
        SearchResultsPage results = new HomePage(driver)
                .open()
                .search("radiohead");

        assertTrue(results.isOnSearchPage(),
                "Submitting the search form should take us to /search");
    }

    @Test
    void searchingForNonsenseStillReturnsAStablePage() {
        SearchResultsPage results = new HomePage(driver)
                .open()
                .search("zzqqxx-no-such-artist-123");

        // The result count may be zero, but the page must load.
        assertTrue(results.isOnSearchPage());
    }
}
