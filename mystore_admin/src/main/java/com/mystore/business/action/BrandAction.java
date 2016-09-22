package com.mystore.business.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.ConfigReader;
import com.mystore.business.common.Constans;
import com.mystore.business.dto.Brand;
import com.mystore.business.dto.BrandInfo;
import com.mystore.business.dto.Category;
import com.mystore.business.service.BrandService;
import com.mystore.business.service.CategoryService;
import com.mystore.business.service.RegionService;

@Controller("brandAction")
@Scope("prototype")
public class BrandAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Autowired
	private BrandService brandService;
	

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private RegionService regionService;
	
	private Integer id;
	
	private Integer pid;
	
	private Integer id_cate;
	
	private String name;
	
	private String rome;
	
	private String jianPin;
	
	private String enName;
	
	private String keyWords;
	
	private String descr;
	
	private String status;
	
	private String path_logoimg_fir;
	
	private String path_logoimg_sec;
	
	private String url;
	
	private Integer sort;
	
	private Integer id_brand;
	
	private String manufacturer;
	
	private Integer id_region;
	
	private String info;
	
	private String culture;
	
	private String honorsandawards;
	
	private String qualification;
	
	private BrandInfo brandInfo;
	
	private File upload_path_logoimg_fir; 
     
    private String upload_path_logoimg_firContentType;  
     
    private String upload_path_logoimg_firFileName;
     
    private File upload_path_logoimg_sec; 
     
    private String upload_path_logoimg_secContentType;  
     
    private String upload_path_logoimg_secFileName;
    
    private String type;
	
	public String index(){
		
		return "index";
	}
	
	public void get_note() throws IOException{
		
		StringBuilder returnResult = new StringBuilder("");
        returnResult.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		try{
			if(id == null){
				returnResult.append("<tree id=\"0\">");
					returnResult.append("<item text=\"品牌管理\" id=\"-1\" open=\"1\">");
					
					   List<Brand> cateList = brandService.selectBrandListByPid(-1);
					   if(cateList != null && cateList.size() > 0){
						   for(Brand region:cateList){
							   int count = brandService.selectCountByPid(region.getId());
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
				   List<Brand> cateList = brandService.selectBrandListByPid(id);
				   if(cateList != null && cateList.size() > 0){
					   for(Brand region:cateList){
						   int count = brandService.selectCountByPid(region.getId());
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
			
			if(id_cate == null || StringUtils.isBlank(name)){
				returnCode = -2;
				return;
			}
			
			Brand brand = brandService.getBrandByPidAndName(pid==null?-1:pid, name);
			if(brand != null){
				returnCode = -3;
				return;
			}
			
			brand = new Brand();
			brand.setPid(pid==null?-1:pid);
			brand.setId_cate(id_cate);
			
			if(pid != null && pid != -1){
				Brand parent = brandService.getBrandById(pid);
				if(parent.getPid() == -1){
					brand.setPids(","+pid+",");
				}else{
					brand.setPids(parent.getPids()+pid+",");
				}
			}
			
			brand.setName(name);
			brand.setRome(rome);
			brand.setJianPin(jianPin);
			brand.setEnName(enName);
			brand.setKeyWords(keyWords);
			brand.setDescr(descr);
			brand.setSort(sort == null?1:sort);
			brand.setUrl(url);
			brand.setPath_logoimg_fir(path_logoimg_fir);
			brand.setPath_logoimg_sec(path_logoimg_sec);
			
			BrandInfo brandInfo = new BrandInfo();
			brandInfo.setManufacturer(manufacturer);
			brandInfo.setId_region(id_region);
			brandInfo.setInfo(info);
			brandInfo.setCulture(culture);
			brandInfo.setHonorsandawards(honorsandawards);
			brandInfo.setQualification(qualification);
				
			brand.setBrandInfo(brandInfo);
			
			brandService.addBrand(brand);
		
			returnCode = brand.getId();
		
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
	public void getBrandById() throws IOException, JSONException{
		JSONObject data = new JSONObject();
		Integer returnCode = 0;
		try{
			
			if(id == null){
				returnCode = -2;
				return;
			}
			
	        Brand brand = brandService.getBrandById(id);
	        if(brand != null){
		        data.put("id_cate", brand.getId_cate());
		        Category cate = categoryService.getCateById(brand.getId_cate());
		        data.put("cateName",cate!= null?cate.getName():"");
		        data.put("name", brand.getName());
		        data.put("rome", brand.getRome());
		        data.put("jianPin", brand.getJianPin());
		        data.put("enName", brand.getEnName());
		        data.put("keyWords", brand.getKeyWords());
		        data.put("descr", brand.getDescr());
		        data.put("sort", brand.getSort());
		        data.put("url", brand.getUrl());
		        data.put("path_logoimg_fir", brand.getPath_logoimg_fir());
		        data.put("path_logoimg_sec", brand.getPath_logoimg_sec());
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
	
	 /**
     * @param 
     * @return -3:同级有重名;-2:参数错误;-1:服务异常;
     * 
     * */
	public void updateNote() throws IOException{
		Integer returnCode = 0;
		try{
			
			if(id == null || id_cate == null || StringUtils.isBlank(name)){
				returnCode = -2;
				return;
			}
			
			Brand brand = brandService.getBrandByPidAndName(pid==null?-1:pid, name);
			if(brand != null && brand.getId().intValue() != id.intValue()){
				returnCode = -3;
				return;
			}
			
			brand = new Brand();
	        brand.setId(id);
			brand.setPid(pid);
			brand.setId_cate(id_cate);
			brand.setName(name);
			brand.setRome(rome);
			brand.setJianPin(jianPin);
			brand.setEnName(enName);
			brand.setKeyWords(keyWords);
			brand.setDescr(descr);
			brand.setSort(sort == null?1:sort);
			brand.setUrl(url);
			brand.setPath_logoimg_fir(path_logoimg_fir);
			brand.setPath_logoimg_sec(path_logoimg_sec);
			
			brandService.upateBrandById(brand);
		
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
			
			brandService.deleteBrandById(id);
		
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().print(returnCode);
		}
		
	}
	
	public String brandInfo() throws Exception{
		
		if(pid != null){
			brandInfo = brandService.getBrandInfoByBid(pid);
			if(brandInfo != null && brandInfo.getId_region() != null){
				brandInfo.setRegName(regionService.getRegionById(brandInfo.getId_region()).getName());
			}
		}
		
		return "brandInfo";
	}
	
	public void updateBrandInfo() throws Exception{
		
		Integer returnCode = 0;
		try{
			
			if(pid == null){
				returnCode = -2;
				return;
			}
			
			BrandInfo brandInfo = new BrandInfo();
			brandInfo.setId_brand(pid);
			brandInfo.setManufacturer(manufacturer);
			brandInfo.setId_region(id_region);
			brandInfo.setInfo(info);
			brandInfo.setCulture(culture);
			brandInfo.setHonorsandawards(honorsandawards);
			brandInfo.setQualification(qualification);
			
			brandService.upateBrandInfoByBid(brandInfo);
		
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().print(returnCode);
		}
	}

	public String cate_select(){
		
		return "cate_select";
		
	}
	
	public String region_select(){
		
		return "region_select";
		
	}

	/**
	  * 图片上传
	  * @return -3:文件太大;-2:文件不存在; -1:服务异常;0;1:上传成功
	 * @throws JSONException 
	  * 
	  * 
	  * */
	 public void uploadImg() throws IOException, JSONException{
		 
		int returnCode = 0;
		JSONObject data = new JSONObject();
		String path = null;
		FileOutputStream fos = null;
        FileInputStream fis = null;
    	HttpServletResponse response = ServletActionContext.getResponse();
		try{
			
			File file = null;
			String fileName = null;	
			String suffixName = null;
			
			if(type.equals("upload_path_logoimg_fir")){
				file = upload_path_logoimg_fir;
				fileName = upload_path_logoimg_firFileName;
				suffixName = fileName.substring(fileName.lastIndexOf(".")+1);
			}else if(type.equals("upload_path_logoimg_sec")){
				file = upload_path_logoimg_sec;
				fileName = upload_path_logoimg_secFileName;
				suffixName = fileName.substring(fileName.lastIndexOf(".")+1);
			}
			
			if(!file.exists()){
				returnCode = -2;
				return;
			}

			
			fis = new FileInputStream(file);
	        
	        if(fis.available() == 0){
	        	returnCode = -2;
				return;
	        }
	        
	       if(fis.available() > Constans.SIZE_MAX_IMG_UPLOAD){
	        	returnCode = -3;
				return;
	        }
	       
	        path = Constans.PATH_UPLOAD_LOGO_BRAND+new SimpleDateFormat(Constans.DATE_FORMATE).format(new Date())+"."+suffixName;
	        
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
		}catch(Exception e){
			returnCode = -1;
        }finally{
        	 if(fis != null){
				 fis.close();
			 }
			 if(fos != null){
				 fos.close();
			 }
	       	 data.put("returnCode", returnCode);
	       	 response.setContentType("text/html;charset=UTF-8");
        	 response.getWriter().print(data.toString());
        }
	 }
	 
	 public void getAllNote() throws Exception{
			
	        StringBuilder returnResult = new StringBuilder("");
	        returnResult.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			try{
					returnResult.append("<tree id=\"0\">");
						returnResult.append("<item text=\"品牌管理\" id=\"-1\" open=\"1\">");
						
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
			   List<Brand> brandList = brandService.selectBrandListByPid(pid);
			   if(brandList != null && brandList.size() > 0){
				   for(Brand brand:brandList){
						  returnResult.append("<item text=\""+brand.getName()+"\" id=\""+brand.getId()+"\" open=\"1\">");
					      getNextNote(returnResult,brand.getId());   
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

	public Integer getId_cate() {
		return id_cate;
	}

	public void setId_cate(Integer idCate) {
		id_cate = idCate;
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

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPath_logoimg_fir() {
		return path_logoimg_fir;
	}

	public void setPath_logoimg_fir(String pathLogoimgFir) {
		path_logoimg_fir = pathLogoimgFir;
	}

	public String getPath_logoimg_sec() {
		return path_logoimg_sec;
	}

	public void setPath_logoimg_sec(String pathLogoimgSec) {
		path_logoimg_sec = pathLogoimgSec;
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

	public Integer getId_brand() {
		return id_brand;
	}

	public void setId_brand(Integer idBrand) {
		id_brand = idBrand;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Integer getId_region() {
		return id_region;
	}

	public void setId_region(Integer idRegion) {
		id_region = idRegion;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getCulture() {
		return culture;
	}

	public void setCulture(String culture) {
		this.culture = culture;
	}

	public String getHonorsandawards() {
		return honorsandawards;
	}

	public void setHonorsandawards(String honorsandawards) {
		this.honorsandawards = honorsandawards;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public BrandInfo getBrandInfo() {
		return brandInfo;
	}

	public void setBrandInfo(BrandInfo brandInfo) {
		this.brandInfo = brandInfo;
	}

	public File getUpload_path_logoimg_fir() {
		return upload_path_logoimg_fir;
	}

	public void setUpload_path_logoimg_fir(File uploadPathLogoimgFir) {
		upload_path_logoimg_fir = uploadPathLogoimgFir;
	}

	public String getUpload_path_logoimg_firContentType() {
		return upload_path_logoimg_firContentType;
	}

	public void setUpload_path_logoimg_firContentType(
			String uploadPathLogoimgFirContentType) {
		upload_path_logoimg_firContentType = uploadPathLogoimgFirContentType;
	}

	public String getUpload_path_logoimg_firFileName() {
		return upload_path_logoimg_firFileName;
	}

	public void setUpload_path_logoimg_firFileName(
			String uploadPathLogoimgFirFileName) {
		upload_path_logoimg_firFileName = uploadPathLogoimgFirFileName;
	}

	public File getUpload_path_logoimg_sec() {
		return upload_path_logoimg_sec;
	}

	public void setUpload_path_logoimg_sec(File uploadPathLogoimgSec) {
		upload_path_logoimg_sec = uploadPathLogoimgSec;
	}

	public String getUpload_path_logoimg_secContentType() {
		return upload_path_logoimg_secContentType;
	}

	public void setUpload_path_logoimg_secContentType(
			String uploadPathLogoimgSecContentType) {
		upload_path_logoimg_secContentType = uploadPathLogoimgSecContentType;
	}

	public String getUpload_path_logoimg_secFileName() {
		return upload_path_logoimg_secFileName;
	}

	public void setUpload_path_logoimg_secFileName(
			String uploadPathLogoimgSecFileName) {
		upload_path_logoimg_secFileName = uploadPathLogoimgSecFileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
