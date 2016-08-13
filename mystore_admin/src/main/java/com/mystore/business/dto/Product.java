package com.mystore.business.dto;

import java.util.Date;

public class Product extends BasicDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer id_cate;
	
	private String cateName;
	
	private Integer id_brand;
	
	private String brandName;
	
	private String name;
	
	private String sn;
	
	private String shortTitle;
	
	private String subTitle;
	
	private String enTitle;
	
	private String rome;
	
	private String jianPin;
	
	private String keyWords;
	
	private String path_img;
	
	private String descr;
	
	private String isShow;
	
	private String isOnSale;
	
	private String isShipping;
	
	private Date onOrOffSaleTime;
	
	private Date onSaleStartTime;
	
	private Date onSaleEndTime;
	
	private Date offSaleStartTime;
	
	private Date offSaleEndTime;

	private Integer sort;
	
	private String status;
	
	private Date createDate;
	
	private Date lastDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_cate() {
		return id_cate;
	}

	public void setId_cate(Integer idCate) {
		id_cate = idCate;
	}

	public Integer getId_brand() {
		return id_brand;
	}

	public void setId_brand(Integer idBrand) {
		id_brand = idBrand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getEnTitle() {
		return enTitle;
	}

	public void setEnTitle(String enTitle) {
		this.enTitle = enTitle;
	}

	public String getRome() {
		return rome;
	}

	public void setRome(String rome) {
		this.rome = rome;
	}

	public String getJianPin() {
		return jianPin;
	}

	public void setJianPin(String jianPin) {
		this.jianPin = jianPin;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getPath_img() {
		return path_img;
	}

	public void setPath_img(String pathImg) {
		path_img = pathImg;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getIsOnSale() {
		return isOnSale;
	}

	public void setIsOnSale(String isOnSale) {
		this.isOnSale = isOnSale;
	}

	public String getIsShipping() {
		return isShipping;
	}

	public void setIsShipping(String isShipping) {
		this.isShipping = isShipping;
	}

	public Date getOnOrOffSaleTime() {
		return onOrOffSaleTime;
	}

	public void setOnOrOffSaleTime(Date onOrOffSaleTime) {
		this.onOrOffSaleTime = onOrOffSaleTime;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Date getOnSaleStartTime() {
		return onSaleStartTime;
	}

	public void setOnSaleStartTime(Date onSaleStartTime) {
		this.onSaleStartTime = onSaleStartTime;
	}

	public Date getOnSaleEndTime() {
		return onSaleEndTime;
	}

	public void setOnSaleEndTime(Date onSaleEndTime) {
		this.onSaleEndTime = onSaleEndTime;
	}

	public Date getOffSaleStartTime() {
		return offSaleStartTime;
	}

	public void setOffSaleStartTime(Date offSaleStartTime) {
		this.offSaleStartTime = offSaleStartTime;
	}

	public Date getOffSaleEndTime() {
		return offSaleEndTime;
	}

	public void setOffSaleEndTime(Date offSaleEndTime) {
		this.offSaleEndTime = offSaleEndTime;
	}

}
