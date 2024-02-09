package Tests.TwoC;

import Helpers.UnitHelper;
import Models.Unit;
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

public class UnitSearchTestSteps {
    private final static String URL = "https://kariyer.baykartech.com/tr/basvuru/acik-pozisyonlar/";
    private final WebDriverManager browser = new WebDriverManager(new ChromeDriver(), URL);
    private final By unitSearchBoxSelector = By.id("myInput2");
    private List<Unit> units = new ArrayList<>();


    @Given("Acik pozisyonlar sayfasindayim")
    public void acikPozisyonlarSayfasindayim() {
        browser.open();
        units = UnitHelper.getCurrentUnits(browser);
    }

    @When("{string} birim arama kutusuna yazar")
    public void kelimesiniBirimAramaKutusunaYazar(String searchKey) {
        WebElement unitSearchBox = browser.findElement(unitSearchBoxSelector);
        unitSearchBox.sendKeys(searchKey);
    }

    @Then("icinde {string} iceren birimler listelenir")
    public void icindeKelimesiniIcerenBirimlerListelenir(String searchKey) {
        List<String> expectedUnits = UnitHelper.getUnitNames(filterUnitsBySearchKey(searchKey));
        List<String> actualUnits = UnitHelper.getUnitNames(UnitHelper.getCurrentUnits(browser));
        Assertions.assertEquals(expectedUnits, actualUnits);
    }

    private List<Unit> filterUnitsBySearchKey(String searchKey) {
        return units.stream().filter(unit -> unit.getName().contains(searchKey.toUpperCase())).toList();
    }

    @After()
    public void closeBrowser() {
        browser.quit();
    }
}
