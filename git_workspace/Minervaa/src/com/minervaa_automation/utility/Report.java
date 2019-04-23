package com.minervaa_automation.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Report 
{
	public static StringBuilder builder = new StringBuilder();
	public static StringBuilder builder1 = new StringBuilder();
	static String appender;
	static int bodyCount;
	static String summary;
//	double subNum = .1;
	public static  void  header()
	{
		builder1.append("<html><header style=max-width:1170px;margin-top:0;margin-bottom:0;margin-left:auto;margin-right:auto;font-family:helvetica;>"
				+ "<div align=center style=background-color:dodgerblue;color:white;padding:10px;font-family:helvetica;font-weight:bold;>UAT AUTOMATION EXECUTION SUMMARY REPORT</div><br>"
				+"<style type=text/css>table{border-collapse:collapse;width:100%;}table,th{border:1px solid black;margin:10px;text-align: center;}th{width:0%;}td{border:1px solid black;margin:10px;text-align:left;}</style>"
				+ "<div style=font-family:helvetica;width:100%;display:table;font-size:14px;>"
				+ "<div class=title align=left style=padding:10px;border-radius:4px;box-sizing:border-box;border-width:2px;background-color:#efefef;border-color:#333;border-style:solid;color:#000;height:120px;width:30%;float:left;>"
				+ "<p style=margin:2px;font-weight:700;>Execution Platform Details</p><p style=margin:2px;>OS      : "+System.getProperty("os.name")+"</p><p style=margin:2px;>Java      : 1.8</p><p style=margin:2px>Selenium     : 3.0.0</p><p style=margin:2px;>Browser     :Internet Explorer 11</p></div>"
				+"<div class=title align=left style=padding:10px;border-radius:4px;box-sizing:border-box;border-width:2px;background-color:#ffcccc;border-color:#f00;border-style:solid;color:#000;height:120px;width:30%;float:right;>"
				+ "<p style=margin:2px;font-weight:700;>Summary</p><p style=margin:2px;margin-top:7px;>Status      :"+ListenerClass.resultVariable+"</p><p style=margin:2px;margin-top:7px;>Execution Date     :"+new SimpleDateFormat("yyyy-MMM-dd(hh:mm:ss)").format(Calendar.getInstance().getTime())+"</p><p style=margin:2px;margin-top:7px;>System User      :"+System.getProperty("user.name")+"</p></div></div>");
		//			+ "<div class=title align=left style=padding:10px;border-radius:4px;box-sizing:border-box;border-width:2px;border-color:#f00;background-color:#ffcccc;border-style:solid;color:#000;height:120px;width:30%;float:left;margin-left:5%;margin-right:5%;>"
//				+ "<p style=margin:2px;font-weight:700;>Summary</p><p style=margin:2px;>TestCaseName     :"+TestNGCreator.stringClass+"</p><p style=margin:2px;>Status      :"+ListenerClass.resultVariable+"</p><p style=margin:2px;>ExecutionDate     :"+new SimpleDateFormat("yyyy-MMM-dd(HH-mm-ss)").format(Calendar.getInstance().getTime())+"</p></div></div>");
				/*+"<div class=title align=left style=padding:10px;border-radius:4px;box-sizing:border-box;border-width:2px;border-color:#0085c3;background-color:#b7e8ff;border-style:solid;color:#000;height:120px;width:30%;float:left;>"
				+ "<p style=margin:2px;font-weight:700;>Author Info</p><p style=margin:2px;>Author Name    : Automation DellTeam </p><p style=margin:2px;>Version    : 1.0</p><p style=margin:2px;>System User    : "+System.getProperty("user.name")+"</p></div></div>")*/;
//		builder.append("<table><tr><th align=left>Exection Platform Details</th><th align=center>Execution Platform Details</th></tr></table></header>");
	//	builder.append("<html><div style="+"background-color:black;color:white;padding:20px;"+"align="+"left"+">"+"<h2>Execution Platform Details</h2><br>");
				builder1.append("<body><table style=font-size:13px;margin-top:15px;margin-left:0;margin-right:0;><tr style=background-color:dodgerblue;color:white;><th>TC Name</th><th>"+TestNGCreator.stringClass+"</th></tr></table>");
				builder1.append("<table style=font-size:13px;margin-top:-10px;margin-left:0;margin-right:0;><tr style=background-color:dodgerblue;color:white;><th>Sl.No</th><th >Test Steps</th><th>Expected Value</th><th>Actual Value</th><th>Status</th><th>Screen Shot</th></tr>");
		
	}
	
	public  static void put(String logs, String expected, String actual, String status)
	{		
		//String imge="C:\\Users\\Narasimhamurthy_SJ\\Desktop\\Temp\\GSD_Automation\\Object_Data_Repository\\1342105_AMER_01_ScreenShots\\screenShot.jpg";
		Path path = Paths.get(TestNGCreator.finalResultPath+"/"+TestNGCreator.tcID+"_"+"_01_ScreenShots");
		bodyCount++;
		if(status.equalsIgnoreCase("pass"))
		{
//		appender = "<tr><td>" + bodyCount + "</td><td>" + logs + "</td><td>" + expected+ "</td><td>" + actual+ "</td><td><img src=C:\\Users\\SeleniumWorkSpace\\Gsd_Automation\\correct.PNG style=height:20px;width:20px;></td></tr>";
			appender = "<tr><td>" + bodyCount + "</td><td>" + logs + "</td><td>" + expected+ "</td><td>" + actual+ "</td><td style=color:#8BC34A;font-weight:bold;>PASS</td><td></td></tr>";
		}
		else if(status.equalsIgnoreCase("fail"))
		{
//			appender = "<tr><td>" + bodyCount + "</td><td>" + logs + "</td><td>" + expected+ "</td><td>" + actual+ "</td><td><img src=C:\\Users\SeleniumWorkSpace\\Gsd_Automation\\incorrect.PNG style=height:20px;width:20px;></td></tr>";
			appender = "<tr><td>" + bodyCount + "</td><td>" + logs + "</td><td>" + expected+ "</td><td>" + actual+ "</td><td style=color:crimson;font-weight:bold;>FAIL</td><td><a><img src=C:\\Users\\Narasimhamurthy_SJ\\Desktop\\Temp\\GSD_Automation\\Object_Data_Repository\\1342105_AMER_01_ScreenShots\\screenShot.jpg style=height:45px;width:90px;></img></a></td></tr>";
		}
		builder.append(appender);
	}
	
//	public void loopBodyReport(String logs, String expected, String actual, String status)
//	{
//		
//		appender = "<td></td><td>" + subNum+"."+logs + "</td><td>" + expected+ "</td><td>" + actual+ "</td><td>" + status+ "</td>";
//		builder.append(appender);
//
//	}
	public static void closure()
	{
		
		/*summary = "<div style=font-family:helvetica;width:200%;display:table;font-size:14px;>"+ "<div class=title align=left style=padding:10px;border-radius:4px;box-sizing:border-box;border-width:2px;background-color:#ffcccc;border-color:#f00;border-style:solid;color:#000;height:120px;width:30%;float:left;>"
				+ "<p style=margin:2px;font-weight:700;>Summary</p><p style=margin:2px;>TestCaseName     :"+TestNGCreator.stringClass+"</p><p style=margin:2px;>Status      :"+ListenerClass.resultVariable+"</p><p style=margin:2px;>ExecutionDate     :"+new SimpleDateFormat("yyyy-MMM-dd(hh:mm:ss)").format(Calendar.getInstance().getTime())+"</p></div></div>";*/
		builder.append("</table>");
		//builder.append(summary);
		builder.append("</body></html><br>");
		try
        {
			//FileWriter write = new FileWriter(new File("C:\\Users\\amrutha_varshini\\SeleniumWorkSpace\\Gsd_Automation\\src"));
			//write.write(builder);
			//File file = new File("C:\\Users\\amrutha_varshini\\SeleniumWorkSpace\\Gsd_Automation\\final.jsp");
            /*Path path = Paths.get(TestNGCreator.finalResultPath+"\\"+TestNGCreator.tcID+"_"+TestNGCreator.region+"_01_Reports");
            Files.createDirectories(path);
            File file = new File(path+"\\AutomationReport.jsp");//\\"+TestNGCreator.tcID+TestNGCreator.region+".jsp");
        	FileWriter fwriter = new FileWriter((file));
    		BufferedWriter bwriter = new BufferedWriter(fwriter);
    		bwriter.write(builder1.toString());
    		bwriter.write(builder.toString());
    		bwriter.close();*/
			
			Path path = Paths.get(TestNGCreator.finalResultPath+"/"+TestNGCreator.tcID+"_"+"_01_Reports");
			Files.createDirectories(path);
			File file = new File(path+"/"+TestNGCreator.tcID+"_"+"_Report.jsp");//\\"+TestNGCreator.tcID+TestNGCreator.region+".jsp");
			FileWriter fwriter = new FileWriter((file));
			BufferedWriter bwriter = new BufferedWriter(fwriter);
			bwriter.write(builder1.toString());
			bwriter.write(builder.toString());
			bwriter.close();			
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	}
}