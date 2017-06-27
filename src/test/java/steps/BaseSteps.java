package steps;

import org.openqa.selenium.WebDriver;
import pages.RightPanel;
import util.DriverFactory;
import pages.Editor;

import java.util.concurrent.TimeUnit;

/**
 * Created by chris on 6/26/17.
 */
public class BaseSteps {

    protected WebDriver driver;
    protected RightPanel RightPanel;
    protected Editor Editor;

    public BaseSteps()
    {
        driver = DriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        RightPanel = new RightPanel(driver);
        Editor = new Editor(driver);
    }

    public void beforeAll()
    {

    }
}
