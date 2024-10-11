package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage extends BasePage {

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    private static final String BUTTON_LOGIN_XPATH = ".//a[text()='Войти']";

    @Step("Клик по кнопке \"Войти\"")
    public void clickLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_DURATION)).until(ExpectedConditions.elementToBeClickable(By.xpath(BUTTON_LOGIN_XPATH)));
        driver.findElement(By.xpath(BUTTON_LOGIN_XPATH)).click();
    }
}