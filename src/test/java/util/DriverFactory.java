package util;

import java.util.concurrent.atomic.AtomicInteger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by chris on 6/26/17.
 */
public final class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private static volatile AtomicInteger driverCount = new AtomicInteger(0);

    private static volatile Queue<WebDriver> warehouse = new ArrayDeque<WebDriver>();

    static { Runtime.getRuntime().addShutdownHook(new Thread(DriverFactory::shutdown)); }

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

            synchronized (driverCount) {
                LOG.info("Produced driver " + driverCount.incrementAndGet());
                warehouse.add(driver.get());
            }
            return driver.get();
        }
        catch(Exception ex)
        {
            LOG.info("EXCEPTION CAUGHT CREATING DRIVER:\n " );
            ex.printStackTrace();
            return null;
        }
    }

    private static void shutdown()
    {
        for (WebDriver d : warehouse)
        {
            LOG.info("Shutting down driver " + warehouse.size());
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
