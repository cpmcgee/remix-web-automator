package pages.rightpanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.RightPanel;
import org.openqa.selenium.NoSuchElementException;

/**
 * Created by chris on 6/26/17.
 */
public class ContractTab extends RightPanel {

    public static final By createButton = By.cssSelector("#output .createContract button[title='Create']");

    public static final By envDropdown = By.id("selectExEnvOptions");

    public static final By acctDropdown = By.id("txorigin");

    public static final By gasLimit = By.id("gasLimit");

    public static final By value = By.id("value");

    public ContractTab(WebDriver driver) { super(driver); }

    public void clickPublish()
    {

    }

    public void clickCreate()
    {
        try {
            driver.findElement(createButton).click();
        }
        catch (NoSuchElementException ex)
        {
            System.out.println("Could not click create, error is present");
        }
    }
}
