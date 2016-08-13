package com.mystore.business.dao;

import java.util.List;

import com.mystore.business.dto.UserPower;

public interface UserPowerMapper { 
	
	/**
	 * 增加用户权限
	 * 
	 * */
	public void addUserPower(UserPower userPower);
	
	/**
	 * 删除用户权限
	 * 
	 * */
	public void deleteUserPower(UserPower userPower);
	
	/**
	 * 查询用户权限列表
	 * 
	 * */
	public List<UserPower> selectUserPower(UserPower userPower);
}

