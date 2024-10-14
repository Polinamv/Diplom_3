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

@DisplayName("Переход на главную страницу")
public class RoutingToMainPageTests extends BaseTest {
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
    @DisplayName("Переход на главную страницу через кнопку \"Конструктор\"")
    public void routingToMainPageByConstructorButton() {
        loginPage.loginToAccount(email, password);
        mainPage.waitMakeTheBurgerTitleToLoad();
        mainPage.waitLoadingToEnd();
        mainPage.clickPersonalAccountButton();
        personalAccountPage.waitAccountEditTextToLoad();
        personalAccountPage.waitLoadingToEnd();
        personalAccountPage.clickConstructorButton();
        mainPage.waitMakeTheBurgerTitleToLoad();
        String makeTheBurgerText = mainPage.getMakeTheBurgerTitleText();
        Assert.assertEquals("Соберите бургер", makeTheBurgerText);
    }

    @Test
    @DisplayName("Переход на главную страницу через логотип")
    public void routingToMainPageByLogoButton() {
        loginPage.loginToAccount(email, password);
        mainPage.waitMakeTheBurgerTitleToLoad();
        mainPage.waitLoadingToEnd();
        mainPage.clickPersonalAccountButton();
        personalAccountPage.waitAccountEditTextToLoad();
        personalAccountPage.waitLoadingToEnd();
        personalAccountPage.clickLogoButton();
        mainPage.waitMakeTheBurgerTitleToLoad();
        String makeTheBurgerText = mainPage.getMakeTheBurgerTitleText();
        Assert.assertEquals("Соберите бургер", makeTheBurgerText);
    }
}