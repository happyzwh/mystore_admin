package com.mystore.business.dao;

import java.util.List;
import java.util.Map;

import com.mystore.business.dto.ProAttr;

public interface ProAttrMapper {  
	
	/**
	 * 增加商品属性
	 * 
	 * */
	public void addProAttr(ProAttr proAttr);
	/**
	 * 修改商品属性
	 * 
	 * */
	public void updateProAttrById(ProAttr proAttr);
	/**
	 * 根据商品id得商品属性
	 * 
	 * */
	public List<ProAttr> getProAttrByProId(Integer proId);
	/**
	 * 
	 * 删除商品属性
	 * 
	 * */
	public void deleteProAttrByProId(Integer proId);
	/**
	 * 
	 * 删除商品属性
	 * 
	 * */
	public void deleteProAttrByCateAttrId(Integer cateAttrId);
	/**
	 * 根据商品id及分类属性id得商品属性
	 * 
	 * */
	public ProAttr getProAttrByMap(Map<String,Object> map);
	/**
	 * 根据分类属性id删除商品分类
	 * 
	 * */
	public void deletProAttrByCateAttrId(Integer cateAttrId);

}

