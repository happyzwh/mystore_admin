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

import com.mystore.business.dto.Region;
import com.mystore.business.service.RegionService;

@Controller("regionAction")
@Scope("prototype")
public class RegionAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RegionService regionService;
	
	private Integer id;
	
	private Integer pid;
	
	private String name;
	
	private String type;
	
	private String code;
	
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
					returnResult.append("<item text=\"区域管理\" id=\"-1\" open=\"1\">");
					
					   List<Region> cateList = regionService.selectRegionListByPid(-1);
					   if(cateList != null && cateList.size() > 0){
						   for(Region region:cateList){
							   int count = regionService.selectCountByPid(region.getId());
							   if(count == 0){
								   returnResult.append("<item text=\""+region.getName()+"\" id=\""+region.getId()+"\" child=\"0\"></item>");
							   }else{
								   returnResult.append("<item text=\""+region.getName()+"\" id=\""+region.getId()+"\" child=\"1\"></item>"); 
							   } 
						   }
					   }
					
					returnResult.append("</item>");
				returnResult.append("</tree>");
			}else{
				returnResult.append("<tree id=\""+id+"\">");
				   List<Region> cateList = regionService.selectRegionListByPid(id);
				   if(cateList != null && cateList.size() > 0){
					   for(Region region:cateList){
						   int count = regionService.selectCountByPid(region.getId());
						   if(count == 0){
							   returnResult.append("<item text=\""+region.getName()+"\" id=\""+region.getId()+"\" child=\"0\"></item>");
						   }else{
							   returnResult.append("<item text=\""+region.getName()+"\" id=\""+region.getId()+"\" child=\"1\"></item>"); 
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
     * @return -3：同级有重名;-2:参数错误;-1:服务异常;
     * 
     * */
	public void addNote() throws IOException{
		Integer returnCode = 0;
		try{
			
			if(StringUtils.isBlank(name)){
				returnCode = -2;
				return;
			}
			
			Region cate = regionService.getRegionByPidAndName(pid==null?-1:pid, name);
			if(cate != null){
				returnCode = -3;
				return;
			}
			
			cate = new Region();
			cate.setName(name);
			cate.setSort(sort == null?1:sort);
			cate.setPid(pid==null?-1:pid);
			cate.setType(type);
			cate.setCode(code);
			
			regionService.addRegion(cate);
		
			returnCode = cate.getId();
		
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
	public void getRegionById() throws IOException, JSONException{
		JSONObject data = new JSONObject();
		Integer returnCode = 0;
		try{
			
			if(id == null){
				returnCode = -2;
				return;
			}
			
	        Region cate = regionService.getRegionById(id);
	        data.put("name", cate.getName());
	        data.put("type", cate.getType());
	        data.put("code", cate.getCode());
	        data.put("sort", cate.getSort());
		
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
     * @return -3:同级有重名;-2:参数错误;-1:服务异常;
     * 
     * */
	public void updateNote() throws IOException{
		Integer returnCode = 0;
		try{
			
			if(id == null || StringUtils.isBlank(name)){
				returnCode = -2;
				return;
			}
			
			Region cate = regionService.getRegionByPidAndName(pid==null?-1:pid, name);
			if(cate != null && cate.getId().intValue() != id.intValue()){
				returnCode = -3;
				return;
			}
			
			cate = new Region();
			cate.setName(name);
			cate.setSort(sort == null?1:sort);
			cate.setPid(pid==null?-1:pid);
			cate.setType(type);
			cate.setCode(code);
			cate.setId(id);
			
			regionService.upateRegionById(cate);
		
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
			
			regionService.deleteRegionById(id);
		
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().print(returnCode);
		}
		
	}
	
	public void getAllNote() throws Exception{
		
        StringBuilder returnResult = new StringBuilder("");
        returnResult.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		try{
				returnResult.append("<tree id=\"0\">");
					returnResult.append("<item text=\"区域管理\" id=\"-1\" open=\"1\">");
					
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
		   List<Region> regList = regionService.selectRegionListByPid(pid);
		   if(regList != null && regList.size() > 0){
			   for(Region reg:regList){
					  returnResult.append("<item text=\""+reg.getName()+"\" id=\""+reg.getId()+"\" open=\"1\">");
				      getNextNote(returnResult,reg.getId());   
				   returnResult.append("</item>");
			   }
		   }
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
