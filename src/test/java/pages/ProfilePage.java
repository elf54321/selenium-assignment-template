package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage {

    // Complex XPath: any anchor whose href ends in a Bandcamp profile slug.
    private final By profileLink = By.xpath(
            "//a[contains(@href,'/profile') or contains(@href,'/user')]");

    private final By avatar = By.xpath(
            "//div[contains(@class,'menubar')]//img[contains(@class,'avatar')]");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoggedIn() {
        // After login Bandcamp shows the user avatar / profile link in the header.
        return isPresent(avatar) || isPresent(profileLink);
    }

    public String getProfileUrlOrEmpty() {
        if (isPresent(profileLink)) {
            return driver.findElement(profileLink).getAttribute("href");
        }
        return "";
    }
}
