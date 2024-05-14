package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.Utilities.XLUtility;
import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_002 extends BaseClass {


	@Test(dataProvider="loginDataValue") 
	       public void loginTest(String userData, String
			pwdData) throws InterruptedException {
		test = extent.createTest("login test 2", "From external report");
		    LoginPage lp=new LoginPage(driver);
		    driver.get(baseURL);
			lp.setUserName(userData); 
			lp.setPassword(pwdData); 
			lp.clickSubmit();
			System.out.println("test    "+driver.switchTo().alert().getText());
			Assert.assertEquals(driver.switchTo().alert().getText(), "User or Password is not valid");
			driver.switchTo().alert().accept();
			
	}
	
	@DataProvider(name="loginDataValue")
	Object [][] getData() throws IOException{

		String path="C:\\Users\\XCITE\\eclipse-workspace\\SeleniumAutomationTesting\\src\\test\\java\\com\\inetbanking\\testData\\LoginData.xlsx";
		int rowNum=XLUtility.getRowCount(path,"Sheet1");
		int colCount=XLUtility.getCellCount(path, "Sheet1", rowNum);
		String loginData[][]=new String [rowNum][colCount];
		for(int i=1;i<=rowNum;i++) {
			for(int j=0;j<colCount;j++) {
				loginData[i-1][j]=XLUtility.getCellData(path, "Sheet1", i, j);

			}
		}
		return loginData;

	}

}


