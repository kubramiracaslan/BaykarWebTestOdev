package Models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class WebDriverManager {
    private final RemoteWebDriver driver;
    private final String URL;

    public WebDriverManager(RemoteWebDriver remoteWebDriver, String URI) {
        driver = remoteWebDriver;
        URL = URI;
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

    public void open() {
        driver.manage().window().maximize();
        driver.get(URL);
    }

    public void quit() {
        if (driver != null)
            driver.quit();
    }

    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }
}
