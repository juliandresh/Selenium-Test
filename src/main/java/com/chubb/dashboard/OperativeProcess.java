package com.chubb.dashboard;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.chubb.json.config.ConfigurationFile;

public class OperativeProcess {
	
	boolean ulrStatus;
	boolean login;
	
	Map<String, Boolean> process = new HashMap<String, Boolean>();
	
	
	public static void main(String[] args) {
		
		ConfigurationFile file = new ConfigurationFile();
		Map<String, Boolean> process = new HashMap<String, Boolean>();
		file.setApplication("WIS");
		file.setImageExtension(".jpg");
		file.setPassword("pepito");
		file.setUser("perez");
		process.put("Crear Polizas", true);
		process.put("Consultar Polizas", false);
		JSONObject jo = new JSONObject(file);
		jo.put("Polizas", process);
		System.out.println(jo);
		
	}


	public boolean isUlrStatus() {
		return ulrStatus;
	}

	public void setUlrStatus(boolean ulrStatus) {
		this.ulrStatus = ulrStatus;
	}

	
	public boolean isLogin() {
		return login;
	}


	public void setLogin(boolean login) {
		this.login = login;
	}


	public Map<String, Boolean> getProcess() {
		return process;
	}


	public void setProcess(Map<String, Boolean> process) {
		this.process = process;
	}
	
	
}
