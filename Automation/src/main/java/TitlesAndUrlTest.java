import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TitlesAndUrlTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://websitedemos.net/brandstore-02/");
    }
    @Test
    public void validateTitleAndUrl() {
        String expectedTitle = "Home - Brandstore";
        String expectedUrl = "https://websitedemos.net/brandstore-02/";

        String actualTitle = driver.getTitle();
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals("Page title mismatch", expectedTitle, actualTitle);
        Assert.assertEquals("URL mismatch", expectedUrl, actualUrl);
    }
	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
