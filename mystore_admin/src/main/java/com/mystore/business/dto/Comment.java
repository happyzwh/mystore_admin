package com.mystore.business.dto;

import java.util.Date;
import java.util.List;

public class Comment extends BasicDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private Integer id;
	
	private Integer id_pro;
	
	private String productName;
	
	private Integer id_user;
	
	private String userName;

	private Integer score;
	
	private String content;
	
	private String isreply;
	
	private String status;
	
	private Date createDate;
	
	private Date lastDate;
	
	private Integer type;
	
	private List<CommentReply> replys;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_pro() {
		return id_pro;
	}

	public void setId_pro(Integer id_pro) {
		this.id_pro = id_pro;
	}

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public List<CommentReply> getReplys() {
		return replys;
	}

	public void setReplys(List<CommentReply> replys) {
		this.replys = replys;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
		
}
