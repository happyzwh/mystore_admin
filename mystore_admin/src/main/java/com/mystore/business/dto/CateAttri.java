package com.mystore.business.dto;

import java.util.Date;

public class CateAttri extends BasicDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer id_cate;
	
	private Integer id_attr;
	
	private BasicAttri basicAttr;
	
	private String attriName;
	
	private Integer sort;
	
	private Date createDate;
	
	private Date lastDate;
	
	private Boolean isOwn;

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

	public Integer getId_attr() {
		return id_attr;
	}

	public void setId_attr(Integer idAttr) {
		id_attr = idAttr;
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

	public String getAttriName() {
		return attriName;
	}

	public void setAttriName(String attriName) {
		this.attriName = attriName;
	}

	public Boolean getIsOwn() {
		return isOwn;
	}

	public void setIsOwn(Boolean isOwn) {
		this.isOwn = isOwn;
	}

	public BasicAttri getBasicAttr() {
		return basicAttr;
	}

	public void setBasicAttr(BasicAttri basicAttr) {
		this.basicAttr = basicAttr;
	}

}
