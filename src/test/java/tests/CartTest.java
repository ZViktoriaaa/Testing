package tests;

import io.qameta.allure.Story;
import org.testng.annotations.Test;

import java.util.List;

import static enums.ProductNaming.*;
import static enums.TitleNaming.CART;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static user.UserFactory.withAdminPermission;

public class CartTest extends BaseTest {
    List<String> goodsList =
            List.of(BACKPACK.getDisplayName(), BIKE.getDisplayName(), TSHIRT.getDisplayName());

    @Story("Проверяем содержимое корзины")
    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login(withAdminPermission());

        for (String goodName : goodsList) {
            productsPage.addToCart(goodName);
        }
        productsPage.switchToCart();
        assertEquals(productsPage.getNamePage(), CART.getDisplayName());
        assertFalse(cartPage.getProductsNames().isEmpty());
        assertEquals(cartPage.getProductsNames().size(), 3);
    }
}
