package com.chubb.pdf;

import com.chubb.json.config.ConfigurationFile;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class PDFFileDocument 
{
	List<String> screenShotList;

	public void manipulatePdf(ConfigurationFile configFile) throws Exception 
	{
		String timeStamp = new SimpleDateFormat(configFile.getDateFormat()).format(new Date());
		String pdfLocation = configFile.getPdfLocation() + timeStamp + "//" +configFile.getApplication() + ".pdf";		
		
    	File file = new File(pdfLocation);
        file.getParentFile().mkdirs();
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(pdfLocation));
        Document doc = new Document(pdfDoc);

        Table table = new Table(1);
        table.setWidthPercent(100);
        
        for (String screenShotfile : screenShotList) {        	
        	System.out.println("PDF: " + screenShotfile);
        	table.addCell(new Image(ImageDataFactory.create(screenShotfile)).setAutoScale(true));
        	Thread.sleep(3000);
		}
        table.addCell("END OF DOCUMENT");
        doc.add(table);

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