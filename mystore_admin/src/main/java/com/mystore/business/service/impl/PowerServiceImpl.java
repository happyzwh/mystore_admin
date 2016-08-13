package com.mystore.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.dao.PowerMapper;
import com.mystore.business.dao.RolePowerMapper;
import com.mystore.business.dao.UserPowerMapper;
import com.mystore.business.dto.Power;
import com.mystore.business.dto.RolePower;
import com.mystore.business.dto.UserPower;
import com.mystore.business.service.PowerService;

@Service("powerService")
public class PowerServiceImpl implements PowerService{
	
	@Autowired
	private PowerMapper powerMapper;
	
	@Autowired
	private UserPowerMapper userPowerMapper;
	
	@Autowired
	private RolePowerMapper rolePowerMapper;

	@Override
	public Integer addPower(Power power) throws Exception{
		// TODO Auto-generated method stub
		if(power != null){
			powerMapper.addPower(power);
		}
		
		return power.getId();
	}

	@Override
	public Integer selectCountByPid(Integer pid) throws Exception{
		// TODO Auto-generated method stub
		return powerMapper.selectCountByPid(pid);
	}

	@Override
	public Power selectPowerById(Integer id) throws Exception{
		// TODO Auto-generated method stub
		return powerMapper.selectPowerById(id);
	}
	
	@Override
	public Power selectPowerByBh(String bh) throws Exception{
		// TODO Auto-generated method stub
		return powerMapper.selectPowerByBh(bh);
	}

	@Override
	public List<Power> selectPowerByPid(Integer pid) throws Exception{
		// TODO Auto-generated method stub
		return powerMapper.selectPowerByPid(pid);
	}

	@Override
	public void updatePowerById(Power power) throws Exception{
		// TODO Auto-generated method stub
		if(power != null && power.getId() != null){
			powerMapper.updatePowerById(power);
		}
	}

	@Override
	public void deletePowerById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		if(id != null){
			
			UserPower userPower = new UserPower();
			userPower.setPowerId(id);
			userPowerMapper.deleteUserPower(userPower);
			
			RolePower rolePower = new RolePower();
			rolePower.setPowerId(id);
			rolePowerMapper.deleteRolePower(rolePower);
			
			List<Power> list = powerMapper.selectPowerByPid(id);
			if(list != null && list.size() > 0){
				for(Power power:list){
					deletePowerById(power.getId());
				}
			}
			powerMapper.deletePowerById(id);
		}
	}

	@Override
	public Power selectPowerByPidAndName(Integer pid, String name) {
		// TODO Auto-generated method stub
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("pid", pid);
		paraMap.put("name", name);
		return powerMapper.selectPowerByPidAndName(paraMap);
	}

	@Override
	public List<Power> selectPowerByUid(Integer uid) {
		// TODO Auto-generated method stub
		return powerMapper.selectPowerByUid(uid);
	}

	@Override
	public List<Power> selectAllPower() {
		// TODO Auto-generated method stub
		return powerMapper.selectAllPower();
	}

}
