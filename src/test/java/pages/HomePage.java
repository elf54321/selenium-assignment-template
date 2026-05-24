package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    private static final String URL = "https://bandcamp.com/";

    // Complex XPath: anchor whose visible text is "log in", case-insensitive,
    // anywhere inside the page header.
    private final By loginLink =
            By.xpath("//div[contains(@id,'pagedata') or contains(@class,'menubar')]" +
                     "//a[translate(normalize-space(text()),'LOGIN','login')='log in']");

    // Bandcamp's search uses different IDs across pages; we try a few.
    private final By searchInput = By.xpath(
            "//input[@type='search' or @name='q' or @id='search-input'" +
            " or contains(@class,'search')]");
    private final By cookieAccept =
            By.xpath("//button[contains(translate(., 'ACEPT', 'acept'), 'accept')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get(URL);
        acceptCookiesIfPresent();
        return this;
    }

    private void acceptCookiesIfPresent() {
        if (isPresent(cookieAccept)) {
            try {
                driver.findElement(cookieAccept).click();
            } catch (Exception ignored) {
                // Cookie banner is best-effort, do not fail the test for it.
            }
        }
    }

    public boolean hasLoginLink() {
        return isPresent(loginLink);
    }

    public LoginPage goToLogin() {
        // Fall back to direct URL if the link layout has shifted.
        if (isPresent(loginLink)) {
            waitUntilClickable(loginLink).click();
        } else {
            driver.get("https://bandcamp.com/login");
        }
        return new LoginPage(driver);
    }

    public SearchResultsPage search(String query) {
        // Try the search form first; fall back to direct URL so the test does
        // not depend on Bandcamp's exact header markup.
        try {
            WebElement input = waitUntilVisible(searchInput);
            input.clear();
            input.sendKeys(query);
            input.submit();
        } catch (Exception e) {
            driver.get("https://bandcamp.com/search?q=" + query);
        }
        return new SearchResultsPage(driver);
    }
}
