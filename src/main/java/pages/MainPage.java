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
    private static final String SECTION_BUNS_XPATH = ".//span[text()='Булки']";
    private static final String SECTION_SAUCE_XPATH = ".//span[text()='Соусы']";
    private static final String SECTION_FILLING_XPATH = ".//span[text()='Начинки']";
    private static final String ELEMENT_BUN_XPATH = ".//img[@alt='Флюоресцентная булка R2-D3']";
    private static final String ELEMENT_SAUCE_X_XPATH = ".//img[@alt='Соус Spicy-X']";
    private static final String ELEMENT_FILLING_XPATH = ".//img[@alt='Мясо бессмертных моллюсков Protostomia']";

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

    @Step("Проверка булки в зоне видимости")
    public boolean isBunInViewport() {
        return isElementInViewport(driver.findElement(By.xpath(ELEMENT_BUN_XPATH)));
    }

    @Step("Проверка соуса в зоне видимости")
    public boolean isSauceInViewport() {
        return isElementInViewport(driver.findElement(By.xpath(ELEMENT_SAUCE_X_XPATH)));
    }

    @Step("Проверка начинки в зоне видимости")
    public boolean isFillingInViewport() {
        return isElementInViewport(driver.findElement(By.xpath(ELEMENT_FILLING_XPATH)));
    }

    private boolean isElementInViewport(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_DURATION))
                .until(
                        driver -> {
                            Rectangle rect = element.getRect();
                            Dimension windowSize = driver.manage().window().getSize();
                            return rect.getX() >= 0
                                    && rect.getY() >= 0
                                    && rect.getX() + rect.getWidth() <= windowSize.getWidth()
                                    && rect.getY() + rect.getHeight() <= windowSize.getHeight();
                        }
                );
    }
}