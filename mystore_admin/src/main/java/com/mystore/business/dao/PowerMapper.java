package com.mystore.business.dao;

import java.util.List;
import java.util.Map;

import com.mystore.business.dto.Power;
public interface PowerMapper { 
	
	/**
	 * 根据id查询对象
	 * 
	 * */
	public Power selectPowerById(Integer id);
	
	/**
	 * 根据bh查询对象
	 * 
	 * */
	public Power selectPowerByBh(String bh);
	
	
	/**
	 * 根据id查询对象
	 * 
	 * */
	public Integer addPower(Power power);
	
	/**
	 * 根据id修改对象
	 * 
	 * */
	public void updatePowerById(Power power);

	/**
	 * 根据id删除对象
	 * 
	 * */
	public void deletePowerById(Integer id) throws Exception;
	
	/**
	 * 根据pid查询对象列表
	 * 
	 * */
	public List<Power> selectPowerByPid(Integer pid);
	
	/**
	 * 根据pid查询对象数量
	 * 
	 * */
	public Integer selectCountByPid(Integer pid);
	
	/**
	 * 根据父id和名称
	 * 
	 * */
	public Power selectPowerByPidAndName(Map<String,Object> paraMap);
	/**
	 * 查询用户权限列表
	 * 
	 * */
	public List<Power> selectPowerByUid(Integer uid);
	
	/**
	 * 查询所有权限列表
	 * 
	 * */
	public List<Power> selectAllPower();
	
}

