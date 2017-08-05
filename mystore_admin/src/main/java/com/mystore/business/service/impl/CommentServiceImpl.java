package com.mystore.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.common.Pager;
import com.mystore.business.dao.CommentMapper;
import com.mystore.business.dto.Comment;
import com.mystore.business.dto.CommentReply;
import com.mystore.business.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public Comment getProCommentById(Integer id) {
		// TODO Auto-generated method stub
		return commentMapper.getProCommentById(id);
	}

	@Override
	public Pager<Comment> getProCommentList(Comment comment,Integer pageNum,Integer pageSize) {
		// TODO Auto-generated method stub
		if(pageNum == null || (pageNum != null && pageNum == 0))pageNum =1;
		if(pageSize == null || (pageSize != null && pageSize == 0))pageSize = 10;
		if(pageNum != null && pageSize != null){
			int pageIndex = (pageNum - 1)*pageSize;
			comment.setPageIndex(pageIndex);
			comment.setPageSize(pageSize);
		}
		Pager<Comment> pager = new Pager<Comment>();
		if(pageNum != null && pageSize != null){
			pager.setPageNo(pageNum);
			pager.setPageSize(pageSize);
		}
		int count = commentMapper.getProCommentCount(comment);
		if(count == 0)return null;
		pager.setRowCount(count);
		if(pageNum != null && pageSize != null){
			pager.setPageCount(count%pageSize==0?(count/pageSize):(count/pageSize+1));
		}
		List<Comment> list = commentMapper.getProCommentList(comment);
		pager.setResultList(list);
		return pager;
	}

	@Override
	public void updateProCommentStatus(Comment comment,CommentReply commentReply) {
		// TODO Auto-generated method stub
		this.commentMapper.updateProCommentStatus(comment);
		if(commentReply != null){
			this.commentMapper.saveCommentReply(commentReply);
		}
	}

}
