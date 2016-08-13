package com.mystore.business.dao;

import java.util.List;

import com.mystore.business.dto.RolePower;

public interface RolePowerMapper { 
	
	/**
	 * 增加角色权限
	 * 
	 * */
	public void addRolePower(RolePower rolePower);
	
	/**
	 * 删除角色权限
	 * 
	 * */
	public void deleteRolePower(RolePower rolePower);
	
	/**
	 * 查询角色权限列表
	 * 
	 * */
	public List<RolePower> selectRolePower(RolePower rolePower);
}

