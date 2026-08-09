package tests;

import customer.Customer;
import customer.CustomerFactory;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static enums.ErrorMessage.*;
import static enums.ProductNaming.*;
import static enums.TitleNaming.CHECKOUT;
import static enums.TitleNaming.CHECKOUT_OVERVIEW;
import static org.testng.Assert.assertEquals;
import static user.UserFactory.withAdminPermission;

@Epic("Оформление заказа")
@Feature("Данные покупателя")
public class CheckoutTest extends BaseTest {
    List<String> goodsList =
            List.of(BACKPACK.getDisplayName(), BIKE.getDisplayName(), TSHIRT.getDisplayName());

    private final Customer customer = CustomerFactory.defaultCustomer();

    @BeforeMethod
    public void openCheckoutPage() {
        loginPage.open();
        loginPage.login(withAdminPermission());

        for (String goodName : goodsList) {
            productsPage.addToCart(goodName);
        }
        productsPage.switchToCart();
        cartPage.checkout();
    }

    @Story("Открытие страницы оформления заказы")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка открытия страницы ввода данных покупателя")
    @Test
    public void checkoutInformationCanBeOpened() {
        assertEquals(checkoutPage.getNamePage(), CHECKOUT.getDisplayName());
    }

    @Story("Заполнение данных покупателя")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка заполнения имени, фамилии и zip-кода покупатель")
    @Test
    public void checkoutInformationCanBeEntered() {
        checkoutPage.enterFirstName(customer.getFirstName());
        checkoutPage.enterLastName(customer.getLastName());
        checkoutPage.enterPostalCode(customer.getPostalCode());
        checkoutPage.continueCheckout();

        assertEquals(
                checkoutOverviewPage.getNamePage(),
                CHECKOUT_OVERVIEW.getDisplayName()
        );
    }

    @Story("Оставление пустого поля имени")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка ошибки при незаполненном поле имени")
    @Test
    public void firstNameIsRequired() {
        checkoutPage.enterLastName(customer.getLastName());
        checkoutPage.enterPostalCode(customer.getPostalCode());
        checkoutPage.continueCheckout();

        assertEquals(
                checkoutPage.getErrorMessage(),
                FIRST_NAME_REQUIRED.getMessage()
        );
    }

    @Story("Оставление пустого поля фамилии")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка ошибки при незаполненном поле фамилии")
    @Test
    public void lastNameIsRequired() {
        checkoutPage.enterFirstName(customer.getFirstName());
        checkoutPage.enterPostalCode(customer.getPostalCode());
        checkoutPage.continueCheckout();

        assertEquals(
                checkoutPage.getErrorMessage(),
                LAST_NAME_REQUIRED.getMessage()
        );
    }

    @Story("Оставление пустого поля zip-кода")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка ошибки при незаполненном поле zip-кода")
    @Test
    public void postalCodeIsRequired() {
        checkoutPage.enterFirstName(customer.getFirstName());
        checkoutPage.enterLastName(customer.getLastName());
        checkoutPage.continueCheckout();

        assertEquals(
                checkoutPage.getErrorMessage(),
                POSTAL_CODE_REQUIRED.getMessage()
        );
    }
}
