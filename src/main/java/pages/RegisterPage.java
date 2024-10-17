package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    private static final String FIELD_NAME_XPATH = ".//label[text()='Имя']/parent::div/input";
    private static final String FIELD_EMAIL_XPATH = ".//label[text()='Email']/parent::div/input";
    private static final String FIELD_PASSWORD_XPATH = ".//label[text()='Пароль']/parent::div/input";
    private static final String BUTTON_REGISTER_XPATH = ".//button[text()='Зарегистрироваться']";
    private static final String TEXT_ERROR_PASSWORD_XPATH = ".//p[text()='Некорректный пароль']";
    private static final String BUTTON_LOGIN_XPATH = ".//a[text()='Войти']";

    @Step("Ввод имени")
    public void setName(String name) {
        driver.findElement(By.xpath(FIELD_NAME_XPATH)).sendKeys(name);
    }

    @Step("Ввод емейла")
    public void setEmail(String email) {
        driver.findElement(By.xpath(FIELD_EMAIL_XPATH)).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void setPassword(String password) {
        driver.findElement(By.xpath(FIELD_PASSWORD_XPATH)).sendKeys(password);
    }

    @Step("Клик по кнопке \"Зарегистрироваться\"")
    public void clickRegisterButton() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_DURATION)).until(ExpectedConditions.elementToBeClickable(By.xpath(BUTTON_REGISTER_XPATH)));
        driver.findElement(By.xpath(BUTTON_REGISTER_XPATH)).click();
    }

    @Step("Дождаться сообщения об ошибке")
    public void waitErrorMessageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_DURATION)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TEXT_ERROR_PASSWORD_XPATH)));
    }

    @Step("Получить текст сообщения об ошибке")
    public String getErrorMessageText() {
        return driver.findElement(By.xpath(TEXT_ERROR_PASSWORD_XPATH)).getText();
    }

    @Step("Клик по кнопке \"Войти\"")
    public void clickLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_DURATION)).until(ExpectedConditions.elementToBeClickable(By.xpath(BUTTON_LOGIN_XPATH)));
        driver.findElement(By.xpath(BUTTON_LOGIN_XPATH)).click();
    }

    @Step("Ввод данных пользователя и клик по кнопке \"Зарегистрироваться\"")
    public void setUpUserDataAndClickRegister(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }
}