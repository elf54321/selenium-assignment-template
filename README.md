# Selenium-2 Assignment

Selenium UI tests for **https://bandcamp.com**.

## Stack
- Java 17
- Selenium 4.18
- JUnit 5
- Gradle (no wrapper required by template)
- Chrome / ChromeDriver (headless in CI)

## Structure
```
src/test/java/
  pages/
    BasePage.java          - shared waits, JavaScript helpers
    HomePage.java          - bandcamp landing page
    LoginPage.java         - login form
    ProfilePage.java       - user profile (logged-in)
    SearchResultsPage.java - search results
  tests/
    BaseTest.java            - driver setup/teardown
    LoginTest.java           - login form scenarios
    SearchTest.java          - search form scenarios
    SmokeTest.java           - homepage availability
    NavigationTest.java      - multi-page checks, browser history
    BrowserFeaturesTest.java - cookies, JavaScript executor
  utils/
    DriverFactory.java     - Chrome WebDriver factory (headless)
```

## Running locally
```
./gradlew test
```

## Credentials
Tests read credentials from environment variables:
- `BANDCAMP_USER`
- `BANDCAMP_PASS`

For the CI a dummy account is used; login tests that need real credentials
are skipped automatically when env vars are not set.

## Website under test
- URL: https://bandcamp.com
- Login: top-right "log in" link → email + password form

## Notes
- All page interaction goes through Page Object classes — no `driver.findElement`
  calls inside test methods.
- Tests use explicit waits (`WebDriverWait` + `ExpectedConditions`).
- Several locators are XPath with predicates / contains() to handle Bandcamp's
  generated class names.
