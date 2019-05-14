	package com.chubb.selenium.applications;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import com.chubb.dashboard.DashboardList;
import com.chubb.dashboard.Result;
import com.chubb.json.config.ConfigurationFile;
import com.chubb.screenshot.ScreenShot;


public class MarineTest {
	private static Logger LOG = LogManager.getLogger(MarineTest.class);
	
	List<String> listado = new ArrayList<String>();		
	ConfigurationFile configFile;
	DashboardList dashboardList;
	Result result;
	String nameofCurrMethod = null;
		
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

	public DashboardList getDashboard() {
		return dashboardList;
	}

	public void setDashboard(DashboardList dashboardList) {
		this.dashboardList = dashboardList;
	}

	public void startTest()
	{					
		// Optional, if not specified, WebDriver will search your path for chromedriver.
		System.setProperty(configFile.getWebdriver(), configFile.getDriverPath());
		WebDriver driver = new ChromeDriver();
		ScreenShot screenShot = new ScreenShot();		
		List<Result> listResult = new ArrayList<Result>();
		dashboardList.setListResult(listResult);  
		try 
		{
		  driver.manage().window().maximize();

		  //Validar si la página cargó correctamente	
		  Status status = isPageLoadOK(driver); 		  
		  if(status.isValidation()) 
		  {			  
			  takeScreenShot(driver, screenShot);			  
			  addResult2List("Load Page", status.isValidation(), status.getMessage(), status.getException());
			  status = null;
			  Thread.sleep(3000);
		  }
		  else 
		  {
			  addResult2List("Load Page", status.isValidation(), status.getMessage(), status.getException());		  			 			 
			  //sendEmail();			  			  		  
			  driver.close();
			  System.exit(0);
		  }
		  		  
		//Validar si usuario autenticó correctamente
		  status = isLoginOK(driver); 
		  if(status.isValidation())
		  {
			addResult2List("Login", status.isValidation(), status.getMessage(), status.getException() );			
			takeScreenShot(driver, screenShot);
			Thread.sleep(3000);
		  }
		  else {
			  addResult2List("Login", status.isValidation(), status.getMessage(), status.getException());
			  //sendEmail();			  			  
			  driver.close();
			  System.exit(0);			  
		  }
		  getConsultaPolizaOK(driver, screenShot);
		  driver.quit();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		listado = screenShot.getScreenShotList();
	}
	
	
	public Status isPageLoadOK(WebDriver driver)
	{		
		Status status = new Status();
		nameofCurrMethod = new Exception().getStackTrace()[0].getMethodName();
		try 
		{
			driver.get(configFile.getWeburl());
			String elemento = driver.findElement(By.xpath("//span[@class='ace']")).getText();
			if(elemento.equals("Chubb Latinoamérica - Software de Marine")) {
				status.setValidation(true);
				status.setMessage("Elemento encontrado: " + elemento + ". Página carga correctamente");
				LOG.info(status.getMessage());
			}
			else 
			{
				status.setValidation(false);
				status.setMessage("Por favor validar la etiqueta de comparación para esta prueba");
				LOG.error(status.getMessage());
				
			}		  	
		}
		catch (NoSuchElementException e) {
			status.setValidation(false);			
			status.setMessage("URL no alcanzable. Por favor validar conectividad al url "+configFile.getWeburl()+ " desde el sitio de prueba");
			status.setException(e.getMessage());
			LOG.fatal(status.getMessage());
			LOG.fatal("Excepción capturada: " + e.getMessage());
			return status;
		}		
		return status;
	}	
	
	public Status isLoginOK(WebDriver driver) 
	{
		Status status = new Status();		
		nameofCurrMethod = new Exception().getStackTrace()[0].getMethodName();
		try {
			driver.findElement(By.linkText("Colombia")).click();		
			WebElement usuario = driver.findElement(By.name("Usuario"));
			usuario.sendKeys(configFile.getUser());
			WebElement password = driver.findElement(By.name("Pass"));
			password.sendKeys(configFile.getPassword());
			password.submit();
			String elemento = driver.findElement(By.xpath("//a[@class='Inicio ']")).getText();
			if(elemento.equals("Inicio")) {				
				status.setValidation(true);
				status.setMessage("La prueba supero la fase de login");
				LOG.info(status.getMessage());
			}
			else {
				status.setValidation(false);
				status.setMessage("La prueba generó un error al hacer Login");
				LOG.info(status.getMessage());
			}
		}
		catch (NoSuchElementException e) {
			status.setValidation(false);			
			status.setMessage("Error al hacer login en URL seleccionado.");
			status.setException(e.getMessage());
			LOG.fatal(status.getMessage());
			LOG.fatal("Excepción capturada: " + e.getMessage());
			return status;
		}
							
		return status;
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
	
	private Result addResult2List(String functionality, boolean status, String message, String exception) {
		Result result = new Result();
		result.setFunctionality(functionality);		
		result.setStatus(status);
		result.setMessage(message);
		result.setException(exception);
		System.out.println(result.getMessage());
		dashboardList.addResult(result);
		
		return result;
	}
}
