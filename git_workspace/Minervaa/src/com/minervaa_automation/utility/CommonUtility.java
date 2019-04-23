package com.minervaa_automation.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CommonUtility 
{

	static Properties obj_property;
	public static String alertMessage;
		
	//Generic method
	//This method is developed to handle any changes with respect to locator
	//This method receives the object property in the form "loactortype:locatorValue" and returns appropriate locator 	
	public static By getLocator(String objectProperty) throws Exception 
	{
        try
        {
	    //load the property file 
		obj_property=CommonUtility.loadPropertyFile();
		
		String[] locatorArray=objectProperty.split("%");
		
        String locatorType = locatorArray[0];
        String locatorValue =locatorArray[1];
        //Accessing locators name through property file 
        //and this method will Return a instance of By class based on type of locator
        if(locatorType.toLowerCase().equals(obj_property.getProperty("locatortype1")))
                return By.id(locatorValue);
        else if(locatorType.toLowerCase().equals(obj_property.getProperty("locatortype2")))
                return By.name(locatorValue);
        else if((locatorType.toLowerCase().equals(obj_property.getProperty("locatortype3"))))
                return By.className(locatorValue);
        else if((locatorType.toLowerCase().equals(obj_property.getProperty("locatortype4"))))
                return By.tagName(locatorValue);
        else if((locatorType.toLowerCase().equals(obj_property.getProperty("locatortype5"))))
                return By.linkText(locatorValue);
        else if(locatorType.toLowerCase().equals(obj_property.getProperty("locatortype6")))
                return By.partialLinkText(locatorValue);
        else if((locatorType.toLowerCase().equals(obj_property.getProperty("locatortype7"))))
                return By.cssSelector(locatorValue);
        else if(locatorType.toLowerCase().equals(obj_property.getProperty("locatortype8")))
                return By.xpath(locatorValue);
        else
                throw new Exception("Locator type '" + locatorType + "' not defined!!");
    }
	catch(Exception e)
    {
		System.out.println("Caught exception while executing getLocator method from utility"+e);
		e.printStackTrace();
		return null;
	}
}

	
	//this method will load property file 
        public static Properties loadPropertyFile() throws IOException
        {
        	
        	obj_property=new Properties();
        	FileInputStream propertyFilePath=new FileInputStream(TestNGCreator.configfolder);
   		    obj_property.load(propertyFilePath);
   		   
   		    return obj_property;
        }
        
 //--------------------------Checking Empty Field---------------------
        public static String check_Is_StringEmty(String fieldName,String value){
          
        	String fieldValue="";
            try{
                  
                  if(value.isEmpty()){
                         
                          System.out.println(fieldName+"  field is empty");
                          fieldValue=fieldName+"  field is empty";
                  }
                  
                   else{
                  System.out.println(fieldName+" : "+value);
                  fieldValue=fieldName+" : "+value;
                  }
                  return  fieldValue; 
                  
            }
            catch(Exception e){
                  
                  System.out.println("filed is null and filed name is: "+fieldName);
                  return  fieldValue;
            
            }
     }

        
        
//-------------------------Highlight Element method---------------
	public static WebElement highlightElement(WebDriver driver, WebElement element)
	{
			JavascriptExecutor js=(JavascriptExecutor)driver; 
			js.executeScript("arguments[0].style.border='4px groove green'", element); 

			try 
			{
				Thread.sleep(500);
			} 
			catch (InterruptedException e) 
			{
				System.out.println(e.getMessage());
			}
			js.executeScript("arguments[0].setAttribute('style','border: solid 2px white')", element); 
			
		return element;
		
	}
		
	
		public static WebElement highlightElement(WebDriver driver, By byLocator)
		{
			WebElement element = driver.findElement(byLocator);
			JavascriptExecutor js=(JavascriptExecutor)driver; 
			js.executeScript("arguments[0].style.border='4px groove green'", element); 

			try 
			{
				Thread.sleep(500);
			} 
			catch (InterruptedException e)
			{
				System.out.println(e.getMessage());
			}
			js.executeScript("arguments[0].style.border=''", element); 
			
		return element;
		
	}
		
		//This method is used to scrolling upto that element is present
				public static WebElement scrollToViewElement(WebDriver driver,By locatorType){
				
				try{	
				WebElement elementNameThatNeedToBeScrolled = driver.findElement(locatorType);
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementNameThatNeedToBeScrolled);
				Thread.sleep(500);
				return elementNameThatNeedToBeScrolled;
				}
				
				catch(Exception e){
					
					e.printStackTrace();
					Assert.fail("Failed to scroll:  \n",e);
					return null;
				}
				
				}
				
				
	//--------------------Alert Handle----------
				
				public static void handleAlert(WebDriver driver){
					 try{
							//System.out.println("switched to alert");
							Alert alert=driver.switchTo().alert();
							
							System.out.println("Alert Message:"+alert.getText());
							
							alert.accept();
							
							
						}
						catch(NoAlertPresentException e){
						}
					
				
				} 
				
				
				public static boolean isAlertPresent(WebDriver driver){ 
					try{ 
			               Alert a = new WebDriverWait(driver, 15).until(ExpectedConditions.alertIsPresent());
			               if(a!=null){
			                   System.out.println("Alert is present");
			                   alertMessage=a.getText();
			                   System.out.println(a.getText());
			                   driver.switchTo().alert().accept();
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

				
				//--------------------------------Compare Result-------------------
				
				
                public static boolean compareResults(ArrayList<String> actualValue,ArrayList<String> expectedValue){
                    boolean isResultAsExpected=false;
                    if(actualValue.size()==expectedValue.size()){
                                    
                                    for(int i=0;i<actualValue.size();i++){
                                                    
                                                    if(actualValue.get(i).equals(expectedValue.get(i))){
                                                                    
                                                                    System.out.println("Actual value:"+actualValue.get(i)+" Expected value:"+expectedValue.get(i));
                                                                    System.out.println("Result is as expected");
                                                                    isResultAsExpected=true;
                                                    }
                                                    else{
                                                                    
                                                                    System.out.println("Actual value:"+actualValue.get(i)+" Expected value:"+expectedValue.get(i));
                                                                    System.out.println("Result is as not as expected");
                                                                    isResultAsExpected=false;
                                                                    break;
                                                    }
                                                    
                                                    
                                    }
                                    
                                    
                                   
                    }
                    
                    return isResultAsExpected;   
    }


}
