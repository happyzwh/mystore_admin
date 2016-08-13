package com.mystore.business.dto;

import java.util.Date;

public class AdvResourceModule extends BasicDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer pid;
	
	private Integer id_pro;
	
	private String sn;
	
	private String name;
	
	private Integer sort;
	
	private String url;
	
	private String path_pic;
	
	private String content;
	
	private String isMarkShow;
	
	private String isShow;
	
	private String status;
	
	private Date createDate;
	
	private Date lastDate;
	
	private Product product;
	
	private Category category;
	
	private Brand brand;

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

	public Integer getId_pro() {
		return id_pro;
	}

	public void setId_pro(Integer idPro) {
		id_pro = idPro;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPath_pic() {
		return path_pic;
	}

	public void setPath_pic(String pathPic) {
		path_pic = pathPic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIsMarkShow() {
		return isMarkShow;
	}

	public void setIsMarkShow(String isMarkShow) {
		this.isMarkShow = isMarkShow;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	
}
