package com.mystore.business.service;

import java.util.List;

import com.mystore.business.dto.AdvImg;
import com.mystore.business.dto.AdvPageType;
import com.mystore.business.dto.AdvProModule;
import com.mystore.business.dto.AdvResourceModule;

public interface AdvertiseService {

	public void addAdvPageType(AdvPageType advPageType);
	
	public void updateAdvPageTypeById(AdvPageType advPageType);
	
	public void deleteAdvPageTypeById(Integer id);
	
    public AdvPageType getAdvPageTypeById(Integer id);
    
    public AdvPageType getAdvPageTypeByBh(String bh);
    
    public List<AdvPageType> getAdvPageTypeListByPid(Integer pid);
    
    public Integer selectCountByPid(Integer pid);
    
    public AdvPageType getAdvPageTypeByPidAndName(Integer pid,String name);
    
    public void addAdvProModule(AdvProModule advProModule);
	
	public void updateAdvProModuleById(AdvProModule advProModule);
	
	public void updateAdvProModuleListById(List<AdvProModule> advProModuleList);
	
	public void deleteAdvProModuleById(Integer id);
	
	public void deleteAdvProModuleByPId(Integer pid);
	
	public AdvProModule getAdvProModuleById(Integer id);
	
	public List<AdvProModule> getAdvProModuleListByPid(Integer pid);
	
	public void addAdvResourceModule(AdvResourceModule advResourceModule);
	
	public void updateAdvResourceModuleById(AdvResourceModule advResourceModule);
	
	public void deletaAdvResourceModuleById(Integer id);
	
	public void deleteAdvResourceModuleByPid(Integer pid);
	
	public AdvResourceModule getAdvResourceModuleById(Integer id);
	
	public List<AdvResourceModule> getAdvResourceModuleListByPid(Integer pid);
	
	public void addAdvImg(AdvImg advImg);
	
	public void updateAdvImgById(AdvImg advImg);
	
	public void updateAdvImgListById(List<AdvImg> list);
	
	public void deleteAdvImgById(Integer id);
	
	public void deleteAdvImgByPid(Integer pid);
	
	public AdvImg getAdvImgByid(Integer id);
	
	public List<AdvImg> getAdvImgListByPid(Integer pid);
	
	public AdvProModule getAdvProModuleByPidAndProId(Integer pid,Integer proId);
	
	public void updateAdvResModuleListById(List<AdvResourceModule> list);
	
}
