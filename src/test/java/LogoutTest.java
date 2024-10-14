import apiData.UserMethods;
import apiData.UserRequestBody;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.PersonalAccountPage;

@DisplayName("Выход из аккаунта")
public class LogoutTest extends BaseTest {
    final MainPage mainPage = new MainPage(driver);
    final LoginPage loginPage = new LoginPage(driver);
    final PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
    final UserMethods methods = new UserMethods();
    final UserRequestBody user = new UserRequestBody(email, password, name);

    String accessToken;

    @Before
    public void setUp() {
        ValidatableResponse createUserResponse = methods.create(user);
        accessToken = createUserResponse.extract().path("accessToken");
        driver.get("https://stellarburgers.nomoreparties.site/login");
    }

    @Override
    public void clearUp() {
        methods.delete(accessToken);
        super.clearUp();
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void logoutTest() {
        loginPage.loginToAccount(email, password);
        mainPage.waitMakeTheBurgerTitleToLoad();
        mainPage.waitLoadingToEnd();
        mainPage.clickPersonalAccountButton();
        personalAccountPage.waitAccountEditTextToLoad();
        personalAccountPage.waitLoadingToEnd();
        personalAccountPage.clickLogoutButton();
        loginPage.waitLoginTitleToLoad();
        String loginTitleTextText = loginPage.getTitleText();
        Assert.assertEquals("Вход", loginTitleTextText);
    }
}