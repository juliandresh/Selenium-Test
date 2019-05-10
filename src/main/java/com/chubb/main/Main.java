package com.chubb.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.chubb.dashboard.DashboardList;
import com.chubb.json.config.ConfigJSON;
import com.chubb.json.config.ConfigurationFile;
import com.chubb.pdf.PDFFileDocument;
import com.chubb.selenium.applications.MarineTest;

public class Main {

	public static void main(String[] args) {
				
		ConfigJSON json = new ConfigJSON();
		ConfigurationFile configFile = json.getConfigurationFile();
		String timeStamp = new SimpleDateFormat(configFile.getDateFormat()).format(new Date());
		MarineTest marine = new MarineTest();
		DashboardList dashboard = new DashboardList();
		PDFFileDocument pdfFileDocument = new PDFFileDocument();
		configFile.setCurrentDateTime(timeStamp);
		
		
		//Iniciar Prueba a Sitio Web con Selenium		
		marine.setConfigFile(configFile);
		marine.setDashboard(dashboard);
		marine.startTest();
				
		//Obtener Listado de ScreenShots generados
		List<String> listadoArchivos = marine.getListado();
		//Enviar listado de ScreenShots a modulo de PDF 
		pdfFileDocument.setScreenShotList(listadoArchivos);
		
		try 
		{	
			//Crear archivo PDF con listado de imagenes
			pdfFileDocument.createPdfDocument(configFile);			
		} 
		catch (Exception e1) 
		{		
			e1.printStackTrace();
		}
							
		dashboard.createJSONFile(marine.getDashboard());
		/*List<Result> listResult = marine.getDashboard().getListResult();
		for (Result result : listResult) {
			System.out.println(result.getFunctionality() + "-" + result.getAction() + "-" + result.isStatus() + "-" + result.getMessage());
		}*/
		//Crear PDF TABLERO

	}

}
