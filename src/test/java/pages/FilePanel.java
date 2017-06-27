package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by chris on 6/26/17.
 */
public class FilePanel{

    public static final By handle = By.id("filepanel");

    public static final By newFile = By.cssSelector("#filepanel .newFile i");

    public WebDriver driver;

    public FilePanel(WebDriver driver) { this.driver = driver; }

}
