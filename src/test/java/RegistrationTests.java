import apiData.UserCredentials;
import apiData.UserMethods;
import apiData.UserRequestBody;
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
    final UserRequestBody user = getRandomUser();

    @Before
    public void setup() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void registerUserSuccess() {
        registerPage.setUpUserDataAndClickRegister(user.getName(), user.getEmail(), user.getPassword());
        loginPage.waitLoginTitleToLoad();
        String titleText = loginPage.getTitleText();
        Assert.assertEquals("Вход", titleText);
        deleteUser();
    }

    @Test
    @DisplayName("Регистрация пользователя с некорректным паролем")
    public void registerUserFail() {
        registerPage.setUpUserDataAndClickRegister(user.getName(), user.getEmail(), "54321");
        registerPage.waitErrorMessageToLoad();
        String errorMessage = registerPage.getErrorMessageText();
        Assert.assertEquals("Некорректный пароль", errorMessage);
    }

    private void deleteUser() {
        UserCredentials credentials = new UserCredentials(user.getEmail(), user.getPassword());
        UserMethods methods = new UserMethods();
        ValidatableResponse loginUserResponse = methods.login(credentials);
        String accessToken = loginUserResponse.extract().path("accessToken");
        methods.delete(accessToken);
    }
}