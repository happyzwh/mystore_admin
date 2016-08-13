package com.mystore.business.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.mystore.business.dto.Power;

public class PowerTag extends TagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer powerId;
	
	private String bh;
	
	@Override
	public int doStartTag() throws JspException {
        
		if(bh == null)return SKIP_BODY;
		
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		Object o = request.getSession().getAttribute(Constans.KEY_SESSION);
		UserSession userSession = (UserSession)o;
		List<Power> powerList = userSession.getUser().getPowerList();
		if(powerList == null || powerList.size() == 0)return SKIP_BODY;
		for(Power power:powerList){
			if(power.getBh().equals(bh)){
				return EVAL_BODY_INCLUDE;
			}
		}
	    return SKIP_BODY;
	}

	public Integer getPowerId() {
		return powerId;
	}

	public void setPowerId(Integer powerId) {
		this.powerId = powerId;
	}

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

}
