package com.amazon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

public class ExcelUtils {
	
	public static String saveDataToExcel(List<WebElement> name  ,List<WebElement> price)   {
		
		FileOutputStream file = null;
		try {
			file = new FileOutputStream(System.getProperty("user.dir")+"/ouput_excel.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet s = wb.createSheet("output");
		XSSFRow r0 = s.createRow(0);
		XSSFCell c0 = r0.createCell(0);
		c0.setCellValue("Latest Arrival Mobile Name");
		r0.createCell(1).setCellValue("Price");
		
		for(int i=1;i<price.size();i++) {
					
				XSSFRow r = s.createRow(i);
				r.createCell(0).setCellValue(name.get(i-1).getText());
				r.createCell(1).setCellValue(price.get(i-1).getText());
				
		}
		
		try {
			wb.write(file);
			wb.close();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Write Sucessfully";
		
	}
	
	public static String input() throws IOException {
			
			FileInputStream file = null;
			
			try {
				file = new FileInputStream("C:\\Users\\2303652\\eclipse-workspace\\AmazonMiniProject\\src\\test\\resources\\input_amazon.xlsx");
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			
			XSSFWorkbook w  = new XSSFWorkbook(file);
			XSSFSheet s = w.getSheetAt(0);
			XSSFRow r = s.getRow(1);
			
			String input = r.getCell(0).getStringCellValue();
			
			w.close();
			file.close();
			
			return input;
		}
	

}
