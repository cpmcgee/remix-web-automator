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
import pages.rightpanel.*;
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

    @Then("^I verify i can compile the contract without errors$")
    public void iVerifyICanCompileTheContractWithoutErrors() throws Throwable {
        String num1 = "100";
        String num2 = "200";

        RightPanel.contract().clickCreate(num1);
        Instance instance = RightPanel.contract().getInstance(0); //an instance of a smart contract, managed from the contract tab in the right panel
        Attribute a = instance.getAttribute("number");
        Method set = instance.getMethod("setNumber");
        Method get = instance.getMethod("getNumber");

        String s = a.getDecoded();
        org.junit.Assert.assertTrue(s.contains(num1));

        set.execute(num2);

        s = a.getDecoded();
        org.junit.Assert.assertTrue(s.contains(num2));

        s =get.execute().getTransaction(0).getDecoded();
        org.junit.Assert.assertTrue(s.contains(num2));

        set.execute(300);
        set.execute(400);
        System.out.println(get.execute().getTransaction(1).getDecoded());
        System.out.println(get.execute().getTransaction(0).getDecoded());

        System.out.println("\nValue: " + a.getValue());
        System.out.println("\nName: " + a.getName());
        System.out.println("Execution Cost: " + a.getExCost());
        System.out.println("Transaction Cost: " + a.getTxCost());
        System.out.println("Decoded: " + a.getDecoded());


    }
}
