package com.mystore.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.common.Pager;
import com.mystore.business.dao.RoleMapper;
import com.mystore.business.dao.RolePowerMapper;
import com.mystore.business.dao.UserRoleMapper;
import com.mystore.business.dto.Role;
import com.mystore.business.dto.RolePower;
import com.mystore.business.dto.UserRole;
import com.mystore.business.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private RolePowerMapper rolePowerMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public void addRole(Role role) throws Exception{
		// TODO Auto-generated method stub
		if(role != null){
			roleMapper.addRole(role);
		}
	}

	@Override
	public void deleteRoleById(Integer id) throws Exception{
		// TODO Auto-generated method stub
		if(id != null){
			 
			 RolePower rolePower = new RolePower();
			 rolePower.setRoleId(id);
			 rolePowerMapper.deleteRolePower(rolePower);
			 
			 UserRole userRole = new UserRole();
			 userRole.setRoleId(id);
			 userRoleMapper.deleteUserRole(userRole);
			
			 roleMapper.deleteRoleById(id);
		}
	}

	@Override
	public Role selectRoleById(Integer id) throws Exception{
		// TODO Auto-generated method stub
		return roleMapper.selectRoleById(id);
	}

	@Override
	public Role selectRoleByName(String name) throws Exception{
		// TODO Auto-generated method stub
		return roleMapper.selectRoleByName(name);
	}

	@Override
	public Pager<Role> getPageRole(Role role, Integer pageNum,
			Integer pageSize) throws Exception{
		// TODO Auto-generated method stub
		if(pageNum != null && pageNum == 0)pageNum =1;
		if(pageSize != null && pageSize == 0)pageSize = 10;
		if(pageNum != null && pageSize != null){
			int pageIndex = (pageNum - 1)*pageSize;
			role.setPageIndex(pageIndex);
			role.setPageSize(pageSize);
		}
		Pager<Role> pager = new Pager<Role>();
		if(pageNum != null && pageSize != null){
			pager.setPageNo(pageNum);
			pager.setPageSize(pageSize);
		}
		int count = roleMapper.selectRoleCount(role);
		if(count == 0)return null;
		pager.setRowCount(count);
		if(pageNum != null && pageSize != null){
			pager.setPageCount(count%pageSize==0?(count/pageSize):(count/pageSize+1));
		}
		List<Role> list = roleMapper.selectRoleList(role);
		pager.setResultList(list);
		return pager;
	}

	@Override
	public void updateRoleById(Role role) throws Exception{
		// TODO Auto-generated method stub
		if(role != null && role.getId() != null){
			roleMapper.updateRoleById(role);
		}
	}

	@Override
	public List<RolePower> getRolePowerList(Integer id) throws Exception {
		// TODO Auto-generated method stub
		RolePower rolePower = new RolePower();
		rolePower.setRoleId(id);
		return rolePowerMapper.selectRolePower(rolePower);
	}

	@Override
	public void addRolePower(Integer id, String powerIds) throws Exception {
		// TODO Auto-generated method stub
		RolePower rolePower = new RolePower();
		rolePower.setRoleId(id);
		rolePowerMapper.deleteRolePower(rolePower);
		
		String[] powerId = powerIds.split(",");
		for(String s:powerId){
			rolePower = new RolePower();
			rolePower.setRoleId(id);
			rolePower.setPowerId(Integer.valueOf(s));
			rolePowerMapper.addRolePower(rolePower);
		}
	}

}
