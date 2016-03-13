package com.samsonan.bplaces.model;

/**
 * Class to transfer filter values to/from form 
 * @author ShamanXXI
 *
 * For categories: http://www.worldheritagesite.org/categories/category.php?id=10
 *
 */
public class PlaceFilters {

	public final static String [] NATURE_TYPES = {"FLORA_FAUNA", "DESERT", "FOREST", "RIVER_LAKE", "VOLC", "COAST","MOUNT", "CAVE", "NAT"};
	public final static String [] CULTURE_TYPES = {"RELIG", "URBAN", "ARCH", "CULT_LND", "CULT"};

	private String region; //region/subregion code 
	private String [] countries;
	
    private boolean isUnesco = false; 
    
    /* only to maintain visual state of the filters */
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

	public String getRegion() {
		if (region!=null && region.isEmpty())
			return null;
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	public String[] getCountries() {
		return countries;
	}

	public void setCountries(String[] countries) {
		this.countries = countries;
	}

	@Override
    public String toString() {
        return "PlaceFilters [isUnesco=" + isUnesco + ", naturalTypes="+naturalTypes+", culturalTypes=" + culturalTypes + "]";
    }	
	
}
