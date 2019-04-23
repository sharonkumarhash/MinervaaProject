package com.minervaa_automation.pages;

import java.util.Hashtable;

import com.minervaa_automation.utility.ReadExcel;
import com.minervaa_automation.utility.TestNGCreator;

public class Pojo {

	public static Hashtable<String,String> SignInPage_Data=null;

	public void storePageDataFromExcel(){
		SignInPage_Data=ReadExcel.getEntireExcelSheetData(TestNGCreator.folderPath, "SignInPage", "Object Name/Label Name", "Locator Type%Object Properties");
	}
}
