import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartAndCheckoutTest {
	private WebDriver driver;
	private WebElement totalElement;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\WebDrivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://websitedemos.net/brandstore-02/");
	}

	// Test Empty Cart
	@Test
	public void testEmptyCart() {
		WebElement cartIcon = driver.findElement(By.xpath("//*[@id=\"ast-site-header-cart\"]/div[1]/a/div/i"));
		cartIcon.click();
		WebElement emptyCartMessage = driver
				.findElement(By.xpath("//*[@id=\"astra-mobile-cart-drawer\"]/div[2]/div/div/div/div[1]/p"));
		Assert.assertTrue("No products in the cart.", emptyCartMessage.isDisplayed());
		driver.navigate().to("https://websitedemos.net/brandstore-02/");
	}

	// Test Add Product by Searching add cart and checkout
	@Test
	public void testAddProductToCart() {

		WebElement everythingTab = driver.findElement(By.xpath("//*[@id=\"menu-item-45\"]/a"));
		everythingTab.click();

		WebElement searchBar = driver.findElement(By.id("wc-block-search__input-1"));
		searchBar.sendKeys("jeans");
		searchBar.submit();

		WebElement product = driver.findElement(By.xpath("//*[@id=\"main\"]/div/ul/li[2]/div[1]/a[1]/img"));
		product.click();

		WebElement quantityInput = driver.findElement(By.id("quantity_65bb9601ad7b4"));
		quantityInput.clear();
		quantityInput.sendKeys("4");

		WebElement addToCartButton = driver
				.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div[2]/div[2]/form/button"));
		addToCartButton.click();

		WebElement viewCartButton = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div/a"));
		viewCartButton.click();

		WebElement totalElement = driver.findElement(By.xpath(
				"//*[@id=\"post-39\"]/div/div/section[2]/div/div/div/div/div/div/div/div[2]/div/table/tbody/tr[2]/td"));
		WebElement subtotalElement = driver.findElement(By.xpath(
				"//*[@id=\"post-39\"]/div/div/section[2]/div/div/div/div/div/div/div/div[2]/div/table/tbody/tr[1]/td/span"));

		String totalText = totalElement.getText();
		String subtotalText = subtotalElement.getText();

		double totalValue = Double.parseDouble(totalText.replace("$", ""));
		double subtotalValue = Double.parseDouble(subtotalText.replace("$", ""));

		System.out.println("Total: " + totalValue);
		System.out.println("Subtotal: " + subtotalValue);

		WebElement checkoutButton = driver.findElement(
				By.xpath("//*[@id=\"post-39\"]/div/div/section[2]/div/div/div/div/div/div/div/div[2]/div/div/a"));
		checkoutButton.click();

		WebElement firstNameField = driver.findElement(By.id("billing_first_name"));
		firstNameField.sendKeys("Sharujan");

		WebElement lastNameField = driver.findElement(By.id("billing_last_name"));
		lastNameField.sendKeys("Ganeshan");

		WebElement companyNameField = driver.findElement(By.id("billing_company"));
		companyNameField.sendKeys("SLIIT");

		WebElement countryRegionField = driver.findElement(By.id("billing_country"));
		Select countryRegionSelect = new Select(countryRegionField);
		countryRegionSelect.selectByVisibleText("Sri Lanka");

		WebElement streetAddressField = driver.findElement(By.id("billing_address_1"));
		streetAddressField.sendKeys("123 Main Street");

		WebElement apartmentSuiteField = driver.findElement(By.id("billing_address_2"));
		apartmentSuiteField.sendKeys("Apt. 1");

		WebElement townCityField = driver.findElement(By.id("billing_city"));
		townCityField.sendKeys("Hatton");

		WebElement postcodeZipField = driver.findElement(By.id("billing_postcode"));
		postcodeZipField.sendKeys("12345");

		WebElement phoneField = driver.findElement(By.id("billing_phone"));
		phoneField.sendKeys("0123456789");

		WebElement emailAddressField = driver.findElement(By.id("billing_email"));
		emailAddressField.sendKeys("sharujan.gs@gmail.com");

		WebElement orderNotesField = driver.findElement(By.id("order_comments"));
		orderNotesField.sendKeys("Please keep this safely.");

		WebElement codRadioButton = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/article/div/div/section[2]/div/div/div/div/div/div/div/form[3]/div[3]/div/ul/li[1]/label"));
		codRadioButton.click();

		Assert.assertTrue(codRadioButton.isSelected());

		WebElement placeOrderButton = driver.findElement(By.xpath("//*[@id=\"payment_method_bacs\"]"));
		placeOrderButton.click();

	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
