package Helpers;

import Models.Position;
import Models.WebDriverManager;
import org.openqa.selenium.By;

import java.util.List;

public class PositionHelper {

    private static final By positionSelector = By.className("liProgram");
    private static final By positionNameSelector = By.cssSelector("div.position-head a");
    private static final By positionUnitNameSelector = By.cssSelector("div.position-category");

    public static List<Position> getCurrentPositions(WebDriverManager browser) {
        return browser.findElements(positionSelector).stream().map(positionElement -> {
            String positionName = positionElement.findElement(positionNameSelector).getText();
            String positionUnitName = positionElement.findElement(positionUnitNameSelector).getText();
            return new Position(positionName, positionUnitName);
        }).toList();
    }

    public static List<String> getPositionNames(List<Position> positions) {
        return positions.stream().map(Position::getName).toList();
    }
}
