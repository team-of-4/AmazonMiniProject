package com.amazon;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSetup 
{
	
	public static WebDriver driver;
	
	public static WebDriver getWebDriver() {
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please select the browser: 1.Chrome 2.Edge");
		
		String input = sc.nextLine();
		
		if(input.equalsIgnoreCase("Chrome")|| input.equals("1")) {
			driver = new ChromeDriver();
		}
		else if (input.equalsIgnoreCase("Edge")|| input.equals("2")) {
			driver = new EdgeDriver();
		}
		else {
			System.out.println("Please select the approriate option");
		}
		
		
		return driver;
	}
	
}
