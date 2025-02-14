package Automation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Automation.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneApp_Original {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "Banarsi Saree";
		String actualMsg = "Thankyou for the order.";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");

	
		// login page
		driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
		driver.findElement(By.cssSelector(".login-btn")).click();

		// in Add to cart opage
		// Explicit wait until page loads
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		WebElement product = null;

		// pagination click of next button
		do {
			List<WebElement> productList = driver.findElements(By.cssSelector(".mb-3"));
			product = productList.stream()
					.filter(s -> s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
					.findFirst().orElse(null);
			System.out.println(product);
			if (product == null) {
				//check if next page present
				Boolean hasNextPage = driver.findElements(By.cssSelector("a[aria-label='Next page']")).size() > 0;
				if (!hasNextPage) {
					System.out.println("next button is disdabled ---> break -- No product found with that name");
					break;
				}
				
				//click Next Page
				WebElement element = driver.findElement(By.cssSelector("a[aria-label='Next page']"));
				System.out.println(element);
				Actions actions = new Actions(driver);
				actions.moveToElement(element).click().perform();
			} else {
				//product found -> click add to cart button
				product.findElement(By.cssSelector(".btn.w-10.rounded")).click();
				
				// Explicit wait
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
				wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

				// click 'Cart' button
				driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
			}
		} while (product == null);

		
		//check if the Cart screen has the productName we added
		List<WebElement> itemsInCart = driver.findElements(By.cssSelector(".cartSection h3"));
		System.out.println(itemsInCart.stream().filter(p->p.getText().equalsIgnoreCase(productName)).count());
		boolean isItemFound = itemsInCart.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		System.out.println(productName +" found is "+isItemFound );
		Assert.assertTrue(isItemFound);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//select country dropdown and click 'place orde' button
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
		List<WebElement> listCountry = driver.findElements(By.xpath("//span[@class='ng-star-inserted']"));
		System.out.println("listCountry :: "+listCountry.size());
		WebElement filteredCountry = listCountry.stream().filter(country->country.getText().equalsIgnoreCase("india")).findFirst().orElse(null);
		System.out.println("filteredCountry:::"+ filteredCountry.getText());
		filteredCountry.click();
		
		driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
		
		String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(confirmMsg);
		
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(actualMsg));
		driver.close();
	
	}

}
