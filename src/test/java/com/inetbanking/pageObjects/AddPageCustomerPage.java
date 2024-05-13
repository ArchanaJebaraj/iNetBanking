package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddPageCustomerPage {
	public AddPageCustomerPage(WebDriver rdriver )
	{
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="/html/body/div[3]/div/ul/li[2]/a")
	WebElement linkNewAddCustomer;
	
	@FindBy(xpath="//input[@name='name']")
	WebElement txtCustomerName;
	
	@FindBy(xpath="//input[@value='f']")
	WebElement rdGender;
	
	@FindBy(xpath="//input[@name='dob']")
	WebElement txtDob;
		
	@FindBy(xpath="//textarea[@name='addr']")
	WebElement txtAddress;
	
	@FindBy(xpath="//input[@name='city']")
	WebElement txtCity;
	
	@FindBy(xpath="//input[@name='state']")
	WebElement txtState;
	
	@FindBy(xpath="//input[@name='pinno']")
	WebElement txtPinNo;
	
	@FindBy(xpath="//input[@name='telephoneno']")
	WebElement txtTelePhontNo;
	
	@FindBy(xpath="//input[@name='emailid']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@name='sub']")
	WebElement btnSub;
	
	
	public void clickNewAddNewCutomer()
	{
		linkNewAddCustomer.click();
		//linkNewAddCustomer.click();
	}
	
	public void customerName(String cname)
	{
		txtCustomerName.sendKeys(cname);
	}
	
	public void cusGender()
	{
		rdGender.click();
	}
	public void custdob(String mm,String dd,String yy) {
		txtDob.sendKeys(mm);
		txtDob.sendKeys(dd);
		txtDob.sendKeys(yy);
		
	}
	
	public void custAddress(String custAddress) {
		txtAddress.sendKeys(custAddress);
		
	}
	public void custCity(String custCity) {
		txtCity.sendKeys(custCity);
		
	}
	public void custState(String custState) {
		txtState.sendKeys(custState);
		
	}
	public void custPinNo(String custPinNo) {
		txtPinNo.sendKeys(custPinNo);
	
	}
	
	public void custTeleNo(int custTeleNo) {
		txtTelePhontNo.sendKeys(String.valueOf(custTeleNo));

	}
	public void custEmail(String custEmail) {
		txtEmail.sendKeys(custEmail);
		}
	
	public void custPassword(String custPassword) {
		txtPassword.sendKeys(custPassword);
		
	}
	
	public void clickSub() {
		btnSub.click();
		
	}
	
	
}
