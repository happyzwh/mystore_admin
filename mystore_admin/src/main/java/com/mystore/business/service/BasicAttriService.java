package com.mystore.business.service;

import java.util.List;

import com.mystore.business.common.Pager;
import com.mystore.business.dto.BasicAttri;
import com.mystore.business.dto.BasicAttriValue;

public interface BasicAttriService {
	
	/**
	 * 
	 * 根据主键求实体
	 * 
	 * */
	public BasicAttri getBasicAttriById(Integer id) throws Exception;
	
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
	public void addBasicAttri(BasicAttri basicAttri) throws Exception;
	
	/**
	 * 
	 * 修改实体
	 * 
	 * */
	public void updateBasicAttriById(BasicAttri basicAttri) throws Exception;
	
	/**
	 * 
	 * 删除实体
	 * 
	 * */
	public void deleteBasicAttriById(Integer id) throws Exception;
	
	/**
	 * 
	 * 查询实体列表
	 * 
	 * */
	public Pager<BasicAttri> getBasicAttriList(BasicAttri basicAttri,Integer pageNum,Integer pageSize) throws Exception;
	
	/**
	 * 
	 * 根据主键求实体
	 * 
	 * */
	public BasicAttriValue getBasicAttriValueById(Integer id);
	
	/**
	 * 
	 * 根据值求实体
	 * 
	 * */
	public BasicAttriValue getBasicAttriValueByValue(Integer pid,String value);
	
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
	 * 查询实体列表
	 * 
	 * */
	public List<BasicAttriValue> getBasicAttriValueList(BasicAttriValue basicAttriValue);

}
