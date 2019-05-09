package com.chubb.selenium.applications;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		  
		  
		  //Validar si la página cargó correctamente
		  
		  if(isPageOK(driver)) {			  
			  process.setUlrStatus(true);
			  takeScreenShot(driver, screenShot);
			  Thread.sleep(3000);
		  }
		  else 
		  { 
			  process.setUlrStatus(false);
			  //sendEmail();
			  //WriteLog();			  
			  driver.close();
			  System.exit(0);
		  }
		  
		//Validar si usuario autenticó correctamente
		  if(isLoginOK(driver))
		  {
			process.setLogin(true);
			takeScreenShot(driver, screenShot);
			Thread.sleep(3000);
		  }
		  else {
			process.setLogin(true);
			//sendEmail();
			  //WriteLog();			  
			  driver.close();
			  System.exit(0);
			  System.err.println("Usuario o Password invalido");
		  }
		  getConsultaPolizaOK(driver, screenShot);
		  /*
		  if(getConsultaPolizaOK(driver, screenShot))
		  {
			  
		  }
		  
		  
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
	
	public boolean isPageOK(WebDriver driver)
	{
		boolean validation = false;
		String elemento = driver.findElement(By.xpath("//span[@class='ace']")).getText();
		System.out.println(elemento);
		if(elemento.equals("Chubb Latinoamérica - Software de Marine")) {
			validation = true; 
		}
		else 
		{
			validation = false;
		}		  		
		return validation;
	}
	
	public boolean isLoginOK(WebDriver driver) 
	{
		boolean validation = false;
		driver.findElement(By.linkText("Colombia")).click();		
		WebElement usuario = driver.findElement(By.name("Usuario"));
		usuario.sendKeys(configFile.getUser());
		WebElement password = driver.findElement(By.name("Pass"));
		password.sendKeys(configFile.getPassword());
		password.submit();
		String elemento = driver.findElement(By.xpath("//a[@class='Inicio']")).getText();
		if(elemento.equals("Inicio")) {
			validation = true;
		}
		else {
			validation = false;
		}					
		return validation;
	}
	
	public void getConsultaPolizaOK(WebDriver driver, ScreenShot screenShot)
	{		
		try 
		{			
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
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  			
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
