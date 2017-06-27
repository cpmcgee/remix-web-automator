package util;

import com.sun.jmx.remote.internal.ArrayQueue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by chris on 6/26/17.
 */
public final class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private static int driverCount = 0;

    private static Queue<WebDriver> warehouse = new ArrayDeque<WebDriver>();

    static {Runtime.getRuntime().addShutdownHook(new Thread(DriverFactory::shutdown)); }

    public static WebDriver getDriver()
    {
        DesiredCapabilities caps;
        Configuration.load();
        try {
            if (driver.get() != null) { return driver.get(); }

            switch (Configuration.getBrowserType()) {
                case Configuration.CHROME:
                    caps = DesiredCapabilities.chrome();
                    caps.setBrowserName("chrome");
                    driver.set((Configuration.getGridEnabled() == "true") ?
                            new RemoteWebDriver(new URL(Configuration.getHubUrl()), caps) :
                            new ChromeDriver(caps));
                    break;
                case Configuration.FIREFOX:
                    caps = DesiredCapabilities.firefox();
                    caps.setBrowserName("firefox");
                    driver.set((Configuration.getGridEnabled() == "true") ?
                            new RemoteWebDriver(new URL(Configuration.getHubUrl()), caps) :
                            new FirefoxDriver(caps));
                    break;
                default:
                    throw new Exception("DRIVER TOO DRUNK TO DRIVE");
            }
            driverCount++;
            LOGGS.info("Produced driver " + driverCount);
            warehouse.add(driver.get());
            return driver.get();
        }
        catch(Exception ex)
        {
            LOGGS.info("EXCEPTION CAUGHT CREATING DRIVER:\n " );
            ex.printStackTrace();
            return null;
        }
    }

    private static void shutdown()
    {
        int count = 0;
        for (WebDriver d : warehouse)
        {
            LOGGS.info("Shutting down driver " + ++count);
            warehouse.remove().quit();
        }
    }

    public static class Driver
    {
        public static WebDriver get()
        {
            return getDriver();
        }

        public static void load(String url)
        {
            get().get(url);
        }
    }
}
