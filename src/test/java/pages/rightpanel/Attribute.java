package pages.rightpanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.Wait;

/**
 * Created by chris on 6/30/17.
 */
public class Attribute{

    private WebElement element;

    private String value;

    private int txCost;

    private int exCost;

    private String decoded;

    private final By callButton = By.cssSelector(".call");

    public Attribute(WebElement we)
    {
        element = we;

        String subText = element.findElement(By.cssSelector(".gasUsed .gasUsed"))
                .getText();
        value = element.findElement(By.cssSelector(".value .value"))
                .getText().replace("Value:", "")
                .replace(" ", "")
                .replace("\"", "");
        txCost = Integer.parseInt(
                element.findElement(By.cssSelector(".gasUsed"))
                        .getText().replace(subText, "")
                        .replaceAll("\\D+", ""));
        exCost = Integer.parseInt(
                subText.replaceAll("\\D+", ""));
        decoded = element.findElement(By.cssSelector(".decoded ol")).getText();
    }

    public String getName()
    {
        //element.findElement(callButton).click();
        return element.findElement(callButton).getAttribute("title");
    }

    public String getValue()
    {
        element.findElement(callButton).click();
        return element.findElement(By.cssSelector(".value .value"))
                .getText().replace("Value:", "")
                .replace(" ", "")
                .replace("\"", "");
    }

    public int getTxCost() {
        element.findElement(callButton).click();
        String subText = element.findElement(By.cssSelector(".gasUsed .gasUsed"))
                .getText();
        return Integer.parseInt(
                element.findElement(By.cssSelector(".gasUsed"))
                        .getText().replace(subText, "")
                        .replaceAll("\\D+", ""));
    }
    public int getExCost() {
        element.findElement(callButton).click();
        String subText = element.findElement(By.cssSelector(".gasUsed .gasUsed"))
                .getText();
        return Integer.parseInt(
                subText.replaceAll("\\D+", ""));
    }
    public String getDecoded() {
        element.findElement(callButton).click();
        return element.findElement(By.cssSelector(".decoded ol")).getText();
    }
}
