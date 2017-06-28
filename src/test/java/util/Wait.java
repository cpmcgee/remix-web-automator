package util;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by chris on 6/28/17.
 */
public class Wait {

    //SANDBOX CLASS FOR EXPERIMENTATION - NOT USED YET

    public static WebElement find(SearchContext in, Function<SearchContext, WebElement> action, int timeoutSeconds)
    {
        return new FluentWait<SearchContext>(in)
                .withTimeout(timeoutSeconds, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(Exception.class)
                .until(action);
    }

    public static List<WebElement> findAll(SearchContext in, Function<SearchContext, List<WebElement>> action, int timeoutSeconds)
    {
        return new FluentWait<SearchContext>(in)
                .withTimeout(timeoutSeconds, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(Exception.class)
                .until(action);
    }

    public static <T> void until(T in, Predicate<T> pred, int timeoutSeconds)
    {
        long start = System.currentTimeMillis();
        while ((System.currentTimeMillis() - start) < (timeoutSeconds*1000))
        {
            try
            {
                if (pred.test(in))
                    return;
            }
            catch (Exception ex)
            {
                System.out.println("Exception caught in wait\n" + ex.getMessage());
            }
        }
        throw new TimeoutException("Predicate never evaluated true");
    }
}
