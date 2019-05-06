package com.chubb.selenium.applications;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class PegaTest {

	
	public static void main(String [] args) 
	{
		  // Optional, if not specified, WebDriver will search your path for chromedriver.
		  System.setProperty("webdriver.chrome.driver", "C:/angular/chromedriver.exe");

		  WebDriver driver = new ChromeDriver();
		  driver.get("http://172.25.98.190");
		  driver.manage().window().maximize();
		  System.out.println(driver.getTitle());
		  try 
		  {
			  Thread.sleep(1000);
			  
			  WebElement usuario = driver.findElement(By.name("UserIdentifier"));
			  usuario.sendKeys("XXX");
			  
			  WebElement password = driver.findElement(By.name("Password"));
			  password.sendKeys("XXXX");
			  
			  WebElement bLogIn = driver.findElement(By.xpath("//button[@id='sub']"));
			  bLogIn.click();
			  Thread.sleep(3000); 
			  //WebElement records = driver.findElement(By.linkText("Records"));
			  driver.findElement(By.linkText("Records")).click();
			  Thread.sleep(5000); 
			  driver.findElement(By.xpath("//span[contains(text(),'SysAdmin')]")).click();
			  
			  Actions action = new Actions(driver);
			  WebElement element = driver.findElement(By.xpath("//span[contains(text(),'SysAdmin')]"));
			  action.contextClick(element).perform();
			  System.out.println("Pasó por el click derecho");
			  Thread.sleep(3000);
			  action.keyDown(Keys.ARROW_DOWN).click();
			  
			  Thread.sleep(3000);
			  
			  driver.quit();
			  
		  } 
		  catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		  }  // Let the user actually see something!			 		 
		   
	}
	
}
