package PageOJ;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Homepage {
    @FindBy(xpath = "//input[@id=\"key\"]")
    public WebElement tbsearch;

    By suggestion = By.xpath("//div[contains(@class,\" suggest \")]");
    By tophit = By.xpath("//li[@class=\"ais-Hits-item\"]");
    By topnewhit = By.xpath("//li[@class=\"ais-Hits-item news-hits-item hits-js\"]");
    By toppro = By.xpath("//div[@class =\"cdt-product\"]|//div[@class =\"cdt-product product-status\"]");
    WebDriver webDriver;

    public Homepage(WebDriver webDriver)
    {
        this.webDriver =  webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    public void inputkeyword(String keyword)
    {
        this.tbsearch.sendKeys(keyword);
    }

    public WebElement suggestionbox()
    {
        WebDriverWait suggestionwait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        WebElement pnlSuggestion = suggestionwait.until(
                ExpectedConditions.visibilityOfElementLocated(suggestion)
        );
        return pnlSuggestion;
    }

    public List<WebElement> top3hit ()
    {
        List<WebElement> top3hit = this.webDriver.findElements(tophit);
        return top3hit;
    }

    public List<WebElement> top3newhit ()
    {
        List<WebElement> top3newhit = this.webDriver.findElements(topnewhit);
        return top3newhit;
    }

    public List<WebElement> topproduct ()
    {
        tbsearch.sendKeys(Keys.ENTER);
        List<WebElement> topproduct = this.webDriver.findElements(toppro);
        return topproduct;
    }
}
