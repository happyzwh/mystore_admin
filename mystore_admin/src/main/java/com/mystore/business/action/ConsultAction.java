package com.mystore.business.action;

import java.util.List;

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
import com.mystore.business.dto.Consult;
import com.mystore.business.dto.ConsultReply;
import com.mystore.business.service.ConsultService;

@Controller("consultAction")
@Scope("prototype")
public class ConsultAction extends BaseAction{
	
	private Integer id;
	
	private String userName;
	
	private String productName;
	
	private String isreply;
	
	private String status;
	
	private String type;
	
	private String content;
	
	private List<Consult> list;
	
	private Consult consult;
	
	@Autowired
	private ConsultService consultService;
	
	public String list(){
		
		Consult consult = new Consult();
		consult.setUserName(StringUtils.isBlank(userName)?null:userName);
		consult.setProductName(StringUtils.isBlank(productName)?null:productName);
		consult.setType(StringUtils.isBlank(type)?null:type);
		consult.setIsreply(StringUtils.isBlank(isreply)?null:isreply);
		consult.setStatus(StringUtils.isBlank(status)?null:status);
		
		Pager<Consult> pager = consultService.getProConsultList(consult, pageNo, pageSize);
		if(pager != null && pager.getResultList() != null && pager.getResultList().size() > 0){
			  list = pager.getResultList();
			  pageInfo = PageInfo.setPage(pager.getPageNo(), pager.getPageSize(), pager.getPageCount(), pager.getRowCount());
		}
		
		return "list";
		
	}
	
	public String detail(){
		
		if(id != null){
			consult = consultService.getProConsultById(id);
		}
		
		return "detail";
	}
	
	public void audit() throws Exception{
		
		int returnCode = 0;
		
		try{
		
			if(id == null || StringUtils.isBlank(status)){
				returnCode = -2;
				return;
			}
			
			Consult consult = new Consult();
			consult.setId(id);
			consult.setStatus(status);
			
			ConsultReply  consultReply = null;
			if(StringUtils.isNotBlank(content)){
				 consult.setIsreply("1");
				
				 Object o = ServletActionContext.getRequest().getSession().getAttribute(Constans.KEY_SESSION);
				 UserSession userSession = (UserSession)o;
				   
				 consultReply = new ConsultReply();
				 consultReply.setPid(id);
				 consultReply.setContent(content);
				 consultReply.setStatus("1");
				 consultReply.setId_adm_user(userSession.getUser().getId());
				 consultReply.setUserName(userSession.getUser().getUserName());
			}
			
			consultService.updateProConsultStatus(consult, consultReply);
			
			returnCode = 1;
			
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Consult> getList() {
		return list;
	}

	public void setList(List<Consult> list) {
		this.list = list;
	}

	public Consult getConsult() {
		return consult;
	}

	public void setConsult(Consult consult) {
		this.consult = consult;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsreply() {
		return isreply;
	}

	public void setIsreply(String isreply) {
		this.isreply = isreply;
	}

}
