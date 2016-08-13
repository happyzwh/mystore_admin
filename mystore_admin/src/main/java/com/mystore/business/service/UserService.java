package com.mystore.business.service;

import java.util.List;

import com.mystore.business.common.Pager;
import com.mystore.business.dto.User;
import com.mystore.business.dto.UserPower;
import com.mystore.business.dto.UserRole;

public interface UserService {
	/**
	 * 增加用户
	 * 
	 * */
	public void addUser(User user) throws Exception;
	/**
	 * 修改用户
	 * 
	 * */
	public void updateUserById(User user) throws Exception;
	/**
	 * 根据主键查询用户
	 * 
	 * */
	public User getUserById(Integer id) throws Exception;
	/**
	 * 
	 * 根据用户查询用户
	 * 
	 * */
	public User getUserByAccount(String account) throws Exception;
	/**
	 * 根据帐号密码查询用户
	 * 
	 * */
	public User selectByAccount(String account,String password) throws Exception;
	/**
	 * 
	 * 查询用户列表
	 * 
	 * */
	public Pager<User> getPageUser(User user,Integer pageNum,Integer pageSize)throws Exception;
	/**
	 * 
	 * 删除用户更改用户状态
	 * 
	 * */
	public void deleteUserById(Integer id) throws Exception;
	
	/**
	 * 
	 * 查询用户角色列表
	 * 
	 * */
	public List<UserRole> getUerRoleList(Integer id) throws Exception;
	
	/**
	 * 
	 * 查询用户权限列表
	 * 
	 * */
	public List<UserPower> getUerPowerList(Integer id) throws Exception;
	

	/**
	 * 
	 * 增加用户角色
	 * 
	 * */
	public void addUserRole(Integer id,String roleIds) throws Exception;
	
	/**
	 * 
	 * 增加用户权限
	 * 
	 * */
	public void addUserPower(Integer id,String powerIds) throws Exception;
}
