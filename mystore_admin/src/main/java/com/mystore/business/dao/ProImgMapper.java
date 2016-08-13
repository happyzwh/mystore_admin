package com.mystore.business.dao;

import java.util.List;

import com.mystore.business.dto.ProImg;

public interface ProImgMapper {  
	
	/**
	 * 增加商品图片
	 * 
	 * */
	public void addProImg(ProImg proImg);
	/**
	 * 修改商品图片
	 * 
	 * */
	public void updateProImgById(ProImg proImg);
	/**
	 * 根据主键查询商品图片
	 * 
	 * */
	public List<ProImg> getProImgListByProId(Integer proId);
	/**
	 * 
	 * 删除更改图片
	 * 
	 * */
	public void deleteProImgById(Integer id);
	
	/**
	 * 
	 * 删除更改图片
	 * 
	 * */
	public void deleteProImgByProId(Integer proId);

}

