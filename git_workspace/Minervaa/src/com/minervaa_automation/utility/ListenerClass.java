package com.minervaa_automation.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClass extends BrowserInitialization implements ITestListener
{

	public void onTestStart(ITestResult result) 
	{
		// TODO Auto-generated method stub		
	}
	
	public static String resultVariable; 

	@Override
	public void onTestSuccess(ITestResult result)
	{
		try
		{
			System.out.println(TestNGCreator.finalResultPath + "/iteration_results.txt" );
			FileWriter writer = new FileWriter(new File(TestNGCreator.finalResultPath + "/iteration_results.txt"));
			writer.write("True"); 
		    writer.flush();
		    writer.close();
		    resultVariable = "Pass";  
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		try
		{
			System.out.println(TestNGCreator.finalResultPath + "/iteration_results.txt");
			FileWriter writer = new FileWriter(new File(TestNGCreator.finalResultPath + "/iteration_results.txt"));
			writer.write("False"); 
		    writer.flush();
		    writer.close();
		    resultVariable= "Fail";
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Failed in  creating result text file");
			e.printStackTrace();
		} 
		/*
		try
		{
            Path path = Paths.get(TestNGCreator.finalResultPath+"\\"+TestNGCreator.tcID+"_"+TestNGCreator.region+"_01_ScreenShots");
            Files.createDirectories(path);
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // The below method will save the screen shot in d drive with name
            // "screenshot.png"
            FileUtils.copyFile(scrFile,
                         new File(path+"\\screenshot_"+ new SimpleDateFormat("yyyy-MMM-dd(hh:mm:ss)").format(Calendar.getInstance().getTime())
                                       + ".jpg"));
            // System.out.println(TestNGCreator.finalResultPath+"\\iteration
            // results.txt");
            FileWriter writer = new FileWriter(new File(TestNGCreator.finalResultPath + "\\iteration results.txt"));
            writer.write("False");
            writer.flush();
            writer.close();
            resultVariable = "Fail";
     	} 
     	catch (IOException e) 
     	{
            // TODO Auto-generated catch block
            e.printStackTrace();
     	}*/
	}

	@Override
	public void onTestSkipped(ITestResult result)
	{
		try
		{
			System.out.println(TestNGCreator.finalResultPath + "/iteration_results.txt");
			FileWriter writer = new FileWriter(new File(TestNGCreator.finalResultPath + "/iteration_results.txt"));
			writer.write("Skipped"); 
		    writer.flush();
		    writer.close();
		    resultVariable = "SKIPPED";
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onStart(ITestContext context) 
	{
		System.out.println("started");
	}

	@Override
	public void onFinish(ITestContext context) 
	{
		System.out.println("finished");
	}
}
