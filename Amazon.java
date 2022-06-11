package Week4day1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void switchToNewWindow(ChromeDriver Chrome) {
		Set<String> windowHandles = Chrome.getWindowHandles();
		List<String> winList = new ArrayList<String>(windowHandles);
		Chrome.switchTo().window(winList.get(1));
	}

	public static void takeScreenShot(ChromeDriver chrome) throws IOException {
		File source = chrome.getScreenshotAs(OutputType.FILE);
		File desc = new File("./screenshots/screenshot.png");
		FileUtils.copyFile(source, desc);
	}

	public static void verifyCartTotal(String price, String cartTotal) {

		if (cartTotal.contains(price))
			System.out.println("Price and cart total are same");
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver Chrome = new ChromeDriver(options);
		Chrome.get("https://www.amazon.in/");
		Chrome.manage().window().maximize();

		// search oneplus 9 pro
		Chrome.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro" + "\n");

		// Get the price of the first product
		String firstPrice = Chrome.findElement(By.xpath("//span[@class='a-price-whole']")).getText();
		System.out.println("Price of first product : " + firstPrice);

		// Print the number of customer ratings for the first displayed product
		Thread.sleep(5000);
		WebElement eleRate = Chrome.findElement(By.xpath("(//a[@target='_blank']/span/following::span)[5]"));

		String numOfRatings = eleRate.getText();
		System.out.println("Number of Customer ratings for first product : " + numOfRatings);

		// Click the first text link of the first image
		Chrome.findElement(By.xpath("//a[@target='_blank']/span")).click();
		switchToNewWindow(Chrome);

		// Take a screen shot of the product displayed
		takeScreenShot(Chrome);				

		// Click 'Add to Cart' button
		Chrome.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();

		// Get the cart subtotal and verify if it is correct
		Thread.sleep(5000);
		String cartSubTotal = Chrome.findElement(By.xpath("(//div[@id='attach-added-to-cart-message']//span/span)[2]")).getText();
		verifyCartTotal(firstPrice, cartSubTotal);

		Chrome.quit();
	
	}
}

