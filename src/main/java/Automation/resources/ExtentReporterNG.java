package Automation.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {


	public static ExtentReports getReportObject() {
		// ExtentReports, ExtentSparkReporter
		// creates reports
		String filePath = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter report = new ExtentSparkReporter(filePath);
		report.config().setReportName("Web Automation Results");
		report.config().setDocumentTitle("Test Results");

		// attach created report to main calss responsible to create and consolidate all
		// testcase execution
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Tester", "Tamil");
		return extent;

	}

}
