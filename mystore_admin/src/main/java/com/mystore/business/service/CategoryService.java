package com.mystore.business.service;

import java.util.List;

import com.mystore.business.dto.CateAttri;
import com.mystore.business.dto.Category;

public interface CategoryService {
	
	/**
	 * 根据主键得实体
	 * 
	 * */
	public Category getCateById(Integer id) throws Exception;
	
	/**
	 * 根据父id和名称查询实体
	 * 
	 * */
	public Category getCateByPidAndName(Integer pid,String name) throws Exception;
	
	/**
	 * 保存实体
	 * 
	 * */
	public Integer addCategory(Category cate) throws Exception;
	
	/**
	 * 根据主键修改实体
	 * 
	 * */
	public void upateCateById(Category cate) throws Exception;
	
	/**
	 * 根据主键删除实体
	 * 
	 * */
	public void deleteCateById(Integer id) throws Exception;
	
	/**
	 * 根据父id求对象列表
	 * 
	 * */
	public List<Category> selectCateListByPid(Integer pid) throws Exception;
	
	/**
	 * 根据父id求对象列表数量
	 * 
	 * */
	public Integer selectCountByPid(Integer pid);
	
	/**
	 * 根据分类id求对象列表
	 * 
	 * */
	public List<CateAttri> selectCateAttriListByCateId(Integer cid);

	/**
	 * 增加持久对象
	 * 
	 * */
	public void addCateAttri(List<CateAttri> list);
	
	
	/**
	 * 删除对象
	 * 
	 * */
	public void deleteCateAttri(CateAttri cateAttri);
	
	/**
	 * 
	 * 查询分类的所有父分类
	 * 
	 * */
	public List<Category> getAllParentCategoryById(Integer id) throws Exception;
	
	/**
	 * 
	 * 查询分类的所有子分类
	 * 
	 * */
	public List<Category> getAllSonCategoryById(Integer id) throws Exception;
	/**
	 * 
	 * 批量更新分类状态为不可用
	 * 
	 * */
	public void batchUpdateCateStatusNoByIds(List<Integer> ids) throws Exception;


}
