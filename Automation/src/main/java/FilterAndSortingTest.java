import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class FilterAndSortingTest {
	private WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\WebDrivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://websitedemos.net/brandstore-02/");
	}

	// Filtering the price
	@Test
	public void filterProductsByPriceTest() {
		WebElement everythingTab = driver.findElement(By.xpath("//*[@id=\"menu-item-45\"]/a"));
		everythingTab.click();
		WebElement minPriceSlider = driver
				.findElement(By.xpath("//*[@id=\"woocommerce_price_filter-5\"]/form/div/div[1]/span[1]"));
		WebElement maxPriceSlider = driver
				.findElement(By.xpath("//*[@id=\"woocommerce_price_filter-5\"]/form/div/div[1]/span[2]"));

		// Set the price range by dragging the sliders
		Actions sliderAction = new Actions(driver);
		sliderAction.dragAndDropBy(minPriceSlider, 50, 0).perform();
		sliderAction.dragAndDropBy(maxPriceSlider, -30, 0).perform();

		WebElement filterButton = driver
				.findElement(By.xpath("//*[@id=\"woocommerce_price_filter-5\"]/form/div/div[2]/button"));
		filterButton.click();

		WebElement resultsInfo = driver.findElement(By.className("woocommerce-result-count"));
		String resultsText = resultsInfo.getText();

		if (resultsText.contains("results")) {
			System.out.println("Filtering results worked: " + resultsText);
		} else {
			System.out.println("Filtering results failed: " + resultsText);
		}
	}

	//sort by low to high
	@Test
	public void defaultSortingTest() {
		WebElement everythingTab = driver.findElement(By.xpath("//*[@id=\"menu-item-45\"]/a"));
		everythingTab.click();
		WebElement sortingDropdown = driver.findElement(By.xpath("//*[@id=\"main\"]/div/form/select"));
		Select sortingOptions = new Select(sortingDropdown);
		sortingOptions.selectByVisibleText("Sort by price: low to high");

		WebElement resultsInfo = driver.findElement(By.className("woocommerce-result-count"));
		String resultsText = resultsInfo.getText();

		if (resultsText.contains("results")) {
			System.out.println("Filtering results worked: " + resultsText);
		} else {
			System.out.println("Filtering results failed: " + resultsText);
		}
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
