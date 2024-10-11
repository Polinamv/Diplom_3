import apiData.UserCredentials;
import apiData.UserMethods;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.RegisterPage;

@DisplayName("Регистрация пользователя")
public class RegistrationTests extends BaseTest {
    final RegisterPage registerPage = new RegisterPage(driver);
    final LoginPage loginPage = new LoginPage(driver);
    final String email = "paa@login.ru";
    final String password = "123456";
    final String name = "Polina";

    @Before
    public void setup() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void registerUserSuccess() {
        registerPage.setUpUserDataAndClickRegister(name, email, password);
        loginPage.waitLoginTitleToLoad();
        String titleText = loginPage.getTitleText();
        Assert.assertEquals("Вход", titleText);
        deleteUser();
    }

    @Test
    @DisplayName("Регистрация пользователя с некорректным паролем")
    public void registerUserFail() {
        registerPage.setUpUserDataAndClickRegister(name, email, "54321");
        registerPage.waitErrorMessageToLoad();
        String errorMessage = registerPage.getErrorMessageText();
        Assert.assertEquals("Некорректный пароль", errorMessage);
    }

    private void deleteUser() {
        UserCredentials credentials = new UserCredentials(email, password);
        UserMethods methods = new UserMethods();
        ValidatableResponse loginUserResponse = methods.login(credentials);
        String accessToken = loginUserResponse.extract().path("accessToken");
        methods.delete(accessToken);
    }
}