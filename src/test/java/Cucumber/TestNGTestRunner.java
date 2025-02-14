package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "C:/Users/tamil/eclipse-workspace/SelenumFrameWorkDesign/src/test/java/Cucumber", 
				 glue = "Automation.StepDefinition",monochrome = true, plugin = {"html:targets/cucumber.html"}
				 ,tags="@Regression")
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
	

}
