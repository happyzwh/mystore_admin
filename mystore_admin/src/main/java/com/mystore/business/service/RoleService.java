package com.mystore.business.service;

import java.util.List;

import com.mystore.business.common.Pager;
import com.mystore.business.dto.Role;
import com.mystore.business.dto.RolePower;

public interface RoleService {
	
	/**
	 * 根据id查询角色
	 * 
	 * */
	public Role selectRoleById(Integer id) throws Exception;
	/**
	 * 根据名称查询角色
	 * 
	 * */
	public Role selectRoleByName(String name) throws Exception;
	/**
	 * 增加角色
	 * 
	 * */
	public void addRole(Role role) throws Exception;
	/**
	 * 修改角色
	 * 
	 * */
	public void updateRoleById(Role role) throws Exception;
	/**
	 * 删除角色
	 * 
	 * */
	public void deleteRoleById(Integer id) throws Exception;
	/**
	 * 查询角色列表
	 * 
	 * */
	public Pager<Role> getPageRole(Role role,Integer pageNum,Integer pageSize) throws Exception;
	

	/**
	 * 
	 * 查询角色权限列表
	 * 
	 * */
	public List<RolePower> getRolePowerList(Integer id) throws Exception;
	
	/**
	 * 
	 * 增加角色权限
	 * 
	 * */
	public void addRolePower(Integer id,String powerIds) throws Exception;

}
