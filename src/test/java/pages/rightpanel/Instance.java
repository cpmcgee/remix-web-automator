package pages.rightpanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chris on 6/30/17.
 */
public class Instance {
    private WebElement element;

    private ContractTab tab;

    private List<Method> methods;

    private List<Attribute> attributes;

    private final By attributesLocator = By.cssSelector(".instance .contractProperty.constant");

    private final By methodsLocator = By.cssSelector(".instance .contractProperty");

    public Instance(WebElement rootElement, ContractTab ct) {
        tab = ct;
        element = rootElement;
        methods = getMethods();
        attributes = getAttributes();
    }

    public Attribute getAttribute(String name)
    {
        List<Attribute> atbs = getAttributes();
        for (Attribute a : atbs)
        {
            String n = a.getName();
            if (n.equalsIgnoreCase(name))
            {
                return a;
            }
        }
        return null;
    }

    public List<Attribute> getAttributes() {
        return tab.findAll(attributesLocator)
                .stream()
                .map(we -> new Attribute(we))
                .collect(Collectors.toList());
    }

    public Method getMethod(String name)
    {
        List<Method> mds = getMethods();
        for (Method m : mds)
        {
            String n = m.getName();
            if (n.equalsIgnoreCase(name))
            {
                return m;
            }
        }
        return null;
    }

    public List<Method> getMethods()
    {
        return tab.findAll(methodsLocator)
                .stream()
                .map(we -> new Method(we))
                .collect(Collectors.toList());
    }

}
