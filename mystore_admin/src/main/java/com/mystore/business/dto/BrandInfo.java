package com.mystore.business.dto;

import java.util.Date;

public class BrandInfo extends BasicDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer id_brand;
	
	private String manufacturer;
	
	private Integer id_region;
	
	private String regName;
	
	private String info;
	
	private String culture;
	
	private String honorsandawards;
	
	private String qualification;
	
	private String status;
	
	private Date createDate;
	
	private Date lastDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_brand() {
		return id_brand;
	}

	public void setId_brand(Integer idBrand) {
		id_brand = idBrand;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Integer getId_region() {
		return id_region;
	}

	public void setId_region(Integer idRegion) {
		id_region = idRegion;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getCulture() {
		return culture;
	}

	public void setCulture(String culture) {
		this.culture = culture;
	}

	public String getHonorsandawards() {
		return honorsandawards;
	}

	public void setHonorsandawards(String honorsandawards) {
		this.honorsandawards = honorsandawards;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public String getRegName() {
		return regName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}
	
}
