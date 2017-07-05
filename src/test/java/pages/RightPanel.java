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
    public static final By contractTabLoc = By.cssSelector("#menu #options li[title='Environment']");
    public static final By settingsTabLoc = By.cssSelector("#menu #options li[title='Settings']");
    public static final By filesTabLoc = By.cssSelector("#menu #options li[title='Publish']");
    public static final By debuggerTabLoc = By.cssSelector("#menu #options li[title='Debugger']");
    public static final By analysisTabLoc = By.cssSelector("#menu #options li[title='Static Analysis']");
    public static final By docsTab = By.cssSelector("#menu #options #helpButton");

    public final ContractTab contractTab;
    public final SettingsTab settingsTab;
    public final FilesTab filesTab;
    public final DebuggerTab debuggerTab;
    public final AnalysisTab analysisTab;

    public RightPanel(WebDriver driver) {

        super(driver);
        contractTab = new ContractTab(driver);
        settingsTab = new SettingsTab(driver);
        filesTab = new FilesTab(driver);
        debuggerTab = new DebuggerTab(driver);
        analysisTab = new AnalysisTab(driver);
    }

    public void waitForWarning()
    {
        new WebDriverWait(driver, 100).until(ExpectedConditions.invisibilityOfElementLocated(warning));
    }

    public ContractTab contract()
    {
        click(contractTabLoc);
        return contractTab;
    }

    public SettingsTab settings()
    {
        click(settingsTabLoc);
        return settingsTab;
    }

    public FilesTab files()
    {
        click(filesTabLoc);
        return filesTab;
    }

    public DebuggerTab debugger()
    {
        click(debuggerTabLoc);
        return debuggerTab;
    }

    public AnalysisTab analysis()
    {
        click(analysisTabLoc);
        return analysisTab;
    }

    public void docs()
    {
        click(docsTab);
    }
}

