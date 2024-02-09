package Tests.TwoB;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class LanguageTestSteps {
    private final static String URL = "https://kariyer.baykartech.com";
    private final static WebDriver webDriver = new ChromeDriver();

    private final By languageButtonSelector = By.cssSelector("nav.navbar .attr-nav li.siteCustomLi a");
    private final By openPositionsButtonSelector = By.cssSelector("div.wrapper a button.fix-btn");

    private String firstLanguage;

    Map<String, String> sectionHeadersMap = new HashMap<>() {{
        put("personal", "#personal .site-heading h2");
        put("campuses", "#campuses .site-heading h2");
        put("services", "#services .site-heading h2 strong");
    }};

    Map<String, String> dictionary = new HashMap<>(){{
        put("campuses_EN_header", "OUR CAMPUSES");
        put("campuses_TR_header", "YERLEŞKELERİMİZ");
        put("personal_EN_header", "STATS");
        put("personal_TR_header", "İSTATİSTİKLER");
        put("services_EN_header", "OPEN POSITIONS");
        put("services_TR_header", "AÇIK POZİSYONLAR");
        put("openPositionButton_EN_Text", "OPEN POSITIONS");
        put("openPositionButton_TR_Text", "AÇIK POZİSYONLAR");
    }};

    @Given("Dil testi icin sayfa acik")
    public void dilTestiIcinSayfaAcik() {
        webDriver.manage().window().maximize();
        webDriver.get(URL);
    }

    @And("Dil butonu tiklanabilir durumda")
    public void dilButonuTiklanabilirDurumda() {
        new WebDriverWait(webDriver, Duration.ofSeconds(1))
                .until(ExpectedConditions.elementToBeClickable(languageButtonSelector));
    }

    @When("Dil durumu bilgisi alinir")
    public void dilDurumuBilgisiAlinir() {
        firstLanguage = getCurrentLanguage();
    }

    @And("Dil butonuna tiklanir")
    public void dilButonunaTiklanir() {
        WebElement languageButtonWebElement = getLanguageButton();
        Assertions.assertTrue(languageButtonWebElement.isDisplayed() && languageButtonWebElement.isEnabled());
        languageButtonWebElement.click();
    }

    @Then("Dil degisimi kontrol edilir")
    public void dilDegisimiKontrolEdilir() {
        String actualLanguage = getCurrentLanguage();
        Assertions.assertNotEquals(firstLanguage, actualLanguage);
    }

    @And("Dil butonu yazi dili kontrol edilir")
    public void dilButonuYaziDiliKontrolEdilir() {
        String actualLanguageButtonText = getLanguageButton().getText();
        Assertions.assertEquals(firstLanguage, actualLanguageButtonText);
    }

    @And("AcikPosizyonlar buton yazi dili kontrol edilir")
    public void acikposizyonlarButonYaziDiliKontrolEdilir() {
        String expected = dictionary.get("openPositionButton_" + getCurrentLanguage() + "_Text");
        WebElement openPositionsButton = webDriver.findElement(openPositionsButtonSelector);
        String actual = openPositionsButton.getText();
        Assertions.assertEquals(expected, actual);
    }

    @And("Anabasliklarin dile gore degisimi kontrol edilir")
    public void anabasliklarinDileGoreDegisimiKontrolEdilir() {
        for (Map.Entry<String, String> section : sectionHeadersMap.entrySet()) {
            String expectedSectionHeader = dictionary.get(section.getKey() + "_" + getCurrentLanguage() + "_header");
            String actualSectionHeader = webDriver.findElement(By.cssSelector(section.getValue())).getText();
            Assertions.assertEquals(expectedSectionHeader.toUpperCase(), actualSectionHeader.toUpperCase());
        }
    }

    @AfterAll
    public static void cleanUp() {
        webDriver.quit();
    }

    private WebElement getLanguageButton() {
        return webDriver.findElement(languageButtonSelector);
    }

    private String getCurrentLanguage() {
        // TODO: html > lang her zaman "en", dil degisse bile
        return webDriver.getCurrentUrl().split("/")[3].toUpperCase();
    }
}
