package com.mystore.business.dto;

import java.util.Date;

public class Brand extends BasicDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer pid;
	
	private String pids;
	
	private Integer id_cate;
	
	private String name;
	
	private String rome;
	
	private String jianPin;
	
	private String enName;
	
	private String keyWords;
	
	private String descr;
	
	private String status;
	
	private String path_logoimg_fir;
	
	private String path_logoimg_sec;
	
	private String url;
	
	private Integer sort;
	
	private Date createDate;
	
	private Date lastDate;
	
	private BrandInfo brandInfo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getId_cate() {
		return id_cate;
	}

	public void setId_cate(Integer idCate) {
		id_cate = idCate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPath_logoimg_fir() {
		return path_logoimg_fir;
	}

	public void setPath_logoimg_fir(String pathLogoimgFir) {
		path_logoimg_fir = pathLogoimgFir;
	}

	public String getPath_logoimg_sec() {
		return path_logoimg_sec;
	}

	public void setPath_logoimg_sec(String pathLogoimgSec) {
		path_logoimg_sec = pathLogoimgSec;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	public BrandInfo getBrandInfo() {
		return brandInfo;
	}

	public void setBrandInfo(BrandInfo brandInfo) {
		this.brandInfo = brandInfo;
	}

	public String getPids() {
		return pids;
	}

	public void setPids(String pids) {
		this.pids = pids;
	}
	
}
