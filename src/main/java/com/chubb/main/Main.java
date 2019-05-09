package com.chubb.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.chubb.dashboard.OperativeProcess;
import com.chubb.json.config.ConfigJSON;
import com.chubb.json.config.ConfigurationFile;
import com.chubb.pdf.PDFFileDocument;
import com.chubb.selenium.applications.MarineTest;

public class Main {

	public static void main(String[] args) {
				
		ConfigJSON json = new ConfigJSON();
		ConfigurationFile configFile = json.getConfigurationFile();
		String timeStamp = new SimpleDateFormat(configFile.getDateFormat()).format(new Date());
		configFile.setCurrentDateTime(timeStamp);
		
		
		//Iniciar Prueba a Sitio Web con Selenium
		MarineTest marine = new MarineTest();
		marine.setConfigFile(configFile);
		marine.startTest();
		
		//Creación de Archivo PDF
		PDFFileDocument pdfFileDocument = new PDFFileDocument();
		//Obtener Listado de ScreenShots generados
		List<String> listadoArchivos = marine.getListado();
		//Enviar listado de ScreenShots a modulo de PDF 
		pdfFileDocument.setScreenShotList(listadoArchivos);
		
		try 
		{	
			//Crear archivo PDF con listado de imagenes
			pdfFileDocument.manipulatePdf(configFile);
		} 
		catch (Exception e1) 
		{		
			e1.printStackTrace();
		}

	}

}
