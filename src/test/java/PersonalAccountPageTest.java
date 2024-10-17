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

@DisplayName("Открытие Личного Кабинета")
public class PersonalAccountPageTest extends BaseTest {
    final MainPage mainPage = new MainPage(driver);
    final LoginPage loginPage = new LoginPage(driver);
    final PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
    final UserMethods methods = new UserMethods();
    final UserRequestBody user = getRandomUser();

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
    @DisplayName("Открытие Личного Кабинета")
    public void openPersonalAccountPage() {
        loginPage.loginToAccount(user.getEmail(), user.getPassword());
        mainPage.waitMakeTheBurgerTitleToLoad();
        mainPage.waitLoadingToEnd();
        mainPage.clickPersonalAccountButton();
        personalAccountPage.waitAccountEditTextToLoad();
        String accountEditText = personalAccountPage.getAccountEditText();
        Assert.assertEquals("В этом разделе вы можете изменить свои персональные данные", accountEditText);
    }
}