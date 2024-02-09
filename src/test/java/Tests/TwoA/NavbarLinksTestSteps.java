package Tests.TwoA;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class NavbarLinksTestSteps {
    private final String URL = "https://kariyer.baykartech.com";
    private static WebDriver webDriver;
    private WebElement nav;

    private final By liDropdownSelector = By.cssSelector("li.dropdown");

    @BeforeAll
    public static void setDriver() {
        webDriver = new ChromeDriver();
    }

    @Given("Navbar test icin sayfa acilir")
    public void navbarTestIcinSayfaAcilir() {
        webDriver.manage().window().maximize();
        webDriver.get(URL);
    }

    @And("Navbar görünür durumda")
    public void navbarGorunurDurumda() {
        nav = webDriver.findElement(By.tagName("nav"));
        new WebDriverWait(webDriver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOf(nav));
    }

    @When("Dropdown icindeki baglantilara tikla")
    public void dropdownIcindekiBaglantilaraTikla() {
        for (WebElement liHasDropdownMenu: nav.findElements(liDropdownSelector)) {
            WebElement dropDownMenu = waitUntilDropdownMenuVisible(liHasDropdownMenu);

            List<WebElement> dropDrownMenuItems = dropDownMenu.findElements(By.tagName("a"));
            dropDrownMenuItems.forEach(dropDownMenuItem -> {
                String urlBeforeClick = webDriver.getCurrentUrl();
                click(dropDownMenuItem);
                if (!urlBeforeClick.equals(webDriver.getCurrentUrl()))
                    webDriver.navigate().back();
                waitUntilDropdownMenuVisible(liHasDropdownMenu);
            });
        }
    }

    @When("Linklere tikla")
    public void linklereTikla() {
        for (WebElement a: nav.findElements(By.cssSelector("li a.smooth-menu"))) {
            if (hasDropdownMenu(a))
                continue;

            String urlBeforeClick = webDriver.getCurrentUrl();
            String expected = a.getAttribute("href");
            click(a);
            if (isTargetBlank(a)) {
                String firstTab = webDriver.getWindowHandle();
                String lastTab = webDriver.getWindowHandles().stream().reduce((first, second) -> second).orElse(null);
                webDriver.switchTo().window(lastTab);
                Assertions.assertEquals(webDriver.getCurrentUrl(), expected);
                webDriver.switchTo().window(firstTab);
            } else {
                Assertions.assertEquals(webDriver.getCurrentUrl(), expected);
                if (!urlBeforeClick.equals(webDriver.getCurrentUrl()))
                    webDriver.navigate().back();
            }
        }
    }

    @AfterAll
    public static void cleanUp() {
        webDriver.quit();
    }

    private void click(WebElement webElement) {
        new WebDriverWait(webDriver, Duration.ofSeconds(1)).until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    private WebElement waitUntilDropdownMenuVisible(WebElement webElement) {
        new Actions(webDriver).moveToElement(webElement).perform();
        WebElement dropDownMenu = webElement.findElement(By.cssSelector("ul.dropdown-menu"));
        new WebDriverWait(webDriver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOf(dropDownMenu));
        return dropDownMenu;
    }

    private boolean hasDropdownMenu(WebElement webElement) {
        return webElement.getAttribute("data-toggle") != null;
    }

    private boolean isTargetBlank(WebElement webElement) {
        return webElement.getAttribute("target").equals("_blank");
    }
}
