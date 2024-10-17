package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private static final String TITLE_LOGIN_XPATH = ".//h2[text()='Вход']";
    private static final String FIELD_EMAIL_XPATH = ".//label[text()='Email']/parent::div/input";
    private static final String FIELD_PASSWORD_XPATH = ".//label[text()='Пароль']/parent::div/input";
    private static final String BUTTON_LOGIN_XPATH = ".//button[text()='Войти']";

    @Step("Дождаться загрузки страницы входа")
    public void waitLoginTitleToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_DURATION)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TITLE_LOGIN_XPATH)));
    }

    @Step("Получить текст заголовка на странице входа")
    public String getTitleText() {
        return driver.findElement(By.xpath(TITLE_LOGIN_XPATH)).getText();
    }

    @Step("Ввод емейла")
    public void setEmail(String email) {
        driver.findElement(By.xpath(FIELD_EMAIL_XPATH)).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void setPassword(String password) {
        driver.findElement(By.xpath(FIELD_PASSWORD_XPATH)).sendKeys(password);
    }

    @Step("Клик по кнопке \"Войти\"")
    public void clickLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_DURATION)).until(ExpectedConditions.elementToBeClickable(By.xpath(BUTTON_LOGIN_XPATH)));
        driver.findElement(By.xpath(BUTTON_LOGIN_XPATH)).click();
    }

    @Step("Вход в аккаунт")
    public void loginToAccount(String email, String password) {
        waitLoginTitleToLoad();
        waitLoadingToEnd();
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }
}