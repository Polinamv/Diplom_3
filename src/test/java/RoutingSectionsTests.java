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
    final String email = "paa@login.ru";
    final String password = "123456";
    final String name = "Polina";
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
    @DisplayName("Навигация по вкладкам Конструктора - булки, соусы, начинки")
    public void routingToBurgersSections() {
        loginPage.loginToAccount(email, password);
        mainPage.waitMakeTheBurgerTitleToLoad();
        mainPage.waitLoadingToEnd();
        mainPage.clickSauceButton();
        boolean isSauceInViewport = mainPage.isSauceInViewport();
        Assert.assertTrue(isSauceInViewport);
        mainPage.clickFillingButton();
        boolean isFillingInViewport = mainPage.isFillingInViewport();
        Assert.assertTrue(isFillingInViewport);
        mainPage.clickBunsButton();
        boolean isBunInViewport = mainPage.isBunInViewport();
        Assert.assertTrue(isBunInViewport);
    }
}