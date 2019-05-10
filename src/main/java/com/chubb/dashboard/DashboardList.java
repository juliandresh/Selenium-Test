package com.chubb.dashboard;

import java.util.List;
import org.json.JSONObject;


public class DashboardList {
	
	List<Result> listResult;	
		
	public List<Result> getListResult() {
		return listResult;
	}


	public void setListResult(List<Result> listResult) {
		this.listResult = listResult;
	}
	
	public void addResult(Result result) {
		listResult.add(result);
	}	
	
	public void createJSONFile(DashboardList dashboardResult) {
		
		JSONObject jo = new JSONObject(dashboardResult);		
		System.out.println(jo);
		
	}
}
