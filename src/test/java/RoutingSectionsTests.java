import apiData.UserMethods;
import apiData.UserRequestBody;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;

@DisplayName("Навигация по вкладкам Конструктора бургера")
public class RoutingSectionsTests extends BaseTest {
    final MainPage mainPage = new MainPage(driver);
    final LoginPage loginPage = new LoginPage(driver);
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
    @DisplayName("Навигация по вкладкам Конструктора - \"Булки\"")
    public void routingToBunSection() {
        loginPage.loginToAccount(email, password);
        mainPage.waitMakeTheBurgerTitleToLoad();
        mainPage.waitLoadingToEnd();
        mainPage.clickSauceButton();
        mainPage.clickBunsButton();
        boolean isBunAtTheBottomOfTab = mainPage.isBunAtTheBottomOfTab();
        Assert.assertTrue(isBunAtTheBottomOfTab);
    }

    @Test
    @DisplayName("Навигация по вкладкам Конструктора - \"Соусы\"")
    public void routingToSauceSection() {
        loginPage.loginToAccount(email, password);
        mainPage.waitMakeTheBurgerTitleToLoad();
        mainPage.waitLoadingToEnd();
        mainPage.clickSauceButton();
        boolean isSauceAtTheBottomOfTab = mainPage.isSauceAtTheBottomOfTab();
        Assert.assertTrue(isSauceAtTheBottomOfTab);
    }

    @Test
    @DisplayName("Навигация по вкладкам Конструктора - \"Начинки\"")
    public void routingToFillingSection() {
        loginPage.loginToAccount(email, password);
        mainPage.waitMakeTheBurgerTitleToLoad();
        mainPage.waitLoadingToEnd();
        mainPage.clickFillingButton();
        boolean isFillingAtTheBottomOfTab = mainPage.isFillingAtTheBottomOfTab();
        Assert.assertTrue(isFillingAtTheBottomOfTab);
    }
}