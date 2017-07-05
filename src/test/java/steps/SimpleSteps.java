package steps;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.RemixBase;
import pages.rightpanel.Attribute;
import pages.rightpanel.ContractTab;
import pages.rightpanel.Instance;
import util.DriverFactory.Driver;
import util.LOGGS;
import util.Wait;

/**
 * Created by chris on 6/26/17.
 */
public class SimpleSteps extends BaseSteps{

    @Before
    public void before() throws InterruptedException
    {
        beforeAll();
    }

    @Given("^I navigate to the remix website$")
    public void iNavigateToTheRemixWebsite() throws Throwable {
        LOGGS.info("step 1");
        Driver.load(RemixBase.URL);
    }

    @And("^I wait for the page and compiler to load$")
    public void iWaitForThePageAndCompilerToLoad() throws Throwable {
        RightPanel.waitForWarning();
    }

    @And("^I delete the default tab$")
    public void iDeleteTheDefaultTab() throws Throwable {
        Editor.closeActiveTab();
    }

    @And("^I create a new tab called \"([^\"]*)\"$")
    public void iCreateANewTabCalled(String tabName) throws Throwable {
        Editor.newTab();
        Editor.renameTab("Untitled.sol", tabName);
    }

    @And("^I load into the tab the code from \"([^\"]*)\"$")
    public void iLoadIntoTheTabTheCodeFrom(String fileName) throws Throwable {
        Editor.clearEditor();
        Editor.pasteFromFile(fileName);
    }

    @Then("^I verify i can compile the contract without errors$")
    public void iVerifyICanCompileTheContractWithoutErrors() throws Throwable {
        RightPanel.contract().clickCreate();
        Instance instance = RightPanel.contract().getInstance(0); //an instance of a smart track, managed from the contract tab in the right panel
        Attribute a = instance.getAttribute("number");
        System.out.println(a.getValue());
    }
}
