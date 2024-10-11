import org.junit.After;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    BrowserDrivers browserDrivers = new BrowserDrivers();
    // Можем выбрать driver для браузера:
    // WebDriver driver = browserDrivers.firefoxDriver();
    WebDriver driver = browserDrivers.chromeDriver();

    @After
    public void clearUp() {
        driver.quit();
    }
}