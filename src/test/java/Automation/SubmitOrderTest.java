package Automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Automation.PageObjects.CartPage;
import Automation.PageObjects.CheckOutPage;
import Automation.PageObjects.OrderConfirmationPage;
import Automation.PageObjects.OrdersPage;
import Automation.PageObjects.ProductCatalogue;
import Automation.TestComponents.BaseTest;
import Automation.TestComponents.Retry;

public class SubmitOrderTest extends BaseTest {

//	@Test(dataProvider = "getData", groups = { "Purchase" })

//	public void submitOrder(String userName,String password,String productName) throws IOException, InterruptedException { // TODO Auto-generated method stub
	@Test(dataProvider = "getData",retryAnalyzer = Retry.class)
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException { // TODO
																										// Auto-generated
																										// method stub
//		String productName = "ZARA COAT 3";//"Banarsi Saree";
		String actualMsg = "Thankyou for the order.";

		// login page
		// LandingPage landingPageObj = launchApp();

		// LandingPage landingPageObj = new LandingPage(initializeDriver());
		// landingPageObj.goTo();
		System.out.println(landingPageObj.toString());

		ProductCatalogue productPageObj = landingPageObj.loginMethod(input.get("userName"), input.get("password"));
//		ProductCatalogue productPageObj = landingPageObj.loginMethod(userName,password);

		// in Add to cart opage
//		productPageObj.AddProductToCart(productName);
		productPageObj.AddProductToCart(input.get("productName"));

		// click gotoCart page
		CartPage cartPageObj = productPageObj.GoToCartPage();

		// check if the Cart screen has the productName we added
//		boolean isItemFound = cartPageObj.verifyProductDisplay(productName);
		boolean isItemFound = cartPageObj.verifyProductDisplay(input.get("productName"));

		AssertJUnit.assertTrue(isItemFound);

		// checkout button
		CheckOutPage checkoutPageObj = cartPageObj.checkOutMethod();

		// checkOutPage
		// select country dropdown and click 'place orde' button
		checkoutPageObj.selectCountry("india");
		// submit button
		OrderConfirmationPage confirmationPageObj = checkoutPageObj.submitMethod();

		// final page
		Boolean flag = confirmationPageObj.getOrderPlacedMsg(actualMsg);

		Assert.assertTrue(flag);
		// driver.close();

	}

	// verify if ZARA coat 3 is displaying in Orders Placed page
	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryPage() {
		String productName = "ZARA COAT 3";// "Banarsi Saree";

		ProductCatalogue productPageObj = landingPageObj.loginMethod("anshika@gmail.com", "Iamking@000");
		OrdersPage ordersPage = productPageObj.GoToOrdersPage();
		Boolean flag = ordersPage.verifyOrderDisplay(productName);
		Assert.assertTrue(flag);

	}

	// used to drives data and pass multiple data sets
	@DataProvider
	public Object[][] getData() throws IOException {

		// format 1-------
		// return new Object[][] {{"anshika@gmail.com","Iamking@000","ZARA COAT
		// 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};

		// format 2--
		/**
		 * HashMap<Object, Object> map = new HashMap<Object, Object>();
		 * map.put("userName", "anshika@gmail.com"); map.put("password", "Iamking@000");
		 * map.put("productName", "ZARA COAT 3");
		 * 
		 * HashMap<Object, Object> map1 = new HashMap<Object, Object>();
		 * map1.put("userName", "anshika@gmail.com"); map1.put("password",
		 * "Iamking@000"); map1.put("productName", "ADIDAS ORIGINAL");
		 * 
		 * // return new Object[][] { { map }, { map1 } };
		 **/
		// formatr 3
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\Automation\\Data\\PurchaseOrder.json";
		System.out.println("filePath ::: " + filePath);
		List<HashMap<String, String>> data = getJsonToMap(filePath);

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

	
	
	//Extent Reports
}
