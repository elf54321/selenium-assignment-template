package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final String URL = "https://bandcamp.com/login";

    // Bandcamp has used a few different IDs over time; match several.
    private final By usernameField = By.xpath(
            "//input[@id='username-field' or @name='username'" +
            " or @type='email' or @id='username']");
    private final By passwordField = By.xpath(
            "//input[@id='password-field' or @name='password' or @type='password']");

    // Complex XPath: submit button inside the login form, matching by type and
    // visible text "log in", regardless of any wrapping span.
    private final By submitButton = By.xpath(
            "//form[contains(@action,'login') or contains(@id,'login')]" +
            "//button[@type='submit' or contains(translate(.,'LOGIN','login'),'log in')]");

    private final By errorMessage = By.xpath(
            "//*[contains(@class,'error') and " +
            "(contains(translate(.,'INCORRECT','incorrect'),'incorrect') or " +
            " contains(translate(.,'INVALID','invalid'),'invalid') or " +
            " contains(translate(.,'WRONG','wrong'),'wrong'))]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(URL);
        return this;
    }

    public LoginPage typeUsername(String username) {
        waitUntilVisible(usernameField).sendKeys(username);
        return this;
    }

    public LoginPage typePassword(String password) {
        waitUntilVisible(passwordField).sendKeys(password);
        return this;
    }

    public void submit() {
        waitUntilClickable(submitButton).click();
    }

    public void loginAs(String username, String password) {
        typeUsername(username);
        typePassword(password);
        submit();
    }

    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().contains("/login");
    }

    public boolean hasErrorMessage() {
        return isPresent(errorMessage);
    }
}
