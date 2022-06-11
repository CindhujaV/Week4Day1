package Week4day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Windows {

	public static void main(String[] args) throws InterruptedException {

	//Chrome Setup and disable notifications
	WebDriverManager.chromedriver().setup();
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-notifications");
	ChromeDriver Chrome = new ChromeDriver(options);
	Chrome.get("http://www.irctc.co.in/nget/train-search");
	Chrome.manage().window().maximize();
	
	Thread.sleep(3000);
 
	Chrome.findElement(By.xpath("//button[text()='OK']")).click();
	Chrome.findElement(By.linkText("FLIGHTS")).click();
	
	//set and list 
	Set<String> windowHandles = Chrome.getWindowHandles();
	List<String> windows = new ArrayList<String>(windowHandles);
	
	//Switch Window
	Chrome.switchTo().window(windows.get(1));
	System.out.println(Chrome.getTitle());
	
	//Close Window
	Chrome.close();
	
	
	
	
	}

}
