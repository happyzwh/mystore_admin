package com.mystore.business.dao;

import java.util.List;
import java.util.Map;

import com.mystore.business.dto.AdvProModule;

public interface AdvProModuleMapper {
	
	public void addAdvProModule(AdvProModule advProModule);
	
	public void updateAdvProModuleById(AdvProModule advProModule);
	
	public void deleteAdvProModuleById(Integer id);
	
	public void deleteAdvProModuleByPId(Integer pid);
	
	public AdvProModule getAdvProModuleById(Integer id);
	
	public List<AdvProModule> getAdvProModuleListByPid(Integer pid);
	
	public AdvProModule getAdvProModuleByPidAndProId(Map<String,Object> map);

}
