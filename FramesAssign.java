package Week4day1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import week3day2.list;

public class FramesAssign {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver Chrome = new ChromeDriver();
		Chrome.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		Chrome.manage().window().maximize();
				
		//WebElement myFrame = Chrome.findElement(By.xpath("//iframe[@data-ezsrc=\"frames1.html\"]"));
		//Chrome.switchTo().frame(myFrame);     //Switch using src
		
		//Chrome.switchTo().frame(0); //Switch using frame index
		
		//Switch using Frame id
		Chrome.switchTo().frame("frame1");
		Chrome.findElement(By.xpath("//b[@id='topic']/following::input")).sendKeys("Selenium");
		
		Chrome.switchTo().frame("frame3");
		Chrome.findElement(By.xpath("//input[@id='a']")).click();
		
		//Switch using ParentFrame to append
		Chrome.switchTo().parentFrame();
		Chrome.findElement(By.xpath("//b[@id='topic']/following::input")).sendKeys("Append");
		
		//Moving to MainBody
		Chrome.switchTo().defaultContent();
		
		
		Chrome.switchTo().frame("frame2");
		Chrome.findElement(By.xpath("//select[@id='animals']")).click();
		
		WebElement AnimalEleDrop = Chrome.findElement(By.id("animals"));
		Select selAnimal = new Select(AnimalEleDrop);
		selAnimal.selectByVisibleText("Avatar");
		
		//Get and print the selected dropdown value
		WebElement Dropvalue = selAnimal.getFirstSelectedOption();
		String Selectedvalue = Dropvalue.getText();
		System.out.println(Selectedvalue);

		Chrome.close();
	}

}
