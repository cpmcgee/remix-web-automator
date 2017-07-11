import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by chris on 7/10/17.
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = "json:target/cucumber.json",
                 features = "src/test/resources/features")
public class RunTestsNoParallel {
}
