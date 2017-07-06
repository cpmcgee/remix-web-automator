package pages.rightpanel;

import com.sun.xml.internal.ws.commons.xmlutil.Converter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.RemixBase;
import pages.RightPanel;
import org.openqa.selenium.NoSuchElementException;
import util.Wait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chris on 6/26/17.
 */
public class ContractTab extends RemixBase {

    public static final By createButton = By.cssSelector("#output .createContract button[title='Create']");

    public static final By createArg = By.cssSelector("#output .createContract .contractProperty input");

    public static final By envDropdown = By.id("selectExEnvOptions");

    public static final By acctDropdown = By.id("txorigin");

    public static final By gasLimit = By.id("gasLimit");

    public static final By value = By.id("value");

    public static final By instancesLocator = By.cssSelector(".create .createContract .instance");

    private List<Instance> instances;

    private int latestInstance = 0;

    public ContractTab(WebDriver driver) {
        super(driver);
    }

    public void clickPublish() {

    }

    public void clickCreate() {
        try {
            find(createButton).click();
            //Wait.until(0, (x) -> (int)getInstances().size() > (int)x, 5);
            getInstances();
            latestInstance++;
        }
        catch (NoSuchElementException ex) {
            System.out.println("Could not click create, error is present");
        }
    }

    public void clickCreate(String arg) {
        try {
            find(createArg).sendKeys(arg);
            find(createButton).click();
            //Wait.until(0, (x) -> (int)getInstances().size() > (int)x, 5);
            getInstances();
            latestInstance++;
        }
        catch (NoSuchElementException ex) {
            System.out.println("Could not click create, error is present");
        }
    }

    public void clickCreate(double arg) {
        try {
            find(createArg).sendKeys(Double.toString(arg));
            find(createButton).click();
            //Wait.until(0, (x) -> (int)getInstances().size() > (int)x, 5);
            getInstances();
            latestInstance++;
        }
        catch (NoSuchElementException ex) {
            System.out.println("Could not click create, error is present");
        }
    }

    public List<Instance> getInstances() {
        instances = findAll(instancesLocator).stream()
                .map(we -> new Instance(we, this))
                .collect(Collectors.toList());
        return instances;
    }

    public Instance getInstance(int index) {
        return instances.get(index);
    }
}









