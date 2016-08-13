package com.mystore.business.dao;

import java.util.List;
import java.util.Map;

import com.mystore.business.dto.User;


public interface UserMapper {  
	
	/**
	 * 增加用户
	 * 
	 * */
	public void addUser(User user);
	/**
	 * 修改用户
	 * 
	 * */
	public void updateUserById(User user);
	/**
	 * 根据主键查询用户
	 * 
	 * */
	public User getUserById(Integer id);
	
	/**
	 * 
	 * 根据用户查询用户
	 * 
	 * */
	public User getUserByAccount(String account);
    
	/**
	 * 根据帐号密码查询用户
	 * 
	 * */
	public User selectByAccount(Map<String,String> paraMap);
	
	/**
	 * 
	 * 查询用户列表
	 * 
	 * */
	public List<User> selectUserList(User user);
	/**
	 * 查询用户数量
	 * 
	 * */
	public int selectUserCount(User user);
	/**
	 * 
	 * 删除用户更改用户状态
	 * 
	 * */
	public void deleteUserById(Integer id) throws Exception;

}

