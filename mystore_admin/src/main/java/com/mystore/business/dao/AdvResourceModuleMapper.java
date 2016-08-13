package com.mystore.business.dao;

import java.util.List;

import com.mystore.business.dto.AdvResourceModule;

public interface AdvResourceModuleMapper {
	
	public void addAdvResourceModule(AdvResourceModule advResourceModule);
	
	public void updateAdvResourceModuleById(AdvResourceModule advResourceModule);
	
	public void deletaAdvResourceModuleById(Integer id);
	
	public void deleteAdvResourceModuleByPid(Integer pid);
	
	public AdvResourceModule getAdvResourceModuleById(Integer id);
	
	public List<AdvResourceModule> getAdvResourceModuleListByPid(Integer pid);

}
