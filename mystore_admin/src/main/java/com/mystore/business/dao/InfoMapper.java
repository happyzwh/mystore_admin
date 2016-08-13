package com.mystore.business.dao;

import java.util.List;
import java.util.Map;

import com.mystore.business.dto.Info;
import com.mystore.business.dto.InfoCate;

public interface InfoMapper {

	public void addInfoCate(InfoCate infoCate);
	
	public void updateInfoCateById(InfoCate infoCate);
	
	public void deletInfoCateById(Integer id);
	
	public InfoCate getInfoCateById(Integer id);
	
	public List<InfoCate> getInfoCateListByPid(Integer pid);
	
    public Integer selectCountByPid(Integer pid);
	
	public InfoCate getInfoCateByPidAndName(Map<String,Object> map);
	
	public InfoCate getInfoCateByBh(String bh);
	
	public void addInfo(Info info);
	
	public void updateInfoById(Info info);
	
	public void deleteInfoById(Integer id);
	
	public void deleteInfoBycateId(Integer cateId);
	
	public List<Info> getInfoList(Info info);
	
	public Info getInfoById(Integer id);
	
	public Integer getInfoCount(Info info);
	

	
}
