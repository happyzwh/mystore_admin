package com.mystore.business.dao;

import java.util.List;

import com.mystore.business.dto.Role;



public interface RoleMapper { 
	
	/**
	 * 根据id查询角色
	 * 
	 * */
	public Role selectRoleById(Integer id);
	/**
	 * 根据名称查询角色
	 * 
	 * */
	public Role selectRoleByName(String name);
	/**
	 * 增加角色
	 * 
	 * */
	public void addRole(Role role);
	/**
	 * 修改角色
	 * 
	 * */
	public void updateRoleById(Role role);
	/**
	 * 删除角色
	 * 
	 * */
	public void deleteRoleById(Integer id);
	/**
	 * 查询角色列表
	 * 
	 * */
	public List<Role> selectRoleList(Role role);
	/**
	 * 查询角色数量
	 * 
	 * */
	public int selectRoleCount(Role role);
	


}

