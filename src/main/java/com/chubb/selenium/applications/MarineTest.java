package com.chubb.selenium.applications;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.chubb.dashboard.OperativeProcess;
import com.chubb.json.config.ConfigurationFile;
import com.chubb.screenshot.ScreenShot;


public class MarineTest {
	
	List<String> listado = new ArrayList<String>();		
	ConfigurationFile configFile;
	OperativeProcess process = new OperativeProcess();
		
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
		// Optional, if not specified, WebDriver will search your path for chromedriver.
		System.setProperty(configFile.getWebdriver(), configFile.getDriverPath());
		WebDriver driver = new ChromeDriver();
		ScreenShot screenShot = new ScreenShot();
		  
		try 
		{
		  driver.manage().window().maximize();
		  driver.get(configFile.getWeburl());
		  String span = driver.findElement(By.xpath("//span[@class='ace']")).getText();
		  System.out.println(span);
		  if(span.equals("Chubb Latinoamérica - Software de Marine")) {
			  System.out.println(span);
			  process.setAuthentication(true);
			  
		  }
		  else { 
			  process.setAuthentication(false);
			  //sendEmail();
			  //WriteLog();
			  System.err.println("Informar sobre validación");
			  System.exit(0);
		  }
		  
		  Thread.sleep(2000);		  		  			  
		  
		  /*driver.findElement(By.linkText("Colombia")).click();
		  takeScreenShot(driver, screenShot);
		  Thread.sleep(3000);		  		  
		  
		  WebElement usuario = driver.findElement(By.name("Usuario"));
		  usuario.sendKeys(configFile.getUser());
		  WebElement password = driver.findElement(By.name("Pass"));
		  password.sendKeys(configFile.getPassword());
		  takeScreenShot(driver, screenShot);
		  password.submit();
		  Thread.sleep(3000);
		  		  
		  WebElement rootMenu = driver.findElement(By.xpath(" //div[@id='el3']"));
		  
		  Actions action = new Actions(driver);
		  action.moveToElement(rootMenu).perform();
		  Thread.sleep(1000);
		  
		  //inside sub-menu click on 'Consultar Pólizas'
		  action.moveToElement(driver.findElement(By.linkText("Consultar Pólizas"))).click().perform();			
		  Thread.sleep(3000);
		  takeScreenShot(driver, screenShot);
		  
		  Select dropdown = new Select(driver.findElement(By.name("M")));
		  dropdown.selectByValue("1");
		  String polizaTest= "0506512";
		  WebElement polN = driver.findElement(By.name("PolN"));
		  polN.sendKeys(polizaTest);
		  takeScreenShot(driver, screenShot);
		  polN.submit();
		  
		  driver.findElement(By.linkText(polizaTest)).click();			  
		  String var = String.valueOf(driver.findElement(By.xpath("//td[contains(text(),'"+ polizaTest +"')]")).getText());
		  
		  var = var.substring(8, 15);
		  if (polizaTest.equals(var))
		  {				  
			  takeScreenShot(driver, screenShot);
			  System.out.println("DONE");
		  }
		  Thread.sleep(3000);
		  WebElement rootMenu1 = driver.findElement(By.xpath("//div[@id='el5']"));
		  rootMenu1.click();
		  
		  Thread.sleep(3000);*/
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
		try 
		{
			screenShot.screenCapture(driver, configFile);			
		} 
		catch (Exception e) 
		{			
			System.out.println("Ha ocurrido un error al querer generar el respectivo ScreenShot: " + e.getMessage());
			
		}
	}	
}
