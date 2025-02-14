package Automation.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Automation.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<ExtentTest>(); //thread safe
	
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test=extent.createTest(result.getMethod().getMethodName());
		extentTestThreadLocal.set(test);
		System.out.println("on test start : "+(result.getMethod().getMethodName()));
		System.out.println("done/.....");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTestThreadLocal.get().log(Status.PASS,  " - Test passed "+result.getMethod().getMethodName());

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentTestThreadLocal.get().fail(result.getThrowable());
		extentTestThreadLocal.get().log(Status.FAIL,"Test failed .TestName : "+result.getMethod().getMethodName() );

		String filePath = null;

		// get webdriver from the result
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());

			filePath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		extentTestThreadLocal.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

		// screenshot
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
