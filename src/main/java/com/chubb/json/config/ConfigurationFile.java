package com.chubb.json.config;

public class ConfigurationFile 
{
	String application;
	String webdriver;
	String driverPath;
	String weburl;
	String user;
	String password;
	String screenShotName;
	String imageExtension;
	String imageLocation;
	String pdfLocation;
	String dateFormat;
	String currentDateTime;
	
	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getWebdriver() {
		return webdriver;
	}

	public void setWebdriver(String webdriver) {
		this.webdriver = webdriver;
	}

	public String getDriverPath() {
		return driverPath;
	}

	public void setDriverPath(String driverPath) {
		this.driverPath = driverPath;
	}

	public String getWeburl() {
		return weburl;
	}
	
	public void setWeburl(String weburl) {
		this.weburl = weburl;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getScreenShotName() {
		return screenShotName;
	}
	
	public void setScreenShotName(String screenShotName) {
		this.screenShotName = screenShotName;
	}
	
	public String getImageExtension() {
		return imageExtension;
	}
	
	public void setImageExtension(String imageExtension) {
		this.imageExtension = imageExtension;
	}
	
	public String getImageLocation() {
		return imageLocation;
	}
	
	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}
	
	public String getPdfLocation() {
		return pdfLocation;
	}
	
	public void setPdfLocation(String pdfLocation) {
		this.pdfLocation = pdfLocation;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getCurrentDateTime() {
		return currentDateTime;
	}

	public void setCurrentDateTime(String currentDateTime) {
		this.currentDateTime = currentDateTime;
	}			
}
