package Automation.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;

import Automation.AbstractComponentsUtil.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(id="userEmail")
	WebElement userEmailEle ;
	
	@FindBy(id="userPassword")
	WebElement userPasswordEle ;
	
	@FindBy(css=".login-btn")
	WebElement loginSubmitEle ;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;
	
	public ProductCatalogue loginMethod(String userName,String Password) {
		userEmailEle.sendKeys(userName);
		userPasswordEle.sendKeys(Password);
		loginSubmitEle.click();
		ProductCatalogue productPageObj = new ProductCatalogue(driver);
		return productPageObj;
	}

	public String getErrorMessage() {
		waitForWebElementToApper(errorMsg);
		System.out.println("errorMsg.getText() ::"+errorMsg.getText());
		return errorMsg.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}



}
