package com.chubb.screenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.chubb.json.config.ConfigurationFile;


public class ScreenShot {
	
	List<String> screenShotList = new ArrayList<String>();
	
	/**
	 * 
	 * @param driver: Driver Selenium creado para el Test
	 * @param path: Ruta donde se almacenan los screeShots
	 * @param fileName: Nombre del ScreenShot
	 * @param extensionFile: Extensión de la imagen (.jpg, .gif, .bpm)
	 * @throws Exception: Posible excepción si no se encuentra la ruta
	 */
	public void screenCapture(WebDriver driver, ConfigurationFile configFile)
	{		
		//Crear un formato de fecha
		String timeStamp = new SimpleDateFormat(configFile.getDateFormat()).format(new Date());			
				
		//Creación del directorio y el nombre del ScreenShot
		StringBuffer builderPath = new StringBuffer();
		builderPath.append(configFile.getImageLocation());
		builderPath.append("//");
		builderPath.append(configFile.getCurrentDateTime());
		builderPath.append("//");
		builderPath.append(configFile.getApplication());
		builderPath.append(timeStamp);
		builderPath.append(configFile.getImageExtension());		
				
		String filePath = builderPath.toString();		
		
		//Funcionalidad que permite crear el ScreenShot con la clase TakesScreenShot con el driver como parametro
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		//Llamado al método getScreenshotAs para crear una arhivo de imagen
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		//Mover el archivo al destino especificado
		File file = new File(filePath);
		try 
		{
			FileUtils.copyFile(SrcFile, file);
		} 
		catch (IOException e) 
		{		
			e.printStackTrace();
		}
		//Adicionar en el listado el path del ScreenShot Creado 
		screenShotList.add(filePath);		
	}

	//Obtener listado de ScreenShots generados en el proceso de prueba
	public List<String> getScreenShotList() {		
		return screenShotList;
	}	

}
