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

public class PositionSearchTestSteps {
    private final static String URL = "https://kariyer.baykartech.com/tr/basvuru/acik-pozisyonlar/";
    private final WebDriverManager browser = new WebDriverManager(new ChromeDriver(), URL);

    private final By positionSearchBoxSelector = By.id("myInput");

    List<Position> positions = new ArrayList<>();
    String searchText = "";

    @Given("Acik pozisyonlar sayfasindayim_")
    public void acikPozisyonlarSayfasindayim() {
        browser.open();
        positions = PositionHelper.getCurrentPositions(browser);
    }

    @When("{string} kelimesini pozisyon arama kutusuna yazar")
    public void kelimesiniPozisyonAramaKutusunaYazar(String searchKeyText) {
        WebElement positionSearchInput = browser.findElement(positionSearchBoxSelector);
        searchText = searchKeyText;
        positionSearchInput.sendKeys(searchKeyText);
    }

    @Then("Basligi icinde aranan kelime iceren pozisyonlar listelenir")
    public void icindeKelimesiniIcerenPozisyonlarListelenir() {
        List<String> actualPositions = PositionHelper.getPositionNames(PositionHelper.getCurrentPositions(browser));
        List<String> expectedPositions = PositionHelper.getPositionNames(filterPositionsBySearchKey(searchText));
        Assertions.assertEquals(expectedPositions, actualPositions);
    }

    @After()
    public void closeBrowser() {
        browser.quit();
    }

    private List<Position> filterPositionsBySearchKey(String searchKey) {
        return positions.stream().filter(position -> position.getName().contains(searchKey.toUpperCase())).toList();
    }
}
