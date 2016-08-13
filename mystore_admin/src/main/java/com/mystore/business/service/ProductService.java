package com.mystore.business.service;

import java.util.List;
import java.util.Map;

import com.mystore.business.common.Pager;
import com.mystore.business.dto.ProAttr;
import com.mystore.business.dto.ProImg;
import com.mystore.business.dto.ProInfo;
import com.mystore.business.dto.ProPrice;
import com.mystore.business.dto.Product;

public interface ProductService {
	
	/**
	 * 增加商品
	 * 
	 * */
	public void addProduct(Product pro) throws Exception;
	/**
	 * 修改商品
	 * 
	 * */
	public void updateProById(Product pro) throws Exception;
	/**
	 * 根据主键查询商品
	 * 
	 * */
	public Product getProById(Integer id) throws Exception;
	
	/**
	 * 根据查询商品
	 * @param name:名称;sn:编号
	 * 
	 * */
	public Product getProduct(String name,String sn) throws Exception;
    
	/**
	 * 
	 * 查询用户列表
	 * 
	 * */
	public Pager<Product> selectProList(Product pro,Integer pageNo,Integer pageSize) throws Exception;
	/**
	 * 查询用户数量
	 * 
	 * */
	public int selectProCount(Product pro) throws Exception;
	/**
	 * 
	 * 删除用户更改用户状态
	 * 
	 * */
	public void deleteProById(Integer id) throws Exception;
	
	/**
	 * 增加商品详情
	 * 
	 * */
	public void addProInfo(ProInfo proInfo) throws Exception;
	/**
	 * 修改商品详情
	 * 
	 * */
	public void updateProInfoByProId(ProInfo proInfo) throws Exception;
	/**
	 * 根据主键查询商品详情
	 * 
	 * */
	public ProInfo getProInfoByProId(Integer proId) throws Exception;
	/**
	 * 
	 * 删除用户更改用户状态
	 * 
	 * */
	public void deleteProInfoByProId(Integer proId) throws Exception;
	
	/**
	 * 增加商品价格
	 * 
	 * */
	public void addProPrice(ProPrice proPrice) throws Exception;
	/**
	 * 修改商品价格
	 * 
	 * */
	public void updateProPriceByProId(ProPrice proPrice) throws Exception;
	/**
	 * 根据主键查询商品价格
	 * 
	 * */
	public ProPrice getProPriceByProId(Integer proId) throws Exception;
	/**
	 * 
	 * 删除用户更改用户状态
	 * 
	 * */
	public void deleteProPriceByProId(Integer proId) throws Exception;
	
	/**
	 * 增加商品图片
	 * 
	 * */
	public void addProImg(ProImg proImg) throws Exception;
	/**
	 * 修改商品图片
	 * 
	 * */
	public void updateProImgById(ProImg proImg) throws Exception;
	/**
	 * 根据主键查询商品图片
	 * 
	 * */
	public List<ProImg> getProImgListByProId(Integer proId) throws Exception;
	/**
	 * 
	 * 删除用户更改图片
	 * 
	 * */
	public void deleteProImgById(Integer id) throws Exception;
	
	/**
	 * 修改商品图片
	 * 
	 * */
	public void updateProImgListById(List<ProImg> list) throws Exception;

	/**
	 * 增加商品属性
	 * 
	 * */
	public void addProAttr(ProAttr proAttr) throws Exception;
	
	/**
	 * 增加商品属性
	 * 
	 * */
	public void addProAttrList(List<ProAttr> list) throws Exception;
	/**
	 * 修改商品属性
	 * 
	 * */
	public void updateProAttrById(ProAttr proAttr) throws Exception;
	/**
	 * 根据商品id得商品属性
	 * 
	 * */
	public List<ProAttr> getProAttrByProId(Integer proId) throws Exception;
	/**
	 * 
	 * 删除商品属性
	 * 
	 * */
	public void deleteProAttrByProId(Integer proId) throws Exception;

	/**
	 * 
	 * 批量更改上下架
	 * 
	 * */
	public void updateBatchProIsOnSale(List<Integer> ids) throws Exception;
	
	/**
	 * 根据商品名称模糊查询商品属性
	 * 
	 * 
	 * */
	public List<Map<String,Object>> selectProListByLikeNameOrSn(String name);

}
