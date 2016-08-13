package com.mystore.business.service;

import java.util.List;

import com.mystore.business.common.Pager;
import com.mystore.business.dto.Info;
import com.mystore.business.dto.InfoCate;

public interface InfoService {
	
	public void addInfoCate(InfoCate infoCate);
	
	public void updateInfoCateById(InfoCate infoCate);
	
	public void deletInfoCateById(Integer id);
	
	public InfoCate getInfoCateById(Integer id);
	
	public List<InfoCate> getInfoCateListByPid(Integer pid);
	
    public Integer selectCountByPid(Integer pid);
	
	public InfoCate getInfoCateByPidAndName(Integer pid, String name);
	
	public InfoCate getInfoCateByBh(String bh);
	
	public void addInfo(Info info);
	
	public void updateInfoById(Info info);
	
	public Info getInfoById(Integer id);
	
	public void deleteInfoById(Integer id);
	
	public void deleteInfoBycateId(Integer cateId);
	
	public Pager<Info> getInfoList(Info info,Integer pageNo,Integer pageSize);
	
	public Integer getInfoCount(Info info);
	
	public void updateInfoListById(List<Info> list);

}
