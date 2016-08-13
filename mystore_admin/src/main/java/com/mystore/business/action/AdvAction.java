package com.mystore.business.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.ConfigReader;
import com.mystore.business.common.Constans;
import com.mystore.business.dto.AdvImg;
import com.mystore.business.dto.AdvPageType;
import com.mystore.business.dto.AdvProModule;
import com.mystore.business.dto.AdvResourceModule;
import com.mystore.business.dto.Product;
import com.mystore.business.service.AdvertiseService;
import com.mystore.business.service.BrandService;
import com.mystore.business.service.CategoryService;
import com.mystore.business.service.ProductService;

@Controller("advAction")
@Scope("prototype")
public class AdvAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AdvertiseService advertiseService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BrandService brandService;
	
	private Integer id;
	
	private String ids;
	
	private Integer pid;
	
	private String name;
	
	private String type;
	
	private String bh;
	
	private Integer sort;
	
	private String descr;
	
	private String title;
	
	private String path_pic;
	
	private String isMarkShow;
	
	private String isShow;
	
	private Date endTime;
	
	private Date startTime;
	
	private String isPermEffe;
	
	private Integer proId;
	
	private String content;
	
	private String url;
	
	private String status;
	
	private List<AdvImg> advImgList;
	
	private List<AdvProModule> proMoudleList;
	
	private List<AdvResourceModule> resMoudleList;
	
	private AdvImg advImg;
	
	private AdvProModule advProModule;
	
	private AdvResourceModule advResourceModule;
	
	public String index(){
		
		return "index";
	}
	
	public String list(){
		
		return "list";
	}
	
	public String list_advimg(){
		
	    advImgList = advertiseService.getAdvImgListByPid(pid);
		
		return "list_advimg";
	}
	
	public String list_pro() throws Exception{
		
	    proMoudleList = advertiseService.getAdvProModuleListByPid(pid);
	    if(proMoudleList != null && proMoudleList.size() > 0){
	    	for(AdvProModule proModule : proMoudleList){
	    		Product pro = productService.getProById(proModule.getId_pro());
	    		proModule.setProduct(pro);
	    		if(pro != null){
	    			proModule.setCategory(categoryService.getCateById(pro.getId_cate()));
	    			proModule.setBrand(brandService.getBrandById(pro.getId_brand()));
	    		}
	    	}
	    }
		
		return "list_pro";
	}
	
	public String list_resource() throws Exception{
		
		resMoudleList = advertiseService.getAdvResourceModuleListByPid(pid);
		
		if(resMoudleList != null && resMoudleList.size() > 0){
	    	for(AdvResourceModule resModule : resMoudleList){
	    		Product pro = productService.getProById(resModule.getId_pro());
	    		if(pro != null){
	    			resModule.setProduct(pro);
	    			resModule.setCategory(categoryService.getCateById(pro.getId_cate()));
	    			resModule.setBrand(brandService.getBrandById(pro.getId_brand()));
	    		}
	    	}
	    }
		
		
		return "list_resource";
	}
	
	public String edit_advimg(){
		
		if(id != null){
		    advImg =  advertiseService.getAdvImgByid(id);
		    if(advImg != null){
		    	pid = advImg.getPid();
		    }
		}
		
		return "edit_advimg";
	}
	
	public String edit_pro() throws Exception{
		
		if(id != null){
			advProModule =  advertiseService.getAdvProModuleById(id);
		    if(advProModule != null){
		    	pid = advProModule.getPid();
		    	Product pro = productService.getProById(advProModule.getId_pro());
		    	advProModule.setProduct(pro);
	    		if(pro != null){
	    			advProModule.setCategory(categoryService.getCateById(pro.getId_cate()));
	    			advProModule.setBrand(brandService.getBrandById(pro.getId_brand()));
	    		}
		    }
		}
		
		return "edit_pro";
		
	}
	
	public String edit_resource() throws Exception{
		
		if(id != null){
			advResourceModule =  advertiseService.getAdvResourceModuleById(id);
		    if(advResourceModule != null){
		    	pid = advResourceModule.getPid();
		    	Product pro = productService.getProById(advResourceModule.getId_pro());
	    		if(pro != null){
	    			advResourceModule.setProduct(pro);
	    			advResourceModule.setCategory(categoryService.getCateById(pro.getId_cate()));
	    			advResourceModule.setBrand(brandService.getBrandById(pro.getId_brand()));
	    		}
		    }
		}
		
		return "edit_resource";
		
	}
	
	public void get_note() throws IOException{
		
		StringBuilder returnResult = new StringBuilder("");
        returnResult.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		try{
			if(id == null){
				returnResult.append("<tree id=\"0\">");
					returnResult.append("<item text=\"广告分类\" id=\"-1\" open=\"1\">");
					
					   List<AdvPageType> cateList = advertiseService.getAdvPageTypeListByPid(-1);
					   if(cateList != null && cateList.size() > 0){
						   for(AdvPageType adv:cateList){
							   int count = advertiseService.selectCountByPid(adv.getId());
							   if(count == 0){
								   returnResult.append("<item text=\""+adv.getName()+"\" id=\""+adv.getId()+"\" child=\"0\"></item>");
							   }else{
								   returnResult.append("<item text=\""+adv.getName()+"\" id=\""+adv.getId()+"\" child=\"1\"></item>"); 
							   } 
						   }
					   }
					
					returnResult.append("</item>");
				returnResult.append("</tree>");
			}else{
				returnResult.append("<tree id=\""+id+"\">");
				   List<AdvPageType> cateList = advertiseService.getAdvPageTypeListByPid(id);
				   if(cateList != null && cateList.size() > 0){
					   for(AdvPageType adv:cateList){
						   int count = advertiseService.selectCountByPid(adv.getId());
						   if(count == 0){
							   returnResult.append("<item text=\""+adv.getName()+"\" id=\""+adv.getId()+"\" child=\"0\"></item>");
						   }else{
							   returnResult.append("<item text=\""+adv.getName()+"\" id=\""+adv.getId()+"\" child=\"1\"></item>"); 
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
			
			AdvPageType cate = advertiseService.getAdvPageTypeByPidAndName(pid==null?-1:pid, name);
			if(cate != null){
				returnCode = -3;
				return;
			}
			
			if(StringUtils.isNotBlank(bh)){
				cate = advertiseService.getAdvPageTypeByBh(bh);
				if(cate != null){
					returnCode = -4;
					return;
				}
			}
			
			cate = new AdvPageType();
			cate.setName(name);
			cate.setSort(sort == null?1:sort);
			cate.setPid(pid==null?-1:pid);
			cate.setType(type);
			cate.setBh(bh);
			cate.setDescr(descr);
			
			advertiseService.addAdvPageType(cate);
		
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
			
			AdvPageType cate = advertiseService.getAdvPageTypeById(id);
	        data.put("name", cate.getName());
	        data.put("type", cate.getType());
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
			
			AdvPageType cate = advertiseService.getAdvPageTypeByPidAndName(pid==null?-1:pid, name);
			if(cate != null && cate.getId().intValue() != id.intValue()){
				returnCode = -3;
				return;
			}
			
			if(StringUtils.isNotBlank(bh)){
				cate = advertiseService.getAdvPageTypeByBh(bh);
				if(cate != null && cate.getId().intValue() != id.intValue()){
					returnCode = -4;
					return;
				}
			}
			
			cate = new AdvPageType();
			cate.setName(name);
			cate.setSort(sort == null?1:sort);
			cate.setPid(pid==null?-1:pid);
			cate.setType(type);
			cate.setBh(bh);
			cate.setDescr(descr);
			cate.setId(id);
			
			advertiseService.updateAdvPageTypeById(cate);
		
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
			
			advertiseService.deleteAdvPageTypeById(id);
		
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().print(returnCode);
		}
		
	}
	
	public void saveAdvImg() throws IOException{
		
		Integer returnCode = 0;
		try{
			
			if(pid == null){
				returnCode = -2;
				return;
			}
			
			AdvImg advImg = new AdvImg();
			advImg.setPid(pid);
			advImg.setTitle(title);
			advImg.setPath_pic(path_pic);
			advImg.setUrl(url);
			advImg.setIsPermEffe(isPermEffe);
			advImg.setSort(sort == null?1:sort);
			advImg.setStartTime(startTime);
			advImg.setEndTime(endTime);
			advImg.setStatus(status);
			
			if(id != null){
				advImg.setId(id);
				advertiseService.updateAdvImgById(advImg);				
			}else{
				advertiseService.addAdvImg(advImg);
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
	 * @return 
	 *  -4:文件太大; -3:文件不存在 ;-2:参数错误 ;-1:服务异常
	 * 
	 * */
	public void uploadImg() throws IOException, JSONException{
		
		Integer returnCode = 0;
		JSONObject data = new JSONObject();
		String path = "";
		FileOutputStream fos = null;
        FileInputStream fis = null;
		try{
			  
			  HttpServletRequest request = ServletActionContext.getRequest();
			  MultiPartRequestWrapper multiWrapper = (MultiPartRequestWrapper)request;
			  Enumeration<String> fileParameterNames = multiWrapper.getFileParameterNames();
		        while (fileParameterNames != null && fileParameterNames.hasMoreElements()) {
		            String inputName = (String) fileParameterNames.nextElement();
		            String[] contentType = multiWrapper.getContentTypes(inputName);
		            if (contentType != null && contentType.length > 0) {
		                String[] fileName = multiWrapper.getFileNames(inputName);
		                if (StringUtils.isNoneBlank(fileName)) {
		                	
		                    File[] files = multiWrapper.getFiles(inputName);
		                    if(files != null && files.length > 0){
		                    	File file = files[0];
		                    	String suffixName = fileName[0].substring(fileName[0].lastIndexOf(".")+1);
		                    	if(file != null){
		                    		
		                    		if(!file.exists()){
		                				returnCode = -3;
		                				return;
		                			}
		                			
		                			fis = new FileInputStream(file);
		                	        
		                	        if(fis.available() == 0){
		                	        	returnCode = -3;
		                				return;
		                	        }
		                	        
		                	       if(fis.available() > Constans.SIZE_MAX_IMG_UPLOAD){
		                	        	returnCode = -4;
		                				return;
		                	        }
		                	       
		                	        path = Constans.PATH_UPLOAD_IMG_ADV+new SimpleDateFormat(Constans.DATE_FORMATE).format(new Date())+"."+suffixName;
		                	        
		                	        File newFiel = new File(ConfigReader.getPath_pic_upload()+"/"+path);
		                	        
		                	       if(!newFiel.getParentFile().exists()) {
		                	    	   newFiel.getParentFile().mkdirs();
		                	    	}
		                	        
		                           fos = new FileOutputStream(newFiel);
		                           
		                           byte[] buffer = new byte[1024];
		                           int len = 0;
		                           while ((len = fis.read(buffer)) > 0) {
		                               fos.write(buffer, 0, len);
		                           }
		                           returnCode = 1;
		                           data.put("path", path);
		                           
		                    	}
		                    }
		                    
		                }
		            }
		        }
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 if(fis != null){
				 fis.close();
			 }
			 if(fos != null){
				 fos.close();
			 }
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 data.put("returnCode", returnCode);
			 response.getWriter().print(data.toString());
		}
	}
	
	public void delAdvImg() throws IOException{
		Integer returnCode = 0;
		try{
			
			if(id == null){
				returnCode = -2;
				return;
			}
			
			advertiseService.deleteAdvImgById(id);
			
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().print(returnCode);
		}
	}

	public void saveAdvImgSort() throws IOException{
		
		Integer returnCode = 0;
		try{
		
			if(StringUtils.isBlank(ids)){
				returnCode = -2;
				return;
			}
			
			String[] idArr = ids.split(",");
			
			List<AdvImg> list = new ArrayList<AdvImg>();
			
			int i = 1;
			for(String s:idArr){
				if(StringUtils.isBlank(s))continue;
				AdvImg img = new AdvImg();
	            img.setId(Integer.valueOf(s));
	            img.setSort(i++);
				list.add(img);
			}
			
			advertiseService.updateAdvImgListById(list);
		
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().print(returnCode);
		}
	
	}
	
	public void selectProListByLikeNameOrSn() throws IOException, JSONException{
		
		Integer returnCode = 0;
		JSONObject data = new JSONObject();
		try{
		
			if(StringUtils.isBlank(name)){
				returnCode = -2;
				return;
			}
			
			List<Map<String,Object>> mapList = productService.selectProListByLikeNameOrSn(name);
			if(mapList != null && mapList.size() > 0){
				JSONArray list = new JSONArray();
				for(Map<String,Object> map:mapList){
					JSONObject ent = new JSONObject();
					ent.put("id", map.get("id").toString());
					ent.put("name", map.get("name").toString());
					ent.put("sn", map.get("sn").toString());
					ent.put("id", map.get("id").toString());
					ent.put("cateName", map.get("cateName").toString());
					ent.put("brandName", map.get("brandName").toString());
					list.put(ent);
				}
				data.put("list", list);
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
	
	public void saveProModule() throws IOException{
		
		Integer returnCode = 0;
		try{
		
			if(pid == null || proId == null){
				returnCode = -2;
				return;
			}
			
			AdvProModule  advProModule = advertiseService.getAdvProModuleByPidAndProId(pid, proId);
			if(advProModule != null ){
				if(id == null || id.intValue() != advProModule.getId().intValue()){
					returnCode = -3;
					return;
				}
			}
			
			advProModule = new AdvProModule();
			advProModule.setPid(pid);
			advProModule.setId_pro(proId);
			advProModule.setName(name);
			advProModule.setSort(sort == null?1:sort);
			advProModule.setStartTime(startTime);
			advProModule.setEndTime(endTime);
			advProModule.setStatus(status);
			
			if(id != null){
				advProModule.setId(id);
				advertiseService.updateAdvProModuleById(advProModule);
			}else{
				advertiseService.addAdvProModule(advProModule);
			}
		
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().print(returnCode);
		}
	}
	
	public void delProModule() throws IOException{
		Integer returnCode = 0;
		try{
			
			if(id == null){
				returnCode = -2;
				return;
			}
			
			advertiseService.deleteAdvProModuleById(id);
			
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().print(returnCode);
		}
	}

	public void saveProModuleSort() throws IOException{
		
		Integer returnCode = 0;
		try{
		
			if(StringUtils.isBlank(ids)){
				returnCode = -2;
				return;
			}
			
			String[] idArr = ids.split(",");
			
			List<AdvProModule> list = new ArrayList<AdvProModule>();
			
			int i = 1;
			for(String s:idArr){
				if(StringUtils.isBlank(s))continue;
				AdvProModule img = new AdvProModule();
	            img.setId(Integer.valueOf(s));
	            img.setSort(i++);
				list.add(img);
			}
			
			advertiseService.updateAdvProModuleListById(list);
		
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().print(returnCode);
		}
	
	}
	
	public void saveResModule() throws IOException{
		
		Integer returnCode = 0;
		try{
		
			if(pid == null){
				returnCode = -2;
				return;
			}
			
			AdvResourceModule advResourceModule = new AdvResourceModule();
			advResourceModule.setPid(pid);
			if(proId != null){
				Product product = productService.getProById(proId);
				if(product != null){
					advResourceModule.setId_pro(proId);
					advResourceModule.setSn(product.getSn());
				}
			}
			advResourceModule.setName(name);
			advResourceModule.setSort(sort == null?1:sort);
			advResourceModule.setContent(content);
			advResourceModule.setIsMarkShow(isMarkShow);
			advResourceModule.setIsShow(isShow);
			advResourceModule.setPath_pic(path_pic);
			advResourceModule.setUrl(url);
			advResourceModule.setStatus(status);
			if(id != null){
				advResourceModule.setId(id);
				advertiseService.updateAdvResourceModuleById(advResourceModule);
			}else{
				advertiseService.addAdvResourceModule(advResourceModule);
			}
		
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().print(returnCode);
		}
	}
	
	public void delResModule() throws IOException{
		Integer returnCode = 0;
		try{
			
			if(id == null){
				returnCode = -2;
				return;
			}
			
			advertiseService.deletaAdvResourceModuleById(id);
			
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().print(returnCode);
		}
	}

	public void saveResModuleSort() throws IOException{
		
		Integer returnCode = 0;
		try{
		
			if(StringUtils.isBlank(ids)){
				returnCode = -2;
				return;
			}
			
			String[] idArr = ids.split(",");
			
			List<AdvResourceModule> list = new ArrayList<AdvResourceModule>();
			
			int i = 1;
			for(String s:idArr){
				if(StringUtils.isBlank(s))continue;
				AdvResourceModule img = new AdvResourceModule();
	            img.setId(Integer.valueOf(s));
	            img.setSort(i++);
				list.add(img);
			}
			
			advertiseService.updateAdvResModuleListById(list);
		
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public List<AdvImg> getAdvImgList() {
		return advImgList;
	}

	public void setAdvImgList(List<AdvImg> advImgList) {
		this.advImgList = advImgList;
	}

	public List<AdvProModule> getProMoudleList() {
		return proMoudleList;
	}

	public void setProMoudleList(List<AdvProModule> proMoudleList) {
		this.proMoudleList = proMoudleList;
	}

	public List<AdvResourceModule> getResMoudleList() {
		return resMoudleList;
	}

	public void setResMoudleList(List<AdvResourceModule> resMoudleList) {
		this.resMoudleList = resMoudleList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath_pic() {
		return path_pic;
	}

	public void setPath_pic(String pathPic) {
		path_pic = pathPic;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getIsPermEffe() {
		return isPermEffe;
	}

	public void setIsPermEffe(String isPermEffe) {
		this.isPermEffe = isPermEffe;
	}

	public AdvImg getAdvImg() {
		return advImg;
	}

	public void setAdvImg(AdvImg advImg) {
		this.advImg = advImg;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public AdvProModule getAdvProModule() {
		return advProModule;
	}

	public void setAdvProModule(AdvProModule advProModule) {
		this.advProModule = advProModule;
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public AdvResourceModule getAdvResourceModule() {
		return advResourceModule;
	}

	public void setAdvResourceModule(AdvResourceModule advResourceModule) {
		this.advResourceModule = advResourceModule;
	}

	public String getIsMarkShow() {
		return isMarkShow;
	}

	public void setIsMarkShow(String isMarkShow) {
		this.isMarkShow = isMarkShow;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
