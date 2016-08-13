package com.mystore.business.dao;

import com.mystore.business.dto.ProPrice;

public interface ProPriceMapper {  
	
	/**
	 * 增加商品价格
	 * 
	 * */
	public void addProPrice(ProPrice proPrice);
	/**
	 * 修改商品价格
	 * 
	 * */
	public void updateProPriceByProId(ProPrice proPrice);
	/**
	 * 根据主键查询商品价格
	 * 
	 * */
	public ProPrice getProPriceByProId(Integer proId);
	/**
	 * 
	 * 删除用户更改用户状态
	 * 
	 * */
	public void deleteProPriceByProId(Integer proId);

}

