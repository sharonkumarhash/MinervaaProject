package com.minervaa_automation.workflows;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.minervaa_automation.pages.SignInPage;
import com.minervaa_automation.utility.CommonUtility;

public class BaseFlow {
	
	public SignInPage SIgnInPageobj = new SignInPage();
	
	public static String alertText;
	public boolean isAlertPresent(WebDriver driver){ 
	    try{ 
	        Alert a = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
	        if(a!=null){
	            System.out.println("Alert is present");
	            System.out.println(a.getText());
	            driver.switchTo().alert().accept();
	            alertText=a.getText();
	            return true;
	        }else{
	            throw new Throwable();
	        }
	    } 
	    catch (Throwable e) {
	        //System.err.println("Alert isn't present!!");
	        return false; 
	    } 

	}
	
	
	public void verify(WebDriver driver,WebElement element){
		
		if(element!= null){
			CommonUtility.highlightElement(driver, element);
		}else{
			System.out.println("Element is Absent");
		}
	}
		
		public static WebDriverWait wait;
		public static int alertcount=1;

		public void genericWait(WebDriver driver){
			wait = new WebDriverWait(driver, 240);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		}
	
}

