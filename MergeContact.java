package Week4day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void switchToNewWindow1(ChromeDriver Chrome) 
	{
		Set<String> windowHandles = Chrome.getWindowHandles();
		List<String> winList = new ArrayList<String>(windowHandles);
		Chrome.switchTo().window(winList.get(1));
	}

	public static void switchToParentWindow(ChromeDriver Chrome, String parentWindow) 
	{
		Chrome.switchTo().window(parentWindow);
	}

	public static void main(String[] args) throws InterruptedException
	{
		
	//1. Launch URL "http://leaftaps.com/opentaps/control/login"
	WebDriverManager.chromedriver().setup();
	ChromeDriver Chrome = new ChromeDriver();
	Chrome.get("http://leaftaps.com/opentaps/control/login");
	Chrome.manage().window().maximize();
	Chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	
	//2. Enter UserName and Password Using Id Locator
	Chrome.findElement(By.xpath("//input[@id='username']")).sendKeys("DemoSalesManager");
	Chrome.findElement(By.xpath("//input[@id='password']")).sendKeys("crmsfa");

	// 3. Click on Login Button using Class Locator
	Chrome.findElement(By.xpath("//input[@class='decorativeSubmit']")).click();
	
	// 4. Click on CRM/SFA Link
	Chrome.findElement(By.xpath("//a[contains(text(), 'CRM/SFA')]")).click();
	
	//5. Click on contacts Button
	Chrome.findElement(By.xpath("//a[contains(text(),'Contacts')]")).click();
	
	//6. Click on Merge Contacts using Xpath Locator
	Chrome.findElement(By.xpath("//a[contains(text(),'Merge Contacts')]")).click();
	String parentWindow = Chrome.getWindowHandle();
	
	//7. Click on Widget of From Contact
	Chrome.findElement(By.xpath("//input[@id='partyIdFrom']/following::img[@alt='Lookup']")).click();
	switchToNewWindow1(Chrome);
	
	//8. Click on First Resulting Contact
	Chrome.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
	switchToParentWindow(Chrome, parentWindow);
	
	// 9. Click on Widget of To Contact
	Chrome.findElement(By.xpath("(//input[@id='partyIdTo']/following::img[@alt='Lookup'])")).click();
	switchToNewWindow1(Chrome);
		
	// 10. Click on Second Resulting Contact
	Chrome.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[2]")).click();
	switchToParentWindow(Chrome, parentWindow);
	
	// 11. Click on Merge button using Xpath Locator
	Chrome.findElement(By.xpath("//a[text()='Merge']")).click();
	

	// 12. Accept the Alert
	Alert alert = Chrome.switchTo().alert();
	alert.accept();
	
	 //13. Verify the title of the page
	System.out.println(Chrome.getTitle());
	
	// Close the page
	Chrome.close();
	
	}

}
