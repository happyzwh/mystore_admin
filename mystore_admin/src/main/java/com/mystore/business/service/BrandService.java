package com.mystore.business.service;

import java.util.List;

import com.mystore.business.dto.Brand;
import com.mystore.business.dto.BrandInfo;

public interface BrandService {
	
	/**
	 * 根据主键得实体
	 * 
	 * */
	public Brand getBrandById(Integer id) throws Exception;
	
	/**
	 * 根据主键得实体
	 * 
	 * */
	public BrandInfo getBrandInfoByBid(Integer bid) throws Exception;
	
	/**
	 * 根据父id和名称查询实体
	 * 
	 * */
	public Brand getBrandByPidAndName(Integer pid,String name) throws Exception;
	
	/**
	 * 保存实体
	 * 
	 * */
	public Integer addBrand(Brand brand) throws Exception;
	
	/**
	 * 根据主键修改实体
	 * 
	 * */
	public void upateBrandById(Brand brand) throws Exception;
	
	/**
	 * 根据主键删除实体
	 * 
	 * */
	public void deleteBrandById(Integer id) throws Exception;
	
	/**
	 * 根据父id求对象列表
	 * 
	 * */
	public List<Brand> selectBrandListByPid(Integer pid) throws Exception;
	
	/**
	 * 根据父id求对象列表数量
	 * 
	 * */
	public Integer selectCountByPid(Integer pid) throws Exception;
	
	/**
	 * 根据主键修改实体
	 * 
	 * */
	public void upateBrandInfoByBid(BrandInfo brandInfo) throws Exception;

}
