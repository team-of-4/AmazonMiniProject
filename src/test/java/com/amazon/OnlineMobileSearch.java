package com.amazon;
 
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class OnlineMobileSearch {
	static WebDriver driver;
	static List<WebElement> phoneNames;
	static List<WebElement> phonePrices;
	static WebElement sortby;
	static List<WebElement> options;
	static Select select;
	static String selectedOption;
	static String sucessMsg;
	static String input;
	public void setupDriver() {
		driver = DriverSetup.getWebDriver();
		driver.get("https://www.amazon.in");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}
	public void searchMobile() {
		input = "";
		try {
			 input = ExcelUtils.input();
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//input[contains(@class,'nav-input')]")).sendKeys(input);
		TakeScreenShot.takeScreenShot(driver,"homepage.png");
		driver.findElement(By.id("nav-search-submit-button")).click();
		TakeScreenShot.takeScreenShot(driver,"search.png");
	}
	public void validateOutput() {

		String msg = driver.findElement(By.xpath("//*[@id='search']/span[2]/div/h1/div/div[1]/div/div")).getText();
		System.out.println(msg);
		if(msg.contains(input)) {
			System.out.println("Search string validated : Correct Output");
		}
		else {
			System.out.println("Search string not validated : Wrong Output");
			driver.quit();
		}
 
		String[] msg_arr = msg.split(" ");
		String noOfPages = msg_arr[0];
		if(noOfPages.matches("^[1][-][0-9]+$")) 
			System.out.println(noOfPages+":Number of pages validated");
		else
			System.out.println(noOfPages+":Number of pages not validated");
		String noOfItems = msg_arr[3].replace(",", "");
		boolean iAmInt = false;
		try {
			Integer.parseInt(noOfItems);
			iAmInt = true;
		}
		catch(NumberFormatException e) {
			//not a integer
		}
		if(iAmInt==true) 
			System.out.println(noOfItems+":Number of items validated");
		else
			System.out.println(noOfItems+":Number of items not validated");

	}
	public void validateSortbyOptions() {
		 sortby = driver.findElement(By.id("s-result-sort-select"));
		 select = new Select(sortby);
		options = select.getOptions();
			int optionCount = 5; //
			if(optionCount == options.size()) 
				System.out.println("Number of sort by options should be 5 : validated");
			else
				System.out.println("Number of sort by options should be 5 : not validated");
 
	}
	public void validateNewArrival() {
		//selecting newest arrivals
		select.selectByVisibleText("Newest Arrivals");
	//	TakeScreenShot.takeScreenShot(driver,"3rd.png");
		driver.navigate().refresh();
		TakeScreenShot.takeScreenShot(driver,"new arrival.png");
		//validating newest arrivals
        selectedOption = driver.findElement(By.xpath("//span[@class='a-dropdown-prompt']")).getText();
		if(selectedOption.equalsIgnoreCase("Newest Arrivals"))
			System.out.println("Latest arrival has been selected");
		else
			System.out.println("Latest arrival has not selected");
 
	}

	public void fetchingPhoneNameAndPrice() {
		phoneNames = new ArrayList<WebElement>(15);
		phoneNames = driver.findElements(By.xpath("//span[contains(@class,'a-size-medium a-color-base a-text-normal')]"));
		phonePrices = new ArrayList<WebElement>(15);
		phonePrices = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
 
	}

	public void saveData()  {
		sucessMsg = ExcelUtils.saveDataToExcel(phoneNames, phonePrices);
		System.out.println(sucessMsg);
	}

	public static void main(String[] args)  {
		OnlineMobileSearch oms = new OnlineMobileSearch();
		oms.setupDriver();
		oms.searchMobile();
		oms.validateOutput();
		oms.validateSortbyOptions();
		oms.validateNewArrival();
		oms.fetchingPhoneNameAndPrice();
		oms.saveData();


	
	} 
}