package Automation.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Automation.PageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver = null;
	public LandingPage landingPageObj;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Automation//resources//GlobalData.properties");
		prop.load(file);
		//jave ternary operator
		String browserName = System.getProperty("browser")!=null?System.getProperty("browser") : prop.getProperty("browser");
		System.out.println("browserName : " + browserName);
		if (browserName.contains("chrome")) {
			// WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless")) {
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));//maximmize


		} else if (browserName.equalsIgnoreCase("firefox")) {
			// firefor browser
			driver = new FirefoxDriver();
		}else if (browserName.equalsIgnoreCase("edge")) {
			// firefor browser
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	/*
	 * @BeforeMethod public LandingPage launchApplication() throws IOException {
	 * driver = initializeDriver(); LandingPage landingPageObj = new
	 * LandingPage(driver); landingPageObj.goTo(); return landingPageObj; }
	 */

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApp() throws IOException {
		driver = initializeDriver();
		landingPageObj = new LandingPage(driver);
		landingPageObj.goTo();
		return landingPageObj;
	}

	public List<HashMap<String, String>> getJsonToMap(String strFilePath) throws IOException {

		// read json to string
		System.out.println("inside getJsonToMap.....");
		String jsonContent = FileUtils.readFileToString(new File(strFilePath), StandardCharsets.UTF_8);

		// convert string to hashmap using Jackson
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		System.out.println("before return data set");
		return data;
		// {map,map}

	}
	
	//take screenshot only on filure testcases
		public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
			// Screenshot
			String filePath = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".jpeg";
			TakesScreenshot ts = (TakesScreenshot) driver;
			File file = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(filePath));
			return filePath;
		}

}
