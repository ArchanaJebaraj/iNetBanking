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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.inetbanking.Utilities.ReadConfig;

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
	
	@AfterClass()
	public void tearDown() {
		driver.quit();
	}
	
	
}
