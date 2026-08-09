package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CheckoutOverviewPage extends BasePage {
    private final By itemName = By.className("inventory_item_name");
    private final By itemPrice = By.className("inventory_item_price");
    private final By finishBtn = By.id("finish");
    private final By totalPrice = By.className("summary_total_label");
    private final By itemTotal = By.className("summary_subtotal_label");
    private final By tax = By.className("summary_tax_label");


    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получаем список названий товаров")
    public ArrayList<String> getItemsNames() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemName));

        List<WebElement> allItemsNames = driver.findElements(itemName);
        ArrayList<String> names = new ArrayList<>();

        for (WebElement item : allItemsNames) {
            names.add(item.getText());
        }

        return names;
    }

    @Step("Получаем список цен товаров")
    public ArrayList<String> getItemsPrices() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemPrice));

        List<WebElement> allItemsPrices = driver.findElements(itemPrice);
        ArrayList<String> prices = new ArrayList<>();

        for (WebElement item : allItemsPrices) {
            prices.add(item.getText());
        }

        return prices;
    }

    @Step("Получаем сумму товаров")
    public String getItemTotal() {
        return driver.findElement(itemTotal).getText();
    }

    @Step("Получаем сумму налога")
    public String getTax() {
        return driver.findElement(tax).getText();
    }

    @Step("Получаем итоговую сумму заказа")
    public String getTotalPrice() {
        return driver.findElement(totalPrice).getText();
    }

    @Step("Завершаем оформление заказа")
    public void clickFinish() {
        driver.findElement(finishBtn).click();
    }
}
