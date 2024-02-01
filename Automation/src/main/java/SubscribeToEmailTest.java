import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.By;

public class SubscribeToEmailTest {

	private WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\WebDrivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://websitedemos.net/brandstore-02/");
	}

	@Test
	public void testSubscribeToEmail() {
		WebElement emailInput = driver.findElement(By.id("wpforms-4299-field_2"));
		WebElement subscribeButton = driver.findElement(By.id("wpforms-submit-4299"));

		String email = "sharujan.gs@gmail.com";
		emailInput.sendKeys(email);
		subscribeButton.click();

		try {
			Thread.sleep(2000); // Sleep for 2 seconds (
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement successMessage = driver.findElement(By.xpath("//*[@id=\"wpforms-confirmation-4299\"]/p"));// Locate the success message and validate its text
		String expectedMessage = "Thanks for signing up for the newsletter! We'll be in touch soon.";
		String actualMessage = successMessage.getText();
		Assert.assertEquals("Success message does not match", expectedMessage, actualMessage);		// Validating the success message

	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}