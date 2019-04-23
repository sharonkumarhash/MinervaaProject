package com.minervaa_automation.utility;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.internal.ClassHelper;
import org.testng.internal.PackageUtils;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

@Listeners(com.minervaa_automation.utility.ListenerClass.class)
public class TestNGCreator extends ListenerClass{

	public static Class className = null;
	public static String stringClass = null;
	public static String excelPath = null;
	public static String sheetName = null;
	public static String finalResultPath = null;
	public static ArrayList<String> TestData = null;
	public static String folderPath = null;
	public static String configfolder = null;
	public static String tcID = null;

	public static void main(String[] args) {
		
    	tcID = args[0];
   		excelPath = args[1];
   		finalResultPath = args[2];

   		TestNGCreator test = new TestNGCreator();
   		Path p = Paths.get(excelPath);
   		Path orfolder = p.getParent();

   		folderPath = orfolder.toString() + "/ObjectRepository.xlsx";

   		configfolder = orfolder.toString() + "/Config.properties";

   		TestData = ReadExcel.getTestData(excelPath, "TC_ID", tcID);
   		test.createXml(tcID);
   		System.out.println("class name :" + stringClass); 
       }
               
               public void createXml(String testCaseId)  
               {
            	   boolean flag = false; 
            	   String includeName = null;
                              try
                              {
                                             
                              String testName = testCaseId;
                              List<String> testNames = new ArrayList<String>();
//                           com.dell.delta_uat_automation.testcases
                              String[] testClasses = PackageUtils.findClassesInPackage("com.minervaa_automation.testcases", new ArrayList<String>(),
                                                            new ArrayList<String>());
                              List<XmlInclude> includes = new ArrayList<XmlInclude>();

//                           Class className = null;
                              ClassLoader cl = Thread.currentThread().getContextClassLoader();
                              for (String eachClass : testClasses) 
                              {
                                             
//                                          System.out.println("eachClass   :"+eachClass);
                                             Class currentClass = cl.loadClass(eachClass);

                                             if (eachClass.contains(testCaseId)) 
                                             {
                                                            className = currentClass;
                                                            stringClass = currentClass.getSimpleName();
                                                            Set<Method> allMethods = ClassHelper.getAvailableMethods(currentClass);
                                                            Iterator<Method> iMethods = allMethods.iterator();
                                                            while (iMethods.hasNext()) 
                                                            {
                                                                           Method eachMethod = iMethods.next();
                                                                           Test test = eachMethod.getAnnotation(Test.class);
                                                                           if (test != null) {
//                                                                                       System.out.println("Method Name = " + eachMethod.getName());
                                                                                          String method = eachMethod.getName();
                                                                                        	 includeName=method; 
                                                                                                         includes.add(new XmlInclude(method));
//                                                                                                         System.out.println("mtd name"+method);
                                                                                                         flag = true; 

                                                                                          // System.out.println("Its class = " +
                                                                                          // eachMethod.getDeclaringClass().getName());
                                                                           }
                                                            }

                                                            if(flag)
                                                            {
                                                            	
                                                            	break;
                                                            }
                                             }

                              }
                              if(!includes.isEmpty())
                              {
                                             List<Class<? extends ITestNGListener>> listnerClasses = new ArrayList<Class<? extends ITestNGListener>>();
                                             listnerClasses.add(com.minervaa_automation.utility.ListenerClass.class);
                                             TestNG testNG = new TestNG();
                                             
                                             XmlSuite suite = new XmlSuite();
                                             suite.setName("Suite");
                                             XmlTest xmlTest = new XmlTest(suite);
//                                             testNG.setUseDefaultListeners(true);
                                             testNG.setListenerClasses(listnerClasses); 
//                                             testNG.set
                                             xmlTest.setName("Test");
                                             xmlTest.setVerbose(0);
                                             XmlClass xmlClass = new XmlClass(className);
                                             xmlTest.getClasses().add(xmlClass);
                                             XmlInclude include = new XmlInclude(includeName);
                                             xmlClass.setIncludedMethods(includes);
                                             testNG.setXmlSuites(Arrays.asList(suite));
//                                             FileWriter writer = new FileWriter(new File("TestNG.xml"));
//                                             writer.write(suite.toXml());
//                                             writer.flush();
//                                             writer.close();
//                                             testNG.setUseDefaultListeners(true);
                                             testNG.run();
                              }
//                           else {
//                                          System.out.println("null");
//                                          
//                           }
                              }
                              catch(Exception e)
                              {
                                             e.printStackTrace();
                              }
               }
}
