package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.List;

import static enums.ProductNaming.*;
import static enums.TitleNaming.CART;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static user.UserFactory.withAdminPermission;

@Epic("Корзина")
@Feature("Содержимое корзины")
public class CartTest extends BaseTest {
    List<String> goodsList =
            List.of(BACKPACK.getDisplayName(), BIKE.getDisplayName(), TSHIRT.getDisplayName());

    @Story("Проверка добавленных товаров")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка отображения добавленных товаров в корзине")
    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login(withAdminPermission());

        for (String goodName : goodsList) {
            productsPage.addToCart(goodName);
        }
        assertEquals(productsPage.checkCounterValue(), goodsList.size());

        productsPage.switchToCart();
        assertEquals(productsPage.getNamePage(), CART.getDisplayName());
        assertFalse(cartPage.getProductsNames().isEmpty());
        assertEquals(cartPage.getProductsNames().size(), 3);
        assertEquals(cartPage.getProductsNames(), goodsList);
    }
}
