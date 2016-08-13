package com.mystore.business.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.Pager;
import com.mystore.business.dto.BasicAttri;
import com.mystore.business.dto.CateAttri;
import com.mystore.business.dto.Category;
import com.mystore.business.service.BasicAttriService;
import com.mystore.business.service.CategoryService;

@Controller("categoryAction")
@Scope("prototype")
public class CategoryAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BasicAttriService basicAttriService;
	
	private Integer id;
	
	private String ids;
	
	private Integer pid;
	
	private String name;
	
	private String rome;
	
	private String jianPin;
	
	private String enName;
	
	private String descr;
	
	private String keyWords;
	
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
					returnResult.append("<item text=\"分类管理\" id=\"-1\" open=\"1\">");
					
					   List<Category> cateList = categoryService.selectCateListByPid(-1);
					   if(cateList != null && cateList.size() > 0){
						   for(Category cate:cateList){
							   int count = categoryService.selectCountByPid(cate.getId());
							   if(count == 0){
								   returnResult.append("<item text=\""+cate.getName()+"\" id=\""+cate.getId()+"\" child=\"0\"></item>");
							   }else{
								   returnResult.append("<item text=\""+cate.getName()+"\" id=\""+cate.getId()+"\" child=\"1\"></item>"); 
							   } 
						   }
					   }
					
					returnResult.append("</item>");
				returnResult.append("</tree>");
			}else{
				returnResult.append("<tree id=\""+id+"\">");
				   List<Category> cateList = categoryService.selectCateListByPid(id);
				   if(cateList != null && cateList.size() > 0){
					   for(Category cate:cateList){
						   int count = categoryService.selectCountByPid(cate.getId());
						   if(count == 0){
							   returnResult.append("<item text=\""+cate.getName()+"\" id=\""+cate.getId()+"\" child=\"0\"></item>");
						   }else{
							   returnResult.append("<item text=\""+cate.getName()+"\" id=\""+cate.getId()+"\" child=\"1\"></item>"); 
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
			
			Category cate = categoryService.getCateByPidAndName(pid==null?-1:pid, name);
			if(cate != null){
				returnCode = -3;
				return;
			}
			
			cate = new Category();
			cate.setName(name);
			cate.setSort(sort == null?1:sort);
			cate.setPid(pid==null?-1:pid);
			cate.setRome(rome);
			cate.setJianPin(jianPin);
			cate.setEnName(enName);
			cate.setKeyWords(keyWords);
			cate.setDescr(descr);
			
			categoryService.addCategory(cate);
		
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
			
	        Category cate = categoryService.getCateById(id);
	        data.put("name", cate.getName());
	        data.put("rome", cate.getRome());
	        data.put("jianPin", cate.getJianPin());
	        data.put("enName", cate.getEnName());
	        data.put("keyWords", cate.getKeyWords());
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
			
			Category cate = categoryService.getCateByPidAndName(pid==null?-1:pid, name);
			if(cate != null && cate.getId().intValue() != id.intValue()){
				returnCode = -3;
				return;
			}
			
			cate = new Category();
			cate.setId(id);
			cate.setName(name);
			cate.setSort(sort == null?1:sort);
			cate.setPid(pid==null?-1:pid);
			cate.setRome(rome);
			cate.setJianPin(jianPin);
			cate.setEnName(enName);
			cate.setKeyWords(keyWords);
			cate.setDescr(descr);
			
			categoryService.upateCateById(cate);
		
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
			
			categoryService.deleteCateById(id);
		
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
					returnResult.append("<item text=\"分类管理\" id=\"-1\" open=\"1\">");
					
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
		   List<Category> cateList = categoryService.selectCateListByPid(pid);
		   if(cateList != null && cateList.size() > 0){
			   for(Category cate:cateList){
					  returnResult.append("<item text=\""+cate.getName()+"\" id=\""+cate.getId()+"\" open=\"1\">");
				      getNextNote(returnResult,cate.getId());   
				   returnResult.append("</item>");
			   }
		   }
	}
	
	public String cateAttri(){
		
		return "cateAttri";
	}
	
	public void getAttriByCateId() throws IOException, JSONException{
		Integer returnCode = 0;
		JSONObject data = new JSONObject();
		JSONArray list = new JSONArray();
		data.put("list", list);
		try{
			
			if(id == null){
				returnCode = -2;
				return;
			}
			
			List<CateAttri> ownList =  categoryService.selectCateAttriListByCateId(id);
	
			if(ownList != null && ownList.size() > 0){
				for(CateAttri cateAttri:ownList){
					JSONObject ent = new JSONObject();
					list.put(ent);
					ent.put("id_attri", cateAttri.getId_attr());
					ent.put("name_attri", cateAttri.getAttriName());
					ent.put("isOwn", true);
				}
			}
			
			List<BasicAttri>  baseList = null;
			Pager<BasicAttri> pager = basicAttriService.getBasicAttriList(null,null,null);
			if(pager != null && pager.getResultList() != null && pager.getResultList().size() > 0){
				baseList = pager.getResultList();
			}
			
			if(baseList != null && baseList.size() > 0){
				for(BasicAttri basicAttri:baseList){	
					boolean tf = false;
					if(ownList != null && ownList.size() > 0){
						for(CateAttri cateAttri:ownList){
							if(cateAttri.getId_attr().intValue() == basicAttri.getId().intValue()){
								tf = true;
								break;
							}
						}
					}
					if(!tf){
						JSONObject ent = new JSONObject();
						list.put(ent);
						ent.put("id_attri", basicAttri.getId());
						ent.put("name_attri", basicAttri.getName());
						ent.put("isOwn", false);
					}
				}
			}
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 data.put("returnCode", returnCode);
			 response.getWriter().print(data.toString());
		}
	}
	
	public void saveAttr() throws IOException{
		Integer returnCode = 0;
		try{
		
			if(id == null || StringUtils.isBlank(ids)){
				returnCode = -2;
				return;
			}
			
			String[] idArr = ids.split(",");
			
			List<CateAttri> list = new ArrayList<CateAttri>();
			
			int i = 1;
			for(String s:idArr){
				if(StringUtils.isBlank(s))continue;
				CateAttri cteAttri = new CateAttri();
				cteAttri.setId_cate(id);
				cteAttri.setId_attr(Integer.valueOf(s));
				cteAttri.setSort(i++);
				list.add(cteAttri);
			}
			
			categoryService.addCateAttri(list);
		
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

	public String getRome() {
		return rome;
	}

	public void setRome(String rome) {
		this.rome = rome;
	}

	public String getJianPin() {
		return jianPin;
	}

	public void setJianPin(String jianPin) {
		this.jianPin = jianPin;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
