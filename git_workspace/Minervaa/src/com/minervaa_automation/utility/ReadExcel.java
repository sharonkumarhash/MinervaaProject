package com.minervaa_automation.utility;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;

public class ReadExcel {
	
	static FileInputStream fistream=null;
	static Workbook wb=null;
	static Sheet sheet_Name=null;
	static int totalRows=0;
	public  static Hashtable<String,String> hTable=null;
	static String objectName=null;
	static String ObjectProperty=null;
	
	public static  ArrayList<String> tc_Data=null;

	static String  tcIDFromExcel=null;
	
	public static ArrayList<String>  getTestData(String path, String columnNameOfTCID, String tcID){
		tc_Data=new ArrayList<String>();
			
		try{
			
			fistream=new FileInputStream(path);
		    wb=WorkbookFactory.create(fistream);
		    
            outerloop:
            for (int sheetNoIndex = 0; sheetNoIndex < wb.getNumberOfSheets(); sheetNoIndex++) {
                    
                	sheet_Name = wb.getSheet(wb.getSheetName(sheetNoIndex));
                    
                    String sheetName=wb.getSheetName(sheetNoIndex);
                      
                    int indexOfTCID=ReadExcel.getColumnIndex(path, sheetName, columnNameOfTCID);

          			totalRows= sheet_Name.getLastRowNum();
			
          			for(int i=1;i<=totalRows;i++){

          				Cell tcIdCell=sheet_Name.getRow(i).getCell(indexOfTCID);
				   				   
          				String tcNameFromExcel=checkCellType( sheet_Name, tcIdCell, i,indexOfTCID);
				   
				    	if(tcID.equalsIgnoreCase(tcNameFromExcel)){
				    		int j;
				    		int cnt=sheet_Name.getRow(i).getLastCellNum();
						
				    		Cell eachCellDetails=null;	 
				    			for(j=0;j<cnt;j++){
				    				eachCellDetails=sheet_Name.getRow(i).getCell(j);
							   
				    				String individualCelldata=checkCellType( sheet_Name, eachCellDetails, i,j);
							   
							  	    tc_Data.add(individualCelldata);
								}
				    			if(j==cnt){
				    				break outerloop;
				    			}
				    		}
				    	}
          		}
			if(tc_Data.isEmpty()){
				System.out.println("entered TCID: "+tcID+" Not found");
				Assert.fail("entered TCID: "+tcID+" Not found");
			}
			return tc_Data;
		}
		catch(Exception e){
			System.out.println("Caught exception while reading entire data from excel sheet: " +e); 
			e.printStackTrace();
				
			return null;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static String checkCellType(Sheet sheetname,Cell c, int rowIndex,int columnIndex){		
		String celldata=null;
	
		try{
			switch(sheet_Name.getRow(rowIndex).getCell(columnIndex).getCellType())
			{
				case Cell.CELL_TYPE_NUMERIC:celldata= Integer.toString((int) sheet_Name.getRow(rowIndex).getCell(columnIndex).getNumericCellValue());
											break;
		 
		 		case Cell.CELL_TYPE_STRING:celldata= sheet_Name.getRow(rowIndex).getCell(columnIndex).getStringCellValue().trim();
		 								   break;
												  
		 		default:System.err.println("Cell type not defined. Update the code");
			}
			return celldata;
		}
		catch(Exception e){
			e.printStackTrace();
			System.err.println("failed while checking cell type of Excel "+e);
			System.exit(0);
			//	Assert.fail();
			return null;
		}
	}
	
	//this method will read entire excel data and stores it in single hashtable object
	public static Hashtable<String,String>  getEntireExcelSheetData(String path,String sheetName,String columnNameOfObject,String columnNameOfObjectProperty){
		
		int columnNumberOfObjectName=ReadExcel.getColumnIndex(path, sheetName, columnNameOfObject);
		int columnNumberOfobjctProperty=ReadExcel.getColumnIndex(path, sheetName, columnNameOfObjectProperty);
		
		try{
			fistream=new FileInputStream(path);
			wb=WorkbookFactory.create(fistream);
			sheet_Name=wb.getSheet(sheetName);
			totalRows= sheet_Name.getLastRowNum();
			  
			hTable=new Hashtable<String,String> ();
		
		    for(int i=1;i<=totalRows;i++){
		    	// System.out.println("rows cnt: "+totalRows);
			   
		    	//Retrieve object name
		    	objectName=sheet_Name.getRow(i).getCell(columnNumberOfObjectName).getStringCellValue().trim();
		    	//System.out.println("objn:"+objectName);
			 
		    	//Retrieve object property value
		    	ObjectProperty=sheet_Name.getRow(i).getCell(columnNumberOfobjctProperty).getStringCellValue().trim();
		    	//System.out.println("objval:"+ObjectProperty);
			
		    	//insert object name and property into hashtable
		    	hTable.put(objectName,ObjectProperty);	
			}
		    return hTable;
		}
		catch(Exception e){
			System.out.println("Caught exception while reading entire data from excel sheet: " +e);
			return null;			
		}
	}
	
	//This method will return single cell data from excel
	public static String retrieveDataFromExcel(String path,String sheetName,int rowNum,int cellNum){
		try{
			fistream=new FileInputStream(path);
			wb=WorkbookFactory.create(fistream);
			return (wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue());
		}
		catch(Exception e){
			System.out.println("Caught exception while reading the data from excel sheet:"+e);
			return "";			
		}
	}
		
	//this method will provide column number based on the column name
	public static int getColumnIndex(String excelPath,String sheetName,String columnName){
		int columnIndex=0;
		boolean status=false;
		try{
			fistream=new FileInputStream(excelPath);
			wb=WorkbookFactory.create(fistream);
			int colomnCount=wb.getSheet(sheetName).getRow(0).getLastCellNum();
			 	 
			for(int i=0;i<colomnCount;i++){
				if(columnName.equals(wb.getSheet(sheetName).getRow(0).getCell(i).getStringCellValue().trim())){
					status=true;
					columnIndex=i; 
					break;
				}
			}
			if(status){
				// System.out.println("column found: "+columnName);
			}
			else{
				System.out.println("column name: "+columnName+" not found");
			}
			return columnIndex;
		}
		catch(Exception e){
			System.out.println("Caught exception while retrieving column index: " +e);
			return 0;
		}
	}
	
	/*public static int getColumnIndex(String excelPath,String sheetName,String columnName)
	{
		
	}*/
}
