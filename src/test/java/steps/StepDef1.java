package steps;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RemixBase;
import pages.RightPanel;
import util.Configuration;
import util.DriverFactory.Driver;
import util.LOGGS;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

/**
 * Created by chris on 6/26/17.
 */
public class StepDef1 extends BaseSteps{

    @Before
    public void before() throws InterruptedException
    {
        beforeAll();
    }

    @Given("^I navigate to the remix website$")
    public void iNavigateToTheRemixWebsite() throws Throwable {
        LOGGS.info("step 1");
        Driver.load(RemixBase.URL);
        RightPanel.waitForWarning();
    }

    @Then("^I can click some stuff$")
    public void iCanClickSomeStuff() throws Throwable {
        LOGGS.info("step 2");
        //Editor.closeActiveTab();
        Editor.newTab();
        Editor.renameTab(2, "BigOldTab.sol");
        Editor.pasteFromFile("TestContract.txt");
        RightPanel.contract().clickCreate();
    }
}
