package com.samsonan.bplaces.model;

public class LocationRequest {

	private String key;
	private String reqTableName;
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getReqTableName() {
		return reqTableName;
	}
	
	public void setReqTableName(String tableName) {
		this.reqTableName = tableName;
	}
	
	@Override
	public String toString() {
		return "LocationRequest [key=" + key + ", reqTableName=" + reqTableName + "]";
	}
	
	
	
}
