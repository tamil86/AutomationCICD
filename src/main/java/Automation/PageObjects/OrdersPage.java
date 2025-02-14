package Automation.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.AbstractComponentsUtil.AbstractComponent;

public class OrdersPage extends AbstractComponent {
	WebDriver driver ;
	
	@FindBy(css="td:nth-child(3)")
	List<WebElement> productNames;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	public Boolean verifyOrderDisplay(String productName) {
		Boolean match=productNames.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}
}
