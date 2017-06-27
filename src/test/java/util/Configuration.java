package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.lang.System.getProperty;

/**
 * Created by chris on 6/26/17.
 */
public final class Configuration {

    private static String path = "/home/chris/IdeaProjects/basicframework/src/config.txt";

    private static Properties props = new Properties();

    public static final String FIREFOX = "firefox";
    public static final String CHROME = "chrome";
    public static final String APPIUM = "appium";

    public static void load()
    {
        try {
            props.load(new FileInputStream(path));
        }
        catch(IOException ex) {
            System.out.println("Error opening util file!");
        }

        System.setProperty("webdriver.gecko.driver", getFirefoxPath());
        System.setProperty("webdriver.chrome.driver", getChromePath());
        System.setProperty("hub.url", getHubUrl());
        //System.setProperty("contracts.directory", getContractsDirectory());
        //System.setProperty("log.directory", getLogDirectory());
        System.out.println(getGridEnabled());
    }

    public static String getContractsDirectory() { return props.getProperty("contracts.directory"); }

    public static String getBrowserType()
    {
        return props.getProperty("browser.type");
    }

    public static String getGridEnabled() { return props.getProperty("enable.grid"); }

    public static String getFirefoxPath()
    {
        return props.getProperty("firefox.driver");
    }

    public static String getChromePath()
    {
        return props.getProperty("chrome.driver");
    }

    public static String getHubUrl()
    {
        return props.getProperty("hub.url");
    }

    public static String getLogDirectory() { return props.getProperty("log.directory"); }
}
