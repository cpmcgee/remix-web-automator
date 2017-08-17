package util;
import org.junit.rules.MethodRule;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.FileOutputStream;
import org.junit.Rule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * Created by chris on 8/17/17.
 */
public class ScreenshotRule implements MethodRule {
    private WebDriver driver;

    public ScreenshotRule(WebDriver driver) {
        this.driver = driver;
    }

    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object object) {
        return new Statement(){
            @Override
            public void evaluate() throws Throwable{
                try{
                    statement.evaluate();
                }
                catch(Throwable t) {
                    LOG.screenshot();
                }
            }
        };
    }
}
