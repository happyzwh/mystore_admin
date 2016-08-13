package com.mystore.business.service;

import java.util.List;

import com.mystore.business.dto.Power;

public interface PowerService {
	
	/**
	 * 根据id查询对象
	 * 
	 * */
	public Power selectPowerById(Integer id) throws Exception;
	
	/**
	 * 根据bh查询对象
	 * 
	 * */
	public Power selectPowerByBh(String bh) throws Exception;
	
	
	/**
	 * 根据id查询对象
	 * 
	 * */
	public Integer addPower(Power power) throws Exception;
	
	/**
	 * 根据id修改对象
	 * 
	 * */
	public void updatePowerById(Power power) throws Exception;
	
	/**
	 * 根据id删除对象
	 * 
	 * */
	public void deletePowerById(Integer id) throws Exception;
	
	/**
	 * 根据pid查询对象列表
	 * 
	 * */
	public List<Power> selectPowerByPid(Integer pid) throws Exception;
	
	/**
	 * 根据pid查询对象数量
	 * 
	 * */
	public Integer selectCountByPid(Integer pid) throws Exception;
	
	/**
	 * 根据父id和名称
	 * 
	 * */
	public Power selectPowerByPidAndName(Integer pid,String name);
	
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
