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

    private final By callArgs = By.cssSelector(".contractProperty input");

    private List<Transaction> transactions;

    public Method(WebElement we)
    {
        element = we;
    }

    public String getName()
    {
        return element.findElement(callButton).getAttribute("title");
    }

    public Transaction execute()
    {
        element.findElement(callButton).click();
        getTransactions();
        return transactions.get(transactions.size() - 1);
    }

    public Transaction execute(String args)
    {
        element.findElement(callArgs).clear();
        element.findElement(callArgs).sendKeys(args);
        return execute();
    }

    public Transaction execute(double args)
    {
        element.findElement(callArgs).clear();
        element.findElement(callArgs).sendKeys(Double.toString(args));
        return execute();
    }

    public List<Transaction> getTransactions()
    {
        transactions = element.findElements(txLocator).stream().map(we -> new Transaction(we)).collect(Collectors.toList());
        //for (WebElement we : element.findElements(txLocator))
        //{
        //    transactions.add(new Transaction(we));
        //}
        return transactions;
    }

    public Transaction getTransaction(int index)
    {
        return transactions.get(index);
    }
}
