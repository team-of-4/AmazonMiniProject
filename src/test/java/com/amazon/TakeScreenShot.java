package com.amazon;


import java.io.File;
import java.time.LocalTime;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
 
public class TakeScreenShot {
	public static void takeScreenShot(WebDriver driver,String imgName) {
		try {
		TakesScreenshot ss = ((TakesScreenshot) driver);
		File srcFile = ss.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir") + "\\screenshots\\"  + imgName);
		FileUtils.copyFile(srcFile, destFile);
		} catch(Exception e) {}
	}
}