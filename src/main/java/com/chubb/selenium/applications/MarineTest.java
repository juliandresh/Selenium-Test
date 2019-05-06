package com.chubb.selenium.applications;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.chubb.json.config.ConfigurationFile;
import com.chubb.screenshot.ScreenShot;


public class MarineTest {
	
	List<String> listado = new ArrayList<String>();		
	ConfigurationFile configFile;
		
	public List<String> getListado() {
		return listado;
	}
	
	public void setListado(List<String> listado) {
		this.listado = listado;
	}
	
	public ConfigurationFile getConfigFile() {
		return configFile;
	}

	public void setConfigFile(ConfigurationFile configFile) {
		this.configFile = configFile;
	}

	public void startTest()
	{
		
		String webDriver = configFile.getWebdriver();
		String driverPath = configFile.getDriverPath();
		String url = configFile.getWeburl();
		String user = configFile.getUser();
		String pass = configFile.getPassword();
		
		// Optional, if not specified, WebDriver will search your path for chromedriver.
		System.setProperty(webDriver, driverPath);

		WebDriver driver = new ChromeDriver();
		ScreenShot screenShot = new ScreenShot();
		  
	  try 
	  {
		  driver.get(url);
		  driver.manage().window().maximize();
		  Thread.sleep(2000);		  		  			  
		  
		  driver.findElement(By.linkText("Colombia")).click();
		  takeScreenShot(driver, screenShot);
		  Thread.sleep(3000);		  		  
		  
		  WebElement usuario = driver.findElement(By.name("Usuario"));
		  usuario.sendKeys(user);
		  WebElement password = driver.findElement(By.name("Pass"));
		  password.sendKeys(pass);
		  takeScreenShot(driver, screenShot);
		  password.submit();
		  Thread.sleep(3000);
		  
		  takeScreenShot(driver, screenShot);
		  Thread.sleep(3000);
		  /*Thread.sleep(3000);  // Let the user actually see something!
		  
		  WebElement rootMenu = driver.findElement(By.xpath(" //div[@id='el3']"));
		  Actions action = new Actions(driver);

		  action.moveToElement(rootMenu).perform();
		  Thread.sleep(1000);
		  
		  //inside sub-menu click on 'Consultar Pólizas'
		  action.moveToElement(driver.findElement(By.linkText("Consultar Pólizas"))).click().perform();			
		  Thread.sleep(3000);
		  screenShot.ScreenCapture(driver, "test" + (cont++) +".png");
		  Select dropdown = new Select(driver.findElement(By.name("M")));
		  dropdown.selectByValue("1");
		  			  
		  Thread.sleep(1000);
		  String polizaTest= "0506512";
		  WebElement polN = driver.findElement(By.name("PolN"));
		  polN.sendKeys(polizaTest);
		  screenShot.ScreenCapture(driver, "test" + (cont++) +".png");
		  polN.submit();
		  Thread.sleep(3000);
		  
		  driver.findElement(By.linkText(polizaTest)).click();			  
		  String var = String.valueOf(driver.findElement(By.xpath("//td[contains(text(),'"+ polizaTest +"')]")).getText());
		  
		  var = var.substring(8, 15);
		  if (polizaTest.equals(var))
		  {				  
			  screenShot.ScreenCapture(driver, "test" + (cont++) +".png");
			  System.out.println("DONE");
		  }*/
		  //Thread.sleep(8000);
	      driver.quit();
		  } 
		  catch (Exception e) 
		  {			
			e.printStackTrace();
		  }
		   listado = screenShot.getScreenShotList();
	}

	//Método que solicita generación de un ScreenShot en un directorio entregado
	private void takeScreenShot(WebDriver driver, ScreenShot screenShot) 
	{		
		String imageLocation = configFile.getImageLocation();
		String applicationName = configFile.getApplication();
		String extImage = configFile.getImageExtension();
		try 
		{
			screenShot.screenCapture(driver, imageLocation, applicationName, extImage);			
		} 
		catch (Exception e) 
		{			
			e.printStackTrace();
		}
	}	
}
