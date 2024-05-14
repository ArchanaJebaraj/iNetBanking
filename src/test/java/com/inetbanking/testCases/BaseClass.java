package com.inetbanking.testCases;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentReporter;
import com.inetbanking.Utilities.ReadConfig;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass {

	/*
	 * public String baseURL="https://demo.guru99.com/v4/index.php"; public String
	 * userName="mngr569806"; public String password="jEhYjyr";
	 */
	ReadConfig readConfig=new ReadConfig();
	public String baseURL=readConfig.getApplicationUrl();
	public String userName=readConfig.getUserName();
	public String password=readConfig.getPassword();
	
	public static WebDriver driver;
	 public static Logger logger;
	 public static ExtentHtmlReporter htmlReporter;
	 public static ExtentReports extent;
	 public static ExtentTest test;
	
	 
	 @BeforeTest
	    public void startReport() {
	        // initialize the HtmlReporter
	        htmlReporter = new ExtentHtmlReporter("C:\\Users\\XCITE\\eclipse-workspace\\SeleniumAutomationTesting\\reports\\"+timestamp()+".html");
	        //htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testReport.html");
	      	        //initialize ExtentReports and attach the HtmlReporter
	        extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);
	  
	        //configuration items to change the look and feel
	        //add content, manage tests etc
	       //htmlReporter.config().setChartVisibilityOnOpen(true);
	        htmlReporter.config().setDocumentTitle("Simple Automation Report");
	        htmlReporter.config().setReportName("Test Report");
	       //htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	        htmlReporter.config().setCSS(".r-img { width: 20%; }");
	        extent=new ExtentReports();
	        extent.attachReporter(htmlReporter);
	        
	        htmlReporter.config().setTheme(Theme.DARK);
	        htmlReporter.config().setTheme(Theme.STANDARD);
	        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	    }
	 
	@BeforeClass
	@Parameters("browser")
	public void setup(String br) {
			 		
		 Logger logger=Logger.getLogger("ebanking");
		 PropertyConfigurator.configure("Log4j.properties");
		 
		 if(br.equals("chrome")) {
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\XCITE\\eclipse-workspace\\SeleniumAutomationTesting\\Drivers\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+readConfig.getChromePath());
			 driver=new ChromeDriver();
		 }
		 else if(br.equals("firefox")) {
			 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+readConfig.getFirefoxPath());
			 driver=new FirefoxDriver(); 
		 }
		 else if(br.equals("ie")) {
			 System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+readConfig.getIePath());
			 driver=new InternetExplorerDriver(); 
		 }
		}
	
	public void catureScreenShort(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target=new File("C:\\Users\\XCITE\\eclipse-workspace\\SeleniumAutomationTesting\\ScreenShots\\"+timestamp()+".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
		
		
	}

	public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }
	
	public String randomString() {
		String generatedString=RandomStringUtils.randomAlphabetic(8);
		return generatedString;
		
	}
	
	public static String CaptureScreenshot(WebDriver driver) throws IOException {
		/*
		 * String FileSeparator = System.getProperty("file.separator"); String
		 * Extent_report_path = "."+FileSeparator+"Reports"; String ScreenshotPath =
		 * Extent_report_path+FileSeparator+"screenshots";
		 * 
		 * File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); String
		 * screenshotName = "screenshot"+Math.random()+".png"; String screenshotpath =
		 * ScreenshotPath+FileSeparator+screenshotName;
		 * 
		 * FileUtils.copyFile(src,new File(screenshotpath)); return
		 * "."+FileSeparator+"screenshots"+FileSeparator+screenshotName;
		 */
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"ScreenShots\\"+timestamp()+".png";
		File target=new File(path);
		try {
		FileUtils.copyFile(source, target);
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		 return path;
		    }
	
	@AfterMethod
    public void getResult(ITestResult result) throws IOException {
        if(result.getStatus() == ITestResult.FAILURE) {
        	 test.addScreenCaptureFromPath(CaptureScreenshot(driver));
               test.log(Status.FAIL,result.getThrowable());
           
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
        	 test.addScreenCaptureFromPath(CaptureScreenshot(driver));
            test.log(Status.PASS, result.getTestName());
           
        }
        else {
            test.log(Status.SKIP, result.getTestName());
        }
    }
	@AfterClass()
	public void tearDown() {
		
		extent.flush();
		driver.quit();
	}
	
	
}
