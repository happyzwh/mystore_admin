package com.mystore.business.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.PageInfo;
import com.mystore.business.common.Pager;
import com.mystore.business.dto.Info;
import com.mystore.business.dto.InfoCate;
import com.mystore.business.service.InfoService;

@Controller("infoAction")
@Scope("prototype")
public class InfoAction extends BaseAction{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private InfoService infoService;
	
	private Integer id;
	
	private String ids;
	
	private Integer pid;
	
	private Integer id_cate;
	
	private String name;
	
	private String bh;
	
	private Integer sort;
	
	private String descr;
	
	private String title;
	
	private String content;
	
	private Integer status;
	
	private List<Info> infoList;
	
	private Info info;
	
	public String index(){
		
		return "index";
	}
	
	public String list(){
		
		return "list";
	}
	
	public void get_note() throws IOException{
		
		StringBuilder returnResult = new StringBuilder("");
        returnResult.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		try{
			if(id == null){
				returnResult.append("<tree id=\"0\">");
					returnResult.append("<item text=\"信息分类\" id=\"-1\" open=\"1\">");
					
					   List<InfoCate> cateList = infoService.getInfoCateListByPid(-1);
					   if(cateList != null && cateList.size() > 0){
						   for(InfoCate infoCate:cateList){
							   int count = infoService.selectCountByPid(infoCate.getId());
							   if(count == 0){
								   returnResult.append("<item text=\""+infoCate.getName()+"\" id=\""+infoCate.getId()+"\" child=\"0\"></item>");
							   }else{
								   returnResult.append("<item text=\""+infoCate.getName()+"\" id=\""+infoCate.getId()+"\" child=\"1\"></item>"); 
							   } 
						   }
					   }
					
					returnResult.append("</item>");
				returnResult.append("</tree>");
			}else{
				returnResult.append("<tree id=\""+id+"\">");
				   List<InfoCate> cateList = infoService.getInfoCateListByPid(id);
				   if(cateList != null && cateList.size() > 0){
					   for(InfoCate infoCate:cateList){
						   int count = infoService.selectCountByPid(infoCate.getId());
						   if(count == 0){
							   returnResult.append("<item text=\""+infoCate.getName()+"\" id=\""+infoCate.getId()+"\" child=\"0\"></item>");
						   }else{
							   returnResult.append("<item text=\""+infoCate.getName()+"\" id=\""+infoCate.getId()+"\" child=\"1\"></item>"); 
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
			
			if(StringUtils.isBlank(name)){
				returnCode = -2;
				return;
			}
			
			InfoCate cate = infoService.getInfoCateByPidAndName(pid==null?-1:pid, name);
			if(cate != null){
				returnCode = -3;
				return;
			}
			
			if(StringUtils.isNotBlank(bh)){
				cate = infoService.getInfoCateByBh(bh);
				if(cate != null){
					returnCode = -4;
					return;
				}
			}
			
			cate = new InfoCate();
			cate.setName(name);
			cate.setSort(sort == null?1:sort);
			cate.setPid(pid==null?-1:pid);
			cate.setBh(bh);
			cate.setDescr(descr);
			
			infoService.addInfoCate(cate);
		
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
	public void getCateById() throws IOException, JSONException{
		JSONObject data = new JSONObject();
		Integer returnCode = 0;
		try{
			
			if(id == null){
				returnCode = -2;
				return;
			}
			
			InfoCate cate = infoService.getInfoCateById(id);
	        data.put("name", cate.getName());
	        data.put("bh", cate.getBh());
	        data.put("descr", cate.getDescr());
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
     * @return -4:编号重复;-3:同级有重名;-2:参数错误;-1:服务异常;
     * 
     * */
	public void updateNote() throws IOException{
		Integer returnCode = 0;
		try{
			
			if(id == null || StringUtils.isBlank(name)){
				returnCode = -2;
				return;
			}
			
			InfoCate cate = infoService.getInfoCateByPidAndName(pid==null?-1:pid, name);
			if(cate != null && cate.getId().intValue() != id.intValue()){
				returnCode = -3;
				return;
			}
			
			if(StringUtils.isNotBlank(bh)){
				cate = infoService.getInfoCateByBh(bh);
				if(cate != null && cate.getId().intValue() != id.intValue()){
					returnCode = -4;
					return;
				}
			}
			
			cate = new InfoCate();
			cate.setName(name);
			cate.setSort(sort == null?1:sort);
			cate.setPid(pid==null?-1:pid);
			cate.setBh(bh);
			cate.setDescr(descr);
			cate.setId(id);
			
			infoService.updateInfoCateById(cate);
		
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
			
			infoService.deletInfoCateById(id);
		
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().print(returnCode);
		}
		
	}

	
	public String infoList(){
		
		if(id_cate != null && id_cate != -1){
			Info info = new Info();
			info.setId_cate(id_cate);
			if(StringUtils.isNoneBlank(title)){
				info.setTitle(title);
			}
			if(status != null){
				info.setStatus(status);
			}
			Pager<Info> pager = infoService.getInfoList(info, pageNo, pageSize);
			if(pager != null && pager.getResultList() != null && pager.getResultList().size() > 0){
				infoList = pager.getResultList();
				pageInfo = PageInfo.setPage(pager.getPageNo(), pager.getPageSize(), pager.getPageCount(), pager.getRowCount());
			}
		}
		
		return "infoList";
	}
	
	public String edit(){
		if(id != null){
			info = infoService.getInfoById(id);
		}
		return "edit";
	}
	
	public void save() throws Exception{
		Integer returnCode = 0;
		try{
			Info info = new Info();
			info.setId_cate(id_cate);
			if(StringUtils.isNoneBlank(title)){
				info.setTitle(title);
			}
			if(StringUtils.isNoneBlank(content)){
				info.setContent(content);
			}
			if(status != null){
				info.setStatus(status);
			}
			if(id != null){
				info.setId(id);
				infoService.updateInfoById(info);
			}else{
				infoService.addInfo(info);
			}
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().print(returnCode);
		}
	}
	
	public void delete() throws Exception{
		Integer returnCode = 0;
		try{
			if(id != null){
				infoService.deleteInfoById(id);
			}
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().print(returnCode);
		}
	}
	
	public void sort() throws IOException{
		
		Integer returnCode = 0;
		try{
		
			if(StringUtils.isBlank(ids)){
				returnCode = -2;
				return;
			}
			
			String[] idArr = ids.split(",");
			
			List<Info> list = new ArrayList<Info>();
			
			int i = 1;
			for(String s:idArr){
				if(StringUtils.isBlank(s))continue;
				Info img = new Info();
	            img.setId(Integer.valueOf(s));
	            img.setSort(i++);
				list.add(img);
			}
			
			infoService.updateInfoListById(list);
		
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Info> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<Info> infoList) {
		this.infoList = infoList;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public Integer getId_cate() {
		return id_cate;
	}

	public void setId_cate(Integer id_cate) {
		this.id_cate = id_cate;
	}

}
