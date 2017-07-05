package pages.rightpanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by chris on 6/30/17.
 */
public class Transaction
{
    private WebElement element;

    private String result;

    private int txCost;

    private int exCost;

    private String decoded;

    public Transaction(WebElement we)
    {
        element = we;

        result = element.findElement(By.cssSelector(".returned ")).getText().replace("Result:", "");

        String subText = element.findElement(By.cssSelector(".gasUsed .gasUsed"))
                .getText();

        txCost = Integer.parseInt(
                element.findElement(By.cssSelector(".gasUsed"))
                        .getText().replace(subText, "")
                        .replaceAll("\\D+", ""));

        exCost = Integer.parseInt(
                subText.replaceAll("\\D+", ""));

        decoded = element.findElement(By.cssSelector(".decoded ol")).getText();
    }

    public String getResult() {return result;}
    public int getTxCost() {return txCost;}
    public int getExCost() {return exCost;}
    public String getDecoded() {return decoded;}
}