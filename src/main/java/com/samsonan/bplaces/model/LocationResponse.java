package com.samsonan.bplaces.model;

import java.util.Map;

public class LocationResponse {

	private String tableName;
	private Map<String,String> locationMap;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Map<String, String> getLocationMap() {
		return locationMap;
	}
	public void setLocationMap(Map<String, String> locationMap) {
		this.locationMap = locationMap;
	}
	
	
	
	
}
