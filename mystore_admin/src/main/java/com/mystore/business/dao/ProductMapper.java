package com.mystore.business.dao;

import java.util.List;
import java.util.Map;

import com.mystore.business.dto.Product;

public interface ProductMapper {  
	
	/**
	 * 增加商品
	 * 
	 * */
	public void addProduct(Product pro);
	/**
	 * 修改商品
	 * 
	 * */
	public void updateProById(Product pro);
	/**
	 * 根据主键查询商品
	 * 
	 * */
	public Product getProById(Integer id);
	
	/**
	 * 根据查询商品
	 * @param name:名称;sn:编号
	 * 
	 * */
	public Product getProduct(Map<String,Object> map);
    
	/**
	 * 
	 * 查询用户列表
	 * 
	 * */
	public List<Product> selectProList(Product pro);
	/**
	 * 查询用户数量
	 * 
	 * */
	public int selectProCount(Product pro);
	/**
	 * 
	 * 删除用户更改用户状态
	 * 
	 * */
	public void deleteProById(Integer id);
	
	/**
	 * 
	 * 批量更改上下架
	 * 
	 * */
	public void updateBatchProIsOnSale(Map<String,Object> map);
	
	/**
	 * 根据商品名称模糊查询商品属性
	 * 
	 * 
	 * */
	public List<Map<String,Object>> selectProListByLikeNameOrSn(String name);

}

