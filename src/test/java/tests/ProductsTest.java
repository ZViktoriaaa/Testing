package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.List;

import static enums.ProductNaming.*;
import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static user.UserFactory.withAdminPermission;

@Epic("Каталог товаров")
@Feature("Добавление товаров к корзину")
public class ProductsTest extends BaseTest {
    List<String> goodsList =
            List.of(BACKPACK.getDisplayName(), BIKE.getDisplayName(), TSHIRT.getDisplayName());


    @Story("Добавление товаров в корзину")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка добавления товаров в корзину и корректного отображения счётчика")
    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        assertEquals(productsPage.getNamePage(), PRODUCTS.getDisplayName());

        for (String goodName : goodsList) {
            productsPage.addToCart(goodName);
        }

        assertEquals(productsPage.checkCounterValue(), 3);

        productsPage.addToCart(2);
        assertEquals(productsPage.checkCounterValue(), 4);
        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)");
    }
}
