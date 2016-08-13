package com.mystore.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.dao.AdvImgMapper;
import com.mystore.business.dao.AdvPageTypeMapper;
import com.mystore.business.dao.AdvProModuleMapper;
import com.mystore.business.dao.AdvResourceModuleMapper;
import com.mystore.business.dto.AdvImg;
import com.mystore.business.dto.AdvPageType;
import com.mystore.business.dto.AdvProModule;
import com.mystore.business.dto.AdvResourceModule;
import com.mystore.business.service.AdvertiseService;

@Service("advertiseService")
public class AdvertiseServiceImpl implements AdvertiseService{

	@Autowired
	private AdvPageTypeMapper advPageTypeMapper;
	
	@Autowired
	private AdvProModuleMapper advProModuleMapper;
	
	@Autowired
	private AdvResourceModuleMapper advResourceModuleMapper;
	
	@Autowired
	private AdvImgMapper advImgMapper;
	
	@Override
	public void addAdvImg(AdvImg advImg) {
		// TODO Auto-generated method stub
		if(advImg != null){
			advImgMapper.addAdvImg(advImg);
		}
	}

	@Override
	public void addAdvPageType(AdvPageType advPageType) {
		// TODO Auto-generated method stub
		if(advPageType != null){
			advPageTypeMapper.addAdvPageType(advPageType);
		}
	}

	@Override
	public void addAdvProModule(AdvProModule advProModule) {
		// TODO Auto-generated method stub
		if(advProModule != null){
			advProModuleMapper.addAdvProModule(advProModule);
		}
	}

	@Override
	public void addAdvResourceModule(AdvResourceModule advResourceModule) {
		// TODO Auto-generated method stub
		if(advResourceModule != null){
			advResourceModuleMapper.addAdvResourceModule(advResourceModule);
		}
	}

	@Override
	public void deletaAdvResourceModuleById(Integer id) {
		// TODO Auto-generated method stub
		if(id != null){
			advResourceModuleMapper.deletaAdvResourceModuleById(id);
		}
	}

	@Override
	public void deleteAdvImgById(Integer id) {
		// TODO Auto-generated method stub
		if(id != null){
			advImgMapper.deleteAdvImgById(id);
		}
	}

	@Override
	public void deleteAdvImgByPid(Integer pid) {
		// TODO Auto-generated method stub
		if(pid != null){
			advImgMapper.deleteAdvImgById(pid);
		}
	}

	@Override
	public void deleteAdvPageTypeById(Integer id) {
		// TODO Auto-generated method stub
		if(id != null){
			advPageTypeMapper.deleteAdvPageTypeById(id);
		}
	}

	@Override
	public void deleteAdvProModuleById(Integer id) {
		// TODO Auto-generated method stub
		if(id != null){
			advProModuleMapper.deleteAdvProModuleById(id);
		}
	}

	@Override
	public void deleteAdvProModuleByPId(Integer pid) {
		// TODO Auto-generated method stub
		if(pid != null){
			advProModuleMapper.deleteAdvProModuleByPId(pid);
		}
	}

	@Override
	public void deleteAdvResourceModuleByPid(Integer pid) {
		// TODO Auto-generated method stub
		if(pid != null){
			advResourceModuleMapper.deleteAdvResourceModuleByPid(pid);
		}
	}

	@Override
	public List<AdvImg> getAdvImgListByPid(Integer pid) {
		// TODO Auto-generated method stub
		if(pid != null){
			return advImgMapper.getAdvImgListByPid(pid);
		}
		return null;
	}

	@Override
	public AdvImg getAdvImgByid(Integer id) {
		// TODO Auto-generated method stub
		if(id != null){
			return  advImgMapper.getAdvImgByid(id);
		}
		return null;
	}

	@Override
	public AdvPageType getAdvPageTypeById(Integer id) {
		// TODO Auto-generated method stub
		if(id != null){
			return advPageTypeMapper.getAdvPageTypeById(id);
		}
		return null;
	}

	@Override
	public AdvProModule getAdvProModuleById(Integer id) {
		// TODO Auto-generated method stub
		if(id != null){
			return advProModuleMapper.getAdvProModuleById(id);
		}
		return null;
	}

