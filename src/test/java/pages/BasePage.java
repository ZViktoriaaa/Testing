package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

import java.time.Duration;

public class BasePage {
    protected final By pageName = dataTest("title");
    public static final String BASE_URL = PropertyReader.getProperty("saucedemo.url");
    public static final String TEXT_LOCATOR_PATTERN = "//*[text()='%s']";

    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Открываем страницу")
    public boolean pageIsOpen() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageName));
        return driver.findElement(pageName).isDisplayed();
    }

    protected By dataTest(String value) {
        return By.cssSelector(String.format("[data-test='%s']", value));
    }

    @Step("Получаем название страницы")
    public String getNamePage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageName));
        return driver.findElement(pageName).getText();
    }
}
