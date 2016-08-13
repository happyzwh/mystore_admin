package com.mystore.business.dao;

import java.util.List;

import com.mystore.business.dto.CateAttri;

public interface CateAttriMapper {
	
	/**
	 * 根据分类id求对象列表
	 * 
	 * */
	public List<CateAttri> selectCateAttriListByCateId(Integer cid);
	
	/**
	 * 根据属性id求对象列表
	 * 
	 * */
	public List<CateAttri> selectCateAttriListByAttrId(Integer aid); 

	/**
	 * 增加持久对象
	 * 
	 * */
	public void addCateAttri(CateAttri cateAttri);
	
	/**
	 * 修改持久对象
	 * 
	 * */
	public void updateCateAttri(CateAttri cateAttri);
	
	
	/**
	 * 删除对象
	 * 
	 * */
	public void deleteCateAttri(CateAttri cateAttri);

}
