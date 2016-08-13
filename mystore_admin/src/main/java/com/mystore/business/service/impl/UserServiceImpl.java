package com.mystore.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.common.Pager;
import com.mystore.business.dao.UserMapper;
import com.mystore.business.dao.UserPowerMapper;
import com.mystore.business.dao.UserRoleMapper;
import com.mystore.business.dto.User;
import com.mystore.business.dto.UserPower;
import com.mystore.business.dto.UserRole;
import com.mystore.business.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private UserPowerMapper userPowerMapper;
	
	@Override
	public User selectByAccount(String account, String password) throws Exception{
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(account) || StringUtils.isBlank(password))return null;
		Map<String,String> paraMap = new HashMap<String,String>();
		paraMap.put("account", account);
		paraMap.put("password", password);
		return userMapper.selectByAccount(paraMap);
	}

	@Override
	public Pager<User> getPageUser(User user, Integer pageNum, Integer pageSize)
			throws Exception {
		// TODO Auto-generated method stub
		if(pageNum != null && pageNum == 0)pageNum =1;
		if(pageSize != null && pageSize == 0)pageSize = 10;
		if(pageNum != null && pageSize != null){
			int pageIndex = (pageNum - 1)*pageSize;
			user.setPageIndex(pageIndex);
			user.setPageSize(pageSize);
		}
		Pager<User> pager = new Pager<User>();
		if(pageNum != null && pageSize != null){
			pager.setPageNo(pageNum);
			pager.setPageSize(pageSize);
		}
		int count = userMapper.selectUserCount(user);
		if(count == 0)return null;
		pager.setRowCount(count);
		if(pageNum != null && pageSize != null){
			pager.setPageCount(count%pageSize==0?(count/pageSize):(count/pageSize+1));
		}
		List<User> list = userMapper.selectUserList(user);
		pager.setResultList(list);
		return pager;
	}

	@Override
	public User getUserById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.getUserById(id);
	}

	@Override
	public void deleteUserById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		if(id != null){
			UserRole userRole = new UserRole();
			userRole.setUserId(id);
			userRoleMapper.deleteUserRole(userRole);
			
			UserPower userPower = new UserPower();
			userPower.setUserId(id);
			userPowerMapper.deleteUserPower(userPower);
			
			userMapper.deleteUserById(id);
		}
	}

	@Override
	public User getUserByAccount(String account) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.getUserByAccount(account);
	}

	@Override
	public void addUser(User user) throws Exception{
		// TODO Auto-generated method stub
		if(user != null){
			userMapper.addUser(user);
		}
	}

	@Override
	public void updateUserById(User user) throws Exception{
		// TODO Auto-generated method stub
		if(user != null && user.getId() != null){
			userMapper.updateUserById(user);
		}
	}

	@Override
	public List<UserPower> getUerPowerList(Integer id) throws Exception {
		// TODO Auto-generated method stub
		UserPower userPower = new UserPower();
		userPower.setUserId(id);
		return userPowerMapper.selectUserPower(userPower);
	}

	@Override
	public List<UserRole> getUerRoleList(Integer id) throws Exception {
		// TODO Auto-generated method stub
		UserRole userRole = new UserRole();
		userRole.setUserId(id);
		return userRoleMapper.selectUserRole(userRole);
	}

	@Override
	public void addUserRole(Integer id, String roleIds) throws Exception {
		// TODO Auto-generated method stub
		
		  UserRole userRole = new UserRole();
		  userRole.setUserId(id);
		  userRoleMapper.deleteUserRole(userRole);
		  
		  String[] roleId = roleIds.split(",");
		  for(String s:roleId){
			  userRole = new UserRole();
			  userRole.setUserId(id);
			  userRole.setRoleId(Integer.valueOf(s));
			  userRoleMapper.addUserRole(userRole);
		  }
	}

	@Override
	public void addUserPower(Integer id, String powerIds) throws Exception {
		// TODO Auto-generated method stub
		
		UserPower userPower = new UserPower();
		userPower.setUserId(id);
		userPowerMapper.deleteUserPower(userPower);
		
		String[] powerId = powerIds.split(",");
		for(String s:powerId){
			userPower = new UserPower();
			userPower.setUserId(id);
			userPower.setPowerId(Integer.valueOf(s));
			userPowerMapper.addUserPower(userPower);
		}
		
	}

}
