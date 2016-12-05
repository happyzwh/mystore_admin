package com.mystore.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.common.Pager;
import com.mystore.business.dao.InfoMapper;
import com.mystore.business.dto.Info;
import com.mystore.business.dto.InfoCate;
import com.mystore.business.service.InfoService;

@Service("infoService")
public class InfoServiceImpl implements InfoService{
	
	@Autowired
	private InfoMapper infoMapper;
	

	@Override
	public void addInfoCate(InfoCate infoCate) {
		// TODO Auto-generated method stub
		if(infoCate != null){
			infoMapper.addInfoCate(infoCate);
		}
	}

	@Override
	public void updateInfoCateById(InfoCate infoCate) {
		// TODO Auto-generated method stub
		if(infoCate != null){
			infoMapper.updateInfoCateById(infoCate);
		}
	}

	@Override
	public void deletInfoCateById(Integer id) {
		// TODO Auto-generated method stub
		if(id != null){
			this.deleteInfoBycateId(id);
			infoMapper.deletInfoCateById(id);
		}
	}

	@Override
	public InfoCate getInfoCateById(Integer id) {
		// TODO Auto-generated method stub
		if(id != null){
			return infoMapper.getInfoCateById(id);
		}
		return null;
	}

	@Override
	public List<InfoCate> getInfoCateListByPid(Integer pid) {
		// TODO Auto-generated method stub
		if(pid != null){
			return infoMapper.getInfoCateListByPid(pid);
		}
		return null;
	}

	@Override
	public Integer selectCountByPid(Integer pid) {
		// TODO Auto-generated method stub
		if(pid != null){
			return infoMapper.selectCountByPid(pid);
		}
		return 0;
	}

	@Override
	public InfoCate getInfoCateByPidAndName(Integer pid, String name) {
		// TODO Auto-generated method stub
		if(pid != null && StringUtils.isNoneBlank(name)){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("pid", pid);
			map.put("name", name);
			return infoMapper.getInfoCateByPidAndName(map);
		}
		return null;
	}

	@Override
	public InfoCate getInfoCateByBh(String bh) {
		// TODO Auto-generated method stub
		if(StringUtils.isNoneBlank(bh)){
			return infoMapper.getInfoCateByBh(bh);
		}
		return null;
	}

	@Override
	public void addInfo(Info info) {
		// TODO Auto-generated method stub
		if(info != null){
			infoMapper.addInfo(info);
		}
	}

	@Override
	public void updateInfoById(Info info) {
		// TODO Auto-generated method stub
		if(info != null){
			infoMapper.updateInfoById(info);
		}
	}

	@Override
	public void deleteInfoById(Integer id) {
		// TODO Auto-generated method stub
		if(id != null){
			infoMapper.deleteInfoById(id);
		}
	}

	@Override
	public void deleteInfoBycateId(Integer cateId) {
		// TODO Auto-generated method stub
		if(cateId != null){
			infoMapper.deleteInfoBycateId(cateId);
		}
	}

	public Info getInfoById(Integer id){
		if(id != null){
			return infoMapper.getInfoById(id);
		}
		return null;
	}
	
	@Override
	public Pager<Info> getInfoList(Info info,Integer pageNum,Integer pageSize){
		// TODO Auto-generated method stub
		if(pageNum != null && pageNum == 0)pageNum =1;
		if(pageSize != null && pageSize == 0)pageSize = 10;
		if(pageNum != null && pageSize != null){
			int pageIndex = (pageNum - 1)*pageSize;
			info.setPageIndex(pageIndex);
			info.setPageSize(pageSize);
		}
		Pager<Info> pager = new Pager<Info>();
		if(pageNum != null && pageSize != null){
			pager.setPageNo(pageNum);
			pager.setPageSize(pageSize);
		}
		int count = infoMapper.getInfoCount(info);
		if(count == 0)return null;
		pager.setRowCount(count);
		if(pageNum != null && pageSize != null){
			pager.setPageCount(count%pageSize==0?(count/pageSize):(count/pageSize+1));
		}
		List<Info> list = infoMapper.getInfoList(info);
		pager.setResultList(list);
		return pager;
	}

	@Override
	public Integer getInfoCount(Info info) {
		// TODO Auto-generated method stub
		if(info != null){
			infoMapper.getInfoCount(info);
		}
		return 0;
	}
	
	@Override
	public void updateInfoListById(List<Info> list){
		if(list != null && list.size() > 0){
			for(Info info:list){
				this.updateInfoById(info);
			}
		}
	}
	

}
