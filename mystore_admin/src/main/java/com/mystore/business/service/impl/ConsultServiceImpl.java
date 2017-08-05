package com.mystore.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.common.Pager;
import com.mystore.business.dao.ConsultMapper;
import com.mystore.business.dto.Consult;
import com.mystore.business.dto.ConsultReply;
import com.mystore.business.service.ConsultService;

@Service
public class ConsultServiceImpl implements ConsultService{
	
	@Autowired
	private ConsultMapper consultMapper;

	@Override
	public Consult getProConsultById(Integer id) {
		// TODO Auto-generated method stub
		return consultMapper.getProConsultById(id);
	}

	@Override
	public Pager<Consult> getProConsultList(Consult consult, Integer pageNum,Integer pageSize) {
		// TODO Auto-generated method stub
		if(pageNum == null || (pageNum != null && pageNum == 0))pageNum =1;
		if(pageSize == null || (pageSize != null && pageSize == 0))pageSize = 10;
		if(pageNum != null && pageSize != null){
			int pageIndex = (pageNum - 1)*pageSize;
			consult.setPageIndex(pageIndex);
			consult.setPageSize(pageSize);
		}
		Pager<Consult> pager = new Pager<Consult>();
		if(pageNum != null && pageSize != null){
			pager.setPageNo(pageNum);
			pager.setPageSize(pageSize);
		}
		int count = consultMapper.getProConsultCount(consult);
		if(count == 0)return null;
		pager.setRowCount(count);
		if(pageNum != null && pageSize != null){
			pager.setPageCount(count%pageSize==0?(count/pageSize):(count/pageSize+1));
		}
		List<Consult> list = consultMapper.getProConsultList(consult);
		pager.setResultList(list);
		return pager;
	}

	@Override
	public void updateProConsultStatus(Consult consult,ConsultReply consultReply) {
		// TODO Auto-generated method stub
		consultMapper.updateProConsultStatus(consult);
		if(consultReply != null){
			consultMapper.saveConsultReply(consultReply);
		}
	}

}
