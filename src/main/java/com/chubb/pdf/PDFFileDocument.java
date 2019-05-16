package com.chubb.pdf;

import com.chubb.dashboard.Result;
import com.chubb.json.config.ConfigurationFile;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.io.File;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class PDFFileDocument 
{
	List<String> screenShotList;
	List<Result> listResult;

	public void createPdfDocument(ConfigurationFile configFile) throws Exception 
	{
		String timeStamp = new SimpleDateFormat(configFile.getDateFormat()).format(new Date());
		String pdfLocation = configFile.getPdfLocation() + timeStamp + "//" +configFile.getApplication() + ".pdf";		
		
    	File file = new File(pdfLocation);
        file.getParentFile().mkdirs();
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(pdfLocation));
        Document doc = new Document(pdfDoc);

        Paragraph paragraph = new Paragraph();
        paragraph.add("\n");
        paragraph.add("TABLERO DE RESULTADOS");
        doc.add(paragraph);
        
        Table tableDashboard = new Table(2);
        tableDashboard.setWidthPercent(70);
        for (Result result : listResult) {
        	tableDashboard.addCell(result.getFunctionality());
        	tableDashboard.addCell(getIcon(result.isStatus()));
		}
        //tableDashboard.addCell("");
        //tableDashboard.addCell("");

        doc.add(tableDashboard);
        
        Paragraph paragraph1 = new Paragraph();
        paragraph1.add("SCREENSHOTS");
        doc.add(paragraph1);
        
        Table tableScreenShot = new Table(1);
        tableScreenShot.setWidthPercent(100);
        
        for (String screenShotfile : screenShotList) {        	
        	System.out.println("PDF: " + screenShotfile);
            ImageData data = ImageDataFactory.create(screenShotfile);
            Image img = new Image(data);
            img.setHeight(500);
            img.setWidth(500);
            tableScreenShot.addCell(img);            
		}
        
        doc.add(tableScreenShot);
        
        
        doc.close();
        System.out.println("DONE");
    }

	private Image getIcon(boolean status) {
		ImageData data = null;
		Image image = null; 
		try 
		{
			if (status == true)
			{
				data = ImageDataFactory.create("C://Selenium//icons//right-icon.png");
			} else {
				data = ImageDataFactory.create("C://Selenium//icons//wrong.png");		
			}
			image = new Image(data);
			image.setHeight(30);
            image.setWidth(30);
		} 
		catch (MalformedURLException e) 
		{			
			e.printStackTrace();
		}
        
		return image;
	}
	
	public List<String> getScreenShotList() {
		return screenShotList;
	}

	public void setScreenShotList(List<String> screenShotList) {
		this.screenShotList = screenShotList;
	}

	public List<Result> getListResult() {
		return listResult;
	}

	public void setListResult(List<Result> listResult) {
		this.listResult = listResult;
	}      
	
}