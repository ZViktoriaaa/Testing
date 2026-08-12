package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final By errorMessage = dataTest("error");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Step("Вводим имя: {0}")
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    @Step("Вводим фамилию: {0}")
    public void enterLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    @Step("Вводим ZIP-код: {0}")
    public void enterPostalCode(String postalCode) {
        driver.findElement(postalCodeInput).sendKeys(postalCode);
    }

    @Step("Получаем сообщение об ошибке")
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    @Step("Переходим к подтверждению заказа")
    public void continueCheckout() {
        driver.findElement(continueBtn).click();
    }
}
