package Week4day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SwitchWindows {
	
	
	public static void switchToNewWindow(ChromeDriver Chrome) {
		
		Set<String> windowHandles = Chrome.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowHandles);
		
	}
	public static void switchToParentWindow(ChromeDriver Chrome, String parentWindow) 
	{
		
		Chrome.switchTo().window(parentWindow);
	}
	
	public static void getPageTitle1(ChromeDriver Chrome) {
		
		System.out.println("Title of the page : " + Chrome.getTitle());
	}

	public static void getPageTitle(ChromeDriver chrome) {
		
		System.out.println("Title of the page : " + chrome.getTitle());
	}

	public static void closeAllExceptParent(ChromeDriver Chrome, String parentWinTitle, String parentWindow) 
	{
		Set<String> windowHandles = Chrome.getWindowHandles();
		List<String> winList = new ArrayList<String>(windowHandles);
		int openWinInd = 0;
		for (int i = 0; i < winList.size(); i++) {
			Chrome.switchTo().window(winList.get(i));
			if (Chrome.getTitle().equals(parentWinTitle))
				openWinInd = i;
			if (!(Chrome.getTitle().equals(parentWinTitle)))
				Chrome.close();
		}
		}
	public static void main(String[] args) throws InterruptedException 
	{
		
	//Chrome setup and disable notifications
	WebDriverManager.chromedriver().setup();
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-notifications");
	ChromeDriver Chrome = new ChromeDriver(options);
	Chrome.get("http://www.leafground.com/pages/Window.html");
	Chrome.manage().window().maximize();
	Chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	
	String parentWindow = Chrome.getWindowHandle();
	String parentWinTitle = Chrome.getTitle();
	
	
	Chrome.findElement(By.xpath("//button[@id='home']")).click();
	switchToNewWindow(Chrome);
	getPageTitle(Chrome);
	switchToParentWindow(Chrome, parentWindow);
	Chrome.findElement(By.xpath("//button[text() ='Open Multiple Windows']")).click();
	Chrome.findElement(By.xpath("//button[text()='Do not close me ']")).click();
	switchToNewWindow(Chrome);
	getPageTitle(Chrome);
	Chrome.findElement(By.xpath("//button[text()='Wait for 5 seconds']")).click();
	Thread.sleep(5000);
	switchToNewWindow(Chrome);
	closeAllExceptParent(Chrome, parentWinTitle, parentWindow); 
	
	}


	}


