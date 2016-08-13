package com.mystore.business.dto;

import java.util.Date;

public class ProInfo extends BasicDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer id_pro;
	
	private String provider;
	
	private String descr;
	
	private String feature;
	
	private String proShow;
	
	private String areaIntroduce;
	
	private String qualification;
	
	private String useMethod;
	
	private String brandCulture;
	
	private String honor;
	
	private String providerIntroduce;
	
	private String status;
	
	private Date createDate;
	
	private Date lastDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_pro() {
		return id_pro;
	}

	public void setId_pro(Integer idPro) {
		id_pro = idPro;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getProShow() {
		return proShow;
	}

	public void setProShow(String proShow) {
		this.proShow = proShow;
	}

	public String getAreaIntroduce() {
		return areaIntroduce;
	}

	public void setAreaIntroduce(String areaIntroduce) {
		this.areaIntroduce = areaIntroduce;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getUseMethod() {
		return useMethod;
	}

	public void setUseMethod(String useMethod) {
		this.useMethod = useMethod;
	}

	public String getBrandCulture() {
		return brandCulture;
	}

	public void setBrandCulture(String brandCulture) {
		this.brandCulture = brandCulture;
	}

	public String getHonor() {
		return honor;
	}

	public void setHonor(String honor) {
		this.honor = honor;
	}

	public String getProviderIntroduce() {
		return providerIntroduce;
	}

	public void setProviderIntroduce(String providerIntroduce) {
		this.providerIntroduce = providerIntroduce;
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

}
