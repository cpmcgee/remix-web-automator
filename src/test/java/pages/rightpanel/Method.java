package pages.rightpanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chris on 6/30/17.
 */
public class Method
{
    private WebElement element;

    private final By callButton = By.cssSelector(".call");

    private final By txLocator = By.cssSelector(".output .result");

    private List<Transaction> transactions;

    public Method(WebElement we)
    {
        element = we;
    }

    public void execute()
    {
        element.findElement(callButton).click();
        getTransactions();
    }

    public List<Transaction> getTransactions()
    {
        transactions = element.findElements(txLocator).stream().map(we -> new Transaction(we)).collect(Collectors.toList());
        return transactions;
    }

    public Transaction getTransaction(int index)
    {
        return transactions.get(index);
    }
}
