package pages.rightpanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.RightPanel;

/**
 * Created by chris on 6/26/17.
 */
public class ContractTab extends RightPanel {

    public static final By createButton = By.cssSelector("#output .createContract button[title='Create']");

    public ContractTab(WebDriver driver) { super(driver); }

    public void clickPublish()
    {

    }

    public void clickCreate()
    {
        driver.findElement(createButton).click();
    }
}
