package com.mystore.business.dao;

import java.util.List;

import com.mystore.business.dto.Comment;
import com.mystore.business.dto.CommentReply;

public interface CommentMapper {
	
	/**
	 * 根据id查询商品评论
	 * 
	 * */
	public Comment getProCommentById(Integer id);
	
	/**
	 * 根据条件查询商品评论数量
	 * 
	 * */
	public Integer getProCommentCount(Comment comment);
	
	/**
	 * 根据条件查询商品评论列表
	 * 
	 * */
	public List<Comment> getProCommentList(Comment comment);
	
	/**
	 * 修改评论状态
	 * 
	 * */
	public void updateProCommentStatus(Comment comment);
	
	/**
	 * 增加评论回复
	 * 
	 * */
	public int saveCommentReply(CommentReply commentReply);

}
