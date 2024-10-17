package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private static final String BUTTON_PERSONAL_ACCOUNT_XPATH = ".//p[text()='Личный Кабинет']/parent::a";
    private static final String BUTTON_LOGIN_TO_ACCOUNT_XPATH = ".//button[text()='Войти в аккаунт']";
    private static final String TITLE_MAKE_THE_BURGER_XPATH = ".//h1[text()='Соберите бургер']";
    private static final String SECTION_BUNS_XPATH = ".//span[text()='Булки']/parent::div";
    private static final String SECTION_SAUCE_XPATH = ".//span[text()='Соусы']/parent::div";
    private static final String SECTION_FILLING_XPATH = ".//span[text()='Начинки']/parent::div";

    @Step("Клик по кнопке \"Личный Кабинет\"")
    public void clickPersonalAccountButton() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_DURATION)).until(ExpectedConditions.elementToBeClickable(By.xpath(BUTTON_PERSONAL_ACCOUNT_XPATH)));
        driver.findElement(By.xpath(BUTTON_PERSONAL_ACCOUNT_XPATH)).click();
    }

    @Step("Клик по кнопке \"Войти в аккаунт\"")
    public void clickLoginToAccountButton() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_DURATION)).until(ExpectedConditions.elementToBeClickable(By.xpath(BUTTON_LOGIN_TO_ACCOUNT_XPATH)));
        driver.findElement(By.xpath(BUTTON_LOGIN_TO_ACCOUNT_XPATH)).click();
    }

    @Step("Дождаться отображения заголовка \"Соберите бургер\"")
    public void waitMakeTheBurgerTitleToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_DURATION)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TITLE_MAKE_THE_BURGER_XPATH)));
    }

    @Step("Получить текст заголовка \"Соберите бургер\"")
    public String getMakeTheBurgerTitleText() {
        return driver.findElement(By.xpath(TITLE_MAKE_THE_BURGER_XPATH)).getText();
    }

    @Step("Клик по кнопке \"Булки\"")
    public void clickBunsButton() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_DURATION)).until(ExpectedConditions.elementToBeClickable(By.xpath(SECTION_BUNS_XPATH)));
        driver.findElement(By.xpath(SECTION_BUNS_XPATH)).click();
    }

    @Step("Клик по кнопке \"Соусы\"")
    public void clickSauceButton() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_DURATION)).until(ExpectedConditions.elementToBeClickable(By.xpath(SECTION_SAUCE_XPATH)));
        driver.findElement(By.xpath(SECTION_SAUCE_XPATH)).click();
    }

    @Step("Клик по кнопке \"Начинки\"")
    public void clickFillingButton() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_DURATION)).until(ExpectedConditions.elementToBeClickable(By.xpath(SECTION_FILLING_XPATH)));
        driver.findElement(By.xpath(SECTION_FILLING_XPATH)).click();
    }

    @Step("Проверка, что таб \"Булки\" выбран")
    public boolean isBunsSelected() {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_DURATION))
                .until(ExpectedConditions.attributeContains(By.xpath(SECTION_BUNS_XPATH), "class", "tab_tab_type_current"));
    }

    @Step("Проверка, что таб \"Соусы\" выбран")
    public boolean isSaucesSelected() {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_DURATION))
                .until(ExpectedConditions.attributeContains(By.xpath(SECTION_SAUCE_XPATH), "class", "tab_tab_type_current"));
    }

    @Step("Проверка, что таб \"Начинки\" выбран")
    public boolean isFillingsSelected() {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_DURATION))
                .until(ExpectedConditions.attributeContains(By.xpath(SECTION_FILLING_XPATH), "class", "tab_tab_type_current"));
    }
}