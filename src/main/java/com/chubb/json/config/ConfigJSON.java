package com.chubb.json.config;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ConfigJSON 
{
	ConfigurationFile configFile;
	
    public ConfigurationFile getConfigurationFile()
    {
    	try 
    	{ 
    		//Obtener path actual
    		Path path = Paths.get( System.getProperty("user.dir"));        
    	
    		//Construir path para arhivo de configuración
    		StringBuffer configPath = new StringBuffer();
	        configPath.append(path.getRoot());
	        configPath.append(path.subpath(0, (path.getNameCount()-1)));
	        configPath.append("\\config.json");        	        
			
	        //Leer archivo de configuración json
	        InputStream is = new FileInputStream(String.valueOf(configPath));
			JSONTokener tokener = new JSONTokener(is);
	        JSONObject config = new JSONObject(tokener);
	        
	        //Crear un objecto con la configuración
	        configFile = new ConfigurationFile();
	        
	        configFile.setApplication(config.getString("application"));
	        configFile.setWebdriver(config.getString("webdriver"));
	        configFile.setDriverPath(config.getString("driverPath"));
	        configFile.setWeburl(config.getString("web-url"));
	        configFile.setWeburl(config.getString("web-url"));	       	       
	        configFile.setUser(config.getString("user"));
	        configFile.setPassword(config.getString("password"));
	        configFile.setScreenShotName(config.getString("screenShotName"));
	        configFile.setImageExtension(config.getString("imageExtension"));
	        configFile.setImageLocation(config.getString("imageLocation"));
	        configFile.setPdfLocation(config.getString("pdfLocation"));	      
	        configFile.setDateFormat(config.getString("dateFormat"));
	        	        
		} 
    	catch (FileNotFoundException e) 
    	{			
			e.printStackTrace();
		}
    	return configFile;
    }
}
