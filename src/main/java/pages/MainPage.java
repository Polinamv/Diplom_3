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
    private static final String TITLE_BUNS_XPATH = ".//h2[text()='Булки']";
    private static final String TITLE_SAUCE_XPATH = ".//h2[text()='Соусы']";
    private static final String TITLE_FILLING_XPATH = ".//h2[text()='Начинки']";

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
    public boolean isBunAtTheBottomOfTab() {
        try {
            Thread.sleep(500);
            return isSectionAtTheBottomOfTab(
                    driver.findElement(By.xpath(SECTION_BUNS_XPATH)),
                    driver.findElement(By.xpath(TITLE_BUNS_XPATH))
            );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Проверка соуса в зоне видимости")
    public boolean isSauceAtTheBottomOfTab() {
        try {
            Thread.sleep(500);
            return isSectionAtTheBottomOfTab(
                    driver.findElement(By.xpath(SECTION_SAUCE_XPATH)),
                    driver.findElement(By.xpath(TITLE_SAUCE_XPATH))
            );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Проверка начинки в зоне видимости")
    public boolean isFillingAtTheBottomOfTab() {
        try {
            Thread.sleep(500);
            return isSectionAtTheBottomOfTab(
                    driver.findElement(By.xpath(SECTION_FILLING_XPATH)),
                    driver.findElement(By.xpath(TITLE_FILLING_XPATH))
            );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isSectionAtTheBottomOfTab(WebElement tabs, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_DURATION))
                .until(
                        driver -> {
                            Rectangle rectTabs = tabs.getRect();
                            Rectangle rectElement = element.getRect();
                            int elementsDiff = rectElement.getY() - rectTabs.getY() - rectTabs.getHeight();
                            return elementsDiff < 100 && elementsDiff > 0;
                        }
                );
    }
}