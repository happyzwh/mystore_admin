package com.mystore.business.dao;

import com.mystore.business.dto.ProInfo;

public interface ProInfoMapper {  
	
	/**
	 * 增加商品详情
	 * 
	 * */
	public void addProInfo(ProInfo proInfo);
	/**
	 * 修改商品详情
	 * 
	 * */
	public void updateProInfoByProId(ProInfo proInfo);
	/**
	 * 根据主键查询商品详情
	 * 
	 * */
	public ProInfo getProInfoByProId(Integer proId);
	/**
	 * 
	 * 删除用户更改用户状态
	 * 
	 * */
	public void deleteProInfoByProId(Integer proId);

}

