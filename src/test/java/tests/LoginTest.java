package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static enums.ErrorMessage.*;
import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

@Epic("Авторизация")
@Feature("Вход в систему")
public class LoginTest extends BaseTest {

    @Story("Успешная авторизация")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка авторизации с корректными учётными данныи")
    @Test
    public void correctLogin() {
        loginPage.open();
        loginPage.login(withAdminPermission());

        assertTrue(productsPage.pageIsOpen());
        assertEquals(productsPage.getNamePage(), PRODUCTS.getDisplayName());
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {withIncorrectPermission(), WRONG_CREDENTIALS.getMessage()},
                {withEmptyLogin(), USERNAME_REQUIRED.getMessage()},
                {withEmptyPassword(), PASSWORD_REQUIRED.getMessage()},
                {withLockedPermission(), LOCKED_OUT.getMessage()},
        };
    }

    @Story("некорректная авторизация")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка ошибок при вводе некорректных учетных данных")
    @Test(dataProvider = "loginData")
    public void incorrectLogin(User user, String errorMsg) {
        loginPage.open();
        loginPage.login(user);

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorMessage(), errorMsg);
    }
}
