import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;

public class ContactUsTest {
	private WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\WebDrivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://websitedemos.net/brandstore-02/");
	}
	//Test cintactus page
	@Test
	public void testContactUsPage() {
		clickContactUsTab();

		WebElement nameInput = driver.findElement(By.id("wpforms-858-field_0"));
		nameInput.sendKeys("Sharuk");

		WebElement subjectInput = driver.findElement(By.id("wpforms-858-field_5"));
		subjectInput.sendKeys("Inquiry");

		WebElement emailInput = driver.findElement(By.id("wpforms-858-field_4"));
		emailInput.sendKeys("abc@gmail.com");

		WebElement messageInput = driver.findElement(By.id("wpforms-858-field_2"));
		messageInput.sendKeys("Hi");

		WebElement sendMessageButton = driver.findElement(By.id("wpforms-submit-858"));

		sendMessageButton.click();

		WebElement successMessage = driver.findElement(By.xpath("//*[@id=\"wpforms-confirmation-858\"]/p"));
		assertEquals("Thanks for contacting us! We will be in touch with you shortly.", successMessage.getText());
	}

	private void clickContactUsTab() {

		WebElement contactUsTab = driver.findElement(
				By.xpath("/html/body/div[1]/header/div[1]/div/div/div/div/div[2]/div[1]/div/div/nav/div/ul/li[2]/a"));
		contactUsTab.click();
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
