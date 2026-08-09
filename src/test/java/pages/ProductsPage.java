package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {
    private static final String ADD_TO_CART = "//*[text()='%s']//ancestor::div" +
            "[@class='inventory_item']//child::*[text()='Add to cart']";
    private final By counter = dataTest("shopping-cart-badge");
    private final By cartLink = dataTest("shopping-cart-link");
    private final By addToCartBtn = By.xpath(String.format(TEXT_LOCATOR_PATTERN, "Add to cart"));

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Добавляем товар в корзину: {0}")
    public void addToCart(final String goodsName) {
        By goods = By.xpath(String.format(ADD_TO_CART, goodsName));
        driver.findElement(goods).click();
    }

    @Step("Добавляем товар в корзину по номеру: {0}")
    public void addToCart(int number) {
        driver.findElements(addToCartBtn).get(number).click();
    }

    @Step("Получаем значение счетчика корзины")
    public int checkCounterValue() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(counter));
        return Integer.parseInt(driver.findElement(counter).getText());
    }

    @Step("Получаем цвет счётчика корзины")
    public String checkCounterColor() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(counter));
        return driver.findElement(counter).getCssValue("background-color");
    }

    @Step("Переходим в корзину")
    public void switchToCart() {
        driver.findElement(cartLink).click();
    }
}
