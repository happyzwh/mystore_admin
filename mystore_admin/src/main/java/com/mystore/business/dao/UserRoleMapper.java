package com.mystore.business.dao;

import java.util.List;

import com.mystore.business.dto.UserRole;

public interface UserRoleMapper { 
	
	/**
	 * 增加用户角色
	 * 
	 * */
	public void addUserRole(UserRole userRole);
	
	/**
	 * 删除用户角色
	 * 
	 * */
	public void deleteUserRole(UserRole userRole);
	
	/**
	 * 查询用户角色列表
	 * 
	 * */
	public List<UserRole> selectUserRole(UserRole userRole);
}

