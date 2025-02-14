package Automation.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.AbstractComponentsUtil.AbstractComponent;

public class OrderConfirmationPage extends AbstractComponent {

	WebDriver driver;

	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement confirmOrdEle;

	public Boolean getOrderPlacedMsg(String actualMsg) {
		// select country dropdown 
		if(confirmOrdEle.getText().equalsIgnoreCase(actualMsg)) {
			return true;
		}
		return false;
	}




}
