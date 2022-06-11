package Week4day1;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Frames {

	public static void main(String[] args) {
		
	//Chrome Driver Setup
	WebDriverManager.chromedriver().setup();
	ChromeDriver Chrome = new ChromeDriver();
	Chrome.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_prompt");
	Chrome.manage().window().maximize();
	
	//Handling cookies
	Chrome.findElement(By.xpath("//div[@id='accept-choices']")).click();
	
	//Switching to Frame 
	Chrome.switchTo().frame(0);
	
	//Clicking on Tryit button
	Chrome.findElement(By.xpath("//button[text()='Try it']")).click();
	
	//Prompt Alert
	Alert alert = Chrome.switchTo().alert();
	alert.sendKeys("Iam entered in the alert textbox");
	alert.accept();
	
	//Getting the text entered in alert textbox
	String text = Chrome.findElement(By.id("demo")).getText();
	
	if(text.contains("Iam entered in the aler textbox"))
			{
		System.out.println("Well Done you did it!!");
			}
		else
		{
			System.out.println("Keep Trying!!");
			}
				
	

	}

}
