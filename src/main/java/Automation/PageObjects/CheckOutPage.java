package Automation.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Automation.AbstractComponentsUtil.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement enterCountryEle;

	@FindBy(xpath = "//span[@class='ng-star-inserted']")
	List<WebElement> countryListPopulatedEle;

	@FindBy(css = ".btnn.action__submit.ng-star-inserted")
	WebElement SubmitEle;

	public void selectCountry(String inputCountry) {
		// select country dropdown
		enterCountryEle.sendKeys("ind");
		System.out.println("countryListPopulated :: " + countryListPopulatedEle.size());
		WebElement filteredCountry = countryListPopulatedEle.stream()
				.filter(country -> country.getText().equalsIgnoreCase(inputCountry)).findFirst().orElse(null);
		System.out.println("filteredCountry:::" + filteredCountry.getText());

		filteredCountry.click();
	}

	public OrderConfirmationPage submitMethod() {
		// click 'place order' button
		SubmitEle.click();
		OrderConfirmationPage confirmationPageObj = new OrderConfirmationPage(driver);
		return confirmationPageObj;
	}

}
