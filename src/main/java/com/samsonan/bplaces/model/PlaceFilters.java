package com.samsonan.bplaces.model;

public class PlaceFilters {

	public final static String [] NATURE_TYPES = {"BEACH", "LAKE", "MOUNT", "RIVER", "VOLC"};
	public final static String [] CULTURE_TYPES = {"TEMPLE", "VILLAGE"};
	
    private boolean isUnesco = false; 
    private boolean isCulture = true; 
    private boolean isNature = true; 

	private String [] naturalTypes;
	private String [] culturalTypes;

	public PlaceFilters(){
		naturalTypes = NATURE_TYPES;
		culturalTypes = CULTURE_TYPES;
	}
	
	
	public boolean isUnesco() {
		return isUnesco;
	}

	public void setUnesco(boolean isUnesco) {
		this.isUnesco = isUnesco;
	}

	public boolean isCulture() {
		return isCulture;
	}

	public void setCulture(boolean isCulture) {
		this.isCulture = isCulture;
	}

	public boolean isNature() {
		return isNature;
	}

	public void setNature(boolean isNature) {
		this.isNature = isNature;
	}


	public String[] getNaturalTypes() {
		return naturalTypes;
	}


	public void setNaturalTypes(String[] naturalTypes) {
		this.naturalTypes = naturalTypes;
	}


	public String[] getCulturalTypes() {
		return culturalTypes;
	}


	public void setCulturalTypes(String[] culturalTypes) {
		this.culturalTypes = culturalTypes;
	}

	
	
}
