package com.mystore.business.dao;

import java.util.List;

import com.mystore.business.dto.AdvImg;

public interface AdvImgMapper {
	
	public void addAdvImg(AdvImg advImg);
	
	public void updateAdvImgById(AdvImg advImg);
	
	public void deleteAdvImgById(Integer id);
	
	public void deleteAdvImgByPid(Integer pid);
	
	public AdvImg getAdvImgByid(Integer id);
	
	public List<AdvImg> getAdvImgListByPid(Integer pid);

}
