package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pages.FilePanel;
import pages.RemixBase;
import pages.rightpanel.*;
import util.DriverFactory.Driver;
import util.LOGGS;

import static org.junit.Assert.assertTrue;

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
        //RightPanel.waitForWarning();
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

    @Then("^I create the contract with an argument of \"([^\"]*)\"$")
    public void iCreateTheContractWithAnArgumentOf(String arg) throws Throwable {
        if (arg.equals(""))
            RightPanel.contract().clickCreate();
        else
            RightPanel.contract().clickCreate(arg);
    }

    @And("^I verify running \"([^\"]*)\" will return \"([^\"]*)\"$")
    public void iVerifyRunningWillReturn(String methodName, String result) throws Throwable {
        Instance contract = RightPanel.contract().getInstance(0);
        Method method = contract.getMethod(methodName);
        assertTrue("Method did not return correct " + result, method.execute().getDecoded().contains(result));
    }

    @Then("^I run \"([^\"]*)\" with an argument of \"([^\"]*)\"$")
    public void iRunWithAnArgumentOf(String methodName, String arg) throws Throwable {
        if (arg.equals(""))
            RightPanel.contract().getInstance(0).getMethod(methodName).execute();
        else
            RightPanel.contract().getInstance(0).getMethod(methodName).execute(arg);
    }

    @Then("^I verify the \"([^\"]*)\" attribute has a value of \"([^\"]*)\"$")
    public void iVerifyTheAttributeHasAValueOf(String attributeName, String val) throws Throwable {
        Instance contract = RightPanel.contract().getInstance(0);
        Attribute attribute = contract.getAttribute(attributeName);
        String decoded = attribute.getDecoded();
        Editor.newTab();
        FilePanel.openFile("TestContract.sol");
        assertTrue(decoded.contains(val));
    }
}
