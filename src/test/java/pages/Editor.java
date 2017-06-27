package pages;

import org.openqa.selenium.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by chris on 6/26/17.
 */
public class Editor extends RemixBase {

    public static final By handle = By.id("editor");

    public static final By input = By.cssSelector("#input .ace_text-input");

    public FilePanel filePanel;

    public Editor(WebDriver driver) {
        super(driver);
        filePanel = new FilePanel(driver);
    }

    public void pasteFromFile(String fileName) throws FileNotFoundException
    {
        //((JavascriptExecutor)driver).executeScript("ace.setValue('')");
        Scanner s = new Scanner(new File(contractsPath + fileName));
        WebElement textBox = find(input);
        String handle = driver.getWindowHandle();
        while (s.hasNextLine())
        {
            //((JavascriptExecutor)driver).executeScript("ace.insert('"+s.next()+"')");
            textBox.sendKeys(s.nextLine());
        }
    }

    public void newTab()
    {
        click(filePanel.newFile);
    }

    public void clickTab(int index)
    {
        click(tabAt(index));
    }

    public void closeTab(int index)
    {
        clickTab(index);
        closeActiveTab();
    }

    public void closeActiveTab()
    {
        click(By.cssSelector("#files .file.active span i"));
        try{
            driver.switchTo().alert().accept();
        }
        catch(Exception ex){}
    }

    public void renameTab(int index, String name)
    {
        WebElement tab = tabAt(index);
        if (!tab.getAttribute("class").contains("active")) {
            click(tab);
            tab = tabAt(index);
        }
        click(tab);

        executeScript("arguments[0].setAttribute('value', '" + name + "')", tab.findElement(By.tagName("input")));
        String handle = driver.getWindowHandle();
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        driver.switchTo().alert().accept();
        driver.switchTo().window(handle);
        //tab.findElement(By.tagName("input")).sendKeys(name);
    }

    public WebElement tabAt(int index)
    {
        return find(By.cssSelector("#files li:nth-child(" + index + ")"));
    }
}
