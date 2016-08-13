package com.mystore.business.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.PageInfo;
import com.mystore.business.common.Pager;
import com.mystore.business.dto.BasicAttri;
import com.mystore.business.dto.BasicAttriValue;
import com.mystore.business.service.BasicAttriService;

@Controller("basicAttriAction")
@Scope("prototype")
public class BasicAttriAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private BasicAttriService basicAttriService;
	
	private Integer id;
	
	private Integer pid;
	
	private String name;
	
	private String value;
	
	private String rome;
	
	private String jianPin;
	
	private String enName;
	
	private String descr;
	
	private Integer sort;
	
	private String showType;
	
	private List<BasicAttri> attriList;
	
	private List<BasicAttriValue> valueList;
	
	private BasicAttri basicAttri;
	
	private BasicAttriValue basicAttriValue;
	
	public String list() throws Exception{
		
		
		BasicAttri basicAttri = new  BasicAttri();
		basicAttri.setName(StringUtils.isNoneBlank(name)?name:null);
		
		Pager<BasicAttri> pager = basicAttriService.getBasicAttriList(basicAttri, pageNo, pageSize);
		if(pager != null && pager.getResultList() != null && pager.getResultList().size() > 0){
			attriList = pager.getResultList();
			pageInfo = PageInfo.setPage(pager.getPageNo(), pager.getPageSize(), pager.getPageCount(), pager.getRowCount());
		}
		
		return "list";
		
	}

	public String add(){
		   
	    return "add";
		   
    }
	
	public String edit() throws Exception{
		
		basicAttri = basicAttriService.getBasicAttriById(id);
		   
	    return "edit";
		   
    }
	
	public void save() throws Exception{
		  
		  int returnCode =0;
		  try{
			  
			  if(StringUtils.isBlank(name) || StringUtils.isBlank(showType)){
				  returnCode = -2;
				  return;
			  }
			  
			  BasicAttri basicAttri = basicAttriService.getBasicAttriByName(name);
			  
			  if(basicAttri != null){
				  if(id == null){
					  returnCode = -3;
					  return;
				  }else if( id.intValue() != basicAttri.getId().intValue() ){
					  returnCode = -3;
					  return;
				  }
			  }
			  
			  basicAttri = new  BasicAttri();
			  basicAttri.setName(name);
			  basicAttri.setRome(rome);
			  basicAttri.setJianPin(jianPin);
			  basicAttri.setEnName(enName);
			  basicAttri.setShowType(showType);
			  basicAttri.setSort(sort == null?1:sort);
			  basicAttri.setDescr(descr);
			  if(id == null){
				  basicAttriService.addBasicAttri(basicAttri);
			  }else{
				  basicAttri.setId(id);
				  basicAttriService.updateBasicAttriById(basicAttri);
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
		  
		  int returnCode =0;
		  try{
			  
			  if( id == null ){
				  returnCode = -2;
				  return;
			  }
			  
			  basicAttriService.deleteBasicAttriById(id);
			  
		  }catch(Exception e){
			  returnCode = -1;
		  }finally{
			  HttpServletResponse response=ServletActionContext.getResponse();
			  response.setContentType("text/html;charset=UTF-8");
			  response.getWriter().print(returnCode);
		  }
	}
	
	public String valueList() throws Exception{
		
		BasicAttriValue basicAttriValue = new BasicAttriValue();
		basicAttriValue.setPid(pid);
		basicAttriValue.setValue(StringUtils.isNoneBlank(value)?value:null);
		
		valueList = basicAttriService.getBasicAttriValueList(basicAttriValue);
		
		return "valueList";
		
	}
	
	public String valueAdd() throws Exception{
		
		return "valueAdd";
		
	}
	
	public String valueEdit() throws Exception{
		
		basicAttriValue = basicAttriService.getBasicAttriValueById(id);
		
		return "valueEdit";
		
	}
	
    public void valueDelete() throws Exception{
		
    	  int returnCode =0;
		  try{
			  
			  if( id == null ){
				  returnCode = -2;
				  return;
			  }
			  
			  basicAttriService.deleteBasicAttriValueById(id);
			  
		  }catch(Exception e){
			  returnCode = -1;
		  }finally{
			  HttpServletResponse response=ServletActionContext.getResponse();
			  response.setContentType("text/html;charset=UTF-8");
			  response.getWriter().print(returnCode);
		  }
		
	}
    
    public void valueSave() throws Exception{
		
  	      int returnCode =0;
		  try{
			  
			  if( pid == null || StringUtils.isBlank(value)){
				  returnCode = -2;
				  return;
			  }
			  
			  BasicAttriValue basicAttriValue  = basicAttriService.getBasicAttriValueByValue(pid, value);
			  
			  if(basicAttriValue != null){
				  if(id == null){
					  returnCode = -3;
					  return;
				  }else if( id.intValue() != basicAttriValue.getId().intValue() ){
					  returnCode = -3;
					  return;
				  }
			  }
			  
			  basicAttriValue = new BasicAttriValue();
			  basicAttriValue.setPid(pid);
			  basicAttriValue.setValue(value);
			  basicAttriValue.setSort(sort == null?1:sort);
			  if(id == null){
				  basicAttriService.addBasicAttriValue(basicAttriValue);
			  }else{
				  basicAttriValue.setId(id);
				  basicAttriService.updateBasicAttriValueById(basicAttriValue);
			  }
			  
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public List<BasicAttri> getAttriList() {
		return attriList;
	}

	public void setAttriList(List<BasicAttri> attriList) {
		this.attriList = attriList;
	}

	public List<BasicAttriValue> getValueList() {
		return valueList;
	}

	public void setValueList(List<BasicAttriValue> valueList) {
		this.valueList = valueList;
	}

	public BasicAttri getBasicAttri() {
		return basicAttri;
	}

	public void setBasicAttri(BasicAttri basicAttri) {
		this.basicAttri = basicAttri;
	}

	public BasicAttriValue getBasicAttriValue() {
		return basicAttriValue;
	}

	public void setBasicAttriValue(BasicAttriValue basicAttriValue) {
		this.basicAttriValue = basicAttriValue;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
