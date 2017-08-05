package com.mystore.business.dao;

import java.util.List;

import com.mystore.business.dto.Consult;
import com.mystore.business.dto.ConsultReply;

public interface ConsultMapper {
	
	/**
	 * 根据id查询商品咨询
	 * 
	 * */
	public Consult getProConsultById(Integer id);
	
	/**
	 * 根据条件查询商品咨询数量
	 * 
	 * */
	public Integer getProConsultCount(Consult consult);
	
	/**
	 * 根据条件查询商品咨询列表
	 * 
	 * */
	public List<Consult> getProConsultList(Consult consult);
	
	/**
	 * 修改咨询状态
	 * 
	 * */
	public void updateProConsultStatus(Consult consult);
	
	/**
	 * 增加咨询回复
	 * 
	 * */
	public int saveConsultReply(ConsultReply consultReply);
}
