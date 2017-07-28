package util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.*;

/**
 * Created by chris on 6/27/17.
 */
public final class LOG {

    public static String path;

    private static String file;

    private static BufferedWriter writer;

    public static void init() {
        try {
            path = Configuration.getLogDirectory();
            file = path + "log.txt";
            writer = new BufferedWriter(new FileWriter(file));
        }
        catch(Exception ex)
        {
            System.out.println("Failed to create logger");
        }
    }

    public static void info(String entry){
        String msg = "\n[INFO] " + entry;
        System.out.println(msg);
        try {
            writer.write(msg);
        }
        catch(IOException ex) {
            System.out.println("logger error");
        }
    }

    public static void error(String entry){
        String msg = "\n[ERROR] " + entry;
        System.out.println(msg);
        try {
            writer.write(msg);
        }
        catch(IOException ex) {
            System.out.println("logger error");
        }
    }

    public static void screenshot()
    {
        int ct = 1;
        WebDriver currentDriver = DriverFactory.getDriver();
        File scrShot = ((TakesScreenshot)currentDriver).getScreenshotAs(OutputType.FILE);
        String fileName = path + "screenshot";
        try {
            while (new File(fileName + ct).exists()) {
                ct++;
            }
            FileUtils.copyFile(scrShot, new File(fileName + ct));
        }
        catch(IOException ex)
        {
            System.out.println("screenshot error");
        }
    }
}
