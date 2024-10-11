package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected static final int DEFAULT_DURATION = 60;
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    private static final String LOADING_IMAGE_XPATH = ".//img[@class='Modal_modal__loading__3534A']";

    @Step("Дождаться окончания загрузки")
    public void waitLoadingToEnd() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_DURATION)).until(ExpectedConditions.invisibilityOfElementLocated((By.xpath(LOADING_IMAGE_XPATH))));
    }
}