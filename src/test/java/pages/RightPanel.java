package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.rightpanel.*;
import util.DriverFactory;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by chris on 6/26/17.
 */
public class RightPanel extends RemixBase {

    public static final By handle = By.id("righthand-panel");
    public static final By tabs = By.cssSelector("#menu #options li");
    public static final By warning = By.cssSelector("#output .sol.warning");
    public static final By contractTab = By.cssSelector("#menu #options li[title='Environment']");
    public static final By settingsTab = By.cssSelector("#menu #options li[title='Settings']");
    public static final By filesTab = By.cssSelector("#menu #options li[title='Publish']");
    public static final By debuggerTab = By.cssSelector("#menu #options li[title='Debugger']");
    public static final By analysisTab = By.cssSelector("#menu #options li[title='Static Analysis']");
    public static final By docsTab = By.cssSelector("#menu #options #helpButton");

    public RightPanel(WebDriver driver) {
        super(driver);
    }

    public void waitForWarning()
    {
        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(warning));
    }

    public ContractTab contract()
    {
        click(contractTab);
        return new ContractTab(driver);
    }

    public SettingsTab settings()
    {
        click(settingsTab);
        return new SettingsTab(driver);
    }

    public FilesTab files()
    {
        click(filesTab);
        return new FilesTab(driver);
    }

    public DebuggerTab debugger()
    {
        click(debuggerTab);
        return new DebuggerTab(driver);
    }

    public AnalysisTab analysis()
    {
        click(analysisTab);
        return new AnalysisTab(driver);
    }

    public void docs()
    {
        click(docsTab);
    }
}

