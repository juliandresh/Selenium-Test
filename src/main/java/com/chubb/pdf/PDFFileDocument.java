package com.chubb.pdf;

import com.chubb.json.config.ConfigurationFile;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class PDFFileDocument 
{
	List<String> screenShotList;

	public void createPdfDocument(ConfigurationFile configFile) throws Exception 
	{
		String timeStamp = new SimpleDateFormat(configFile.getDateFormat()).format(new Date());
		String pdfLocation = configFile.getPdfLocation() + timeStamp + "//" +configFile.getApplication() + ".pdf";		
		
    	File file = new File(pdfLocation);
        file.getParentFile().mkdirs();
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(pdfLocation));
        Document doc = new Document(pdfDoc);

        Table tableScreenShot = new Table(1);
        tableScreenShot.setWidthPercent(100);
        
        for (String screenShotfile : screenShotList) {        	
        	System.out.println("PDF: " + screenShotfile);
        	tableScreenShot.addCell(new Image(ImageDataFactory.create(screenShotfile)).setAutoScale(true));
        	//Thread.sleep(3000);
		}
        tableScreenShot.addCell("END OF DOCUMENT");
        doc.add(tableScreenShot);
        
        Paragraph paragraph = new Paragraph();
        paragraph.add("\n");
        paragraph.add("TABLERO DE RESULTADOS");
        doc.add(paragraph);
        
        Table tableDashboard = new Table(2);
        tableDashboard.setWidthPercent(70);
        tableDashboard.addCell("");
        tableDashboard.addCell("");

        doc.add(tableDashboard);
        doc.close();
        System.out.println("DONE");
    }
	
	public List<String> getScreenShotList() {
		return screenShotList;
	}

	public void setScreenShotList(List<String> screenShotList) {
		this.screenShotList = screenShotList;
	}      
	
	
}