import org.junit.After;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    BrowserDrivers browserDrivers = new BrowserDrivers();
    // Можем выбрать driver для браузера:
    // WebDriver driver = browserDrivers.firefoxDriver();
    WebDriver driver = browserDrivers.chromeDriver();

    final String email = "polya@login.ru";
    final String password = "123456";
    final String name = "Polina";

    @After
    public void clearUp() {
        driver.quit();
    }
}