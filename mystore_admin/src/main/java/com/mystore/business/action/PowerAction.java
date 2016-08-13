package com.mystore.business.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.dto.Power;
import com.mystore.business.service.PowerService;

@Controller("powerAction")
@Scope("prototype")
public class PowerAction  extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PowerService powerService;
	
	private Integer id;
	
	private Integer pid;
	
	private String name;
	
	private String bh;
	
	private String type;
	
	private String url;
	
	private Integer sort;

	public String index(){
		
		return "index";
	}
	
	public void get_note() throws IOException{
        StringBuilder returnResult = new StringBuilder("");
        returnResult.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		try{
			if(id == null){
				returnResult.append("<tree id=\"0\">");
					returnResult.append("<item text=\"权限管理\" id=\"-1\" open=\"1\">");
					
					   List<Power> powerList = powerService.selectPowerByPid(-1);
					   if(powerList != null && powerList.size() > 0){
						   for(Power power:powerList){
							   int count = powerService.selectCountByPid(power.getId());
							   if(count == 0){
								   returnResult.append("<item text=\""+power.getName()+"\" id=\""+power.getId()+"\" child=\"0\"></item>");
							   }else{
								   returnResult.append("<item text=\""+power.getName()+"\" id=\""+power.getId()+"\" child=\"1\"></item>"); 
							   } 
						   }
					   }
					
					returnResult.append("</item>");
				returnResult.append("</tree>");
			}else{
				returnResult.append("<tree id=\""+id+"\">");
				List<Power> powerList = powerService.selectPowerByPid(id);
				   if(powerList != null && powerList.size() > 0){
					   for(Power power:powerList){
						   int count = powerService.selectCountByPid(power.getId());
						   if(count == 0){
							   returnResult.append("<item text=\""+power.getName()+"\" id=\""+power.getId()+"\" child=\"0\"></item>");
						   }else{
							   returnResult.append("<item text=\""+power.getName()+"\" id=\""+power.getId()+"\" child=\"1\"></item>"); 
						   } 
					   }
				   }
				   returnResult.append("</tree>");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			  HttpServletResponse response=ServletActionContext.getResponse();
			  response.setContentType("text/xml;charset=UTF-8");
			  response.getWriter().print(returnResult);
		}
		
	}
    /**
     * @param 
     * @return -4:编号重复;-3：同级有重名;-2:参数错误;-1:服务异常;
     * 
     * */
	public void addNote() throws IOException{
		Integer returnCode = 0;
		try{
			
			if(StringUtils.isBlank(name) || StringUtils.isBlank(type)){
				returnCode = -2;
				return;
			}
			
			Power power = powerService.selectPowerByPidAndName(pid==null?-1:pid, name);
			if(power != null){
				returnCode = -3;
				return;
			}
			
			if(StringUtils.isNotBlank(bh) && powerService.selectPowerByBh(bh) != null){
				returnCode = -4;
				return;
			}
			
			power = new Power();
			power.setBh(bh);
			power.setName(name);
			power.setType(type);
			power.setUrl(url);
			power.setSort(sort == null?1:sort);
			power.setPid(pid==null?-1:pid);
			
			powerService.addPower(power);
		
			returnCode = power.getId();
		
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
     * @return -2:参数错误;-1:服务异常;0:正确
     * 
     * */
	public void getPowerById() throws IOException, JSONException{
		JSONObject data = new JSONObject();
		Integer returnCode = 0;
		try{
			
			if(id == null){
				returnCode = -2;
				return;
			}
			
	        Power power = powerService.selectPowerById(id);
	        data.put("bh", power.getBh());
	        data.put("name", power.getName());
	        data.put("type", power.getType());
	        data.put("url", power.getUrl());
	        data.put("sort", power.getSort());
		
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 data.put("returnCode", returnCode);
			 response.getWriter().print(data.toString());
		}
		
		
	}
	
	 /**
     * @param 
     * @return -4:编号重复;-3:同级有重名;-2:参数错误;-1:服务异常;
     * 
     * */
	public void updateNote() throws IOException{
		Integer returnCode = 0;
		try{
			
			if(id == null || StringUtils.isBlank(name) || StringUtils.isBlank(type)){
				returnCode = -2;
				return;
			}
			
			Power power = powerService.selectPowerByPidAndName(pid==null?-1:pid, name);
			if(power != null && power.getId().intValue() != id.intValue()){
				returnCode = -3;
				return;
			}
			
			if(StringUtils.isNotBlank(bh) && powerService.selectPowerByBh(bh) != null){
				returnCode = -4;
				return;
			}
			
			power = powerService.selectPowerById(id);
			power.setBh(bh);
			power.setName(name);
			power.setType(type);
			power.setUrl(url);
			power.setSort(sort == null?1:sort);
			
			powerService.updatePowerById(power);
		
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
     * @return -2:参数错误;-1:服务异常;
     * 
     * */
	public void deleteNote() throws IOException{
		Integer returnCode = 0;
		try{
			
			if(id == null){
				returnCode = -2;
				return;
			}
			
			powerService.deletePowerById(id);
		
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().print(returnCode);
		}
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PowerService getPowerService() {
		return powerService;
	}

	public void setPowerService(PowerService powerService) {
		this.powerService = powerService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}
	

}
