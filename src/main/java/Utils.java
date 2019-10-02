import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Utils extends BasePage{
    //method to maximise the screen
    public void maximisescreen()

    {
        BasePage.driver.manage().window().fullscreen();
    }

    //implicit wait
    public void implicitwait() {
        BasePage.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //Wait for locator to be clickable for given time in seconds
    public static  void waitForClickable(By by, long time)
    {
        WebDriverWait wait = new WebDriverWait(BasePage.driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    //method to findElement(By, by) and click() to Click on any element of the webpage
    public void clickButton(By by)
    {
        driver.findElement(by).click();
    }

    //findElement(By, by) with sendKeys()
    public void enterText(By by, String text)
    {
        driver.findElement(by).sendKeys(text);
    }

    //getText() is a method that gets you the inner text of the web element
    public String getText(By by)
    {
        return driver.findElement(by).getText();
    }
    //Clicking Element
    public static void clickElement(By by) {
        driver.findElement(by).click();
    }


}
