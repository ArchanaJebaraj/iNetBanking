package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddPageCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass {
	@Test
	public void addNewCustomer() throws InterruptedException, IOException {
		test = extent.createTest("Add customer", "Adding customer details");
		LoginPage lp=new LoginPage(driver);
		driver.get(baseURL);
		lp.setUserName(userName);
		lp.setPassword(password);
		lp.clickSubmit();
		Thread.sleep(3000);
		
		AddPageCustomerPage acp=new AddPageCustomerPage(driver);
		Thread.sleep(3000);
		acp.clickNewAddNewCutomer();
		Thread.sleep(6000);
		acp.customerName("Archana");
		acp.cusGender();
		acp.custdob("10", "15", "1995");
		
		acp.custAddress("INDIA");
		acp.custCity("tamil nadu");
		acp.custState("AP");
		acp.custPinNo("5000064");
		acp.custTeleNo(567899);
		acp.custEmail(randomString()+"@gamil.com");
		acp.custPassword("abcdefgh");
		acp.clickSub();
	    Thread.sleep(1000);
	    catureScreenShort(driver,"Add customer");
		
		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
		if(res==true) {
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
	}
	
	
	

}
