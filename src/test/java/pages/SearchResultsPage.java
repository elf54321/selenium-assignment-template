package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsPage extends BasePage {

    // Complex XPath: every result row that lives inside a list with id "pgBd"
    // OR within a results container; this handles both legacy and current markup.
    private final By resultItems = By.xpath(
            "//ul[contains(@class,'result-items')]/li" +
            " | //li[contains(@class,'searchresult')]");

    private final By resultHeadings = By.xpath(
            "//li[contains(@class,'searchresult')]" +
            "//div[contains(@class,'heading')]");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public int countResults() {
        waitUntilVisible(By.tagName("body"));
        return driver.findElements(resultItems).size();
    }

    public List<String> resultTitles() {
        return driver.findElements(resultHeadings).stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public boolean isOnSearchPage() {
        return driver.getCurrentUrl().contains("/search");
    }
}
