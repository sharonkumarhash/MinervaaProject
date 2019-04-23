package com.minervaa_automation.pages;

import org.openqa.selenium.By;

import com.minervaa_automation.utility.CommonUtility;

public class SignInPage extends Pojo {
	
	public By emailTextBox() throws Exception{
		 return CommonUtility.getLocator(SignInPage_Data.get("EmailTextBox"));
	}
   
}
