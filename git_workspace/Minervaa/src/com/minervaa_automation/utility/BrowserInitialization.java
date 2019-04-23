package com.minervaa_automation.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.minervaa_automation.pages.Pojo;

public class BrowserInitialization{

	Hashtable<String,String> runMode;
	public WebDriver driver=null;	
	
	Properties obj_property;

	Pojo obj_LoginPage_Pojo=null;

	public String userID = System.getProperty("user.name");

	@BeforeSuite
	public void  loadObjectsPropertiesFromExcel()
	{		
		obj_LoginPage_Pojo=new Pojo();
		obj_LoginPage_Pojo.storePageDataFromExcel();	
	}

	@BeforeClass
	public  void setBrowser() throws IOException 
	{
		
		obj_property=CommonUtility.loadPropertyFile();
		System.setProperty(obj_property.getProperty("setPropertyForChromeDriver"), obj_property.getProperty("chromeDriverForLinuxPath"));
		driver=new ChromeDriver();
		driver.manage().window().maximize();

		driver.get(obj_property.getProperty("url"));
	}
		
	@AfterMethod
	public void activitiesAfterExecutingTC(ITestResult testResult)
	{
		try 
		{			
			if (testResult.getStatus() == ITestResult.FAILURE) 
			{ 
				Path path = Paths.get(TestNGCreator.finalResultPath+"/"+TestNGCreator.tcID+"_"+"_01_ScreenShots");
				Files.createDirectories(path);
                       
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				// The below method will save the screen shot in d drive with name
				// "screenshot.png"
				FileUtils.copyFile(scrFile,new File(path+"/screenShot"+".jpg"));
            
				//+new SimpleDateFormat("yyyy-MMM-dd(hh:mm:ss)").format(Calendar.getInstance().getTime())
            
				//FileUtils.copyFile(scrFile,new File("C:\\Users\\Narasimhamurthy_SJ\\Desktop\\Temp\\GSD_Automation\\Object_Data_Repository\\1342105_AMER_01_ScreenShots\\screenshot"+ ".jpg"))     
			}
		} 
		catch (Exception e) 
		{
            // TODO Auto-generated catch block
			System.out.println("Failed while taking screen shot"+e);
            e.printStackTrace();
		}
	}
	
	//close the browser
	@AfterSuite
	public void terminateSettings()
	{
		//driver.quit();
		Report.header();
		Report.closure();
	}
}