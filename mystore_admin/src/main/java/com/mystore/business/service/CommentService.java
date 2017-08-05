package com.mystore.business.service;

import com.mystore.business.common.Pager;
import com.mystore.business.dto.Comment;
import com.mystore.business.dto.CommentReply;

public interface CommentService {
	/**
	 * 根据id查询商品评论
	 * 
	 * */
	public Comment getProCommentById(Integer id);
	
	/**
	 * 根据条件查询商品评论列表
	 * 
	 * */
	public Pager<Comment> getProCommentList(Comment comment,Integer pageNo,Integer pageSize);
	
	/**
	 * 修改评论状态
	 * 
	 * */
	public void updateProCommentStatus(Comment comment,CommentReply commentReply);

}
