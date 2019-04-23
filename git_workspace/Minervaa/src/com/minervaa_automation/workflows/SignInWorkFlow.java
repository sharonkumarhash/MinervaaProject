package com.minervaa_automation.workflows;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.minervaa_automation.utility.TestDataField;
import com.minervaa_automation.utility.TestNGCreator;

public class SignInWorkFlow extends BaseFlow{
	 
		public void emailFieldValidation(WebDriver driver) throws Exception{
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			System.out.println("Executing Sign in page email validation Method");
			
			try{
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(SIgnInPageobj.emailTextBox())).sendKeys(TestNGCreator.TestData.get(TestDataField.Email.ordinal()));
				
			}catch(Exception e){
				e.printStackTrace();
				Assert.fail("failed while executing Sign in page email validation Method: "+e);
			} 
		}
}
