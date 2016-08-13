package com.mystore.business.dao;

import java.util.List;
import java.util.Map;

import com.mystore.business.dto.BasicAttriValue;

public interface BasicAttriValueMapper {
	
	/**
	 * 
	 * 根据主键求实体
	 * 
	 * */
	public BasicAttriValue getBasicAttriValueById(Integer id);
	
	/**
	 * 
	 * 根据父id和值求实体
	 * 
	 * */
	public BasicAttriValue getBasicAttriValueByValue(Map<String,Object> map);
	
	/**
	 * 
	 * 增加实体
	 * 
	 * */
	public void addBasicAttriValue(BasicAttriValue basicAttriValue);
	
	/**
	 * 
	 * 修改实体
	 * 
	 * */
	public void updateBasicAttriValueById(BasicAttriValue basicAttriValue);
	
	/**
	 * 
	 * 删除实体
	 * 
	 * */
	public void deleteBasicAttriValueById(Integer id);
	
	/**
	 * 
	 * 删除实体
	 * 
	 * */
	public void deleteBasicAttriValueByPid(Integer pid);
	
	/**
	 * 
	 * 查询实体列表
	 * 
	 * */
	public List<BasicAttriValue> getBasicAttriValueList(BasicAttriValue basicAttriValue);

}
