package com.mystore.business.dao;

import java.util.List;

import com.mystore.business.dto.BasicAttri;

public interface BasicAttriMapper {
	
	/**
	 * 
	 * 根据主键求实体
	 * 
	 * */
	public BasicAttri getBasicAttriById(Integer id);
	
	/**
	 * 
	 * 根据名称求实体
	 * 
	 * */
	public BasicAttri getBasicAttriByName(String name);
	
	/**
	 * 
	 * 增加实体
	 * 
	 * */
	public void addBasicAttri(BasicAttri basicAttri);
	
	/**
	 * 
	 * 修改实体
	 * 
	 * */
	public void updateBasicAttriById(BasicAttri basicAttri);
	
	/**
	 * 
	 * 删除实体
	 * 
	 * */
	public void deleteBasicAttriById(Integer id);
	
	/**
	 * 
	 * 查询实体列表
	 * 
	 * */
	public List<BasicAttri> getBasicAttriList(BasicAttri basicAttri);
	
	/**
	 * 
	 * 查询实体数量
	 * 
	 * */
	public Integer getBasicAttriCount(BasicAttri basicAttri);
}
