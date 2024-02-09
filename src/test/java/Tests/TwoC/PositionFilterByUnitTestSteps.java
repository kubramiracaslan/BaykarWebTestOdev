package Tests.TwoC;

import Helpers.PositionHelper;
import Models.Position;
import Models.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class PositionFilterByUnitTestSteps {
    private final static String URL = "https://kariyer.baykartech.com/tr/basvuru/acik-pozisyonlar/";
    private final WebDriverManager browser = new WebDriverManager(new ChromeDriver(), URL);
    private final By unitCheckboxSelector = By.cssSelector("ul#myUL2 input");
    private String unitFilter = "";
    private List<Position> positions = new ArrayList<>();

    @Given("Acik pozisyonlar sayfasindayim__")
    public void acikPozisyonlarSayfasindayim() {
        browser.open();
        positions = PositionHelper.getCurrentPositions(browser);
    }

    @When("herhangi bir {string} secer")
    public void herhangiBirSecer(String selectedUnitName) {
        List<WebElement> unitCheckboxes = browser.findElements(unitCheckboxSelector);
        for (WebElement unitCheckbox: unitCheckboxes) {
            String unitName = unitCheckbox.getAttribute("value");
            if (unitName.equals(selectedUnitName)) {
                unitFilter = selectedUnitName;
                unitCheckbox.click();
                break;
            }
        }
    }

    @Then("secilen birimleri iceren pozisyonlar listelenir")
    public void secilenBirimleriIcerenPozisyonlarListelenir() {
        List<String> expectedPositions = PositionHelper.getPositionNames(PositionHelper.getCurrentPositions(browser));
        List<String> actualPositions = PositionHelper.getPositionNames(filterPositionsBy(unitFilter));
        Assertions.assertEquals(expectedPositions, actualPositions);
    }

    @After()
    public void closeBrowser() {
        browser.quit();
    }

    private List<Position> filterPositionsBy(String unitName) {
        return positions.stream().filter(position -> position.getUnit().getName().equals(unitName.toUpperCase())).toList();
    }
}
