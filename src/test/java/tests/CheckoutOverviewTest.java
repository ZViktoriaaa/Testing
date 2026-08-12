package tests;

import customer.Customer;
import customer.CustomerFactory;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static enums.ProductNaming.*;
import static enums.TitleNaming.CHECKOUT_COMPLETE;
import static enums.TitleNaming.CHECKOUT_OVERVIEW;
import static org.testng.Assert.assertEquals;
import static user.UserFactory.withAdminPermission;

@Epic("Оформление заказа")
@Feature("Обзор и завершение заказа")
public class CheckoutOverviewTest extends BaseTest {
    List<String> goodsList =
            List.of(BACKPACK.getDisplayName(), BIKE.getDisplayName(), TSHIRT.getDisplayName());

    List<String> pricesList =
            List.of(BACKPACK.getPrice(), BIKE.getPrice(), TSHIRT.getPrice());

    private final Customer customer = CustomerFactory.defaultCustomer();

    @BeforeMethod
    public void openCheckoutOverviewPage() {
        loginPage.open();
        loginPage.login(withAdminPermission());

        for (String goodName : goodsList) {
            productsPage.addToCart(goodName);
        }

        productsPage.switchToCart();
        cartPage.checkout();

        checkoutPage.enterFirstName(customer.getFirstName());
        checkoutPage.enterLastName(customer.getLastName());
        checkoutPage.enterPostalCode(customer.getPostalCode());
        checkoutPage.continueCheckout();
    }

    @Story("Открытие страницы обзора заказа")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка открытия страницы Checkout: Overview")
    @Test
    public void checkoutOverviewPageShouldBeOpened() {
        assertEquals(checkoutOverviewPage.getNamePage(), CHECKOUT_OVERVIEW.getDisplayName());
    }

    @Story("Отображение добавленных товаров")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка отображения добавленных товаров в правильном порядке")
    @Test
    public void addedProductsShouldBeDisplayedInOrder() {
        assertEquals(
                checkoutOverviewPage.getItemsNames(),
                goodsList
        );
    }

    @Story("Проверка цен товаров")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка корректного отображения цен добавленных товаров")
    @Test
    public void addedProductsPricesShouldBeCorrect() {
        assertEquals(
                checkoutOverviewPage.getItemsPrices(),
                pricesList
        );
    }

    @Story("Проверка суммы товаров")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка корректной суммы добавленных товаров")
    @Test
    public void itemTotalPriceShouldBeCorrect() {
        assertEquals(checkoutOverviewPage.getItemTotal(), "Item total: $55.97");
    }

    @Story("Проверка налога")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка корректного расчёта налога")
    @Test
    public void taxShouldBeCorrect() {
        assertEquals(checkoutOverviewPage.getTax(), "Tax: $4.48");
    }

    @Story("Проверка итоговой суммы")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка корректной итоговой суммы заказа")
    @Test
    public void totalPriceShouldBeCorrect() {
        assertEquals(checkoutOverviewPage.getTotalPrice(), "Total: $60.45");
    }

    @Story("Завершение заказа")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Проверка успешного завершения оформления заказа")
    @Test
    public void orderShouldBeCompleted() {
        checkoutOverviewPage.clickFinish();
        assertEquals(checkoutCompletePage.getNamePage(), CHECKOUT_COMPLETE.getDisplayName());
    }

    @Story("Сообщение об успешном заказе")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Проверка отображения сообщения после успешного оформления заказа")
    @Test
    public void successfulOrderMessageShouldBeDisplayed() {
        checkoutOverviewPage.clickFinish();
        assertEquals(checkoutCompletePage.getCompleteMessage(), "Thank you for your order!"
        );
    }
}
