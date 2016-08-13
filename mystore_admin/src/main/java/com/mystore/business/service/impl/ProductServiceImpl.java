package com.mystore.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.common.Pager;
import com.mystore.business.dao.ProAttrMapper;
import com.mystore.business.dao.ProImgMapper;
import com.mystore.business.dao.ProInfoMapper;
import com.mystore.business.dao.ProPriceMapper;
import com.mystore.business.dao.ProductMapper;
import com.mystore.business.dto.ProAttr;
import com.mystore.business.dto.ProImg;
import com.mystore.business.dto.ProInfo;
import com.mystore.business.dto.ProPrice;
import com.mystore.business.dto.Product;
import com.mystore.business.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private ProInfoMapper proInfoMapper;
	
	@Autowired
	private ProPriceMapper proPriceMapper;
	
	@Autowired
	private ProImgMapper proImgMapper;
	

	@Autowired
	private ProAttrMapper proAttrMapper;

	@Override
	public void addProduct(Product pro) throws Exception {
		// TODO Auto-generated method stub
		if(pro != null){
			productMapper.addProduct(pro);
		}
	}

	@Override
	public void deleteProById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		if(id != null){
			proImgMapper.deleteProImgByProId(id);
			proPriceMapper.deleteProPriceByProId(id);
			proInfoMapper.deleteProInfoByProId(id);
			proAttrMapper.deleteProAttrByProId(id);
			productMapper.deleteProById(id);
		}
	}

	@Override
	public Product getProById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		if(id == null)return null;
		return productMapper.getProById(id);
	}

	@Override
	public Product getProduct(String name, String sn) throws Exception {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(name) && StringUtils.isBlank(sn))return null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", name);
		map.put("sn", sn);
		return productMapper.getProduct(map);
	}

	@Override
	public int selectProCount(Product pro) throws Exception {
		// TODO Auto-generated method stub
		if(pro == null)return 0;
		return productMapper.selectProCount(pro);
	}

	@Override
	public Pager<Product> selectProList(Product pro,Integer pageNum,Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		if(pageNum != null && pageNum == 0)pageNum =1;
		if(pageSize != null && pageSize == 0)pageSize = 10;
		if(pageNum != null && pageSize != null){
			int pageIndex = (pageNum - 1)*pageSize;
			pro.setPageIndex(pageIndex);
			pro.setPageSize(pageSize);
		}
		Pager<Product> pager = new Pager<Product>();
		if(pageNum != null && pageSize != null){
			pager.setPageNo(pageNum);
			pager.setPageSize(pageSize);
		}
		int count = productMapper.selectProCount(pro);
		if(count == 0)return null;
		pager.setRowCount(count);
		if(pageNum != null && pageSize != null){
			pager.setPageCount(count%pageSize==0?(count/pageSize):(count/pageSize+1));
		}
		List<Product> list = productMapper.selectProList(pro);
		pager.setResultList(list);
		return pager;
	}

	@Override
	public void updateProById(Product pro) throws Exception {
		// TODO Auto-generated method stub
		if(pro != null && pro.getId() != null){
			productMapper.updateProById(pro);
		}
	}

	@Override
	public void addProInfo(ProInfo proInfo) throws Exception {
		// TODO Auto-generated method stub
		if(proInfo != null){
			proInfoMapper.addProInfo(proInfo);
		}
	}

	@Override
	public void deleteProInfoByProId(Integer proId) throws Exception {
		// TODO Auto-generated method stub
		if(proId != null){
			proInfoMapper.deleteProInfoByProId(proId);
		}
	}

	@Override
	public ProInfo getProInfoByProId(Integer proId) throws Exception {
		// TODO Auto-generated method stub
		if(proId == null)return null;
		return proInfoMapper.getProInfoByProId(proId);
	}

	@Override
	public void updateProInfoByProId(ProInfo proInfo) throws Exception {
		// TODO Auto-generated method stub
		if(proInfo != null && proInfo.getId_pro() != null){
			proInfoMapper.updateProInfoByProId(proInfo);
		}
	}

	@Override
	public void addProPrice(ProPrice proPrice) throws Exception {
		// TODO Auto-generated method stub
		if(proPrice != null){
			proPriceMapper.addProPrice(proPrice);
		}
	}

	@Override
	public void deleteProPriceByProId(Integer proId) throws Exception {
		// TODO Auto-generated method stub
		if(proId != null){
			proPriceMapper.deleteProPriceByProId(proId);
		}
	}

	@Override
	public ProPrice getProPriceByProId(Integer proId) throws Exception {
		// TODO Auto-generated method stub
		if(proId == null)return null;
		return proPriceMapper.getProPriceByProId(proId);
	}

	@Override
	public void updateProPriceByProId(ProPrice proPrice) throws Exception {
		// TODO Auto-generated method stub
		if(proPrice != null && proPrice.getId_pro() != null){
			proPriceMapper.updateProPriceByProId(proPrice);
		}
	}

	@Override
	public void addProImg(ProImg proImg) {
		// TODO Auto-generated method stub
		if(proImg != null){
			proImgMapper.addProImg(proImg);
		}
	}

	@Override
	public void deleteProImgById(Integer id) {
		// TODO Auto-generated method stub
		if(id != null){
			proImgMapper.deleteProImgById(id);
		}
	}

	@Override
	public List<ProImg> getProImgListByProId(Integer proId) {
		// TODO Auto-generated method stub
		if(proId == null)return null;
		return proImgMapper.getProImgListByProId(proId);
	}

	@Override
	public void updateProImgById(ProImg proImg) {
		// TODO Auto-generated method stub
		if(proImg != null && proImg.getId() != null){
			proImgMapper.updateProImgById(proImg);
		}
	}

	@Override
	public void updateProImgListById(List<ProImg> list) throws Exception {
		// TODO Auto-generated method stub
		if(list != null && list.size() > 0){
			for(ProImg img:list){
				updateProImgById(img);
			}
		}
	}

	@Override
	public void addProAttr(ProAttr proAttr) {
		// TODO Auto-generated method stub
		if(proAttr != null){
			proAttrMapper.addProAttr(proAttr);
		}
	}

	@Override
	public void deleteProAttrByProId(Integer proId) {
		// TODO Auto-generated method stub
		if(proId != null){
			proAttrMapper.deleteProAttrByProId(proId);
		}
	}

	@Override
	public List<ProAttr> getProAttrByProId(Integer proId) {
		// TODO Auto-generated method stub
		if(proId == null)return null;
		return proAttrMapper.getProAttrByProId(proId);
	}

	@Override
	public void updateProAttrById(ProAttr proAttr) {
		// TODO Auto-generated method stub
		if(proAttr != null && proAttr.getId() != null){
			proAttrMapper.updateProAttrById(proAttr);
		}
	}

	@Override
	public void addProAttrList(List<ProAttr> list) throws Exception {
		// TODO Auto-generated method stub
		if(list != null && list.size() > 0){
			for(ProAttr proAttr:list){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id_pro", proAttr.getId_pro());
				map.put("id_cateAttr", proAttr.getId_cateAttr());
				ProAttr proAttrQu = proAttrMapper.getProAttrByMap(map);
				if(proAttrQu != null){
					proAttr.setId(proAttrQu.getId());
					proAttrMapper.updateProAttrById(proAttr);
				}else{
					proAttrMapper.addProAttr(proAttr);
				}
			}
		}
	}

	@Override
	public void updateBatchProIsOnSale(List<Integer> idList) throws Exception {
		// TODO Auto-generated method stub
		if(idList != null && idList.size() > 0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("idList", idList);
			productMapper.updateBatchProIsOnSale(map);
		}
	}

	@Override
	public List<Map<String, Object>> selectProListByLikeNameOrSn(String name) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(name))return null;
		return productMapper.selectProListByLikeNameOrSn(name);
	}
	
}
