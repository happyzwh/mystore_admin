package com.mystore.business.service;

import com.mystore.business.common.Pager;
import com.mystore.business.dto.Consult;
import com.mystore.business.dto.ConsultReply;

public interface ConsultService {
	
	/**
	 * 根据id查询商品咨询
	 * 
	 * */
	public Consult getProConsultById(Integer id);
	
	/**
	 * 根据条件查询商品咨询列表
	 * 
	 * */
	public Pager<Consult> getProConsultList(Consult consult,Integer pageNo,Integer pageSize);
	
	/**
	 * 修改咨询状态
	 * 
	 * */
	public void updateProConsultStatus(Consult consult,ConsultReply consultReply);

}
