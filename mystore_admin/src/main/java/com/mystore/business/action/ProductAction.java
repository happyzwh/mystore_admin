package com.mystore.business.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.ConfigReader;
import com.mystore.business.common.Constans;
import com.mystore.business.common.PageInfo;
import com.mystore.business.common.Pager;
import com.mystore.business.dto.BasicAttri;
import com.mystore.business.dto.BasicAttriValue;
import com.mystore.business.dto.Brand;
import com.mystore.business.dto.CateAttri;
import com.mystore.business.dto.Category;
import com.mystore.business.dto.ProAttr;
import com.mystore.business.dto.ProImg;
import com.mystore.business.dto.ProInfo;
import com.mystore.business.dto.ProPrice;
import com.mystore.business.dto.Product;
import com.mystore.business.service.BasicAttriService;
import com.mystore.business.service.BrandService;
import com.mystore.business.service.CategoryService;
import com.mystore.business.service.ProductService;
import com.mystore.business.util.Thumbnail;

@Controller("productAction")
@Scope("prototype")
public class ProductAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private BasicAttriService basicAttriService;
	
	private List<Product> list;
	
	private Product product;
	
    private Integer id;
    
    private String ids;
	
	private Integer id_cate;
	
	private String cateName;
	
	private Integer id_brand;
	
	private String brandName;
	
	private String name;
	
	private String sn;
	
	private String shortTitle;
	
	private String subTitle;
	
	private String enTitle;
	
	private String rome;
	
	private String jianPin;
	
	private String keyWords;
	
	private String path_img;
	
	private String descr;
	
	private String isShow;
	
	private String isOnSale;
	
	private String isShipping;
	
	private Date onOrOffSaleTime;
	
	private Date onSaleStartTime;
	
	private Date onSaleEndTime;
	
	private Date offSaleStartTime;
	
	private Date offSaleEndTime;

	private Integer sort;
	
	private String status;
	
	private Integer id_pro;

	private String provider;
	
	private String feature;
	
	private String proShow;
	
	private String areaIntroduce;
	
	private String qualification;
	
	private String useMethod;
	
	private String brandCulture;
	
	private String honor;
	
	private String providerIntroduce;
	
	private ProInfo proInfo;
	
	private Double markPrice;
	
	private Double shopPrice;
	
	private Double activePrice;
	
	private ProPrice price;
	
	private List<ProImg> imgList;
	
	private List<CateAttri> attrList;
	
	private Map<String,ProAttr> attrMap;
	
	public String list() throws Exception{
		
		
		Product pro = new Product();
		pro.setName(StringUtils.isBlank(name)?null:name.trim());
		pro.setSn(StringUtils.isBlank(sn)?null:sn.trim());
		pro.setId_cate(id_cate);
		pro.setId_brand(id_brand);
		pro.setIsOnSale(StringUtils.isBlank(isOnSale)?null:isOnSale);
		
		Pager<Product> pager = productService.selectProList(pro, pageNo, pageSize);
		if(pager != null && pager.getResultList() != null && pager.getResultList().size() > 0){
			  list = pager.getResultList();
			  for(Product product:list){
				  Category cate = categoryService.getCateById(product.getId_cate());
				  if(cate != null)product.setCateName(cate.getName());
				  Brand brand = brandService.getBrandById(product.getId_brand());
				  if(brand != null)product.setBrandName(brand.getName());
			  }
		      pageInfo = PageInfo.setPage(pager.getPageNo(), pager.getPageSize(), pager.getPageCount(), pager.getRowCount());
		}
		return "list";
		
	}
	
	public String cate_select(){
		
		return "cate_select";
	}
	
	public String brand_select(){
		
		return "brand_select";
	}

	public String jbxx() throws Exception{
		
		if(id != null){
			product = productService.getProById(id);
			if(product != null){
				Category cate = categoryService.getCateById(product.getId_cate());
				if(cate != null)product.setCateName(cate.getName());
				Brand brand = brandService.getBrandById(product.getId_brand());
				if(brand != null)product.setBrandName(brand.getName());
			}
		}
		
		return "jbxx";
	}
	
	public String info() throws Exception{
		
		if(id != null){
			proInfo = productService.getProInfoByProId(id);
		}
	
		return "info";
	}
	
	public String attr() throws Exception{
		
		if(id == null)return "attr";
		Product product = productService.getProById(id);
		if(product == null)return "attr";
		
	    attrList = categoryService.selectCateAttriListByCateId(product.getId_cate());
		if(attrList != null && attrList.size() > 0){
			for(CateAttri cateAttri:attrList){
				BasicAttri basicAttri = basicAttriService.getBasicAttriById(cateAttri.getId_attr());
				if(basicAttri != null){
					cateAttri.setBasicAttr(basicAttri);
					if(StringUtils.isNoneBlank(basicAttri.getShowType()) && (basicAttri.getShowType().equals("1") || basicAttri.getShowType().equals("2") || basicAttri.getShowType().equals("3"))){
						BasicAttriValue basicAttriValue = new BasicAttriValue();
						basicAttriValue.setPid(basicAttri.getId());
						List<BasicAttriValue> list = basicAttriService.getBasicAttriValueList(basicAttriValue);
						if(list != null && list.size() > 0){
							basicAttri.setValueList(list);
						}
					}
				}
			}
		}
		
		List<ProAttr> proAttrList = productService.getProAttrByProId(id);
		if(proAttrList != null && proAttrList.size() > 0){
			 attrMap = new HashMap<String,ProAttr>();
			for(ProAttr proAttr:proAttrList){
				attrMap.put(String.valueOf(proAttr.getId_cateAttr()), proAttr);
			}
		}
		
		return "attr";
	}
	
	public void saveProAttr() throws IOException{
		
		Integer returnCode = 0;
		try{
		
			if(id_pro == null || StringUtils.isBlank(ids)){
				returnCode = -2;
				return;
			}
			String[] valueArr = ids.split(";");
			
			List<ProAttr> list = new ArrayList<ProAttr>();
			
			for(String vs:valueArr){
				if(StringUtils.isBlank(vs))continue;
				String[] vsArr = vs.split("-");
				ProAttr proAttr = new ProAttr();
				list.add(proAttr);
				proAttr.setId_pro(id_pro);
				proAttr.setId_cateAttr(Integer.valueOf(vsArr[0]));
				proAttr.setShowType(vsArr[1]);
				if(vsArr[1].equals("0")){
					proAttr.setAttrValue(vsArr[2]);
				}else if(vsArr[1].equals("1")){
					proAttr.setId_attr(Integer.valueOf(vsArr[2]));
				}else if(vsArr[1].equals("2")){
					proAttr.setId_attr(Integer.valueOf(vsArr[2]));
				}else if(vsArr[1].equals("3")){
					proAttr.setAttrValue(vsArr[2]);
				}
			}
			
		    productService.addProAttrList(list);
			
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().print(returnCode);
		}
		
	}
	
	public void updateBatchProIsOnSale() throws IOException{
		Integer returnCode = 0;
		try{
			
			if(StringUtils.isBlank(ids)){
				returnCode = -2;
				return;
			}
			
			List<Integer> idList = new ArrayList<Integer>();
			String[] idArr = ids.split(",");
			for(String s:idArr){
				if(StringUtils.isBlank(s))continue;
				idList.add(Integer.valueOf(s));
			}
			productService.updateBatchProIsOnSale(idList);
			
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().print(returnCode);
		}
		
	}
	
	public String img() throws Exception{
		
		if(id != null){
             imgList = productService.getProImgListByProId(id);
		}
		
		return "img";
	}
	
	public String price() throws Exception{
		if(id != null){
			price = productService.getProPriceByProId(id);
		}
		
		return "price";
	}
	/**
	 * @return
	 *   -4:编号重复 -3：名称重复  -2:参数错误 -1:服务异常  0:成功  >0 产品id
	 * @throws IOException 
	 * 
	 * */
	public void saveJbxx() throws IOException{
		Integer returnCode = 0;
		try{
			
			if(id_cate == null || id_brand == null || StringUtils.isBlank(name) || StringUtils.isBlank(sn)){
				returnCode = -2;
				return;
			}
			
			Product product = productService.getProduct(name, null);
			if(product != null){
				if(id == null || id.intValue() != product.getId().intValue()){
					returnCode = -3;
					return;
				}
			}
			
			product = productService.getProduct(null, sn);
			if(product != null){
				if(id == null || id.intValue() != product.getId().intValue()){
					returnCode = -4;
					return;
				}
			}
			
			product = new Product();
			product.setId_cate(id_cate);
			product.setId_brand(id_brand);
			product.setName(name);
			product.setSn(sn);
			product.setShortTitle(shortTitle);
			product.setSubTitle(subTitle);
			product.setEnTitle(enTitle);
			product.setRome(rome);
			product.setJianPin(jianPin);
			product.setKeyWords(keyWords);
			product.setIsShow(isShow);
			product.setIsShipping(isShipping);
			product.setDescr(descr);
			product.setIsOnSale(isOnSale);
			product.setSort(sort == null?1:sort);
			
			if(id == null){
				productService.addProduct(product);
			}else{
				product.setId(id);
				productService.updateProById(product);
			}
			
			returnCode = product.getId();
		
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().print(returnCode);
		}
	}
	
	public void delPro() throws IOException{
		
		Integer returnCode = 0;
		try{
			if(id == null){
				returnCode = -2;
				return;
			}
			
			productService.deleteProById(id);
			
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().print(returnCode);
		}
		
	}
	
	public void saveInfo() throws IOException{
		Integer returnCode = 0;
		try{
			proInfo = productService.getProInfoByProId(id_pro);
			if(proInfo == null)proInfo = new ProInfo();
			proInfo.setId_pro(id_pro);
			proInfo.setProvider(provider);
			proInfo.setDescr(descr);
			proInfo.setFeature(feature);
			proInfo.setProShow(proShow);
			proInfo.setAreaIntroduce(areaIntroduce);
			proInfo.setQualification(qualification);
			proInfo.setUseMethod(useMethod);
			proInfo.setBrandCulture(brandCulture);
			proInfo.setHonor(honor);
			proInfo.setProviderIntroduce(providerIntroduce);
			
			if(proInfo.getId() == null){
				productService.addProInfo(proInfo);
			}else{
				productService.updateProInfoByProId(proInfo);
			}
		
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().print(returnCode);
		}
	}
	
	public void savePrice() throws IOException{
		Integer returnCode = 0;
		try{
			
			if(markPrice == null || shopPrice == null){
				returnCode = -2;
				return;
			}
			
			price = productService.getProPriceByProId(id_pro);
			if(price == null)price = new ProPrice();
			
			price.setMarkPrice(markPrice);
			price.setShopPrice(shopPrice);
			price.setActivePrice(activePrice);
			price.setId_pro(id_pro);
			
			if(price.getId() == null){
				productService.addProPrice(price);
			}else{
				productService.updateProPriceByProId(price);
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
			 
			  if(id_pro == null){
				  returnCode = -2;
				  return;
			  }
			  
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
		                	       
		                	        path = Constans.PATH_UPLOAD_IMG_PRO+id_pro+"/"+new SimpleDateFormat(Constans.DATE_FORMATE).format(new Date())+"_big."+suffixName;
		                	        
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
		                           
		                           
		                           ProImg proImg = new ProImg();
		                           proImg.setId_pro(id_pro);
		                           proImg.setPath_img(path);
		                           proImg.setSort(sort==null?1:sort);
		                           if(id != null){
		                        	   proImg.setId(id);
		                        	   productService.updateProImgById(proImg);
		                           }else{
		                        	   productService.addProImg(proImg);
		                           }
		                           data.put("id", proImg.getId());
		                           
		                    	}
		                    }
		                    
		                }
		            }
		        }
		        
		        String path_mid = ConfigReader.getPath_pic_upload()+"/"+path.replace("big", "mid");
		        String path_small = ConfigReader.getPath_pic_upload()+"/"+path.replace("big", "small");
		        
		        Thumbnail.getThumbnail(ConfigReader.getPath_pic_upload()+"/"+path, path_mid, Constans.SIZE_WIDTH_MID_THUMBNAIL, Constans.SIZE_HEIGHT_MID_THUMBNAIL);
		        Thumbnail.getThumbnail(ConfigReader.getPath_pic_upload()+"/"+path, path_small, Constans.SIZE_WIDTH_SMALL_THUMBNAIL, Constans.SIZE_HEIGHT_SMALL_THUMBNAIL);
		        
		}catch(Exception e){
			returnCode = -1;
		}finally{
			
			 if(fis != null){
				 fis.close();
			 }
			 if(fos != null){
				 fos.flush();
				 fos.close();
			 }
			 
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 data.put("returnCode", returnCode);
			 response.getWriter().print(data.toString());
		}

	}
	
	public void saveImgSort() throws IOException{
		
		Integer returnCode = 0;
		try{
		
			if(id_pro == null || StringUtils.isBlank(ids)){
				returnCode = -2;
				return;
			}
			
			String[] idArr = ids.split(",");
			
			List<ProImg> list = new ArrayList<ProImg>();
			
			int i = 1;
			for(String s:idArr){
				if(StringUtils.isBlank(s))continue;
				ProImg img = new ProImg();
				img.setId_pro(id_pro);
	            img.setId(Integer.valueOf(s));
	            img.setSort(i++);
				list.add(img);
			}
			
			productService.updateProImgListById(list);
		
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().print(returnCode);
		}
	
	}
	
	public void delImg() throws IOException{
		
		Integer returnCode = 0;
		try{
		
			if(id == null){
				returnCode = -2;
				return;
			}
			
			productService.deleteProImgById(id);
			
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

	public Integer getId_cate() {
		return id_cate;
	}

	public void setId_cate(Integer idCate) {
		id_cate = idCate;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public Integer getId_brand() {
		return id_brand;
	}

	public void setId_brand(Integer idBrand) {
		id_brand = idBrand;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getEnTitle() {
		return enTitle;
	}

	public void setEnTitle(String enTitle) {
		this.enTitle = enTitle;
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

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getPath_img() {
		return path_img;
	}

	public void setPath_img(String pathImg) {
		path_img = pathImg;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getIsOnSale() {
		return isOnSale;
	}

	public void setIsOnSale(String isOnSale) {
		this.isOnSale = isOnSale;
	}

	public String getIsShipping() {
		return isShipping;
	}

	public void setIsShipping(String isShipping) {
		this.isShipping = isShipping;
	}

	public Date getOnOrOffSaleTime() {
		return onOrOffSaleTime;
	}

	public void setOnOrOffSaleTime(Date onOrOffSaleTime) {
		this.onOrOffSaleTime = onOrOffSaleTime;
	}

	public Date getOnSaleStartTime() {
		return onSaleStartTime;
	}

	public void setOnSaleStartTime(Date onSaleStartTime) {
		this.onSaleStartTime = onSaleStartTime;
	}

	public Date getOnSaleEndTime() {
		return onSaleEndTime;
	}

	public void setOnSaleEndTime(Date onSaleEndTime) {
		this.onSaleEndTime = onSaleEndTime;
	}

	public Date getOffSaleStartTime() {
		return offSaleStartTime;
	}

	public void setOffSaleStartTime(Date offSaleStartTime) {
		this.offSaleStartTime = offSaleStartTime;
	}

	public Date getOffSaleEndTime() {
		return offSaleEndTime;
	}

	public void setOffSaleEndTime(Date offSaleEndTime) {
		this.offSaleEndTime = offSaleEndTime;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Product> getList() {
		return list;
	}

	public void setList(List<Product> list) {
		this.list = list;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getId_pro() {
		return id_pro;
	}

	public void setId_pro(Integer idPro) {
		id_pro = idPro;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getProShow() {
		return proShow;
	}

	public void setProShow(String proShow) {
		this.proShow = proShow;
	}

	public String getAreaIntroduce() {
		return areaIntroduce;
	}

	public void setAreaIntroduce(String areaIntroduce) {
		this.areaIntroduce = areaIntroduce;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getUseMethod() {
		return useMethod;
	}

	public void setUseMethod(String useMethod) {
		this.useMethod = useMethod;
	}

	public String getBrandCulture() {
		return brandCulture;
	}

	public void setBrandCulture(String brandCulture) {
		this.brandCulture = brandCulture;
	}

	public String getHonor() {
		return honor;
	}

	public void setHonor(String honor) {
		this.honor = honor;
	}

	public String getProviderIntroduce() {
		return providerIntroduce;
	}

	public void setProviderIntroduce(String providerIntroduce) {
		this.providerIntroduce = providerIntroduce;
	}

	public ProInfo getProInfo() {
		return proInfo;
	}

	public void setProInfo(ProInfo proInfo) {
		this.proInfo = proInfo;
	}

	public Double getMarkPrice() {
		return markPrice;
	}

	public void setMarkPrice(Double markPrice) {
		this.markPrice = markPrice;
	}

	public Double getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(Double shopPrice) {
		this.shopPrice = shopPrice;
	}

	public Double getActivePrice() {
		return activePrice;
	}

	public void setActivePrice(Double activePrice) {
		this.activePrice = activePrice;
	}

	public ProPrice getPrice() {
		return price;
	}

	public void setPrice(ProPrice price) {
		this.price = price;
	}

	public List<ProImg> getImgList() {
		return imgList;
	}

	public void setImgList(List<ProImg> imgList) {
		this.imgList = imgList;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<CateAttri> getAttrList() {
		return attrList;
	}

	public void setAttrList(List<CateAttri> attrList) {
		this.attrList = attrList;
	}

	public Map<String, ProAttr> getAttrMap() {
		return attrMap;
	}

	public void setAttrMap(Map<String, ProAttr> attrMap) {
		this.attrMap = attrMap;
	}

    
}
