package com.mystore.business.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.Constans;
import com.mystore.business.common.PageInfo;
import com.mystore.business.common.Pager;
import com.mystore.business.common.UserSession;
import com.mystore.business.core.PublicKeyMap;
import com.mystore.business.core.RSAUtils;
import com.mystore.business.dto.Power;
import com.mystore.business.dto.Role;
import com.mystore.business.dto.User;
import com.mystore.business.dto.UserPower;
import com.mystore.business.dto.UserRole;
import com.mystore.business.service.PowerService;
import com.mystore.business.service.RoleService;
import com.mystore.business.service.UserService;
import com.mystore.business.util.MD5;

@Controller("userAction")
@Scope("prototype")
public class UserAction  extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PowerService powerService;
	
	private String account;
	
	private String userName;
	
	private String password;
	
	private String today;
		
	private List<User> list;
	
	private Integer id;
	
	private User user;
	
	private String email;
	
	private Integer sort;
	
	private List<Role> roleList;
	
	private Map<String,Object> userRoleMap;
	
	private Map<String,Object> userPowerMap;
	
	private String roleIds;
	
	private String powerIds;
	
	private List<Power> powerList;
	
	private Map<String, Object> model;
	
	public String toLogin(){
		
		model = new HashMap<String, Object>();
		
		PublicKeyMap publicKeyMap = RSAUtils.getPublicKeyMap();
		
		model.put("exponent", publicKeyMap.getExponent());
		model.put("modulus", publicKeyMap.getModulus());
		
		return "toLogin";
		
	}
	
	/**
	 * 登录
	 * @return  -2:参数错误;-1:服务异常;0:用户名或密码错误;1:正确
	 * @throws IOException 
	 * 
	 * */
	@SuppressWarnings("static-access")
	public void login() throws IOException{
		
		int returnCode = 0;
		try{
			if(StringUtils.isBlank(account) || StringUtils.isBlank(password)){
				returnCode = -2;
				return;
			}
			
			password = RSAUtils.decryptStringByJs(password);
			
			User user = userService.selectByAccount(account.trim(), new MD5().GetMD5Code(password.trim()));
			if(user != null){
				returnCode = 1;
				UserSession userSession = new UserSession();
				userSession.setUser(user);
				ServletActionContext.getRequest().getSession().setAttribute(Constans.KEY_SESSION, userSession);
				List<Power> userMenuPowerList = new ArrayList<Power>();
				user.setMenuPowerList(userMenuPowerList);
				List<Power> powerList = null;
				if(user.getFlag().equals("1")){
					powerList = powerService.selectAllPower();
				}else{
					powerList = powerService.selectPowerByUid(user.getId());
				}
				user.setPowerList(powerList);
				if(powerList != null && powerList.size() > 0){
					for(Power power:powerList){
						if(power.getPid().intValue() != -1)continue;
						userMenuPowerList.add(power);
					}
				}
				if(userMenuPowerList.size() > 0){
				    for(Power power:userMenuPowerList){
				    	setUserPower(power,powerList);
				    }
				}
			}
		}catch(Exception e){
			returnCode = -1;
		}finally{
			HttpServletResponse response = ServletActionContext.getResponse();
		 	response.setContentType("text/html;charset=UTF-8");
        	response.getWriter().print(returnCode);
		}

	}

	private void setUserPower(Power power,List<Power> powerList){
	     for(Power pow:powerList){
	    	 if(pow.getPid().intValue() != power.getId().intValue())continue;
	    	 if(power.getPowerList() == null)power.setPowerList(new ArrayList<Power>());
	    	 power.getPowerList().add(pow);
	    	 setUserPower(pow,powerList);
	     }
	}
	
	public String index(){
		return "index";
	}
	/**
	 * 头部信息
	 * @return
	 */
	public String top()throws IOException{
		Object o = ServletActionContext.getRequest().getSession().getAttribute(Constans.KEY_SESSION);
		UserSession userSession = (UserSession)o;
		userName = userSession.getUser().getUserName();
		SimpleDateFormat  format = new SimpleDateFormat ("yyyy年MM月dd日");
		today = format.format(new Date());
		return "top";
	}
  /**
   * 退出登录
   * @return
   */
   public String logout()throws IOException{
	   Object o = ServletActionContext.getRequest().getSession().getAttribute(Constans.KEY_SESSION);
	   if(o != null){
		   ServletActionContext.getRequest().getSession().removeAttribute(Constans.KEY_SESSION);
	   }
	   return "logout";
	}
	
   /**
    * 
    * @return
 * @throws Exception 
    */
   public String list()throws Exception{
	   User user = new  User();
	   if(StringUtils.isNoneBlank(userName)){
		   user.setUserName(userName.trim());
	   }
	   if(StringUtils.isNoneBlank(account)){
		   user.setAccount(account.trim());
	   }
	   
	   Pager<User> pager=(Pager<User>)userService.getPageUser(user, pageNo==null?1:pageNo, pageSize==null?10:pageSize);
	   if(pager != null && pager.getResultList() != null && pager.getResultList().size() > 0){
		  list = pager.getResultList();
	      pageInfo = PageInfo.setPage(pager.getPageNo(), pager.getPageSize(), pager.getPageCount(), pager.getRowCount());
	   }
	   return "list";
   }
   
   /**
    * 添加用户
    * @return
    */
   public String add(){
	   
	   return "add";
	   
   }
   
   public String left() throws Exception{
	   
	   Object o = ServletActionContext.getRequest().getSession().getAttribute(Constans.KEY_SESSION);
	   UserSession userSession = (UserSession)o;
	   powerList = userSession.getUser().getMenuPowerList();
	   
	   return "left";
	   
   }
   /**
    * 修改
    * @return
 * @throws Exception 
    */
   public String edit()throws Exception{
	   
	   if(id != null){
		   user = userService.getUserById(id);
	   }
	   
	   return "edit";
   }
   
   /**
	 * 删除用户
	 * @throws Exception
	 */
	public void delete() throws Exception{
		
		  int returnCode =0;
		  try{
			  if(id != null){
				  userService.deleteUserById(id);
			  }
		  }catch(Exception e){
			  returnCode = -1;
		  }finally{
			  HttpServletResponse response=ServletActionContext.getResponse();
			  response.setContentType("text/html;charset=UTF-8");
			  response.getWriter().print(returnCode);
		  }
	}
    /**
     * @param 
     * @return -3：帐号已存在;-2：参数错误;-1：服务异常;0:保存成功
     * 
     * */
	@SuppressWarnings("static-access")
	public void save() throws Exception{
		  
		  int returnCode =0;
		  try{
			  
			  if(id != null){
				  if(StringUtils.isBlank(account) || StringUtils.isBlank(userName)){
					  returnCode = -2;
					  return;
				  }
			  }else{
				  if(StringUtils.isBlank(account) || StringUtils.isBlank(userName) || StringUtils.isBlank(password)){
					  returnCode = -2;
					  return;
				  }
			  }
			  
			  sort = sort == null?1:sort;
			  
			  user = userService.getUserByAccount(account);
			  if(user != null){
				  if(id == null){
					  returnCode = -3;
					  return;
				  }else if(id.intValue() != user.getId().intValue()){
						  returnCode = -3;
						  return;
				  }
			  }
			  
			  User user = new User();
			  user.setAccount(account);
			  user.setUserName(userName);
			  user.setSort(sort);
			  if(StringUtils.isNotBlank(password)){
				  user.setPassword(new MD5().GetMD5Code(password));
			  }
			  user.setEmail(email);
			  if(id == null){
				  userService.addUser(user);
			  }else{
				  user.setId(id);
				  userService.updateUserById(user);
			  }
			  
		  }catch(Exception e){
			  returnCode = -1;
		  }finally{
			  HttpServletResponse response=ServletActionContext.getResponse();
			  response.setContentType("text/html;charset=UTF-8");
			  response.getWriter().print(returnCode);
		  }
	}
	
	public String userRole() throws Exception{
		
		Pager<Role> pager = roleService.getPageRole(null, null, null);
		if(pager != null && pager.getResultList() != null && pager.getResultList().size() > 0){
			roleList = pager.getResultList();
		}
		
		List<UserRole> userRoleList = userService.getUerRoleList(id);
		if(userRoleList != null && userRoleList.size() > 0){
			userRoleMap = new HashMap<String,Object>();
			for(UserRole userRole:userRoleList){
				userRoleMap.put(userRole.getRoleId().toString()+"-", userRole.getRoleId().toString());
			}
		}
		
		return "userRole";
	}
	
	public void addUserRole() throws IOException{
		  int returnCode =0;
		  try{
			  if(id == null || StringUtils.isBlank(roleIds)){
				  returnCode = -2;
				  return;
			  }
		      userService.addUserRole(id, roleIds);
		  }catch(Exception e){
			  returnCode = -1;
		  }finally{
			  HttpServletResponse response=ServletActionContext.getResponse();
			  response.setContentType("text/html;charset=UTF-8");
			  response.getWriter().print(returnCode);
		  }
	}
	
	public String userPower() throws Exception{
		
		return "userPower";
	}
	
	public void getAllNote() throws Exception{
		
		List<UserPower> userPowerList = userService.getUerPowerList(id);
		if(userPowerList != null && userPowerList.size() > 0){
			userPowerMap = new HashMap<String,Object>();
			for(UserPower userPower:userPowerList){
				userPowerMap.put(userPower.getPowerId().toString(), userPower.getPowerId().toString());
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
				   if(userPowerMap == null || userPowerMap.isEmpty() || !userPowerMap.containsKey(String.valueOf(power.getId().intValue()))){
					   returnResult.append("<item text=\""+power.getName()+"\" id=\""+power.getId()+"\" open=\"1\">");
				   }else{
					   returnResult.append("<item text=\""+power.getName()+"\" id=\""+power.getId()+"\" open=\"1\" checked=\"1\">");
				   }
				      getNextNote(returnResult,power.getId());   
				   returnResult.append("</item>");
			   }
		   }
	}
	
	
	public String userPowerView() throws Exception{
		
		return "userPowerView";
	}
	
    public void getAllNoteView() throws Exception{
		
		List<Power> PowerList = powerService.selectPowerByUid(id);
		if(PowerList != null && PowerList.size() > 0){
			userPowerMap = new HashMap<String,Object>();
			for(Power power:PowerList){
				userPowerMap.put(power.getId().toString(), power.getId().toString());
			}
		}
		
		
        StringBuilder returnResult = new StringBuilder("");
        returnResult.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		try{
				returnResult.append("<tree id=\"0\">");
					returnResult.append("<item text=\"权限管理\" id=\"-1\" open=\"1\">");
					
					getNextNoteView(returnResult,-1); 
					
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
	
	private void getNextNoteView(StringBuilder returnResult,Integer pid) throws Exception{
		   List<Power> powerList = powerService.selectPowerByPid(pid);
		   if(powerList != null && powerList.size() > 0){
			   for(Power power:powerList){
				   if(userPowerMap == null || userPowerMap.isEmpty() || !userPowerMap.containsKey(String.valueOf(power.getId().intValue()))){
					   returnResult.append("<item text=\""+power.getName()+"\" id=\""+power.getId()+"\" open=\"1\">");
				   }else{
					   returnResult.append("<item text=\""+power.getName()+"\" id=\""+power.getId()+"\" open=\"1\" checked=\"1\">");
				   }
				      getNextNote(returnResult,power.getId());   
				   returnResult.append("</item>");
			   }
		   }
	}
	
	public void saveUserPower() throws IOException{
		Integer returnCode = 0;
		try{
			
			 if(id == null || StringUtils.isBlank(powerIds)){
				  returnCode = -2;
				  return;
			  }
		      userService.addUserPower(id, powerIds);
			
		}catch(Exception e){
			returnCode = -1;
		}finally{
			  HttpServletResponse response=ServletActionContext.getResponse();
			  response.setContentType("text/html;charset=UTF-8");
			  response.getWriter().print(returnCode);
		}

	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public Map<String, Object> getUserRoleMap() {
		return userRoleMap;
	}

	public void setUserRoleMap(Map<String, Object> userRoleMap) {
		this.userRoleMap = userRoleMap;
	}

	public Map<String, Object> getUserPowerMap() {
		return userPowerMap;
	}

	public void setUserPowerMap(Map<String, Object> userPowerMap) {
		this.userPowerMap = userPowerMap;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getPowerIds() {
		return powerIds;
	}

	public void setPowerIds(String powerIds) {
		this.powerIds = powerIds;
	}

	public List<Power> getPowerList() {
		return powerList;
	}

	public void setPowerList(List<Power> powerList) {
		this.powerList = powerList;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}
    
}
