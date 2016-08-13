package com.mystore.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.dao.BrandInfoMapper;
import com.mystore.business.dao.BrandMapper;
import com.mystore.business.dto.Brand;
import com.mystore.business.dto.BrandInfo;
import com.mystore.business.service.BrandService;

@Service("brandService")
public class BrandServiceImpl implements BrandService{
	
	@Autowired
	private  BrandMapper brandMapper;
	
	@Autowired
	private  BrandInfoMapper brandInfoMapper;

	@Override
	public Integer addBrand(Brand brand) throws Exception {
		// TODO Auto-generated method stub
		
		if(brand != null){
			brandMapper.addBrand(brand);
			if(brand.getBrandInfo() != null){
				brand.getBrandInfo().setId_brand(brand.getId());
				brandInfoMapper.addBrandInfo(brand.getBrandInfo());
			}
		}
		
		return brand.getId();
	}

	@Override
	public void deleteBrandById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		if(id != null){
			brandInfoMapper.deleteBrandInfoByBid(id);
			brandMapper.deleteBrandById(id);
		}
	}

	@Override
	public Brand getBrandById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		if(id != null){
			return brandMapper.getBrandById(id);
		}
		return null;
	}

	@Override
	public Brand getBrandByPidAndName(Integer pid,String name) throws Exception {
		// TODO Auto-generated method stub
		if(pid == null || StringUtils.isBlank(name))return null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pid", pid);
		map.put("name", name);
		return brandMapper.getBrandByPidAndName(map);
	}

	@Override
	public List<Brand> selectBrandListByPid(Integer pid) throws Exception {
		// TODO Auto-generated method stub
		if(pid == null)return null;
		return brandMapper.selectBrandListByPid(pid);
	}

	@Override
	public Integer selectCountByPid(Integer pid) throws Exception {
		// TODO Auto-generated method stub
		if(pid == null)return 0;
		return brandMapper.selectCountByPid(pid);
	}

	@Override
	public void upateBrandById(Brand brand) throws Exception {
		// TODO Auto-generated method stub
		if(brand != null && brand.getId() != null){
			brandMapper.upateBrandById(brand);
			if(brand.getBrandInfo() != null){
				if(brandInfoMapper.getBrandInfoByBid(brand.getId()) != null){
					brand.getBrandInfo().setId_brand(brand.getId());
					brandInfoMapper.upateBrandInfoByBid(brand.getBrandInfo());
				}else{
					brandInfoMapper.addBrandInfo(brand.getBrandInfo());
				}
			}
		}
	}

	@Override
	public BrandInfo getBrandInfoByBid(Integer bid) throws Exception {
		// TODO Auto-generated method stub
		if(bid == null)return null;
		return brandInfoMapper.getBrandInfoByBid(bid);
	}

	@Override
	public void upateBrandInfoByBid(BrandInfo brandInfo) throws Exception {
		// TODO Auto-generated method stub
		if(brandInfo != null && brandInfo.getId_brand() != null){
			brandInfoMapper.upateBrandInfoByBid(brandInfo);
		}
	}
}
