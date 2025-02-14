package Automation.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Automation.AbstractComponentsUtil.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".mb-3")
	List<WebElement> productsEle;

	@FindBy(css = ".ng-animating")
	WebElement ItemAddedAlertEle;

	@FindBy(css = ("a[aria-label='Next page']"))
	WebElement nextPageEle;

	@FindBy(css = "a[aria-label='Next page']")
	List<WebElement> hasNextPageListEle;

	By productsBy = By.cssSelector(".mb-3");
	By toastMsgLocator = By.cssSelector("#toast-container");
	By addToCartLocator = By.cssSelector(".btn.w-10.rounded");

	public List<WebElement> getProductList() {
		waitForElementToApper(productsBy);
		return productsEle;
	}

	public WebElement getProductName(String productName) {
		// pagination click of next button
		WebElement product;
		System.out.println("size :: "+getProductList().size());
		do {
			// getProductList().stream().map(s -> System.out.println(s.findElement(By.cssSelector("b")).getText());
			
			product = getProductList().stream()
					.filter(s -> s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst()
					.orElse(null);
			System.out.println("product :: "+product);
			if (product == null) {
				// check if next page present
				Boolean hasNextPage = hasNextPageListEle.size() > 0;
				if (!hasNextPage) {
					System.out.println("next button is disdabled ---> break -- No product found with that name");
					return product;
				}
				
				// click Next Page
				System.out.println(nextPageEle);
				Actions actions = new Actions(driver);
				actions.moveToElement(nextPageEle).click().perform();
			} else {
				return product;
			}
		} while (product == null);
		return product;
	}

	public void AddProductToCart(String productName) {

		WebElement product = getProductName(productName);
		
	//	WebElement product = getProductName(productName);
		System.out.println("product found : " + product.getText());
		// product found -> click add to cart button
		System.out.println("before clicking add to cart");
		
		Actions action = new Actions(driver);
		WebElement we = product.findElement(addToCartLocator);
		System.out.println("located add to cart ::"+we.getText());
		action.moveToElement(we).click().build().perform();
		
	//	product.findElement(addToCartLocator).click();
		System.out.println("After clicking add to cart");

		// Explicit wait
		waitForElementToApper(toastMsgLocator);
		waitForElementToBeInvisible(ItemAddedAlertEle);
		System.out.println("*******************Item added to cart");
	}


}
