package com.samsonan.bplaces.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="country")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY, region="location")
public class Country {

	@Id
	@Column(name="iso_2")
	private String codeIso2Char;

	@Column(name="iso_3")
	private String codeIso3Char;
	
	@Column(name="name")
	private String name;
	
	@Column(name="region")
	private String regionName;

	@Column(name="subregion")
	private String subRegionName;

	@Column(name="subregion_code")
	private String subRegionCode;
	
	public String getCodeIso2Char() {
		return codeIso2Char;
	}

	public String getCodeIso3Char() {
		return codeIso3Char;
	}

	public String getName() {
		return name;
	}

	public String getRegionName() {
		return regionName;
	}

	public String getSubRegionName() {
		return subRegionName;
	}

	public String getSubRegionCode() {
		return subRegionCode;
	}
	
	
	
}
