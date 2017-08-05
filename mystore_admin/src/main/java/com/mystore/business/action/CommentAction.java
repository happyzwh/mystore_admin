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
import com.mystore.business.dto.Comment;
import com.mystore.business.dto.CommentReply;
import com.mystore.business.dto.Consult;
import com.mystore.business.dto.ConsultReply;
import com.mystore.business.service.CommentService;


@Controller("commentAction")
@Scope("prototype")
public class CommentAction extends BaseAction{
	
	private Integer id;
	
	private String userName;
	
	private String productName;
	
	private String status;
	
	private String content;
	
	private List<Comment> list;
	
	private String isreply;
	
	@Autowired
	private CommentService commentService;
	
	private Comment comment;
	
	public String list(){
		
		Comment comment = new Comment();
		comment.setUserName(StringUtils.isBlank(userName)?null:userName);
		comment.setProductName(StringUtils.isBlank(productName)?null:productName);
		comment.setStatus(StringUtils.isBlank(status)?null:status);
		comment.setIsreply(StringUtils.isBlank(isreply)?null:isreply);
		
		Pager<Comment> pager = commentService.getProCommentList(comment, pageNo, pageSize);
		if(pager != null && pager.getResultList() != null && pager.getResultList().size() > 0){
			  list = pager.getResultList();
			  pageInfo = PageInfo.setPage(pager.getPageNo(), pager.getPageSize(), pager.getPageCount(), pager.getRowCount());
		}
		
		return "list";
	}

	public String detail(){
		
		if(id != null){
			comment = commentService.getProCommentById(id);
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
			
			Comment comment = new Comment();
			comment.setId(id);
			comment.setStatus(status);
			
			CommentReply  commentReply = null;
			if(StringUtils.isNotBlank(content)){
				comment.setIsreply("1");
				
				 Object o = ServletActionContext.getRequest().getSession().getAttribute(Constans.KEY_SESSION);
				 UserSession userSession = (UserSession)o;
				   
				 commentReply = new CommentReply();
				 commentReply.setPid(id);
				 commentReply.setContent(content);
				 commentReply.setStatus("1");
				 commentReply.setId_adm_user(userSession.getUser().getId());
				 commentReply.setUserName(userSession.getUser().getUserName());
			}
			
			commentService.updateProCommentStatus(comment, commentReply);
			
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

	public List<Comment> getList() {
		return list;
	}

	public void setList(List<Comment> list) {
		this.list = list;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
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

	public String getIsreply() {
		return isreply;
	}

	public void setIsreply(String isreply) {
		this.isreply = isreply;
	}
	
}
