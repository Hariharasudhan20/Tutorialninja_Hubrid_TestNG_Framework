package com.tutorialninja.qa.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.apache.poi.ss.usermodel.CellType;

public class Utilities {
	
	public static final int Implicitly_wait=10;
	public static final int Page_load_time=5;

	public static String Timestamp() {
		
		 Date date=new Date();
		 
		 String timestamp= date.toString().replace(" ", "_").replace(":", "_");
		 return "amotooricap9"+timestamp+"@gmail.com";
	 }
	
	
	public static Object[][] getTestdatafromExcel(String sheetName) {
		
		XSSFWorkbook book=null;
		File excelfile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\testdata\\TutorialsninjaTestdata.xlsx");
		try {
		FileInputStream fisexcel=new FileInputStream(excelfile);
		book= new XSSFWorkbook(fisexcel);
		}catch(Exception e) {
			e.printStackTrace();
		}
		XSSFSheet sheet = book.getSheet(sheetName);
		
		int row=sheet.getLastRowNum();
		int col=sheet.getRow(0).getLastCellNum();
		
		Object[][] data=new Object[row][col];
		
		for(int i=0;i<row;i++) {
			XSSFRow rows=sheet.getRow(i+1);
			
			for(int j=0;j<col;j++) {
				XSSFCell cell=rows.getCell(j);
			CellType celltype = cell.getCellType();
				
		
				switch (celltype) {
					
				case STRING:
					data[i][j]=cell.getStringCellValue();
					break;
					
				case NUMERIC:
					data[i][j]=Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j]=cell.getBooleanCellValue();
					break;
					
				}
				
				
				
			}
			
			
		}
		
		return data;
	}
	
	public static String capturescreenshot(WebDriver driver, String testname) {
		
		File srcScreeshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationscreenshotpath= System.getProperty("user.dir")+"\\Screenshots\\"+testname+".png";
		try {
		FileHandler.copy(srcScreeshot, new File(destinationscreenshotpath));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return destinationscreenshotpath;
		
	}
	
	
	
}
