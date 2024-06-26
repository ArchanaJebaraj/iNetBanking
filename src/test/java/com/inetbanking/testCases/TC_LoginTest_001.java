package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;


public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void loginTest() throws IOException {
		test = extent.createTest("login test", "Login test");
		test.info("Assert Test");
		driver.get(baseURL);
		//logger.info("URL is opened");
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(userName);
		lp.setPassword(password);
		lp.clickSubmit();
		catureScreenShort(driver,"login page");
		System.out.println(driver.getTitle());
		test.info("Assert Test");
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
		}
		
		else {
			Assert.assertTrue(false);
		}
		
	}
	
}
