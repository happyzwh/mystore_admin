package com.mystore.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.dao.CateAttriMapper;
import com.mystore.business.dao.CategoryMapper;
import com.mystore.business.dao.ProAttrMapper;
import com.mystore.business.dto.CateAttri;
import com.mystore.business.dto.Category;
import com.mystore.business.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private  CategoryMapper categoryMapper;
	
	@Autowired
	private  CateAttriMapper cateAttriMapper;
	
	@Autowired
	private  ProAttrMapper proAttrMapper;
	
	@Override
	public Integer addCategory(Category cate) throws Exception {
		// TODO Auto-generated method stub
		if(cate != null){
			categoryMapper.addCategory(cate);
		}
		return cate.getId();
	}

	@Override
	public void deleteCateById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		if(id != null){
			List<Category> list = categoryMapper.selectCateListByPid(id);
			if(list != null && list.size() > 0){
				for(Category cate:list){
					deleteCateById(cate.getId());
				}
			}
			categoryMapper.deleteCateById(id);
		}
	}

	@Override
	public Category getCateById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		if(id == null)return null;
		return categoryMapper.getCateById(id);
	}

	@Override
	public Category getCateByPidAndName(Integer pid, String name)
			throws Exception {
		// TODO Auto-generated method stub
		if(pid == null || StringUtils.isBlank(name))return null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pid", pid);
		map.put("name", name);
		return categoryMapper.getCateByPidAndName(map);
	}

	@Override
	public void upateCateById(Category cate) throws Exception {
		// TODO Auto-generated method stub
		if(cate != null){
			categoryMapper.upateCateById(cate);
		}
	}

	@Override
	public List<Category> selectCateListByPid(Integer pid) throws Exception {
		// TODO Auto-generated method stub
		if(pid != null){
			return categoryMapper.selectCateListByPid(pid);
		}
		return null;
	}

	@Override
	public Integer selectCountByPid(Integer pid) {
		// TODO Auto-generated method stub
		if(pid != null){
			return categoryMapper.selectCountByPid(pid);
		}
		return 0;
	}

	@Override
	public void addCateAttri(List<CateAttri> list) {
		// TODO Auto-generated method stub
		if(list != null && list.size() >0){
			
			List<CateAttri> cateAttris = cateAttriMapper.selectCateAttriListByCateId(list.get(0).getId_cate());
		    if(cateAttris == null || cateAttris.size() == 0){
				for(CateAttri cateAttri:list){
					cateAttriMapper.addCateAttri(cateAttri);
				}
		    }else{
		    	
		    	for(CateAttri cateAttriOld:cateAttris){
		    		boolean tf = false;
		    		for(CateAttri cateAttriNew:list){
		    			if(cateAttriNew.getId_attr().intValue() == cateAttriOld.getId_attr().intValue()){
		    				cateAttriMapper.updateCateAttri(cateAttriNew);
		    				tf = true;
		    				break;
		    			}
		    		}
		    		if(!tf){
		    			proAttrMapper.deleteProAttrByCateAttrId(cateAttriOld.getId());
		    			cateAttriMapper.deleteCateAttri(cateAttriOld);	
		    		}
		    		
		    	}
		    	
		    	for(CateAttri cateAttriNew:list){
		    		boolean tf = false;
		    		for(CateAttri cateAttriOld:cateAttris){
		    			if(cateAttriNew.getId_attr().intValue() == cateAttriOld.getId_attr().intValue()){
		    				tf = true;
		    				break;
		    			}
		    		}
		    		if(!tf){
		    			cateAttriMapper.addCateAttri(cateAttriNew);
		    		}
		    	}
		    }
		}
	}

	@Override
	public void deleteCateAttri(CateAttri cateAttri) {
		// TODO Auto-generated method stub
		if(cateAttri != null && ( cateAttri.getId_attr() != null || cateAttri.getId_cate() != null)){
			cateAttriMapper.deleteCateAttri(cateAttri);
		}
	}

	@Override
	public List<CateAttri> selectCateAttriListByCateId(Integer cid) {
		// TODO Auto-generated method stub
		if(cid == null)return null;
		return cateAttriMapper.selectCateAttriListByCateId(cid);
	}

}
