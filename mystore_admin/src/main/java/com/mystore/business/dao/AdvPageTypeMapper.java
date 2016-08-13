package com.mystore.business.dao;

import java.util.List;
import java.util.Map;

import com.mystore.business.dto.AdvPageType;

public interface AdvPageTypeMapper {
	
	
	public void addAdvPageType(AdvPageType advPageType);
	
	public void updateAdvPageTypeById(AdvPageType advPageType);
	
	public void deleteAdvPageTypeById(Integer id);
	
    public AdvPageType getAdvPageTypeById(Integer id);
    
    public AdvPageType getAdvPageTypeByBh(String bh);
    
    public List<AdvPageType> getAdvPageTypeListByPid(Integer pid);
    
    public Integer selectCountByPid(Integer pid);
    
    public AdvPageType getAdvPageTypeByPidAndName(Map<String,Object> map);
    
}
