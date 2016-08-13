package com.mystore.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.common.Pager;
import com.mystore.business.dao.BasicAttriMapper;
import com.mystore.business.dao.BasicAttriValueMapper;
import com.mystore.business.dao.CateAttriMapper;
import com.mystore.business.dao.ProAttrMapper;
import com.mystore.business.dto.BasicAttri;
import com.mystore.business.dto.BasicAttriValue;
import com.mystore.business.dto.CateAttri;
import com.mystore.business.service.BasicAttriService;

@Service("basicAttriService")
public class BasicAttriServiceImpl implements BasicAttriService{
	
	@Autowired
	private BasicAttriMapper basicAttriMapper;
	
	@Autowired
	private BasicAttriValueMapper basicAttriValueMapper;
	
	@Autowired
	private CateAttriMapper cateAttriMapper;
	
	@Autowired
	private ProAttrMapper proAttrMapper;

	@Override
	public void addBasicAttri(BasicAttri basicAttri) throws Exception {
		// TODO Auto-generated method stub
		if(basicAttri != null){
			basicAttriMapper.addBasicAttri(basicAttri);
		}
	}

	@Override
	public void deleteBasicAttriById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		if(id != null){
			BasicAttri basicAttri = basicAttriMapper.getBasicAttriById(id);
			if(basicAttri != null){
				List<CateAttri> list = cateAttriMapper.selectCateAttriListByAttrId(id);
				if(list != null && list.size() > 0){
					for(CateAttri cateAttri:list){
						proAttrMapper.deleteProAttrByCateAttrId(cateAttri.getId());
					}
				}
		        CateAttri cateAttr = new CateAttri();
		        cateAttr.setId_attr(id);
			    cateAttriMapper.deleteCateAttri(cateAttr);
				basicAttriValueMapper.deleteBasicAttriValueByPid(basicAttri.getId());
				basicAttriMapper.deleteBasicAttriById(id);
			}
		}
	}

	@Override
	public BasicAttri getBasicAttriById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		if(id == null)return null;
		return basicAttriMapper.getBasicAttriById(id);
	}

	@Override
	public Pager<BasicAttri> getBasicAttriList(BasicAttri basicAttri,
			Integer pageNum, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		if(pageNum != null && pageNum == 0)pageNum =1;
		if(pageSize != null && pageSize == 0)pageSize = 10;
		if(pageNum != null && pageSize != null){
			int pageIndex = (pageNum - 1)*pageSize;
			basicAttri.setPageIndex(pageIndex);
			basicAttri.setPageSize(pageSize);
		}
		Pager<BasicAttri> pager = new Pager<BasicAttri>();
		if(pageNum != null && pageSize != null){
			pager.setPageNo(pageNum);
			pager.setPageSize(pageSize);
		}
		int count = basicAttriMapper.getBasicAttriCount(basicAttri);
		if(count == 0)return null;
		pager.setRowCount(count);
		if(pageNum != null && pageSize != null){
			pager.setPageCount(count%pageSize==0?(count/pageSize):(count/pageSize+1));
		}
		List<BasicAttri> list = basicAttriMapper.getBasicAttriList(basicAttri);
		pager.setResultList(list);
		return pager;
	}

	@Override
	public void updateBasicAttriById(BasicAttri basicAttri) throws Exception {
		// TODO Auto-generated method stub
		if(basicAttri != null && basicAttri.getId() != null){
			basicAttriMapper.updateBasicAttriById(basicAttri);
		}
	}

	@Override
	public void addBasicAttriValue(BasicAttriValue basicAttriValue) {
		// TODO Auto-generated method stub
		if(basicAttriValue != null){
			basicAttriValueMapper.addBasicAttriValue(basicAttriValue);
		}
	}

	@Override
	public void deleteBasicAttriValueById(Integer id) {
		// TODO Auto-generated method stub
		if(id != null){
			basicAttriValueMapper.deleteBasicAttriValueById(id);
		}
	}

	@Override
	public BasicAttriValue getBasicAttriValueById(Integer id) {
		// TODO Auto-generated method stub
		if(id == null)return null;
		return basicAttriValueMapper.getBasicAttriValueById(id);
	}

	@Override
	public List<BasicAttriValue> getBasicAttriValueList(
			BasicAttriValue basicAttriValue) {
		// TODO Auto-generated method stub
		return basicAttriValueMapper.getBasicAttriValueList(basicAttriValue);
	}

	@Override
	public void updateBasicAttriValueById(BasicAttriValue basicAttriValue) {
		// TODO Auto-generated method stub
		if(basicAttriValue != null && basicAttriValue.getId() != null){
			basicAttriValueMapper.updateBasicAttriValueById(basicAttriValue);
		}
	}

	@Override
	public BasicAttri getBasicAttriByName(String name) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(name))return null;
		return basicAttriMapper.getBasicAttriByName(name);
	}

	@Override
	public BasicAttriValue getBasicAttriValueByValue(Integer pid, String value) {
		// TODO Auto-generated method stub
		if(pid == null || StringUtils.isBlank(value))return null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pid", pid);
		map.put("value", value);
		return basicAttriValueMapper.getBasicAttriValueByValue(map);
	}

}
