import apiData.UserRequestBody;
import org.junit.After;
import org.openqa.selenium.WebDriver;

import java.util.Random;

public class BaseTest {

    BrowserDrivers browserDrivers = new BrowserDrivers();
    // Можем выбрать driver для браузера:
    // WebDriver driver = browserDrivers.firefoxDriver();
    WebDriver driver = browserDrivers.chromeDriver();

    @After
    public void clearUp() {
        driver.quit();
    }

    public UserRequestBody getRandomUser() {
        return new UserRequestBody(
                "user" + new Random().nextInt(1000000) + "@mail.com",
                "123456",
                "Name"
        );
    }
}