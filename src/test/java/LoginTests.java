import apiData.UserMethods;
import apiData.UserRequestBody;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

@DisplayName("Логин пользователя")
public class LoginTests extends BaseTest {
    final MainPage mainPage = new MainPage(driver);
    final LoginPage loginPage = new LoginPage(driver);
    final RegisterPage registerPage = new RegisterPage(driver);
    final ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
    final UserMethods methods = new UserMethods();
    final UserRequestBody user = getRandomUser();

    String accessToken;

    @Before
    public void setUp() {
        ValidatableResponse createUserResponse = methods.create(user);
        accessToken = createUserResponse.extract().path("accessToken");
    }

    @Override
    public void clearUp() {
        methods.delete(accessToken);
        super.clearUp();
    }

    @Test
    @DisplayName("Логин пользователя через кнопку \"Войти в аккаунт\"")
    public void loginUserByLoginToAccountButton() {
        driver.get("https://stellarburgers.nomoreparties.site");
        mainPage.waitLoadingToEnd();
        mainPage.clickLoginToAccountButton();
            loginPage.loginToAccount(user.getEmail(), user.getPassword());
        mainPage.waitMakeTheBurgerTitleToLoad();
        String makeTheBurgerTitleText = mainPage.getMakeTheBurgerTitleText();
        Assert.assertEquals("Соберите бургер", makeTheBurgerTitleText);
    }

    @Test
    @DisplayName("Логин пользователя через кнопку \"Личный Кабинет\"")
    public void loginUserByPersonalAccountButton() {
        driver.get("https://stellarburgers.nomoreparties.site");
        mainPage.waitLoadingToEnd();
        mainPage.clickPersonalAccountButton();
        loginPage.loginToAccount(user.getEmail(), user.getPassword());
        mainPage.waitMakeTheBurgerTitleToLoad();
        String makeTheBurgerTitleText = mainPage.getMakeTheBurgerTitleText();
        Assert.assertEquals("Соберите бургер", makeTheBurgerTitleText);
    }

    @Test
    @DisplayName("Логин пользователя через кнопку \"Зарегистрироваться\"")
    public void loginUserByRegisterButton() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        registerPage.waitLoadingToEnd();
        registerPage.clickLoginButton();
        loginPage.loginToAccount(user.getEmail(), user.getPassword());
        mainPage.waitMakeTheBurgerTitleToLoad();
        String makeTheBurgerTitleText = mainPage.getMakeTheBurgerTitleText();
        Assert.assertEquals("Соберите бургер", makeTheBurgerTitleText);
    }

    @Test
    @DisplayName("Логин пользователя через кнопку \"Восстановить пароль\"")
    public void loginUserByForgotPasswordButton() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        forgotPasswordPage.waitLoadingToEnd();
        forgotPasswordPage.clickLoginButton();
        loginPage.loginToAccount(user.getEmail(), user.getPassword());
        mainPage.waitMakeTheBurgerTitleToLoad();
        String makeTheBurgerTitleText = mainPage.getMakeTheBurgerTitleText();
        Assert.assertEquals("Соберите бургер", makeTheBurgerTitleText);
    }
}