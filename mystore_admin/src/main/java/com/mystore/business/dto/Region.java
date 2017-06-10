package com.mystore.business.dto;

import java.util.List;

import com.mystore.business.annotation.ExcludeAnnotation;

public class Region extends BasicDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private Integer id;
	
	@ExcludeAnnotation
	private Integer pid;
	
	private String name;
	
	@ExcludeAnnotation
	private String type;
	
	@ExcludeAnnotation
	private String code;

	@ExcludeAnnotation
	private Integer sort;
	
	private List<Region> sons;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<Region> getSons() {
		return sons;
	}

	public void setSons(List<Region> sons) {
		this.sons = sons;
	}

}
