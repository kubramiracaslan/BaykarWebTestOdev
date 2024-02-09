package Helpers;

import Models.Unit;
import Models.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UnitHelper {

    private static final By unitCheckBoxSelector = By.cssSelector("ul#myUL2 input");

    public static List<Unit> getCurrentUnits(WebDriverManager browser) {
        return browser.findElements(unitCheckBoxSelector).stream().filter(WebElement::isDisplayed).map(unitCheckBox-> {
            String unitName = unitCheckBox.getAttribute("value").toUpperCase();
            return new Unit(unitName);
        }).toList();
    }

    public static List<String> getUnitNames(List<Unit> units) {
        return units.stream().map(Unit::getName).toList();
    }
}
