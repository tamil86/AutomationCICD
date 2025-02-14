package Automation.AbstractComponentsUtil;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Automation.PageObjects.CartPage;
import Automation.PageObjects.OrdersPage;

public class AbstractComponent {

	WebDriver driver;

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement goToCartHeader;

	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement goToOrdersPage;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToApper(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForWebElementToApper(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitForElementToBeInvisible(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public CartPage GoToCartPage() throws InterruptedException {

		System.out.println("goToCartHeader :: add to cart button :" + goToCartHeader.getText());

		Actions action = new Actions(driver);
		// action.pause(Duration.ofSeconds(2)).perform();
		action.moveToElement(goToCartHeader).click().build().perform();
		// action.pause(Duration.ofSeconds(2)).perform();

		// goToCartHeader.click();
		CartPage cartPageObj = new CartPage(driver);
		return cartPageObj;
	}

	public OrdersPage GoToOrdersPage() {
		goToOrdersPage.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;

	}
}
