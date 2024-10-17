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
    @DisplayName("Навигация по вкладкам Конструктора - \"Булки\"")
    public void routingToBunSection() {
        loginPage.loginToAccount(user.getEmail(), user.getPassword());
        mainPage.waitMakeTheBurgerTitleToLoad();
        mainPage.waitLoadingToEnd();
        mainPage.clickSauceButton();
        mainPage.sleep();
        mainPage.clickBunsButton();
        boolean isBunsSelected = mainPage.isBunsSelected();
        Assert.assertTrue(isBunsSelected);
    }

    @Test
    @DisplayName("Навигация по вкладкам Конструктора - \"Соусы\"")
    public void routingToSauceSection() {
        loginPage.loginToAccount(user.getEmail(), user.getPassword());
        mainPage.waitMakeTheBurgerTitleToLoad();
        mainPage.waitLoadingToEnd();
        mainPage.clickSauceButton();
        boolean isSaucesSelected = mainPage.isSaucesSelected();
        Assert.assertTrue(isSaucesSelected);
    }

    @Test
    @DisplayName("Навигация по вкладкам Конструктора - \"Начинки\"")
    public void routingToFillingSection() {
        loginPage.loginToAccount(user.getEmail(), user.getPassword());
        mainPage.waitMakeTheBurgerTitleToLoad();
        mainPage.waitLoadingToEnd();
        mainPage.clickFillingButton();
        boolean isFillingsSelected = mainPage.isFillingsSelected();
        Assert.assertTrue(isFillingsSelected);
    }
}