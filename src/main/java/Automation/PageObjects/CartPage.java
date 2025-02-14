package Automation.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.AbstractComponentsUtil.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> intemInCartEle;

	@FindBy(css = ".totalRow button")
	WebElement checkOutButtonEle;

	public Boolean verifyProductDisplay(String productName) {
		boolean isItemFound = intemInCartEle.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
		System.out.println(productName + " found is " + isItemFound);
		return isItemFound;
	}

	public CheckOutPage checkOutMethod() {
		checkOutButtonEle.click();
		CheckOutPage checkoutPageObj = new CheckOutPage(driver);
		return checkoutPageObj;		
	}

}