	@Override
	public List<AdvProModule> getAdvProModuleListByPid(Integer pid) {
		// TODO Auto-generated method stub
		if(pid != null){
			return advProModuleMapper.getAdvProModuleListByPid(pid);
		}
		return null;
	}

	@Override
	public AdvResourceModule getAdvResourceModuleById(Integer id) {
		// TODO Auto-generated method stub
		if(id != null){
			return advResourceModuleMapper.getAdvResourceModuleById(id);
		}
		return null;
	}

	@Override
	public List<AdvResourceModule> getAdvResourceModuleListByPid(Integer pid) {
		// TODO Auto-generated method stub
		if(pid != null){
			return  advResourceModuleMapper.getAdvResourceModuleListByPid(pid);
		}
		return null;
	}

	@Override
	public void updateAdvImgById(AdvImg advImg) {
		// TODO Auto-generated method stub
		if(advImg != null && advImg.getId() != null){
			advImgMapper.updateAdvImgById(advImg);
		}
	}

	@Override
	public void updateAdvPageTypeById(AdvPageType advPageType) {
		// TODO Auto-generated method stub
		if(advPageType != null && advPageType.getId() != null){
			advPageTypeMapper.updateAdvPageTypeById(advPageType);
		}
	}

	@Override
	public void updateAdvProModuleById(AdvProModule advProModule) {
		// TODO Auto-generated method stub
		if(advProModule != null && advProModule.getId() != null){
			advProModuleMapper.updateAdvProModuleById(advProModule);
		}
	}

	@Override
	public void updateAdvResourceModuleById(AdvResourceModule advResourceModule) {
		// TODO Auto-generated method stub
		if(advResourceModule != null && advResourceModule.getId() != null){
			advResourceModuleMapper.updateAdvResourceModuleById(advResourceModule);
		}
	}

	@Override
	public List<AdvPageType> getAdvPageTypeListByPid(Integer pid) {
		// TODO Auto-generated method stub
		if(pid != null){
			return advPageTypeMapper.getAdvPageTypeListByPid(pid);
		}
		return null;
	}

	@Override
	public Integer selectCountByPid(Integer pid) {
		// TODO Auto-generated method stub
		if(pid != null){
			return advPageTypeMapper.selectCountByPid(pid);
		}
		return 0;
	}

	@Override
	public AdvPageType getAdvPageTypeByPidAndName(Integer pid, String name) {
		// TODO Auto-generated method stub
		if(pid != null && StringUtils.isNoneBlank(name)){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("pid", pid);
			map.put("name", name);
			return advPageTypeMapper.getAdvPageTypeByPidAndName(map);
		}
		return null;
	}

	@Override
	public AdvPageType getAdvPageTypeByBh(String bh) {
		// TODO Auto-generated method stub
		if(StringUtils.isNoneBlank(bh)){
			return advPageTypeMapper.getAdvPageTypeByBh(bh);
		}
		return null;
	}

	@Override
	public void updateAdvImgListById(List<AdvImg> list) {
		// TODO Auto-generated method stub
		if(list != null && list.size() > 0){
			for(AdvImg advImg:list){
				this.updateAdvImgById(advImg);
			}
		}
	}

	@Override
	public void updateAdvProModuleListById(List<AdvProModule> advProModuleList) {
		// TODO Auto-generated method stub
		if(advProModuleList != null && advProModuleList.size() > 0){
			for(AdvProModule advProModule:advProModuleList){
				this.updateAdvProModuleById(advProModule);
			}
		}
	}

	@Override
	public AdvProModule getAdvProModuleByPidAndProId(Integer pid, Integer proId) {
		// TODO Auto-generated method stub
		if(pid == null || proId == null)return null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pid", pid);
		map.put("proId", proId);
		return advProModuleMapper.getAdvProModuleByPidAndProId(map);
		
	}

	@Override
	public void updateAdvResModuleListById(List<AdvResourceModule> list) {
		// TODO Auto-generated method stub
		if(list != null && list.size() > 0){
			for(AdvResourceModule res:list){
				this.updateAdvResourceModuleById(res);
			}
		}
	}

}
