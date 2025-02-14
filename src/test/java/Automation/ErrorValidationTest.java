package Automation;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Automation.AbstractComponentsUtil.AbstractComponent;
import Automation.PageObjects.CartPage;
import Automation.PageObjects.ProductCatalogue;
import Automation.TestComponents.BaseTest;
import Automation.TestComponents.Retry;

public class ErrorValidationTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws IOException {

		landingPageObj.loginMethod("anshika@gmail.com", "Iamkin@000");
		Assert.assertEquals("Incorrect email or password.", landingPageObj.getErrorMessage());

	}

	@Test(retryAnalyzer = Retry.class)
	public void productErrorValidation() {
		ProductCatalogue productPageObj = landingPageObj.loginMethod("anshika@gmail.com", "Iamking@000");
		String productName = "ZARA COAT 3";//"Banarsi Saree";
		// in Add to cart opage
		WebElement eleProduct = productPageObj.getProductName(productName);
		Boolean flag=false;
		if (eleProduct != null && eleProduct.getText().contains(productName)) {
			flag=true;
		}
		Assert.assertTrue(flag);;

	}

}
