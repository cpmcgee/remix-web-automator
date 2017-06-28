package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by chris on 6/26/17.
 */
public class FilePanel extends RemixBase{

    public static final By handle = By.id("filepanel");

    public static final By newFile = By.cssSelector("#filepanel .newFile i");

    public static final By files = By.cssSelector(".fileexplorer_WY6Dn>li>ul>li>div>span>label");

    public WebDriver driver;

    public FilePanel(WebDriver driver) { super(driver); }

    public List<WebElement> getOpenFileList()
    {
        return findAll(files);
    }

}
