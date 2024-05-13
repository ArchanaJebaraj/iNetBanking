package com.inetbanking.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.inetbanking.testCases.BaseClass;

public class Reporting  implements ITestListener {
	
	  public ExtentSparkReporter sparkReporter;
	  
	  public ExtentTest test; ExtentReports extent = new ExtentReports(); String
	  repName;
	  
	  public void onStart() { String timeStamp=new
	  SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	  repName="Test-Report-"+timeStamp+".html"; sparkReporter=new
	  ExtentSparkReporter("C:\\Users\\XCITE\\eclipse-workspace\\SeleniumAutomationTesting\\reports");
	  System.out.println("test path");
	  sparkReporter.config().setDocumentTitle("Inet banking");
	  sparkReporter.config().setReportName("Selenium Automation");
	  sparkReporter.config().setTheme(Theme.DARK); ExtentReports extent = new
	  ExtentReports(); extent.attachReporter(sparkReporter);
	  extent.setSystemInfo("Application", "Banking Application");
	  extent.setSystemInfo("Operating System", System.getProperty("os.name"));
	  extent.setSystemInfo("user name", System.getProperty("user.name"));
	  extent.setSystemInfo("Environment", "QA"); extent.setSystemInfo("user",
	  "Archana");
	  
	  } public void onTestSuccess(ITestResult result) {
	  
	  test=extent.createTest(result.getName());
	  test.assignCategory(result.getMethod().getAfterGroups());
	  test.createNode(result.getName()); test.log(Status.PASS, "Test passed");
	  
	  } public void onTestFailure(ITestResult result) {
	  test=extent.createTest(result.getName()); test.createNode(result.getName());
	  test.assignCategory(result.getMethod().getAfterGroups());
	  test.log(Status.FAIL, "Test Failed"); test.log(Status.FAIL,
	  result.getThrowable().getMessage());
	  
	  } public void onTestSkipped(ITestResult result) {
	  test=extent.createTest(result.getName()); test.createNode(result.getName());
	  test.assignCategory(result.getMethod().getAfterGroups());
	  test.log(Status.SKIP, "Test Skipped"); test.log(Status.SKIP,
	  result.getThrowable().getMessage());
	  
	  }
	  
	  public void onFinish(ITestContext testContext) { extent.flush(); }
	 

}