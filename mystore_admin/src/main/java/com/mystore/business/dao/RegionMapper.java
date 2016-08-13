package com.mystore.business.dao;

import java.util.List;
import java.util.Map;

import com.mystore.business.dto.Region;

public interface RegionMapper {
	
	/**
	 * 根据主键得实体
	 * 
	 * */
	public Region getRegionById(Integer id);
	
	/**
	 * 根据父id和名称查询实体
	 * 
	 * */
	public Region getRegionByPidAndName(Map<String,Object> map);
	
	/**
	 * 保存实体
	 * 
	 * */
	public Integer addRegion(Region region);
	
	/**
	 * 根据主键修改实体
	 * 
	 * */
	public void upateRegionById(Region region);
	
	/**
	 * 根据主键删除实体
	 * 
	 * */
	public void deleteRegionById(Integer id);
	
	/**
	 * 根据父id求对象列表
	 * 
	 * */
	public List<Region> selectRegionListByPid(Integer pid);
	
	/**
	 * 根据父id求对象列表数量
	 * 
	 * */
	public Integer selectCountByPid(Integer pid);

}
