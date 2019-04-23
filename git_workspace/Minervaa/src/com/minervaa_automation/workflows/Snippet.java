package com.minervaa_automation.workflows;

public class Snippet 
{

}

/*package com.dell.delta_uat_automation.workflows;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dell.delta_uat_automation.utility.CommonUtility;

public class Snippet {
		//---------------------entitlement validation method-------------------------
		
		public void entitlementValidationInsideAssetsPage(WebDriver driver) throws Exception
		{
	
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 120);
			
			try
			{
				
				System.out.println("Entered into Entitlement Validation Inside Assets Page........");
				
				wait.until(ExpectedConditions.elementToBeClickable(AssetsPageobj.lnkEntitlementLinkInsideAssetsPage()));
				CommonUtility.highlightElement(driver, AssetsPageobj.lnkEntitlementLinkInsideAssetsPage()).click();
				System.out.println("Clicked on Entitlement Link.........");
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(AssetsPageobj.txtEntitlementStatus()));
				String entitlementStatus=driver.findElement(AssetsPageobj.txtEntitlementStatus()).getAttribute("title").trim();
				
				if(entitlementStatus.equals("Green"))
				{
				System.out.println("Service Tag is in Warranty");
				}
				else
					{
						System.out.println("Entitlement Status Color:"+entitlementStatus);
					}
					
				System.out.println("Entitlement Validation Inside Assets Page is Validated Successfully.........");
					
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Assert.fail("Entitlement Validation failed: "+e);
			} 
		
		}
		
		
		
		
		
		
		
		//---------------------------------------handle Pop-up----------------------------------------
		 
			public void handledAcknowledgePopup(WebDriver driver) throws Exception
	
			{
				
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(driver, 120);
				System.out.println("Entering Acknowledge handle Method");
				try{
					
					wait.until(ExpectedConditions.presenceOfElementLocated(HomePageobj.acknowledgeButton()));
					wait.until(ExpectedConditions.visibilityOfElementLocated(HomePageobj.acknowledgeButton()));
					CommonUtility.highlightElement(driver, HomePageobj.acknowledgeButton()).click();
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
					Assert.fail("failed while handling Acknoledgement Pop-Up: "+e);
				} 
	
		
			}
		
		
		
		
		public void ServiceTagSearch(WebDriver driver,String svcTag) throws IOException, InterruptedException
		{
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 120);
			
					
			try
			{
				
				System.out.println("Entered into ServiceTagSearch method........");
				
				
				wait.until(ExpectedConditions.elementToBeClickable(HomePageobj.lnkAssetsTab()));
				wait.until(ExpectedConditions.visibilityOfElementLocated(HomePageobj.lnkAssetsTab()));
				CommonUtility.highlightElement(driver, HomePageobj.lnkAssetsTab()).click();
				
				Thread.sleep(5000);//10000
				//Waiting 60sec for the object servicetag textbox to be loaded
				wait.until(ExpectedConditions.presenceOfElementLocated(AssetsPageobj.txtServiceTag()));
				WebElement serviceTagElement=driver.findElement(AssetsPageobj.txtServiceTag());
				if(serviceTagElement.isDisplayed())
				{
					System.out.println("ServiceTag textbox is displayed..........");
					driver.findElement(AssetsPageobj.txtServiceTag()).sendKeys(svcTag);
					driver.findElement(AssetsPageobj.txtServiceTag()).sendKeys(Keys.ENTER);
					wait.until(ExpectedConditions.presenceOfElementLocated(AssetsPageobj.getCustomerNumber()));
					wait.until(ExpectedConditions.visibilityOfElementLocated(AssetsPageobj.getCustomerNumber()));
					String getcustNum=driver.findElement(AssetsPageobj.getCustomerNumber()).getAttribute("value").trim();
					WebElement getCustomerNumberElement = driver.findElement(By.xpath("//input [@aria-labelledby='ISP_Customer_Number_Label']/.."));
					CommonUtility.highlightElement(driver, getCustomerNumberElement);
	
					if(getcustNum==null || getcustNum=="")
					{
						System.err.println("Service Tag Search Failed : "+svcTag);
						Assert.fail("Service tag serach failed: ");
						
	
					}
					else
					{
						System.out.println("Service Tag Search Passed : "+svcTag);
					}
				}
				else
				{
					System.err.println("Service Tag Search box is not visible............");
	
				}			
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
				Assert.fail("Service tag serach failed: "+e);
				
			}
			
		}
		
		
		
		
		
		
		
		
		
		
	
		public void SR_Creation(WebDriver driver, String Title, String DiagnosisTier1, String DiagnosisTier2, String DiagnosisTier3, String DiagnosisTier4)
		{
			
			
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 80);		
			System.out.println("Entered into the Service Request Creation Method...........");
			try
			{
				Thread.sleep(10000);//4000
	//			driver.findElement(AssetsPageobj.btnNew_SRCreation()).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Active SR:New']")));
				WebElement btnNew_SRCreationElement = driver.findElement(By.xpath("//button[@aria-label='Active SR:New']"));
				
				
				
				
				
				
				
				WebElement newSRbutton=CommonUtility.highlightElement(driver, AssetsPageobj.btnNew_SRCreation());
				wait.until(ExpectedConditions.elementToBeClickable(AssetsPageobj.btnNew_SRCreation())).click();
				
				
				
				
	
			    int attempts = 0;
		        while(attempts < 4) {
		            try {
		            	newSRbutton.isDisplayed();
		               // result = true;
		                break;
		            } catch(Exception e) {
		            	Thread.sleep(2000);
		            }
		            
		            attempts++;
		        }
				
				
				
				
				
				
				Thread.sleep(4000);
				wait.until(ExpectedConditions.visibilityOfElementLocated(AssetsPageobj.txtSRTitle()));
				WebElement srtitle=driver.findElement(AssetsPageobj.txtSRTitle());
				if(srtitle.isDisplayed())
				{
					
					Thread.sleep(3000);
					wait.until(ExpectedConditions.elementToBeClickable(AssetsPageobj.SRType())).clear();
					driver.findElement(AssetsPageobj.SRType()).sendKeys("Incident");
					driver.findElement(AssetsPageobj.SRType()).sendKeys(Keys.TAB);
					
					wait.until(ExpectedConditions.elementToBeClickable(AssetsPageobj.txtSRTitle())).clear();
					driver.findElement(AssetsPageobj.txtSRTitle()).sendKeys(Title);
					driver.findElement(AssetsPageobj.txtSRTitle()).sendKeys(Keys.TAB);
					
					driver.findElement(AssetsPageobj.txtDiagnosisTier1()).clear();
					driver.findElement(AssetsPageobj.txtDiagnosisTier1()).sendKeys(DiagnosisTier1);
					driver.findElement(AssetsPageobj.txtDiagnosisTier1()).sendKeys(Keys.ENTER);
					driver.findElement(AssetsPageobj.txtDiagnosisTier2()).clear();
					driver.findElement(AssetsPageobj.txtDiagnosisTier2()).sendKeys(DiagnosisTier2);
					driver.findElement(AssetsPageobj.txtDiagnosisTier2()).sendKeys(Keys.ENTER);
					driver.findElement(AssetsPageobj.txtDiagnosisTier3()).clear();
					driver.findElement(AssetsPageobj.txtDiagnosisTier3()).sendKeys(DiagnosisTier3);
					driver.findElement(AssetsPageobj.txtDiagnosisTier3()).sendKeys(Keys.ENTER);
					driver.findElement(AssetsPageobj.txtDiagnosisTier4()).clear();
					driver.findElement(AssetsPageobj.txtDiagnosisTier4()).sendKeys(DiagnosisTier4);
					driver.findElement(AssetsPageobj.txtDiagnosisTier4()).sendKeys(Keys.ENTER);
					
					
					System.out.println("Entering firmware text field");
					driver.findElement(AssetsPageobj.firmwareVersion()).sendKeys("Not Available",Keys.TAB);
					
					
					
					
					
					
					
					SetContact(driver);
					String sr=driver.findElement(AssetsPageobj.lnkSRNumber()).getAttribute("text").trim();
					CommonUtility.highlightElement(driver, AssetsPageobj.lnkSRNumber());
					if(sr==null || sr=="" )
					{
						System.err.println("SR Creation Failed........");
						Assert.fail("Service Request Creation failed: ");
	
					}
					System.out.println("The Newly Created SR Number is: "+sr);
					
				
				
				
				System.out.println("SR creation completed successfully........................");
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Assert.fail("Service Request Creation failed: "+e);
			}
		}
		
		public void SetContact(WebDriver driver)
		{
			
			WebDriverWait wait = new WebDriverWait(driver, 60);		
			System.out.println("Entered into the SetContact Method...........");
			try
			{
				//clicking on last name icon
			
				CommonUtility.highlightElement(driver, AssetsPageobj.lnkLastNameIcon()).click();
	
				Thread.sleep(3000);
				wait.until(ExpectedConditions.presenceOfElementLocated(AssetsPageobj.btnLastNameMoveRight()));
				
				//clicking on last name move right
				CommonUtility.highlightElement(driver, AssetsPageobj.btnLastNameMoveRight()).click();
				
				//retrievePrimaryContact(driver);
	
				Thread.sleep(3000);
				//clicking on last name ok
				CommonUtility.highlightElement(driver, AssetsPageobj.btnLastNameOK()).click();
				Thread.sleep(5000);
			}
			
			catch(Exception e)
			{
				
				e.printStackTrace();
				Assert.fail("Failed while selecting contact\n"+e);
				//System.err.println("Failed while selecting contact\n");
	
			}
		}
		
		
		
		
		public static String AlternateContactLastName=null;
		public static String AlternateContactFirstName=null;
		public static String AlternateContactPhone=null;
		
		
		public void retrieveAlternateContact(WebDriver driver) throws Exception
	
		{
		
			
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			WebDriverWait wait=new WebDriverWait(driver,150);
			
			try{
				Thread.sleep(2000);
				
				System.out.println("Entered into Retrieve Alternate Contact Method..........");
				
				String AlternateContactLastName=driver.findElement(AssetsPageobj.AlternateContactLastName()).getAttribute("title");
			    System.out.println("Alternate Contact Details, Last Name is: "+AlternateContactLastName);
			    
				String AlternateContactFirstName=driver.findElement(AssetsPageobj.AlternateContactFirstName()).getAttribute("title");
			    System.out.println("Alternate Contact Details, First Name is: "+AlternateContactFirstName);
			    
				String AlternateContactPhone=driver.findElement(AssetsPageobj.AlternateContactPhone()).getAttribute("title");
			    System.out.println("Alternate Contact Details, Phone is: "+AlternateContactPhone);
			    
			}
			
			catch(Exception e){
				System.err.println("Failed while retrieving alternate contact----\n"+e);
			}
		
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		public void alternateContact(WebDriver driver) throws Exception{
			
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			WebDriverWait wait=new WebDriverWait(driver,150);
			Thread.sleep(5000);
			try{
			CommonUtility.highlightElement(driver, obj_ReviewAndSubmitDispatchPage.alternateContactCheeseBox()).click();
			
			Thread.sleep(2000);
			//retrieveAlternateContact(driver);
	
			CommonUtility.highlightElement(driver, obj_ReviewAndSubmitDispatchPage.OkBtnAlternateContact()).click();
			}
			catch(Exception e){
				System.err.println("Failed while selecting alternate contact--------\n"+e);
			}
		}
	
		
		
		
		
		public void commentsToVendor(WebDriver driver) throws Exception
		{
	
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 120);
			
			try
			{
				
				System.out.println("Entered into Comments to vendor Validation........");
			
				
				wait.until(ExpectedConditions.elementToBeClickable(AssetsPageobj.btnCommentsButton()));
				CommonUtility.highlightElement(driver, AssetsPageobj.btnCommentsButton()).click();
				System.out.println("Clicked on Comments.........");
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(AssetsPageobj.valCommentsCreated()));
				String commentsCreated=driver.findElement(AssetsPageobj.valCommentsCreated()).getAttribute("title").trim();
				System.out.println("Comments Created is:"+commentsCreated);
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(AssetsPageobj.valCommentsCreatedBy()));
				String commentsCreatedBy=driver.findElement(AssetsPageobj.valCommentsCreatedBy()).getAttribute("title").trim();
				System.out.println("Comments CreatedBy is:"+commentsCreatedBy);
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(AssetsPageobj.valCommentsReceiver()));
				String commentsReceiver=driver.findElement(AssetsPageobj.valCommentsReceiver()).getAttribute("title").trim();
				System.out.println("Comments Receiver is:"+commentsReceiver);
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(AssetsPageobj.valCommentsType()));
				String commentsType=driver.findElement(AssetsPageobj.valCommentsType()).getAttribute("title").trim();
				System.out.println("Comments Type is:"+commentsType);
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(AssetsPageobj.valCommentsInstruction()));
				String commentsInstruction=driver.findElement(AssetsPageobj.valCommentsInstruction()).getAttribute("title").trim();
				System.out.println("Comments Instruction is:"+commentsInstruction);
				
	
					
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Assert.fail("Comments to vendor Validation failed: "+e);
			}  
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		public static String PrimaryContactLastName=null;
		public static String PrimaryContactFirstName=null;
		public static String PrimaryContactPhone=null;
		public static String PrimaryContactEmail=null;
		
		
		public void retrievePrimaryContact(WebDriver driver) throws Exception
	
		{
		
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			WebDriverWait wait=new WebDriverWait(driver,150);
			
			try{
				Thread.sleep(2000);
				
				System.out.println("Entered into Retrieve Primary Contact Method..........");
				
				String PrimaryContactLastName=driver.findElement(AssetsPageobj.PrimaryContactLastName()).getAttribute("value");
			    System.out.println("Primary Contact Details, Last Name is: "+PrimaryContactLastName);
			    
				String PrimaryContactFirstName=driver.findElement(AssetsPageobj.PrimaryContactFirstName()).getAttribute("value");
			    System.out.println("Primary Contact Details, First Name is: "+PrimaryContactFirstName);
			    
				String PrimaryContactPhone=driver.findElement(AssetsPageobj.PrimaryContactPhone()).getAttribute("value");
			    System.out.println("Primary Contact Details, Phone is: "+PrimaryContactPhone);
			    
				String PrimaryContactEmail=driver.findElement(AssetsPageobj.PrimaryContactEmail()).getAttribute("value");
			    System.out.println("Primary Contact Details, Email is: "+PrimaryContactEmail);
			    
			}
			catch(Exception e){
				System.err.println("Failed while retrieving Primary contact----\n"+e);
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		public void ActivityCreation_Assets(WebDriver driver, String ActivityType, String ActivitySubType)
		{
			WebDriverWait wait = new WebDriverWait(driver, 60);		
			System.out.println("Entered into the ActivityCreation_Assets Method...........");
			try
			{
				//WebElement btnNewButton_ActivityElement = driver.findElement(By.xpath("//button[@aria-label='Activities:New']"));
				CommonUtility.highlightElement(driver, AssetsPageobj.btnNewButton_Activity()).click();
				
				Thread.sleep(2000);//3000
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(AssetsPageobj.txtOwner()));
				CommonUtility.highlightElement(driver, AssetsPageobj.txtOwner()).click();
	
				driver.findElement(AssetsPageobj.txtOwner()).sendKeys(Keys.TAB);
				
				driver.findElement(AssetsPageobj.txtSRnumber()).sendKeys(Keys.TAB);
				//Thread.sleep(2000);
				//entering Activity Type
				System.out.println("Entering Activity Type.....");
				
				
				
				driver.findElement(AssetsPageobj.ddlActivityType()).sendKeys(ActivityType);
				driver.findElement(AssetsPageobj.ddlActivityType()).sendKeys(Keys.TAB);
			
				
	
				Thread.sleep(3000);
				System.out.println("Clicking on the Open link of the activity.......");
				
			  
				//clicking on link status
				CommonUtility.highlightElement(driver, AssetsPageobj.lnkStatus()).click();
				Thread.sleep(6000);
				
				if(ActivityType.contains("Credit") ||  ActivityType.contains("Exchange"))//Raj has changed this line of code
				{
					driver.findElement(AssetsPageobj.ddlActivitySubType()).sendKeys(ActivitySubType);
				}
				
							
				wait.until(ExpectedConditions.visibilityOfElementLocated(AssetsPageobj.txtAgentDescription()));
				wait.until(ExpectedConditions.elementToBeClickable(AssetsPageobj.txtAgentDescription()));
				
				System.out.println("Entering Agent Description");
				driver.findElement(AssetsPageobj.txtAgentDescription()).sendKeys("Test Data");
				driver.findElement(AssetsPageobj.txtAgentDescription()).sendKeys(Keys.TAB);
				System.out.println("Entering Symptoms");
				driver.findElement(AssetsPageobj.txtSymptoms()).sendKeys("Test Data");
				driver.findElement(AssetsPageobj.txtSymptoms()).sendKeys(Keys.TAB);	
				System.out.println("Entering Troubleshooting");
				driver.findElement(AssetsPageobj.txtTroubleshooting()).sendKeys("Test Data");
				driver.findElement(AssetsPageobj.txtTroubleshooting()).sendKeys(Keys.TAB);
				System.out.println("Entering Consulsion");
				driver.findElement(AssetsPageobj.txtConculsion()).sendKeys("Test Data");
				driver.findElement(AssetsPageobj.txtConculsion()).sendKeys(Keys.TAB);
				System.out.println("Entering Environment");
				driver.findElement(AssetsPageobj.ddlEnvironment()).clear();//Raj added this line
				driver.findElement(AssetsPageobj.ddlEnvironment()).sendKeys("Chrome OS");
				driver.findElement(AssetsPageobj.ddlEnvironment()).sendKeys(Keys.TAB);
				System.out.println("Entering Sub-Environment");
				driver.findElement(AssetsPageobj.ddlSubEnvironement()).clear();//Raj added this line
				driver.findElement(AssetsPageobj.ddlSubEnvironement()).sendKeys("None");
				driver.findElement(AssetsPageobj.ddlSubEnvironement()).sendKeys(Keys.TAB);
				//Thread.sleep(1000);//2000
				
				
				
				
				wait.until(ExpectedConditions.presenceOfElementLocated(obj_ReviewAddressAndDetailsPage.getCreateDispatchButton()));
				
				wait.until(ExpectedConditions.elementToBeClickable(obj_ReviewAddressAndDetailsPage.getCreateDispatchButton()));
				WebElement btncreateDispatch=driver.findElement(obj_ReviewAddressAndDetailsPage.getCreateDispatchButton());
				
				
				
				    int attempts = 0;
			        while(attempts < 4) {
			            try {
			            	btncreateDispatch.isDisplayed();
			               // result = true;
			                break;
			            } catch(Exception e) {
			            	Thread.sleep(2000);
			            }
			            
			            attempts++;
			        }
				
				if(btncreateDispatch.isDisplayed())
				{
					System.out.println("Activity Created Successfully...........");
				}
				else
				{
					System.err.println("Activity Creation FAILED..........");
					Assert.fail("Activity Creation failed ");
	
				}
				
				System.out.println("Activity creation completed successfully........................");
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Assert.fail("Activity Creation failed: "+e);
	
			}
			
		}
	
}

*/