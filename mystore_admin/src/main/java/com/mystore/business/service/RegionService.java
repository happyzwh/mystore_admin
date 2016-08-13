package com.mystore.business.service;

import java.util.List;

import com.mystore.business.dto.Region;

public interface RegionService {
	
	/**
	 * 根据主键得实体
	 * 
	 * */
	public Region getRegionById(Integer id) throws Exception;
	
	/**
	 * 根据父id和名称查询实体
	 * 
	 * */
	public Region getRegionByPidAndName(Integer pid,String name) throws Exception;
	
	/**
	 * 保存实体
	 * 
	 * */
	public Integer addRegion(Region region) throws Exception;
	
	/**
	 * 根据主键修改实体
	 * 
	 * */
	public void upateRegionById(Region region) throws Exception;
	
	/**
	 * 根据主键删除实体
	 * 
	 * */
	public void deleteRegionById(Integer id) throws Exception;
	
	/**
	 * 根据父id求对象列表
	 * 
	 * */
	public List<Region> selectRegionListByPid(Integer pid) throws Exception;
	
	/**
	 * 根据父id求对象列表数量
	 * 
	 * */
	public Integer selectCountByPid(Integer pid) throws Exception;

}
