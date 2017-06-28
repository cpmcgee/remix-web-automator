package pages;

import org.openqa.selenium.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by chris on 6/26/17.
 */
public class Editor extends RemixBase {

    public static final By handle = By.id("editor");

    public static final By input = By.cssSelector("#input .ace_text-input");

    public static final By aceContent = By.cssSelector("#input .ace_content");

    public FilePanel filePanel;

    public Editor(WebDriver driver) {
        super(driver);
        filePanel = new FilePanel(driver);
    }

    public void clearEditor()
    {
        for (WebElement we : find(aceContent).findElements(By.tagName("span")))
        {
            try {
                we.clear();
            }
            catch (Exception ex)
            {
                executeScript("arguments[0].innerText=''", we);
            }
        }
    }

    public void pasteFromFile(String fileName) throws FileNotFoundException
    {
        Scanner s = new Scanner(new File(contractsPath + fileName));
        WebElement textBox = find(input);
        while (s.hasNextLine())
        {
            String nextLine = massage(s.nextLine());
            if (nextLine.equals("}"))
            {
                textBox.sendKeys(Keys.ARROW_DOWN);
                textBox.sendKeys(Keys.ENTER);
            }
            else
            {
                textBox.sendKeys(nextLine);
                textBox.sendKeys(Keys.ENTER);
            }
        }
    }

    private String massage(String s)
    {
        return s.replace("\t", "");
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

    public void renameTab(String oldName, String newName)
    {
        List<WebElement> files = filePanel.getOpenFileList();
        for (WebElement we : files)
        {
            if (we.getAttribute("innerText").equals(oldName))
            {
                we.click();
                we.click();
                executeScript("arguments[0].innerText='"+newName+"'", we);
                we.sendKeys(Keys.ENTER);
                Alert.dismiss();
            }
        }
    }

    public WebElement tabAt(int index)
    {
        return find(By.cssSelector("#files li:nth-child(" + index + ")"));
    }
}
