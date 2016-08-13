package com.mystore.business.dto;

import java.util.Date;

public class proClick  extends BasicDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer id_pro;
	
	private Integer clickNub;
	
	private Integer collectNub;
	
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

	public Integer getClickNub() {
		return clickNub;
	}

	public void setClickNub(Integer clickNub) {
		this.clickNub = clickNub;
	}

	public Integer getCollectNub() {
		return collectNub;
	}

	public void setCollectNub(Integer collectNub) {
		this.collectNub = collectNub;
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
