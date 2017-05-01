package com.mystore.business.dao;

import java.util.List;
import java.util.Map;

import com.mystore.business.dto.Category;

public interface CategoryMapper {
	
	/**
	 * 根据主键得实体
	 * 
	 * */
	public Category getCateById(Integer id);
	
	/**
	 * 根据父id和名称查询实体
	 * 
	 * */
	public Category getCateByPidAndName(Map<String,Object> map);
	
	/**
	 * 保存实体
	 * 
	 * */
	public Integer addCategory(Category cate);
	
	/**
	 * 根据主键修改实体
	 * 
	 * */
	public void upateCateById(Category cate);
	
	/**
	 * 根据主键删除实体
	 * 
	 * */
	public void deleteCateById(Integer id);
	
	/**
	 * 根据父id求对象列表
	 * 
	 * */
	public List<Category> selectCateListByPid(Integer pid);
	
	/**
	 * 根据父id求对象列表数量
	 * 
	 * */
	public Integer selectCountByPid(Integer pid);
	
	/**
	 * 
	 * 批量更新分类状态为不可用
	 * 
	 * */
	public void batchUpdateCateStatusNoByIds(List<Integer> ids) throws Exception;

}
