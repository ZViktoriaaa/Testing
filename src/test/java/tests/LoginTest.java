package tests;

import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static enums.ErrorMessage.*;
import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

public class LoginTest extends BaseTest {

    @Step("Корректная авторизация")
    @Test
    public void correctLogin() {
        loginPage.open();
        loginPage.login(withAdminPermission());

        assertTrue(productsPage.pageIsOpen());
        assertEquals(productsPage.getNamePage(), PRODUCTS.getDisplayName(),
                "Name of the page doesn't correspond to the expected");
    }

    @DataProvider()
    public Object[][] loginData() {
        return new Object[][]{
                {withIncorrectPermission(), WRONG_CREDENTIALS.getMessage()},
                {withEmptyLogin(), USERNAME_REQUIRED.getMessage()},
                {withEmptyPassword(), PASSWORD_REQUIRED.getMessage()},
                {withLockedPermission(), LOCKED_OUT.getMessage()},
        };
    }

    @Step("Некорректная авторизация")
    @Test(dataProvider = "loginData")
    public void incorrectLogin(User user, String errorMsg) {
        loginPage.open();
        loginPage.login(user);

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorMessage(), errorMsg);
    }
}
