package com.mystore.business.dto;

import java.util.Date;

public class ProAttr extends BasicDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer id_pro;
	
	private Integer id_cateAttr;
	
	private CateAttri cateAttr;
	
	private Integer id_attr;
	
	private String showType;
	
	private String attrValue;
	
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

	public Integer getId_pro() {
		return id_pro;
	}

	public void setId_pro(Integer idPro) {
		id_pro = idPro;
	}

	public Integer getId_cateAttr() {
		return id_cateAttr;
	}

	public void setId_cateAttr(Integer idCateAttr) {
		id_cateAttr = idCateAttr;
	}

	public Integer getId_attr() {
		return id_attr;
	}

	public void setId_attr(Integer idAttr) {
		id_attr = idAttr;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
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

	public CateAttri getCateAttr() {
		return cateAttr;
	}

	public void setCateAttr(CateAttri cateAttr) {
		this.cateAttr = cateAttr;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

}
