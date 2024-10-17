package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage extends BasePage {

    public PersonalAccountPage(WebDriver driver) {
        super(driver);
    }

    private static final String ACCOUNT_TEXT_XPATH = ".//p[text()='В этом разделе вы можете изменить свои персональные данные']";
    private static final String BUTTON_CONSTRUCTOR_XPATH = ".//p[text()='Конструктор']/parent::a[@class='AppHeader_header__link__3D_hX']";
    private static final String BUTTON_LOGO_XPATH = ".//a/parent::div[@class='AppHeader_header__logo__2D0X2']";
    private static final String BUTTON_LOGOUT_XPATH = ".//button[text()='Выход']";

    @Step("Дождаться появления текста о редактировании данных")
    public void waitAccountEditTextToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_DURATION)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ACCOUNT_TEXT_XPATH)));
    }

    @Step("Получение текста о редактировании данных")
    public String getAccountEditText() {
        return driver.findElement(By.xpath(ACCOUNT_TEXT_XPATH)).getText();
    }

    @Step("Клик по кнопке \"Конструктор\" из ЛК")
    public void clickConstructorButton() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_DURATION)).until(ExpectedConditions.elementToBeClickable(By.xpath(BUTTON_CONSTRUCTOR_XPATH)));
        driver.findElement(By.xpath(BUTTON_CONSTRUCTOR_XPATH)).click();
    }

    @Step("Клик по логотипу из ЛК")
    public void clickLogoButton() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_DURATION)).until(ExpectedConditions.elementToBeClickable(By.xpath(BUTTON_LOGO_XPATH)));
        driver.findElement(By.xpath(BUTTON_LOGO_XPATH)).click();
    }

    @Step("Клик по кнопке \"Выход\" из ЛК")
    public void clickLogoutButton() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_DURATION)).until(ExpectedConditions.elementToBeClickable(By.xpath(BUTTON_LOGOUT_XPATH)));
        driver.findElement(By.xpath(BUTTON_LOGOUT_XPATH)).click();
    }
}