package steps;

import org.openqa.selenium.WebDriver;
import pages.FilePanel;
import pages.RightPanel;
import util.DriverFactory;
import pages.Editor;
import util.LOG;

import java.util.concurrent.TimeUnit;

/**
 * Created by chris on 6/26/17.
 */
public class BaseSteps {

    protected WebDriver driver;
    protected RightPanel RightPanel;
    protected Editor Editor;
    protected FilePanel FilePanel;

    public BaseSteps() {
        LOG.init();
        driver = DriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        RightPanel = new RightPanel(driver);
        FilePanel = new FilePanel(driver);
        Editor = new Editor(driver);
    }

    public void beforeAll() {

    }
}
