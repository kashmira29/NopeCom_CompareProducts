import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CompareProducts extends Utils {

    SoftAssert softAssert = new SoftAssert();
    Loadprops loadprop = new Loadprops();

    //before method to open the browser
    @BeforeMethod

    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\Resources\\Webdriver\\chromedriver.exe");
        //open the browser
        driver = new ChromeDriver();
        //maximise the window browser screen
        maximisescreen();
        implicitwait();
        //open the website
        driver.get("https://demo.nopcommerce.com");
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();

    }

    @Test
    public void UserShouldBeAbleToAddItemForComparision() {
        clickButton(By.xpath("//div[@data-productid=\"4\"]  //input[@value=\"Add to compare list\"]"));
        clickButton(By.xpath("//span[@class=\"close\"]"));
        //driver.findElement(By.xpath("//span[@class=\"close\"]")).click();

        softAssert.assertTrue(driver.findElement(By.linkText("product comparison")).isDisplayed());

        clickButton(By.xpath("//div[@data-productid=\"18\"]  //input[@value=\"Add to compare list\"]"));

        //waitForClickable(By.xpath("//div[@data-productid=\"18"),10);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clickButton(By.xpath("//a[@href=\"/compareproducts\"]"));
        //waitForClickable(By.xpath("//a[@href=\"/compareproducts\"]"),10);

        //softAssert.assertTrue(driver.findElement(By.linkText("product comparison")).isDisplayed());

        List<WebElement>al = driver.findElements(By.xpath("//tr[@class=\"product-name\"]//a[@href]"));
        System.out.println(al.size());
        List<String> pname = new ArrayList<>();
        for(WebElement e : al) {

            pname.add(e.getText());
            System.out.println(e.getText());
        }
        String expectedname[] = {"HTC One M8 Android L 5.0 Lollipop", "Apple MacBook Pro 13-inch"};
        System.out.println("Expected Product Name:" + Arrays.toString(expectedname));
        String actualname[] = pname.toArray(new String[pname.size()]);
        System.out.println("Actual Product Name:" + Arrays.toString(actualname));
        Assert.assertEquals(expectedname, actualname);



        clickButton(By.xpath("//a[@class=\"clear-list\"]"));
        String ExpectedResult2 = "You have no items to compare.";
        String ActualResult2 = getText(By.xpath("//div[@class=\"no-data\"]"));

        softAssert.assertEquals(ExpectedResult2, ActualResult2);
        softAssert.assertAll();

    }

    @Test
    public void UserShouldBeAbleToAddCommentinNewsDetails() {
        clickButton(By.xpath("//a[@href=\"/new-online-store-is-open\"][@class=\"read-more\"]"));
        enterText(By.xpath("//*[@id=\"AddNewComment_CommentTitle\"]"), loadprop.getProperty("Title"));
        enterText(By.xpath("//*[@id=\"AddNewComment_CommentText\"]"), loadprop.getProperty("Comment"));
        clickButton(By.xpath("//input[@name=\"add-comment\"]"));

        String ActualResult = getText(By.xpath("//div[@class=\"result\"]"));
        String ExpectedResult = "News comment is successfully added.";
        Assert.assertEquals(ActualResult, ExpectedResult);
        softAssert.assertEquals(ExpectedResult, ActualResult);

        List<WebElement> commentList=driver.findElements(By.xpath("//div[@class='comment news-comment']"));
        System.out.println("List of comments "+commentList.size());
        WebElement lasteComment=commentList.get(commentList.size()-1);
        String lastelementText=(lasteComment.getText());
        System.out.println(lastelementText);
        if(lasteComment.getAttribute("outerHTML").contains("comment-time"))
        {
            System.out.println("last elemment details");
        }
        softAssert.assertTrue(lasteComment.getAttribute("outerHTML").contains("comment-time"));
        softAssert.assertAll();

    }


    @Test
    public  void UserShouldBeAbleToSearchItemsInSearchBox() {

        enterText(By.xpath("//*[@id=\"small-searchterms\"]"),loadprop.getProperty("ProductName"));
        driver.findElement(By.xpath("//input[@class=\"button-1 search-box-button\"]")).click();
        List<WebElement> al = driver.findElements(By.xpath("//h2[@class=\"product-title\"]"));
        System.out.println(al.size());
        for (WebElement e : al) {
             //System.out.println(e.getText());
           if (e.getText().contains("Nike")) {
               e.isDisplayed();
                System.out.println(e.getText());


            }

        }
    }
}