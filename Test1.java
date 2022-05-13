package BravoSelenium;

import PageOJ.Homepage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test1 {
    WebDriver driver;
    Homepage homepage;
    String keyword = "iphone 13 pro max";

    @Before
    public void setup()
    {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.get("https://fptshop.com.vn/");
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        this.homepage = new Homepage(this.driver);
    }

    @After
    public void TearDown () throws InterruptedException
    {
        Thread.sleep(2000);
        this.driver.quit();
    }

    @Test
    public void SuggesionBox()
    {
        this.homepage.inputkeyword(this.keyword);
        //tbsearch.sendKeys(Keys.ENTER);
        WebElement pnlSuggesionbox = this.homepage.suggestionbox();
        Assert.assertTrue(pnlSuggesionbox.isDisplayed());
    }

    @Test
    public void Top3()
    {
        this.homepage.inputkeyword(this.keyword);
        //tbsearch.sendKeys(Keys.ENTER);
        WebDriverWait waitPopup = new WebDriverWait(this.driver, Duration.ofSeconds(15));
        WebElement pnlSuggesionbox = this.homepage.suggestionbox();
        List<WebElement> top3hit = this.homepage.top3hit();
        Assert.assertEquals(3,top3hit.size());

        boolean isBold = true;
        boolean isKeyword = true;

        for (WebElement item: top3hit)
        {
         WebElement keyword = item.findElement(By.xpath("//em"));
         String boldstyle = keyword.getCssValue("font-weight");
         isBold = Integer.parseInt(boldstyle) >= 500;
         if (isBold==false)
             break;

         isKeyword = keyword.getText().equalsIgnoreCase(this.keyword);
         if (isKeyword==false)
             break;
        }

        Assert.assertTrue(isBold);
        Assert.assertTrue(isKeyword);
    }
    @Test
    public void top3newrelated()
    {
        this.homepage.inputkeyword(this.keyword);
        WebDriverWait waitPopup = new WebDriverWait(this.driver, Duration.ofSeconds(15));
        List<WebElement> top3newhit = this.homepage.top3newhit();
        Assert.assertEquals(3,top3newhit.size());

        boolean isKeyword = true;
        boolean isBold = true;

        for (WebElement item: top3newhit)
        {
            List<WebElement> keyword = item.findElements(By.xpath("//em"));
            for (WebElement itempro: keyword)
            {
                isKeyword = this.keyword.toUpperCase().replace(" ","").contains(itempro.getText().toUpperCase().replace(" ",""));

                if (isKeyword==false)
                    break;

                String boldstyle = itempro.getCssValue("font-weight");

                isBold = Integer.parseInt(boldstyle) >= 500;
                if (isBold==false)
                    break;

            }

        }
        Assert.assertTrue(isBold);
        Assert.assertTrue(isKeyword);

    }
    @Test
    public void topproduct()
    {
        this.homepage.inputkeyword(this.keyword);
        WebDriverWait waitPopup = new WebDriverWait(this.driver, Duration.ofSeconds(15));
        List<WebElement> topproduct = this.homepage.topproduct();
        //Assert.assertEquals(23,topproduct.size());

        boolean isKeyword = true;
        boolean isBold = true;

        for (WebElement item: topproduct)
        {
            List<WebElement> keyword = item.findElements(By.xpath("//em"));
            for (WebElement itempro: keyword)
            {
                isKeyword = this.keyword.toUpperCase().contains(itempro.getText().toUpperCase());

                if (isKeyword==false)
                    break;

                String boldstyle = itempro.getCssValue("font-weight");

                isBold = Integer.parseInt(boldstyle) >= 500;
                if (isBold==false)
                    break;

            }
            if (isKeyword==false)
                break;
        }
        Assert.assertTrue(isKeyword);
    }
}
