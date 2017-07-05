package pages.rightpanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
        value = element.findElement(By.cssSelector(".value .value")).getText().replace("Value:", "").replace(" ", "");

        String subText = element.findElement(By.cssSelector(".gasUsed .gasUsed"))
                .getText();

        txCost = Integer.parseInt(
                element.findElement(By.cssSelector(".gasUsed"))
                        .getText().replace(subText, "")
                        .replaceAll("\\D+", ""));

        exCost = Integer.parseInt(
                subText.replaceAll("\\D+", ""));

        decoded = element.findElement(By.cssSelector(".decoded ol")).getText();

        element.findElement(callButton).click();
    }

    public String getName()
    {
        return element.findElement(callButton).getAttribute("title");
    }

    public String getValue() {return value;}
    public int getTxCost() {return txCost;}
    public int getExCost() {return exCost;}
    public String getDecoded() {return decoded;}
}
