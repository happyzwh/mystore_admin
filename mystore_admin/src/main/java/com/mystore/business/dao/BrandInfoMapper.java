package com.mystore.business.dao;

import com.mystore.business.dto.BrandInfo;

public interface BrandInfoMapper {
	
	/**
	 * 根据主键得实体
	 * 
	 * */
	public BrandInfo getBrandInfoByBid(Integer bid);
	
	/**
	 * 保存实体
	 * 
	 * */
	public Integer addBrandInfo(BrandInfo brandInfo);
	
	/**
	 * 根据主键修改实体
	 * 
	 * */
	public void upateBrandInfoByBid(BrandInfo brandInfo);
	
	/**
	 * 根据主键删除实体
	 * 
	 * */
	public void deleteBrandInfoByBid(Integer bid);

}
