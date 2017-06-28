package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.Configuration;
import util.DriverFactory;
import util.Wait;

import java.util.List;

/**
 * Created by chris on 6/26/17.
 */
public class RemixBase {

    public WebDriver driver;
    protected static final String contractsPath = Configuration.getContractsDirectory();
    public static final String URL = "https://remix.ethereum.org";
    public static final By handle = By.tagName("body");

    public RemixBase(WebDriver driver)
    {
        this.driver = driver;
    }

    public void click(By by) { driver.findElement(by).click(); }

    public void click(WebElement we) { we.click(); }

    public WebElement find(By by) { return driver.findElement(by); }

    public void executeScript(String script, Object o) { ((JavascriptExecutor)driver).executeScript(script, o); }

    public List<WebElement> findAll(By by) { return driver.findElements(by); }

    public String getWindowHandle()
    {
        return driver.getWindowHandle();
    }

    public static class Alert
    {
        public static void dismiss()
        {
            if (isPresent())
                DriverFactory.getDriver().switchTo().alert().dismiss();
        }

        public static void accept()
        {
            if (isPresent())
                DriverFactory.getDriver().switchTo().alert().accept();
        }

        public static boolean isPresent()
        {
            return ExpectedConditions.alertIsPresent().apply(DriverFactory.getDriver()) != null;
        }
    }

}