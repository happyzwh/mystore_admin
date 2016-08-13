package com.mystore.business.dao;

import java.util.List;
import java.util.Map;

import com.mystore.business.dto.Brand;

public interface BrandMapper {
	
	/**
	 * 根据主键得实体
	 * 
	 * */
	public Brand getBrandById(Integer id);
	
	/**
	 * 根据父id和名称查询实体
	 * 
	 * */
	public Brand getBrandByPidAndName(Map<String,Object> map);
	
	/**
	 * 保存实体
	 * 
	 * */
	public Integer addBrand(Brand brand);
	
	/**
	 * 根据主键修改实体
	 * 
	 * */
	public void upateBrandById(Brand brand);
	
	/**
	 * 根据主键删除实体
	 * 
	 * */
	public void deleteBrandById(Integer id);
	
	/**
	 * 根据父id求对象列表
	 * 
	 * */
	public List<Brand> selectBrandListByPid(Integer pid);
	
	/**
	 * 根据父id求对象列表数量
	 * 
	 * */
	public Integer selectCountByPid(Integer pid);

}
