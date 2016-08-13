package com.mystore.business.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.PageInfo;
import com.mystore.business.common.Pager;
import com.mystore.business.dto.Power;
import com.mystore.business.dto.Role;
import com.mystore.business.dto.RolePower;
import com.mystore.business.service.PowerService;
import com.mystore.business.service.RoleService;

@Controller("roleAction")
@Scope("prototype")
public class RoleAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PowerService powerService;
	
	private String name;
	
	private Integer id;
	
	private String descr;
	
	private Integer sort;
	
	private List<Role> list;
	
	private Role role;
	
	private Map<String,Object> rolePowerMap;
	
	private String powerIds;
	
	public String list() throws Exception{
		
		Role role = new Role();
		role.setName(name);
		
		Pager<Role> pager=(Pager<Role>)roleService.getPageRole(role, pageNo==null?1:pageNo, pageSize==null?10:pageSize);
		if(pager != null && pager.getResultList() != null && pager.getResultList().size() > 0){
			list = pager.getResultList();
			pageInfo = PageInfo.setPage(pager.getPageNo(), pager.getPageSize(), pager.getPageCount(), pager.getRowCount());
		}
		
		return "list";
	}
	
	public String add(){
		
		return "add";
		
	}
	
	public String edit() throws Exception{
		
		if(id != null){
			role = roleService.selectRoleById(id);
		}
		
		return "edit";
	}
	
	public void delete() throws IOException{
		
		int returnCode =0;
		try{
			if(id != null){
				  roleService.deleteRoleById(id);
			}
		}catch(Exception e){
			  returnCode = -1;
		}finally{
			  HttpServletResponse response=ServletActionContext.getResponse();
			  response.setContentType("text/html;charset=UTF-8");
			  response.getWriter().print(returnCode);
		}
	
	}
	
	public void save() throws IOException{
		
		  int returnCode =0;
		  try{
			  
			  if(StringUtils.isBlank(name)){
				  returnCode = -2;
				  return;
			  }
			  
			  sort = sort == null?1:sort;
			  
			  role = roleService.selectRoleByName(name);
			  if(role != null){
				  if(id == null){
					  returnCode = -3;
					  return;
				  }else if(id.intValue() != role.getId().intValue()){
					  returnCode = -3;
				      return;
				  }
			  }
			  
			  Role role = new Role();
			  role.setName(name);
			  role.setDescr(descr);
			  role.setSort(sort);
			  if(id == null){
				  roleService.addRole(role);
			  }else{
				  role.setId(id);
				  roleService.updateRoleById(role);
			  }
			  
		  }catch(Exception e){
			  returnCode = -1;
		  }finally{
			  HttpServletResponse response=ServletActionContext.getResponse();
			  response.setContentType("text/html;charset=UTF-8");
			  response.getWriter().print(returnCode);
		  }
		
	}

	public String rolePower(){
		
		return "rolePower";
	}
	
	public void getAllNote() throws Exception{
		
		List<RolePower> rolePowerList = roleService.getRolePowerList(id);
		if(rolePowerList != null && rolePowerList.size() > 0){
			rolePowerMap = new HashMap<String,Object>();
			for(RolePower rolePower:rolePowerList){
				rolePowerMap.put(rolePower.getPowerId().toString(), rolePower.getPowerId().toString());
			}
		}
		
        StringBuilder returnResult = new StringBuilder("");
        returnResult.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		try{
				returnResult.append("<tree id=\"0\">");
					returnResult.append("<item text=\"权限管理\" id=\"-1\" open=\"1\">");
					
					getNextNote(returnResult,-1); 
					
					returnResult.append("</item>");
				returnResult.append("</tree>");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			  HttpServletResponse response=ServletActionContext.getResponse();
			  response.setContentType("text/xml;charset=UTF-8");
			  response.getWriter().print(returnResult);
		}
		
	}
	
	private void getNextNote(StringBuilder returnResult,Integer pid) throws Exception{
		   List<Power> powerList = powerService.selectPowerByPid(pid);
		   if(powerList != null && powerList.size() > 0){
			   for(Power power:powerList){
				   if(rolePowerMap == null || rolePowerMap.isEmpty() || !rolePowerMap.containsKey(String.valueOf(power.getId().intValue()))){
					   returnResult.append("<item text=\""+power.getName()+"\" id=\""+power.getId()+"\" open=\"1\">");
				   }else{
					   returnResult.append("<item text=\""+power.getName()+"\" id=\""+power.getId()+"\" open=\"1\" checked=\"1\">");
				   }
				      getNextNote(returnResult,power.getId());   
				   returnResult.append("</item>");
			   }
		   }
	}
	
	public void saveRolePower() throws IOException{
		Integer returnCode = 0;
		try{
			
			 if(id == null || StringUtils.isBlank(powerIds)){
				  returnCode = -2;
				  return;
			  }
		      roleService.addRolePower(id, powerIds);
			
		}catch(Exception e){
			returnCode = -1;
		}finally{
			  HttpServletResponse response=ServletActionContext.getResponse();
			  response.setContentType("text/html;charset=UTF-8");
			  response.getWriter().print(returnCode);
		}
		
	}
	
	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<Role> getList() {
		return list;
	}

	public void setList(List<Role> list) {
		this.list = list;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Map<String, Object> getRolePowerMap() {
		return rolePowerMap;
	}

	public void setRolePowerMap(Map<String, Object> rolePowerMap) {
		this.rolePowerMap = rolePowerMap;
	}

	public String getPowerIds() {
		return powerIds;
	}

	public void setPowerIds(String powerIds) {
		this.powerIds = powerIds;
	}
    
}
