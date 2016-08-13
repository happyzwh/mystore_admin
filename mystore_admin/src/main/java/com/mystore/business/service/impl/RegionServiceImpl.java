package com.mystore.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.dao.RegionMapper;
import com.mystore.business.dto.Region;
import com.mystore.business.service.RegionService;
@Service("regionService")
public class RegionServiceImpl implements RegionService{

	@Autowired
	private  RegionMapper regionMapper;
	
	@Override
	public Integer addRegion(Region region) throws Exception {
		// TODO Auto-generated method stub
		if(region != null){
			regionMapper.addRegion(region);
		}
		return region.getId();
	}

	@Override
	public void deleteRegionById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		if(id != null){
			List<Region> list = regionMapper.selectRegionListByPid(id);
			if(list != null && list.size() > 0){
				for(Region region:list){
					deleteRegionById(region.getId());
				}
			}
			regionMapper.deleteRegionById(id);
		}
	}

	@Override
	public Region getRegionById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		if(id == null)return null;
		return regionMapper.getRegionById(id);
	}

	@Override
	public Region getRegionByPidAndName(Integer pid,String name)
			throws Exception {
		// TODO Auto-generated method stub
		if(pid == null || StringUtils.isBlank(name))return null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pid", pid);
		map.put("name", name);
		return regionMapper.getRegionByPidAndName(map);
	}

	@Override
	public Integer selectCountByPid(Integer pid) throws Exception {
		// TODO Auto-generated method stub
		if(pid == null)return 0;
		return regionMapper.selectCountByPid(pid);
	}

	@Override
	public List<Region> selectRegionListByPid(Integer pid) throws Exception {
		// TODO Auto-generated method stub
		if(pid == null)return null;
		return regionMapper.selectRegionListByPid(pid);
	}

	@Override
	public void upateRegionById(Region region) throws Exception {
		// TODO Auto-generated method stub
		if(region != null && region.getId() != null){
			regionMapper.upateRegionById(region);
		}
		
	}

}
